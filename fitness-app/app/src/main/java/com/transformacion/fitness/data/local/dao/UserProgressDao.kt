package com.transformacion.fitness.data.local.dao

import androidx.room.*
import com.transformacion.fitness.data.local.entity.UserProgressEntity
import kotlinx.coroutines.flow.Flow

/**
 * DAO para xestionar o progreso do usuario na base de datos.
 * 
 * Como só hai un rexistro (singleton), as operacións son simples.
 */
@Dao
interface UserProgressDao {
    
    /**
     * Obten o único rexistro de progreso do usuario.
     * @return Flow coa entidade de progreso
     */
    @Query("SELECT * FROM user_progress WHERE id = 1")
    fun getUserProgress(): Flow<UserProgressEntity>
    
    /**
     * Inserta ou actualiza o progreso do usuario.
     * Como é singleton, sempre usa OnConflictStrategy.REPLACE.
     * @param progress A entidade de progreso a gardar
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateUserProgress(progress: UserProgressEntity)
    
    /**
     * Inicializa o rexistro de progreso con valores por defecto.
     * Só se chama na primeira execución da app.
     */
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun initializeProgress(progress: UserProgressEntity = UserProgressEntity())
    
    /**
     * Borra o progreso do usuario.
     * Usar só para reseteos completos.
     */
    @Query("DELETE FROM user_progress")
    suspend fun deleteProgress()
}
