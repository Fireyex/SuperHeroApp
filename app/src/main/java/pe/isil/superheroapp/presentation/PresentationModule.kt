package pe.isil.superheroapp.presentation

import pe.isil.superheroapp.data.DataModule.getSuperHeroService

object PresentationModule {

    fun getSearchSuperHeroViewModel(): SearchSuperHeroViewModel {
        return SearchSuperHeroViewModel(getSuperHeroService())
    }
}
