package com.debugdesk.superheros.routes

import com.debugdesk.superheros.utils.EndPoints.SUPER_HERO
import com.debugdesk.superheros.models.Response
import com.debugdesk.superheros.repository.SuperHeroRepository
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

/**
 * Defines a route for retrieving all superheroes from the repository.
 *
 * This function sets up a GET request handler for the `/superheros` endpoint.
 * When a GET request is received at this endpoint, it retrieves the list of all superheroes
 * from the repository and responds with the data encapsulated in a [Response] object. The response
 * includes a success status, the list of superheroes, and a message indicating successful retrieval.
 *
 * **Response Handling**:
 * - **Success**: Responds with a `200 OK` status and a [Response] object containing:
 *   - `success` set to `true`
 *   - `heroes` containing the list of all superheroes
 *   - `message` set to "Fetched successfully"
 *
 * @param superHeroRepository An instance of [SuperHeroRepository] used to fetch the list of superheroes.
 * @see SuperHeroRepository
 * @see Response
 * @author Prashant Singh
 * @since 11 Aug 2024
 */

fun Routing.getSuperHeros(superHeroRepository: SuperHeroRepository) {
    get(SUPER_HERO) {
        call.respond(
            message = Response(
                success = true,
                heroes = superHeroRepository.getAllHero(),
                message = "Fetched successfully"
            )
        )
    }
}
