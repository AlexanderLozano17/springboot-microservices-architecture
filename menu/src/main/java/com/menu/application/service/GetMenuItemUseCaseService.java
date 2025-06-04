package com.menu.application.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.menu.application.dto.MenuCategoryDto;
import com.menu.application.dto.MenuItemDto;
import com.menu.application.mapper.MenuCategoryApplicationMapper;
import com.menu.application.mapper.MenuItemApplicationMapper;
import com.menu.application.spi.MenuCategoryPersistencePort;
import com.menu.application.spi.MenuItemPersistencePort;
import com.menu.application.usecase.GetMenuItemUseCase;
import com.menu.domain.model.MenuCategory;
import com.menu.domain.model.MenuItem;

@Service
public class GetMenuItemUseCaseService implements GetMenuItemUseCase {

	private final MenuItemPersistencePort itemPersistencePort;
	private final MenuCategoryPersistencePort categoryPersistencePort;
	private final MenuItemApplicationMapper mapper;
	private final MenuCategoryApplicationMapper categoryMappper;
	
	public GetMenuItemUseCaseService(MenuItemPersistencePort itemPersistencePort, 
									 MenuCategoryPersistencePort categoryPersistencePort,
									 MenuCategoryApplicationMapper categoryMappper,
									 MenuItemApplicationMapper mapper) {
		this.itemPersistencePort = itemPersistencePort;
		this.categoryPersistencePort = categoryPersistencePort;
		this.categoryMappper = categoryMappper;
		this.mapper = mapper;
	}
	
	@Override
	public Optional<MenuItemDto> findById(Long id, boolean includeCategory) {
        Optional<MenuItem> itemOpt = itemPersistencePort.getdById(id);        
        
        return itemOpt.map(item -> {
        	MenuItemDto menuItemDto = mapper.menuItemToMenuItemDto(itemOpt.get());
       	 
			if (includeCategory && menuItemDto.getMenuCategoryId() != null && menuItemDto.getMenuCategoryId() != null) {				
				 Long categoryId = menuItemDto.getMenuCategoryId();
				 
                 Optional<MenuCategory> categoryDtoOpt = categoryPersistencePort.getdById(categoryId);

                 if (categoryDtoOpt.isPresent()) {
                	 
                	 MenuCategoryDto categoryDto = categoryMappper.menuCategoryToMenuCategoryDto(categoryDtoOpt.get());               	 
                	 
                     return menuItemDto.toBuilder() // Obtiene un builder pre-inicializado con los valores de menuItemDto
                                       .menuCategoryDetails(categoryDto) // Setea el nuevo valor
                                       .build(); // <-- ¡Devuelve la NUEVA instancia modificada!
                 }
			}
			return menuItemDto;
        });
	}

	@Override
	public List<MenuItemDto> findAll() {
		return itemPersistencePort.getAll().stream()
				.map(mapper::menuItemToMenuItemDto)
				.collect(Collectors.toList());
	}

}
