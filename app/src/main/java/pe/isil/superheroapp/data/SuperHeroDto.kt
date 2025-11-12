package pe.isil.superheroapp.data

import com.google.gson.annotations.SerializedName
import pe.isil.superheroapp.domain.SuperHero

data class SuperHeroDto(
    val id: Int,
    val name: String,
    val powerstats: PowerStatsDto,
    val biography: BiographyDto,
    val image: ImageDto
) {
    fun toDomain(): SuperHero {
        return SuperHero(
            id = id,
            name = name,
            fullName = biography.fullName,
            imageUrl = image.url,
            intelligence = powerstats.intelligence.toIntOrNull() ?: 0,
            strength = powerstats.strength.toIntOrNull() ?: 0,
            speed = powerstats.speed.toIntOrNull() ?: 0,
            power = powerstats.power.toIntOrNull() ?: 0,
            placeOfBirth = biography.placeOfBirth,
            alignment = biography.alignment
        )
    }
}

data class PowerStatsDto(
    val intelligence: String,
    val strength: String,
    val speed: String,
    val power: String
)

data class BiographyDto(
    @SerializedName("full-name")
    val fullName: String,
    @SerializedName("place-of-birth")
    val placeOfBirth: String,
    val alignment: String
)

data class ImageDto(
    val url: String
)

data class SuperHeroesResponseDto(
    @SerializedName("results")
    val superHeroes: List<SuperHeroDto>
)
