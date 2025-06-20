package com.inventory.domain.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import com.inventory.utils.UnitOfMeasure;

public class RecipeIngredient extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private Dish dish;
	private Product product;
	private BigDecimal quantityNeeded;
	private UnitOfMeasure unitForRecipe;
	
    public RecipeIngredient() {}

    public RecipeIngredient(Dish dish, Product product, BigDecimal quantityNeeded, UnitOfMeasure unitForRecipe) {
        this.dish = dish;
        this.product = product;
        this.quantityNeeded = quantityNeeded;
        this.unitForRecipe = unitForRecipe;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Dish getDish() { return dish; }
    public void setDish(Dish dish) { this.dish = dish; }
    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }
    public BigDecimal getQuantityNeeded() { return quantityNeeded; }
    public void setQuantityNeeded(BigDecimal quantityNeeded) { this.quantityNeeded = quantityNeeded; }
    public UnitOfMeasure getUnitForRecipe() { return unitForRecipe; }
    public void setUnitForRecipe(UnitOfMeasure unitForRecipe) { this.unitForRecipe = unitForRecipe; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RecipeIngredient that = (RecipeIngredient) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
