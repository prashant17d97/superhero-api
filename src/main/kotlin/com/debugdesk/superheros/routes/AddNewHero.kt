package com.debugdesk.superheros.routes

import com.debugdesk.superheros.utils.EndPoints.NEW_HERO
import com.debugdesk.superheros.models.Superhero
import com.debugdesk.superheros.repository.SuperHeroRepository
import io.ktor.http.*
import io.ktor.serialization.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

/**
 * Defines a route for adding a new superhero to the repository.
 *
 * This function sets up a POST request handler for the `/superheros/add_hero` endpoint.
 * When a POST request is received at this endpoint, it expects a `Superhero` object in the
 * request body. The function attempts to add the received superhero to the repository.
 * Based on the result of the operation, it responds with appropriate HTTP status codes and
 * messages:
 *
 * - **NoContent (204)**: Indicates that the superhero was added successfully.
 * - **BadRequest (400)**: Indicates that there was an issue with the request, such as invalid
 *   data or JSON conversion errors.
 *
 * **Error Handling**:
 * - **IllegalStateException**: Responds with a `BadRequest` status if there is an issue with
 *   processing the request.
 * - **JsonConvertException**: Responds with a `BadRequest` status if there is an error in
 *   converting the JSON data.
 *
 * @param repository An instance of [SuperHeroRepository] used to add the new superhero.
 * @throws IllegalStateException if the request cannot be processed due to an invalid state.
 * @throws JsonConvertException if there is an error in converting the request body from JSON.
 *
 * @author Prashant Singh
 * @since 11 Aug 2024
 *
 * @see SuperHeroRepository
 * @see Superhero
 */
fun Routing.addNewHero(repository: SuperHeroRepository) {
    post(NEW_HERO) {
        try {
            val task = call.receive<Superhero>()
            val isSuccess = repository.addNewHero(task)
            if (isSuccess) {
                call.respond(
                    message = "Data Added successfully",
                    status = HttpStatusCode.NoContent
                )
            } else {
                call.respond(
                    message = "Something went wrong",
                    status = HttpStatusCode.BadRequest
                )
            }

        } catch (ex: IllegalStateException) {
            call.respond(message = ex.localizedMessage, status = HttpStatusCode.BadRequest)
        } catch (ex: JsonConvertException) {
            call.respond(message = ex.localizedMessage, status = HttpStatusCode.BadRequest)
        }
    }
}
