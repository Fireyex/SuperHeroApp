package pe.isil.superheroapp.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import pe.isil.superheroapp.domain.SuperHero
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.PlatformContext

@Composable
fun SuperHeroDetailView(
    superHero: SuperHero,
    viewModel: SearchSuperHeroViewModel
) {
    val favorites = viewModel.favorites.collectAsState()
    val isFavorite = favorites.value.any { it.id == superHero.id }

    Scaffold { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(16.dp)
                .fillMaxSize()
        ) {
            Box {
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(superHero.imageUrl),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(250.dp)
                        .clip(RoundedCornerShape(16.dp))
                )

                IconButton(
                    onClick = {
                        if (isFavorite) {
                            viewModel.removeFavorite(superHero)
                        } else {
                            viewModel.addFavorite(superHero)
                        }
                    },
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(12.dp)
                        .size(48.dp)
                        .background(
                            color = Color.White.copy(alpha = 0.7f),
                            shape = CircleShape
                        )
                ) {
                    Icon(
                        imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = null,
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = superHero.name,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold
            )

            Text("Nombre real: ${superHero.fullName}")
            Text("Lugar de origen: ${superHero.placeOfBirth}")

            Spacer(modifier = Modifier.height(16.dp))
            AlignmentBadge(alignment = superHero.alignment)
            Spacer(modifier = Modifier.height(16.dp))

            StatBar(label = "Intelligence", value = superHero.intelligence)
            StatBar(label = "Strength", value = superHero.strength)
            StatBar(label = "Speed", value = superHero.speed)
            StatBar(label = "Power", value = superHero.power)
        }
    }
}


@Composable
fun StatBar(label: String, value: Int) {
    Column(modifier = Modifier.padding(vertical = 6.dp)) {
        Text(label, style = MaterialTheme.typography.bodySmall)

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(20.dp)
                .clip(RoundedCornerShape(50))
                .background(Color.LightGray.copy(alpha = 0.3f))
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(fraction = value / 100f)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(50))
                    .background(MaterialTheme.colorScheme.primary)
            )
        }

        Text(
            "$value / 100",
            style = MaterialTheme.typography.bodySmall,
            modifier = Modifier.align(Alignment.End)
        )
    }
}

@Composable
fun AlignmentBadge(alignment: String) {
    val color = when (alignment.lowercase()) {
        "good" -> Color(0xFF4CAF50)
        "bad" -> Color(0xFFF44336)
        else -> Color.Gray
    }

    Box(
        modifier = Modifier
            .clip(CircleShape)
            .background(color)
            .padding(horizontal = 16.dp, vertical = 8.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = alignment.uppercase(),
            color = Color.White,
            style = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.Bold
        )
    }
}