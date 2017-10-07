package com.xie.jieou.utils;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

public class JedisClusterUtil {
	private static JedisCluster jedisCluster;
	
	static {
		Set<HostAndPort> nodes = new HashSet<>();
		nodes.add(new HostAndPort("101.200.155.15", 7001));
		jedisCluster = new JedisCluster(nodes,1000,1000,1000,"wzproot",new GenericObjectPoolConfig());	
	}
	
	public static void setex(String key,int seconds,String value) {
		jedisCluster.setex(key, seconds, value);
	}
	
	public static void main (String args[]){
		for(int i=0;i<100000;i++){
			System.out.println(i);
			setex("key:"+i, 60, "value:"+i);
		}
	}
}
