package com.debugdesk.superheros.di

import com.debugdesk.superheros.repository.SuperHeroRepository
import com.debugdesk.superheros.repository.SuperHeroRepositoryImpl
import org.koin.dsl.module

/**
 * Koin module definition for dependency injection.
 *
 * This module provides a singleton instance of the [SuperHeroRepository] interface,
 * which is implemented by the [SuperHeroRepositoryImpl] class.
 * The Koin framework will manage the lifecycle of this singleton instance,
 * ensuring that the same instance is injected wherever [SuperHeroRepository] is required.
 *
 * Usage:
 * To retrieve an instance of [SuperHeroRepository], you can use Koin's
 * `get<SuperHeroRepository>()` method in any component that has access to Koin.
 *
 * Example:
 * ```
 * val repository: SuperHeroRepository = get()
 * or
 * val repository: SuperHeroRepository by inject()
 * ```
 *
 * @author Prashant Singh
 * @since 11 Aug 2024
 */
val koinModule = module {
    single<SuperHeroRepository> {
        SuperHeroRepositoryImpl()
    }
}
