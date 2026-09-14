package com.transformacion.fitness.data.local.dao

import androidx.room.*
import com.transformacion.fitness.data.local.entity.ExerciseEntity
import kotlinx.coroutines.flow.Flow

/**
 * DAO (Data Access Object) para xestionar os exercicios na base de datos.
 * 
 * Proporciona operacións CRUD para a táboa 'exercises'.
 * Todas as funcións devolven Flow ou suspend functions para operacións asíncronas.
 */
@Dao
interface ExerciseDao {
    
    /**
     * Obten todos os exercicios ordenados por ID.
     * @return Flow coa lista de exercicios (emite cambios cando se actualiza a BD)
     */
    @Query("SELECT * FROM exercises ORDER BY id ASC")
    fun getAllExercises(): Flow<List<ExerciseEntity>>
    
    /**
     * Obten un exercicio polo seu ID.
     * @param exerciseId O ID do exercicio
     * @return Flow co exercicio ou null se non existe
     */
    @Query("SELECT * FROM exercises WHERE id = :exerciseId")
    fun getExerciseById(exerciseId: Int): Flow<ExerciseEntity?>
    
    /**
     * Inserta ou actualiza un exercicio.
     * Se xa existe (mesmo ID), actualízao. Se non, insírteo.
     * @param exercise O exercicio a inserir/actualizar
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExercise(exercise: ExerciseEntity)
    
    /**
     * Inserta varios exercicios dunha vez.
     * Útil para o seed inicial de datos.
     * @param exercises Lista de exercicios a inserir
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllExercises(exercises: List<ExerciseEntity>)
    
    /**
     * Borra todos os exercicios da táboa.
     * Usar con precaución, só para reseteos ou migracións.
     */
    @Query("DELETE FROM exercises")
    suspend fun deleteAllExercises()
    
    /**
     * Conta o número total de exercicios na táboa.
     * @return Número de exercicios
     */
    @Query("SELECT COUNT(*) FROM exercises")
    suspend fun getExerciseCount(): Int
}
