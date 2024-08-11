package com.debugdesk.superheros.routes

import com.debugdesk.superheros.utils.EndPoints.QueryParams.FRANCHISE
import com.debugdesk.superheros.utils.EndPoints.QueryParams.ID
import com.debugdesk.superheros.utils.EndPoints.QueryParams.NAME
import com.debugdesk.superheros.utils.EndPoints.SEARCH
import com.debugdesk.superheros.models.Response
import com.debugdesk.superheros.repository.SuperHeroRepository
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

/**
 * Defines a route for searching superheroes based on various query parameters.
 *
 * This function sets up a GET request handler for the `/superheros/search` endpoint.
 * When a GET request is received at this endpoint, it attempts to find superheroes
 * based on the provided query parameters: `id`, `name`, and `franchise`.
 * It retrieves the corresponding superheroes from the [SuperHeroRepository] and returns
 * the results in the response.
 *
 * **Query Parameters**:
 * - `id`: Optional query parameter to filter superheroes by their unique identifier.
 * - `name`: Optional query parameter to filter superheroes by their name.
 * - `franchise`: Optional query parameter to filter superheroes by their belonging franchise.
 *
 * **Response Handling**:
 * - **Success**: If superheroes matching the query parameters are found, responds with a `200 OK`
 *   status and the list of superheroes.
 * - **Heroes Not Found**: If no superheroes match the query parameters, responds with a `400 Bad Request`
 *   status and the message "Heroes not Found."
 * - **Invalid Input**: Handles cases where the query parameters are invalid or not formatted correctly.
 *   - **Number Format Exception**: If the `id` parameter cannot be converted to an integer, responds with a
 *     `404 Not Found` status and the message "Only Numbers Allowed."
 *   - **No Such Element Exception**: If an element is not found during processing, responds with a `404 Not Found`
 *     status and the message "Heroes not Found."
 *   - **Illegal Argument Exception**: If an invalid argument is provided, responds with a `404 Not Found`
 *     status and the message "Invalid Argument Provided."
 *
 * @param superHeroRepository An instance of [SuperHeroRepository] used to search for superheroes.
 * @see SuperHeroRepository
 * @author Prashant Singh
 * @since 11 Aug 2024
 */
fun Routing.search(superHeroRepository: SuperHeroRepository) {
    get(SEARCH) {
        val heroId = call.request.queryParameters[ID]?.toIntOrNull()
        val heroName = call.request.queryParameters[NAME]
        val franchise = call.request.queryParameters[FRANCHISE]
        val dataResponse = superHeroRepository.findSuperhero(
            id = heroId,
            name = heroName,
            franchise = franchise
        )?.let {
            it to HttpStatusCode.OK
        }
        val (response, status) = try {
            dataResponse ?: (Response(success = false, message = "Heroes not Found.") to HttpStatusCode.BadRequest)
        } catch (e: NumberFormatException) {
            Response(success = false, message = "Only Numbers Allowed.") to HttpStatusCode.NotFound
        } catch (e: NoSuchElementException) {
            Response(success = false, message = "Heroes not Found.") to HttpStatusCode.NotFound
        } catch (e: IllegalArgumentException) {
            Response(success = false, message = "Invalid Argument Provided.") to HttpStatusCode.NotFound
        }
        call.respond(
            message = response,
            status = status
        )
    }
}

