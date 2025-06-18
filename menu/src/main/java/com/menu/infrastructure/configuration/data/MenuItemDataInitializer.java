package com.menu.infrastructure.configuration.data;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.menu.infrastructure.persistence.entity.MenuCategoryEntity;
import com.menu.infrastructure.persistence.entity.MenuItemEntity;
import com.menu.infrastructure.persistence.repository.MenuCategoryJpaRepository;
import com.menu.infrastructure.persistence.repository.MenuItemJpaRepository;

@Component
public class MenuItemDataInitializer {

    private static final Logger LOGGER = LoggerFactory.getLogger(MenuItemDataInitializer.class);

    private final MenuCategoryJpaRepository menuCategoryJpaRepository;
    private final MenuItemJpaRepository menuItemJpaRepository;

    public MenuItemDataInitializer(MenuCategoryJpaRepository menuCategoryJpaRepository,                                  
                                   MenuItemJpaRepository menuItemJpaRepository) {
        this.menuCategoryJpaRepository = menuCategoryJpaRepository;        
        this.menuItemJpaRepository = menuItemJpaRepository;
    }

    @Transactional
    public void menuItemRun() {
        LOGGER.info("Iniciando verificación e inicialización de datos de ítems de menú...");

        if (menuItemJpaRepository.count() == 0) {
            LOGGER.info("No se encontraron ítems de menú. Inicializando con datos por defecto.");

            try {
                // Carga las categorías y platos que ya deberían haber sido inicializados
                MenuCategoryEntity entradasCategory = menuCategoryJpaRepository.findByName("Entrantes").orElseThrow(() -> new RuntimeException("Category 'Entrantes' no encontrada."));
                MenuCategoryEntity platosFuertesCategory = menuCategoryJpaRepository.findByName("Platos principales").orElseThrow(() -> new RuntimeException("Category 'Platos principales' no encontrada."));
                MenuCategoryEntity bebidasCategory = menuCategoryJpaRepository.findByName("Bebidas").orElseThrow(() -> new RuntimeException("Category 'Bebidas' no encontrada."));
                MenuCategoryEntity postresCategory = menuCategoryJpaRepository.findByName("Postres").orElseThrow(() -> new RuntimeException("Category 'Postres' no encontrada."));

//                DishEntity ajiacoDish = dishJpaRepository.findByName("Ajiaco Santafereño").orElse(null);
//                DishEntity lomoDish = dishJpaRepository.findByName("Lomo Salteado Base").orElse(null);
//                DishEntity limonadaDish = dishJpaRepository.findByName("Limonada Natural").orElse(null);
                // Coca-Cola no tiene Dish, por lo que su DishEntity será null.

                List<MenuItemEntity> menuItems = List.of(
                    new MenuItemEntity("Mini Empanadas", "Deliciosas empanadas de carne con ají.", new BigDecimal("8.50"), true, entradasCategory, null),
                    new MenuItemEntity("Ajiaco Tradicional", "Nuestro famoso ajiaco con crema y alcaparras.", new BigDecimal("25.00"), true, platosFuertesCategory, 1L),
                    new MenuItemEntity("Lomo al Carbón con Arroz y Papa", "Jugoso lomo al carbón acompañado de arroz y papas.", new BigDecimal("35.00"), true, platosFuertesCategory, 2L),
                    new MenuItemEntity("Limonada Fresca", "Limonada 100% natural.", new BigDecimal("5.00"), true, bebidasCategory, 3L),
                    new MenuItemEntity("Gaseosa Cola", "Refrescante bebida de cola.", new BigDecimal("4.00"), true, bebidasCategory, null),
                    new MenuItemEntity("Tres Leches Especial", "Suave bizcocho empapado en tres leches.", new BigDecimal("12.00"), true, postresCategory, null),
                    new MenuItemEntity("Menú Ejecutivo", "Plato fuerte del día con sopa y bebida.", new BigDecimal("28.00"), false, platosFuertesCategory, null)
                );

                menuItemJpaRepository.saveAll(menuItems);
                LOGGER.info("Datos de ítems de menú inicializados exitosamente. Se insertaron {} ítems.", menuItems.size());

            } catch (Exception e) {
                LOGGER.error("Error al inicializar los datos de ítems de menú: {}", e.getMessage(), e);
            }
        } else {
            LOGGER.info("Ya existen ítems de menú en la base de datos. No se requiere inicialización.");
        }
        LOGGER.info("Finalizada la verificación e inicialización de datos de ítems de menú.");
    }
}
