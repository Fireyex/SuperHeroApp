package pe.isil.superheroapp.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import pe.isil.superheroapp.domain.SuperHero

@Composable
fun FavoritesView(
    viewModel: SearchSuperHeroViewModel,
    onSelect: (SuperHero) -> Unit = {}
) {
    val favorites = viewModel.favorites.collectAsState()

    if (favorites.value.isEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(24.dp),
            contentAlignment = Alignment.Center
        ) {
            Text("No hay favoritos agregados", style = MaterialTheme.typography.titleMedium)
        }
    } else {
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            contentPadding = PaddingValues(12.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(favorites.value) { superHero ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(0.75f),
                    shape = RoundedCornerShape(16.dp),
                    onClick = { onSelect(superHero) },
                    elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
                ) {
                    Column {
                        AsyncImage(
                            model = superHero.imageUrl,
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(140.dp)
                                .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                        )
                        Column(modifier = Modifier.padding(12.dp)) {
                            Text(
                                text = superHero.name,
                                style = MaterialTheme.typography.titleMedium
                            )
                            Text(
                                text = "${superHero.fullName}",
                                style = MaterialTheme.typography.bodySmall,
                                color = MaterialTheme.colorScheme.primary
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Button(
                                onClick = { viewModel.removeFavorite(superHero) },
                                modifier = Modifier.fillMaxWidth(),
                                shape = RoundedCornerShape(12.dp)
                            ) {
                                Text("Quitar")
                            }
                        }
                    }
                }
            }
        }
    }
}
