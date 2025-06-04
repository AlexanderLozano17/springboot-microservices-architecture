package com.menu.application.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.menu.application.dto.MenuCategoryDto;
import com.menu.application.spi.MenuCategoryPersistencePort;
import com.menu.application.usecase.GetMenuCategoryUseCase;

@Service
public class GetMenuCategoryUseCaseService implements GetMenuCategoryUseCase {
	
	private final MenuCategoryPersistencePort persistencePort; 
	
	public GetMenuCategoryUseCaseService(MenuCategoryPersistencePort persistencePort) {
		this.persistencePort = persistencePort;
	}

	@Override
	public Optional<MenuCategoryDto> findById(Long id) {
		return persistencePort.getdById(id);
	}

}
