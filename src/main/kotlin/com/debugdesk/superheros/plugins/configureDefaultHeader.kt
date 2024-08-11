package com.debugdesk.superheros.plugins

import io.ktor.server.application.*
import io.ktor.server.plugins.defaultheaders.*
import io.ktor.http.*
import java.time.Duration

/**
 * Configures default headers for the Ktor application.
 *
 * This function installs the `DefaultHeaders` feature for the Ktor application,
 * setting a default `Cache-Control` header for all responses. The `Cache-Control`
 * header is set to make the responses public and cacheable for one year,
 * with the `max-age` directive specifying the cache duration in seconds and
 * the `immutable` directive indicating that the response will not change.
 *
 * The cache duration is set to one year (365 days), calculated in seconds.
 * This setting helps to improve performance by reducing the need for repeated
 * requests for resources that do not change frequently.
 *
 * @see io.ktor.features.DefaultHeaders
 * @see io.ktor.http.HttpHeaders
 *
 * @author Prashant Singh
 * @since 11 Aug 2024
 */
fun Application.configureDefaultHeader() {
    install(DefaultHeaders) {
        val oneYearInSeconds = Duration.ofDays(365).seconds
        header(
            name = HttpHeaders.CacheControl,
            value = "public, max-age=$oneYearInSeconds, immutable"
        )
    }
}
