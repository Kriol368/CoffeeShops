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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.SearchBarDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Comments(coffeeShopName: String) {
    val gridState = rememberLazyStaggeredGridState()
    var isButtonVisible by remember { mutableStateOf(false) }
    var firstVisibleItemIndex by remember { mutableIntStateOf(0) }
    var scrollToTopTrigger by remember { mutableIntStateOf(0) }
    var searchQuery by remember { mutableStateOf("") }

    val filteredComments by remember(searchQuery, sampleComments) {
        derivedStateOf {
            if (searchQuery.isBlank()) {
                sampleComments
            } else {
                sampleComments.filter { comment ->
                    comment.contains(searchQuery, ignoreCase = true)
                }
            }
        }
    }

    val showButton by remember {
        derivedStateOf {
            val currentFirstVisibleItem = gridState.firstVisibleItemIndex
            val isScrollingDown = currentFirstVisibleItem > firstVisibleItemIndex

            firstVisibleItemIndex = currentFirstVisibleItem
            isScrollingDown && currentFirstVisibleItem != 0
        }
    }

    LaunchedEffect(gridState.isScrollInProgress) {
        isButtonVisible = showButton
    }

    LaunchedEffect(scrollToTopTrigger) {
        if (scrollToTopTrigger > 0) {
            gridState.animateScrollToItem(0)
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
                fontSize = 32.sp,
                fontFamily = FontFamily(Font(R.font.aliviaregular)),
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
            )

            SearchBar(
                query = searchQuery,
                onQueryChange = { newQuery ->
                    searchQuery = newQuery
                },
                onSearch = { },
                active = false,
                onActiveChange = { },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                trailingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Buscar"
                    )
                },
                colors = SearchBarDefaults.colors(
                    containerColor = Color(0xFFFBE3E3)
                )
            ) {
            }

            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Fixed(2),
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalItemSpacing = 16.dp,
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                contentPadding = PaddingValues(bottom = 80.dp),
                state = gridState
            ) {
                items(filteredComments) { comment ->
                    CommentCard(comment = comment)
                }
            }
        }

        if (isButtonVisible) {
            Button(
                onClick = {
                    scrollToTopTrigger++
                },
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(bottom = 16.dp)
                    .fillMaxWidth(0.5f)
                    .height(50.dp),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFF99AAA))
            ) {
                Text("Add new comment")
            }
        }
    }
}

@Composable
fun CommentCard(comment: String) {
    Card(
        modifier = Modifier
            .fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color(0xFFFBE3E3))
    ) {
        Text(
            text = comment,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontSize = 10.sp,
                lineHeight = 12.sp
            ), modifier = Modifier.padding(12.dp)
        )
    }
}