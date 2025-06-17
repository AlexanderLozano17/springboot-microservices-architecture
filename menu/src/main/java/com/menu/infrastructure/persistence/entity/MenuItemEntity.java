package com.menu.infrastructure.persistence.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "menu_items", indexes = @Index(name = "IDX_MENUITEM_NAME", columnList = "name"))  
public class MenuItemEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

	@Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; 

    @NotBlank(message = "El nombre del ítem no puede estar vacío.") 
    @Size(max = 255, message = "El nombre no puede exceder los 255 caracteres.") 
    @Column(nullable = false, unique = true, length = 255) 
    private String name; 

    @Size(max = 500, message = "La descripción no puede exceder los 500 caracteres.")
    @Column(length = 500) 
    private String description; // Una descripción atractiva para el cliente que se mostrará en el menú.

    @NotNull(message = "El precio no puede ser nulo.") 
    @DecimalMin(value = "0.00", inclusive = false, message = "El precio debe ser mayor que cero.") 
    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal price; // El precio de venta al público de este ítem del menú.

    @NotNull(message = "La disponibilidad no puede ser nula.")
    @Column(nullable = false) 
    private Boolean available; // Indica si el ítem está actualmente disponible para la venta (verdadero/falso).

    @Size(max = 500, message = "La URL de la imagen no puede exceder los 500 caracteres.")
    @Column(name = "image_url", length = 500) 
    private String imageUrl;
    
    // --- Relación: Muchos a Uno (Many-to-One) con MenuCategoryEntity ---
    // MUCHOS MenuItemEntity (ítems de menú) pueden pertenecer a UNA MenuCategoryEntity (categoría).
    @ManyToOne // Define la relación como Muchos a Uno.
    @JoinColumn(name = "category_id", nullable = false) // Mapea la clave foránea en la tabla 'menu_items'. 'name = "category_id"' es el nombre de la columna FK; 'nullable = false' significa que cada ítem de menú DEBE tener una categoría.
    @NotNull(message = "Un ítem de menú debe tener una categoría asociada.") // Anotación de validación: asegura que el campo 'category' no sea nulo.
    private MenuCategoryEntity category; // Referencia a la entidad de la categoría a la que pertenece este ítem del menú.

    // --- Relación: Muchos a Uno (Many-to-One) con DishEntity ---
    // Un MenuItemEntity (ítem de menú) puede estar basado en UN DishEntity (receta base).
    // Permite flexibilidad: un Dish (receta) puede ser usado por varios MenuItems con diferentes nombres o precios.
    // 'nullable = true' permite que un MenuItem exista sin un Dish (ej., para bebidas embotelladas que no tienen una "receta" compleja interna).
    // Si *todo* ítem de menú debe ser una receta, cámbialo a 'nullable = false'.
    @ManyToOne // Define la relación como Muchos a Uno.
    @JoinColumn(name = "dish_id", nullable = true) // Mapea la clave foránea en la tabla 'menu_items'. 'name = "dish_id"' es el nombre de la columna FK; 'nullable = true' permite que un ítem de menú no tenga una receta asociada.
    private DishEntity dish; // Referencia a la entidad del plato/receta base asociado. Será nulo si no hay receta base.

    public MenuItemEntity() {}

    public MenuItemEntity(String name, String description, BigDecimal price, Boolean available, MenuCategoryEntity category, DishEntity dish) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.available = available;
        this.category = category; // Asigna la categoría a la que pertenece este ítem.
        this.dish = dish; // Asigna el plato base (receta) si existe.
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
    public MenuCategoryEntity getCategory() { return category; }
    public void setCategory(MenuCategoryEntity category) { this.category = category; }
    public DishEntity getDish() { return dish; }
    public void setDish(DishEntity dish) { this.dish = dish; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // Si es el mismo objeto en memoria, son iguales.
        if (o == null || getClass() != o.getClass()) return false; // Si el objeto es nulo o de una clase diferente, no son iguales.
        MenuItemEntity that = (MenuItemEntity) o; // Convierte el objeto a MenuItemEntity para comparar.
        return id != null && Objects.equals(id, that.id); // Son iguales si sus IDs no son nulos y son iguales.
    }

    @Override
    public int hashCode() {
        return Objects.hash(id); // Genera un código hash basado en el ID del objeto.
    }
}
