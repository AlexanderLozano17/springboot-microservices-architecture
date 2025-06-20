package com.inventory.infrastructure.web.model.request;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class DishRequest extends BaseModelRequest implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "El nombre del plato no puede estar vacío.") 
    @Size(max = 50, message = "El nombre del plato no puede exceder los 50 caracteres.")
    @Column(nullable = false, unique = true, length = 50)
    private String name;
    
	@Size(max = 500, message = "La descripción del plato no puede exceder los 500 caracteres.")
	@Column(length = 500)
	private String description;
}
