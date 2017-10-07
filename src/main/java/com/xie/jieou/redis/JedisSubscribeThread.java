package com.xie.jieou.redis;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.LogManager;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPubSub;

public class JedisSubscribeThread implements Runnable{
	private JedisCluster jedisCluster;
	private JedisPubSub jedisPubSub;
	private String channel;
	
	private final Logger log = (Logger) LogManager.getLogger(JedisSubscribeThread.class);
	/**
	 * @param channel 需要订阅的频道名
	 */
	public JedisSubscribeThread(String channel,JedisPubSub jedisPubSub) {
		this.channel = channel;
		this.jedisPubSub = jedisPubSub;
		Set<HostAndPort> nodes = new HashSet<>();
		nodes.add(new HostAndPort("101.200.155.15", 7001));
		this.jedisCluster = new JedisCluster(nodes,1000,1000,1000,"wzproot",new GenericObjectPoolConfig());	
	}
	
	@Override
	public void run() {
		log.info("start to get channel message ");
		try {
			jedisCluster.subscribe(jedisPubSub, channel);
		}catch (Exception e){
			log.info("excpeiton catch");
		}
	}
	
	
}
