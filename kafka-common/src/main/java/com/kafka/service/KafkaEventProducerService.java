package com.kafka.service;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaEventProducerService {
	
	private final Logger logger = LoggerFactory.getLogger(KafkaEventProducerService.class);

	private final KafkaTemplate<String, Object> kafkaTemplate;
	
	public KafkaEventProducerService(KafkaTemplate<String, Object> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}
		
	/**
	 * Envía un mensaje de forma síncrona (bloqueante) a Kafka sin clave.
	 * @param topic nombre del topic Kafka
	 * @param payload el mensaje a enviar
	 */
	public <T> void sendMessage(String topic, T payload) {
		logger.info("→ START | " + getClass().getName() +"::sendMessage()");
		
		try {			
            kafkaTemplate.send(topic, payload).get(); // Bloqueante
            logger.info("✓ SUCCESS | " + getClass().getName() + "::sendMessage() - Evento enviado (sync) a topic [{}] con key [{}]", topic);
            logger.info("← END | " + getClass().getName() +"::sendMessage()");
            
        } catch (Exception ex) {        	
        	logger.error("✖ ERROR | " + getClass().getName() +"::sendMessage() - Error en envío síncrono a topic [{}] con key [{}]: {}", topic, ex.getMessage());
        	logger.info("← END | " + getClass().getName() +"::sendMessage()");
            throw new RuntimeException("Error enviando mensaje a Kafka", ex);
        }
	}
	
	/**
	 * Envía un mensaje de forma síncrona (bloqueante) a Kafka sin clave.
	 * @param topic nombre del topic Kafka
	 * @param payload el mensaje a enviar
	 */
	public <T> void sendMessageWithKey(String topic, T payload) {
		logger.info("→ START | " + getClass().getName() +"::sendMessageWithKey()");
		
		String key = UUID.randomUUID().toString();

		try {			
            kafkaTemplate.send(topic, key, payload).get(); // Bloqueante
            logger.info("✓ SUCCESS | " + getClass().getName() + "::sendMessageWithKey() - Evento enviado (sync) a topic [{}] con key [{}]", topic, key);
            logger.info("← END | " + getClass().getName() +"::sendMessageWithKey()");
            
        } catch (Exception ex) {        	
        	logger.error("✖ ERROR | " + getClass().getName() +"::sendMessageWithKey() - Error en envío síncrono a topic [{}] con key [{}]: {}", topic, key, ex.getMessage());
        	logger.info("← END | " + getClass().getName() +"::sendMessageWithKey()");
            throw new RuntimeException("Error enviando mensaje a Kafka", ex);
        }
	}
}
