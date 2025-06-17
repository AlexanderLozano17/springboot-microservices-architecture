package com.menu.infrastructure.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.menu.application.dto.MenuItemDto;
import com.menu.domain.model.MenuItem;
import com.menu.infrastructure.persistence.entity.MenuItemEntity;
import com.menu.infrastructure.web.model.request.MenuItemRequest;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MenuItemWebMapper {

	/**
	 * 
	 * @param dto
	 * @return
	 */ 
	@Mapping(target = "category.id", source = "menuCategoryId")
	MenuItemEntity menuItemToEntity(MenuItem menuItem);
	
	/**
	 * 
	 * @param entity
	 * @return
	 */
	@Mapping(target = "menuCategoryId", source="category.id")
	MenuItem entityToMenuItem(MenuItemEntity menuItemEntity);
	
	/**
	 * 
	 * @param request
	 * @return
	 */
	@Mapping(target = "updatedAt", ignore = true)
	MenuItemDto requestToDto(MenuItemRequest menuItemRequest);
    


}
