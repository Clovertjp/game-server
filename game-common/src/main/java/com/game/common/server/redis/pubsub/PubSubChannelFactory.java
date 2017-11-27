package com.game.common.server.redis.pubsub;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.game.common.server.scheduler.*;
import com.game.common.server.redis.R;
import com.game.common.server.redis.pubsub.channel.HotSwapChannel;
import com.game.common.server.redis.pubsub.channel.RefreshConfigChannel;
import com.google.common.base.Strings;

import redis.clients.jedis.JedisPubSub;

/**
 * @author tangjp
 *
 */
public class PubSubChannelFactory {
	
	private static PubSubChannelFactory factory=new PubSubChannelFactory();
	
	private static final Logger logger = LogManager.getLogger(PubSubChannelFactory.class);
	
	private static Map<String,Class<? extends IPubSubChannel>> channels=new HashMap<>();
	
	public JedisPubSub listener=new GamePubSub();
	
	private PubSubChannelFactory(){
		
	}
	
	public static PubSubChannelFactory getInstance(){
		return factory;
	}
	
	public void init(){
		addMap();
		subscribe();
	}
	
	private void addMap(){
		channels.put(HotSwapChannel.class.getSimpleName(), HotSwapChannel.class);
		channels.put(RefreshConfigChannel.class.getSimpleName(), RefreshConfigChannel.class);
	}
	
	private void subscribe(){
		GameScheduler.getInstance().getThreadExecutor().execute(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
		            logger.info("Pub/sub subscribe global channel");
		            R.getLocalRedis().subscribe(listener, new ArrayList<>(channels.keySet()));
		        } catch (Exception e) {
		            logger.error("Pub/sub subscribe error", e);
		        }
			}
		});
	}
	
	public IPubSubChannel getChannel(String channelName) throws InstantiationException, IllegalAccessException{
		if(Strings.isNullOrEmpty(channelName) || !channels.containsKey(channelName)){
			return null;
		}
		return channels.get(channelName).newInstance();
	}

}
