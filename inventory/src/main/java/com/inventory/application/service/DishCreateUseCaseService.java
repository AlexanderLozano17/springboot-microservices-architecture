package com.inventory.application.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.inventory.application.dto.DishDto;
import com.inventory.application.mapper.DishApplicationMapper;
import com.inventory.application.spi.DishPersistencePort;
import com.inventory.application.usecase.DishCreateUseCase;
import com.inventory.domain.model.Dish;

@Service
public class DishCreateUseCaseService implements DishCreateUseCase {
	
	private final DishPersistencePort dishPersistencePort;
	private final DishApplicationMapper mapper;
	
	public DishCreateUseCaseService(DishPersistencePort dishPersistencePort, DishApplicationMapper mapper) {
		this.dishPersistencePort = dishPersistencePort;
		this.mapper = mapper;
	}

	@Override
	public Optional<DishDto> save(DishDto dishDto) {		
		Dish dist = mapper.dishtDtoToDish(dishDto);
		Optional<Dish> saveDish = dishPersistencePort.save(dist);
		DishDto distDto = mapper.dishToDistDto(saveDish.get());
		return Optional.ofNullable(distDto);
	}

}
