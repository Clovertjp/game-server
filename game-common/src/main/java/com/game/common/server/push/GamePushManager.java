package com.game.common.server.push;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.util.BlockingArrayQueue;

import com.game.common.server.config.Config;
import com.game.common.server.engine.GameEngine;
import com.game.common.server.handler.GameHandlerManager;
import com.game.common.server.palyer.GamePlayer;
import com.game.common.server.session.GameSession;
import com.game.common.server.thread.GameThreadFactory;
import com.game.pb.server.message.MessageObj;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.protobuf.Message;
import com.googlecode.protobuf.format.JsonFormat;

/**
 * @author tangjp
 *
 */
public class GamePushManager {
	
	private static final Logger logger = LogManager.getLogger(GamePushManager.class);
	
	private static JsonFormat format=new JsonFormat();
	private static final String SEPARATOR=" | ";
	private static final String PUSH_STR="PUSH";
	private static final String SYSTEM_UID="0000";
	
	private static GamePushManager pushManager=new GamePushManager();
	public static GamePushManager getInstance() {
		return pushManager;
	}
	private GamePushManager() {
		
	}
	
	private static ExecutorService executor=new ThreadPoolExecutor(Config.PUSH_QUEUE_THREAD, Config.PUSH_QUEUE_MAX_THREAD,
            0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(20),
            new GameThreadFactory("GamePushManagerQueue"));
	
	
	public void pushOne(String pushUid,String toUid,String cmd,Message pushMessage) {
		GamePlayer player=GameEngine.getInstance().findPlayerById(toUid);
		List<GamePlayer> list=new ArrayList<>();
		list.add(player);
		PushMessage message=new PushMessage(cmd,pushUid,toUid, list, pushMessage);
		addTask(message);
	}
	
	public void systemPushOne(String toUid,String cmd,Message pushMessage) {
		pushOne(SYSTEM_UID, toUid, cmd, pushMessage);
	}
	
	public void pushOne(String pushUid,GamePlayer toPlayer,String cmd,Message pushMessage) {
		List<GamePlayer> list=new ArrayList<>();
		list.add(toPlayer);
		PushMessage message=new PushMessage(cmd,pushUid,toPlayer.getUid(), list, pushMessage);
		addTask(message);
	}
	
	public void systemPushOne(GamePlayer toPlayer,String cmd,Message pushMessage) {
		pushOne(SYSTEM_UID, toPlayer, cmd, pushMessage);
	}
	
	public void pushList(String pushUid,List<String> toUidList,String cmd,Message pushMessage) {
		List<GamePlayer> list=new ArrayList<>();
		for(String toUid : toUidList) {
			GamePlayer player=GameEngine.getInstance().findPlayerById(toUid);
			if(player==null) {
				continue;
			}
			list.add(player);
		}
		PushMessage message=new PushMessage(cmd,pushUid,Joiner.on(",").join(toUidList), list, pushMessage);
		addTask(message);
	}
	
	public void systemPushList(List<String> toUidList,String cmd,Message pushMessage) {
		pushList(SYSTEM_UID, toUidList, cmd, pushMessage);
	}
	
	public void pushListWithPlayer(String pushUid,List<GamePlayer> toPlayerList,String cmd,Message pushMessage) {
		List<String> list=new ArrayList<>();
		for(GamePlayer toPlayer : toPlayerList) {
			list.add(toPlayer.getUid());
		}
		PushMessage message=new PushMessage(cmd,pushUid,Joiner.on(",").join(list), toPlayerList, pushMessage);
		addTask(message);
	}
	
	public void systemPushListWithPlayer(List<GamePlayer> toPlayerList,String cmd,Message pushMessage) {
		pushListWithPlayer(SYSTEM_UID, toPlayerList, cmd, pushMessage);
	}
	
	public void pushAll(String pushUid,String cmd,Message pushMessage) {
		PushMessage message=new PushMessage(cmd,pushUid,Joiner.on(",").join(GameEngine.getInstance().getGamePlayerMap().keySet())
				, new ArrayList<>(GameEngine.getInstance().getGamePlayerMap().values()), pushMessage);
		addTask(message);
	}
	
	public void systemPushAll(String cmd,Message pushMessage) {
		pushAll(SYSTEM_UID, cmd, pushMessage);
	}
	
	private void addTask(PushMessage message) {
		executor.execute(new PushTaskRunnable(message));
	}
	
	private class PushTaskRunnable implements Runnable{
		
		public PushMessage pushMessage;
		public PushTaskRunnable(PushMessage pushMessage) {
			this.pushMessage = pushMessage;
		}

		@Override
		public void run() {
			try {
				MessageObj.NetMessage ret=MessageObj.NetMessage.newBuilder().setClassData(pushMessage.getPushMessage().toByteString())
						.setClassName(pushMessage.getPushMessage().getClass().getSimpleName()).setUid("").build();
				StringBuilder sb=new StringBuilder();
				sb.append(PUSH_STR).append(SEPARATOR)
				.append(pushMessage.getPushUid()).append(SEPARATOR)
				.append("%s").append(SEPARATOR)
				.append(pushMessage.getPushCmd());
				String strFormat=sb.toString();
				for(GamePlayer gamePlayer : pushMessage.getPushGamePlayerList()) {
					GameSession session=gamePlayer.getGameSession();
					if(session==null || session.getChannel()==null) {
						continue;
					}
					session.getChannel().writeAndFlush(ret);
					logger.info(String.format(strFormat, gamePlayer.getUid()));
				} 
				
			}catch (Exception e) {
				logger.error("push error",e);
			}finally {
				logger.info(pushMessage);
			}
		}
		
	}
	
	
	private class PushMessage{		
		private String pushCmd;
		private List<GamePlayer> pushGamePlayerList;
		private String pushUid;
		private Message pushMessage;
		private String toUids;
		public PushMessage(String pushCmd,String pushUid,String toUids, List<GamePlayer> pushGamePlayerList, Message pushMessage) {
			this.pushCmd = pushCmd;
			this.pushGamePlayerList = pushGamePlayerList;
			this.pushMessage = pushMessage;
			this.pushUid=pushUid;
			this.toUids=toUids;
		}
		public String getPushCmd() {
			return pushCmd;
		}
		public void setPushCmd(String pushCmd) {
			this.pushCmd = pushCmd;
		}
		public List<GamePlayer> getPushGamePlayerList() {
			return pushGamePlayerList;
		}
		public void setPushGamePlayerList(List<GamePlayer> pushGamePlayerList) {
			this.pushGamePlayerList = pushGamePlayerList;
		}
		public Message getPushMessage() {
			return pushMessage;
		}
		public void setPushMessage(Message pushMessage) {
			this.pushMessage = pushMessage;
		}
		
		public String getPushUid() {
			return pushUid;
		}
		public void setPushUid(String pushUid) {
			this.pushUid = pushUid;
		}
		public String getToUids() {
			return toUids;
		}
		public void setToUids(String toUids) {
			this.toUids = toUids;
		}
		@Override
		public String toString() {
			StringBuilder sb=new StringBuilder();
			sb.append(PUSH_STR).append(SEPARATOR)
			.append(pushUid).append(SEPARATOR)
			.append(toUids).append(SEPARATOR)
			.append(pushCmd).append(SEPARATOR)
			.append(format.printToString(pushMessage));
			return sb.toString();
		}
	}
	
}


