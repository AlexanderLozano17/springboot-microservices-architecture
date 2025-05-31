package com.menu.infrastructure.configuration;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.menu.infrastructure.persistence.entity.MenuCategoryEntity;
import com.menu.infrastructure.persistence.repository.MenuCategoryJpaRepository;

@Component
public class DataLoader implements CommandLineRunner {
	
	private final MenuCategoryJpaRepository jpaRepository;
	
	public DataLoader(MenuCategoryJpaRepository jpaRepository) {
		this.jpaRepository = jpaRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		if (jpaRepository.count() == 0) {
			
			 List<MenuCategoryEntity> categories = List.of(
		                new MenuCategoryEntity(1L,"Entrantes", "Platos para comenzar la comida"),
		                new MenuCategoryEntity(2L,"Platos principales", "Platos principales del menú"),
		                new MenuCategoryEntity(3L,"Postres", "Dulces y postres deliciosos"),
		                new MenuCategoryEntity(4L,"Bebidas", "Bebidas frías y calientes")
		            );
			 jpaRepository.saveAll(categories);
		}
		
	}

}
