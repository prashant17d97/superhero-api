# Superhero API

## Overview

The Superhero API is a Ktor-based web application designed to manage a collection of superhero data. It allows for the addition, retrieval, updating, and deletion of superhero entries. This API provides endpoints for managing superhero data, with operations to refresh the data, search for superheroes, and handle various CRUD (Create, Read, Update, Delete) operations.

## Features

- **Create**: Add new superheroes to the repository.
- **Read**: Fetch a list of all superheroes or search for superheroes by specific criteria.
- **Update**: Modify existing superhero details.
- **Delete**: Remove superheroes either individually or in bulk.
- **Refresh**: Reload the superhero data into the repository.

## Technologies Used

- **Ktor**: A framework for building asynchronous servers and clients in Kotlin.
- **Koin**: A dependency injection framework for Kotlin.
- **Serialization**: Kotlinx.serialization for JSON serialization and deserialization.

## Project Structure

- **`Application.kt`**: Main application entry point.
- **`routes/`**: Contains route handlers for API endpoints.
- **`models/`**: Defines data models used in the application.
- **`utils/`**: Utility classes and functions.
- **`config/`**: Configuration files for Ktor and other setups.

## Setup and Installation

### Prerequisites

- JDK 11 or higher
- Kotlin 1.8 or higher
- Gradle (or any other build tool compatible with the project)

### Steps to Run the Application

1. **Clone the Repository**

   ```bash
   git clone https://github.com/prashant17d97/superhero-api.git
   cd superhero-api
   ```

2. **Build the Project**

   If you are using Gradle:

   ```bash
   ./gradlew build
   ```

3. **Run the Application**

   ```bash
   ./gradlew run
   ```

   Alternatively, you can use:

   ```bash
   java -jar build/libs/superhero-api.jar
   ```

## API Endpoints

### 1. **Home**

- **Endpoint**: `GET /`
- **Description**: Returns a welcome message.
- **Response**: `200 OK`

### 2. **Refresh Data**

- **Endpoint**: `GET /superheros/refresh`
- **Description**: Refreshes the superhero data in the repository.
- **Response**: `200 OK` if successful, `400 Bad Request` if something went wrong.

### 3. **Search Heroes**

- **Endpoint**: `GET /superheros/search`
- **Description**: Searches for superheroes by ID, name, or franchise.
- **Query Parameters**:
  - `id` (optional): The ID of the superhero.
  - `name` (optional): The name of the superhero.
  - `franchise` (optional): The franchise to which the superhero belongs.
- **Response**: `200 OK` with the list of heroes if found, `400 Bad Request` or `404 Not Found` if no heroes found.

### 4. **Add New Hero**

- **Endpoint**: `POST /superheros/add_hero`
- **Description**: Adds a new superhero to the repository.
- **Request Body**: JSON object representing the superhero.
- **Response**: `204 No Content` if successful, `400 Bad Request` if the data is invalid.

### 5. **Update Hero**

- **Endpoint**: `PUT /superheros/update_hero/{id}`
- **Description**: Updates an existing superhero.
- **Path Parameter**:
  - `id`: The ID of the superhero to update.
- **Request Body**: JSON object representing the updated superhero.
- **Response**: `200 OK` if updated successfully, `400 Bad Request` if data is invalid, `404 Not Found` if the superhero is not found.

### 6. **Delete Hero**

- **Endpoint**: `DELETE /superheros/delete`
- **Description**: Deletes a superhero by ID.
- **Query Parameters**:
  - `id`: The ID of the superhero to delete.
- **Response**: `200 OK` if successfully deleted, `400 Bad Request` if invalid request, `404 Not Found` if the superhero is not found.

### 7. **Delete All Heroes**

- **Endpoint**: `DELETE /superheros/delete_all`
- **Description**: Deletes all superheroes from the repository.
- **Response**: `200 OK` if all superheroes are deleted, `404 Not Found` if no data was found.

### 8. **Get All Heroes**

- **Endpoint**: `GET /superheros`
- **Description**: Retrieves a list of all superheroes.
- **Response**: `200 OK` with the list of heroes.

## Configuration

### Dependency Injection

The application uses Koin for dependency injection. The Koin module is defined in `Application.kt`:

```kotlin
val koinModule = module {
    single<SuperHeroRepository> {
        SuperHeroRepositoryImpl()
    }
}
```

### Serialization

The application uses Kotlinx.serialization for JSON processing:

```kotlin
@OptIn(ExperimentalSerializationApi::class)
fun Application.configureSerialization() {
    install(ContentNegotiation) {
        json(Json {
            namingStrategy = JsonNamingStrategy.SnakeCase
            ignoreUnknownKeys = true
        })
    }
}
```

### Default Headers

Sets default headers for caching:

```kotlin
fun Application.configureDefaultHeader() {
    install(DefaultHeaders) {
        val oneYearInSeconds = Duration.ofDays(365).seconds
        header(
            name = HttpHeaders.CacheControl,
            value = "public, max-age=$oneYearInSeconds, immutable"
        )
    }
}
```

### Status Pages

Configures status pages for error handling:

```kotlin
fun Application.configureStatusPages() {
    install(StatusPages) {
        status(HttpStatusCode.NotFound) { call, _ ->
            call.respond(
                message = "Data Not Found.",
                status = HttpStatusCode.NotFound
            )
        }
    }
}
```

## Example Data

Sample superhero data for testing:

```kotlin
object HeroUtil {
    val superHeros = listOf(
        Superhero(
            name = "Spider-Man",
            realName = "Peter Parker",
            originStory = "Peter Parker was bitten by a radioactive spider, gaining spider-like abilities.",
            powersAndAbilities = listOf(
                "Wall-crawling",
                "Spider-sense",
                "Super strength",
                "Agility",
                "Web-shooting"
            ),
            weaknesses = listOf("Lack of experience with certain types of crime"),
            costumeAndAppearance = "Red and blue suit with a spider emblem",
            personalityTraits = listOf("Brave", "Selfless", "Witty"),
            alliesAndSidekicks = listOf("Iron Man", "Doctor Strange"),
            enemiesAndRivals = listOf("Green Goblin", "Doctor Octopus"),
            weaponsAndGadgets = listOf("Web-shooters"),
            baseOfOperations = "New York City",
            missionAndGoals = "To protect New York City and uphold justice",
            backstoryAndRelationships = "Orphaned at a young age, raised by Aunt May and Uncle Ben",
            catchphraseOrMotto = "With great power comes great responsibility",
            signatureMovesOrTechniques = listOf("Web-slinging", "Spider-Sense"),
            roleInTheWorld = "Superhero and protector of New York City",
            secretIdentity = "Peter Parker",
            growthAndDevelopment = "Grew from a high school student to a mature hero",
            publicPictureUrl = "http://example.com/spiderman.jpg",
            id = 1,
            netWorthInComics = 1.0e8,
            belongingFranchise = "Marvel"
        )
    )
}
```

## Author

- **Name**: Prashant Singh
- **Date**: 11 Aug 2024

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.