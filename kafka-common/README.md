# 🟦 kafka-commun

Microservicio centralizado para la gestión de configuración y eventos Kafka, desarrollado con **Spring Boot** y **Spring Kafka**.

## 📌 Descripción

`kafka-common` es un microservicio que centraliza:
- La **configuración común** para productores y consumidores Kafka.
- La **gestión de topics** (creación y consulta).
- Un **modelo base de eventos** para estandarizar los datos transmitidos por Kafka.

Este microservicio está diseñado para ser reutilizable por otros microservicios que necesiten publicar o consumir eventos, siguiendo un modelo desacoplado y mantenible.

---

## ⚙️ Tecnologías

- Java 17+
- Spring Boot
- Spring Kafka
- Apache Kafka
- Kafka Admin Client
- Lombok
- Maven

---

## 📂 Estructura del Proyecto

```
📁 kafka-common                              
│   ├── 📁 config      # Configuración común de Kafka para productores y consumidores
│   ├── 📁 controller  # Controladores REST para exponer servicios de gestión de topics u operaciones Kafka
│   ├── 📁 entity      # Clases que representan los modelos de dominio o estructuras de eventos
│   ├── 📁 properties  # Clases para mapear propiedades personalizadas desde application.yml/properties
│   ├── 📁 saga        # Clases relacionadas con el manejo de eventos distribuidos y patrón Saga
│   ├── 📁 service     # Servicios con lógica de negocio como creación de topics o publicación de eventos
│   ├── 📁 utils       # Utilidades y funciones de ayuda reutilizables (conversores, formateadores, etc.)
├── 📄 pom.xml         # Archivo de configuración Maven con dependencias y configuración del proyecto
└── 📄 README.md       # Documento con descripción, instrucciones y detalles técnicos del proyecto
                  
```

## 🔄 Funcionalidades

- ✅ Configuración centralizada de Kafka (Producer y Consumer)
- ✅ Servicio para crear topics automáticamente
- ✅ Servicio para consultar topics existentes
- ✅ Clase abstracta `BaseEntityData` para estructurar todos los eventos enviados a Kafka
- ✅ Uso de Kafka Admin Client para gestión de topics


## Uso desde otros microservicios
```
<dependency>
  <groupId>com.kafka-common</groupId>
  <artifactId>kafka-common</artifactId>
  <version>0.0.1-SNAPSHOT</version>
</dependency>
```
