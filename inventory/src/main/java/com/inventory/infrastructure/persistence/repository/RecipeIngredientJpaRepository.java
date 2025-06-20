package com.inventory.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inventory.infrastructure.persistence.entity.RecipeIngredientEntity;

public interface RecipeIngredientJpaRepository extends JpaRepository<RecipeIngredientEntity, Long> {

}
