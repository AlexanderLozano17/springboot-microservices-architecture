package com.kafka.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.kafka.saga.BaseEventData;
import com.kafka.utils.FunctionUtils;

@Component
public class KafkaSagaEventListener {

	private final Logger logger = LoggerFactory.getLogger(KafkaSagaEventListener.class);
		
    @KafkaListener(
    		topics = "topic-orders", 
    		groupId = "group-order", 
    		containerFactory = "kafkaListenerContainerFactory")
    public void consume(BaseEventData event) {
        logger.info("✓ Evento Saga recibido - sagaId: {}", event.getAggregateId());

        try {
            logger.info("→ Procesando evento [{}] desde [{}] con estado [{}]", 
                event.getEventType(), 
                event.getSource(), 
                event.getSagaState()
            );

            FunctionUtils.printJsonPretty(event);

        } catch (Exception e) {
            logger.error("✖ Error al procesar evento Saga con sagaId [{}]: {}", event.getAggregateId(), e.getMessage(), e);
        }
    }
}
