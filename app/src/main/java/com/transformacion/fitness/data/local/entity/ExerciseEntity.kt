package com.transformacion.fitness.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entidade Room para almacenar os exercicios do catálogo.
 * Corresponde ao array EXERCISES do prototipo HTML.
 */
@Entity(tableName = "exercises")
data class ExerciseEntity(
    @PrimaryKey val id: Int,
    val name: String,           // Nome do exercicio en galego
    val description: String,    // Instrucións detalladas (pode ter HTML simple)
    val emoji: String,          // Emoji representativo
    val category: String,       // Categoria: empuxe, traccion, pernas, core
    val difficulty: Int = 1,    // Dificultade 1-5
    val isBodyweight: Boolean = true, // Se usa só peso corporal
    val createdAt: Long = System.currentTimeMillis()
)
