package com.debugdesk.superheros.routes

import com.debugdesk.superheros.utils.EndPoints.HOME
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

/**
 * Defines a route for handling requests to the home page.
 *
 * This function sets up a GET request handler for the root endpoint (`/`).
 * When a GET request is received at this endpoint, it responds with a simple welcome message.
 * The response includes a status code indicating successful retrieval.
 *
 * **Response Handling**:
 * - **Success**: Responds with a `200 OK` status and a message "Welcome" indicating successful handling of the request.
 *
 * @author Prashant Singh
 * @since 11 Aug 2024
 */
fun Routing.homePage() {
    get(HOME) {
        call.respond(
            message = "Welcome",
            status = HttpStatusCode.OK
        )
    }
}
