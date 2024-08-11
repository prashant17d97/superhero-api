package com.debugdesk.superheros.repository

import com.debugdesk.superheros.models.Superhero

/**
 * Interface representing a repository for managing superhero data.
 *
 * This repository interface defines methods for CRUD operations and other functionalities
 * related to superhero management. Implementations of this interface should provide
 * concrete logic for interacting with the data source, whether it be an in-memory store,
 * database, or any other persistence mechanism.
 *
 * Methods:
 * - `refresh()`: Refreshes or reloads the superhero data from the data source.
 * - `getAllHero()`: Retrieves a list of all superheroes.
 * - `getSuperHeroById(id: Int)`: Retrieves a superhero by its unique identifier.
 * - `findSuperhero(id: Int?, name: String?, franchise: String?)`: Searches for superheroes based on optional criteria.
 * - `deleteHero(id: Int)`: Deletes a superhero by its unique identifier.
 * - `deleteAllHero()`: Deletes all superheroes from the repository.
 * - `addNewHero(task: Superhero)`: Adds a new superhero to the repository.
 * - `updateHero(superhero: Superhero)`: Updates the details of an existing superhero.
 *
 * @see Superhero
 * @author Prashant Singh
 * @since 11 Aug 2024
 */
interface SuperHeroRepository {

    /**
     * Refreshes or reloads the superhero data from the data source.
     * This method is typically used to update the repository's data
     * to reflect any changes made in the data source.
     */
    fun refresh()

    /**
     * Retrieves a list of all superheroes.
     *
     * @return A list of [Superhero] objects representing all superheroes in the repository.
     */
    fun getAllHero(): List<Superhero>

    /**
     * Retrieves a superhero by its unique identifier.
     *
     * @param id The unique identifier of the superhero.
     * @return The [Superhero] object with the specified ID.
     * @throws NoSuchElementException If no superhero with the given ID exists.
     */
    fun getSuperHeroById(id: Int): Superhero

    /**
     * Searches for superheroes based on optional criteria.
     *
     * @param id Optional ID of the superhero.
     * @param name Optional name of the superhero.
     * @param franchise Optional franchise the superhero belongs to.
     * @return A list of [Superhero] objects that match the given criteria.
     *         Returns null if no superheroes match the criteria.
     */
    fun findSuperhero(id: Int?, name: String?, franchise: String?): List<Superhero>?

    /**
     * Deletes a superhero by its unique identifier.
     *
     * @param id The unique identifier of the superhero to be deleted.
     * @return `true` if the superhero was successfully deleted, `false` otherwise.
     */
    fun deleteHero(id: Int): Boolean

    /**
     * Deletes all superheroes from the repository.
     *
     * @return `true` if all superheroes were successfully deleted, `false` otherwise.
     */
    fun deleteAllHero(): Boolean

    /**
     * Adds a new superhero to the repository.
     *
     * @param task The [Superhero] object to be added.
     * @return `true` if the superhero was successfully added, `false` otherwise.
     */
    fun addNewHero(task: Superhero): Boolean

    /**
     * Updates the details of an existing superhero.
     *
     * @param superhero The [Superhero] object with updated details.
     * @return `true` if the superhero was successfully updated, `false` otherwise.
     */
    fun updateHero(superhero: Superhero): Boolean
}
