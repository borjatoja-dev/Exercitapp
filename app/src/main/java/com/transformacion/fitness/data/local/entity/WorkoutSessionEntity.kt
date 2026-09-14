package com.transformacion.fitness.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entidade Room para rexistrar cada sesión de adestramento completada.
 * Permite facer seguimento da evolución e estatísticas.
 */
@Entity(tableName = "workout_sessions")
data class WorkoutSessionEntity(
    @PrimaryKey(autoGenerate = true) val sessionId: Long = 0,
    val date: Long,             // Timestamp de finalización
    val durationSeconds: Int,   // Duración real en segundos
    val exercisesCompleted: Int,// Número de exercicios completados
    val roundsCompleted: Int,   // Roldas completadas
    val perceivedEffort: Int? = null, // RPE 1-10 (opcional)
    val notes: String? = null   // Notas do usuario
)
