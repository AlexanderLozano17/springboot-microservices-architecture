package com.kafka.entity;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

@Data
public class ListTopicEntity implements Serializable {

	List<TopicEntity> listTopic;

	
	@Data
	public static class TopicEntity implements Serializable {

		private static final long serialVersionUID = 1L;
		private String name;
		private int partitions = 3;
		private int replicas = 1;
	}
}