package com.debugdesk.superheros.routes

import com.debugdesk.superheros.utils.EndPoints.DELETE
import com.debugdesk.superheros.utils.EndPoints.QueryParams.ID
import com.debugdesk.superheros.repository.SuperHeroRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

/**
 * Defines a route for deleting a specific superhero from the repository.
 *
 * This function sets up a DELETE request handler for the `/superheros/delete` endpoint.
 * When a DELETE request is received at this endpoint, it expects an ID as a query parameter
 * to identify the superhero to be deleted. The function attempts to delete the superhero
 * with the specified ID from the repository. Based on the result of the operation, it responds
 * with appropriate HTTP status codes and messages:
 *
 * - **OK (200)**: Indicates that the specified superhero was successfully deleted.
 * - **NotFound (404)**: Indicates that no superhero with the given ID was found.
 * - **BadRequest (400)**: Indicates that the request was invalid, such as missing or incorrect ID.
 *
 * **Response Handling**:
 * - **Invalid Request**: Responds with a `BadRequest` status and the message "Invalid Request" if
 *   the ID is missing or invalid.
 * - **Success**: Responds with an `OK` status and a message of "Delete Successfully" followed
 *   by the updated count of remaining superheroes if the deletion was successful.
 * - **Failure**: Responds with a `NotFound` status and the message "No Data Found" if no
 *   superhero with the specified ID was found.
 *
 * @param repository An instance of [SuperHeroRepository] used to perform the deletion.
 * @see SuperHeroRepository
 * @author Prashant Singh
 * @since 11 Aug 2024
 */
fun Routing.deleteHero(repository: SuperHeroRepository) {
    delete(DELETE) {
        val response = call.request.queryParameters[ID]?.toIntOrNull()?.let { repository.deleteHero(it) }

        val (message, status) = when (response) {
            null -> "Invalid Request" to HttpStatusCode.BadRequest
            true -> "Delete Successfully\n${repository.getAllHero().size}" to HttpStatusCode.OK
            false -> "No Data Found" to HttpStatusCode.NotFound
        }
        call.respond(
            message = message,
            status = status
        )
    }
}
