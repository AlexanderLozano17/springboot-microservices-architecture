package com.inventory.infrastructure.persistence.entity;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Table(name = "dishes", indexes = {@Index(name = "IDX_DISH_NAME", columnList = "name")})
@Entity
public class DishEntity extends BaseEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;

    @NotBlank(message = "El nombre del plato no puede estar vacío.") 
    @Size(max = 50, message = "El nombre del plato no puede exceder los 50 caracteres.")
    @Column(nullable = false, unique = true, length = 50)
    private String name;

    @Size(max = 500, message = "La descripción del plato no puede exceder los 500 caracteres.") 
    @Column(length = 500) 
    private String description;

    public DishEntity() {}

    public DishEntity(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Long getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescripcion() { return description; }
    public void setDescription(String description) { this.description = description; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // Si es el mismo objeto en memoria, son iguales.
        if (o == null || getClass() != o.getClass()) return false; // Si el objeto es nulo o de una clase diferente, no son iguales.
        DishEntity that = (DishEntity) o; // Convierte el objeto a DishEntity para comparar.
        return id != null && Objects.equals(id, that.id); // Son iguales si sus IDs no son nulos y son iguales.
    }

    @Override
    public int hashCode() {
        return Objects.hash(id); // Genera un código hash basado en el ID del objeto.
    }
}
