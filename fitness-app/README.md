# Fitness App - Adestramento en Casa

Aplicación Android persoal de transformación física.

## Obxectivo

Axudar ao usuario a:
- Reducir progresivamente o porcentaje de graxa corporal
- Manter ou aumentar masa muscular
- Mellorar progresivamente a forza
- Manter unha alta adherencia ao adestramento
- Utilizar motivación, obxectivos e seguimento da evolución

## Características Técnicas

- **Offline-first**: Sen permisos de Internet, todos os datos almacénanse localmente
- **Custe cero**: Software libre, sen servizos de pago
- **Sen contas**: Uso persoal, sen servidor backend
- **Datos locais**: Room (SQLite) + SharedPreferences

## Stack Tecnolóxico

- **Linguaxe**: Kotlin 2.0+
- **UI**: Jetpack Compose + Material 3
- **Arquitectura**: MVVM + Clean Architecture
- **BD Local**: Room 2.6+
- **Estado Reactivo**: Kotlin Flow + StateFlow
- **Navegación**: Navigation Compose
- **Corrutinas**: kotlinx.coroutines

## Estrutura do Proxecto

```
app/src/main/java/com/transformacion/fitness/
├── data/          # Capa de datos (Room, DAOs, Repositorios)
├── domain/        # Capa de negocio (Modelos, Casos de uso)
├── ui/            # Capa de presentación (Compose, ViewModels, Pantallas)
└── util/          # Utilidades
```

## Fases de Desenvolvemento

### Fase 0: Configuración do Proxecto ✅
- [x] Crear estrutura de carpetas
- [x] Configurar Gradle con dependencias
- [x] Crear Application class e MainActivity
- [x] Configurar tema Material 3
- [x] Verificar compilación básica

### Fase 1: Migración de Datos e Modelo (PRÓXIMA)
- [ ] Crear entidades Room
- [ ] Implementar DAOs
- [ ] Crear AppDatabase
- [ ] Seed de exercicios (5 do prototipo)
- [ ] Repositorio básico

### Fase 2: Home e Navegación
- [ ] Configurar Navigation Compose
- [ ] HomeScreen con estatísticas
- [ ] Navegación a WorkoutScreen

### Fase 3: Motor de Adestramento
- [ ] CountdownScreen (5s)
- [ ] WorkoutScreen (45s traballo)
- [ ] RestScreen (15s descanso)
- [ ] Máquina de estados do adestramento

### Fase 4: Finalización e Persistencia
- [ ] FinishScreen
- [ ] Gardar sesión en Room
- [ ] Actualizar progreso do usuario

### Fase 5: Estatísticas e Medallas
- [ ] StatsScreen
- [ ] Sistema de medallas completo
- [ ] Gráficas de evolución

## Como Compilar

1. Abrir o proxecto en Android Studio
2. Agardar a que Gradle sincronice as dependencias
3. Executar `./gradlew assembleDebug` para xerar APK
4. Instalar no dispositivo/emulador

## Licenza

Proxecto persoal de uso privado.
