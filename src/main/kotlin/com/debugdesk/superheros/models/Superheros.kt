package com.debugdesk.superheros.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Data class representing a superhero.
 *
 * This class encapsulates the details of a superhero, including their personal information,
 * abilities, and affiliations. It is used for representing superheroes in the application.
 *
 * @author Prashant Singh
 * @since 11 Aug 2024
 */

@Serializable
data class Superhero(
    @SerialName("name")
    val name: String,                           // Name of the superhero

    @SerialName("real_name")
    val realName: String,                       // Real name or identity

    @SerialName("origin_story")
    val originStory: String,                    // Origin story of the superhero

    @SerialName("powers_and_abilities")
    val powersAndAbilities: List<String>,       // List of powers and abilities

    @SerialName("weaknesses")
    val weaknesses: List<String>,               // List of weaknesses

    @SerialName("costume_and_appearance")
    val costumeAndAppearance: String,           // Description of costume and appearance

    @SerialName("personality_traits")
    val personalityTraits: List<String>,        // List of personality traits

    @SerialName("allies_and_sidekicks")
    val alliesAndSidekicks: List<String>,       // List of allies and sidekicks

    @SerialName("enemies_and_rivals")
    val enemiesAndRivals: List<String>,         // List of enemies and rivals

    @SerialName("weapons_and_gadgets")
    val weaponsAndGadgets: List<String>,        // List of weapons and gadgets

    @SerialName("base_of_operations")
    val baseOfOperations: String,               // Base of operations

    @SerialName("mission_and_goals")
    val missionAndGoals: String,                // Mission and goals

    @SerialName("backstory_and_relationships")
    val backstoryAndRelationships: String,      // Backstory and relationships

    @SerialName("catchphrase_or_motto")
    val catchphraseOrMotto: String,             // Catchphrase or motto

    @SerialName("signature_moves_or_techniques")
    val signatureMovesOrTechniques: List<String>, // Signature moves or techniques

    @SerialName("role_in_the_world")
    val roleInTheWorld: String,                 // Role in the world

    @SerialName("secret_identity")
    val secretIdentity: String,                 // Secret identity

    @SerialName("growth_and_development")
    val growthAndDevelopment: String,           // Growth and development

    @SerialName("public_picture_url")
    val publicPictureUrl: String,               // URL of the public picture

    @SerialName("id")
    val id: Int,                                // Unique identifier for the superhero

    @SerialName("net_worth_in_comics")
    val netWorthInComics: Double,               // Net worth in comics (in USD or other currency)

    @SerialName("belonging_franchise")
    val belongingFranchise: String              // Belonging franchise or universe
)

