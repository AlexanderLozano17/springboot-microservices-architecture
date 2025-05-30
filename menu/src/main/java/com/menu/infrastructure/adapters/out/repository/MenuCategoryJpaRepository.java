package com.menu.infrastructure.adapters.out.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.menu.infrastructure.adapters.out.entities.MenuCategoryEntity;

@Repository
public interface MenuCategoryJpaRepository extends JpaRepository<MenuCategoryEntity, Long> {

}
