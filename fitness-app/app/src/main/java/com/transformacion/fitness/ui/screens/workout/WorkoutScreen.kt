package com.transformacion.fitness.ui.screens.workout

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.transformacion.fitness.di.Container
import com.transformacion.fitness.ui.theme.YellowPrimary

@Composable
fun WorkoutScreen(
    exerciseIndex: Int,
    currentRound: Int,
    onComplete: (nextExerciseIndex: Int, nextRound: Int) -> Unit,
    onBackToHome: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: WorkoutViewModel = viewModel {
        WorkoutViewModel(Container.repository, exerciseIndex, currentRound)
    }
) {
    val uiState by viewModel.uiState.collectAsState()

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF0F172A))
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            // Indicadores de rolda e exercicio
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "⏳ Rolda ${uiState.currentRound}/${uiState.totalRounds}",
                    fontSize = 14.sp,
                    color = Color(0xFF94A3B8)
                )
                Text(
                    text = "🏋️ ${uiState.exerciseIndex + 1}/${uiState.totalExercises}",
                    fontSize = 14.sp,
                    color = Color(0xFF94A3B8)
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Caixa do exercicio
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF334155)
                ),
                shape = RoundedCornerShape(24.dp),
                border = CardDefaults.cardBorder(
                    border = androidx.compose.foundation.BorderStroke(
                        6.dp,
                        YellowPrimary
                    ),
                    shape = RoundedCornerShape(24.dp)
                )
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    // Emoji (placeholder por agora)
                    Text(
                        text = "🏋️",
                        fontSize = 48.sp
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Nome do exercicio
                    Text(
                        text = "Exercicio ${uiState.exerciseIndex + 1}",
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold,
                        color = YellowPrimary
                    )

                    Spacer(modifier = Modifier.height(16.dp))

                    // Descrición (placeholder)
                    Text(
                        text = "Instrucións do exercicio...",
                        fontSize = 15.sp,
                        color = Color(0xFFCBD5E1),
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color(0xFF1E293B), RoundedCornerShape(16.dp))
                            .padding(14.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Temporizador xigante
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF0F172A)
                ),
                shape = RoundedCornerShape(60.dp)
            ) {
                Text(
                    text = "${uiState.timeRemaining}",
                    fontSize = 52.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.White,
                    modifier = Modifier.padding(horizontal = 40.dp, vertical = 16.dp)
                )
            }

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "segundos de movemento",
                fontSize = 14.sp,
                color = Color(0xFF94A3B8)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Hint do seguinte exercicio
            Text(
                text = "⏩ Seguinte: ${uiState.nextExerciseName} (Rolda ${uiState.nextRound})",
                fontSize = 13.sp,
                color = YellowPrimary
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Botón para volver ao inicio
            OutlinedButton(
                onClick = onBackToHome,
                colors = OutlinedButtonDefaults.outlinedButtonColors(
                    contentColor = Color.White
                )
            ) {
                Text("Cancelar")
            }
        }
    }

    // Chamar onComplete cando remate o temporizador
    // Isto debería manexarse no ViewModel e observarse aquí
}
