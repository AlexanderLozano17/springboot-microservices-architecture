package com.menu.application.spi;

import java.util.List;
import java.util.Optional;

import com.menu.domain.model.MenuItem;

public interface MenuItemPersistencePort {

	/**
	 * 
	 * @param menuItem
	 * @return
	 */
	Optional<MenuItem> save(MenuItem menuItemDto);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	Optional<MenuItem> getdById(Long id);
	
	/**
	 * 
	 * @return
	 */
	List<MenuItem> getAll();
}
