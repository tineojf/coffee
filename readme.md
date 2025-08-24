# Cavosh Coffee - Backend ☕

Este es el backend del sistema **Cavosh Coffee**, desarrollado con **Spring Boot**, **Java 17** y **H2 Database** para
entorno de desarrollo.

## 🚀 Requisitos Previos

- [Java 17](https://adoptium.net/)
- [Maven 3.8+](https://maven.apache.org/)
- IDE recomendado: [IntelliJ IDEA](https://www.jetbrains.com/idea/)

## ⚙️ Configuración del Proyecto

1. Clona el repositorio:
   ```bash
   git clone
   cd cavosh-coffee-backend
   ```
2. Abre con IntelliJ IDEA
3. Construye el proyecto:
   ```bash
   mvn clean install
   ```
4. Corre la aplicación:
   ```bash
   mvn spring-boot:run
   ```

## 🗄️ Base de Datos

- En desarrollo se usa H2 Database en memoria.
- La consola de H2 estará disponible en:

    ```bash
    http://localhost:8080/h2-console
    ```

- Configuración por defecto:

    ```bash
    Driver Class: org.h2.Driver
    JDBC URL: jdbc:h2:mem:coffee
    User: sa
    Password: (vacio)
    ```

⚠️ Los datos se pierden al reiniciar la aplicación, ya que la DB es en memoria.

## 📡 Endpoints

- El proyecto incluye una colección de Postman exportada en la carpeta /postman.
- Puedes importarla en tu Postman y probar todos los endpoints de la API fácilmente.

## 📦 Estructura del Proyecto

    /src
        /main
            /java
                com.tuorg.cavoshcoffee # Código fuente principal
            /resources
                application.properties # Configuración
    /postman # Colección de Postman para probar la API

🔧 Tecnologías Usadas:

- Spring Boot 3.5.5
- Spring Web
- Spring Security
- Spring Data JPA
- H2 Database
- Maven
