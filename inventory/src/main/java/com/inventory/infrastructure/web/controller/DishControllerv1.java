package com.inventory.infrastructure.web.controller;

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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inventory.application.dto.DishDto;
import com.inventory.application.usecase.DishCreateUseCase;
import com.inventory.application.usecase.DishDeleteUseCase;
import com.inventory.application.usecase.DishGetUseCase;
import com.inventory.infrastructure.mapper.DishWebMapper;
import com.inventory.infrastructure.web.controller.version.ApiVersionPaths;
import com.inventory.infrastructure.web.model.request.DishRequest;
import com.inventory.infrastructure.web.model.response.CommonApiResponse;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(ApiVersionPaths.V1_DISH)
@Tag(name = "Dish", description = "Dish API")
public class DishControllerv1 {

	private static final Logger LOGGER = LoggerFactory.getLogger(DishControllerv1.class);

	private final DishGetUseCase dishGetUseCaseService;
	private final DishDeleteUseCase dishDeleteUseCase;
	private final DishCreateUseCase dishCreateUseCase;
	private final DishWebMapper mapper;
	
	public DishControllerv1(DishGetUseCase dishGetUseCaseService, 
							DishDeleteUseCase dishDeleteUseCase,
							DishCreateUseCase dishCreateUseCase,
							DishWebMapper mapper) {
		this.dishGetUseCaseService = dishGetUseCaseService;
		this.dishDeleteUseCase = dishDeleteUseCase;
		this.dishCreateUseCase = dishCreateUseCase;
		this.mapper = mapper;
	}
	
	@PostMapping
	public ResponseEntity<EntityModel<CommonApiResponse>> createDish(@Validated @RequestBody DishRequest request) {
		
		DishDto dishDtoReq = mapper.dishRequestToDishDto(request);		
		Optional<DishDto> saveDish = dishCreateUseCase.save(dishDtoReq);
		
		if (saveDish.isPresent()) {
			DishDto dishDto = saveDish.get();
			
			EntityModel<DishDto> resource = EntityModel.of(dishDto);
			resource.add(Link.of(ApiVersionPaths.V1_DISH).withSelfRel().withType("POST"));
			resource.add(Link.of(ApiVersionPaths.V1_DISH + "/" + dishDto.getId()).withSelfRel().withType("GET"));
			resource.add(Link.of(ApiVersionPaths.V1_DISH).withRel("collection-dish").withType("GET"));
			resource.add(Link.of(ApiVersionPaths.V1_DISH + "/" + dishDto.getId()).withRel("delete-dish").withType("DELETE"));
			
			return ResponseEntity.ok(EntityModel.of(CommonApiResponse.success(resource)));
		}
		
		LOGGER.warn("Failed to create dish: {}", request.getName());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(EntityModel.of(CommonApiResponse
				.error(HttpStatus.BAD_REQUEST.value(), "Failed to create dish: " + request.getName())));
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<EntityModel<CommonApiResponse>> getDishById(@PathVariable Long id) {
		LOGGER.info("init getDishById()");
		
		Optional<DishDto> dishOpt = dishGetUseCaseService.findById(id);
		
		if (dishOpt.isPresent()) {			
			DishDto dishDto = dishOpt.get();
			
			EntityModel<DishDto> resource = EntityModel.of(dishDto);
			resource.add(Link.of(ApiVersionPaths.V1_DISH + "/" + id).withSelfRel().withType("GET"));
			resource.add(Link.of(ApiVersionPaths.V1_DISH).withRel("collection-dish").withType("GET"));
			resource.add(Link.of(ApiVersionPaths.V1_DISH + "/" + id).withRel("delete-dish").withType("DELETE"));
			resource.add(Link.of(ApiVersionPaths.V1_DISH).withRel("create-dish").withType("POST"));
			
			return ResponseEntity.ok(EntityModel.of(CommonApiResponse.success(resource)));
		}
		
		LOGGER.warn("Dish not found with id: {}", id);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(EntityModel.of(
				CommonApiResponse.error(HttpStatus.BAD_REQUEST.value(), "Dish not found with id: " + id)));
	}
	
	@GetMapping
	public ResponseEntity<CommonApiResponse<CollectionModel<DishDto>>> getDishAll() {
		LOGGER.info("init getDishAll()");
		
		List<DishDto> listDishDto = dishGetUseCaseService.findAll();
		
		if (!listDishDto.isEmpty()) {
			
			CollectionModel<DishDto> collectionModel = CollectionModel.of(listDishDto);
			collectionModel.add(Link.of(ApiVersionPaths.V1_DISH).withSelfRel().withType("GET"));
			collectionModel.add(Link.of(ApiVersionPaths.V1_DISH + "/1").withRel("one-dish").withType("GET"));
			collectionModel.add(Link.of(ApiVersionPaths.V1_DISH + "/1").withRel("delete-dish").withType("DELETE"));
			collectionModel.add(Link.of(ApiVersionPaths.V1_DISH).withRel("create-dish").withType("POST"));
			
			return ResponseEntity.ok(CommonApiResponse.success(collectionModel));
		}
		
		LOGGER.warn("Dish not found");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(CommonApiResponse.error(HttpStatus.BAD_REQUEST.value(), "Dish not found"));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<EntityModel<CommonApiResponse>> deleteById(@PathVariable Long id) {
		
		boolean isDeleted = dishDeleteUseCase.softDeleteById(id);
		
		if (isDeleted) {						
			return ResponseEntity.ok(EntityModel.of(CommonApiResponse.success(null)));
		}
		LOGGER.warn("Dish have not been remove");
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
				EntityModel.of(CommonApiResponse.error(HttpStatus.BAD_REQUEST.value(), "Dish have not been remove")));
	}
}
