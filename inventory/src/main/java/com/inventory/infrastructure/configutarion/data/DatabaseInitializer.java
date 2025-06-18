package com.inventory.infrastructure.configutarion.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DatabaseInitializer implements CommandLineRunner {

	private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseInitializer.class);

	private final DishDataInitializer dishDataInitializer;

	public DatabaseInitializer(DishDataInitializer dishDataInitializer) {
		this.dishDataInitializer = dishDataInitializer;
	}

	@Override
	public void run(String... args) throws Exception {
		LOGGER.info("init" + DatabaseInitializer.class.getName() + " | Insertando datos de pruebas");
		// El orden es CRÍTICO debido a las dependencias de clave foránea.
        // Primero entidades independientes, luego las que tienen dependencias.
        dishDataInitializer.dishRun();

        LOGGER.info(DatabaseInitializer.class.getName() + " | fin inserción datos de pruebas");
		
	}

}
