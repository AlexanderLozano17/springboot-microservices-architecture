package com.inventory.domain.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

import com.inventory.utils.UnitOfMeasure;

public class Product extends BaseModel implements Serializable {

    private static final long serialVersionUID = 1L;
	private Long id;
    private String name;
    private UnitOfMeasure unitOfMeasure;
    private BigDecimal costPerUnit;
    private Double currentStock;
    private Double minStockLevel; 
    private String supplier;
    private Boolean isPerishable;
    private String storageLocation;
    
    public Product() {}

	public Product(String name, UnitOfMeasure unitOfMeasure, BigDecimal costPerUnit, Double currentStock, String supplier,
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
        Product that = (Product) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
