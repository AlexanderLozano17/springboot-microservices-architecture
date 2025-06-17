package com.menu.infrastructure.persistence.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "menu_categories", indexes = @Index(name = "IDX_CATEGORY_NAME", columnList = "name")) 
@EntityListeners(AuditingEntityListener.class) 
public class MenuCategoryEntity extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) 
    private Long id;

    @NotBlank(message = "El nombre de la categoría no puede estar vacío.") 
    @Size(max = 100, message = "El nombre de la categoría no puede exceder los 100 caracteres.") 
    @Column(nullable = false, unique = true, length = 100) 
    private String name; // El nombre de la categoría, tal como se mostrará en el menú (ej., "Entradas", "Bebidas Calientes").

    @Size(max = 255, message = "La descripción no puede exceder los 255 caracteres.")
    @Column(length = 255) 
    private String description; // Una breve descripción de la categoría, útil para organizar o para el cliente.

    @Column(name = "display_order") 
    private Integer displayOrder; // Un número opcional para definir el orden en que las categorías se mostrarán en el menú (ej., Entradas: 1, Platos principales: 2).

    // --- Relación: Uno a Muchos (One-to-Many) con MenuItemEntity ---
    // Esta parte le dice a JPA que UNA MenuCategoryEntity (esta categoría) puede tener MUCHOS MenuItemEntity (ítems de menú).
    // 'mappedBy = "category"': Indica que la relación es bidireccional y que el campo 'category' en la clase MenuItemEntity es el "dueño" de la relación. Esto significa que la clave foránea (FK) en la base de datos estará en la tabla 'menu_items', apuntando a 'menu_categories'.
    // 'cascade = CascadeType.ALL': Significa que si realizas operaciones (como guardar, actualizar o eliminar) en esta MenuCategoryEntity, estas operaciones se propagarán automáticamente a todos los MenuItemEntity asociados. Por ejemplo, si borras una categoría, todos los ítems de menú de esa categoría también se borrarán.
    // 'orphanRemoval = true': Esta es una opción más específica de cascada. Si un MenuItemEntity que estaba asociado a esta categoría se "desvincula" (es decir, se elimina de la colección 'menuItems' de esta categoría), JPA lo borrará automáticamente de la base de datos porque se considera un "huérfano".
    @OneToMany(mappedBy = "category", cascade = jakarta.persistence.CascadeType.ALL, orphanRemoval = true)
    private Set<MenuItemEntity> menuItems = new HashSet<>(); // Una colección (Set) de todos los ítems de menú que pertenecen a esta categoría. Usamos Set para asegurar que no haya ítems duplicados.

    public MenuCategoryEntity() {}

    public MenuCategoryEntity(String name, String description, Integer displayOrder) {
        this.name = name;
        this.description = description;
        this.displayOrder = displayOrder;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public Integer getDisplayOrder() { return displayOrder; }
    public void setDisplayOrder(Integer displayOrder) { this.displayOrder = displayOrder; }
    public Set<MenuItemEntity> getMenuItems() { return menuItems; }
    public void setMenuItems(Set<MenuItemEntity> menuItems) { this.menuItems = menuItems; }

    // --- Métodos de conveniencia para la gestión de la relación bidireccional ---
    // Estos métodos son útiles cuando trabajas con la colección 'menuItems'. Ayudan a mantener la coherencia
    // en ambos lados de la relación (MenuCategory -> MenuItem y MenuItem -> MenuCategory).
    public void addMenuItem(MenuItemEntity menuItem) {
        this.menuItems.add(menuItem); // Añade el ítem de menú a la colección de esta categoría.
        menuItem.setCategory(this); // ¡Importante! También establece esta categoría como la categoría del ítem de menú. Así, la relación se establece en ambos sentidos.
    }

    public void removeMenuItem(MenuItemEntity menuItem) {
        this.menuItems.remove(menuItem); // Elimina el ítem de menú de la colección de esta categoría.
        menuItem.setCategory(null); // ¡Importante! Rompe la referencia de la categoría en el ítem de menú. Si 'orphanRemoval' es true, esto podría llevar a la eliminación del ítem.
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true; // Si es el mismo objeto en memoria, son iguales.
        if (o == null || getClass() != o.getClass()) return false; // Si el objeto es nulo o de una clase diferente, no son iguales.
        MenuCategoryEntity that = (MenuCategoryEntity) o; // Convierte el objeto a MenuCategoryEntity para comparar.
        return id != null && Objects.equals(id, that.id); // Son iguales si sus IDs no son nulos y son iguales.
    }

    @Override
    public int hashCode() {
        return Objects.hash(id); // Genera un código hash basado en el ID del objeto.
    }
    
}
