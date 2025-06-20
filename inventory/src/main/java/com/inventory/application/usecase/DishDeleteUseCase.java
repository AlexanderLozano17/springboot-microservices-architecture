package com.inventory.application.usecase;

public interface DishDeleteUseCase {

	/**
	 * 
	 * @return
	 */
	boolean deleteById(Long id);
	
	/**
	 * 
	 * @return
	 */
	boolean softDeleteById(Long id);
}
