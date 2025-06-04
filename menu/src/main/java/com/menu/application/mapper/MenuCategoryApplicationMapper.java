package com.menu.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.menu.application.dto.MenuCategoryDto;
import com.menu.domain.model.MenuCategory;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MenuCategoryApplicationMapper {

	/**
	 * 
	 * @param menuCategoryDto
	 * @return
	 */
	MenuCategory menuCategoryDtoToMenuCategory(MenuCategoryDto menuCategoryDto);
	
	/**
	 * 
	 * @param menuCategory
	 * @return
	 */
	MenuCategoryDto menuCategoryToMenuCategoryDto(MenuCategory menuCategory);
}
