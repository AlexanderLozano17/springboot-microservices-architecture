package com.kafka.producer.config;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaProducerUtils {

	private final KafkaTemplate<String, Object> kafkaTemplate;
	
	public KafkaProducerUtils(KafkaTemplate<String, Object> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}
	
	public void send(String topic, String key, Object message) {
		this.kafkaTemplate.send(topic, key, message);
	}
	
	public void send(String topic, Object message) {
		this.kafkaTemplate.send(topic, message);
	}
}
