package com.transformacion.fitness.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.transformacion.fitness.data.local.dao.ExerciseDao
import com.transformacion.fitness.data.local.dao.UserProgressDao
import com.transformacion.fitness.data.local.dao.WorkoutSessionDao
import com.transformacion.fitness.data.local.entity.ExerciseEntity
import com.transformacion.fitness.data.local.entity.UserProgressEntity
import com.transformacion.fitness.data.local.entity.WorkoutSessionEntity

/**
 * Base de datos principal da aplicación usando Room.
 * 
 * Contén todas as táboas necesarias para o funcionamento offline:
 * - exercises: Catálogo de exercicios
 * - workout_sessions: Historial de adestramentos
 * - user_progress: Estatísticas acumuladas do usuario
 * 
 * A base de datos créase unha soa vez e reúsase durante toda a vida da app.
 * Usa o patrón singleton para garantir unha única instancia.
 * 
 * @property exerciseDao DAO para xestionar exercicios
 * @property workoutSessionDao DAO para xestionar sesións
 * @property userProgressDao DAO para xestionar progreso
 */
@Database(
    entities = [
        ExerciseEntity::class,
        WorkoutSessionEntity::class,
        UserProgressEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    
    abstract fun exerciseDao(): ExerciseDao
    abstract fun workoutSessionDao(): WorkoutSessionDao
    abstract fun userProgressDao(): UserProgressDao
    
    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null
        
        /**
         * Obten a única instancia da base de datos.
         * Se non existe, créaa usando o contexto proporcionado.
         * 
         * @param context Contexto da aplicación
         * @return A instancia única de AppDatabase
         */
        fun getInstance(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "fitness_database"
                )
                // Non usamos createFromAsset, inicializamos programaticamente
                .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
