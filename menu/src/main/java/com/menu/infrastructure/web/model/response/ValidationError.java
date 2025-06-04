package com.menu.infrastructure.web.model.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "Object that represents a specific field validation error")
public class ValidationError {

	@Schema(description = "Name of the field that has a validation error", example = "name")
	private String field;

	@Schema(description = "Validation error message associated with the field", example = "must not be blank")
	private String message;
}