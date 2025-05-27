package com.kafka.saga;

/**
 * Estados de los eventos
 */
public enum EventState {
        STARTED,
        IN_PROGRESS,
        PENDING,
        COMPLETED,
        FAILED,
        COMPENSATED
}
