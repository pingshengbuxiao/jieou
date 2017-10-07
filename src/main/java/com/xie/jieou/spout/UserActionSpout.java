package com.xie.jieou.spout;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import com.xie.jieou.redis.JedisSubscribe;
import com.xie.jieou.redis.JedisSubscribeThread;
import com.xie.jieou.utils.WordUtils;

import backtype.storm.spout.SpoutOutputCollector;
import backtype.storm.task.TopologyContext;
import backtype.storm.topology.IRichSpout;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Values;
import edu.emory.mathcs.backport.java.util.Arrays;
import redis.clients.jedis.Jedis;

public class UserActionSpout implements IRichSpout {

	private SpoutOutputCollector collector;
	private Integer tastId;
	private ConcurrentLinkedQueue<String> messageQueue = null;
	
	
	private static Logger log = (Logger) LogManager.getLogger(UserActionSpout.class);
	
	public void declareOutputFields(OutputFieldsDeclarer declare) {
		declare.declare(new Fields("word"));
	}
	
	public void nextTuple() {
		while(!messageQueue.isEmpty()) {
			String message = messageQueue.poll();
			log.info("spout message : "+message+" size : "+messageQueue.size());
			collector.emit(new Values(message));
		}
	}

	public void open(Map arg0, TopologyContext context, SpoutOutputCollector collector) {
		this.collector = collector;
		tastId = context.getThisTaskId();
		JedisSubscribe subHandler = new JedisSubscribe();
		JedisSubscribeThread subscribe = new JedisSubscribeThread("consumer", subHandler);
		Thread subThread = new Thread(subscribe);
		subThread.start();
		this.messageQueue = subHandler.getMessageQueue();
	}

	public void ack(Object arg0) {
		
	}

	public void activate() {

	}

	public void close() {

	}

	public void deactivate() {

	}

	public void fail(Object arg0) {

	}


	public Map<String, Object> getComponentConfiguration() {
		return null;
	}

}
