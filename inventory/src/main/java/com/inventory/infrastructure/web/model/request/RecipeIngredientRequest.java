package com.inventory.infrastructure.web.model.request;

import java.io.Serializable;
import java.math.BigDecimal;

import com.inventory.infrastructure.persistence.entity.DishEntity;
import com.inventory.infrastructure.persistence.entity.ProductEntity;
import com.inventory.utils.UnitOfMeasure;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class RecipeIngredientRequest extends BaseModelRequest implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@NotNull(message = "Un ingrediente de receta debe estar asociado a un plato.")
    private DishEntity dish; // Referencia a la entidad de la receta o plato al que pertenece este ingrediente.

    @NotNull(message = "Un ingrediente de receta debe estar asociado a un producto.")
    private ProductEntity product; // Referencia a la entidad del producto/ingrediente específico que se utiliza.

    @NotNull(message = "La cantidad necesaria no puede ser nula.") 
    @DecimalMin(value = "0.00", inclusive = false, message = "La cantidad necesaria debe ser mayor que cero.") 
    @Column(name = "quantity_needed", nullable = false, precision = 10, scale = 3)
    private BigDecimal quantityNeeded; // La cantidad de este producto específica que se necesita para esta receta. Se usa BigDecimal para precisión con números decimales.

    @NotNull(message = "La unidad para la receta no puede ser nula.") 
    @Enumerated(EnumType.STRING)
    @Column(name = "unit_for_recipe", nullable = false, length = 20)
    private UnitOfMeasure unitForRecipe; // La unidad de medida en la que se expresa la 'quantityNeeded' para esta receta (ej., "Kg", "gramos", "Unidades"). Podría ser diferente de 'unitOfMeasure' en ProductEntity si se necesita una conversión.


}
