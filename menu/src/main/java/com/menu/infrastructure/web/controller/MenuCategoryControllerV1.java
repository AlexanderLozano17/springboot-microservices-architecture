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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.menu.application.dto.MenuCategoryDto;
import com.menu.application.usecase.CreateMenuCategoryUseCase;
import com.menu.application.usecase.GetMenuCategoryUseCase;
import com.menu.infrastructure.mapper.MenuCategoryMapper;
import com.menu.infrastructure.web.controller.version.ApiVersionPaths;
import com.menu.infrastructure.web.model.request.MenuCategoryRequest;
import com.menu.infrastructure.web.model.response.CommonApiResponse;
import com.menu.infrastructure.web.model.response.ErrorResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(ApiVersionPaths.V1_MENU_CATEGORIA)
@Tag(name = "Menu Category", description = "Menu Category API")
public class MenuCategoryControllerV1 {

	private static final Logger LOGGER = LoggerFactory.getLogger(MenuCategoryControllerV1.class);

	private final CreateMenuCategoryUseCase createMenuCategoryUseCase;
	private final GetMenuCategoryUseCase getMenuCategoryUseCase;
	private final MenuCategoryMapper mapper;

	public MenuCategoryControllerV1(CreateMenuCategoryUseCase createMenuCategoryUseCase,
			GetMenuCategoryUseCase getMenuCategoryUseCase, MenuCategoryMapper mapper) {
		this.createMenuCategoryUseCase = createMenuCategoryUseCase;
		this.getMenuCategoryUseCase = getMenuCategoryUseCase;
		this.mapper = mapper;
	}

	@Operation(summary = "Create a new menu category", 
			description = "Allows you to create a new menu category by providing a name and description.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Category created successfully", 
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = CommonApiResponse.class))),
			@ApiResponse(responseCode = "400", description = "Invalid request or validation error", 
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))) })
	@PostMapping
	public ResponseEntity<EntityModel<CommonApiResponse>> createMenuCategory(@Validated @RequestBody MenuCategoryRequest request) {
		LOGGER.info("init createMenuCategory()");
 
		MenuCategoryDto reqDto = mapper.requestToDto(request);
		Optional<MenuCategoryDto> createdCategoryOpt = createMenuCategoryUseCase.create(reqDto);

		if (createdCategoryOpt.isPresent()) {
			MenuCategoryDto categoryDto = createdCategoryOpt.get();

			EntityModel<MenuCategoryDto> resource = EntityModel.of(categoryDto);
			resource.add(Link.of(ApiVersionPaths.V1_MENU_CATEGORIA).withSelfRel());
			resource.add(Link.of(ApiVersionPaths.V1_MENU_CATEGORIA + "/1").withRel("one-categories"));

			return ResponseEntity.status(HttpStatus.CREATED).body(EntityModel.of(CommonApiResponse.success(resource)));
		} 
		
		LOGGER.warn("Failed to create menu category: {}", request.getName());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(EntityModel.of(CommonApiResponse
				.error(HttpStatus.BAD_REQUEST.value(), "Failed to create menu category: " + request.getName())));

	}

	@Operation(summary = "Get a menu category by ID", 
			description = "Retrieves the details of a specific menu category using its ID.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Category found successfully", 
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = CommonApiResponse.class))),
			@ApiResponse(responseCode = "400", description = "Invalid ID or category not found", 
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))) })
	@GetMapping("/{id}")
	public ResponseEntity<EntityModel<CommonApiResponse>> getMenuCategoryById(@PathVariable Long id) {
		LOGGER.info("init getMenuCategoryById()");

		Optional<MenuCategoryDto> entityOpt = getMenuCategoryUseCase.findById(id);

		if (entityOpt.isPresent()) {
			MenuCategoryDto categoryDto = entityOpt.get();

			EntityModel<MenuCategoryDto> resource = EntityModel.of(categoryDto);
			resource.add(Link.of(ApiVersionPaths.V1_MENU_CATEGORIA + "/" +id).withSelfRel());

			return ResponseEntity.ok(EntityModel.of(CommonApiResponse.success(resource)));
		} 
		
		LOGGER.warn("Menu category not found with id: {}", id);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(EntityModel.of(
				CommonApiResponse.error(HttpStatus.BAD_REQUEST.value(), "Menu category not found with id: " + id)));
	
	}

}
