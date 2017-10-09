package com.xie.jieou.topology;

import com.xie.jieou.bolt.UserActionBolt;
import com.xie.jieou.spout.UserActionSpout;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.StormSubmitter;
import backtype.storm.topology.TopologyBuilder;

public class UserActionTopology {

	public static void main(String[] args) {
		try {
			TopologyBuilder builder = new TopologyBuilder();
			builder.setSpout("userActionSpout", new UserActionSpout(), 3);
			builder.setBolt("userActionBolt", new UserActionBolt(),2).shuffleGrouping("userActionSpout");

			Config config = new Config();
			config.setDebug(false);
			if (args != null && args.length > 0) {
				config.setNumWorkers(1);
				StormSubmitter.submitTopology(args[0], config, builder.createTopology());
			} else {
				// 这里是本地模式下运行的启动代码。
				config.setMaxTaskParallelism(1);
				LocalCluster cluster = new LocalCluster();
				cluster.submitTopology("simple", config, builder.createTopology());
				System.out.println("end submit");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
