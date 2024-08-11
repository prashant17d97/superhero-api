package com.debugdesk.superheros.plugins

import com.debugdesk.superheros.di.koinModule
import io.ktor.server.application.*
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

/**
 * Configures Koin for dependency injection in the Ktor application.
 *
 * This function installs the Koin feature in the Ktor application, which is a
 * dependency injection framework used to manage and inject dependencies.
 * It initializes Koin with the specified module configuration.
 *
 * The `modules` function is used to load the Koin modules, which define
 * how dependencies are provided and injected throughout the application.
 * The `koinModule` is passed to Koin to set up the dependency injection context.
 *
 * @author Prashant Singh
 * @since 11 Aug 2024
 *
 * @see org.koin.core.context.startKoin
 * @see org.koin.dsl.module
 * @see koinModule
 *
 */
fun Application.configureKoin() {
    install(Koin) {
        slf4jLogger()
        modules(koinModule)
    }
}
