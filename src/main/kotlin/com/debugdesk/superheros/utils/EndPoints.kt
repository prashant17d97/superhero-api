package com.debugdesk.superheros.utils

import com.debugdesk.superheros.utils.EndPoints.QueryParams.ID

/**
 * Defines the API endpoints and query parameters used in the application.
 *
 * This object contains constants representing the various endpoints for the superhero
 * API, as well as query parameters used in API requests. These constants help to avoid
 * hardcoding strings throughout the application, making it easier to maintain and update
 * the endpoints and parameters as needed.
 *
 * **Endpoints:**
 * - **SUPER_HERO**: Base endpoint for superhero-related routes (`/superheros`).
 * - **HOME**: Endpoint for the home page (`/`).
 * - **REFRESH**: Endpoint for refreshing superhero data (`/superheros/refresh`).
 * - **SEARCH**: Endpoint for searching superheroes (`/superheros/search`).
 * - **DELETE**: Endpoint for deleting a specific superhero (`/superheros/delete`).
 * - **DELETE_ALL**: Endpoint for deleting all superheroes (`/superheros/delete_all`).
 * - **NEW_HERO**: Endpoint for adding a new superhero (`/superheros/add_hero`).
 * - **UPDATE_EXISTING_HERO**: Endpoint for updating an existing superhero, with a path parameter for the hero ID (`/superheros/update_hero/{id}`).
 *
 * **Query Parameters:**
 * - **ID**: Query parameter for specifying the superhero ID (`id`).
 * - **NAME**: Query parameter for specifying the superhero name (`name`).
 * - **FRANCHISE**: Query parameter for specifying the superhero franchise (`franchise`).
 *
 * @author Prashant Singh
 * @since 11 Aug 2024
 *
 */
object EndPoints {

    const val SUPER_HERO = "/superheros"
    const val HOME = "/"
    const val REFRESH = "$SUPER_HERO/refresh"
    const val SEARCH = "$SUPER_HERO/search"
    const val DELETE = "$SUPER_HERO/delete"
    const val DELETE_ALL = "$SUPER_HERO/delete_all"
    const val NEW_HERO = "$SUPER_HERO/add_hero"
    const val UPDATE_EXISTING_HERO = "$SUPER_HERO/update_hero/{$ID}"

    object QueryParams {
        const val ID = "id"
        const val NAME = "name"
        const val FRANCHISE = "franchise"
    }
}
