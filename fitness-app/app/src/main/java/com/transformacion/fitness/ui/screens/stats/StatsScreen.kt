package com.transformacion.fitness.ui.screens.stats

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
fun StatsScreen(
    onBackToHome: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: StatsViewModel = viewModel { 
        StatsViewModel(Container.repository) 
    }
) {
    val uiState by viewModel.uiState.collectAsState()

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color(0xFF0F172A))
            .padding(24.dp),
        contentAlignment = Alignment.TopStart
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            // Título
            Text(
                text = "📊 Estatísticas",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(24.dp))

            if (uiState.isLoading) {
                CircularProgressIndicator(
                    color = YellowPrimary,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            } else {
                // Tarxetas de estatísticas
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // Total sesións
                    StatCard(
                        title = "Sesións",
                        value = "${uiState.totalSessions}",
                        modifier = Modifier.weight(1f)
                    )

                    // Total minutos
                    StatCard(
                        title = "Minutos",
                        value = "${uiState.totalMinutes}",
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    // Racha actual
                    StatCard(
                        title = "Racha actual",
                        value = "${uiState.currentStreak} días",
                        modifier = Modifier.weight(1f)
                    )

                    // Mellor racha
                    StatCard(
                        title = "Mellor racha",
                        value = "${uiState.longestStreak} días",
                        modifier = Modifier.weight(1f)
                    )
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Gráfica semanal (placeholder)
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
                        Text(
                            text = "Actividade semanal",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.White
                        )
                        
                        Spacer(modifier = Modifier.height(16.dp))
                        
                        // Barras da gráfica (placeholder)
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly,
                            verticalAlignment = Alignment.Bottom
                        ) {
                            val days = listOf("L", "M", "X", "J", "V", "S", "D")
                            days.forEachIndexed { index, day ->
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    // Barra
                                    Box(
                                        modifier = Modifier
                                            .width(30.dp)
                                            .height(if (index % 2 == 0) 60.dp else 30.dp)
                                            .background(YellowPrimary, RoundedCornerShape(4.dp))
                                    )
                                    
                                    Spacer(modifier = Modifier.height(4.dp))
                                    
                                    // Día
                                    Text(
                                        text = day,
                                        fontSize = 12.sp,
                                        color = Color(0xFF94A3B8)
                                    )
                                }
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.height(24.dp))

                // Medallas desbloqueadas
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
                        Text(
                            text = "🏅 Medallas",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = Color.White
                        )
                        
                        Spacer(modifier = Modifier.height(16.dp))
                        
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceEvenly
                        ) {
                            listOf("🥉", "🥈", "🥇", "🏆").forEach { medal ->
                                Text(
                                    text = medal,
                                    fontSize = 40.sp
                                )
                            }
                        }
                    }
                }

                Spacer(modifier = Modifier.weight(1f))

                // Botón volver
                Button(
                    onClick = onBackToHome,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(56.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFF475569)
                    ),
                    shape = RoundedCornerShape(40.dp)
                ) {
                    Text(
                        text = "← Volver ao inicio",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White
                    )
                }
            }
        }
    }
}

@Composable
private fun StatCard(
    title: String,
    value: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
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
                text = value,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = YellowPrimary
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = title,
                fontSize = 12.sp,
                color = Color(0xFF94A3B8)
            )
        }
    }
}
