# Exercitapp

Aplicación móbil de adestramento en casa desenvolvida con React Native / Expo e Tailwind CSS.

## Características

- **100% Offline**: Funciona sen conexión a internet
- **Modo Escuro**: Interface minimalista en modo escuro
- **En Galego**: Toda a aplicación está traducida ao galego
- **Sen Material**: Circuíto de 5 exercicios que non requiren material complexo
- **Gamificación**: Sistema de medallas por constancia
- **Progreso Local**: Datos gardados no dispositivo

## Circuíto de Adestramento

3 roldas de 5 exercicios:
1. Flexións na parede (45s)
2. Remo con botellas (45s)
3. Sentadillas con cadeira (45s)
4. Fondos de tríceps (45s)
5. Plancha de xeonllos (45s)

Descanso entre exercicios: 15 segundos

## Instalación

```bash
cd Exercitapp
npm install
npm run android
```

## Estructura do Proxecto

```
Exercitapp/
├── App.tsx                 # Punto de entrada principal
├── screens/
│   ├── HomeScreen.tsx      # Pantalla de inicio
│   └── WorkoutScreen.tsx   # Pantalla de adestramento
├── components/
│   ├── Timer.tsx           # Componte de temporizador
│   ├── ProgressBar.tsx     # Barra de progreso
│   └── ExerciseCard.tsx    # Tarxetas de exercicios e medallas
├── utils/
│   ├── storage.ts          # Xestión de AsyncStorage
│   ├── exercises.ts        # Configuración de exercicios
│   └── badges.ts           # Sistema de medallas
├── types/
│   └── index.ts            # Definicións de tipos TypeScript
└── tailwind.config.js      # Configuración de Tailwind CSS
```

## Tecnoloxías

- React Native
- Expo SDK 57
- TypeScript
- NativeWind (Tailwind CSS para React Native)
- AsyncStorage

## Licencia

Open Source - Libre uso e modificación.
