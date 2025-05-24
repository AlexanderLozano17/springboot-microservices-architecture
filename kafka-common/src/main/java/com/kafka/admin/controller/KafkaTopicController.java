package com.kafka.admin.controller;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/kafka")
public class KafkaTopicController {

	private final KafkaAdmin kafkaAdmin;
	
	public KafkaTopicController(KafkaAdmin kafkaAdmin) {
		this.kafkaAdmin = kafkaAdmin;
	}
	
	@PostMapping("/create-topic")
	public ResponseEntity<String> createTopic(@RequestParam String name,
											  @RequestParam(defaultValue = "3") int partitions,
											  @RequestParam(defaultValue = "1") int replicas) {
		
		NewTopic topic = TopicBuilder.name(name)
				.partitions(partitions)
				.replicas(replicas)
				.build();
		
		try {
			kafkaAdmin.createOrModifyTopics(topic);
			return ResponseEntity.ok("Topic '" + name + "' creado correctamente.");
		
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear el topic: " + e.getMessage());
		}
	}
}
