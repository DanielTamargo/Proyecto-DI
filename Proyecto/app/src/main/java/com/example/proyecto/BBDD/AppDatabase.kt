package com.example.proyecto.BBDD

import androidx.room.Database
import androidx.room.RoomDatabase

//diferentes tipos de migraciones: https://medium.com/androiddevelopers/understanding-migrations-with-room-f01e04b07929

@Database(entities = arrayOf(Personaje::class, Habilidad::class), version = 3)
abstract class AppDatabase : RoomDatabase() {
    abstract fun personajeDao(): PersonajeDao
    abstract fun habilidadDao(): HabilidadDao
}