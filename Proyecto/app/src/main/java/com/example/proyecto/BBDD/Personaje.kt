package com.example.proyecto.BBDD

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//Documentación básica: https://developer.android.com/training/data-storage/room/defining-data#kotlin
//Documentación relaciones: https://developer.android.com/training/data-storage/room/relationships

//val bd = Room.databaseBuilder(this, AppDatabase::class.java, "personajes").build()

@Entity(tableName = "personajes")
data class Personaje (
    @PrimaryKey val id: Int = 1,
    @ColumnInfo(name = "nombre") val nombre: String = "Personaje QM",
    @ColumnInfo(name = "link_imagen") val link_imagen: String = "https://i.gyazo.com/d5b94a4b742c9db9ab409a2530893748.png"
)