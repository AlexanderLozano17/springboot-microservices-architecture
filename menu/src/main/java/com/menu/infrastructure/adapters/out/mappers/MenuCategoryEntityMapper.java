package com.menu.infrastructure.adapters.out.mappers;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.menu.domain.models.MenuCategory;
import com.menu.infrastructure.adapters.out.entities.MenuCategoryEntity;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MenuCategoryEntityMapper {
	
	@Mapping(target = "id", source = "id")
	@Mapping(target = "name", source = "name")
	@Mapping(target = "description", source = "description")
	@Mapping(target = "createAt", source = "createAt")
	MenuCategoryEntity menuCategoryToMenuCategoryEntity(MenuCategory menuCategory);

    @InheritInverseConfiguration
    MenuCategory MenuCategoryEntityToMenuCategory(MenuCategoryEntity menuCategoryEntity);
}
