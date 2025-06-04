package com.menu.infrastructure.adapter;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.menu.application.spi.MenuCategoryPersistencePort;
import com.menu.domain.model.MenuCategory;
import com.menu.infrastructure.mapper.MenuCategoryWebMapper;
import com.menu.infrastructure.persistence.entity.MenuCategoryEntity;
import com.menu.infrastructure.persistence.repository.MenuCategoryJpaRepository;

@Repository
public class MenuCategoryPersistenceAdapter implements MenuCategoryPersistencePort {

	private final MenuCategoryJpaRepository repository;
	private final MenuCategoryWebMapper mapper;
	
	public MenuCategoryPersistenceAdapter(MenuCategoryJpaRepository repository, MenuCategoryWebMapper mapper) {
		this.repository = repository;
		this.mapper = mapper;
	}
		
	@Override
	public Optional<MenuCategory> save(MenuCategory menuCategory) {
		MenuCategoryEntity entity = mapper.MenuCategoryToMenuCategoryEntity(menuCategory);
		MenuCategoryEntity savedEntity = repository.save(entity);
		return Optional.ofNullable(mapper.MenuCategoryEntityToMenuCategory(savedEntity));
	}

	@Override
	public Optional<MenuCategory> getdById(Long id) {
		Optional<MenuCategoryEntity> entity = repository.findById(id);		
		if (entity.isPresent()) {
			return Optional.ofNullable(mapper.MenuCategoryEntityToMenuCategory(entity.get()));
		}
		return Optional.empty();
		
	}

}
