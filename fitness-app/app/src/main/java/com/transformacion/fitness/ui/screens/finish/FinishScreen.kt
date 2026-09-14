package com.transformacion.fitness.ui.screens.finish

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
fun FinishScreen(
    onSaveAndReturn: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: FinishViewModel = viewModel { 
        FinishViewModel(Container.repository) 
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
            // Medalla gañada
            Text(
                text = uiState.earnedMedalEmoji,
                fontSize = 72.sp
            )

            Spacer(modifier = Modifier.height(24.dp))

            // Frase motivacional
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF334155)
                ),
                shape = RoundedCornerShape(20.dp)
            ) {
                Text(
                    text = uiState.motivationalPhrase,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = YellowPrimary,
                    modifier = Modifier.padding(16.dp)
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Estatísticas da sesión
            Card(
                modifier = Modifier.fillMaxWidth(),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF334155)
                ),
                shape = RoundedCornerShape(20.dp)
            ) {
                Column(
                    modifier = Modifier.padding(16.dp)
                ) {
                    // Sesións completadas
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "✅ Sesións completadas",
                            fontSize = 16.sp,
                            color = Color.White
                        )
                        Text(
                            text = "${uiState.totalSessions}",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = YellowPrimary
                        )
                    }

                    Divider(color = Color(0xFF475569))

                    // Minutos totais
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "⏱ Minutos totais",
                            fontSize = 16.sp,
                            color = Color.White
                        )
                        Text(
                            text = "${uiState.totalMinutes}",
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = YellowPrimary
                        )
                    }

                    Divider(color = Color(0xFF475569))

                    // Progreso acumulado
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(vertical = 8.dp)
                    ) {
                        Text(
                            text = "📈 Progreso acumulado",
                            fontSize = 16.sp,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        
                        // Barra de progreso
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(20.dp)
                                .background(Color(0xFF1E293B), RoundedCornerShape(12.dp))
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth(uiState.progressPercent)
                                    .height(20.dp)
                                    .background(
                                        androidx.compose.ui.graphics.Brush.horizontalGradient(
                                            listOf(YellowPrimary, Color(0xFFF59E0B))
                                        ),
                                        RoundedCornerShape(12.dp)
                                    )
                            )
                        }
                        
                        Spacer(modifier = Modifier.height(4.dp))
                        
                        Text(
                            text = "Meta: 300 minutos (20 sesións)",
                            fontSize = 12.sp,
                            color = Color(0xFF94A3B8)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(24.dp))

            // Colección de medallas (placeholder)
            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
            ) {
                val medals = listOf("🥉", "🥈", "🥇", "🏆")
                medals.forEach { medal ->
                    Text(
                        text = medal,
                        fontSize = 32.sp,
                        modifier = Modifier.padding(horizontal = 6.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(32.dp))

            // Botón volver ao inicio
            Button(
                onClick = onSaveAndReturn,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF475569)
                ),
                shape = RoundedCornerShape(40.dp)
            ) {
                Text(
                    text = "🔁 Volver ao inicio",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White
                )
            }
        }
    }
}
