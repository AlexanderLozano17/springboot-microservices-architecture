package com.menu.infrastructure.configuration.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInitializer implements CommandLineRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseInitializer.class);

	private final MenuCategoryDataInitializer menuCategoryDataInitializer;
	private final DishDataInitializer dishDataInitializer;
	private final MenuItemDataInitializer menuItemDataInitializer;

	public DatabaseInitializer(MenuCategoryDataInitializer menuCategoryDataInitializer,
							   DishDataInitializer dishDataInitializer, 
							   MenuItemDataInitializer menuItemDataInitializer) {
		this.menuCategoryDataInitializer = menuCategoryDataInitializer;
		this.dishDataInitializer = dishDataInitializer;
		this.menuItemDataInitializer = menuItemDataInitializer;
	}

	@Override
	public void run(String... args) throws Exception {
		LOGGER.info("init" + DatabaseInitializer.class.getName() + " | Insertando datos de pruebas");
		// El orden es CRÍTICO debido a las dependencias de clave foránea.
        // Primero entidades independientes, luego las que tienen dependencias.
        menuCategoryDataInitializer.menuCategoryRun();
        dishDataInitializer.dishRun();
        menuItemDataInitializer.menuItemRun(); // Necesita Category y Dish

        LOGGER.info(DatabaseInitializer.class.getName() + " | fin inserción datos de pruebas");
		
	}

}
