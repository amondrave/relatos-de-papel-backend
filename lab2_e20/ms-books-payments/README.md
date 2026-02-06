# ms-books-payments

Microservicio operador de pagos para la plataforma **Relatos de Papel**.

## Responsabilidades
- Registrar compras
- Validar los libros solicitados contra el servicio de catálogo
- Persistir los acuses de compra en su propia base de datos relacional

## Ejecución local
```bash
mvn spring-boot:run
```

Variables de entorno:
- `PORT` (por defecto 8732)
- `EUREKA_URL` (por defecto `http://localhost:8761/eureka`)
- `CATALOGUE_BASE_URL` (por defecto `lb://ms-books-catalogue`)

## API
- `POST /payments` registra una compra tras validar catálogo
- `GET  /payments` lista compras registradas
- `GET  /payments/{id}` obtiene una compra por id

## Consola H2 (solo desarrollo)
- `http://localhost:8732/h2`
- JDBC: `jdbc:h2:mem:paymentsdb`
- usuario: `sa`
