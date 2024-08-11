package com.debugdesk.superheros.repository

import com.debugdesk.superheros.models.Superhero
import com.debugdesk.superheros.utils.HeroUtil.superHeros

/**
 * Implementation of the [SuperHeroRepository] interface for managing superhero data.
 *
 * This class provides concrete methods for managing a list of superheroes, including
 * operations to refresh the list, add, update, delete, and retrieve superheroes.
 * The data is maintained in-memory and is managed using an immutable list.
 *
 * @author Prashant Singh
 * @since 11 Aug 2024
 */
class SuperHeroRepositoryImpl : SuperHeroRepository {

    /** In-memory list of superheroes. */
    private var superHerosCopy = emptyList<Superhero>()

    /**
     * Initializes the repository and loads the initial data.
     * Calls the [refresh] method to populate the `superHerosCopy` list.
     */
    init {
        refresh()
    }

    /**
     * Refreshes the list of superheroes from the data source.
     * This method reloads the `superHerosCopy` list with the current data.
     */
    override fun refresh() {
        superHerosCopy = superHeros.toMutableList()
    }

    /**
     * Retrieves a list of all superheroes.
     *
     * @return A list of [Superhero] objects representing all superheroes in the repository.
     */
    override fun getAllHero(): List<Superhero> = superHerosCopy

    /**
     * Retrieves a superhero by its unique identifier.
     *
     * @param id The unique identifier of the superhero.
     * @return The [Superhero] object with the specified ID.
     * @throws NoSuchElementException If no superhero with the given ID exists.
     */
    override fun getSuperHeroById(id: Int): Superhero {
        return superHerosCopy.first { it.id == id }
    }

    /**
     * Searches for superheroes based on optional criteria.
     *
     * @param id Optional ID of the superhero.
     * @param name Optional name of the superhero.
     * @param franchise Optional franchise the superhero belongs to.
     * @return A list of [Superhero] objects that match the given criteria.
     *         Returns null if no superheroes match the criteria.
     */
    override fun findSuperhero(id: Int?, name: String?, franchise: String?): List<Superhero>? {
        return try {
            id?.let {
                listOf(getSuperHeroById(id))
            } ?: name?.let {
                superHerosCopy.filter { it.name.contains(name, true) }
            } ?: franchise?.let {
                superHerosCopy.filter { it.belongingFranchise.contains(franchise, true) }
            }
        } catch (exception: Exception) {
            null
        }
    }

    /**
     * Deletes a superhero by its unique identifier.
     *
     * @param id The unique identifier of the superhero to be deleted.
     * @return `true` if the superhero was successfully deleted, `false` otherwise.
     */
    override fun deleteHero(id: Int): Boolean {
        return try {
            val initialSize = superHerosCopy.size
            superHerosCopy = superHerosCopy.filter { it.id != id }
            val newSize = superHerosCopy.size
            initialSize != newSize
        } catch (exception: Exception) {
            false
        }
    }

    /**
     * Deletes all superheroes from the repository.
     *
     * @return `true` if all superheroes were successfully deleted, `false` otherwise.
     */
    override fun deleteAllHero(): Boolean {
        superHerosCopy = emptyList()
        return superHerosCopy.isEmpty()
    }

    /**
     * Adds a new superhero to the repository.
     *
     * @param task The [Superhero] object to be added.
     * @return `true` if the superhero was successfully added, `false` otherwise.
     */
    override fun addNewHero(task: Superhero): Boolean {
        val initialSize = superHerosCopy.size
        val id = if (superHerosCopy.isNotEmpty()) {
            superHerosCopy.last().id + 1
        } else {
            1
        }

        val updatedList = superHerosCopy.toMutableList()
        updatedList.add(task.copy(id = id))

        superHerosCopy = updatedList.toList()
        return updatedList.size > initialSize
    }

    /**
     * Updates the details of an existing superhero.
     *
     * @param superhero The [Superhero] object with updated details.
     * @return `true` if the superhero was successfully updated, `false` otherwise.
     */
    override fun updateHero(superhero: Superhero): Boolean {
        val index = superHerosCopy.indexOfFirst { it.id == superhero.id }
        return if (index != -1) {
            superHerosCopy = superHerosCopy.map {
                if (it.id == superhero.id) superhero else it
            }
            true
        } else {
            false
        }
    }
}
