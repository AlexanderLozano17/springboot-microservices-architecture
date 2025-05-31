package com.menu.infrastructure.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.menu.application.dto.MenuCategoryDto;
import com.menu.infrastructure.persistence.entity.MenuCategoryEntity;
import com.menu.infrastructure.web.model.request.MenuCategoryRequest;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MenuCategoryMapper {

	/**
	 * 
	 * @param menuCategoryEntity
	 * @return
	 */
	MenuCategoryDto entityToDto(MenuCategoryEntity menuCategoryEntity);
	
	/**
	 * 
	 * @param MenuCategoryDto
	 * @return
	 */
	MenuCategoryEntity dtoToJpaEntity(MenuCategoryDto MenuCategoryDto); 
	
	/**
	 * 
	 * @param request
	 * @return
	 */
	MenuCategoryEntity requestToJpaEntity(MenuCategoryRequest request); 
	
	/**
	 * 
	 * @param request
	 * @return
	 */
	MenuCategoryDto requestToDto(MenuCategoryRequest request); 

}
