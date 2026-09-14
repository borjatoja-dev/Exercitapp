package com.transformacion.fitness.ui.screens.home

import androidx.compose.runtime.Immutable

@Immutable
data class HomeUiState(
    val currentDay: Int = 1,
    val totalSessions: Int = 0,
    val totalMinutes: Int = 0,
    val isLoading: Boolean = true,
    val error: String? = null
)
