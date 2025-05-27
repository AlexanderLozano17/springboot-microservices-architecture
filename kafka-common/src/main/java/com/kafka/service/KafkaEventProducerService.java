package com.kafka.service;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.kafka.saga.BaseEventData;
import com.kafka.utils.FunctionUtils;

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
	 * @param sagaEvent el mensaje a enviar
	 */
	public void sendMessage(String topic, BaseEventData event) {
		logger.info("→ START | " + getClass().getName() +"::sendMessage()");
		
		try {	
			FunctionUtils.printJsonPretty(event);
            kafkaTemplate.send(topic, event).get(); // Bloqueante
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
	 * @param sagaEvent el mensaje a enviar
	 */
	public void sendMessageWithKey(String topic, BaseEventData  event) {
		logger.info("→ START | " + getClass().getName() +"::sendMessageWithKey()");
		
		String key = UUID.randomUUID().toString();

		try {		
			FunctionUtils.printJsonPretty(event);
            kafkaTemplate.send(topic, key, event).get(); // Bloqueante
            logger.info("✓ SUCCESS | " + getClass().getName() + "::sendMessageWithKey() - Evento enviado (sync) a topic [{}] con key [{}]", topic, key);
            logger.info("← END | " + getClass().getName() +"::sendMessageWithKey()");
            
        } catch (Exception ex) {        	
        	logger.error("✖ ERROR | " + getClass().getName() +"::sendMessageWithKey() - Error en envío síncrono a topic [{}] con key [{}]: {}", topic, key, ex.getMessage());
        	logger.info("← END | " + getClass().getName() +"::sendMessageWithKey()");
            throw new RuntimeException("Error enviando mensaje a Kafka", ex);
        }
	}
}
