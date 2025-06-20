package com.inventory.application.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.inventory.application.dto.DishDto;
import com.inventory.application.mapper.DishApplicationMapper;
import com.inventory.application.spi.DishPersistencePort;
import com.inventory.application.usecase.DishGetUseCase;
import com.inventory.domain.model.Dish;

public class DishGetUseCaseService implements DishGetUseCase {
	
	private final DishPersistencePort dishPersistencePort;
	private final DishApplicationMapper mapper;
	
	public DishGetUseCaseService(DishPersistencePort dishPersistencePort, DishApplicationMapper mapper) {
		this.dishPersistencePort = dishPersistencePort;
		this.mapper = mapper;
	}

	@Override
	public Optional<DishDto> findById(Long id) {
		
		Optional<Dish> dishOpt = dishPersistencePort.getById(id);		
		return dishOpt.map(dish -> {
			DishDto dishDto =  mapper.dishToDistDto(dish);			
			return dishDto;
		});
	}

	@Override
	public List<DishDto> findAll() {
		return dishPersistencePort.getAll().stream()
				.map(mapper::dishToDistDto)
				.collect(Collectors.toList());
	}

}
