package com.inventory.infrastructure.web.model.response;

import java.time.LocalDateTime;
import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Schema(description = "Global error")
public class ErrorResponse {
	
	 @Schema(description = "Current timestamp of the error", example = "2025-05-31T14:23:45")
	 private LocalDateTime timestamp;

	 @Schema(description = "HTTP status code of the error", example = "400")
	 private int status;

	 @Schema(description = "Short description of the HTTP error type", example = "Bad Request")
	 private String error;

	 @Schema(description = "Detailed error message", example = "The 'name' field must not be blank")
	 private String message;

	 @Schema(description = "List of validation error details", 
	         example = "[{\"field\": \"name\", \"message\": \"must not be blank\"}, {\"field\": \"price\", \"message\": \"must be greater than zero\"}]")
	 private List<ValidationError> errors;
}


