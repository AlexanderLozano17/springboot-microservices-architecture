package com.kafka.service;

import java.util.HashSet;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.ListTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.stereotype.Service;

import com.kafka.entity.ListTopicEntity;
import com.kafka.properties.KafkaProperties;
/**
 * Servicio encargado de la gestión de topics en Kafka, limitado a operaciones de creación y consulta.
 * <p>
 * Proporciona una interfaz centralizada para interactuar con el clúster de Kafka en lo referente
 * a la administración de topics, como la creación de nuevos topics bajo demanda o la obtención de
 * metadatos relacionados (particiones, configuraciones, estado, etc.).
 * </p>
 *
 * <p>
 * No realiza envío ni consumo de mensajes, solo gestiona la existencia y propiedades de los topics.
 * </p>
 */
@Service
public class KafkaTopicService {

	private final Logger logger = LoggerFactory.getLogger(KafkaTopicService.class);
	
	private final KafkaProperties properties;
	
	private final KafkaAdmin kafkaAdmin;
	
	public KafkaTopicService(KafkaProperties properties, KafkaAdmin kafkaAdmin) {
		this.properties = properties;
		this.kafkaAdmin = kafkaAdmin;
	}
	
	/**
	 * 
	 * @param topics
	 * @return
	 */
	public String createTopics(ListTopicEntity topics) {
		logger.info("→ START | " + KafkaTopicService.class.getName() +"::createTopics()");
		
		Set<String> topicsCreated = new HashSet<>();
		
		topics.getListTopic().stream().forEach(topic -> {
			NewTopic newTopic = TopicBuilder.name(topic.getName())
					.partitions(topic.getPartitions())
					.replicas(topic.getReplicas())
					.build();
			
			kafkaAdmin.createOrModifyTopics(newTopic);
			topicsCreated.add(topic.getName());
		});
		
		logger.info("← END | " + KafkaTopicService.class.getName() +"::createTopics()");
		return String.join(",", topicsCreated);
	}
	
	/**
	 * 
	 * @return
	 */
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
