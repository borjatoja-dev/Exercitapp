package com.transformacion.fitness.domain.model

/**
 * Modelo de dominio para unha medalla do sistema de gamificación.
 */
data class Medal(
    val id: String,
    val name: String,
    val emoji: String,
    val description: String,
    val requirement: (UserProgress) -> Boolean,
    val isUnlocked: Boolean = false
)
