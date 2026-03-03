# Superhero Management System | Sistema de Gestión de Superhéroes

A full-stack application for managing superheros, teams, and powers, featuring a Spring Boot backend with JWT security and a React/Vite frontend.

Una aplicación full-stack para gestionar superhéroes, equipos y poderes, con un backend de Spring Boot con seguridad JWT y un frontend en React/Vite.

---

## 📑 Table of Contents | Índice de Contenido

- [English Version](#english-version)
  - [Features](#features)
  - [Tech Stack](#tech-stack)
  - [Getting Started](#getting-started)
  - [Database Configuration](#database-configuration)
- [Versión en Español](#versión-en-español)
  - [Características](#características)
  - [Stack Tecnológico](#stack-tecnológico)
  - [Primeros Pasos](#primeros-pasos)
  - [Configuración de Base de Datos](#configuración-de-base-de-datos)

---

# English Version

## Features
- **User Authentication**: Secure login and registration using JWT (JSON Web Tokens).
- **Superhero Management**: Create, Read, Update, and Delete superheros.
- **Teams & Powers**: Assign superheros to teams and grant them unique powers.
- **Secret Identity**: Manage hidden identities for each hero.
- **Modern UI**: Responsive dashboard built with React and TanStack Router.

## Tech Stack
- **Backend**: Java 17+, Spring Boot 3.x, Spring Security, JPA/Hibernate.
- **Frontend**: React 19, Vite, TypeScript, Axios, TanStack Query/Router.
- **Database**: H2 (In-memory for testing), Supports MySQL/PostgreSQL.

## Getting Started

### Prerequisites
- **Java 17 or higher**
- **Node.js v24+**

### Running the Backend
1. Clone the repository.
2. Navigate to the root directory.
3. Run the following command (Maven Wrapper is included):
   ```bash
   ./mvnw spring-boot:run
   ```
   *The server will start at `http://localhost:8080`.*

### Running the Frontend
1. Navigate to the `frontend` directory.
2. Install dependencies:
   ```bash
   npm install
   ```
3. Start the development server:
   ```bash
   npm run dev
   ```
   *The UI will be available at `http://localhost:5173`.*

## Database Configuration
The project uses an **H2 In-Memory** database by default. Data is lost when the server stops.

To use a persistent database (e.g., MySQL), update `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/your_db
spring.datasource.username=root
spring.datasource.password=your_password
```

---

# Versión en Español

## Características
- **Autenticación**: Inicio de sesión y registro seguro mediante JWT (JSON Web Tokens).
- **Gestión de Héroes**: CRUD completo de superhéroes.
- **Equipos y Poderes**: Asigna héroes a equipos y otórgales poderes únicos.
- **Identidad Secreta**: Gestiona las identidades ocultas de cada héroe.
- **Interfaz Moderna**: Dashboard responsivo construido con React y TanStack Router.

## Stack Tecnológico
- **Backend**: Java 17+, Spring Boot 3.x, Spring Security, JPA/Hibernate.
- **Frontend**: React 19, Vite, TypeScript, Axios, TanStack Query/Router.
- **Base de Datos**: H2 (En memoria para pruebas), compatible con MySQL/PostgreSQL.

## Primeros Pasos

### Requisitos Previos
- **Java 17 o superior**
- **Node.js v24+**

### Ejecución del Backend
1. Clona el repositorio.
2. Ve al directorio raíz.
3. Ejecuta el siguiente comando (se incluye el Maven Wrapper):
   ```bash
   ./mvnw spring-boot:run
   ```
   *El servidor arrancará en `http://localhost:8080`.*

### Ejecución del Frontend
1. Entra en el directorio `frontend`.
2. Instala las dependencias:
   ```bash
   npm install
   ```
3. Arranca el servidor de desarrollo:
   ```bash
   npm run dev
   ```
   *La interfaz estará disponible en `http://localhost:5173`.*

## Configuración de Base de Datos
El proyecto usa **H2 en memoria** por defecto. Los datos se pierden al reiniciar el servidor.

Para usar una base de datos persistente (ej. MySQL), modifica `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/tu_base_de_datos
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
```
