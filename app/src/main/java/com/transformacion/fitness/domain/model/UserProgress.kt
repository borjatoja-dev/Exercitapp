package com.transformacion.fitness.domain.model

/**
 * Modelo de dominio para o progreso acumulado do usuario.
 */
data class UserProgress(
    val totalSessions: Int = 0,
    val totalMinutes: Int = 0,
    val currentStreak: Int = 0,
    val longestStreak: Int = 0,
    val lastWorkoutDate: Long? = null,
    val level: Int = 1,
    val unlockedMedalIds: List<String> = emptyList()
) {
    /**
     * Calcula o día actual baseado nas sesións completadas.
     */
    fun getCurrentDay(): Int = totalSessions + 1
    
    /**
     * Verifica se hai racha activa (último adestramento fai menos de 24h).
     */
    fun hasActiveStreak(): Boolean {
        val now = System.currentTimeMillis()
        val oneDayMs = 24 * 60 * 60 * 1000L
        return lastWorkoutDate?.let { now - it < oneDayMs } ?: false
    }
}
