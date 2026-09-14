package com.transformacion.fitness.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.transformacion.fitness.domain.repository.FitnessRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: FitnessRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        loadProgress()
    }

    private fun loadProgress() {
        viewModelScope.launch {
            try {
                repository.getUserProgress().collect { progress ->
                    _uiState.value = HomeUiState(
                        currentDay = progress.totalSessions + 1,
                        totalSessions = progress.totalSessions,
                        totalMinutes = progress.totalMinutes,
                        isLoading = false,
                        error = null
                    )
                }
            } catch (e: Exception) {
                _uiState.value = HomeUiState(
                    isLoading = false,
                    error = "Error ao cargar progreso: ${e.message}"
                )
            }
        }
    }

    fun onStartWorkout() {
        // Navegación manexada pola pantalla
    }

    fun onViewStats() {
        // Navegación manexada pola pantalla
    }
}
