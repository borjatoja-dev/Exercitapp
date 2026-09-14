package com.transformacion.fitness.ui.screens.stats

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.transformacion.fitness.domain.repository.FitnessRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class StatsViewModel(
    private val repository: FitnessRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(StatsUiState())
    val uiState: StateFlow<StatsUiState> = _uiState.asStateFlow()

    init {
        loadStats()
    }

    private fun loadStats() {
        viewModelScope.launch {
            try {
                repository.getUserProgress().collect { progress ->
                    // Datos de exemplo para a gráfica semanal
                    val weeklyData = listOf(1, 0, 1, 1, 0, 1, 1)

                    _uiState.value = StatsUiState(
                        totalSessions = progress.totalSessions,
                        totalMinutes = progress.totalMinutes,
                        currentStreak = progress.currentStreak,
                        longestStreak = progress.longestStreak,
                        weeklyData = weeklyData,
                        unlockedMedals = emptyList(), // Cargar desde MedalFactory
                        recentSessions = emptyList(), // Cargar desde historial
                        isLoading = false
                    )
                }
            } catch (e: Exception) {
                _uiState.value = StatsUiState(
                    isLoading = false,
                    error = "Error ao cargar estatísticas: ${e.message}"
                )
            }
        }
    }
}
