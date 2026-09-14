package com.transformacion.fitness.di

import android.content.Context
import com.transformacion.fitness.data.local.AppDatabase
import com.transformacion.fitness.data.local.DatabaseSeeder
import com.transformacion.fitness.data.repository.FitnessRepositoryImpl
import com.transformacion.fitness.domain.repository.FitnessRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Contedor de dependencias manual para a aplicación.
 *
 * Esta clase proporciona todas as dependencias necesarias
 * sen usar frameworks pesados como Hilt ou Dagger.
 * É sinxelo, explícito e fácil de manter.
 */
class Container private constructor(private val context: Context) {

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

    companion object {
        @Volatile
        private var INSTANCE: Container? = null

        val instance: Container
            get() = INSTANCE ?: throw IllegalStateException(
                "Container non inicializado. Chama a Container.init() en FitnessApp.onCreate()"
            )

        fun init(context: Context): Container {
            return INSTANCE ?: synchronized(this) {
                val container = Container(context.applicationContext)
                container.initialize()
                INSTANCE = container
                container
            }
        }

        val repository: FitnessRepository
            get() = instance.repository
    }

    private fun initialize() {
        databaseSeeder.seedDatabaseIfNeeded()

        CoroutineScope(Dispatchers.IO).launch {
            try {
                val progress = userProgressDao.getById(1)
                if (progress == null) {
                    userProgressDao.insert(
                        com.transformacion.fitness.data.local.entity.UserProgressEntity(
                            id = 1,
                            totalSessions = 0,
                            totalMinutes = 0,
                            currentStreak = 0,
                            longestStreak = 0,
                            lastWorkoutDate = null,
                            level = 1,
                            unlockedMedals = "[]"
                        )
                    )
                }
            } catch (e: Exception) {
                // Ignorar erros na inicialización
            }
        }
    }
}
