package com.transformacion.fitness.domain.model

/**
 * Modelo de dominio que representa unha sesión de adestramento completada.
 * 
 * @property sessionId Identificador único da sesión
 * @property date Timestamp de cando rematou
 * @property durationSeconds Duración total en segundos
 * @property exercisesCompleted Exercicios completados nesta sesión
 * @property roundsCompleted Roldas completadas
 * @property perceivedEffort Esforzo percibido (RPE 1-10), nullable
 * @property notes Notas opcionais
 */
data class WorkoutSession(
    val sessionId: Long,
    val date: Long,
    val durationSeconds: Int,
    val exercisesCompleted: Int,
    val roundsCompleted: Int,
    val perceivedEffort: Int? = null,
    val notes: String? = null
) {
    /**
     * Calcula a duración en minutos (redondeado).
     */
    val durationMinutes: Int get() = durationSeconds / 60
}
