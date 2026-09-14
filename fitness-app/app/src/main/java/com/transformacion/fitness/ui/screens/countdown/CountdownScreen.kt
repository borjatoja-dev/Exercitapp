package com.transformacion.fitness.ui.screens.countdown

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun CountdownScreen(
    onComplete: () -> Unit,
    onBackToHome: () -> Unit,
    modifier: Modifier = Modifier
) {
    var count by remember { mutableStateOf(5) }
    val isFinished = count <= 0

    LaunchedEffect(count) {
        if (count > 0) {
            delay(1000)
            count--
        } else {
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
            // Número da conta atrás
            Text(
                text = if (count > 0) count.toString() else "0",
                fontSize = 120.sp,
                fontWeight = FontWeight.Black,
                color = Color(0xFFFACC15)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Label
            Text(
                text = "Prepárate...",
                fontSize = 20.sp,
                color = Color(0xFF94A3B8)
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Instrucións
            Text(
                text = "Cando chegue a 0, imita o exercicio",
                fontSize = 14.sp,
                color = Color(0xFF94A3B8)
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Botón para cancelar
            if (count > 0) {
                androidx.compose.material3.OutlinedButton(
                    onClick = onBackToHome,
                    colors = androidx.compose.material3.OutlinedButtonDefaults.outlinedButtonColors(
                        contentColor = Color.White
                    )
                ) {
                    Text("Cancelar")
                }
            }
        }
    }
}
