package com.menu.infrastructure.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.menu.application.dto.MenuCategoryDto;
import com.menu.domain.model.MenuCategory;
import com.menu.infrastructure.persistence.entity.MenuCategoryEntity;
import com.menu.infrastructure.web.model.request.MenuCategoryRequest;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MenuCategoryWebMapper {

	
	/**
	 * 
	 * @param menuCategory
	 * @return
	 */
	MenuCategoryEntity MenuCategoryToMenuCategoryEntity(MenuCategory menuCategory);	
	
	/**
	 * 
	 * @param menuCategory
	 * @return
	 */
	MenuCategory MenuCategoryEntityToMenuCategory(MenuCategoryEntity menuCategory);
	
	/**
	 * 
	 * @param menuCategoryRequest
	 * @return
	 */
	MenuCategoryDto menuCategoryRequestToMenuCategoryDto(MenuCategoryRequest menuCategoryRequest);
	
}
