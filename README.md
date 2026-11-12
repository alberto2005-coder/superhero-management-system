# Superhero Management System

[![Java](https://img.shields.io/badge/Java-17%2B-ED8B00?logo=openjdk&logoColor=white)](https://openjdk.org/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-6DB33F?logo=springboot&logoColor=white)](https://spring.io/projects/spring-boot)
[![React](https://img.shields.io/badge/React-19-61DAFB?logo=react&logoColor=black)](https://react.dev/)
[![MIT](https://img.shields.io/badge/Licencia-MIT-green)](LICENSE)

[Español](#español) · [English](#english)

---

## Español

### Índice

- [Descripción](#descripción)
- [Características](#características)
- [Stack tecnológico](#stack-tecnológico)
- [Estructura del proyecto](#estructura-del-proyecto)
- [Requisitos](#requisitos)
- [Puesta en marcha](#puesta-en-marcha)
- [Base de datos](#base-de-datos)
- [Licencia](#licencia)

### Descripción

Aplicación **full-stack** para gestionar superhéroes, equipos y poderes. El backend es una API REST en **Spring Boot** protegida con **JWT**; el frontend es una SPA en **React + TypeScript** con **Vite** y **TanStack Router/Query**.

### Características

- **Autenticación con JWT**: registro e inicio de sesión con tokens de acceso.
- **CRUD de superhéroes** con identidad secreta y descripción.
- **Equipos y poderes**: cada héroe puede pertenecer a un equipo y tener varios poderes.
- **Dashboard responsivo** con rutas dedicadas para listar, crear y editar.
- **Validación en servidor** con Bean Validation y respuestas de error consistentes.
- **H2 en memoria** por defecto, lista para cambiar a MySQL o PostgreSQL.

### Stack tecnológico

| Capa | Tecnología |
|---|---|
| Backend | Java 17+, Spring Boot 3.x, Spring Security, Spring Data JPA / Hibernate |
| Frontend | React 19, TypeScript, Vite, Axios, TanStack Query y TanStack Router |
| Base de datos | H2 en memoria (por defecto); MySQL o PostgreSQL en producción |
| Build | Maven Wrapper (`./mvnw`) y npm |

### Estructura del proyecto

```text
superhero-management-system/
├── src/main/java/…            # Controladores, servicios, repositorios y seguridad JWT
├── src/main/resources/
│   └── application.properties # Configuración de la base de datos
├── application.properties.example
├── frontend/
│   ├── src/api/               # Cliente Axios y llamadas a la API
│   ├── src/components/        # Formularios y listados de héroes y equipos
│   ├── src/routes/            # Rutas de TanStack Router
│   └── src/routeTree.ts
├── mvnw · mvnw.cmd
├── LICENSE
└── README.md
```

### Requisitos

- **Java 17 o superior**.
- **Node.js 20.19+ o 22.12+** y npm (lo exige Vite 7).
- Maven no hace falta: el wrapper viene incluido.

### Puesta en marcha

**1. Backend**

```bash
./mvnw spring-boot:run        # Windows: mvnw.cmd spring-boot:run
```

Arranca en `http://localhost:8080`.

**2. Frontend**

```bash
cd frontend
npm install
npm run dev
```

La interfaz queda en `http://localhost:5173` y consume la API del puerto 8080.

> [!TIP]
> Si el frontend no encuentra la API, revisa la URL base en `frontend/src/api/axiosInstance.ts` y el puerto del backend.

### Base de datos

Por defecto se usa **H2 en memoria**: los datos desaparecen al reiniciar. Para trabajar con una base persistente, copia el ejemplo y ajusta los valores:

```bash
cp application.properties.example src/main/resources/application.properties
```

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/superheroes
spring.datasource.username=tu_usuario
spring.datasource.password=tu_contraseña
```

### Licencia

**MIT** — consulta [LICENSE](LICENSE).

---

## English

### Table of contents

- [Overview](#overview)
- [Features](#features)
- [Tech stack](#tech-stack)
- [Project structure](#project-structure)
- [Requirements](#requirements)
- [Getting started](#getting-started)
- [Database](#database)
- [License](#license)

### Overview

A **full-stack** application to manage superheroes, teams and powers. The backend is a **Spring Boot** REST API secured with **JWT**; the frontend is a **React + TypeScript** SPA built with **Vite** and **TanStack Router/Query**.

### Features

- **JWT authentication**: registration and login with access tokens.
- **Superhero CRUD** including secret identity and description.
- **Teams and powers**: each hero can belong to a team and hold several powers.
- **Responsive dashboard** with dedicated routes to list, create and edit.
- **Server-side validation** through Bean Validation with consistent error payloads.
- **In-memory H2** by default, ready to switch to MySQL or PostgreSQL.

### Tech stack

| Layer | Technology |
|---|---|
| Backend | Java 17+, Spring Boot 3.x, Spring Security, Spring Data JPA / Hibernate |
| Frontend | React 19, TypeScript, Vite, Axios, TanStack Query and TanStack Router |
| Database | In-memory H2 (default); MySQL or PostgreSQL in production |
| Build | Maven Wrapper (`./mvnw`) and npm |

### Project structure

```text
superhero-management-system/
├── src/main/java/…            # Controllers, services, repositories and JWT security
├── src/main/resources/
│   └── application.properties # Database configuration
├── application.properties.example
├── frontend/
│   ├── src/api/               # Axios client and API calls
│   ├── src/components/        # Hero and team forms and lists
│   ├── src/routes/            # TanStack Router routes
│   └── src/routeTree.ts
├── mvnw · mvnw.cmd
├── LICENSE
└── README.md
```

### Requirements

- **Java 17 or later**.
- **Node.js 20.19+ or 22.12+** and npm (required by Vite 7).
- Maven is not required: the wrapper ships with the repo.

### Getting started

**1. Backend**

```bash
./mvnw spring-boot:run        # Windows: mvnw.cmd spring-boot:run
```

It listens on `http://localhost:8080`.

**2. Frontend**

```bash
cd frontend
npm install
npm run dev
```

The UI runs at `http://localhost:5173` and consumes the API on port 8080.

> [!TIP]
> If the frontend cannot reach the API, check the base URL in `frontend/src/api/axiosInstance.ts` and the backend port.

### Database

**In-memory H2** is used by default: data is lost on restart. For a persistent database, copy the example and adjust the values:

```bash
cp application.properties.example src/main/resources/application.properties
```

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/superheroes
spring.datasource.username=your_user
spring.datasource.password=your_password
```

### License

**MIT** — see [LICENSE](LICENSE).
