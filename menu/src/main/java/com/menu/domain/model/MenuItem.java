package com.menu.domain.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.menu.application.dto.MenuCategoryDto;
import com.menu.application.dto.MenuItemDto.Builder;

public class MenuItem extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private String description;
	private BigDecimal price;
	private Boolean available;
	private Long menuCategoryId;
	private MenuCategory menuCategoryDetails;  
    
    public MenuItem() {}
    
	public MenuItem(Long id, String name, String description, BigDecimal price, Boolean available, Long menuCategoryId,
			MenuCategory menuCategoryDetails, LocalDateTime createdAt, LocalDateTime updatedAt) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.available = available;
		this.menuCategoryId = menuCategoryId;
		this.menuCategoryDetails = menuCategoryDetails;
	}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
    public Boolean getAvailable() { return available; }
    public void setAvailable(Boolean available) { this.available = available; }
    public Long getMenuCategoryId() { return menuCategoryId; }
    public void setMenuCategoryId(Long menuCategoryId) { this.menuCategoryId = menuCategoryId; }
    public MenuCategory getMenuCategoryDetails() { return menuCategoryDetails; }
    public void setMenuCategoryDetails(MenuCategory menuCategoryDetails) { this.menuCategoryDetails = menuCategoryDetails; }
}

