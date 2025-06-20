package com.inventory.application.service;

import org.springframework.stereotype.Service;

import com.inventory.application.spi.DishPersistencePort;
import com.inventory.application.usecase.DishDeleteUseCase;

@Service
public class DishDeleteUseCaseService implements DishDeleteUseCase {
	
	private final DishPersistencePort dishPersistencePort;
	
	public DishDeleteUseCaseService(DishPersistencePort dishPersistencePort) {
		this.dishPersistencePort= dishPersistencePort;
	}

	@Override
	public boolean deleteById(Long id) {
		return dishPersistencePort.delete(id);
	}

	@Override
	public boolean softDeleteById(Long id) {
		return dishPersistencePort.softDeleteById(id);
	}

}
