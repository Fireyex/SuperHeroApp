package pe.isil.superheroapp.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import pe.isil.superheroapp.domain.SuperHero
import pe.isil.superheroapp.presentation.PresentationModule.getSearchSuperHeroViewModel

@Composable
fun Navigation() {
    val navController = rememberNavController()

    val navigationItems = listOf(
        NavigationItem("Search", Icons.Default.Search, "search_superhero"),
        NavigationItem("Favorites", Icons.Default.Favorite, "favorites")
    )

    val selectedIndex = remember { mutableStateOf(0) }
    val selectedSuperHero = remember { mutableStateOf<SuperHero?>(null) }

    val viewModel = getSearchSuperHeroViewModel()

    Scaffold(
        bottomBar = {
            NavigationBar {
                navigationItems.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = index == selectedIndex.value,
                        icon = { Icon(item.icon, contentDescription = null) },
                        onClick = {
                            selectedIndex.value = index
                            navController.navigate(item.route)
                        },
                        label = { Text(item.name) }
                    )
                }
            }
        }
    ) { padding ->
        NavHost(
            navController,
            startDestination = "search_superhero",
            modifier = Modifier.padding(padding)
        ) {
            composable("search_superhero") {
                SearchSuperHeroView(viewModel = viewModel) { superHero ->
                    selectedSuperHero.value = superHero
                    navController.navigate("detail_superhero")
                }
            }
            composable("detail_superhero") {
                selectedSuperHero.value?.let { superHero ->
                    SuperHeroDetailView(superHero = superHero, viewModel = viewModel)
                }
            }
            composable("favorites") {
                FavoritesView(viewModel = viewModel) { superHero ->
                    selectedSuperHero.value = superHero
                    navController.navigate("detail_superhero")
                }
            }
        }
    }
}

data class NavigationItem(
    val name: String,
    val icon: ImageVector,
    val route: String
)