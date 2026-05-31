# Micro Challenge Sngular - Inditex Prices (`sg-inditex_prices--sb`)

## 📖 Descripción

Microservicio REST desarrollado como solución a la prueba técnica propuesta por Inditex para el equipo de Sngular.

La aplicación permite consultar el precio aplicable de un producto para una marca determinada en una fecha concreta, resolviendo las posibles coincidencias mediante la prioridad configurada en base de datos.

El proyecto ha sido desarrollado utilizando **Java 21**, **Spring Framework 6** y **Spring Boot 3**, aplicando una arquitectura por capas y una clara separación entre modelos de dominio, persistencia y transporte.

---

## 🛠 Tech Stack

### Core

* Java 21 (LTS)
* Spring Boot 3.5.14
* Spring Framework 6

### API REST

* Spring Web
* Spring Validation
* Jackson

### Persistencia

* Spring Data JPA
* Hibernate ORM
* H2 Database

### Productividad

* Lombok 1.18.46
* MapStruct 1.6.3

### Testing

* JUnit 5
* Mockito 5
* Spring Boot Test

### Observabilidad

* Spring Boot Actuator

### Contenerización

- Docker
- Eclipse Temurin JRE 21

---

## 🏗 Arquitectura

La solución sigue una arquitectura clásica en tres capas:

```text
Controller
    ↓
Service
    ↓
Repository
```

Adicionalmente, se ha implementado una separación explícita entre:

```text
DTO ↔ DOMAIN ↔ ENTITY
```

### DTO

Objetos utilizados para la comunicación HTTP.

Ejemplo:

```java
PriceSearchQueryDTO
PriceSearchResponseDTO
```

### Domain

Representación del modelo de negocio independiente de la persistencia.

Ejemplo:

```java
Price
Brand
Money
ApplicationDates
```

### Entity

Objetos JPA utilizados para la persistencia.

Ejemplo:

```java
AuditableEntity
PriceEntity
BrandEntity
```

---

## 🔄 Mapeo de Objetos

La transformación entre capas se realiza mediante MapStruct.

### DTO ↔ Domain

```text
PriceSearchQueryDTO
        ↓
PriceDtoMapper
        ↓
Price
```

### Entity ↔ Domain

```text
PriceEntity
        ↓
PriceEntityMapper
        ↓
Price
```

Este enfoque permite desacoplar la API REST y la base de datos del modelo de negocio.

---

## ⚠ Gestión de Excepciones

La aplicación dispone de un sistema centralizado de gestión de errores basado en:

```java
AppErrorCode
CustomException
GlobalExceptionHandler
```

### Ejemplo de respuesta de error

```json
{
  "id": "915b54a0-11b7-48c2-b1eb-ab42839199a1",
  "timestamp": "2026-05-31T16:49:35.310+02:00",
  "http_status_code": 404,
  "error_code": 40402,
  "error_message": "Price not found",
  "ex_message": "Price not found for brand 2 and product 35455"
}
```

### Códigos de error funcionales

| HTTP | Código | Descripción           |
| ---- |--------|-----------------------|
| 400  | 40000  | Bad Request           |
| 400  | 40001  | Validation Errors     |
| 404  | 40400  | Not Found             |
| 404  | 40401  | Brand Not Found       |
| 404  | 40402  | Price Not Found       |
| 500  | 50000  | Internal Server Error |

---

## 📝 Logging

Se han incorporado mecanismos para facilitar la trazabilidad de las peticiones:

### Interceptor

```java
LoggerInterceptor
```

Responsable del registro de información relacionada con la petición y respuesta HTTP.

### Request Body Advice

```java
RequestBodyLoggerAdvice
```

Permite registrar el contenido de las peticiones entrantes.

### Response Body Advice

```java
ResponseBodyLoggerAdvice
```

Permite registrar el contenido de las peticiones salientes.

### Ejemplo de traza

```
[INFO-REQ] » POST /rest/v1/prices/search
PriceController.search coming into ...
Request DTO: PriceSearchQueryDTO[brandId=1, productId=35455, applicationDate=2020-06-16T21:00]
Hibernate: select be1_0.brand_id,be1_0.created_at,be1_0.brand_name,be1_0.updated_at from ecommerce.brands be1_0 where be1_0.brand_id=?
Hibernate: select pe1_0.price_id,pe1_0.brand_id,pe1_0.created_at,pe1_0.currency,pe1_0.end_date,pe1_0.price,pe1_0.price_list,pe1_0.priority,pe1_0.product_id,pe1_0.start_date,pe1_0.updated_at from ecommerce.prices pe1_0 where pe1_0.brand_id=? and pe1_0.product_id=? and ? between pe1_0.start_date and pe1_0.end_date order by pe1_0.priority desc fetch first 1 rows only
Response DTO: PriceSearchResponseDTO[brandId=1, productId=35455, priceList=4, applicationDates=ApplicationDatesResponseDTO[startDate=2020-06-15T16:00, endDate=2020-12-31T23:59:59], money=MoneyResponseDTO[price=38.95, currency=EUR]]
PriceController.search going out in 896 ms ...
```

---

## 🚀 Ejecución Local

### Prerrequisitos

* JDK 21
* Maven 3.9+

### Compilación

```bash
mvn clean install
```

### Ejecución

```bash
mvn spring-boot:run
```

o

```bash
java -jar target/sg-inditex_prices--sb-1.0.0-SNAPSHOT.jar
```

---

## 🐳 Ejecución con Docker

La aplicación incluye un `Dockerfile` para facilitar su despliegue y ejecución en entornos contenerizados.

### Construcción de la imagen

Una vez generado el artefacto mediante Maven:

```bash
mvn clean package
```

construir la imagen Docker:

```bash
docker build -t sg-inditex_prices--sb .
```

### Ejecución del contenedor

```bash
docker run -p 8081:8081 sg-inditex_prices--sb
```

La aplicación quedará disponible en:

```text
http://localhost:8081/rest/v1
```

### Dockerfile

La imagen está basada en:

```text
eclipse-temurin:21-jre
```

e incorpora las siguientes características:

* Ejecución sobre Java 21.
* Usuario no privilegiado (`spring`).
* Optimización para entornos contenerizados mediante:

    * `-XX:+UseContainerSupport`
    * `-XX:MaxRAMPercentage=75.0`
* Exposición del puerto `8081`.

### Flujo completo

```bash
mvn clean package

docker build -t sg-inditex_prices--sb .

docker run -p 8081:8081 sg-inditex_prices--sb
```

---

## 🧪 Testing

Para ejecutar los tests:

```bash
mvn test
```

El proyecto utiliza:

* JUnit 5
* Mockito
* Spring Boot Test

---

## 🧪 Pruebas con Postman

En la carpeta `/resource/postman`, se incluye una colección de Postman para facilitar las pruebas de integración del endpoint y verificar los casos de uso solicitados en la prueba técnica.

### Ficheros incluidos:
* `CHALL - Sn_Inditex_Prices.postman_collection.json` (Colección con las peticiones y scripts de validación).

### Casos de prueba automatizados en la colección:
La colección incluye scripts de pruebas (`Tests` en Postman) que validan automáticamente el código de estado HTTP, la estructura del JSON y los datos esperados para diversos escenarios requeridos:
1.  **Test 01:** Petición a las 10:00 del día 14 para el producto 35455 y marca 1 (ZARA).
2.  **Test 02:** Petición a las 16:00 del día 14 para el producto 35455 y marca 1 (ZARA).
3.  **Test 03:** Petición a las 21:00 del día 14 para el producto 35455 y marca 1 (ZARA).
4.  **Test 04:** Petición a las 10:00 del día 15 para el producto 35455 y marca 1 (ZARA).
5.  **Test 05:** Petición a las 21:00 del día 16 para el producto 35455 y marca 1 (ZARA).
6.  **Test 06:** Petición con un brand_id como null.
7.  **Test 07:** Petición con un brand_id menor que 1.
8.  **Test 08:** Petición con un product_id como null.
9.  **Test 09:** Petición con un product_id menor que 1.
10. **Test 10:** Petición con un date como null.
11. **Test 11:** Petición con un brand que no existe.
12. **Test 12:** Petición con un product que no existe.

### Cómo ejecutar las pruebas en Postman:
1.  Abre Postman e importa el fichero (`Import` -> selecciona los archivos `.json`).
2. Asegúrate de que la aplicación Spring Boot esté corriendo localmente en el puerto `8081`.
3. Ejecutar las peticiones una a una.

---

## 🗄 Base de Datos

La aplicación utiliza una base de datos embebida H2 para simplificar la ejecución de la prueba técnica.

La carga inicial de datos se realiza mediante scripts SQL incluidos en el proyecto como son schema.sql y data.sql.

---

## 📂 Estructura del Proyecto

```text
com.ssdjr2.chall.sg.inditex_prices

├── config
│   ├── advice
│   ├── interceptors
│   └── properties
│
├── controller
│   ├── dto
│   └── mapper
│
├── domain
│   ├── exception
│   └── model
│
├── exception
│   ├── custom
│   └── handler
│
├── persistence
│   ├── entity
│   ├── mapper
│   └── repository
│
├── service
│   └── impl
│
└── util
```

---

## 📋 Endpoint Principal

### Búsqueda de precio aplicable

```http
POST /prices/search
```

### Request

```json
{
  "brand_id": 1,
  "product_id": 35455,
  "application_date": "2020-06-14T10:00:00"
}
```

### Response

```json
{
  "brand_id": 1,
  "product_id": 35455,
  "price_list": 1,
  "application_dates": {
    "start_date": "2020-06-14T00:00:00",
    "end_date": "2020-12-31T23:59:59"
  },
  "money": {
    "price": 35.5,
    "currency": "EUR"
  }
}
```

---

## 📖 Documentación de la API (OpenAPI / Swagger)

La aplicación expone la especificación de la API utilizando **OpenAPI 3** a través de un fichero `openapi.yml` ubicado en `/resource/openapi`.

Desde la interfaz de Swagger UI es posible probar directamente el endpoint de búsqueda de precios sin necesidad de herramientas externas.

---

## 📄 Licencia

Proyecto desarrollado exclusivamente con fines formativos y de evaluación técnica para la prueba propuesta por Inditex y Sngular.
