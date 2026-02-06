# ms-books-catalogue

Microservicio de catálogo de libros para la aplicación Relatos de Papel.

## Descripción

Este microservicio gestiona el CRUD de libros, incluyendo operaciones de búsqueda con filtros y paginación. Utiliza una base de datos H2 en memoria y se registra en Eureka para service discovery.

## Tecnologías

- Java 25
- Spring Boot 4.0.1
- Spring Data JPA
- Spring Cloud Netflix Eureka Client
- H2 Database
- Lombok
- OpenAPI/Swagger

## Configuración

El microservicio corre en el puerto `8088` por defecto.

## API Endpoints

### Obtener libros (paginado)

```
GET /books
```

**Parámetros de consulta:**

| Parámetro | Tipo | Descripción | Ejemplo |
|-----------|------|-------------|---------|
| title | String | Título del libro (búsqueda parcial) | "Cien años" |
| author | String | Autor del libro (búsqueda parcial) | "García Márquez" |
| publicationDate | LocalDate | Fecha de publicación | 2020-01-15 |
| category | String | Categoría del libro | "Ficción" |
| codIsbn | String | Código ISBN | "978-3-16-148410-0" |
| rate | Double | Calificación (0-5) | 4.5 |
| visible | Boolean | Estado de visibilidad | true |
| page | Integer | Número de página (0-indexed) | 0 |
| size | Integer | Tamaño de página | 10 |
| sort | String | Campo de ordenamiento | "id" |

**Respuesta exitosa (200):**
```json
{
  "content": [
    {
      "id": 1,
      "title": "Cien años de soledad",
      "author": "Gabriel García Márquez",
      "publicationDate": "1967-05-30",
      "category": "Ficción",
      "codIsbn": "978-84-376-0494-7",
      "rate": 5.0,
      "visible": true
    }
  ],
  "pageable": {...},
  "totalElements": 1,
  "totalPages": 1,
  "size": 10,
  "number": 0
}
```

### Obtener libro por ID

```
GET /books/{bookId}
```

**Respuesta exitosa (200):**
```json
{
  "id": 1,
  "title": "Cien años de soledad",
  "author": "Gabriel García Márquez",
  "publicationDate": "1967-05-30",
  "category": "Ficción",
  "codIsbn": "978-84-376-0494-7",
  "rate": 5.0,
  "visible": true
}
```

**Respuesta error (404):**
```json
{
  "status": 404,
  "message": "Libro no encontrado",
  "timestamp": "2024-01-15T10:30:00",
  "errors": []
}
```

### Crear libro

```
POST /books
```

**Request body:**
```json
{
  "title": "Cien años de soledad",
  "author": "Gabriel García Márquez",
  "publicationDate": "1967-05-30",
  "category": "Ficción",
  "codIsbn": "978-84-376-0494-7",
  "rate": 5.0,
  "visible": true
}
```

**Validaciones:**
- `title`: Obligatorio, no puede estar vacío
- `author`: Obligatorio, no puede estar vacío
- `publicationDate`: Obligatorio
- `category`: Obligatorio, no puede estar vacío
- `codIsbn`: Obligatorio, no puede estar vacío
- `rate`: Obligatorio, debe estar entre 0 y 5
- `visible`: Obligatorio

**Respuesta exitosa (201):**
```json
{
  "id": 1,
  "title": "Cien años de soledad",
  ...
}
```

**Respuesta error de validación (400):**
```json
{
  "status": 400,
  "message": "Error de validación",
  "timestamp": "2024-01-15T10:30:00",
  "errors": [
    "title: El título es obligatorio",
    "rate: La calificación mínima es 0"
  ]
}
```

### Actualizar libro completamente

```
PUT /books/{bookId}
```

**Request body:**
```json
{
  "title": "Nuevo título",
  "author": "Nuevo autor",
  "publicationDate": "2020-01-01",
  "category": "Nueva categoría",
  "codIsbn": "978-0-00-000000-0",
  "rate": 4.0,
  "visible": true
}
```

**Respuesta exitosa (200):** Libro actualizado

**Respuesta error (404):** Libro no encontrado

### Actualizar libro parcialmente (PATCH)

```
PATCH /books/{bookId}
Content-Type: application/merge-patch+json
```

**Request body (RFC 7386 - JSON Merge Patch):**
```json
{
  "title": "Título actualizado",
  "rate": 4.5
}
```

**Respuesta exitosa (200):** Libro actualizado

**Respuesta error (400):** Datos inválidos

### Eliminar libro

```
DELETE /books/{bookId}
```

**Respuesta exitosa (200):** Sin contenido

**Respuesta error (404):** Libro no encontrado

## Códigos de respuesta

| Código | Descripción |
|--------|-------------|
| 200 | Operación exitosa |
| 201 | Recurso creado exitosamente |
| 400 | Error de validación o datos incorrectos |
| 404 | Recurso no encontrado |
| 500 | Error interno del servidor |

## Documentación Swagger

Acceder a la documentación interactiva en:
```
http://localhost:8088/swagger-ui.html
```

## Ejecución

1. Iniciar el servidor Eureka
2. Ejecutar el microservicio:
```bash
mvn spring-boot:run
```

## Pruebas

```bash
mvn test
```
