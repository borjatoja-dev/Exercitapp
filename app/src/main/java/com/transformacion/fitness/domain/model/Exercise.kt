package com.transformacion.fitness.domain.model

/**
 * Modelo de dominio puro para un exercicio.
 * Sen anotacións Room, usado pola capa de negocio.
 */
data class Exercise(
    val id: Int,
    val name: String,
    val description: String,
    val emoji: String,
    val category: ExerciseCategory,
    val difficulty: Int,
    val isBodyweight: Boolean
)
