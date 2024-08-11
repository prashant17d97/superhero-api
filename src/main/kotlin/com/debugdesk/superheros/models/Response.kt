package com.debugdesk.superheros.models

import kotlinx.serialization.Serializable

/**
 * Data class representing the response structure for API requests.
 *
 * This class encapsulates the details of a response, including the success status,
 * optional messages, pagination details, and a list of superheroes if applicable.
 *
 * @param success Indicates whether the request was successful. `true` if successful, `false` otherwise.
 * @param message An optional message providing additional information about the response. Can be `null`.
 * @param prevPage The page number of the previous page in pagination, if applicable. Can be `null`.
 * @param nextPage The page number of the next page in pagination, if applicable. Can be `null`.
 * @param heroes A list of [Superhero] objects representing the superheroes returned by the request. Defaults to an empty list.
 * @param lastUpdated A timestamp indicating when the data was last updated, if applicable. Can be `null`.
 *
 * @see Superhero
 * @author Prashant Singh
 * @since 11 Aug 2024
 */
@Serializable
data class Response(
    val success: Boolean,
    val message: String? = null,
    val prevPage: Int? = null,
    val nextPage: Int? = null,
    val heroes: List<Superhero> = emptyList(),
    val lastUpdated: Long? = null
)
