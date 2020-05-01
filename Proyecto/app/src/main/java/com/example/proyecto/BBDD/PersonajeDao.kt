package com.example.proyecto.BBDD

import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE
import io.reactivex.Single

@Dao
interface PersonajeDao {

    @Query("SELECT * FROM personajes")
    fun getAll(): List<Personaje>

    @Query("SELECT * FROM personajes WHERE id = :id")
    fun findById(id: Int): Personaje

    @Update
    fun updateCharacter(vararg personaje: Personaje)

    //(onConflict = REPLACE)
    @Insert(onConflict = REPLACE)
    fun insertAll(vararg personajes: Personaje)

    @Insert
    fun insertar(vararg personajes: Personaje)

    @Delete
    fun delete(personaje: Personaje)

    @Delete
    fun deleteAll(personajes: List<Personaje>): Single<Int>

}