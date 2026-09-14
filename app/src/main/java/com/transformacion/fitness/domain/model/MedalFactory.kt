package com.transformacion.fitness.domain.model

/**
 * Factory para crear as medallas dispoñibles no sistema.
 * Inclúe as 4 medallas do prototipo + 2 extras para motivación a longo prazo.
 */
object MedalFactory {
    
    /**
     * Devolve a lista completa de medallas coas súas condicións de desbloqueo.
     */
    fun getAllMedals(): List<Medal> {
        return listOf(
            Medal(
                id = "medal_1_session",
                name = "Primeira Vez",
                emoji = "🥉",
                description = "Completa a túa primeira sesión",
                requirement = { progress -> progress.totalSessions >= 1 }
            ),
            Medal(
                id = "medal_3_sessions",
                name = "Tres Días",
                emoji = "🥈",
                description = "Completa 3 sesións",
                requirement = { progress -> progress.totalSessions >= 3 }
            ),
            Medal(
                id = "medal_7_sessions",
                name = "Unha Semana",
                emoji = "🥇",
                description = "Completa 7 sesións (1 semana)",
                requirement = { progress -> progress.totalSessions >= 7 }
            ),
            Medal(
                id = "medal_14_sessions",
                name = "Dúas Semanas",
                emoji = "🏆",
                description = "Completa 14 sesións (2 semanas)",
                requirement = { progress -> progress.totalSessions >= 14 }
            ),
            Medal(
                id = "medal_30_sessions",
                name = "Un Mes",
                emoji = "💎",
                description = "Completa 30 sesións (1 mes)",
                requirement = { progress -> progress.totalSessions >= 30 }
            ),
            Medal(
                id = "medal_60_sessions",
                name = "Dous Meses",
                emoji = "👑",
                description = "Completa 60 sesións (2 meses)",
                requirement = { progress -> progress.totalSessions >= 60 }
            )
        )
    }
    
    /**
     * Calcula cales medallas están desbloqueadas para un progreso dado.
     */
    fun getUnlockedMedals(progress: UserProgress): List<Medal> {
        return getAllMedals().filter { it.requirement(progress) }
    }
    
    /**
     * Obten a última medalla desbloqueada (para mostrar ao finalizar unha sesión).
     */
    fun getLastUnlockedMedal(progress: UserProgress): Medal? {
        val unlocked = getUnlockedMedals(progress)
        return unlocked.lastOrNull()
    }
}
