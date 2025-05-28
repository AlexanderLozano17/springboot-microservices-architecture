package com.kafka.entity;

import java.io.Serializable;
import java.time.Instant;

import com.kafka.saga.EventState;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;


/**
 * Clase base para todas las entidades. Esta clase contiene los campos comunes
 * para gestionar los eventos que seràn almacenados en una tabla tipo
 * event_store
 */
@MappedSuperclass
@Data
public abstract class BaseEntityData implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "aggregate_id", nullable = false, length = 100)
    private String aggregateId;
    
    @Column(name = "version", nullable = false, length = 10)
    private String version;

    @Column(name = "source", nullable = false, length = 50)
    private String source;

    @Column(name = "event_type", nullable = false, length = 100)
    private String eventType;

    @Column(name = "saga_state", length = 50)
    private EventState sagaState;

    @Column(name = "payload", nullable = false, columnDefinition = "TEXT")
    private Object payload;

    @Column(name = "timestamp", nullable = false)
    private Instant timestamp;

    @Column(name = "compensating", nullable = false)
    private boolean compensating = false;

    @Column(name = "compensation_details")
    private String compensationDetails;

    public BaseEntityData() {
        this.timestamp = Instant.now();
    }
}
