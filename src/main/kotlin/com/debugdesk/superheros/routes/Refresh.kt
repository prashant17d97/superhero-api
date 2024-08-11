package com.debugdesk.superheros.routes

import com.debugdesk.superheros.utils.EndPoints.REFRESH
import com.debugdesk.superheros.repository.SuperHeroRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

/**
 * Defines a route for refreshing the list of superheroes in the repository.
 *
 * This function sets up a GET request handler for the `/superheros/refresh` endpoint.
 * When a GET request is received at this endpoint, it triggers the `refresh` method
 * on the [SuperHeroRepository] to reload or refresh the list of superheroes.
 * The response indicates whether the refresh operation was successful based on the
 * current state of the list of superheroes in the repository.
 *
 * **Response Handling**:
 * - **Success**: Responds with a `200 OK` status and the message "Refreshed successfully" if the list
 *   of superheroes is not empty after the refresh.
 * - **Failure**: Responds with a `400 Bad Request` status and the message "Something went wrong try again"
 *   if the list is empty after the refresh.
 *
 * **Alternative Implementation**:
 * The commented-out code provides an alternative implementation where, upon successful refresh,
 * the client is redirected to the `/superheros` endpoint to fetch the updated list.
 *
 * @param superHeroRepository An instance of [SuperHeroRepository] used to refresh the list of superheroes.
 * @see SuperHeroRepository
 * @author Prashant Singh
 * @since 11 Aug 2024
 */
fun Routing.refresh(superHeroRepository: SuperHeroRepository) {
    get(REFRESH) {
        superHeroRepository.refresh()
        val (message, status) =
            if (superHeroRepository.getAllHero().isNotEmpty())
                "Refreshed successfully" to HttpStatusCode.OK
            else "Something went wrong try again" to HttpStatusCode.BadRequest
        call.respond(
            message = message,
            status = status
        )
    }

    /*
    Way to re-direct
    get(REFRESH) {
        superHeroRepository.refresh()
        call.respondRedirect(SUPER_HERO)
    }
    get(SUPER_HERO) {
        call.respond(
            message = Response(
                success = true,
                message = "Fetched successfully",
                heroes = superHeroRepository.getAllHero()
            ),
            status = HttpStatusCode.OK
        )
    }*/
}
