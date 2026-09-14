package com.transformacion.fitness.domain.model

/**
 * Modelo de dominio que representa o progreso acumulado do usuario.
 * 
 * @property totalSessions Sesións completadas
 * @property totalMinutes Minutos totais acumulados
 * @property currentStreak Racha actual de días consecutivos
 * @property longestStreak Mellor racha histórica
 * @property lastWorkoutDate Data do último adestramento
 * @property level Nivel de experiencia (1-10)
 * @property unlockedMedals Lista de IDs de medallas desbloqueadas
 */
data class UserProgress(
    val totalSessions: Int = 0,
    val totalMinutes: Int = 0,
    val currentStreak: Int = 0,
    val longestStreak: Int = 0,
    val lastWorkoutDate: Long? = null,
    val level: Int = 1,
    val unlockedMedals: List<String> = emptyList()
) {
    /**
     * Calcula se o usuario ten unha medalla específica desbloqueada.
     */
    fun hasMedal(medalId: String): Boolean = unlockedMedals.contains(medalId)
    
    /**
     * Determina o nivel baseado nos minutos totais.
     * Nivel 1: 0-60 min, Nivel 2: 61-180 min, etc.
     */
    fun calculateLevel(): Int {
        return when {
            totalMinutes < 60 -> 1
            totalMinutes < 180 -> 2
            totalMinutes < 300 -> 3
            totalMinutes < 600 -> 4
            totalMinutes < 1200 -> 5
            else -> 6 + (totalMinutes - 1200) / 300
        }.coerceIn(1, 10)
    }
}
