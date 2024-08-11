package com.debugdesk.superheros

import com.debugdesk.superheros.plugins.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*

/**
 * Entry point for the Ktor server application.
 *
 * This function is the main entry point for the Ktor server application when running on the
 * Netty engine. It delegates the startup process to Ktor's `EngineMain` class, which handles
 * the server initialization and application lifecycle.
 *
 * @param args Command-line arguments passed to the application.
 * @see io.ktor.server.netty.EngineMain
 */
fun main() {
    val port = System.getenv("PORT")?.toInt() ?: 8080
    embeddedServer(Netty, port = port) {
        module()
    }.start(wait = true)
}

/**
 * Configures the Ktor application module.
 *
 * This function sets up various configurations and features for the Ktor application. It is
 * invoked during the application startup process to initialize and configure different aspects
 * of the application. The following configurations are applied:
 *
 * - **Koin**: Configures dependency injection using the Koin framework.
 * - **Default Headers**: Sets default HTTP headers for responses.
 * - **Status Pages**: Configures status pages to handle specific HTTP status codes.
 * - **Serialization**: Sets up JSON serialization with specific settings.
 * - **Routing**: Configures routing for handling different API endpoints and requests.
 *
 * The `module` function is annotated with `@Suppress("unused")` to indicate that the function is
 * intentionally unused in some contexts, typically for compatibility with frameworks that
 * require a specific function signature.
 *
 * @see configureKoin
 * @see configureDefaultHeader
 * @see configureStatusPages
 * @see configureSerialization
 * @see configureRouting
 */
fun Application.module() {
    configureKoin()
    configureDefaultHeader()
    configureStatusPages()
    configureSerialization()
    configureRouting()
}
