package com.menu.application.dto;

import java.time.LocalDateTime;

public class MenuCategoryDto {

	private Long id;
	private String name;
	private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
		
	public MenuCategoryDto(Builder builder) {
		this.id = builder.id;
		this.name = builder.name;
		this.description = builder.description;
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
		
		public Builder createdAt(LocalDateTime createdAt) {
			this.createdAt = createdAt;
			return this;
		}
		
		public Builder updatedAt(LocalDateTime updatedAt) {			
			this.updatedAt = updatedAt;
			return this;
		}
		
		public MenuCategoryDto build() {			
			return new MenuCategoryDto(this);
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

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}
}
