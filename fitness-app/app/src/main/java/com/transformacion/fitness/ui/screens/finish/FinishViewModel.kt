package com.transformacion.fitness.ui.screens.finish

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.transformacion.fitness.domain.model.Medal
import com.transformacion.fitness.domain.repository.FitnessRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class FinishViewModel(
    private val repository: FitnessRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow(FinishUiState())
    val uiState: StateFlow<FinishUiState> = _uiState.asStateFlow()

    private val motivationalPhrases = listOf(
        "🚀 Levántaste o cu. Iso é máis do que fai o 80% da xente.",
        "⚡ Un paso cada día. Hoxe gañáchelle á preguiza.",
        "🏆 Non se trata de ser o mellor, trátase de ser mellor que onte.",
        "🧠 O teu corpo escoita todo o que di a túa mente. Hoxe dixeches 'si'.",
        "💥 15 minutos que cambiarán as túas próximas 24 horas.",
        "🌱 A constancia non é perfección. É volver a intentalo."
    )

    init {
        calculateResults()
    }

    private fun calculateResults() {
        viewModelScope.launch {
            try {
                // Obter progreso actualizado (despois de gardar sesión)
                repository.getUserProgress().collect { progress ->
                    val earnedMedal = getEarnedMedal(progress.totalSessions)
                    val phrase = motivationalPhrases.random()
                    val progressPercent = (progress.totalMinutes.toFloat() / 300f).coerceIn(0f, 1f)

                    _uiState.value = FinishUiState(
                        totalSessions = progress.totalSessions,
                        totalMinutes = progress.totalMinutes,
                        earnedMedalEmoji = earnedMedal.emoji,
                        motivationalPhrase = phrase,
                        progressPercent = progressPercent,
                        isSaving = false
                    )
                }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    error = "Error ao cargar resultados: ${e.message}"
                )
            }
        }
    }

    private fun getEarnedMedal(sessions: Int): Medal {
        val medals = MedalFactory.getAllMedals()
        return medals
            .filter { it.isUnlocked(sessions) }
            .lastOrNull()
            ?: Medal(
                id = "starter",
                name = "Inicio",
                emoji = "🌟",
                requirement = { true }
            )
    }

    fun onSaveAndReturn() {
        // A sesión xa debería estar gardada antes de chegar aquí
        // Só necesitamos navegar de volta
    }
}
