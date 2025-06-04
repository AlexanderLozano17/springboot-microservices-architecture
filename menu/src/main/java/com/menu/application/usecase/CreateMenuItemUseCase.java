package com.menu.application.usecase;

import java.util.Optional;

import com.menu.application.dto.MenuItemDto;
import com.menu.domain.model.MenuItem;

public interface CreateMenuItemUseCase {

	/**
	 * 
	 * @param menuItemDto
	 * @return
	 */
	public Optional<MenuItemDto> createMenuItem(MenuItemDto menuItemDto);
}
