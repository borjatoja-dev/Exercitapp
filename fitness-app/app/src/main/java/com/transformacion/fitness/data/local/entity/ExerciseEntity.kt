package com.transformacion.fitness.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entidade que representa un exercicio no catálogo.
 * 
 * Esta táboa contén todos os exercicios dispoñibles para os adestramentos.
 * Os datos iniciais seméntanse na primeira execución da app.
 * 
 * @property id Identificador único do exercicio
 * @property name Nome do exercicio en galego
 * @property description Instrucións detalladas de execución (pode conter HTML básico)
 * @property emoji Icono representativo do exercicio
 * @property category Categoría do exercicio (empuxe, tracción, pernas, core)
 * @property difficulty Nivel de dificultade (1-5, sendo 1 o máis fácil)
 * @property isBodyweight True se o exercicio usa só o peso corporal
 * @property createdAt Timestamp de creación do rexistro
 */
@Entity(tableName = "exercises")
data class ExerciseEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val description: String,
    val emoji: String,
    val category: String,
    val difficulty: Int,
    val isBodyweight: Boolean,
    val createdAt: Long = System.currentTimeMillis()
)
