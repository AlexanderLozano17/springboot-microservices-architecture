package com.menu.infrastructure.persistence.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.menu.domain.model.BaseModel;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
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

    // --- Relación: Uno a Muchos (One-to-Many) con MenuItemEntity ---
    // Un DishEntity (esta receta base) puede ser la base para MUCHOS MenuItemEntity (ítems que aparecen en tu menú).
    // 'mappedBy = "dish"': Indica que la relación es bidireccional y que el campo 'dish' en la clase MenuItemEntity es el "dueño" de la relación. La clave foránea estará en la tabla 'menu_items'.
    // 'cascade = CascadeType.DETACH': Aquí se usa DETACH. Significa que si eliminas un DishEntity, los MenuItemEntity asociados no se eliminan de la base de datos; simplemente se "desvinculan" o se convierten en huérfanos (su campo 'dish_id' podría quedar nulo si la FK es nullable). Ten cuidado si esperas que se borren, en ese caso usarías CASCADE.REMOVE.
    @OneToMany(mappedBy = "dish", cascade = jakarta.persistence.CascadeType.DETACH)
    private Set<MenuItemEntity> menuItems = new HashSet<>(); // Una colección (Set) de todos los ítems de menú que están basados en este plato/receta.

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
    public Set<MenuItemEntity> getMenuItems() { return menuItems; }
    public void setMenuItems(Set<MenuItemEntity> menuItems) { this.menuItems = menuItems; }


    // --- Métodos de conveniencia para la gestión de la relación bidireccional con MenuItemEntity ---
    // Ayudan a mantener la consistencia en ambos lados de la relación (Dish -> MenuItem y MenuItem -> Dish).
    public void addMenuItem(MenuItemEntity menuItem) {
        this.menuItems.add(menuItem); // Añade el ítem de menú a la colección de este plato.
        menuItem.setDish(this); // ¡Importante! También establece este plato como el plato base del ítem de menú.
    }

    public void removeMenuItem(MenuItemEntity menuItem) {
        this.menuItems.remove(menuItem); // Elimina el ítem de menú de la colección de este plato.
        menuItem.setDish(null); // ¡Importante! Rompe la referencia del plato en el ítem de menú.
    }

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
