package com.inventory.infrastructure.persistence.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

public class RecipeIngredientEntity extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id; // El identificador único numérico para esta entrada específica de ingrediente en una receta.

    // --- Relación: Muchos a Uno (Many-to-One) con DishEntity ---
    // MUCHOS RecipeIngredientEntity pueden pertenecer a UN DishEntity (una receta).
    // Esta es una de las dos "patas" de la relación de Muchos a Muchos.
    @ManyToOne // Define la relación como Muchos a Uno.
    @JoinColumn(name = "dish_id", nullable = false) // Mapea la clave foránea en la tabla 'recipe_ingredients'. 'name = "dish_id"' es el nombre de la columna FK; 'nullable = false' significa que cada ingrediente DEBE estar asociado a un plato/receta.
    @NotNull(message = "Un ingrediente de receta debe estar asociado a un plato.") // Anotación de validación: asegura que el campo 'dish' no sea nulo.
    private DishEntity dish; // Referencia a la entidad de la receta o plato al que pertenece este ingrediente.

    // --- Relación: Muchos a Uno (Many-to-One) con ProductEntity ---
    // MUCHOS RecipeIngredientEntity pueden usar UN ProductEntity (un producto de inventario).
    // Esta es la otra "pata" de la relación de Muchos a Muchos.
    @ManyToOne // Define la relación como Muchos a Uno.
    @JoinColumn(name = "product_id", nullable = false) // Mapea la clave foránea en la tabla 'recipe_ingredients'. 'name = "product_id"' es el nombre de la columna FK; 'nullable = false' significa que cada ingrediente DEBE ser un producto existente.
    @NotNull(message = "Un ingrediente de receta debe estar asociado a un producto.") // Anotación de validación: asegura que el campo 'product' no sea nulo.
    private ProductEntity product; // Referencia a la entidad del producto/ingrediente específico que se utiliza.

    @NotNull(message = "La cantidad necesaria no puede ser nula.") 
    @DecimalMin(value = "0.00", inclusive = false, message = "La cantidad necesaria debe ser mayor que cero.") 
    @Column(name = "quantity_needed", nullable = false, precision = 10, scale = 3)
    private BigDecimal quantityNeeded; // La cantidad de este producto específica que se necesita para esta receta. Se usa BigDecimal para precisión con números decimales.

    @NotNull(message = "La unidad para la receta no puede ser nula.") 
    @Enumerated(EnumType.STRING) // ¡IMPORTANTE! Mapea el enum como un String en la base de datos (ej. "Kg", "ml").
                                 // Si usas EnumType.ORDINAL, guardaría el índice (0, 1, 2...), lo cual NO es recomendable.
    @Column(name = "unit_for_recipe", nullable = false, length = 20)
    private UnitOfMeasure unitForRecipe; // La unidad de medida en la que se expresa la 'quantityNeeded' para esta receta (ej., "Kg", "gramos", "Unidades"). Podría ser diferente de 'unitOfMeasure' en ProductEntity si se necesita una conversión.

    public RecipeIngredientEntity() {}

    public RecipeIngredientEntity(DishEntity dish, ProductEntity product, BigDecimal quantityNeeded, UnitOfMeasure unitForRecipe) {
        this.dish = dish;
        this.product = product;
        this.quantityNeeded = quantityNeeded;
        this.unitForRecipe = unitForRecipe;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public DishEntity getDish() { return dish; }
    public void setDish(DishEntity dish) { this.dish = dish; }
    public ProductEntity getProduct() { return product; }
    public void setProduct(ProductEntity product) { this.product = product; }
    public BigDecimal getQuantityNeeded() { return quantityNeeded; }
    public void setQuantityNeeded(BigDecimal quantityNeeded) { this.quantityNeeded = quantityNeeded; }
    public UnitOfMeasure getUnitForRecipe() { return unitForRecipe; }
    public void setUnitForRecipe(UnitOfMeasure unitForRecipe) { this.unitForRecipe = unitForRecipe; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecipeIngredientEntity that = (RecipeIngredientEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
