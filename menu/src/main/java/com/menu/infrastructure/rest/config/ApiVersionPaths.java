package com.menu.infrastructure.rest.config;

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
    public static final String MENU_CATEGORY = "/menu-category";
    public static final String MENU_ITEM = "/menu-item";

    
    /***********************************************
	  Construcción de rutas completas (version + endpoint)
	 ***********************************************/
    public static final String V1_MENU_CATEGORY = API_V1 + MENU_CATEGORY;
    public static final String V1_MENU_ITEM = API_V1 + MENU_ITEM;
    
    private ApiVersionPaths() {}

}
