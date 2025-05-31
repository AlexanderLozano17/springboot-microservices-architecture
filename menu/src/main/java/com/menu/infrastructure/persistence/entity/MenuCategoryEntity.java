package com.menu.infrastructure.persistence.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Entity
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
@Table(name = "menu_category", indexes = @Index(name = "idx_category_name", columnList = "name"))
public class MenuCategoryEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre de la categoría no puede estar vacío.") 
    @Size(max = 50, message = "El nombre no puede exceder los 50 caracteres.") 
    @Column(nullable = false, unique = true)
    private String name;

    @Size(max = 500, message = "La descripción no puede exceder los 500 caracteres.")
    @Column(columnDefinition = "TEXT")
    private String description;
    
    @CreatedDate // Asignado automáticamente al crear la entidad
    @Column(nullable = false, updatable = false) 
    private LocalDateTime createdAt;

    @LastModifiedDate // Asignado automáticamente al actualizar la entidad
    @Column(nullable = false) 
    private LocalDateTime updatedAt;

    public MenuCategoryEntity(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public MenuCategoryEntity(Long id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    public MenuCategoryEntity(String name, String description, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }    
}
