package com.menu.application.usecases.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.menu.application.dtos.MenuItemDto;
import com.menu.application.usecases.MenuItemUseCase;

@Service
public class MenuItemUseCaseImpl implements MenuItemUseCase {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MenuItemUseCaseImpl.class);

	@Override
	public Optional<MenuItemDto> createMenuItemD(MenuItemDto itemDto) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public boolean unavailableMenuItemD(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean availableMenuItemD(Long id) {
		// TODO Auto-generated method stub
		return false;
	}
}
