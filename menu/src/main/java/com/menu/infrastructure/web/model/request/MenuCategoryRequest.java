package com.menu.infrastructure.web.model.request;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class MenuCategoryRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	@NotBlank(message = "El nombre de la categoría no puede estar vacío.")
    @Size(min = 3, max = 50, message = "El nombre de la categoría debe tener entre 3 y 50 caracteres.")
    private String name;

    @Size(max = 255, message = "La descripción no puede exceder los 255 caracteres.")
    private String description;
    
}
