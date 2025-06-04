package com.menu.application.usecase;

import java.util.Optional;

import com.menu.application.dto.MenuCategoryDto;

public interface GetMenuCategoryUseCase {

	/**
	 * 
	 * @param id
	 * @return
	 */
	 Optional<MenuCategoryDto> findById(Long id);
	 
}
