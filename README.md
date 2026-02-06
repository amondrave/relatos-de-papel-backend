> ⚠️ Todos los microservicios se encuentran dentro de la carpeta **lab2_e20**.  
> Esta carpeta es el workspace principal del proyecto.

---

## ⚙️ Requisitos

Antes de ejecutar el proyecto, asegúrate de tener instalado:

- **Java JDK 25**
- **Apache Maven 3.x**
- **IntelliJ IDEA Ultimate** (recomendado)

Verificación rápida:

```bash
java -version
javac -version
mvn -v
```
## 🧩 Importar Microservicios como módulos Maven
Para que cada microservicio pueda ejecutarse como servicio Spring Boot:

Para cada carpeta dentro de lab2_e20:

1. File → New → Module from Existing Sources…

2. Selecciona la carpeta del servicio

3. Importa como Maven

4. Finaliza

Repite el proceso para todos los microservicios.

Compilar desde consola (opcional)
mvn clean package

## ⚠️ Notas importantes

No abrir microservicios individuales como proyectos separados.

No mover ni eliminar la carpeta lab2_e20.

Cada microservicio es independiente, pero todos pertenecen al mismo repositorio Git.

## ▶️ Orden recomendado de arranque

1 Eureka

2 Gateway / Gateway Filters

3 BOOKS-CATALOGUE / BOOKS-PAYMENTS

Peticiones Postman:
https://orange-meadow-504725.postman.co/workspace/holamundo~e91d74f8-f3b2-47d7-b325-8ac44c6cbc96/request/17321329-1d7022bb-2db6-4e94-bc50-7f6848acda9a?action=share&creator=17321329&ctx=documentation


