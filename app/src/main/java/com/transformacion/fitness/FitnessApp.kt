package com.transformacion.fitness

import android.app.Application
import com.transformacion.fitness.di.Container
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * Clase Application que inicializa as dependencias globais.
 */
class FitnessApp : Application() {
    
    // Scope para corrutinas no ciclo de vida da aplicación
    val applicationScope = CoroutineScope(Dispatchers.Main + Job())
    
    // Contedor de dependencias (inicialízase lazy)
    val container: Container by lazy {
        Container.getInstance(this)
    }
    
    override fun onCreate() {
        super.onCreate()
        
        // Inicializar a base de datos en segundo plano
        applicationScope.launch(Dispatchers.IO) {
            container.repository.initializeDatabase()
        }
    }
}
