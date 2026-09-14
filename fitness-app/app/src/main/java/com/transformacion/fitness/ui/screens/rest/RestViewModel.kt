package com.transformacion.fitness.ui.screens.rest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.transformacion.fitness.domain.repository.FitnessRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class RestViewModel(
    private val repository: FitnessRepository,
    private val nextExerciseIndex: Int,
    private val nextRound: Int
) : ViewModel() {

    private val _uiState = MutableStateFlow(RestUiState())
    val uiState: StateFlow<RestUiState> = _uiState.asStateFlow()

    private var timerJob: Job? = null

    init {
        loadNextExercise()
    }

    private fun loadNextExercise() {
        viewModelScope.launch {
            try {
                repository.getExercises().collect { exercises ->
                    if (nextExerciseIndex < exercises.size) {
                        val nextExercise = exercises[nextExerciseIndex]
                        
                        _uiState.value = RestUiState(
                            nextExerciseIndex = nextExerciseIndex,
                            nextRound = nextRound,
                            timeRemaining = 15,
                            nextExerciseName = nextExercise.name,
                            isRunning = true
                        )

                        // Iniciar temporizador de descanso
                        startTimer()
                    }
                }
            } catch (e: Exception) {
                // Manexar erro
            }
        }
    }

    private fun startTimer() {
        timerJob?.cancel()
        timerJob = viewModelScope.launch {
            for (remaining in 14 downTo 0) {
                delay(1000)
                _uiState.value = _uiState.value.copy(timeRemaining = remaining)
                
                if (remaining <= 3) {
                    // Vibrar nos últimos 3 segundos
                }
                
                if (remaining == 0) {
                    onComplete()
                }
            }
        }
    }

    private fun onComplete() {
        timerJob?.cancel()
        // O callback manéxase na pantalla
    }

    fun onBackToHome() {
        timerJob?.cancel()
    }

    override fun onCleared() {
        super.onCleared()
        timerJob?.cancel()
    }
}
