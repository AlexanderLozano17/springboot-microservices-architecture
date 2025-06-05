# 🍽️ Módulo Menu - Spring Boot + OpenAPI + Arquitectura Limpia

Este módulo forma parte de un sistema más amplio y tiene como objetivo gestionar las **categorías de menú** (por ejemplo: Entradas, Platos fuertes, Bebidas). Implementa endpoints REST para crear y consultar categorías, utilizando un enfoque desacoplado y mantenible basado en **arquitectura limpia** y estándares de desarrollo modernos con Spring Boot.

---

## 🧰 Tecnologías Utilizadas

| Tecnología             | Descripción                                                 |
|------------------------|-------------------------------------------------------------|
| **Java 17**            | Lenguaje de programación principal                          |
| **Spring Boot**        | Framework para desarrollo rápido de APIs                    |
| **Spring Web**         | Creación de controladores REST                              |
| **Spring Validation**  | Validación de DTOs con anotaciones                          |
| **OpenAPI 3 / Swagger**| Documentación interactiva y estandarizada de la API         |
| **HATEOAS**            | Inclusión de enlaces en las respuestas                      |
| **Lombok**             | Eliminación de código repetitivo (getters/setters/logs)     |
| **Maven**              | Gestión del proyecto y dependencias                         |

---

## 💡 Buenas prácticas y patrones aplicados

- ✅ Arquitectura por capas y paquetes: `application`, `domain`, `infrastructure`
- ✅ Uso de DTOs, Mappers y Validadores
- ✅ Separación entre casos de uso, entidades de dominio y adaptadores de infraestructura
- ✅ Estilo RESTful con respuestas `EntityModel` (HATEOAS)
- ✅ Validación con `@Validated`, `@Valid`, y `BindingResult`
- ✅ Manejo de errores centralizado con respuesta estructurada
- ✅ Documentación de endpoints con `@Operation` y `@ApiResponse` (Swagger/OpenAPI)

---

## 🗂️ Estructura del Proyecto - Módulo `menu`

```
📁 menu                               # Módulo principal encargado de la gestión de menús
├── 📁 application                   # Lógica de aplicación: casos de uso, servicios y contratos
│   ├── 📁 dto                       # Objetos de transferencia de datos (entrada/salida)
│   ├── 📁 service                   # Interfaces de servicio de aplicación (puertos de salida)
│   ├── 📁 usecase                   # Implementaciones de los casos de uso del negocio
│   ├── 📁 spi                       # Puertos de entrada (interfaces que deben implementar los adaptadores)
│
├── 📁 domain                        # Núcleo del negocio (modelo y lógica pura)
│   ├── 📁 model                     # Entidades del dominio con reglas y comportamiento propio
│   ├── 📁 service                   #  
│   │
├── 📁 infrastructure                # Adaptadores y configuración del mundo externo
│   ├── 📁 adapter                   # Implementaciones de los puertos de infraestructura (SPI)
│   ├── 📁 configuration             # Configuraciones generales (Beans, Swagger, etc.)
│   ├── 📁 mapper                    # Conversores entre entidades, DTOs y modelos de dominio
│   ├── 📁 persistence               # Capa de acceso a datos y repositorios
│   │   ├── 📁 entity                # Entidades JPA que representan tablas de base de datos
│   │   └── 📁 repository            # Interfaces JPA y sus implementaciones personalizadas
│   ├── 📁 web                       # Capa de presentación (HTTP)
│   │   ├── 📁 controller            # Controladores REST expuestos al cliente
│   │   │   └── 📁 advice            # Manejadores globales de errores y validaciones
│   │   ├── 📁 model                 # Modelos propios de la capa web
│   │   │   ├── 📁 request           # Clases para mapear los datos recibidos vía HTTP
│   │   │   └── 📁 response          # Clases para estructurar las respuestas enviadas al cliente
│
├── 📄 pom.xml                       # Archivo de configuración Maven para el módulo
└── 📄 README.md                     # Documentación del módulo menu
```

---

## **📝 Logging**
Se utiliza **SLF4J** para estructurar los logs.

---

### **Acceso al servicio:**  
   
   ```bash
   http://localhost:8083/
   ```
  
---

### **Documentación de la API REST:**  
   Accede a Swagger UI para ver los endpoints disponibles:  
   
   ```bash
   👉 [http://localhost:8081/swagger-ui/index.html](http://localhost:8081/swagger-ui/index.html)
   ```
  

---