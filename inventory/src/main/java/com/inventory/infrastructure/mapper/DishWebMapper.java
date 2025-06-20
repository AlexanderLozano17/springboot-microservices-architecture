package com.inventory.infrastructure.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.inventory.domain.model.Dish;
import com.inventory.infrastructure.persistence.entity.DishEntity;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface DishWebMapper {
	
	/**
	 * 
	 * @param entity
	 * @return
	 */
	Dish entityToDish(DishEntity entity);
	
	/**
	 * 
	 * @param dish
	 * @return
	 */
	DishEntity dishtToEntity(Dish dish);
}
