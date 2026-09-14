package com.transformacion.fitness.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entidade que representa unha sesión de adestramento completada.
 * 
 * Esta táboa garda o historial de todos os adestramentos realizados polo usuario.
 * Cada rexistro corresponde a unha sesión completa (as 3 roldas).
 * 
 * @property sessionId Identificador único autoxerado da sesión
 * @property date Timestamp de cando rematou a sesión
 * @property durationSeconds Duración total real en segundos
 * @property exercisesCompleted Número total de exercicios completados
 * @property roundsCompleted Número de roldas completadas (normalmente 3)
 * @property perceivedEffort Esforzo percibido (RPE 1-10), opcional
 * @property notes Notas opcionais do usuario sobre a sesión
 */
@Entity(tableName = "workout_sessions")
data class WorkoutSessionEntity(
    @PrimaryKey(autoGenerate = true) val sessionId: Long = 0,
    val date: Long,
    val durationSeconds: Int,
    val exercisesCompleted: Int,
    val roundsCompleted: Int,
    val perceivedEffort: Int? = null,
    val notes: String? = null
)
