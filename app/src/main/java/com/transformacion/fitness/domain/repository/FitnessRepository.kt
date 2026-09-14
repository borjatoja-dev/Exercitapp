package com.transformacion.fitness.domain.repository

import com.transformacion.fitness.domain.model.Exercise
import com.transformacion.fitness.domain.model.UserProgress
import com.transformacion.fitness.domain.model.WorkoutSession
import kotlinx.coroutines.flow.Flow

/**
 * Interface do repositorio que define as operacións dispoñibles para a capa de dominio.
 * A implementación concreta está na capa de datos.
 */
interface FitnessRepository {
    
    // Operacións con exercicios
    fun getExercises(): Flow<List<Exercise>>
    suspend fun getExerciseById(id: Int): Exercise?
    
    // Operacións con sesións
    fun getAllSessions(): Flow<List<WorkoutSession>>
    fun getRecentSessions(limit: Int): Flow<List<WorkoutSession>>
    fun getTotalSessionsCount(): Flow<Int>
    fun getTotalDurationMinutes(): Flow<Int>
    suspend fun saveSession(session: WorkoutSession): Long
    
    // Operacións con progreso
    fun getUserProgress(): Flow<UserProgress>
    suspend fun updateUserProgress(progress: UserProgress)
    
    // Inicialización
    suspend fun initializeDatabase()
    suspend fun isDatabaseInitialized(): Boolean
}
