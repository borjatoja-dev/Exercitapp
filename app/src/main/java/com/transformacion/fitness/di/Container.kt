package com.transformacion.fitness.di

import android.content.Context
import com.transformacion.fitness.data.local.AppDatabase
import com.transformacion.fitness.data.repository.FitnessRepositoryImpl
import com.transformacion.fitness.domain.repository.FitnessRepository

/**
 * Contedor de dependencias manual (sen frameworks como Hilt/Dagger).
 * Simple e fácil de entender para un desenvolvemento inicial.
 */
class Container(private val context: Context) {
    
    // Base de datos
    val database: AppDatabase by lazy {
        AppDatabase.getDatabase(context)
    }
    
    // Repositorio
    val repository: FitnessRepository by lazy {
        FitnessRepositoryImpl(context)
    }
    
    companion object {
        @Volatile private var INSTANCE: Container? = null
        
        fun getInstance(context: Context): Container {
            return INSTANCE ?: synchronized(this) {
                val instance = Container(context.applicationContext)
                INSTANCE = instance
                instance
            }
        }
    }
}
