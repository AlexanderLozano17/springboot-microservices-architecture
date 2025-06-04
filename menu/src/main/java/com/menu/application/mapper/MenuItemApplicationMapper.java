package com.menu.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.menu.application.dto.MenuItemDto;
import com.menu.domain.model.MenuItem;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MenuItemApplicationMapper {
	
	/**
	 * 
	 * @param menuItemDto
	 * @return
	 */
	@Mapping(target = "menuCategoryDetails.id", source = "menuCategoryId")
	MenuItem MenuItemDtoToMenuItem(MenuItemDto menuItemDto);
	
	/**
	 * 
	 * @param menuItem
	 * @return
	 */
    MenuItemDto menuItemToMenuItemDto(MenuItem menuItem);

}
