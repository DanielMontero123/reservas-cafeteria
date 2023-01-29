package dev.android.appreservas.interfaces

import androidx.lifecycle.LiveData
import androidx.room.*
import dev.android.appreservas.clases.Menu

@Dao
interface MenuDAO {

    @Query("SELECT * FROM menu")
    fun obtenerMenus():LiveData<List<Menu>>

    @Query("SELECT * FROM menu WHERE idMenu = :id")
    fun obtenerMenu(id:Int):LiveData<Menu>

    @Insert
    fun insertarMenu(vararg menu:Menu):List<Long>

    @Update
    fun actualizarMenu(menu:Menu)

    @Delete
    fun eliminarMenu(menu:Menu)
}