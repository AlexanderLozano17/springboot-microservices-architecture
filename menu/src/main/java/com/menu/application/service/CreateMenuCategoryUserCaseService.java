package com.menu.application.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.menu.application.dto.MenuCategoryDto;
import com.menu.application.mapper.MenuCategoryApplicationMapper;
import com.menu.application.spi.MenuCategoryPersistencePort;
import com.menu.application.usecase.CreateMenuCategoryUseCase;
import com.menu.domain.model.MenuCategory;

@Service
public class CreateMenuCategoryUserCaseService implements CreateMenuCategoryUseCase {

	private final MenuCategoryPersistencePort persistencePort;
	private MenuCategoryApplicationMapper mapper;
	
	public CreateMenuCategoryUserCaseService(MenuCategoryPersistencePort persistencePort, MenuCategoryApplicationMapper mapper) {
		this.persistencePort = persistencePort;
		this.mapper = mapper;
	}
	
	@Override
	public Optional<MenuCategoryDto> create(MenuCategoryDto menuCategoryDto) {
		MenuCategory menuCategory = mapper.menuCategoryDtoToMenuCategory(menuCategoryDto);
		Optional<MenuCategory> savedCategory = persistencePort.save(menuCategory);
		MenuCategoryDto categoryDto = mapper.menuCategoryToMenuCategoryDto(savedCategory.get());
		return Optional.ofNullable(categoryDto);
	}

}
