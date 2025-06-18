package com.inventory.infrastructure.persistence.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.inventory.infrastructure.persistence.entity.DishEntity;

@Repository
public interface DishJpaRepository extends JpaRepository<DishEntity, Long> {

	Optional<DishEntity> findByName(String string);

}
