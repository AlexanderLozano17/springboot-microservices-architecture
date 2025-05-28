package com.kafka.controller;

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

import com.kafka.saga.EventDataExample;
import com.kafka.service.KafkaEventProducerService;
import com.kafka.service.KafkaTopicService;
import com.kafka.utils.FunctionUtils;

@RestController
@RequestMapping("/api/kafka")
public class KafkaTopicController {
	
	private final Logger logger = LoggerFactory.getLogger(KafkaTopicController.class);

	private final KafkaAdmin kafkaAdmin;
	
	private final KafkaTopicService kafkaTopicService;
	
	private final KafkaEventProducerService kafkaEventProducerService;
	
	public KafkaTopicController(KafkaAdmin kafkaAdmin, 
							    KafkaTopicService kafkaTopicService,
							    KafkaEventProducerService kafkaEventProducerService) {
		this.kafkaAdmin = kafkaAdmin;
		this.kafkaTopicService = kafkaTopicService;
		this.kafkaEventProducerService = kafkaEventProducerService;
	}
	
	@PostMapping("/create-topic")
	public ResponseEntity<String> createTopic(@RequestParam String name,
											  @RequestParam(defaultValue = "3") int partitions,
											  @RequestParam(defaultValue = "1") int replicas) {
		logger.info("→ START | " + getClass().getName() +"::createTopic()");
		NewTopic topic = TopicBuilder.name(name)
				.partitions(partitions)
				.replicas(replicas)
				.build();
		
		try {
			kafkaAdmin.createOrModifyTopics(topic);
			logger.info("✓ SUCCESS  | " + getClass().getName() +"::createTopic() - Topic '" + name + "' creado correctamente.");
			return ResponseEntity.ok("Topic '" + name + "' creado correctamente.");
		
		} catch (Exception e) {
			logger.info("✖ ERROR | " + getClass().getName() +"::createTopic() - {}",  e.getMessage() );
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al crear el topic: " + e.getMessage());
		}
	}
	
	@GetMapping("/get-all-topic")
	public ResponseEntity<Set<String>> getAllTopics() {
		logger.info("→ START | " + getClass().getName() +"::getAllTopics()");
		Set<String> listTopics = this.kafkaTopicService.getAllTopics();		
		logger.info("✓ SUCCESS  | " + getClass().getName() +"::getAllTopics()");
		return ResponseEntity.ok(listTopics);		
	}
	
	
	@PostMapping("/producer-order-example")
	public ResponseEntity<String> producerOrderEventExample(@RequestParam String topicName, @RequestBody EventDataExample event) {
		logger.info("→ START | " + getClass().getName() +"::producerOrderEvent()");

		try {
			FunctionUtils.printJsonPretty(event);
			kafkaEventProducerService.sendMessageWithKey(topicName, event);
			
			logger.info("✓ SUCCESS  | " + getClass().getName() +"::producerOrderEvent()");
			return ResponseEntity.ok("producerOrderEvent creado correctamente.");
		
		} catch (Exception e) {
			logger.info("✖ ERROR | " + getClass().getName() +"::producerOrderEvent() - {}",  e.getMessage() );
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al prudcir el evento producerOrderEvent: " + e.getMessage());
		}
	}
	
}
