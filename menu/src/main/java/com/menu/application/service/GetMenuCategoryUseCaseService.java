package com.menu.application.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.menu.application.dto.MenuCategoryDto;
import com.menu.application.mapper.MenuCategoryApplicationMapper;
import com.menu.application.spi.MenuCategoryPersistencePort;
import com.menu.application.usecase.GetMenuCategoryUseCase;
import com.menu.domain.model.MenuCategory;

@Service
public class GetMenuCategoryUseCaseService implements GetMenuCategoryUseCase {
	
	private final MenuCategoryPersistencePort persistencePort;
	private MenuCategoryApplicationMapper mapper;
	
	public GetMenuCategoryUseCaseService(MenuCategoryPersistencePort persistencePort, MenuCategoryApplicationMapper mapper) {
		this.persistencePort = persistencePort;
		this.mapper = mapper;
	}

	@Override
	public Optional<MenuCategoryDto> findById(Long id) {
		
		Optional<MenuCategory> category = persistencePort.getdById(id);
		if (category.isPresent()) {
			MenuCategoryDto categoryDto = mapper.menuCategoryToMenuCategoryDto(category.get());
			return Optional.ofNullable(categoryDto);
		}	
		return Optional.empty();
	}

}
