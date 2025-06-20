package com.inventory.infrastructure.configutarion;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

//Habilita la auditoría de JPA para que funcionen @CreatedDate y @LastModifiedDate
@EnableJpaAuditing

//Marca esta clase como una clase de configuración de Spring
@Configuration
public class JpaConfig {
 // Esta clase no necesita métodos. Solo con las anotaciones es suficiente 
 // para activar la auditoría en todo el proyecto.
}