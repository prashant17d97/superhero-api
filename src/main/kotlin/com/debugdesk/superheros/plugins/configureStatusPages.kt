package com.debugdesk.superheros.plugins

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*


/**
 * Configures custom status pages for error handling in the Ktor application.
 *
 * This function installs the `StatusPages` feature, which allows for custom handling
 * of HTTP status codes and exceptions. It sets up a custom response for the `404 Not Found`
 * status code.
 *
 * When a `404 Not Found` error occurs, the application will respond with a custom message
 * indicating that the requested data was not found. This enhances user experience by providing
 * clear and specific error messages for common scenarios where data or resources are missing.
 *
 * @author Prashant Singh
 * @since 11 Aug 2024
 *
 * @see io.ktor.features.StatusPages
 * @see io.ktor.http.HttpStatusCode
 */
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
