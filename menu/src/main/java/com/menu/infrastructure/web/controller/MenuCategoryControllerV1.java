package com.menu.infrastructure.web.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.menu.application.dto.MenuCategoryDto;
import com.menu.application.usecase.CreateMenuCategoryUseCase;
import com.menu.application.usecase.GetMenuCategoryUseCase;
import com.menu.infrastructure.mapper.MenuCategoryMapper;
import com.menu.infrastructure.persistence.entity.MenuCategoryEntity;
import com.menu.infrastructure.web.model.request.MenuCategoryRequest;
import com.menu.infrastructure.web.model.response.CommonApiResponse;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/v1/menu-category")
public class MenuCategoryControllerV1 {

	private static final Logger LOGGER = LoggerFactory.getLogger(MenuCategoryControllerV1.class);

	private final CreateMenuCategoryUseCase createMenuCategoryUseCase;
	private final GetMenuCategoryUseCase getMenuCategoryUseCase;
	private final MenuCategoryMapper mapper;

	public MenuCategoryControllerV1(CreateMenuCategoryUseCase createMenuCategoryUseCase, 
									GetMenuCategoryUseCase getMenuCategoryUseCase, 
									MenuCategoryMapper mapper) {
		this.createMenuCategoryUseCase = createMenuCategoryUseCase;
		this.getMenuCategoryUseCase = getMenuCategoryUseCase;
		this.mapper = mapper;
	}

	@PostMapping
	public ResponseEntity<EntityModel<CommonApiResponse>> createMenuCategory(@Validated @RequestBody MenuCategoryRequest request) {

		LOGGER.info("Creating new menu category with name: {}", request.getName());
		
		MenuCategoryDto reqDto = mapper.requestToDto(request);
		Optional<MenuCategoryDto> createdCategoryOpt = createMenuCategoryUseCase.create(reqDto);

		if (createdCategoryOpt.isPresent()) {
			MenuCategoryDto categoryDto = createdCategoryOpt.get();

			EntityModel<MenuCategoryDto> resource = EntityModel.of(categoryDto);
			resource.add(Link.of("/api/v1/menu-category").withSelfRel());
			resource.add(Link.of("/api/v1/menu-category/1").withRel("one-categories"));

			return ResponseEntity.status(HttpStatus.CREATED).body(EntityModel.of(CommonApiResponse.success(resource)));
			
		} else {
			LOGGER.warn("Menu category creation failed for name: {}", request.getName());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(EntityModel
					.of(CommonApiResponse.error(HttpStatus.BAD_REQUEST.value(), "Category could not be created")));
		}
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<EntityModel<CommonApiResponse>> getMenuCategory(@PathVariable Long id) {
		
		LOGGER.info("Get one menu category with id: {}", id);
		
		Optional<MenuCategoryDto> entityOpt = getMenuCategoryUseCase.findById(id);
	
		if (entityOpt.isPresent()) {
			MenuCategoryDto categoryDto = entityOpt.get();

			EntityModel<MenuCategoryDto> resource = EntityModel.of(categoryDto);
			resource.add(Link.of("/api/v1/menu-category/" + id).withSelfRel());

			return ResponseEntity.ok(EntityModel.of(CommonApiResponse.success(resource)));
			
		} else {
			LOGGER.warn("Menu category find failed for id: {}", id);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(EntityModel
					.of(CommonApiResponse.error(HttpStatus.BAD_REQUEST.value(), "Category could not be created")));
		}		
	}
}
