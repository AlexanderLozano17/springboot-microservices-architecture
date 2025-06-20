package com.inventory.infrastructure.configutarion.data;

import java.math.BigDecimal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.inventory.infrastructure.persistence.entity.ProductEntity;
import com.inventory.infrastructure.persistence.repository.ProductJpaRepository;
import com.inventory.utils.UnitOfMeasure;

@Component
public class ProductDataInitializer {

	private static final Logger LOGGER = LoggerFactory.getLogger(DishDataInitializer.class);

    private final ProductJpaRepository productJpaRepository;
    
    public ProductDataInitializer(ProductJpaRepository productJpaRepository) {
    	this.productJpaRepository = productJpaRepository;
    }

    @Transactional
    public void productRun() {
        LOGGER.info("Iniciando verificación e inicialización de datos de platos (recetas)...");

        if (productJpaRepository.count() == 0) {
            LOGGER.info("No se encontraron platos. Inicializando con datos por defecto.");

            try {
            	
            	List<ProductEntity> products = getProducts();

                productJpaRepository.saveAll(products);
                LOGGER.info("Datos de productos inicializados exitosamente. Se insertaron {} platos.", products.size());

            } catch (Exception e) {
                LOGGER.error("Error al inicializar los datos de productos: {}", e.getMessage(), e);
            }
        } else {
            LOGGER.info("Ya existen productos en la base de datos. No se requiere inicialización.");
        }
        LOGGER.info("Finalizada la verificación e inicialización de datos de productos.");
    }
    
    public static List<ProductEntity> getProducts() {
    	return List.of(
    		new ProductEntity("Papa Pastusa", UnitOfMeasure.KILOGRAMS, new BigDecimal("2.50"), 100.0, "agricola Andina", 20.0, true, "Refrigerador A"),
    		new ProductEntity("Pechuga de Pollo", UnitOfMeasure.KILOGRAMS, new BigDecimal("8.75"), 50.0, "avicola Del Valle", 10.0, true, "Refrigerador B"),
    		new ProductEntity("Arroz Blanco", UnitOfMeasure.KILOGRAMS, new BigDecimal("1.20"), 200.0, "granos Colombia",  50.0, false, "Alacena Seca 1"),
    		new ProductEntity("Tomate Chonto", UnitOfMeasure.KILOGRAMS, new BigDecimal("1.80"), 30.0, "agricola Andina", 5.0, true, "Refrigerador A"),
			new ProductEntity("Cebolla Blanca", UnitOfMeasure.KILOGRAMS, new BigDecimal("1.50"), 40.0,"agricola Andina", 8.0, true,"Alacena Seca 2"),
			new ProductEntity("Aceite Vegetal (Litro)", UnitOfMeasure.LITERS, new BigDecimal("4.00"), 20.0, "distribuciones Nacionales", 5.0, false, "Alacena Seca 3"),
			new ProductEntity( "Sal (Kg)", UnitOfMeasure.KILOGRAMS, new BigDecimal("0.75"), 75.0, "granos Colombia", 15.0, false, "Alacena Seca 1"),
			new ProductEntity("Azúcar (Kg)", UnitOfMeasure.KILOGRAMS, new BigDecimal("1.10"), 60.0, "granos Colombia", 10.0, false, "Alacena Seca 1"),
			new ProductEntity( "Leche Entera (Litro)", UnitOfMeasure.LITERS, new BigDecimal("1.00"), 25.0, "lacteos Frescos", 5.0, true, "Refrigerador Lácteos"),
			new ProductEntity("Huevos (Docena)", UnitOfMeasure.UNITS, new BigDecimal("3.50"), 3.0, "avicola Del Valle", 1.0, true, "Refrigerador A"),
	        new ProductEntity("Limón", UnitOfMeasure.KILOGRAMS, new BigDecimal("1.00"), 5.0, "Fruver San Luis", 1.0, true, "Frutas"),
            new ProductEntity("Agua Purificada", UnitOfMeasure.LITERS, new BigDecimal("0.50"), 20.0, "Agua Pureza", 5.0, false, "Almacén"));

    }
}
