package com.transformacion.fitness.ui.screens.workout

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.transformacion.fitness.domain.model.Exercise
import com.transformacion.fitness.domain.repository.FitnessRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class WorkoutViewModel(
    private val repository: FitnessRepository,
    private val exerciseIndex: Int,
    private val currentRound: Int
) : ViewModel() {

    private val _uiState = MutableStateFlow(WorkoutUiState())
    val uiState: StateFlow<WorkoutUiState> = _uiState.asStateFlow()

    private var timerJob: Job? = null
    private var exercises: List<Exercise> = emptyList()

    init {
        loadExercises()
    }

    private fun loadExercises() {
        viewModelScope.launch {
            try {
                repository.getExercises().collect { exerciseList ->
                    exercises = exerciseList
                    if (exerciseIndex < exerciseList.size) {
                        val currentExercise = exerciseList[exerciseIndex]
                        
                        // Calcular seguinte exercicio
                        var nextIdx = exerciseIndex + 1
                        var nextRound = currentRound
                        if (nextIdx >= exerciseList.size) {
                            nextIdx = 0
                            nextRound = currentRound + 1
                        }

                        val nextExerciseName = if (nextRound > 3) {
                            "Último exercicio!"
                        } else if (nextIdx < exerciseList.size) {
                            exerciseList[nextIdx].name
                        } else {
                            ""
                        }

                        _uiState.value = WorkoutUiState(
                            exerciseIndex = exerciseIndex,
                            currentRound = currentRound,
                            timeRemaining = 45,
                            isRunning = true,
                            totalRounds = 3,
                            totalExercises = exerciseList.size,
                            nextExerciseName = nextExerciseName,
                            nextRound = nextRound
                        )

                        // Iniciar temporizador
                        startTimer()
                    }
                }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    error = "Error ao cargar exercicios: ${e.message}"
                )
            }
        }
    }

    private fun startTimer() {
        timerJob?.cancel()
        timerJob = viewModelScope.launch {
            for (remaining in 44 downTo 0) {
                delay(1000)
                _uiState.value = _uiState.value.copy(timeRemaining = remaining)
                
                if (remaining <= 3) {
                    // Vibrar nos últimos 3 segundos (implementar despois)
                }
                
                if (remaining == 0) {
                    onComplete()
                }
            }
        }
    }

    fun onComplete() {
        // Calcular seguinte exercicio/rolda
        val exerciseList = exercises
        var nextIdx = exerciseIndex + 1
        var nextRound = currentRound
        
        if (nextIdx >= exerciseList.size) {
            nextIdx = 0
            nextRound = currentRound + 1
        }

        // Se rematou todas as roldas
        if (nextRound > 3 && nextIdx == 0) {
            // -1 indica que rematou o adestramento
            onCompleteWorkout(-1, 0)
        } else {
            onCompleteWorkout(nextIdx, nextRound)
        }
    }

    private fun onCompleteWorkout(nextExerciseIndex: Int, nextRound: Int) {
        timerJob?.cancel()
        // Chamar callback da pantalla
    }

    fun onBackToHome() {
        timerJob?.cancel()
    }

    override fun onCleared() {
        super.onCleared()
        timerJob?.cancel()
    }
}
