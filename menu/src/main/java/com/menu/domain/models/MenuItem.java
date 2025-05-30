package com.menu.domain.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.menu.application.dtos.MenuCategoryDto;

import lombok.Data;

@Data
public class MenuItem implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private String name;
    private String description;
    private MenuCategoryDto menuCategoryDto;
    private BigDecimal price;
    private boolean available;
    private LocalDateTime dateAvailable;
    private LocalDateTime dateUnavailable;
}
