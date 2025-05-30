package com.menu.domain.service;

import java.util.Optional;

import com.menu.domain.models.MenuCategory;
import com.menu.infrastructure.adapters.out.entities.MenuCategoryEntity;

public interface MenuCategoryRepositoryService {

	public Optional<MenuCategory> createMenuCategory(MenuCategoryEntity categoryDto);
}
