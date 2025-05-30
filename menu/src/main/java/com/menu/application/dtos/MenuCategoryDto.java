package com.menu.application.dtos;

import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "Data transfer object representing a menu category.")
public class MenuCategoryDto implements Serializable {

    private static final long serialVersionUID = 1L;

	@Schema(description = "Unique identifier of the menu category.", example = "1")
    private Long id;

    @Schema(description = "Name of the menu category.", example = "Pizzas")
    private String name;

    @Schema(description = "Description of the menu category.", example = "All kinds of pizzas including vegetarian and meat options.")
    private String description;

    @Schema(description = "Timestamp when the category was created.", example = "2024-05-29T14:30:00")
    private LocalDateTime create_at;
}
