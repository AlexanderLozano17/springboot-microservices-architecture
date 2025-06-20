package com.inventory.infrastructure.configutarion.data;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.inventory.infrastructure.persistence.entity.DishEntity;
import com.inventory.infrastructure.persistence.entity.ProductEntity;
import com.inventory.infrastructure.persistence.entity.RecipeIngredientEntity;
import com.inventory.infrastructure.persistence.repository.DishJpaRepository;
import com.inventory.infrastructure.persistence.repository.ProductJpaRepository;
import com.inventory.infrastructure.persistence.repository.RecipeIngredientJpaRepository;
import com.inventory.utils.UnitOfMeasure;

@Component
public class RecipeIngredientDataInitializer {

	private static final Logger LOGGER = LoggerFactory.getLogger(RecipeIngredientDataInitializer.class);
	
	private final RecipeIngredientJpaRepository jpaRepository;
	private final DishJpaRepository dishJpaRepository;
	private final ProductJpaRepository productJpaRepository;
	
	
	public RecipeIngredientDataInitializer(RecipeIngredientJpaRepository jpaRepository, 
											DishJpaRepository dishJpaRepository,
											ProductJpaRepository productJpaRepository) {
		this.jpaRepository = jpaRepository;
		this.dishJpaRepository = dishJpaRepository;
		this.productJpaRepository = productJpaRepository;
	}
	
	@Transactional
	public void RecipeIngredientRun() {
		LOGGER.info("Iniciando verificación e inicialización de datos de platos (recetas)...");

        if (jpaRepository.count() == 0) {
            LOGGER.info("No se encontraron platos. Inicializando con datos por defecto.");

            try {
            	
            	List<RecipeIngredientEntity> recipeIngredients = getRecipeIngredient();

            	jpaRepository.saveAll(recipeIngredients);
                LOGGER.info("Datos de productos inicializados exitosamente. Se insertaron {} platos.", recipeIngredients.size());

            } catch (Exception e) {
                LOGGER.error("Error al inicializar los datos de productos: {}", e.getMessage(), e);
            }
        } else {
            LOGGER.info("Ya existen productos en la base de datos. No se requiere inicialización.");
        }
        LOGGER.info("Finalizada la verificación e inicialización de datos de productos.");
    }
	
	public List<RecipeIngredientEntity> getRecipeIngredient() {

        // Buscar DishEntities por nombre (asegúrate que existan en sampleDishes)
        DishEntity ajiaco = dishJpaRepository.findByName("Ajiaco Santafereño").orElseThrow(() -> new RuntimeException("El plato 'Ajiaco Santafereño' no se encontró en la base de datos."));        		  
        DishEntity lomoSalteado = dishJpaRepository.findByName("Lomo Salteado Base").orElseThrow(() -> new RuntimeException("Lomo Salteado Base no encontrado"));
        DishEntity limonada = dishJpaRepository.findByName("Limonada Natural").orElseThrow(() -> new RuntimeException("Limonada Natural no encontrado"));
        DishEntity arrozBlanco = dishJpaRepository.findByName("Arroz Blanco Simple").orElseThrow(() -> new RuntimeException("Arroz Blanco Simple no encontrado"));


        // Buscar ProductEntities por nombre (asegúrate que existan en sampleProducts)
        ProductEntity papaPastusa = productJpaRepository.findByName("Papa Pastusa").orElseThrow(() -> new RuntimeException("Papa Pastusa no encontrada"));
        ProductEntity pechugaPollo = productJpaRepository.findByName("Pechuga de Pollo").orElseThrow(() -> new RuntimeException("Pechuga de Pollo no encontrada"));
        ProductEntity arrozBlancoProd = productJpaRepository.findByName("Arroz Blanco").orElseThrow(() -> new RuntimeException("Arroz Blanco (producto) no encontrado"));
        ProductEntity tomateChonto = productJpaRepository.findByName("Tomate Chonto").orElseThrow(() -> new RuntimeException("Tomate Chonto no encontrado"));
        ProductEntity cebollaBlanca = productJpaRepository.findByName("Cebolla Blanca").orElseThrow(() -> new RuntimeException("Cebolla Blanca no encontrada"));
        ProductEntity aceiteVegetal = productJpaRepository.findByName("Aceite Vegetal (Litro)").orElseThrow(() -> new RuntimeException("Aceite Vegetal no encontrado"));
        ProductEntity sal = productJpaRepository.findByName("Sal (Kg)").orElseThrow(() -> new RuntimeException("Sal no encontrada"));
        ProductEntity azucar = productJpaRepository.findByName("Azúcar (Kg)").orElseThrow(() -> new RuntimeException("Azúcar no encontrada"));
        ProductEntity limon = productJpaRepository.findByName("Limón").orElseThrow(() -> new RuntimeException("Limón no encontrado"));
        ProductEntity agua = productJpaRepository.findByName("Agua Purificada").orElseThrow(() -> new RuntimeException("Agua Purificad no encontrada"));


        return List.of(
            // Ingredientes para "Ajiaco Santafereño"
            new RecipeIngredientEntity(ajiaco, papaPastusa, new BigDecimal("0.500"), UnitOfMeasure.KILOGRAMS),
            new RecipeIngredientEntity(ajiaco, pechugaPollo, new BigDecimal("0.300"), UnitOfMeasure.KILOGRAMS),
            new RecipeIngredientEntity(ajiaco, cebollaBlanca, new BigDecimal("0.100"), UnitOfMeasure.KILOGRAMS),
            new RecipeIngredientEntity(ajiaco, sal, new BigDecimal("0.010"), UnitOfMeasure.KILOGRAMS),

            // Ingredientes para "Lomo Salteado Base"
            new RecipeIngredientEntity(lomoSalteado, pechugaPollo, new BigDecimal("0.250"), UnitOfMeasure.KILOGRAMS),
            new RecipeIngredientEntity(lomoSalteado, tomateChonto, new BigDecimal("0.150"), UnitOfMeasure.KILOGRAMS),
            new RecipeIngredientEntity(lomoSalteado, cebollaBlanca, new BigDecimal("0.080"), UnitOfMeasure.KILOGRAMS),
            new RecipeIngredientEntity(lomoSalteado, aceiteVegetal, new BigDecimal("0.020"), UnitOfMeasure.MILLILITERS),
            new RecipeIngredientEntity(lomoSalteado, sal, new BigDecimal("0.005"), UnitOfMeasure.KILOGRAMS),

            // Ingredientes para "Limonada Natural"
            new RecipeIngredientEntity(limonada, limon, new BigDecimal("0.200"), UnitOfMeasure.KILOGRAMS),
            new RecipeIngredientEntity(limonada, azucar, new BigDecimal("0.050"), UnitOfMeasure.KILOGRAMS),
            new RecipeIngredientEntity(limonada, agua, new BigDecimal("1.000"), UnitOfMeasure.LITERS),

            // Ingredientes para "Arroz Blanco Simple"
            new RecipeIngredientEntity(arrozBlanco, arrozBlancoProd, new BigDecimal("0.150"), UnitOfMeasure.KILOGRAMS),
            new RecipeIngredientEntity(arrozBlanco, agua, new BigDecimal("0.250"), UnitOfMeasure.LITERS),
            new RecipeIngredientEntity(arrozBlanco, sal, new BigDecimal("0.002"), UnitOfMeasure.KILOGRAMS)
        );
	}
	    
}
