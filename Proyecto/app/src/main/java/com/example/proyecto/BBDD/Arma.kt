package com.example.proyecto.BBDD

//Uso esta clase para crear objetos de las armas en los fragmentos:
//AsaltoFragment, PistolasFragment, RiflesFragment
//Pero esta clase no se utilizará con la BBDD, aunque la meto en este paquete porque es donde tengo
//las clases
class Arma(
    val id: Int = -1,
    val nombre: String = "Classic",
    val link_imagen: String = "https://i.gyazo.com/73be727d8c2e73e0fdf5721077de112c.png",
    val tipo: String = "Pistola",
    val coste: String = "Free",
    val closeDamage: List<String> = listOf("78", "26", "22"),
    val mediumDamage: List<String> = listOf("66", "22", "18"),
    val farDamage: List<String> = listOf("66", "22", "18")) {
}

