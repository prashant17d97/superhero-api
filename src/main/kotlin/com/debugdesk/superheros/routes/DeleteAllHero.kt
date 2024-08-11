package com.debugdesk.superheros.routes

import com.debugdesk.superheros.utils.EndPoints.DELETE_ALL
import com.debugdesk.superheros.repository.SuperHeroRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

/**
 * Defines a route for deleting all superheroes from the repository.
 *
 * This function sets up a DELETE request handler for the `/superheros/delete_all` endpoint.
 * When a DELETE request is received at this endpoint, it attempts to delete all superheroes
 * from the repository. Based on the result of the operation, it responds with appropriate HTTP
 * status codes and messages:
 *
 * - **OK (200)**: Indicates that all superheroes were successfully deleted.
 * - **NotFound (404)**: Indicates that there were no superheroes to delete.
 *
 * **Response Handling**:
 * - **Success**: Responds with a message of "Delete Successfully" and an `OK` status if the
 *   deletion was successful.
 * - **Failure**: Responds with a message of "No Data Found" and a `NotFound` status if no
 *   superheroes were present to be deleted.
 *
 * @author Prashant Singh
 * @since 11 Aug 2024
 *
 * @param repository An instance of [SuperHeroRepository] used to perform the deletion.
 * @see SuperHeroRepository
 */
fun Routing.deleteAllHero(repository: SuperHeroRepository) {
    delete(DELETE_ALL) {
        val response = repository.deleteAllHero()

        val (message, status) = when (response) {
            true -> "Delete Successfully" to HttpStatusCode.OK
            false -> "No Data Found" to HttpStatusCode.NotFound
        }
        call.respond(
            message = message,
            status = status
        )
    }
}
