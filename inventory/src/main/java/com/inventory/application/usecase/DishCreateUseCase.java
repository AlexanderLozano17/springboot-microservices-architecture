package com.inventory.application.usecase;

import java.util.Optional;

import com.inventory.application.dto.DishDto;

public interface DishCreateUseCase {

	/**
	 * 
	 * @param dishDto
	 * @return
	 */
	Optional<DishDto> save(DishDto dishDto); 
}
