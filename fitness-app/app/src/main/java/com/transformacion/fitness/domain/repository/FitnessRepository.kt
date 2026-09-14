package com.transformacion.fitness.domain.repository

import com.transformacion.fitness.domain.model.Exercise
import com.transformacion.fitness.domain.model.Medal
import com.transformacion.fitness.domain.model.UserProgress
import com.transformacion.fitness.domain.model.WorkoutSession
import kotlinx.coroutines.flow.Flow

/**
 * Interface do repositorio que define as operacións dispoñibles para a capa de negocio.
 * 
 * Esta interface actúa como contrato entre o dominio e os datos.
 * A implementación concreta (FitnessRepositoryImpl) está na capa de datos.
 */
interface FitnessRepository {
    
    // ========== EXERCICIOS ==========
    
    /**
     * Obten todos os exercicios do catálogo.
     * @return Flow que emite a lista de exercicios cando cambia a BD
     */
    fun getAllExercises(): Flow<List<Exercise>>
    
    /**
     * Obten un exercicio específico polo seu ID.
     * @param id O ID do exercicio
     * @return Flow que emite o exercicio ou null
     */
    fun getExerciseById(id: Int): Flow<Exercise?>
    
    
    // ========== SESIÓNS ==========
    
    /**
     * Garda unha nova sesión completada.
     * @param session A sesión a gardar
     * @return O ID da sesión gardada
     */
    suspend fun saveWorkoutSession(session: WorkoutSession): Long
    
    /**
     * Obten o historial completo de sesións.
     * @return Flow coa lista de sesións ordenadas por data
     */
    fun getAllSessions(): Flow<List<WorkoutSession>>
    
    /**
     * Obten as últimas sesións realizadas.
     * @param limit Número máximo de sesións a devolver
     * @return Flow coa lista de sesións recentes
     */
    fun getRecentSessions(limit: Int = 10): Flow<List<WorkoutSession>>
    
    
    // ========== PROGRESO ==========
    
    /**
     * Obten o progreso actual do usuario.
     * @return Flow que emite o progreso cando se actualiza
     */
    fun getUserProgress(): Flow<UserProgress>
    
    /**
     * Actualiza o progreso do usuario despois dunha sesión.
     * @param progress O novo progreso a gardar
     */
    suspend fun updateUserProgress(progress: UserProgress)
    
    /**
     * Inicializa o progreso con valores por defecto.
     * Chámase na primeira execución da app.
     */
    suspend fun initializeUserProgress()
    
    
    // ========== MEDALLAS ==========
    
    /**
     * Obten todas as medallas dispoñibles co seu estado actual.
     * @return Lista de medallas co flag isUnlocked actualizado
     */
    fun getAllMedals(): Flow<List<Medal>>
    
    /**
     * Obten só as medallas desbloqueadas.
     * @return Flow coa lista de medallas desbloqueadas
     */
    fun getUnlockedMedals(): Flow<List<Medal>>
}
