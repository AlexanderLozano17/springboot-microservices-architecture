package com.menu.infrastructure.adapter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.menu.application.spi.MenuItemPersistencePort;
import com.menu.domain.model.MenuItem;
import com.menu.infrastructure.mapper.MenuItemWebMapper;
import com.menu.infrastructure.persistence.entity.MenuItemEntity;
import com.menu.infrastructure.persistence.repository.MenuItemJpaRepository;

@Repository
public class MenuItemPersistenceAdapter implements MenuItemPersistencePort {
	
	private final MenuItemJpaRepository menuItemJpaRepository;
	private final MenuItemWebMapper mapper;
	
	public MenuItemPersistenceAdapter(MenuItemJpaRepository menuItemJpaRepository, MenuItemWebMapper mapper) {
		this.menuItemJpaRepository = menuItemJpaRepository;
		this.mapper = mapper;
	}

	@Override
	public Optional<MenuItem> save(MenuItem menuItem) {		
		MenuItemEntity entity = mapper.menuItemToEntity(menuItem);
		MenuItemEntity saveEntity = menuItemJpaRepository.save(entity);					
		return Optional.ofNullable(mapper.entityToMenuItem(saveEntity));
	}

	@Override
	public Optional<MenuItem> getdById(Long id) {
		Optional<MenuItemEntity> entity = menuItemJpaRepository.findById(id);		
		if (entity.isPresent()) {
			return Optional.ofNullable(mapper.entityToMenuItem(entity.get()));
		}
		return Optional.empty();
	}

	@Override
	public List<MenuItem> getAll() {
		return menuItemJpaRepository.findAll().stream()
				.map(mapper::entityToMenuItem)
				.collect(Collectors.toList());
	}

}
