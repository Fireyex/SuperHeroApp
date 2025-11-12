package pe.isil.superheroapp.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface SuperHeroService {

    @GET("api.php/f274286a22873ec9fc7a5782940f7ca2/search/{name}")
    suspend fun searchSuperHero(
        @Path("name") name: String
    ): Response<SuperHeroesResponseDto>

}
