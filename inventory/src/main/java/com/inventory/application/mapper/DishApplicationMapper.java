package com.inventory.application.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.inventory.application.dto.DishDto;
import com.inventory.domain.model.Dish;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface DishApplicationMapper {
	
	/**
	 * 
	 * @param dish
	 * @return
	 */
	DishDto dishToDistDto(Dish dish);
	
	/**
	 * 
	 * @param dishDto
	 * @return
	 */
	Dish dishtDtoToDish(DishDto dishDto);

}
