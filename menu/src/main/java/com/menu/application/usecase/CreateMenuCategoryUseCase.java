package com.menu.application.usecase;

import java.util.Optional;

import com.menu.application.dto.MenuCategoryDto;

public interface CreateMenuCategoryUseCase {

	/**
	 * 
	 * @param menuCategoryDto
	 * @return
	 */
	Optional<MenuCategoryDto> create(MenuCategoryDto menuCategoryDto);
}
