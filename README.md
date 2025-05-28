# 🍽️ Restaurante - Sistema de Gestión basado en Microservicios y Event Sourcing

Este sistema gestiona el menú, órdenes, facturación, inventario y promociones del restaurante. Está diseñado con arquitectura de microservicios, con soporte para **Event Sourcing** y el patrón de **Saga**.

---

## 🧱 Esquema de Base de Datos

# 🔐 Spring Security con Roles y Permisos

Este proyecto define una estructura base de tablas para implementar **autenticación y autorización** usando **Spring Security**, **JWT**, **roles** y **permisos**.

---

## 📐 Estructura de Tablas

```plaintext
users
roles
permissions
user_roles
role_permissions
```

---

## 📄 Tabla: `users`

| Campo        | Tipo           | Restricciones           |
|--------------|----------------|-------------------------|
| `id`         | UUID / BIGINT  | PK                      |
| `username`   | VARCHAR(100)   | NOT NULL, UNIQUE        |
| `email`      | VARCHAR(150)   | NOT NULL, UNIQUE        |
| `password`   | VARCHAR(255)   | NOT NULL                |
| `enabled`    | BOOLEAN        | DEFAULT true            |
| `created_at` | TIMESTAMP      | DEFAULT CURRENT_TIMESTAMP |

---

## 📄 Tabla: `roles`

| Campo     | Tipo          | Restricciones     |
|-----------|---------------|-------------------|
| `id`      | UUID / BIGINT | PK                |
| `name`    | VARCHAR(50)   | NOT NULL, UNIQUE  |

---

## 📄 Tabla: `permissions`

| Campo     | Tipo           | Restricciones     |
|-----------|----------------|-------------------|
| `id`      | UUID / BIGINT  | PK                |
| `name`    | VARCHAR(100)   | NOT NULL, UNIQUE  |

---

## 📄 Tabla: `user_roles` (Intermedia)

| Campo      | Tipo          | Restricciones                          |
|------------|---------------|----------------------------------------|
| `user_id`  | UUID / BIGINT | PK, FK a `users(id)`                   |
| `role_id`  | UUID / BIGINT | PK, FK a `roles(id)`                   |

---

## 📄 Tabla: `role_permissions` (Intermedia)

| Campo           | Tipo          | Restricciones                          |
|-----------------|---------------|----------------------------------------|
| `role_id`       | UUID / BIGINT | PK, FK a `roles(id)`                   |
| `permission_id` | UUID / BIGINT | PK, FK a `permissions(id)`             |

---

## 🔗 Relaciones

- `users` ↔ `roles` → muchos a muchos (`user_roles`)
- `roles` ↔ `permissions` → muchos a muchos (`role_permissions`)

---

## 🧪 Ejemplo de Roles y Permisos

| Rol           | Permisos                                |
|---------------|-----------------------------------------|
| `ROLE_ADMIN`  | `CREATE_USERS`, `READ_USERS`, `DELETE_USERS` |
| `ROLE_USER`   | `READ_SELF_PROFILE`                     |

---

## 🧰 Uso recomendado en Spring Security

- `@PreAuthorize("hasAuthority('READ_USERS')")`
- `@PreAuthorize("hasRole('ADMIN')")`
- JWT para token stateless

---

### 🍽️ Menú

#### `menu_category`
| Campo       | Tipo     | Descripción              |
|-------------|----------|--------------------------|
| id          | UUID     | Clave primaria           |
| name        | VARCHAR  | Nombre de la categoría   |
| description | VARCHAR  | Descripción              |

#### `menu_item`
| Campo       | Tipo     | Descripción                      |
|-------------|----------|----------------------------------|
| id          | UUID     | Clave primaria                   |
| name        | VARCHAR  | Nombre del ítem                  |
| description | VARCHAR  | Descripción                      |
| price       | DECIMAL  | Precio del ítem                  |
| available   | BOOLEAN  | Si está disponible               |
| category_id | UUID     | FK → `menu_category.id`          |

---

### 📦 Inventario

#### `inventory_item`
| Campo            | Tipo     | Descripción                        |
|------------------|----------|------------------------------------|
| id               | UUID     | Clave primaria                     |
| name             | VARCHAR  | Nombre del insumo                  |
| unit             | VARCHAR  | Unidad (gramos, litros, etc.)      |
| quantity_available | NUMERIC | Cantidad disponible               |
| min_threshold    | NUMERIC  | Límite mínimo                      |
| last_updated     | TIMESTAMP| Última actualización               |

#### `menu_item_ingredient`
| Campo             | Tipo     | Descripción                    |
|-------------------|----------|--------------------------------|
| id                | UUID     | Clave primaria                 |
| menu_item_id      | UUID     | FK → `menu_item.id`            |
| inventory_item_id | UUID     | FK → `inventory_item.id`       |
| quantity_required | NUMERIC  | Cantidad por ítem de menú      |

---

### 🎁 Promociones

#### `promotion`
| Campo         | Tipo     | Descripción                       |
|---------------|----------|-----------------------------------|
| id            | UUID     | Clave primaria                    |
| name          | VARCHAR  | Nombre de la promoción            |
| description   | TEXT     | Descripción                       |
| discount_type | VARCHAR  | `PERCENTAGE` o `FIXED`            |
| discount_value| DECIMAL  | Valor del descuento               |
| start_date    | DATE     | Fecha de inicio                   |
| end_date      | DATE     | Fecha de fin                      |
| active        | BOOLEAN  | Activa o no                       |

#### `promotion_menu_item`
| Campo         | Tipo     | Descripción                     |
|---------------|----------|---------------------------------|
| id            | UUID     | Clave primaria                  |
| promotion_id  | UUID     | FK → `promotion.id`             |
| menu_item_id  | UUID     | FK → `menu_item.id`             |

---

### 👥 Usuarios (opcional)

#### `user`
| Campo         | Tipo     | Descripción           |
|---------------|----------|-----------------------|
| id            | UUID     | Clave primaria        |
| username      | VARCHAR  | Nombre de usuario     |
| password_hash | VARCHAR  | Contraseña cifrada    |
| full_name     | VARCHAR  | Nombre completo       |
| role          | VARCHAR  | Rol (ADMIN, WAITER...)|

#### `audit_log`
| Campo     | Tipo      | Descripción                         |
|-----------|-----------|-------------------------------------|
| id        | UUID      | Clave primaria                      |
| user_id   | UUID      | FK → `user.id`                      |
| action    | TEXT      | Acción realizada                    |
| timestamp | TIMESTAMP | Fecha y hora de la acción           |

---

## 🧾 Event Sourcing y Sagas

### `event_order_store` (reemplaza `customer_order`)
| Campo      | Tipo      | Descripción                         |
|------------|-----------|-------------------------------------|
| id         | UUID      | Clave primaria                      |
| order_id   | UUID      | Identificador lógico de orden       |
| event_type | VARCHAR   | Tipo de evento (`OrderPlaced`, etc.)|
| payload    | JSONB     | Datos del evento                    |
| created_at | TIMESTAMP | Fecha de creación del evento        |
| status     | VARCHAR   | Estado del evento (`PENDING`, etc.) |
| saga_id    | UUID      | Relación con la saga (opcional)     |

---

### `event_invoice_store` (reemplaza `invoice`)
| Campo       | Tipo      | Descripción                         |
|-------------|-----------|-------------------------------------|
| id          | UUID      | Clave primaria                      |
| invoice_id  | UUID      | Identificador lógico de factura     |
| event_type  | VARCHAR   | Tipo de evento (`InvoiceCreated`, etc.)|
| payload     | JSONB     | Datos del evento                    |
| created_at  | TIMESTAMP | Fecha del evento                    |
| status      | VARCHAR   | Estado del evento                   |
| saga_id     | UUID      | Relación con la saga (opcional)     |

---

### `saga_log`
| Campo        | Tipo      | Descripción                            |
|--------------|-----------|----------------------------------------|
| id           | UUID      | Clave primaria                         |
| saga_type    | VARCHAR   | Nombre de la saga (`CreateOrderSaga`) |
| saga_id      | UUID      | Identificador único                    |
| current_step | VARCHAR   | Paso actual de la saga                 |
| status       | VARCHAR   | `STARTED`, `IN_PROGRESS`, `COMPLETED` |
| created_at   | TIMESTAMP | Fecha de inicio                        |
| updated_at   | TIMESTAMP | Última actualización                   |


---

## ✅ Ventajas de este enfoque

- ✅ **Auditoría total**: trazabilidad de cada cambio.
- 🔁 **Retriable**: puedes reprocesar eventos fallidos.
- 📊 **Escalable**: cada servicio trabaja de forma desacoplada.
- 🔄 **Eventual Consistency**: con posibilidad de reconstrucción de estado.

---

## 🛠️ Próximos pasos

- Integrar con Spring Boot + Kafka para eventos.
- Configurar Sagas orquestadas o coreografiadas.
