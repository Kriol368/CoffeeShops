package com.example.coffeeshops

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

data class CoffeeShop(
    val name: String,
    val address: String,
    val imageRes: Int
)

val coffeeShops = listOf(
    CoffeeShop("Antico Caffè Greco", "St. Italy, Rome", R.drawable.images),
    CoffeeShop("Coffee Room", "St. Germany, Berlin", R.drawable.images1),
    CoffeeShop("Coffee Ibiza", "St. Colon, Madrid", R.drawable.images2),
    CoffeeShop("Pudding Coffee Shop", "St. Diagonal, Barcelona", R.drawable.images3),
    CoffeeShop("L'Express", "St. Picadilly Circus, London", R.drawable.images4),
    CoffeeShop("Coffee Corner", "St. Àngel Guimerà, Valencia", R.drawable.images5),
    CoffeeShop("Sweet Cup", "St. Kinkerstraat, Amsterdam", R.drawable.images6)
)

@Composable
fun CoffeeShops(navController: NavHostController) {
    LazyColumn(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(coffeeShops) { coffeeShop ->
            CoffeeShopCard(
                coffeeShop = coffeeShop,
                onItemClick = {
                    navController.navigate("comments/${coffeeShop.name}")
                }
            )
        }
    }
}

@Composable
fun CoffeeShopCard(coffeeShop: CoffeeShop, onItemClick: () -> Unit) {
    var rating by remember { mutableIntStateOf(0) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onItemClick() },
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Image(
                painter = painterResource(id = coffeeShop.imageRes),
                contentDescription = "Imagen de ${coffeeShop.name}",
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                contentScale = ContentScale.Crop
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = coffeeShop.name,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )

            Spacer(modifier = Modifier.height(4.dp))

            StarRatingBar(
                rating = rating,
                onRatingChange = { newRating ->
                    rating = newRating
                }
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = coffeeShop.address,
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )

            Spacer(modifier = Modifier.height(12.dp))

            HorizontalDivider(
                modifier = Modifier.fillMaxWidth(),
                thickness = 1.dp,
                color = Color.LightGray
            )

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = {},
            ) {
                Text("RESERVE")
            }
        }
    }
}

@Composable
fun StarRatingBar(rating: Int, onRatingChange: (Int) -> Unit) {
    Row {
        for (i in 1..5) {
            Icon(
                imageVector = if (i <= rating) Icons.Filled.Star else Icons.Outlined.Star,
                contentDescription = "Estrella $i",
                tint = if (i <= rating) Color(0xFFFFD700) else Color.Gray,
                modifier = Modifier
                    .size(32.dp)
                    .clickable {
                        onRatingChange(i)
                    }
            )
            Spacer(modifier = Modifier.width(4.dp))
        }
    }
}