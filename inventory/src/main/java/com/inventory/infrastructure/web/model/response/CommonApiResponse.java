package com.inventory.infrastructure.web.model.response;

import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Standard API response wrapper")
public class CommonApiResponse<T> {

    @Schema(description = "HTTP status code of the response", example = "200")
    private int status;

    @Schema(description = "Human-readable message describing the result", example = "Operation completed successfully")
    private String message;

    @Schema(description = "Indicates whether the operation was successful", example = "true")
    private boolean success;

    @Schema(description = "The actual response payload", example = "{ \"id\": 1, \"name\": \"Category A\" }")
    private T data;

    @Schema(description = "Date and time when the response was generated", example = "2025-05-31T16:00:00")
    private LocalDateTime timestamp;

	// Constructor privado para ser usado por los métodos estáticos o un builder
	private CommonApiResponse(int status, String message, boolean success, T data) {
		this.status = status;
		this.message = message;
		this.success = success;
		this.data = data;
		this.timestamp = LocalDateTime.now();
	}

	// Métodos estáticos de fábrica para crear respuestas de éxito
	public static <T> CommonApiResponse<T> success(int status, String message, T data) {
		return new CommonApiResponse<>(status, message, true, data);
	}

	public static <T> CommonApiResponse<T> success(T data) {
		// Un atajo común para respuestas exitosas sin mensaje específico y status 200
		// OK
		return new CommonApiResponse<>(200, "Operación exitosa", true, data);
	}

	// Métodos estáticos de fábrica para crear respuestas de error
	public static <T> CommonApiResponse<T> error(int status, String message) {
		// Para errores sin data asociada
		if (message.isBlank()) message = "Error al procesar la solicitud"; 
		return new CommonApiResponse<>(status, message, false, null);
	}

	public static <T> CommonApiResponse<T> error(int status, String message, T errorDetails) {
		// Para errores con detalles adicionales (ej. lista de errores de validación)
		return new CommonApiResponse<>(status, message, false, errorDetails);
	}

	public int getStatus() {
		return status;
	}

	public String getMessage() {
		return message;
	}

	public boolean isSuccess() {
		return success;
	}

	public T getData() {
		return data;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}
}
