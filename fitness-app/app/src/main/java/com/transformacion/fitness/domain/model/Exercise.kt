package com.transformacion.fitness.domain.model

/**
 * Modelo de dominio que representa un exercicio.
 * 
 * Esta clase é pura (sen anotacións Room) e úsase na capa de negocio.
 * As conversións desde ExerciseEntity fanse no repositorio.
 * 
 * @property id Identificador único
 * @property name Nome en galego
 * @property description Instrucións de execución
 * @property emoji Icono representativo
 * @property category Categoría do exercicio
 * @property difficulty Dificultade do 1 ao 5
 * @property isBodyweight Se usa só peso corporal
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

/**
 * Categorías de exercicios para clasificación e filtrado.
 */
enum class ExerciseCategory {
    EMPUXE,      // Flexións, fondos...
    TRACCION,    // Remos, dominadas...
    PERNAS,      // Sentadillas, zancadas...
    CORE,        // Planchas, abdominais...
    CARDIO       // Burpees, jumping jacks...
}
