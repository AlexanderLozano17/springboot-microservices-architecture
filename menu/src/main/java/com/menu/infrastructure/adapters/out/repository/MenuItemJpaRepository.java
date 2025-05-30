package com.menu.infrastructure.adapters.out.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.menu.infrastructure.adapters.out.entities.MenuItemEntity;

@Repository
public interface MenuItemJpaRepository extends JpaRepository<MenuItemEntity, Long> {

}
