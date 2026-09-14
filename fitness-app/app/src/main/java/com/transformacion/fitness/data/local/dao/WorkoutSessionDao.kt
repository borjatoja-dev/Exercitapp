package com.transformacion.fitness.data.local.dao

import androidx.room.*
import com.transformacion.fitness.data.local.entity.WorkoutSessionEntity
import kotlinx.coroutines.flow.Flow

/**
 * DAO para xestionar as sesións de adestramento na base de datos.
 * 
 * Proporciona operacións CRUD para a táboa 'workout_sessions'.
 * Permite consultar o historial e estatísticas agregadas.
 */
@Dao
interface WorkoutSessionDao {
    
    /**
     * Inserta unha nova sesión completada.
     * @param session A sesión a gardar
     * @return O ID autoxerado da sesión
     */
    @Insert
    suspend fun insertSession(session: WorkoutSessionEntity): Long
    
    /**
     * Obten todas as sesións ordenadas por data (máis recentes primeiro).
     * @return Flow coa lista de sesións
     */
    @Query("SELECT * FROM workout_sessions ORDER BY date DESC")
    fun getAllSessions(): Flow<List<WorkoutSessionEntity>>
    
    /**
     * Obten as últimas N sesións.
     * @param limit Número máximo de sesións a devolver
     * @return Flow coa lista de sesións recentes
     */
    @Query("SELECT * FROM workout_sessions ORDER BY date DESC LIMIT :limit")
    fun getRecentSessions(limit: Int = 10): Flow<List<WorkoutSessionEntity>>
    
    /**
     * Conta o número total de sesións.
     * @return Número de sesións completadas
     */
    @Query("SELECT COUNT(*) FROM workout_sessions")
    fun getTotalSessionsCount(): Flow<Int>
    
    /**
     * Suma a duración total de todas as sesións en segundos.
     * @return Flow coa duración total en segundos
     */
    @Query("SELECT SUM(durationSeconds) FROM workout_sessions")
    fun getTotalDurationSeconds(): Flow<Int?>
    
    /**
     * Obten sesións agrupadas por semana para estatísticas.
     * Devolve o número de sesións por semana (usando timestamp).
     * @return Flow co número de sesións por semana
     */
    @Query("""
        SELECT 
            COUNT(*) as count,
            (date / 604800000) as week
        FROM workout_sessions 
        GROUP BY week 
        ORDER BY week DESC
    """)
    fun getSessionsPerWeek(): Flow<List<Map<String, Any>>>
    
    /**
     * Borra todas as sesións do historial.
     * Usar con precaución.
     */
    @Query("DELETE FROM workout_sessions")
    suspend fun deleteAllSessions()
}
