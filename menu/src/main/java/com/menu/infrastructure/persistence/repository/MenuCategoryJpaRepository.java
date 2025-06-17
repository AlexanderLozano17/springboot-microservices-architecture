package com.menu.infrastructure.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.menu.infrastructure.persistence.entity.MenuCategoryEntity;

public interface MenuCategoryJpaRepository extends JpaRepository<MenuCategoryEntity, Long> {

	Optional<MenuCategoryEntity> findByName(String string);

}
