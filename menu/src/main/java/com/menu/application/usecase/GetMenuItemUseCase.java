package com.menu.application.usecase;

import java.util.List;
import java.util.Optional;

import com.menu.application.dto.MenuItemDto;

public interface GetMenuItemUseCase {

	/**
	 * 
	 * @param id
	 * @return
	 */
	 Optional<MenuItemDto> findById(Long id, boolean includeCategory);
	 
	 /**
	  * 
	  * @param id
	  * @return
	  */
	 List<MenuItemDto> findAll();
}
