package com.transformacion.fitness.domain.model

/**
 * Modelo de dominio para unha sesión de adestramento completada.
 */
data class WorkoutSession(
    val sessionId: Long,
    val date: Long,
    val durationSeconds: Int,
    val exercisesCompleted: Int,
    val roundsCompleted: Int,
    val perceivedEffort: Int? = null,
    val notes: String? = null
)
