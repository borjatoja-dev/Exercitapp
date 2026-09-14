package com.transformacion.fitness.ui.screens.stats

import androidx.compose.runtime.Immutable

@Immutable
data class StatsUiState(
    val totalSessions: Int = 0,
    val totalMinutes: Int = 0,
    val currentStreak: Int = 0,
    val longestStreak: Int = 0,
    val weeklyData: List<Int> = emptyList(), // 7 días
    val unlockedMedals: List<String> = emptyList(),
    val recentSessions: List<String> = emptyList(),
    val isLoading: Boolean = true,
    val error: String? = null
)
