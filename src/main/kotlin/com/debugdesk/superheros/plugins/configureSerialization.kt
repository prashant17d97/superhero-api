package com.debugdesk.superheros.plugins

import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonNamingStrategy

/**
 * Configures JSON serialization for the Ktor application using Kotlinx Serialization.
 *
 * This function installs the `ContentNegotiation` feature for the Ktor application,
 * enabling JSON serialization and deserialization. It sets up the JSON configuration
 * with the Kotlinx Serialization library to handle request and response content.
 *
 * The JSON configuration specifies:
 * - `namingStrategy`: Uses `SnakeCase` for JSON property names, which means
 *   that property names in JSON will be converted to snake_case format.
 * - `ignoreUnknownKeys`: When set to `true`, allows the deserialization process
 *   to ignore any unknown keys in the JSON input that are not present in the data class.
 *
 * This setup ensures compatibility with APIs that use snake_case for JSON properties
 * and makes the application more robust by ignoring unexpected keys in incoming data.
 *
 * @author Prashant Singh
 * @since 11 Aug 2024
 *
 * @see io.ktor.features.ContentNegotiation
 * @see kotlinx.serialization.json.Json
 * @see kotlinx.serialization.json.JsonNamingStrategy
 */
@OptIn(ExperimentalSerializationApi::class)
fun Application.configureSerialization() {
    install(ContentNegotiation) {
        json(Json {
            namingStrategy = JsonNamingStrategy.SnakeCase
            ignoreUnknownKeys = true
        })
    }
}
