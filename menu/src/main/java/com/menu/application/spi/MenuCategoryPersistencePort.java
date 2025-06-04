package com.menu.application.spi;

import java.util.Optional;

import com.menu.domain.model.MenuCategory;

public interface MenuCategoryPersistencePort {

	/**
	 * 
	 * @param menuCategory
	 * @return
	 */
	Optional<MenuCategory> save(MenuCategory menuCategory);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	Optional<MenuCategory> getdById(Long id);
}
