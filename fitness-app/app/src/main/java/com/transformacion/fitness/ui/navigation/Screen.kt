package com.transformacion.fitness.ui.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Countdown : Screen("countdown")
    object Workout : Screen("workout")
    object Rest : Screen("rest")
    object Finish : Screen("finish")
    object Stats : Screen("stats")
}
