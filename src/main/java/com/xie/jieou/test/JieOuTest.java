package com.xie.jieou.test;

import java.util.concurrent.ConcurrentLinkedQueue;

import com.xie.jieou.redis.JedisSubscribe;
import com.xie.jieou.redis.JedisSubscribeThread;

public class JieOuTest {
	
	private ConcurrentLinkedQueue<String> messageQueue = null;
	
	public static void main(String args[]) {
		JieOuTest test = new JieOuTest();
		test.setHello();
		test.start();
	}
	
	public void setHello() {
		JedisSubscribe subHandler = new JedisSubscribe();
		JedisSubscribeThread subscribe = new JedisSubscribeThread("consumer", subHandler);
		Thread subThread = new Thread(subscribe);
		subThread.start();
		this.messageQueue = subHandler.getMessageQueue();
	}
	
	public void start() {
		while(true) {
			while (!messageQueue.isEmpty()){
				String message = messageQueue.poll();
				System.out.println("message : "+message+" size : "+messageQueue.size());
			}
		}
		
	}
}
