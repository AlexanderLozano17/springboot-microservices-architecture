package com.menu.application.usecases;

import java.util.Optional;

import com.menu.application.dtos.MenuCategoryDto;

public interface MenuCategoryUseCase {

	/**
	 * 
	 * @param categoryDto
	 * @return
	 */
	Optional<MenuCategoryDto> createMenuCategory(MenuCategoryDto categoryDto);
}
