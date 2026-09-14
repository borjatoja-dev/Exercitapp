package com.transformacion.fitness.data.local.dao

import androidx.room.*
import com.transformacion.fitness.data.local.entity.ExerciseEntity
import kotlinx.coroutines.flow.Flow

/**
 * DAO para operacións de base de datos con exercicios.
 * Permite consultar, inserir e actualizar o catálogo de exercicios.
 */
@Dao
interface ExerciseDao {
    
    @Query("SELECT * FROM exercises ORDER BY id ASC")
    fun getAllExercises(): Flow<List<ExerciseEntity>>
    
    @Query("SELECT * FROM exercises WHERE id = :exerciseId")
    suspend fun getExerciseById(exerciseId: Int): ExerciseEntity?
    
    @Query("SELECT COUNT(*) FROM exercises")
    fun getExerciseCount(): Flow<Int>
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(exercises: List<ExerciseEntity>)
    
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExercise(exercise: ExerciseEntity)
    
    @Update
    suspend fun updateExercise(exercise: ExerciseEntity)
    
    @Delete
    suspend fun deleteExercise(exercise: ExerciseEntity)
}
