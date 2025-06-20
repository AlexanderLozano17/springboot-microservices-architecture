package com.inventory.application.spi;

import java.util.List;
import java.util.Optional;

import com.inventory.domain.model.Dish;

public interface DishPersistencePort {
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	Optional<Dish> getById(Long id);
	
	/**
	 * 
	 * @return
	 */
	List<Dish> getAll();
	
	/**
	 * 
	 * @param dish
	 * @return
	 */
	Optional<Dish> save(Dish dish);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	Optional<Dish> update(Dish dish);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	boolean delete(Long id);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public boolean softDeleteById(Long id);
}
