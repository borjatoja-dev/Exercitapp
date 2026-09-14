package com.transformacion.fitness.ui.screens.rest

import androidx.compose.foundation.background
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
import com.transformacion.fitness.ui.theme.BlueAccent

@Composable
fun RestScreen(
    nextExerciseIndex: Int,
    nextRound: Int,
    onComplete: () -> Unit,
    onBackToHome: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: RestViewModel = viewModel {
        RestViewModel(Container.repository, nextExerciseIndex, nextRound)
    }
) {
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(uiState.timeRemaining) {
        if (uiState.timeRemaining == 0 && uiState.isRunning) {
            onComplete()
        }
    }

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF0F172A))
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            // Título de descanso
            Text(
                text = "💨 DESCANSA",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = BlueAccent
            )

            Spacer(modifier = Modifier.height(24.dp))

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
                text = "segundos",
                fontSize = 14.sp,
                color = Color(0xFF94A3B8)
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Info do seguinte exercicio
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF334155)
                ),
                shape = RoundedCornerShape(20.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "Seguinte:",
                        fontSize = 14.sp,
                        color = Color(0xFF94A3B8)
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = uiState.nextExerciseName.ifEmpty { "Cargando..." },
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                }
            }

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
}
