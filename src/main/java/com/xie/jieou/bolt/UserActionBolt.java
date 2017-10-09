package com.xie.jieou.bolt;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import com.xie.jieou.spout.UserActionSpout;
import com.xie.jieou.utils.JedisClusterUtil;

import backtype.storm.task.TopologyContext;
import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.IBasicBolt;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.tuple.Tuple;

public class UserActionBolt implements IBasicBolt {

	
	
	private static Logger log = (Logger) LogManager.getLogger(UserActionBolt.class);
	public void execute(Tuple input, BasicOutputCollector collector) {
		String word = input.getString(0);
		log.info("bolt get message is: "+word);

		log.info("bolt get message is master: "+word);
		
		log.info("we got a bug "+word);
		log.info("we got a bug "+word);

		
		JedisClusterUtil.setex(word, 120, "value:"+word);
	}
	public void declareOutputFields(OutputFieldsDeclarer declarer) {
		// TODO Auto-generated method stub
		
	}

	public Map<String, Object> getComponentConfiguration() {
		// TODO Auto-generated method stub
		return null;
	}

	public void prepare(Map stormConf, TopologyContext context) {
		// TODO Auto-generated method stub
		
	}


	public void cleanup() {
		// TODO Auto-generated method stub
		
	}

}
