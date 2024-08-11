package com.debugdesk.superheros.plugins

import com.debugdesk.superheros.repository.SuperHeroRepository
import com.debugdesk.superheros.routes.*
import io.ktor.server.application.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

/**
 * Configures the routing for the Ktor application.
 *
 * This function sets up the routing for the application by defining various routes and
 * associating them with their respective handlers. It uses dependency injection to provide
 * an instance of `SuperHeroRepository` to the route handlers.
 *
 * The following routes are configured:
 * - **homePage**: Defines the route for the home page.
 * - **refresh**: Defines the route for refreshing superhero data.
 * - **search**: Defines the route for searching superheroes.
 * - **deleteHero**: Defines the route for deleting a specific superhero.
 * - **deleteAllHero**: Defines the route for deleting all superheroes.
 * - **addNewHero**: Defines the route for adding a new superhero.
 * - **updateSuperhero**: Defines the route for updating an existing superhero.
 * - **getSuperHeros**: Defines the route for retrieving all superheroes.
 *
 * The `SuperHeroRepository` is injected into the routing configuration to be used by
 * the route handlers for interacting with the superhero data.
 *
 * @author Prashant Singh
 * @since 11 Aug 2024
 *
 * @see io.ktor.routing.routing
 * @see io.ktor.application.Application
 * @see SuperHeroRepository
 */
fun Application.configureRouting() {
    routing {
        val repository: SuperHeroRepository by inject()
        homePage()
        refresh(repository)
        search(repository)
        deleteHero(repository)
        deleteAllHero(repository)
        addNewHero(repository)
        updateSuperhero(repository)
        getSuperHeros(repository)
    }
}
