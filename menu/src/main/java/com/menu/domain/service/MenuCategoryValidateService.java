package com.menu.domain.service;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.menu.domain.model.MenuCategory;

public class MenuCategoryValidateService {

	/**
	 * Valida que los campos no sean nulos y, en el caso de String, que no estén
	 * vacíos o solo en blanco. Lanza IllegalArgumentException si hay errores.
	 */
    private void validateFields(MenuCategory menuCategory) {
       
    	Map<String, Object> dataToValidate = new HashMap<>();
        dataToValidate.put("id", menuCategory.getId());
        dataToValidate.put("name", menuCategory.getName());
        dataToValidate.put("description", menuCategory.getDescription());

        List<String> errors = dataToValidate.entrySet().stream()
            .filter(entry -> {
                Object value = entry.getValue();
                if (value == null) return true;
                if (value instanceof String) return ((String) value).trim().isEmpty();
                return false;
            })
            .map(entry -> "Campo inválido: '" + entry.getKey() + "'")
            .collect(Collectors.toList());

        if (!errors.isEmpty()) {
            throw new IllegalArgumentException(String.join(", ", errors));
        }
    }
}
