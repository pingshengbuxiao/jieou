package com.xie.jieou.redis;

import redis.clients.jedis.JedisPubSub;

import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;


public class JedisSubscribe extends JedisPubSub {
	private  ConcurrentLinkedQueue<String> messageQueue = new ConcurrentLinkedQueue<String>();
	
	private static Logger log = (Logger) LogManager.getLogger(JedisSubscribe.class);
	@Override
	public void onMessage(String channel, String message) {
		System.out.println(String.format("we get from channel %s message is  %s ", channel,message));
		messageQueue.add(message);
		log.info("the queue : "+messageQueue.size());
		log.info("we get from channel {} message is  {} ",channel,message);
	}
	
	public  ConcurrentLinkedQueue<String> getMessageQueue(){
		return messageQueue;
	}
	
}
