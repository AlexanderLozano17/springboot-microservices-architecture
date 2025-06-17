package com.menu.infrastructure.configuration.data;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.menu.infrastructure.persistence.entity.MenuCategoryEntity;
import com.menu.infrastructure.persistence.repository.MenuCategoryJpaRepository;

@Component
public class MenuCategoryDataInitializer {

	private static final Logger LOGGER = LoggerFactory.getLogger(MenuCategoryDataInitializer.class);

	private final MenuCategoryJpaRepository menuCategoryJpaRepository;

	public MenuCategoryDataInitializer(MenuCategoryJpaRepository menuCategoryJpaRepository) {
		this.menuCategoryJpaRepository = menuCategoryJpaRepository;
	}

	@Transactional // Asegura que toda la operación sea atómica.
	public void menuCategoryRun() {
		LOGGER.info("Iniciando verificación e inicialización de datos de categorías de menú...");

		if (menuCategoryJpaRepository.count() == 0) {
			LOGGER.info("No se encontraron categorías de menú. Inicializando con datos por defecto.");

			try {
				// Importante: Si usas GenerationType.IDENTITY, NO asignes el ID manualmente.
				// La DB se encargará de ello. Si lo asignas, puede haber conflictos.
				List<MenuCategoryEntity> categories = List.of(
						new MenuCategoryEntity("Entrantes", "Platos para comenzar la comida", 1),
						new MenuCategoryEntity("Platos principales", "Platos principales del menú", 2),
						new MenuCategoryEntity("Postres", "Dulces y postres deliciosos", 3),
						new MenuCategoryEntity("Bebidas", "Bebidas frías y calientes", 4)
						);

				menuCategoryJpaRepository.saveAll(categories);
				LOGGER.info("Datos de categorías de menú inicializados exitosamente. Se insertaron {} categorías.",
						categories.size());

			} catch (Exception e) {
				LOGGER.error("Error al inicializar los datos de categorías de menú: {}", e.getMessage(), e);
			}
		} else {
			LOGGER.info("Ya existen categorías de menú en la base de datos. No se requiere inicialización.");
		}
		LOGGER.info("Finalizada la verificación e inicialización de datos de categorías de menú.");
	}
}
