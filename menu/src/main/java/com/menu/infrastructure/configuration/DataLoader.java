package com.menu.infrastructure.configuration;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.menu.infrastructure.persistence.entity.MenuCategoryEntity;
import com.menu.infrastructure.persistence.entity.MenuItemEntity;
import com.menu.infrastructure.persistence.repository.MenuCategoryJpaRepository;
import com.menu.infrastructure.persistence.repository.MenuItemJpaRepository;

@Component
public class DataLoader implements CommandLineRunner {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DataLoader.class);

    private final MenuCategoryJpaRepository menuCategoryjpaRepository;
    private final MenuItemJpaRepository  menuItemjpaRepository;

    public DataLoader(MenuCategoryJpaRepository menuCategoryjpaRepository, MenuItemJpaRepository  menuItemjpaRepository) {
        this.menuCategoryjpaRepository = menuCategoryjpaRepository;
        this.menuItemjpaRepository = menuItemjpaRepository;
    }

    @Override
    @Transactional 
    public void run(String... args) throws Exception {
        
    	 menuCategoryRun();
    	 menuItemRun();
    }
    
    /**
     * Creación de las categorias del menú
     */
    public void menuCategoryRun() {
    	LOGGER.info("Iniciando verificación e inicialización de datos de categorías de menú...");

        if (menuCategoryjpaRepository.count() == 0) {
            LOGGER.info("No se encontraron categorías de menú. Inicializando con datos por defecto.");

            try {
                List<MenuCategoryEntity> categories = List.of(
                		MenuCategoryEntity.builder().id(1L).name("Entrantes").description("Platos para comenzar la comida").build(),
                        MenuCategoryEntity.builder().id(2L).name("Platos principales").description("Platos principales del menú").build(),
                        MenuCategoryEntity.builder().id(3L).name("Postres").description("Dulces y postres deliciosos").build(),
                        MenuCategoryEntity.builder().id(4L).name("Bebidas").description("Bebidas frías y calientes").build()
                );

                menuCategoryjpaRepository.saveAll(categories);
                LOGGER.info("Datos de categorías de menú inicializados exitosamente. Se insertaron {} categorías.", categories.size());

            } catch (Exception e) {
                LOGGER.error("Error al inicializar los datos de categorías de menú: {}", e.getMessage(), e);

            }
        } else {
            LOGGER.info("Ya existen categorías de menú en la base de datos. No se requiere inicialización.");
        }

        LOGGER.info("Finalizada la verificación e inicialización de datos de categorías de menú.");
    }
    
    /**
     * Creación de los item de menú
     */
    public void menuItemRun() {
    	LOGGER.info("Iniciando verificación e inicialización de datos de menú items...");

    	if (menuItemjpaRepository.count() == 0) {
            LOGGER.info("No se encontraron ítems de menú. Inicializando con datos por defecto.");

            try {
                // Recuperar las categorías recién creadas (o existentes) para asociar los ítems
                List<MenuCategoryEntity> allCategories = menuCategoryjpaRepository.findAll();

                // Crear un mapa para acceder fácilmente a las categorías por nombre
                Map<String, MenuCategoryEntity> categoryMap = allCategories.stream()
                        .collect(Collectors.toMap(MenuCategoryEntity::getName, category -> category));

                MenuCategoryEntity entrantes = categoryMap.get("Entrantes");
                MenuCategoryEntity platosPrincipales = categoryMap.get("Platos principales");
                MenuCategoryEntity postres = categoryMap.get("Postres");
                MenuCategoryEntity bebidas = categoryMap.get("Bebidas");

                List<MenuItemEntity> itemsToSave = List.of(
                        // Entrantes
                        MenuItemEntity.builder()
                                .name("Ensalada César")
                                .description("Lechuga romana, crutones, queso parmesano y aderezo César.")
                                .price(new BigDecimal("8.50"))
                                .available(true)
                                .menuCategory(entrantes) // Asocia con la categoría Entrantes
                                .build(),
                        MenuItemEntity.builder()
                                .name("Patatas Bravas")
                                .description("Patatas fritas con salsa brava picante y alioli.")
                                .price(new BigDecimal("6.00"))
                                .available(true)
                                .menuCategory(entrantes)
                                .build(),
                        MenuItemEntity.builder()
                                .name("Sopa de Tomate")
                                .description("Sopa cremosa de tomate fresco con albahaca.")
                                .price(new BigDecimal("5.50"))
                                .available(true)
                                .menuCategory(entrantes)
                                .build(),

                        // Platos principales
                        MenuItemEntity.builder()
                                .name("Salmón a la Plancha")
                                .description("Filete de salmón fresco con guarnición de verduras al vapor.")
                                .price(new BigDecimal("18.00"))
                                .available(true)
                                .menuCategory(platosPrincipales) // Asocia con Platos principales
                                .build(),
                        MenuItemEntity.builder()
                                .name("Lasaña Boloñesa")
                                .description("Capas de pasta, carne picada, bechamel y queso gratinado.")
                                .price(new BigDecimal("14.50"))
                                .available(true)
                                .menuCategory(platosPrincipales)
                                .build(),
                        MenuItemEntity.builder()
                                .name("Risotto de Champiñones")
                                .description("Arroz cremoso con variedad de champiñones y trufa.")
                                .price(new BigDecimal("16.00"))
                                .available(true)
                                .menuCategory(platosPrincipales)
                                .build(),

                        // Postres
                        MenuItemEntity.builder()
                                .name("Tarta de Queso")
                                .description("Clásica tarta de queso con base de galleta y mermelada de frutos rojos.")
                                .price(new BigDecimal("7.00"))
                                .available(true)
                                .menuCategory(postres) // Asocia con Postres
                                .build(),
                        MenuItemEntity.builder()
                                .name("Brownie con Helado")
                                .description("Cálido brownie de chocolate con una bola de helado de vainilla.")
                                .price(new BigDecimal("6.50"))
                                .available(true)
                                .menuCategory(postres)
                                .build(),

                        // Bebidas
                        MenuItemEntity.builder()
                                .name("Agua Mineral")
                                .description("Botella de agua mineral natural.")
                                .price(new BigDecimal("2.00"))
                                .available(true)
                                .menuCategory(bebidas) // Asocia con Bebidas
                                .build(),
                        MenuItemEntity.builder()
                                .name("Refresco de Cola")
                                .description("Lata de refresco de cola.")
                                .price(new BigDecimal("2.50"))
                                .available(true)
                                .menuCategory(bebidas)
                                .build(),
                        MenuItemEntity.builder()
                                .name("Cerveza Local")
                                .description("Cerveza artesanal de la región.")
                                .price(new BigDecimal("4.00"))
                                .available(true)
                                .menuCategory(bebidas)
                                .build(),
                        MenuItemEntity.builder()
                                .name("Café Expresso")
                                .description("Café concentrado y aromático.")
                                .price(new BigDecimal("2.20"))
                                .available(true)
                                .menuCategory(bebidas)
                                .build()
                );

                menuItemjpaRepository.saveAll(itemsToSave);
                LOGGER.info("Datos de ítems de menú inicializados exitosamente. Se insertaron {} ítems.", itemsToSave.size());

            } catch (Exception e) {
                LOGGER.error("Error al inicializar los datos de ítems de menú: {}", e.getMessage(), e);
                throw new RuntimeException("Fallo al inicializar datos de ítems", e);
            }
        } else {
            LOGGER.info("Ya existen ítems de menú en la base de datos. No se requiere inicialización de ítems.");
        }

        LOGGER.info("Finalizada la verificación e inicialización de datos de categorías y ítems de menú.");
    }

}
