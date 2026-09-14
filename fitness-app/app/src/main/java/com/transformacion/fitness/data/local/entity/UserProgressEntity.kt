package com.transformacion.fitness.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entidade que representa o progreso acumulado do usuario.
 * 
 * Esta táboa contén un único rexistro (singleton) coas estatísticas globais.
 * Actualízase despois de cada sesión completada.
 * 
 * @property id Identificador fixo (sempre 1, é un singleton)
 * @property totalSessions Número total de sesións completadas
 * @property totalMinutes Minutos totais acumulados
 * @property currentStreak Racha actual de días consecutivos
 * @property longestStreak A racha máis longa histórica
 * @property lastWorkoutDate Timestamp do último adestramento
 * @property level Nivel de experiencia (1-10)
 * @property unlockedMedals JSON coas IDs das medallas desbloqueadas
 */
@Entity(tableName = "user_progress")
data class UserProgressEntity(
    @PrimaryKey val id: Int = 1,
    val totalSessions: Int = 0,
    val totalMinutes: Int = 0,
    val currentStreak: Int = 0,
    val longestStreak: Int = 0,
    val lastWorkoutDate: Long? = null,
    val level: Int = 1,
    val unlockedMedals: String = "[]"  // JSON array de strings
)
