package com.transformacion.fitness.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entidade Room para almacenar o progreso acumulado do usuario.
 * É un singleton (sempre id = 1) que se actualiza tras cada sesión.
 */
@Entity(tableName = "user_progress")
data class UserProgressEntity(
    @PrimaryKey val id: Int = 1,  // Singleton
    val totalSessions: Int = 0,
    val totalMinutes: Int = 0,
    val currentStreak: Int = 0,   // Días consecutivos
    val longestStreak: Int = 0,
    val lastWorkoutDate: Long? = null, // Timestamp do último adestramento
    val level: Int = 1,           // Nivel de experiencia 1-10
    val unlockedMedals: String = "[]" // JSON cos IDs das medallas desbloqueadas
)
