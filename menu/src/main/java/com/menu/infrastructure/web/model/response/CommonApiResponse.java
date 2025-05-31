package com.menu.infrastructure.web.model.response;

import java.time.LocalDateTime;

public class CommonApiResponse<T> {

	private int status;
	private String message;
	private boolean success;
	private T data;
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
