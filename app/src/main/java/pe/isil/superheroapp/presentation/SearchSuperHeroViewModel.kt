package pe.isil.superheroapp.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import pe.isil.superheroapp.data.SuperHeroService
import pe.isil.superheroapp.domain.SuperHero

class SearchSuperHeroViewModel(private val superHeroService: SuperHeroService) : ViewModel() {

    private val _superHeroes = MutableStateFlow<List<SuperHero>>(emptyList())
    val superHeroes: StateFlow<List<SuperHero>> = _superHeroes

    fun getSuperHeroes(query: String) {
        viewModelScope.launch {
            val response = superHeroService.searchSuperHero(name = query)
            Log.d("SearchSuperHeroViewModel", response.toString())

            if (response.isSuccessful) {
                val resultBody = response.body()

                val list = resultBody?.superHeroes?.map { dto ->
                    dto.toDomain()
                } ?: emptyList()

                _superHeroes.value = list
            } else {
                _superHeroes.value = emptyList()
            }
        }
    }

    private val _favorites = MutableStateFlow<List<SuperHero>>(emptyList())
    val favorites: StateFlow<List<SuperHero>> = _favorites

    fun addFavorite(superHero: SuperHero) {
        if (!_favorites.value.any { it.id == superHero.id }) {
            _favorites.value = _favorites.value + superHero
        }
    }

    fun removeFavorite(superHero: SuperHero) {
        _favorites.value = _favorites.value.filter { it.id != superHero.id }
    }

}
