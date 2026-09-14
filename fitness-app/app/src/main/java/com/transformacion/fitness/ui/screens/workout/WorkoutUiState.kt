package com.transformacion.fitness.ui.screens.workout

import androidx.compose.runtime.Immutable

@Immutable
data class WorkoutUiState(
    val exerciseIndex: Int = 0,
    val currentRound: Int = 1,
    val timeRemaining: Int = 45,
    val isRunning: Boolean = false,
    val totalRounds: Int = 3,
    val totalExercises: Int = 5,
    val nextExerciseName: String = "",
    val nextRound: Int = 1,
    val error: String? = null
)
