package com.menu.infrastructure.configuration.data;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.menu.infrastructure.persistence.entity.DishEntity;
import com.menu.infrastructure.persistence.repository.DishJpaRepository;

@Component
public class DishDataInitializer {

    private static final Logger LOGGER = LoggerFactory.getLogger(DishDataInitializer.class);

    private final DishJpaRepository dishJpaRepository;

    public DishDataInitializer(DishJpaRepository dishJpaRepository) {
        this.dishJpaRepository = dishJpaRepository;
    }

    @Transactional
    public void dishRun() {
        LOGGER.info("Iniciando verificación e inicialización de datos de platos (recetas)...");

        if (dishJpaRepository.count() == 0) {
            LOGGER.info("No se encontraron platos. Inicializando con datos por defecto.");

            try {
                List<DishEntity> dishes = List.of(
                    new DishEntity("Ajiaco Santafereño", "Sopa tradicional colombiana con tres tipos de papa y pollo."),
                    new DishEntity("Lomo Salteado Base", "Preparación base de lomo salteado con verduras."),
                    new DishEntity("Arroz Blanco Simple", "Cocción básica de arroz blanco."),
                    new DishEntity("Limonada Natural", "Bebida refrescante a base de limón y azúcar.")
                );

                dishJpaRepository.saveAll(dishes);
                LOGGER.info("Datos de platos inicializados exitosamente. Se insertaron {} platos.", dishes.size());

            } catch (Exception e) {
                LOGGER.error("Error al inicializar los datos de platos: {}", e.getMessage(), e);
            }
        } else {
            LOGGER.info("Ya existen platos en la base de datos. No se requiere inicialización.");
        }
        LOGGER.info("Finalizada la verificación e inicialización de datos de platos.");
    }
}
