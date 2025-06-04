package com.menu.infrastructure.web.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.CollectionModel;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.menu.application.dto.MenuItemDto;
import com.menu.application.usecase.CreateMenuItemUseCase;
import com.menu.application.usecase.GetMenuItemUseCase;
import com.menu.infrastructure.mapper.MenuItemWebMapper;
import com.menu.infrastructure.web.controller.version.ApiVersionPaths;
import com.menu.infrastructure.web.model.request.MenuItemRequest;
import com.menu.infrastructure.web.model.response.CommonApiResponse;
import com.menu.infrastructure.web.model.response.ErrorResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(ApiVersionPaths.V1_MENU_ITEM)
@Tag(name = "Menu Item", description = "Menu Item API")
public class MenuItemControllerV1 {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MenuItemControllerV1.class);
	
	private final CreateMenuItemUseCase createMenuItemUseCase;
	private final GetMenuItemUseCase getMenuItemUseCase;
	private final MenuItemWebMapper mapper;
	
	public MenuItemControllerV1(CreateMenuItemUseCase createMenuItemUseCase, 
								GetMenuItemUseCase getMenuItemUseCase,
								MenuItemWebMapper mapper) {
		this.createMenuItemUseCase = createMenuItemUseCase;
		this.getMenuItemUseCase = getMenuItemUseCase;
		this.mapper = mapper;
	}
	 
	@Operation(summary = "Create a new menu item", 
			description = "Allows you to create a new menu ietm by providing a name and description.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Item created successfully", 
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = CommonApiResponse.class))),
			@ApiResponse(responseCode = "400", description = "Invalid request or validation error", 
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))) })
	@PostMapping
	public ResponseEntity<EntityModel<CommonApiResponse>> createMenuItem(@Validated @RequestBody MenuItemRequest request) {
		LOGGER.info("init createMenuItem()");
		
		MenuItemDto reqDto = mapper.requestToDto(request);
		Optional<MenuItemDto> createItemOpt = createMenuItemUseCase.createMenuItem(reqDto);
		
		if (createItemOpt.isPresent()) {			
			MenuItemDto itemDto = createItemOpt.get();
			
			EntityModel<MenuItemDto> resource = EntityModel.of(itemDto);
			resource.add(Link.of(ApiVersionPaths.V1_MENU_ITEM).withSelfRel());
			resource.add(Link.of(ApiVersionPaths.V1_MENU_ITEM + "/" + itemDto.getId()).withRel("item"));
			resource.add(Link.of(ApiVersionPaths.V1_MENU_ITEM + "/" + itemDto.getId() + "?includeCategory=true").withRel("item-with-category"));
			resource.add(Link.of(ApiVersionPaths.V1_MENU_ITEM).withRel("collections-item"));
			
			return ResponseEntity.status(HttpStatus.CREATED).body(EntityModel.of(CommonApiResponse.success(resource)));
		}
		
		LOGGER.warn("Failed to create menu item: {}", request.getName());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(EntityModel.of(CommonApiResponse
				.error(HttpStatus.BAD_REQUEST.value(), "Failed to create menu item: " + request.getName()))); 

	}
	
    @Operation(summary = "Get a menu item by ID",
            description = "Retrieves the details of a specific menu item using its ID. " +
            "Optionally, it can include the full details of its main category.")
	@ApiResponses(value = {
		@ApiResponse(responseCode = "200", description = "Menu item found successfully",
		      content = @Content(mediaType = "application/json", schema = @Schema(implementation = CommonApiResponse.class))),
		@ApiResponse(responseCode = "404", description = "Menu item not found",
		      content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class) )),
		@ApiResponse(responseCode = "400", description = "Invalid request (e.g., malformed ID)",
		      content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))) })
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<CommonApiResponse>>  getMenuItemById(
        @Parameter(description = "ID del ítem de menú a recuperar", example = "1")
        @PathVariable Long id,
        
        @Parameter(description = "Si es 'true', incluye los detalles completos de la categoría del ítem.", example = "false")
        @RequestParam(required = false, defaultValue = "false") boolean includeCategory) {
		LOGGER.info("init getMenuItemById()");
		
		Optional<MenuItemDto> itemOpt = getMenuItemUseCase.findById(id, includeCategory);
		
		if (itemOpt.isPresent()) {
			MenuItemDto itemDto = itemOpt.get();
			
			EntityModel<MenuItemDto> resource = EntityModel.of(itemDto);
			resource.add(Link.of(ApiVersionPaths.V1_MENU_ITEM + "/" + id).withSelfRel());
			resource.add(Link.of(ApiVersionPaths.V1_MENU_ITEM + "/" + id + "?includeCategory=true").withRel("item-with-category"));
			resource.add(Link.of(ApiVersionPaths.V1_MENU_ITEM).withRel("collections-item"));
			
			return ResponseEntity.ok(EntityModel.of(CommonApiResponse.success(resource)));
		}
		
		LOGGER.warn("Menu item not found with id: {}", id);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(EntityModel.of(
				CommonApiResponse.error(HttpStatus.BAD_REQUEST.value(), "Menu item not found with id: " + id)));
	}
	
	@Operation(summary = "Get all menu Item", 
			description = "Retrieves the details of a specific menu Item using its ID.")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Item found successfully", 
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = CommonApiResponse.class))),
			@ApiResponse(responseCode = "400", description = "Invalid items not found", 
					content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))) })
	@GetMapping
	public ResponseEntity<CommonApiResponse<CollectionModel<MenuItemDto>>> getAllMenuItem() {
		LOGGER.info("init getMenuItemAll()");

		List<MenuItemDto> listItem = getMenuItemUseCase.findAll();

		if (!listItem.isEmpty()) {			
			
			CollectionModel<MenuItemDto> collectionModel = CollectionModel.of(listItem);
			collectionModel.add(Link.of(ApiVersionPaths.V1_MENU_ITEM ).withSelfRel());
			collectionModel.add(Link.of(ApiVersionPaths.V1_MENU_ITEM + "/" + 1).withRel("item"));			
			collectionModel.add(Link.of(ApiVersionPaths.V1_MENU_ITEM + "/1?includeCategory=true").withRel("item-with-category"));

			
			return ResponseEntity.ok(CommonApiResponse.success(collectionModel));
		}
		
		LOGGER.warn("Menu item not found");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(CommonApiResponse.error(HttpStatus.BAD_REQUEST.value(), "Menu item not found"));
	}
}
