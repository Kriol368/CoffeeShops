package com.example.coffeeshops

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import kotlinx.coroutines.delay

val sampleComments = listOf(
    "Servicio algo flojo, aún así lo recomiendo",
    "Céntrica y acogedora, Volveremos seguro",
    "La ambientacion muy buena, pero en la planta de arriba un poco escasa",
    "La comida estaba deliciosa y bastante bien de precio, mucha variedad de platos. El personal muy amable, nos permitieron ver todo el establecimiento",
    "Muy bueno",
    "Excelente. destacable la extensa carta de cafés",
    "Buen ambiente y buen servicio. Lo recomiendo",
    "En dias festivos demasiado tiempo de espera. Los camareros/as no dan abasto. No lo recomiendo. No volveré",
    "Repetiremos, Gran seleccción de tartas y cafés",
    "Todo lo que he probado en la cafeteria esta riquisimo, dulce o salado. La vajilla muy  bonita todo de diseño que en el entorno del bar queda ideal",
    "Céntrica y acogedora, Volveremos seguro",
    "La ambientacion muy buena, pero en la planta de arriba un poco escasa",
    "La comida estaba deliciosa y bastante bien de precio, mucha variedad de platos. El personal muy amable, nos permitieron ver todo el establecimiento",
    "Muy bueno",
    "Excelente. destacable la extensa carta de cafés",
    "Buen ambiente y buen servicio. Lo recomiendo",
    "En dias festivos demasiado tiempo de espera. Los camareros/as no dan abasto. No lo recomiendo. No volveré",
)

@Composable
fun Comments(coffeeShopName: String, navController: NavHostController) {
    val gridState = rememberLazyStaggeredGridState()
    var isButtonVisible by remember { mutableStateOf(true) }
    var lastScrollTime by remember { mutableStateOf(0L) }

    LaunchedEffect(gridState.isScrollInProgress) {
        if (gridState.isScrollInProgress) {
            delay(100)
            val currentTime = System.currentTimeMillis()
            if (currentTime - lastScrollTime > 150) {
                isButtonVisible = false
                lastScrollTime = currentTime
            }
        } else {
            isButtonVisible = true
        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = coffeeShopName,
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp)
            )

            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalItemSpacing = 12.dp,
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(bottom = 80.dp),
                state = gridState
            ) {
                items(sampleComments) { comment ->
                    CommentCard(comment = comment)
                }
            }
        }

        if (isButtonVisible) {
            Button(
                onClick = {},
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 16.dp)
                    .fillMaxWidth(0.9f)
                    .height(50.dp)
            ) {
                Text("Add New Comment")
            }
        }
    }
}

@Composable
fun CommentCard(comment: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Text(
            text = comment,
            style = MaterialTheme.typography.bodyMedium,
            modifier = Modifier.padding(12.dp)
        )
    }
}