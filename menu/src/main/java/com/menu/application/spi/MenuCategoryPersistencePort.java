package com.menu.application.spi;

import java.util.Optional;

import com.menu.application.dto.MenuCategoryDto;

public interface MenuCategoryPersistencePort {

	/**
	 * 
	 * @param menuCategory
	 * @return
	 */
	Optional<MenuCategoryDto> save(MenuCategoryDto menuCategory);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	Optional<MenuCategoryDto> findById(Long id);
}
