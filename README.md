# 👟 Shoes Shop - Proyecto Microservicios

## 📌 Descripción del proyecto
Shoes Shop es un sistema backend desarrollado con Spring Boot que simula una tienda de zapatillas. El sistema está basado en una arquitectura de microservicios, permitiendo la gestión de productos, clientes, boletas, marcas, colores, tallas y otros elementos del dominio.

El proyecto utiliza persistencia real con JPA/Hibernate y base de datos MySQL.

---

## ⚙️ Tecnologías utilizadas
- Java 21
- Spring Boot
- Spring Data JPA
- Hibernate
- MySQL
- Maven
- Git / GitHub

---

## 🧱 Arquitectura del proyecto
El proyecto sigue el patrón CSR:

- **Controller** → Manejo de endpoints REST
- **Service** → Lógica de negocio
- **Repository** → Acceso a datos

---

## 🗄️ Base de datos
La base de datos utilizada es MySQL.

Tablas principales:
- clientes
- boletas
- zapatillas
- marcas
- colores
- tallas
- materiales
- tipos
- sexos
- regiones y comunas

---

## 🔗 Funcionalidades principales

✔ CRUD completo de entidades  
✔ Relaciones entre tablas (OneToMany, ManyToOne)  
✔ Gestión de boletas y productos  
✔ Validaciones básicas en entidades  
✔ Respuestas REST en formato JSON  

---

## 🚀 Cómo ejecutar el proyecto

1. Clonar el repositorio:
```bash
git clone https://github.com/TU_USUARIO/shoes_shop.git
GET    /api/v1/clientes
POST   /api/v1/clientes
PUT    /api/v1/clientes/{id}
DELETE /api/v1/clientes/{id}

GET    /api/v1/zapatillas
POST   /api/v1/zapatillas
 Configuración del proyecto
spring.datasource.url=jdbc:mysql://localhost:3306/db_shoes_shop
spring.datasource.username=root
spring.datasource.password=tu_password

spring.jpa.hibernate.ddl-auto=update
