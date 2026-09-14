package com.transformacion.fitness.data.local.dao

import androidx.room.*
import com.transformacion.fitness.data.local.entity.WorkoutSessionEntity
import kotlinx.coroutines.flow.Flow

/**
 * DAO para operacións de base de datos con sesións de adestramento.
 * Permite gardar, consultar e eliminar o historial de sesións.
 */
@Dao
interface WorkoutSessionDao {
    
    @Query("SELECT * FROM workout_sessions ORDER BY date DESC")
    fun getAllSessions(): Flow<List<WorkoutSessionEntity>>
    
    @Query("SELECT * FROM workout_sessions WHERE sessionId = :sessionId")
    suspend fun getSessionById(sessionId: Long): WorkoutSessionEntity?
    
    @Query("SELECT * FROM workout_sessions ORDER BY date DESC LIMIT :limit")
    fun getRecentSessions(limit: Int): Flow<List<WorkoutSessionEntity>>
    
    @Query("SELECT COUNT(*) FROM workout_sessions")
    fun getTotalSessionsCount(): Flow<Int>
    
    @Query("SELECT SUM(durationSeconds) FROM workout_sessions")
    fun getTotalDurationSeconds(): Flow<Int?>
    
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertSession(session: WorkoutSessionEntity): Long
    
    @Update
    suspend fun updateSession(session: WorkoutSessionEntity)
    
    @Delete
    suspend fun deleteSession(session: WorkoutSessionEntity)
    
    @Query("DELETE FROM workout_sessions")
    suspend fun deleteAllSessions()
}
