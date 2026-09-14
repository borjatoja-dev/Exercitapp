package com.transformacion.fitness.ui.screens.rest

import androidx.compose.runtime.Immutable

@Immutable
data class RestUiState(
    val nextExerciseIndex: Int = 0,
    val nextRound: Int = 1,
    val timeRemaining: Int = 15,
    val nextExerciseName: String = "",
    val isRunning: Boolean = false
)
