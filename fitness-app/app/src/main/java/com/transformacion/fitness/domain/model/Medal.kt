package com.transformacion.fitness.domain.model

/**
 * Modelo de dominio que representa unha medalla ou logro.
 * 
 * @property id Identificador único da medalla
 * @property name Nome descritivo en galego
 * @property emoji Icono representativo
 * @property description Explicación do requisito
 * @property requirement Función que determina se está desbloqueada
 * @property isUnlocked Se o usuario xa a desbloqueou
 */
data class Medal(
    val id: String,
    val name: String,
    val emoji: String,
    val description: String,
    val requirement: (UserProgress) -> Boolean,
    val isUnlocked: Boolean = false
) {
    /**
     * Verifica se esta medalla debería estar desbloqueada segundo o progreso.
     */
    fun checkUnlock(progress: UserProgress): Boolean = requirement(progress)
}

/**
 * Factory para crear as medallas predefinidas da aplicación.
 * Estas son as medallas baseadas no prototipo orixinal.
 */
object MedalFactory {
    
    /**
     * Devolve a lista completa de medallas dispoñibles.
     */
    fun getAllMedals(): List<Medal> = listOf(
        Medal(
            id = "first_time",
            name = "Primeira vez",
            emoji = "🥉",
            description = "Completa a túa primeira sesión",
            requirement = { progress -> progress.totalSessions >= 1 }
        ),
        Medal(
            id = "three_days",
            name = "Tres días",
            emoji = "🥈",
            description = "Completa 3 sesións",
            requirement = { progress -> progress.totalSessions >= 3 }
        ),
        Medal(
            id = "one_week",
            name = "Unha semana",
            emoji = "🥇",
            description = "Completa 7 sesións",
            requirement = { progress -> progress.totalSessions >= 7 }
        ),
        Medal(
            id = "two_weeks",
            name = "Dúas semanas",
            emoji = "🏆",
            description = "Completa 14 sesións",
            requirement = { progress -> progress.totalSessions >= 14 }
        ),
        Medal(
            id = "one_month",
            name = "Un mes",
            emoji = "💎",
            description = "Completa 30 sesións",
            requirement = { progress -> progress.totalSessions >= 30 }
        ),
        Medal(
            id = "marathon",
            name = "Maratón",
            emoji = "⏱️",
            description = "Acumula 300 minutos totais",
            requirement = { progress -> progress.totalMinutes >= 300 }
        )
    )
    
    /**
     * Obten as medallas desbloqueadas para un progreso dado.
     */
    fun getUnlockedMedals(progress: UserProgress): List<Medal> {
        return getAllMedals().filter { it.checkUnlock(progress) }
    }
    
    /**
     * Obten a última medalla desbloqueada (para mostrar ao finalizar).
     */
    fun getLastUnlockedMedal(progress: UserProgress): Medal? {
        val unlocked = getUnlockedMedals(progress)
        return unlocked.lastOrNull()
    }
}
