package com.menu.infrastructure.adapters.out.entities;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table(name = "menu_category")
public class MenuCategoryEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Name must not be blank")
	@Size(max = 100, message = "Name must not exceed 100 characters")
	@Column(length = 100, nullable = false)
	private String name;

	@Size(max = 255, message = "Description must not exceed 255 characters")
	@Column(length = 255)
	private String description;

	@Column(updatable = false) // opcional, evita que se actualice
	@CreationTimestamp
	private LocalDateTime createdAt;
}
