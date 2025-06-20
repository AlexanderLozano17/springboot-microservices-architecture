package com.inventory.infrastructure.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.inventory.application.service.DishGetUseCaseService;
import com.inventory.infrastructure.web.controller.version.ApiVersionPaths;
import com.inventory.infrastructure.web.model.response.CommonApiResponse;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping(ApiVersionPaths.V1_DISH)
@Tag(name = "Dish", description = "Dish API")
public class DishControllerv1 {

	private static final Logger LOGGER = LoggerFactory.getLogger(DishControllerv1.class);

	private final DishGetUseCaseService getUseCaseService;
	
	public DishControllerv1(DishGetUseCaseService getUseCaseService) {
		this.getUseCaseService = getUseCaseService;
	}
	
	public ResponseEntity<EntityModel<CommonApiResponse>> getDishById() {
		LOGGER.info("init getDishById()");
		
		return null;
	}
}
