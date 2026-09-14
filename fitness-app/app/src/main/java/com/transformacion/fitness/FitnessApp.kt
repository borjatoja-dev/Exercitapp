package com.transformacion.fitness

import android.app.Application
import com.transformacion.fitness.di.Container
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Application class - punto de entrada da aplicación.
 * Inicializa o contedor de dependencias e os datos seed.
 */
class FitnessApp : Application() {
    
    // Contedor de dependencias accesible globalmente
    val container: Container by lazy { Container(this) }
    
    override fun onCreate() {
        super.onCreate()
        
        // Inicializar a base de datos con datos seed
        // Executamos nunha coroutine para non bloquear
        CoroutineScope(Dispatchers.Main).launch {
            container.initialize()
        }
    }
}
