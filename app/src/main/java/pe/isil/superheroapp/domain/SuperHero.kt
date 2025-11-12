package pe.isil.superheroapp.domain

data class SuperHero(
    val id: Int,
    val name: String,
    val fullName: String,
    val imageUrl: String,
    val intelligence: Int,
    val strength: Int,
    val speed: Int,
    val power: Int,
    val placeOfBirth: String,
    val alignment: String
)
