package com.menu.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.menu.application.dto.MenuCategoryDto;
import com.menu.application.dto.MenuItemDto.Builder;

public class MenuItem {

	private Long id;
	private String name;
	private String description;
	private BigDecimal price;
	private Boolean available;
	private Long menuCategoryId;
	private MenuCategory menuCategoryDetails;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
        
    public MenuItem(Builder builder) {
		this.id = builder.id;
		this.name = builder.name;
		this.description = builder.description;
		this.price = builder.price;
		this.available = builder.available;
		this.menuCategoryId = builder.menuCategoryId;
		this.menuCategoryDetails = builder.menuCategoryDetails;
		this.createdAt = builder.createdAt;
		this.updatedAt = builder.updatedAt;
	}

	public static Builder builder() {
        return new Builder();
    }
    
    public static class Builder {
    	
    	private Long id;
    	private String name;
    	private String description;
    	private BigDecimal price;
    	private Boolean available;
    	private Long menuCategoryId;
    	private MenuCategory menuCategoryDetails;
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
		
		public Builder menuCategoryId(Long menuCategoryId) {
			this.menuCategoryId = menuCategoryId;
			return this;
		}
		
		public Builder menuCategoryDetails(MenuCategory menuCategoryDetails) {
			this.menuCategoryDetails = menuCategoryDetails;
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
        
		public MenuItem build() {
			return new MenuItem(this);
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

	public Long getMenuCategoryId() {
		return menuCategoryId;
	}
	
	public MenuCategory getMenuCategoryDetails() {
		return menuCategoryDetails;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}    
}

