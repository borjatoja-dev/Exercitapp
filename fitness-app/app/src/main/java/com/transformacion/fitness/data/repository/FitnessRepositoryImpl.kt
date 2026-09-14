package com.transformacion.fitness.data.repository

import com.transformacion.fitness.data.local.dao.ExerciseDao
import com.transformacion.fitness.data.local.dao.UserProgressDao
import com.transformacion.fitness.data.local.dao.WorkoutSessionDao
import com.transformacion.fitness.data.local.entity.ExerciseEntity
import com.transformacion.fitness.data.local.entity.UserProgressEntity
import com.transformacion.fitness.data.local.entity.WorkoutSessionEntity
import com.transformacion.fitness.domain.model.*
import com.transformacion.fitness.domain.repository.FitnessRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Implementación concreta do FitnessRepository.
 * 
 * Esta clase actúa como intermediaria entre a capa de dominio (pura)
 * e a capa de datos (Room, entidades). Realiza as conversións necesarias
 * entre Entity e modelos de dominio.
 * 
 * @property exerciseDao DAO para operacións con exercicios
 * @property workoutSessionDao DAO para operacións con sesións
 * @property userProgressDao DAO para operacións co progreso
 */
class FitnessRepositoryImpl(
    private val exerciseDao: ExerciseDao,
    private val workoutSessionDao: WorkoutSessionDao,
    private val userProgressDao: UserProgressDao
) : FitnessRepository {
    
    // ========== EXERCICIOS ==========
    
    override fun getAllExercises(): Flow<List<Exercise>> {
        return exerciseDao.getAllExercises().map { entities ->
            entities.map { it.toDomain() }
        }
    }
    
    override fun getExerciseById(id: Int): Flow<Exercise?> {
        return exerciseDao.getExerciseById(id).map { entity ->
            entity?.toDomain()
        }
    }
    
    
    // ========== SESIÓNS ==========
    
    override suspend fun saveWorkoutSession(session: WorkoutSession): Long {
        val entity = session.toEntity()
        return workoutSessionDao.insertSession(entity)
    }
    
    override fun getAllSessions(): Flow<List<WorkoutSession>> {
        return workoutSessionDao.getAllSessions().map { entities ->
            entities.map { it.toDomain() }
        }
    }
    
    override fun getRecentSessions(limit: Int): Flow<List<WorkoutSession>> {
        return workoutSessionDao.getRecentSessions(limit).map { entities ->
            entities.map { it.toDomain() }
        }
    }
    
    
    // ========== PROGRESO ==========
    
    override fun getUserProgress(): Flow<UserProgress> {
        return userProgressDao.getUserProgress().map { entity ->
            entity.toDomain()
        }
    }
    
    override suspend fun updateUserProgress(progress: UserProgress) {
        val entity = progress.toEntity()
        userProgressDao.updateUserProgress(entity)
    }
    
    override suspend fun initializeUserProgress() {
        userProgressDao.initializeProgress()
    }
    
    
    // ========== MEDALLAS ==========
    
    override fun getAllMedals(): Flow<List<Medal>> {
        return getUserProgress().map { progress ->
            MedalFactory.getAllMedals().map { medal ->
                medal.copy(isUnlocked = medal.checkUnlock(progress))
            }
        }
    }
    
    override fun getUnlockedMedals(): Flow<List<Medal>> {
        return getAllMedals().map { medals ->
            medals.filter { it.isUnlocked }
        }
    }
    
    
    // ========== CONVERSIÓNS (Extensions) ==========
    
    /**
     * Converte ExerciseEntity a Exercise (dominio).
     */
    private fun ExerciseEntity.toDomain(): Exercise {
        return Exercise(
            id = id,
            name = name,
            description = description,
            emoji = emoji,
            category = ExerciseCategory.valueOf(category.uppercase()),
            difficulty = difficulty,
            isBodyweight = isBodyweight
        )
    }
    
    /**
     * Converte WorkoutSessionEntity a WorkoutSession (dominio).
     */
    private fun WorkoutSessionEntity.toDomain(): WorkoutSession {
        return WorkoutSession(
            sessionId = sessionId,
            date = date,
            durationSeconds = durationSeconds,
            exercisesCompleted = exercisesCompleted,
            roundsCompleted = roundsCompleted,
            perceivedEffort = perceivedEffort,
            notes = notes
        )
    }
    
    /**
     * Converte WorkoutSession a WorkoutSessionEntity.
     */
    private fun WorkoutSession.toEntity(): WorkoutSessionEntity {
        return WorkoutSessionEntity(
            sessionId = sessionId,
            date = date,
            durationSeconds = durationSeconds,
            exercisesCompleted = exercisesCompleted,
            roundsCompleted = roundsCompleted,
            perceivedEffort = perceivedEffort,
            notes = notes
        )
    }
    
    /**
     * Converte UserProgressEntity a UserProgress (dominio).
     */
    private fun UserProgressEntity.toDomain(): UserProgress {
        // Parsear o JSON de medallas
        val medalsList = try {
            // Simple parsing de JSON array de strings ["id1", "id2"]
            unlockedMedals
                .removePrefix("[")
                .removeSuffix("]")
                .split(",")
                .map { it.trim().removeSurrounding("\"") }
                .filter { it.isNotEmpty() }
        } catch (e: Exception) {
            emptyList()
        }
        
        return UserProgress(
            totalSessions = totalSessions,
            totalMinutes = totalMinutes,
            currentStreak = currentStreak,
            longestStreak = longestStreak,
            lastWorkoutDate = lastWorkoutDate,
            level = level,
            unlockedMedals = medalsList
        )
    }
    
    /**
     * Converte UserProgress a UserProgressEntity.
     */
    private fun UserProgress.toEntity(): UserProgressEntity {
        // Serializar lista de medallas como JSON simple
        val medalsJson = unlockedMedals.joinToString(",", "[", "]") { "\"$it\"" }
        
        return UserProgressEntity(
            id = 1,
            totalSessions = totalSessions,
            totalMinutes = totalMinutes,
            currentStreak = currentStreak,
            longestStreak = longestStreak,
            lastWorkoutDate = lastWorkoutDate,
            level = level,
            unlockedMedals = medalsJson
        )
    }
}
