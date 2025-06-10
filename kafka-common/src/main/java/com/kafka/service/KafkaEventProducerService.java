package com.kafka.service;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.kafka.saga.BaseEventData;
import com.kafka.utils.FunctionUtils;

/**
 * Servicio centralizado que contiene las implementaciones para el envío de mensajes a los distintos topics de Kafka.
 * <p>
 * Este servicio actúa como una fachada para los productores, permitiendo desacoplar la lógica de negocio
 * del mecanismo de mensajería. Se encarga de construir y publicar eventos a los topics correspondientes,
 * garantizando consistencia en el formato, manejo de excepciones y trazabilidad.
 * </p>
 */
@Service
public class KafkaEventProducerService {
	
	private final Logger logger = LoggerFactory.getLogger(KafkaEventProducerService.class);

	private final KafkaTemplate<String, BaseEventData> kafkaTemplate;
	
	public KafkaEventProducerService(KafkaTemplate<String, BaseEventData> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}
		
	/**
	 * Envía un mensaje de forma síncrona (bloqueante) a Kafka sin clave.
	 * @param topic nombre del topic Kafka
	 * @param sagaEvent el mensaje a enviar
	 */
	public void sendMessage(String topic, BaseEventData event) {
		logger.info("→ START | " + KafkaEventProducerService.class.getName() +"::sendMessage()");
		
		try {	
			FunctionUtils.printJsonPretty(event);
            kafkaTemplate.send(topic, event).get(); // Bloqueante
            logger.info("✓ SUCCESS | " + KafkaEventProducerService.class.getName() + "::sendMessage() - Evento enviado (sync) a topic [{}] con key [{}]", topic);
            logger.info("← END | " + KafkaEventProducerService.class.getName() +"::sendMessage()");
            
        } catch (Exception ex) {        	
        	logger.error("✖ ERROR | " + KafkaEventProducerService.class.getName() +"::sendMessage() - Error en envío síncrono a topic [{}] con key [{}]: {}", topic, ex.getMessage());
        	logger.info("← END | " + KafkaEventProducerService.class.getName() +"::sendMessage()");
            throw new RuntimeException("Error enviando mensaje a Kafka", ex);
        }
	}
	
	/**
	 * Envía un mensaje de forma síncrona (bloqueante) a Kafka sin clave.
	 * @param topic nombre del topic Kafka
	 * @param sagaEvent el mensaje a enviar
	 */
	public void sendMessageWithKey(String topic, BaseEventData  event) {
		logger.info("→ START | " + KafkaEventProducerService.class.getName() +"::sendMessageWithKey()");
		
		String key = UUID.randomUUID().toString();

		try {		
			FunctionUtils.printJsonPretty(event);
            kafkaTemplate.send(topic, key, event).get(); // Bloqueante
            logger.info("✓ SUCCESS | " + KafkaEventProducerService.class.getName() + "::sendMessageWithKey() - Evento enviado (sync) a topic [{}] con key [{}]", topic, key);
            logger.info("← END | " + KafkaEventProducerService.class.getName() +"::sendMessageWithKey()");
            
        } catch (Exception ex) {        	
        	logger.error("✖ ERROR | " + KafkaEventProducerService.class.getName() +"::sendMessageWithKey() - Error en envío síncrono a topic [{}] con key [{}]: {}", topic, key, ex.getMessage());
        	logger.info("← END | " + KafkaEventProducerService.class.getName() +"::sendMessageWithKey()");
            throw new RuntimeException("Error enviando mensaje a Kafka", ex);
        }
	}
}
