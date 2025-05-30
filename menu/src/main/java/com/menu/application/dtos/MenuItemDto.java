package com.menu.application.dtos;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Schema(description = "Data transfer object representing a menu item.")
public class MenuItemDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "Unique identifier of the menu item.", example = "1")
    private Long id;

    @Schema(description = "Name of the menu item.", example = "Margherita Pizza")
    private String name;

    @Schema(description = "Detailed description of the menu item.", example = "Classic pizza with tomato sauce, mozzarella cheese, and fresh basil.")
    private String description;

    @Schema(description = "Category to which the menu item belongs.")
    private MenuCategoryDto menuCategoryDto;

    @Schema(description = "Price of the menu item.", example = "35.50")
    private BigDecimal price;

    @Schema(description = "Availability status of the menu item.", example = "true")
    private boolean available;

    @Schema(description = "Date and time when the menu item becomes available.", example = "2020-06-14T00:00:00")
    private LocalDateTime dateAvailable;

    @Schema(description = "Date and time when the menu item becomes unavailable.", example = "2020-12-31T23:59:59")
    private LocalDateTime dateUnavailable;
}
