package com.example.proyecto.BBDD

import androidx.room.*

@Dao
interface HabilidadDao {

    @Query("SELECT * FROM habilidades")
    fun getAll(): List<Habilidad>

    @Query("SELECT * FROM habilidades WHERE personaje_id = :personaje_id")
    fun getAllChampAbilities(personaje_id: Int): List<Habilidad>

    @Query("SELECT * FROM habilidades WHERE id = :id")
    fun findById(id: Int): Habilidad

    @Update
    fun updateAbility(vararg habilidad: Habilidad)

    //(onConflict = REPLACE)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg habilidades: Habilidad)

    @Insert
    fun insertar(vararg habilidades: Habilidad)

    @Delete
    fun delete(habilidad: Habilidad)

}