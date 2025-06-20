package com.inventory.domain.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class Dish extends BaseModel implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private String name;
    private String description;

    public Dish() {}

    public Dish(Long id, String name, String description, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        setCreatedAt(createdAt);
        setUpdatedAt(updatedAt);
    }

    public Dish(String name, String description) {
        this.name = name;
        this.description = description;
    }

    // --- Getters y Setters ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dish that = (Dish) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Dish{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", description='" + description + '\'' +
               ", createdAt=" + getCreatedAt() +
               ", updatedAt=" + getUpdatedAt() +
               '}';
    }
}
