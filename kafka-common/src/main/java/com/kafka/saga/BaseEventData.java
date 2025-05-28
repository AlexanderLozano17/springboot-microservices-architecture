package com.kafka.saga;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * Clase base para todas los event. Esta clase contiene los campos comunes para
 * gestionar los eventos que seràn emitidos a los topic
 */
@Data
public abstract class BaseEventData {

    private String aggregateId;            	// ID único para toda la saga
    private String version;					// Versionar el payload del evento
    private String source;            		// Microservicio que emitió el evento
    private String eventType;         		// Tipo de evento (e.g. "OrderCreated", "PaymentApproved")
    private EventState sagaState;      		// Estado actual de la saga (enum)        
    private Object payload;           		// Datos del evento, genérico para flexibilidad
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private Instant timestamp;        		// Marca temporal del evento
    private boolean compensating;     		// Indica si es evento compensatorio (rollback)
    
    public BaseEventData() {
        this.timestamp = Instant.now();
        this.compensating = false;
    }

}

