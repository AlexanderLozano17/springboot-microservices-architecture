package com.menu.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.menu.infrastructure.persistence.entity.MenuItemEntity;

public interface MenuItemJpaRepository extends JpaRepository<MenuItemEntity, Long> {

}
