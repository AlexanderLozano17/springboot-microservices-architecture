package com.inventory.infrastructure.web.model.request;

import java.time.LocalDateTime;

public class BaseModelRequest {

	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
	private LocalDateTime deletedAt;
	private Boolean isDeleted = false;
	
	public LocalDateTime getCreatedAt() { return createdAt;	}
	public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt;	}
	public LocalDateTime getUpdatedAt() { return updatedAt;	}
	public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt;	}
	public LocalDateTime getDeletedAt() { return deletedAt;	}
	public void setDeletedAt(LocalDateTime deletedAt) { this.deletedAt = deletedAt;	}
	public Boolean getIsDeleted() { return isDeleted; }
	public void setIsDeleted(Boolean isDeleted) { this.isDeleted = isDeleted; }
}
