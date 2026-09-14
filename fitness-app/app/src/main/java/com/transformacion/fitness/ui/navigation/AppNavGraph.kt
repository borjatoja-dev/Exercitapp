package com.transformacion.fitness.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.transformacion.fitness.ui.screens.countdown.CountdownScreen
import com.transformacion.fitness.ui.screens.finish.FinishScreen
import com.transformacion.fitness.ui.screens.home.HomeScreen
import com.transformacion.fitness.ui.screens.rest.RestScreen
import com.transformacion.fitness.ui.screens.stats.StatsScreen
import com.transformacion.fitness.ui.screens.workout.WorkoutScreen

@Composable
fun AppNavGraph(
    navController: NavHostController,
    startDestination: String = Screen.Home.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Screen.Home.route) {
            HomeScreen(
                onStartWorkout = {
                    navController.navigate(Screen.Countdown.route)
                },
                onViewStats = {
                    navController.navigate(Screen.Stats.route)
                }
            )
        }
        
        composable(Screen.Countdown.route) {
            CountdownScreen(
                onComplete = {
                    navController.navigate(Screen.Workout.route) {
                        popUpTo(Screen.Countdown.route) { inclusive = true }
                    }
                },
                onBackToHome = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Countdown.route) { inclusive = true }
                    }
                }
            )
        }
        
        composable(
            route = "${Screen.Workout.route}/{exerciseIndex}/{round}",
            arguments = listOf(
                navArgument("exerciseIndex") { type = NavType.IntType },
                navArgument("round") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val exerciseIndex = backStackEntry.arguments?.getInt("exerciseIndex") ?: 0
            val round = backStackEntry.arguments?.getInt("round") ?: 1
            
            WorkoutScreen(
                exerciseIndex = exerciseIndex,
                currentRound = round,
                onComplete = { nextExerciseIndex, nextRound ->
                    if (nextExerciseIndex == -1) {
                        // Rematou o adestramento
                        navController.navigate(Screen.Finish.route) {
                            popUpTo(Screen.Workout.route) { inclusive = true }
                        }
                    } else {
                        // Ir a descanso
                        navController.navigate("${Screen.Rest.route}/$nextExerciseIndex/$nextRound") {
                            popUpTo(Screen.Workout.route) { inclusive = true }
                        }
                    }
                },
                onBackToHome = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Workout.route) { inclusive = true }
                    }
                }
            )
        }
        
        composable(
            route = "${Screen.Rest.route}/{nextExerciseIndex}/{nextRound}",
            arguments = listOf(
                navArgument("nextExerciseIndex") { type = NavType.IntType },
                navArgument("nextRound") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val nextExerciseIndex = backStackEntry.arguments?.getInt("nextExerciseIndex") ?: 0
            val nextRound = backStackEntry.arguments?.getInt("nextRound") ?: 1
            
            RestScreen(
                nextExerciseIndex = nextExerciseIndex,
                nextRound = nextRound,
                onComplete = {
                    navController.navigate("${Screen.Workout.route}/$nextExerciseIndex/$nextRound") {
                        popUpTo(Screen.Rest.route) { inclusive = true }
                    }
                },
                onBackToHome = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Rest.route) { inclusive = true }
                    }
                }
            )
        }
        
        composable(Screen.Finish.route) {
            FinishScreen(
                onSaveAndReturn = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Finish.route) { inclusive = true }
                    }
                }
            )
        }
        
        composable(Screen.Stats.route) {
            StatsScreen(
                onBackToHome = {
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Stats.route) { inclusive = true }
                    }
                }
            )
        }
    }
}
