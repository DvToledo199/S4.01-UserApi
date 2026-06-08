# S4.01 - User API

Introduction to Spring Boot and REST APIs.

## Level 1 - Health Check API

## Description

This project exposes a simple REST endpoint to verify that the API is running correctly.

## Technologies

- Java 21
- Spring Boot 3.5.14
- Maven
- JUnit 5
- MockMvc

## Endpoint

### GET /health

Returns the health status of the API.

Example response:

```json
{
  "status": "OK"
}
```

## Testing

The project includes automated tests using MockMvc to verify:

- HTTP 200 OK response
- Correct JSON structure
- Expected value of the `status` field

## JAR Execution

The application was successfully packaged and executed as a standalone JAR file.

![JAR Execution](screenshots/jar-execution.png)

## Level 2 - User Management API

### Description

This level extends the API with basic user management functionality using an in-memory list as temporary storage.

### User Model

Each user contains:

- id (UUID)
- name (String)
- email (String)

### Implemented Endpoints

#### GET /users

Returns all users stored in memory.

#### POST /users

Creates a new user.

Request example:

```json
{
  "name": "John Doe",
  "email": "john@example.com"
}
```

The API automatically generates a UUID for the new user.

#### GET /users/{id}

Returns a specific user by UUID.

Response:

- 200 OK when the user exists.
- 404 Not Found when the user does not exist.

#### GET /users?name=value

Filters users by name.

The search is case-insensitive and returns all users whose name contains the provided text.

### Testing

Automated tests were implemented using MockMvc and JUnit 5 to verify:

- Retrieval of an empty user list.
- User creation with automatic UUID generation.
- Retrieval of a user by ID.
- 404 response for non-existing users.
- User filtering by name.

## Level 3 - Layered Architecture

### Description

The application was refactored following a layered architecture to separate responsibilities and improve maintainability.

The architecture is divided into three layers:

- Controller: Handles HTTP requests and responses.
- Service: Contains business logic and application rules.
- Repository: Manages data access.

### Repository Layer

A repository abstraction was introduced through the `UserRepository` interface.

An in-memory implementation called `InMemoryUserRepository` was created to manage users using an internal list.

Implemented repository operations:

- save(User user)
- findAll()
- findById(UUID id)
- searchByName(String name)
- existsByEmail(String email)

### Service Layer

A service abstraction was introduced through the `UserService` interface and implemented in `UserServiceImpl`.

The service layer is responsible for:

- Creating users.
- Retrieving users.
- Searching users by name.
- Retrieving users by ID.
- Applying business rules.

### Business Rule: Unique Email Validation

A validation was added to prevent duplicate email addresses.

When creating a user:

- If the email already exists, an `EmailAlreadyExistsException` is thrown.
- Otherwise, a UUID is generated and the user is stored.

### Dependency Injection

Spring dependency injection was implemented using constructor injection.

Annotations used:

- `@Repository`
- `@Service`

### Testing

The project includes:

- Integration tests using `@SpringBootTest` and `@AutoConfigureMockMvc`.
- Unit tests for `InMemoryUserRepository`.
- Unit tests for `UserServiceImpl` using Mockito.

Mockito was used with:

- `@Mock`
- `@InjectMocks`

Test coverage includes:

- Duplicate email validation.
- Successful user creation.
- User search by ID.
- User search by name.
- Email existence verification.
- User persistence operations.