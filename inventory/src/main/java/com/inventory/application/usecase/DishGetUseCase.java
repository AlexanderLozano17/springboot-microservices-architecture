package com.inventory.application.usecase;

import java.util.List;
import java.util.Optional;

import com.inventory.application.dto.DishDto;

public interface DishGetUseCase {

	/**
	 * 
	 * @param id
	 * @return
	 */
	Optional<DishDto> findById(Long id);

	/**
	 * 
	 * @return
	 */
	List<DishDto> findAll();
}
