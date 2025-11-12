package pe.isil.superheroapp.data

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DataModule {

    fun getSuperHeroService(): SuperHeroService {
        val retrofit = Retrofit.Builder()
            .baseUrl("1")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(SuperHeroService::class.java)
    }
}
