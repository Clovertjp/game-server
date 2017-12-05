package com.game.common.server.push;

import java.util.List;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import org.eclipse.jetty.util.BlockingArrayQueue;

import com.game.common.server.config.Config;
import com.game.common.server.session.GameSession;
import com.game.common.server.thread.GameThreadFactory;
import com.google.protobuf.Message;

/**
 * @author tangjp
 *
 */
public class GamePushManager {
	
	private static GamePushManager pushManager=new GamePushManager();
	public static GamePushManager getInstance() {
		return pushManager;
	}
	private GamePushManager() {
		
	}
	
	private static Queue<PushMessage> pushQueue=new LinkedBlockingQueue<>();
	
	private static ExecutorService executor=Executors.newFixedThreadPool(Config.PUSH_QUEUE_THREAD,
			new GameThreadFactory("GamePushManagerQueue"));
	
	
	public void PushOne(String pushUid,String toUid,String cmd,Message pushMessage) {
		
	}
	
	
	
	
	
	
	
	
	private class PushMessage{
		private String pushCmd;
		private List<GameSession> pushSessionList;
		private Message pushMessage;
		public PushMessage(String pushCmd, List<GameSession> pushSessionList, Message pushMessage) {
			this.pushCmd = pushCmd;
			this.pushSessionList = pushSessionList;
			this.pushMessage = pushMessage;
		}
		public String getPushCmd() {
			return pushCmd;
		}
		public void setPushCmd(String pushCmd) {
			this.pushCmd = pushCmd;
		}
		public List<GameSession> getPushSessionList() {
			return pushSessionList;
		}
		public void setPushSessionList(List<GameSession> pushSessionList) {
			this.pushSessionList = pushSessionList;
		}
		public Message getPushMessage() {
			return pushMessage;
		}
		public void setPushMessage(Message pushMessage) {
			this.pushMessage = pushMessage;
		}
	}
	
}


