package com.inventory.infrastructure.persistence.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum UnitOfMeasure {

	KILOGRAMS("Kg"),
    GRAMS("g"),
    LITERS("L"),
    MILLILITERS("ml"),
    UNITS("Unidades"),
    TABLESPOONS("Cucharadas"), // Ejemplo: Cucharadas
    TEASPOONS("Cucharaditas"), // Ejemplo: Cucharaditas
    PINCHES("Pizcas");      // Ejemplo: Pizcas
	
	private final String displayValue;

	UnitOfMeasure(String displayValue) {
        this.displayValue = displayValue;
    }

    // Este método se usa cuando necesitas obtener el valor String del enum (ej. para mostrarlo en una interfaz).
    @JsonValue // Indica a Jackson (librería para JSON) que este es el valor que debe usar al serializar el enum a JSON.
    public String getDisplayValue() {
        return displayValue;
    }

    // Este método es útil para convertir un String (ej. el que viene de un JSON o un formulario) de vuelta al enum.
    @JsonCreator // Indica a Jackson que este es el método que debe usar para deserializar el String a un enum.
    public static UnitOfMeasure fromDisplayValue(String text) {
        for (UnitOfMeasure b : UnitOfMeasure.values()) {
            if (b.displayValue.equalsIgnoreCase(text)) {
                return b;
            }
        }
        // Opcional: Puedes lanzar una excepción si el valor no es válido.
        throw new IllegalArgumentException("No hay una constante con el valor de visualización: " + text);
    }
}
