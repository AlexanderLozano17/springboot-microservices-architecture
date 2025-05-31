package com.menu.infrastructure.adapter;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.menu.application.dto.MenuCategoryDto;
import com.menu.application.spi.MenuCategoryPersistencePort;
import com.menu.infrastructure.mapper.MenuCategoryMapper;
import com.menu.infrastructure.persistence.entity.MenuCategoryEntity;
import com.menu.infrastructure.persistence.repository.MenuCategoryJpaRepository;

@Repository
public class MenuCategoryPersistenceAdapter implements MenuCategoryPersistencePort {

	private final MenuCategoryJpaRepository repository;
	private final MenuCategoryMapper mapper;
	
	public MenuCategoryPersistenceAdapter(MenuCategoryJpaRepository repository, MenuCategoryMapper mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}
		
	@Override
	public Optional<MenuCategoryDto> save(MenuCategoryDto menuCategoryDto) {
		MenuCategoryEntity entity = mapper.dtoToJpaEntity(menuCategoryDto);
		MenuCategoryEntity savedEntity = repository.save(entity);
		return Optional.ofNullable(mapper.entityToDto(savedEntity));
	}

	@Override
	public Optional<MenuCategoryDto> findById(Long id) {
		MenuCategoryEntity entity = repository.findById(id).get();
		return Optional.ofNullable(mapper.entityToDto(entity));
	}

}
