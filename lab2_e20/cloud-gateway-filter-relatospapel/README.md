# API Gateway – Spring Cloud Gateway con Custom Filters

Este proyecto implementa un **API Gateway** basado en **Spring Cloud Gateway**, diseñado para enrutar peticiones hacia microservicios backend mediante **filtros personalizados**.  
Los filtros permiten definir dinámicamente el método HTTP real (`GET`, `POST`, `PUT`, `PATCH`, `DELETE`) a ejecutar a través del cuerpo de la petición.

El Gateway actúa como **punto único de entrada** para los microservicios, centralizando el enrutamiento y la transformación de solicitudes.

---

## Arquitectura general

- Spring Cloud Gateway
- Filtros personalizados (Custom Gateway Filters)
- Comunicación HTTP directa con microservicios
- Enrutamiento basado en path (`/ms-books-catalogue/**`)

---

## Requisitos de despliegue

### Software requerido

- Java 25 o superior  
- Maven 3.9+  
- Spring Boot 4.x  
- Spring Cloud 2025.x  

### Puertos utilizados

| Servicio           | Puerto |
|--------------------|--------|
| API Gateway        | 8762   |
| ms-books-catalogue | Definido en el microservicio |

---

## Ejecución del proyecto

### Compilar el proyecto

```bash
mvn clean package
```

### Ejecutar el Gateway
```bash
mvn spring-boot:run
```

## Convención de peticiones

El API Gateway utiliza una convención basada en el campo `targetMethod` para determinar el método HTTP real que será ejecutado contra el microservicio destino.

Todas las peticiones hacia el Gateway se realizan mediante **HTTP POST**, y el método original (`GET`, `POST`, `PUT`, `PATCH`, `DELETE`) se define dinámicamente en el cuerpo de la solicitud.

### Estructura mínima del body

```json
{
  "targetMethod": "GET"
}
```
```json
{
  "targetMethod": "DELETE"
}
```

### Estructura con cuerpo de datos

Cuando el método HTTP requiere un cuerpo (POST, PUT, PATCH), este se envía dentro del campo body:

```json
{
  "targetMethod": "POST",
  "body": {
    "title": "El señor de los anillos: La comunidad del anillo",
    "author": "J.R.R. Tolkien",
    "publicationDate": "1954-07-29",
    "category": "Fantasía",
    "codIsbn": "9788445071406",
    "rate": 5,
    "visible": true
  }
}
```
```json
{
  "targetMethod": "PUT",
  "body": {
    "title": "Cien años de soledad Ed6",
    "author": "Gabriel García Márquez",
    "publicationDate": "1994-01-30",
    "category": "Novela",
    "codIsbn": "9788439726875",
    "rate": 5,
    "visible": true
  }
}

```
