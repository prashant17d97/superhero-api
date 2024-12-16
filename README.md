# Superhero API

## Overview

The Superhero API is a Ktor-based web application designed to manage a collection of superhero data. It allows for the addition, retrieval, updating, and deletion of superhero entries. This API provides endpoints for managing superhero data, with operations to refresh the data, search for superheroes, and handle various CRUD (Create, Read, Update, Delete) operations.

### Base URL

The API is deployed and accessible at: **[https://superhero-api-b6u9.onrender.com](https://superhero-api-b6u9.onrender.com)**

---

## Features

- **Create**: Add new superheroes to the repository.
- **Read**: Fetch a list of all superheroes or search for superheroes by specific criteria.
- **Update**: Modify existing superhero details.
- **Delete**: Remove superheroes either individually or in bulk.
- **Refresh**: Reload the superhero data into the repository.

---

## Technologies Used

- **Ktor**: A framework for building asynchronous servers and clients in Kotlin.
- **Koin**: A dependency injection framework for Kotlin.
- **Serialization**: Kotlinx.serialization for JSON serialization and deserialization.

---

## Configuration Details

### Dependency Injection

The application uses **Koin** for dependency injection. The Koin module is defined as follows:

```kotlin
val koinModule = module {
    single<SuperHeroRepository> {
        SuperHeroRepositoryImpl()
    }
}
```

### Serialization

The application uses **Kotlinx.serialization** for JSON processing:

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

Default headers are set for caching purposes:

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

Custom error handling is implemented using **Status Pages**:

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

---

## API Endpoints

### 1. **Home**

- **Endpoint**: `GET /`
- **Full URL**: [https://superhero-api-b6u9.onrender.com/](https://superhero-api-b6u9.onrender.com/)
- **Description**: Returns a welcome message.
- **Response**:  
  ```json
  {
    "message": "Welcome to the Superhero API!"
  }
  ```
  - Status: `200 OK`

### 2. **Refresh Data**

- **Endpoint**: `GET /superheros/refresh`
- **Full URL**: [https://superhero-api-b6u9.onrender.com/superheros/refresh](https://superhero-api-b6u9.onrender.com/superheros/refresh)
- **Description**: Refreshes the superhero data in the repository.
- **Response**:
  - Success: `200 OK`  
  - Failure: `400 Bad Request`

### 3. **Search Heroes**

- **Endpoint**: `GET /superheros/search`
- **Full URL**: [https://superhero-api-b6u9.onrender.com/superheros/search](https://superhero-api-b6u9.onrender.com/superheros/search)
- **Description**: Searches for superheroes by ID, name, or franchise.
- **Query Parameters**:
  - `id` (optional): The ID of the superhero.
  - `name` (optional): The name of the superhero.
  - `franchise` (optional): The franchise to which the superhero belongs.
- **Response**:
  - Success: `200 OK` with a list of superheroes.
  - Failure: `400 Bad Request` or `404 Not Found`.

### 4. **Add New Hero**

- **Endpoint**: `POST /superheros/add_hero`
- **Full URL**: [https://superhero-api-b6u9.onrender.com/superheros/add_hero](https://superhero-api-b6u9.onrender.com/superheros/add_hero)
- **Description**: Adds a new superhero to the repository.
- **Request Body**: JSON object representing the superhero.  
  Example:  
  ```json
  {
    "name": "Superman",
    "realName": "Clark Kent",
    "franchise": "DC"
  }
  ```
- **Response**:
  - Success: `204 No Content`
  - Failure: `400 Bad Request`

### 5. **Update Hero**

- **Endpoint**: `PUT /superheros/update_hero/{id}`
- **Full URL**: [https://superhero-api-b6u9.onrender.com/superheros/update_hero/{id}](https://superhero-api-b6u9.onrender.com/superheros/update_hero/{id})
- **Description**: Updates an existing superhero.
- **Path Parameter**:
  - `id`: The ID of the superhero to update.
- **Request Body**: JSON object representing the updated superhero.  
  Example:  
  ```json
  {
    "name": "Superman",
    "realName": "Clark Kent",
    "franchise": "DC"
  }
  ```
- **Response**:
  - Success: `200 OK`
  - Failure: `400 Bad Request` or `404 Not Found`

### 6. **Delete Hero**

- **Endpoint**: `DELETE /superheros/delete`
- **Full URL**: [https://superhero-api-b6u9.onrender.com/superheros/delete](https://superhero-api-b6u9.onrender.com/superheros/delete)
- **Description**: Deletes a superhero by ID.
- **Query Parameters**:
  - `id`: The ID of the superhero to delete.
- **Response**:
  - Success: `200 OK`
  - Failure: `400 Bad Request` or `404 Not Found`

### 7. **Delete All Heroes**

- **Endpoint**: `DELETE /superheros/delete_all`
- **Full URL**: [https://superhero-api-b6u9.onrender.com/superheros/delete_all](https://superhero-api-b6u9.onrender.com/superheros/delete_all)
- **Description**: Deletes all superheroes from the repository.
- **Response**:
  - Success: `200 OK`
  - Failure: `404 Not Found`

### 8. **Get All Heroes**

- **Endpoint**: `GET /superheros`
- **Full URL**: [https://superhero-api-b6u9.onrender.com/superheros](https://superhero-api-b6u9.onrender.com/superheros)
- **Description**: Retrieves a list of all superheroes.
- **Response**:
  - Success: `200 OK`

---

## Setup and Installation

### Prerequisites

- JDK 17 or higher
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

---

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

---

## Contribution

Contributions are welcome! If you have suggestions, improvements, or bug fixes, feel free to reach out or submit a pull request.

- **LinkedIn**: [Prashant Singh](https://linkedin.com/in/prashant17d97)
- **Contact Email**: [prashantsinghsca@gmail.com](mailto:prashantsinghsca@gmail.com)

---

## Author

- **Name**: Prashant Singh
- **Date**: 11 Aug 2024
