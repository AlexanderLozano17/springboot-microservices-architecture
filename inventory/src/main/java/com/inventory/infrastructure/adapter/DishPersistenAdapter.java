package com.inventory.infrastructure.adapter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.inventory.application.spi.DishPersistencePort;
import com.inventory.domain.model.Dish;
import com.inventory.infrastructure.mapper.DishWebMapper;
import com.inventory.infrastructure.persistence.entity.DishEntity;
import com.inventory.infrastructure.persistence.repository.DishJpaRepository;

@Repository
public class DishPersistenAdapter implements DishPersistencePort {
	
	private final DishJpaRepository dishJpaRepository;
	private final DishWebMapper mapper;
	
	public DishPersistenAdapter(DishJpaRepository dishJpaRepository, DishWebMapper mapper) {
		this.dishJpaRepository = dishJpaRepository;
		this.mapper = mapper;
	}

	@Override
	public Optional<Dish> getById(Long id) {		
		return dishJpaRepository.findById(id)
				.map(mapper::entityToDish);	
	}

	@Override
	public List<Dish> getAll() {
		return dishJpaRepository.findAll().stream()
				.map(mapper::entityToDish)
				.collect(Collectors.toList());
	}

	@Override
	public Optional<Dish> save(Dish dish) {		
		DishEntity entity = mapper.dishtToEntity(dish);
		DishEntity saveEntity = dishJpaRepository.save(entity);
		return Optional.ofNullable(mapper.entityToDish(saveEntity));
	}

	@Override
	public Optional<Dish> update(Dish dish) {		
		DishEntity entity = mapper.dishtToEntity(dish);		
		DishEntity updateEntity = dishJpaRepository.save(entity);		
		return Optional.ofNullable(mapper.entityToDish(updateEntity));
	}

	@Override
	public boolean delete(Long id) {
		if (dishJpaRepository.existsById(id)) {
			dishJpaRepository.deleteById(id);
			return true;
		}
		return false;		
	}

	@Override
	public boolean softDeleteById(Long id) {
		
		Optional<DishEntity> dishEntity = dishJpaRepository.findById(id);		
		if (dishEntity.isPresent()) {
			DishEntity entity = dishEntity.get();
			entity.softDelete(entity);
			dishJpaRepository.save(entity);
			return true;
		}
		return false;	
	}

}
