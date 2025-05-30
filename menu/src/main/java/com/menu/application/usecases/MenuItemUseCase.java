package com.menu.application.usecases;

import java.util.Optional;

import com.menu.application.dtos.MenuItemDto;

public interface MenuItemUseCase {

	/**
	 * 
	 * @param itemDto
	 * @return
	 */
	Optional<MenuItemDto> createMenuItemD(MenuItemDto itemDto);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	boolean unavailableMenuItemD(Long id);
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	boolean availableMenuItemD(Long id);
}
