package com.transformacion.fitness.ui.screens.finish

import androidx.compose.runtime.Immutable

@Immutable
data class FinishUiState(
    val totalSessions: Int = 0,
    val totalMinutes: Int = 0,
    val earnedMedalEmoji: String = "🏅",
    val motivationalPhrase: String = "",
    val progressPercent: Float = 0f,
    val isSaving: Boolean = false,
    val error: String? = null
)
