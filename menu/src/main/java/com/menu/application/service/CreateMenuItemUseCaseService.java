package com.menu.application.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.menu.application.dto.MenuItemDto;
import com.menu.application.mapper.MenuItemApplicationMapper;
import com.menu.application.spi.MenuItemPersistencePort;
import com.menu.application.usecase.CreateMenuItemUseCase;
import com.menu.domain.model.MenuItem;

@Service
public class CreateMenuItemUseCaseService implements CreateMenuItemUseCase {
	
	private final MenuItemPersistencePort persistencePort;
	private final MenuItemApplicationMapper mapper;
	
	public CreateMenuItemUseCaseService(MenuItemPersistencePort persistencePort, MenuItemApplicationMapper mapper) {
		this.persistencePort = persistencePort;
		this.mapper = mapper;
	}

	@Override
	public Optional<MenuItemDto> createMenuItem(MenuItemDto menuItemDto) {
		
		MenuItem menuItem = mapper.MenuItemDtoToMenuItem(menuItemDto);
		Optional<MenuItem> savedMenuItemOpt = persistencePort.save(menuItem);	
		
		if (savedMenuItemOpt.isPresent()) {
			MenuItemDto itemDto = mapper.menuItemToMenuItemDto(savedMenuItemOpt.get());
			return Optional.ofNullable(itemDto);
		}
		return Optional.empty();
	}

}
