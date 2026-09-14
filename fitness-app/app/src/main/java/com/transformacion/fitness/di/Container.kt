package com.transformacion.fitness.di

import android.content.Context
import com.transformacion.fitness.data.local.AppDatabase
import com.transformacion.fitness.data.local.DatabaseSeeder
import com.transformacion.fitness.data.repository.FitnessRepositoryImpl
import com.transformacion.fitness.domain.repository.FitnessRepository

/**
 * Contedor de dependencias manual para a aplicación.
 * 
 * Esta clase proporciona todas as dependencias necesarias
 * sen usar frameworks pesados como Hilt ou Dagger.
 * É sinxelo, explícito e fácil de manter.
 * 
 * @property context Contexto da aplicación
 */
class Container(private val context: Context) {
    
    // Base de datos (singleton)
    val database: AppDatabase by lazy {
        AppDatabase.getInstance(context)
    }
    
    // DAOs
    val exerciseDao by lazy { database.exerciseDao() }
    val workoutSessionDao by lazy { database.workoutSessionDao() }
    val userProgressDao by lazy { database.userProgressDao() }
    
    // Seeder
    val databaseSeeder by lazy { DatabaseSeeder(exerciseDao) }
    
    // Repositorio
    val repository: FitnessRepository by lazy {
        FitnessRepositoryImpl(
            exerciseDao = exerciseDao,
            workoutSessionDao = workoutSessionDao,
            userProgressDao = userProgressDao
        )
    }
    
    /**
     * Inicializa as dependencias da aplicación.
     * Chámase desde FitnessApp.onCreate().
     */
    fun initialize() {
        // Inicializar a base de datos con datos seed se é necesario
        databaseSeeder.seedDatabaseIfNeeded()
        
        // Inicializar o progreso do usuario se é a primeira vez
        // Isto executarase nunha coroutine interna do repositorio
        // porque require operacións suspendidas
    }
}
