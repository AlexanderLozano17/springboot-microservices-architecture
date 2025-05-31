package com.menu.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.menu.infrastructure.persistence.entity.MenuCategoryEntity;

public interface MenuCategoryJpaRepository extends JpaRepository<MenuCategoryEntity, Long> {

}
