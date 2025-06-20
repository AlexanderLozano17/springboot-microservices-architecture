package com.inventory.infrastructure.persistence.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import org.springframework.stereotype.Indexed;

import com.inventory.utils.UnitOfMeasure;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Table(name = "product", indexes = {@Index(name = "IDX_PRODUCT_NAME", columnList = "name, storageLocation")})
@Entity
public class ProductEntity extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;
	
	@NotBlank(message = "El nombre del producto no puede estar vacío.") 
    @Size(max = 100, message = "El nombre del producto no puede exceder los 100 caracteres.") 
    @Column(nullable = false, unique = true, length = 100) 
    private String name; // El nombre del producto/ingrediente (ej., "Papa Pastusa", "Carne de Res (Lomo)").

	@NotNull(message = "La unidad para la receta no puede ser nula.")
	@Enumerated(EnumType.STRING) // ¡IMPORTANTE! Mapea el enum como un String en la base de datos (ej. "Kg","ml").
									// Si usas EnumType.ORDINAL, guardaría el índice (0, 1, 2...), lo cual NO es recomendable.
	@Column(name = "unit_for_recipe", nullable = false, length = 20)
	private UnitOfMeasure unitOfMeasure; // La unidad de medida en la que se gestiona el producto (ej., "Kilogramos", "Unidades", "Gramos", "Litros").

    @NotNull(message = "El costo por unidad no puede ser nulo.")
    @DecimalMin(value = "0.00", inclusive = true, message = "El costo por unidad debe ser mayor o igual que cero.") 
    @Column(name = "cost_per_unit", nullable = false, precision = 10, scale = 2)
    private BigDecimal costPerUnit; // El costo por unidad de este producto para el restaurante.

    @NotNull(message = "El stock actual no puede ser nulo.") 
    @Min(value = 0, message = "El stock actual no puede ser negativo.")
    @Column(name = "current_stock", nullable = false)
    private Double currentStock; // La cantidad actual de este producto en el inventario. Se usa Double para permitir cantidades decimales (ej., 0.5 Kg).

    @NotNull(message = "El nivel mínimo de stock no puede ser nulo.") 
    @Min(value = 0, message = "El nivel mínimo de stock no puede ser negativo.")
    @Column(name = "min_stock_level", nullable = false) 
    private Double minStockLevel; // El nivel mínimo de stock antes de que necesites pedir más de este producto. Se usa Double por consistencia con currentStock.

    @NotBlank(message = "El nombre del proveedor no puede estar vacío.") 
    @Size(max = 100, message = "El nombre del proveedor no puede exceder los 100 caracteres.") 
    @Column(nullable = false)
    private String supplier;
    
    @NotNull(message = "El estado de perecedero no puede ser nulo.") 
    @Column(name = "is_perishable", nullable = false) 
    private Boolean isPerishable; // Indica si el producto es perecedero (TRUE) o no (FALSE).

    @Size(max = 100, message = "La ubicación de almacenamiento no puede exceder los 100 caracteres.") 
    @Column(name = "storage_location", length = 100)
    private String storageLocation; // La ubicación física donde se guarda el producto (ej., "Refrigerador A", "Alacena Seca", "Bodega Fría").

    public ProductEntity() {}

	public ProductEntity(String name, UnitOfMeasure unitOfMeasure, BigDecimal costPerUnit, Double currentStock, String supplier,
			Double minStockLevel, boolean isPerishable, String storageLocation) {
		this.name = name;
	    this.unitOfMeasure = unitOfMeasure;
        this.costPerUnit = costPerUnit;
        this.currentStock = currentStock;
        this.minStockLevel = minStockLevel;
        this.supplier = supplier;
        this.isPerishable = isPerishable;
        this.storageLocation = storageLocation;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }  
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public UnitOfMeasure getUnitOfMeasure() { return unitOfMeasure; }
    public void setUnitOfMeasure(UnitOfMeasure unitOfMeasure) { this.unitOfMeasure = unitOfMeasure; }
    public BigDecimal getCostPerUnit() { return costPerUnit; }
    public void setCostPerUnit(BigDecimal costPerUnit) { this.costPerUnit = costPerUnit; }
    public Double getCurrentStock() { return currentStock; }
    public void setCurrentStock(Double currentStock) { this.currentStock = currentStock; }
    public Double getMinStockLevel() { return minStockLevel; }
    public void setMinStockLevel(Double minStockLevel) { this.minStockLevel = minStockLevel; }    
    public String getSupplier() { return supplier; }
	public void setSupplier(String supplier) { this.supplier = supplier; }
	public Boolean getIsPerishable() { return isPerishable; }
    public void setIsPerishable(Boolean isPerishable) { this.isPerishable = isPerishable; }
    public String getStorageLocation() { return storageLocation; }
    public void setStorageLocation(String storageLocation) { this.storageLocation = storageLocation; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductEntity that = (ProductEntity) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
