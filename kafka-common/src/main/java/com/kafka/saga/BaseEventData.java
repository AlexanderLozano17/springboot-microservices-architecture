package com.kafka.saga;

import java.time.Instant;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonFormat;

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
    private String compensationDetails; 	// para guardar info de errores o resultados de la compensación.
    
    public BaseEventData() {
        this.aggregateId = UUID.randomUUID().toString();
        this.timestamp = Instant.now();
        this.compensating = false;
    }

    /**
	 * @return the sagaId
	 */
	public String getAggregateId() {
		return aggregateId;
	}

	/**
	 * @param sagaId the sagaId to set
	 */
	public void setAggregateId(String aggregateId) {
		this.aggregateId = aggregateId;
	}

	/**
	 * @return the version
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(String version) {
		this.version = version;
	}

	/**
	 * @return the eventType
	 */
	public String getEventType() {
		return eventType;
	}

	/**
	 * @param eventType the eventType to set
	 */
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	/**
	 * @return the source
	 */
	public String getSource() {
		return source;
	}

	/**
	 * @param source the source to set
	 */
	public void setSource(String source) {
		this.source = source;
	}

	/**
	 * @return the sagaState
	 */
	public EventState getSagaState() {
		return sagaState;
	}

	/**
	 * @param sagaState the sagaState to set
	 */
	public void setSagaState(EventState sagaState) {
		this.sagaState = sagaState;
	}

	/**
	 * @return the payload
	 */
	public Object getPayload() {
		return payload;
	}

	/**
	 * @param payload the payload to set
	 */
	public void setPayload(Object payload) {
		this.payload = payload;
	}

	/**
	 * @return the timestamp
	 */
	public Instant getTimestamp() {
		return timestamp;
	}

	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(Instant timestamp) {
		this.timestamp = timestamp;
	}

	/**
	 * @return the compensating
	 */
	public boolean isCompensating() {
		return compensating;
	}

	/**
	 * @param compensating the compensating to set
	 */
	public void setCompensating(boolean compensating) {
		this.compensating = compensating;
	}

	/**
	 * @return the compensationDetails
	 */
	public String getCompensationDetails() {
		return compensationDetails;
	}

	/**
	 * @param compensationDetails the compensationDetails to set
	 */
	public void setCompensationDetails(String compensationDetails) {
		this.compensationDetails = compensationDetails;
	}
}

