package com.debugdesk.superheros.routes

import com.debugdesk.superheros.models.Superhero
import com.debugdesk.superheros.repository.SuperHeroRepository
import com.debugdesk.superheros.utils.EndPoints.QueryParams.ID
import com.debugdesk.superheros.utils.EndPoints.UPDATE_EXISTING_HERO
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.SerializationException


/**
 * Defines a route for updating an existing superhero.
 *
 * This function sets up a PUT request handler for the `/superheros/update_hero/{id}` endpoint.
 * When a PUT request is received at this endpoint, it attempts to update a superhero based on the
 * provided ID in the URL path and the updated superhero data in the request body.
 *
 * **URL Path Parameter**:
 * - `id`: Required path parameter representing the unique identifier of the superhero to be updated.
 *
 * **Request Body**:
 * - A JSON representation of the [Superhero] object with the updated details. The provided data will
 *   have its `id` field set to the value specified in the URL path.
 *
 * **Response Handling**:
 * - **Success**: If the superhero is successfully updated in the repository, responds with a `200 OK` status
 *   and the message "Superhero updated successfully."
 * - **Invalid or Missing ID**: If the `id` parameter is missing or not a valid integer, responds with a `400 Bad Request`
 *   status and the message "Invalid or missing ID."
 * - **Invalid Superhero Data**: If the request body cannot be deserialized into a [Superhero] object,
 *   responds with a `400 Bad Request` status and the message "Invalid superhero data."
 * - **Superhero Not Found**: If the superhero with the specified ID is not found in the repository, responds
 *   with a `404 Not Found` status and the message "Superhero not found."
 *
 * @param repository An instance of [SuperHeroRepository] used to update the superhero in the repository.
 * @author Prashant Singh
 * @since 11 Aug 2024
 */
fun Route.updateSuperhero(repository: SuperHeroRepository) {
    put(UPDATE_EXISTING_HERO) {
        val heroId = call.parameters[ID]?.toIntOrNull()
        if (heroId == null) {
            call.respond(HttpStatusCode.BadRequest, "Invalid or missing ID")
            return@put
        }

        val updatedHero = try {
            call.receive<Superhero>().copy(id = heroId)
        } catch (e: SerializationException) {
            call.respond(HttpStatusCode.BadRequest, "Invalid superhero data")
            return@put
        }

        val isUpdated = repository.updateHero(updatedHero)

        if (isUpdated) {
            call.respond(HttpStatusCode.OK, "Superhero updated successfully")
        } else {
            call.respond(HttpStatusCode.NotFound, "Superhero not found")
        }
    }
}
