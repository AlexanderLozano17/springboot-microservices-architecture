package com.menu.application.usecases.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.menu.application.dtos.MenuCategoryDto;
import com.menu.application.usecases.MenuCategoryUseCase;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MenuCategoryUseCaseImpl implements MenuCategoryUseCase {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MenuCategoryUseCaseImpl.class);

	@Override
	public Optional<MenuCategoryDto> createMenuCategory(MenuCategoryDto categoryDto) {
		// TODO Auto-generated method stub
		return null;
	}

}
