# Aplicación de gestión de citas de peluquería (Arquitectura monolítica)
# Backend

## TO DO
* Login (Spring Security + JWT)
* Tests de controlador: Pruebas GET, POST, PUT, DELETE
* Documentación (Swagger)
* Entidad con asociación ManyToMany (PaymentMethod)
* Exportar colección de Postman
* Despliegue en Render

## Entidades (con operaciones CRUD):
* Appointment : representa una cita
* Customer: representa un cliente
* Treatment: representa un servicio
* Employee: representa un empleado

## Capas:
* Entidad (Modelo, tabla en una BD)
* Repositorio (Permite interactuar con la BD)
* Servicio (Definir e implementar la lógica de negocio: crear citas y asignarlas a empleados y clientes)
* Controlador
* DTO (Devolver cosas que no son la propia entidad. Clases normales para envolver a los datos que se transfieren entre el backend y frontend y viceversa)

## Dependencias:
* PostgreSQL Driver
* Spring Data JPA
* Spring Web
* Spring Boot DevTools

## Proceso:
1. Crear application.properties
2. Crear BD en PgAdmin
3. Crear Entidades: atributos, asociaciones, constructores, métodos (getters y setters, toString, +)
4. Crear Repositorios
    * Spring Data JPA: crear nuevos métodos https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories
5. Crear Servicios para todas las Entidades con métodos CRUD (Create, Retrieve/Read, Update, Delete)
6. Crear Controladores para todas las Entidades con métodos CRUD
7. Pruebas con Postman
8. Crear métodos de búsqueda en AppointmentService (por email de cliente, por teléfono de cliente, por RFC de empleado, por rango de precio del servicio, por servicio)
9. Spring Test: Pruebas GET, POST, PUT, DELETE por entidad

## Servicios peluquería
https://www.ulta.com/beautyservices/hair-salon

## Generar datos para llenar BD
https://www.mockaroo.com/

## Frameworks de control de versiones sobre la BD
Liquibase https://www.liquibase.org/

Flyway https://flywaydb.org/