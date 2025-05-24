package com.kafka.events;

import java.time.Instant;
import java.util.UUID;

public class SagaEvent {

    private String sagaId;            // ID único para toda la saga
    private String eventType;         // Tipo de evento (e.g. "OrderCreated", "PaymentApproved")
    private SagaState sagaState;      // Estado actual de la saga (enum)
    private Object payload;           // Datos del evento, genérico para flexibilidad
    private Instant timestamp;        // Marca temporal del evento
    private boolean compensating;     // Indica si es evento compensatorio (rollback)

    public SagaEvent() {
        this.sagaId = UUID.randomUUID().toString();
        this.timestamp = Instant.now();
        this.compensating = false;
    }

    // Getters y setters

    public String getSagaId() {
        return sagaId;
    }

    public void setSagaId(String sagaId) {
        this.sagaId = sagaId;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public SagaState getSagaState() {
        return sagaState;
    }

    public void setSagaState(SagaState sagaState) {
        this.sagaState = sagaState;
    }

    public Object getPayload() {
        return payload;
    }

    public void setPayload(Object payload) {
        this.payload = payload;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }

    public boolean isCompensating() {
        return compensating;
    }

    public void setCompensating(boolean compensating) {
        this.compensating = compensating;
    }

    // Enum para estados comunes en saga
    public enum SagaState {
        STARTED,
        IN_PROGRESS,
        COMPLETED,
        FAILED,
        COMPENSATED
    }
}

