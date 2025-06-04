package com.menu.infrastructure.web.model.request;

import java.io.Serializable;
import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

@Data 
@Builder
public class MenuItemRequest implements Serializable{

    private static final long serialVersionUID = 1L;
    private Long id;

    @NotBlank(message = "El nombre del ítem no puede estar vacío.")
    @Size(max = 50, message = "El nombre no puede exceder los 50 caracteres.")
    @Column(nullable = false, unique = true, length = 50)
    private String name;

    @Size(max = 500, message = "La descripción no puede exceder los 500 caracteres.")
    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false, precision = 10, scale = 2) 
    private BigDecimal price;

    @Column(nullable = false) 
    private Boolean available;
  
    @NotNull(message = "El ID de categoría no puede ser nulo.")
    private Long menuCategoryId;
}
