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
 * Base de datos Room principal da aplicación.
 * Contén 3 táboas: exercises, workout_sessions, user_progress
 * 
 * Versión 1: Esquema inicial
 */
@Database(
    entities = [
        ExerciseEntity::class,
        WorkoutSessionEntity::class,
        UserProgressEntity::class
    ],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    
    abstract fun exerciseDao(): ExerciseDao
    abstract fun workoutSessionDao(): WorkoutSessionDao
    abstract fun userProgressDao(): UserProgressDao
    
    companion object {
        @Volatile private var INSTANCE: AppDatabase? = null
        
        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "fitness_database"
                )
                .fallbackToDestructiveMigration() // Para desenvolvemento inicial
                .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
