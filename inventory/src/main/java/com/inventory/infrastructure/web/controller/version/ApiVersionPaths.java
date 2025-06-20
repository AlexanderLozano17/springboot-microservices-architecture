package com.inventory.infrastructure.web.controller.version;

/**
 * Clase que centraliza las rutas base y versiones para la API REST.
 * Esto ayuda a mantener consistencia y facilidad de mantenimiento.
 */
public class ApiVersionPaths {

    /***********************************************
              Versiones generales de la API
     ***********************************************/
    public static final String API_V1 = "/api/v1";

    
    /***********************************************
	    Endpoints generales, relativos a la versión
	 ***********************************************/
    public static final String DISH = "/dish";
    public static final String PRODUCT = "/product";
    public static final String RECIPE_INGREDIENT = "/recipe-ingredient";

    
    /***********************************************
	  Construcción de rutas completas (version + endpoint)
	 ***********************************************/
    public static final String V1_DISH = API_V1 + DISH;
    public static final String V1_PRODUCT = API_V1 + PRODUCT;
    public static final String V1_RECIPE_INGREDIENT = API_V1 + RECIPE_INGREDIENT;
    
    private ApiVersionPaths() {}

}
