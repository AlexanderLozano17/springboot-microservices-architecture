package com.menu.infrastructure.adapters.out;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.menu.domain.models.MenuCategory;
import com.menu.domain.service.MenuCategoryRepositoryService;
import com.menu.infrastructure.adapters.out.entities.MenuCategoryEntity;
import com.menu.infrastructure.adapters.out.mappers.MenuCategoryEntityMapper;
import com.menu.infrastructure.adapters.out.repository.MenuCategoryJpaRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MenuCategoryAdapter implements MenuCategoryRepositoryService {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MenuCategoryAdapter.class);
	
	private final MenuCategoryJpaRepository menuCategoryJpaRepository;
	private final MenuCategoryEntityMapper categoryEntityMapper;
	
	@Override
	public Optional<MenuCategory> createMenuCategory(MenuCategoryEntity categoryEntity) {
		
		MenuCategoryEntity savedEntity = this.menuCategoryJpaRepository.save(categoryEntity);

		MenuCategory menuCategory = categoryEntityMapper.MenuCategoryEntityToMenuCategory(savedEntity);
		
		return Optional.ofNullable(menuCategory);
	}

}
