package com.transformacion.fitness.ui.screens.home

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
import com.transformacion.fitness.ui.theme.YellowPrimary

@Composable
fun HomeScreen(
    onStartWorkout: () -> Unit,
    onViewStats: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel { 
        HomeViewModel(Container.repository) 
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
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            // Título
            Text(
                text = "💪 COMEZA",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = YellowPrimary
            )

            Spacer(modifier = Modifier.height(8.dp))

            // Subtítulo
            Text(
                text = "Adestramento 15 min • 17:30",
                fontSize = 14.sp,
                color = Color(0xFF94A3B8)
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Info do día
            if (!uiState.isLoading) {
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFF334155)
                    ),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "📍 Hoxe: Día ${uiState.currentDay}",
                            fontSize = 16.sp,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "⏱ Total acumulado: ${uiState.totalMinutes} min",
                            fontSize = 16.sp,
                            color = Color.White
                        )
                    }
                }
            } else {
                CircularProgressIndicator(
                    color = YellowPrimary,
                    modifier = Modifier.size(40.dp)
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Botón grande de empezar
            Button(
                onClick = onStartWorkout,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = YellowPrimary
                ),
                shape = RoundedCornerShape(60.dp),
                enabled = !uiState.isLoading
            ) {
                Text(
                    text = "▶ EMPEZAR (5 seg)",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF0F172A)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Frase motivacional
            Text(
                text = "Non penses. Só preme. O primeiro movemento é o máis difícil.",
                fontSize = 12.sp,
                color = Color(0xFF64748B),
                modifier = Modifier.fillMaxWidth(),
                textAlign = androidx.compose.ui.text.style.TextAlign.Center
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Botón de estatísticas
            OutlinedButton(
                onClick = onViewStats,
                modifier = Modifier.fillMaxWidth(),
                colors = OutlinedButtonDefaults.outlinedButtonColors(
                    contentColor = Color.White
                ),
                shape = RoundedCornerShape(40.dp)
            ) {
                Text("📊 Ver Estatísticas")
            }
        }
    }
}
