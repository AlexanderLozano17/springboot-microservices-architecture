package com.menu.application.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.menu.application.dto.MenuCategoryDto.Builder;
import com.menu.domain.model.MenuCategory;

public class MenuItemDto {

	private Long id;
	private String name;
	private String description;
	private BigDecimal price;
	private Boolean available;
	private Long categoryId;
	private MenuCategoryDto categoryDetails;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
        
    public MenuItemDto(Builder builder) {
		this.id = builder.id;
		this.name = builder.name;
		this.description = builder.description;
		this.price = builder.price;
		this.available = builder.available;
		this.categoryId = builder.categoryId;
		this.categoryDetails = builder.categoryDetails;
		this.createdAt = builder.createdAt;
		this.updatedAt = builder.updatedAt;
	}

	public static Builder builder() {
        return new Builder();
    }
	
	// Método de instancia para obtener un Builder pre-inicializado con los
	// valores actuales
	public Builder toBuilder() {
        return new Builder()
            .id(this.id)
            .name(this.name)
            .description(this.description)
            .price(this.price)
            .available(this.available)
            .categoryId(this.categoryId)
            .categoryDetails(this.categoryDetails)
            .createdAt(this.createdAt)
            .updatedAt(this.updatedAt);
    }
    
    public static class Builder {
    	
    	private Long id;
    	private String name;
    	private String description;
    	private BigDecimal price;
    	private Boolean available;
    	private Long categoryId;
    	private MenuCategoryDto categoryDetails;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;
        
		public Builder id(Long id) {
			this.id = id;
			return this;
		}
		
		public Builder name(String name) {
			this.name = name;
			return this;
		}
		
		public Builder description(String description) {
			this.description = description;
			return this;
		}
		
		public Builder price(BigDecimal price) {
			this.price = price;
			return this;
		}
		
		public Builder available(Boolean available) {
			this.available = available;
			return this;
		}
		
		public Builder categoryId(Long categoryId) {
			this.categoryId = categoryId;
			return this;
		}
		
		public Builder categoryDetails(MenuCategoryDto categoryDetails) {
			this.categoryDetails = categoryDetails;
			return this;
		}
		
		public Builder createdAt(LocalDateTime createdAt) {
			this.createdAt = createdAt;
			return this;
		}
		
		public Builder updatedAt(LocalDateTime updatedAt) {
			this.updatedAt = updatedAt;
			return this;
		}
        
		public MenuItemDto build() {
			return new MenuItemDto(this);
		}
    }

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public Boolean getAvailable() {
		return available;
	}

	public Long getcategoryId() {
		return categoryId;
	}
	
	public MenuCategoryDto getCategoryDetails() {
		return categoryDetails;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}    
}
