package com.menu.domain.models;

import java.io.Serializable;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class MenuCategory implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long id;
    private String name;
    private String description;
    private LocalDateTime createAt;
}
