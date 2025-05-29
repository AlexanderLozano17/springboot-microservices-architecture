package com.kafka.service;

import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.kafka.properties.KafkaProperties;

@Service
public class KafkaTopicService {

	private final Logger logger = LoggerFactory.getLogger(KafkaTopicService.class);
	
	private final KafkaProperties properties;
	
	public KafkaTopicService(KafkaProperties properties) {
		this.properties = properties;
	}
	
	public Set<String> getAllTopics() {
		logger.info("→ START | " + KafkaTopicService.class.getName() +"::getAllTopics()");
		
		Properties config = new Properties();
		config.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, properties.getBootstrapServers());
		
		try (AdminClient adminClient = AdminClient.create(config)) {
			logger.info("✓ SUCCESS | " + KafkaTopicService.class.getName() +"::getAllTopics()");
			ListTopicsResult topics = adminClient.listTopics();
			logger.info("← END | " + KafkaTopicService.class.getName() +"::getAllTopics()");
			return topics.names().get();
			
		} catch (InterruptedException | ExecutionException e) {
			logger.info("✖ ERROR | " + KafkaTopicService.class.getName() +"::getAllTopics() - {}",  e.getMessage() );
			logger.info("← END  | " + KafkaTopicService.class.getName() +"::getAllTopics()");
			throw new RuntimeException("Error fetching Kafka topics", e);
		}
	}
}
