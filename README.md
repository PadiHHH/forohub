# ForoHub - API REST de Foro de Discusión

<img src="https://img.shields.io/badge/STATUS-EN%20DESARROLLO-green">

## Descripción del Proyecto
ForoHub es una API REST desarrollada como parte del Challenge Backend de Alura LATAM y Oracle ONE. La aplicación permite la gestión de un foro de discusión con sistema de autenticación, donde los usuarios pueden crear, leer, actualizar y eliminar tópicos de discusión.

## Tecnologías Utilizadas
- Java 17
- Spring Boot
- Spring Security
- JSON Web Tokens (JWT)
- MySQL
- Maven
- Lombok
- Flyway Migration
- Spring Data JPA
- Bean Validation

## Características
- Autenticación y autorización mediante JWT
- Registro de usuarios
- CRUD completo de tópicos
- Paginación de resultados
- Filtrado de tópicos por curso
- Validación de datos
- Soft delete de registros
- Manejo de errores personalizado

## Prerrequisitos
- Java JDK 17 o superior
- Maven
- MySQL
- Un cliente de API REST (como Postman o Insomnia)

## Configuración
1. Clonar el repositorio:
```bash
git clone https://github.com/tu-usuario/forohub.git
```

2. Configurar variables de entorno:
```properties
DB_URL=jdbc:mysql://localhost:3306/forohub
DB_USER=tu_usuario
DB_PASSWORD=tu_contraseña
JWT_SECRET=tu_secreto_jwt
```

3. Instalar dependencias:
```bash
mvn clean install
```

## Estructura del Proyecto
```
forohub/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/
│       │       └── alurachallenge/
│       │           └── forohub/
│       │               ├── controller/
│       │               ├── domain/
│       │               │   ├── topico/
│       │               │   └── usuario/
│       │               ├── infra/
│       │               │   ├── errores/
│       │               │   └── security/
│       │               └── service/
│       └── resources/
│           └── application.properties
└── README.md
```

## Endpoints

### Autenticación
```
POST /login - Iniciar sesión
POST /registro - Registrar nuevo usuario
```

### Tópicos
```
POST /topicos - Crear nuevo tópico
GET /topicos - Listar tópicos (acepta parámetros de paginación)
GET /topicos/{id} - Obtener tópico específico
PUT /topicos/{id} - Actualizar tópico
DELETE /topicos/{id} - Eliminar tópico (soft delete)
```

## Ejemplos de Uso

### Registro de Usuario
```json
POST /registro
{
    "login": "usuario",
    "clave": "contraseña"
}
```

### Login
```json
POST /login
{
    "login": "usuario",
    "clave": "contraseña"
}
```

### Crear Tópico
```json
POST /topicos
{
    "titulo": "Duda sobre Spring Boot",
    "mensaje": "¿Cómo implementar seguridad con JWT?",
    "autor": "usuario",
    "curso": "Spring Security"
}
```

## Seguridad
- Todas las rutas excepto `/login` y `/registro` requieren autenticación
- Los tokens JWT expiran después de 2 horas
- Las contraseñas se almacenan encriptadas usando BCrypt
- Se implementa CORS y protección CSRF

## Manejo de Errores
La API incluye manejo de errores personalizado para:
- Recursos no encontrados
- Validación de datos
- Errores de autenticación
- Duplicación de registros
- Errores internos del servidor

## Estado del Proyecto
El proyecto se encuentra en desarrollo activo. Próximas características incluirán:
- Sistema de respuestas a tópicos
- Roles de usuario
- Filtros adicionales
- Métricas y estadísticas
