package com.transformacion.fitness.data.local.dao

import androidx.room.*
import com.transformacion.fitness.data.local.entity.UserProgressEntity
import kotlinx.coroutines.flow.Flow

/**
 * DAO para operacións de base de datos co progreso do usuario.
 * É un singleton (sempre id = 1).
 */
@Dao
interface UserProgressDao {
    
    @Query("SELECT * FROM user_progress WHERE id = 1")
    fun getProgress(): Flow<UserProgressEntity?>
    
    @Query("SELECT * FROM user_progress WHERE id = 1")
    suspend fun getProgressOnce(): UserProgressEntity?
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(progress: UserProgressEntity)
    
    @Update
    suspend fun updateProgress(progress: UserProgressEntity)
    
    @Delete
    suspend fun deleteProgress(progress: UserProgressEntity)
}
