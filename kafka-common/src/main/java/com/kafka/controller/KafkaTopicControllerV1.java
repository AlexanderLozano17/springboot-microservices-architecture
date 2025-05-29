package com.kafka.controller;

import java.util.HashSet;
import java.util.Set;

import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.kafka.entity.ListTopicEntity;
import com.kafka.saga.EventDataExample;
import com.kafka.service.KafkaEventProducerService;
import com.kafka.service.KafkaTopicService;
import com.kafka.utils.ConstantTopics;

@RestController
@RequestMapping("/api/v1/kafka")
public class KafkaTopicControllerV1 {
	
	private final Logger logger = LoggerFactory.getLogger(KafkaTopicControllerV1.class);

	private final KafkaAdmin kafkaAdmin;
	
	private final KafkaTopicService kafkaTopicService;
	
	private final KafkaEventProducerService kafkaEventProducerService;
	
	public KafkaTopicControllerV1(KafkaAdmin kafkaAdmin, 
							    KafkaTopicService kafkaTopicService,
							    KafkaEventProducerService kafkaEventProducerService) {
		this.kafkaAdmin = kafkaAdmin;
		this.kafkaTopicService = kafkaTopicService;
		this.kafkaEventProducerService = kafkaEventProducerService;
	}
	
	@PostMapping("/create-topic")
	public ResponseEntity<String> createTopic(@RequestBody ListTopicEntity topics) {
		logger.info("→ START | " + KafkaTopicControllerV1.class.getName() +"::createTopic()");
				
		try {			
			Set<String> topicsCreated = new HashSet<>();
			
			topics.getListTopic().stream().forEach(topic -> {
				NewTopic newTopic = TopicBuilder.name(topic.getName())
						.partitions(topic.getPartitions())
						.replicas(topic.getReplicas())
						.build();
				
				kafkaAdmin.createOrModifyTopics(newTopic);
				topicsCreated.add(topic.getName());
			});
			
			String createdTopicsStr = String.join(", ", topicsCreated);
			
			logger.info("✓ SUCCESS  | " + KafkaTopicControllerV1.class.getName() +"::createTopic() - Topics → '" + createdTopicsStr + "' creado correctamente.");
			return ResponseEntity.ok("Topic → '" + createdTopicsStr + "' creado correctamente.");
		
		} catch (Exception e) {
			logger.info("✖ ERROR | " + getClass().getName() +"::createTopic() - {}",  e.getMessage() );
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear el topic: " + e.getMessage());
		}
	}
	
	@GetMapping("/get-all-topic")
	public ResponseEntity<Set<String>> getAllTopics() {
		logger.info("→ START | " + KafkaTopicControllerV1.class.getName() +"::getAllTopics()");
		Set<String> listTopics = this.kafkaTopicService.getAllTopics();		
		logger.info("✓ SUCCESS  | " + getClass().getName() +"::getAllTopics()");
		return ResponseEntity.ok(listTopics);		
	}
	
	
	@PostMapping("/producer-order-example")
	public ResponseEntity<String> producerOrderEventExample(@RequestParam String topicName, @RequestBody EventDataExample event) {
		logger.info("→ START | " + KafkaTopicControllerV1.class.getName() +"::producerOrderEventExample()");

		try {
			kafkaEventProducerService.sendMessageWithKey(topicName, event);
			
			logger.info("✓ SUCCESS  | " + getClass().getName() +"::producerOrderEvent()");
			return ResponseEntity.ok("producerOrderEventExample creado correctamente.");
		
		} catch (Exception e) {
			logger.info("✖ ERROR | " + KafkaTopicControllerV1.class.getName() +"::producerOrderEventExample() - {}",  e.getMessage() );
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al prudcir el evento producerOrderEventExample: " + e.getMessage());
		}
	}	
}
