package com.menu.application.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.menu.application.dto.MenuCategoryDto;
import com.menu.application.spi.MenuCategoryPersistencePort;
import com.menu.application.usecase.CreateMenuCategoryUseCase;

@Service
public class CreateMenuCategoryUserCaseService implements CreateMenuCategoryUseCase {

	private final MenuCategoryPersistencePort persistencePort;
	
	public CreateMenuCategoryUserCaseService(MenuCategoryPersistencePort persistencePort) {
		this.persistencePort = persistencePort;
	}
	
	@Override
	public Optional<MenuCategoryDto> create(MenuCategoryDto menuCategoryDto) {
		return persistencePort.save(menuCategoryDto);
	}

}
