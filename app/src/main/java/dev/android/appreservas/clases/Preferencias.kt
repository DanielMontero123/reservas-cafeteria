package dev.android.appreservas.clases

import android.content.Context
import dev.android.appreservas.R

class Preferencias(val context: Context) {
    val SHARED_APP = "MyApp"
    val SHARED_ESTADO = "estado"
    val SHARED_NOMBRE = "nombre"
    val SHARED_CLAVE = "clave"

    val almacenamiento = context.getSharedPreferences(SHARED_APP,Context.MODE_PRIVATE)

    fun guardarEstado(estado:Boolean){
        almacenamiento.edit().putBoolean(SHARED_ESTADO,estado).apply()
    }

    fun devolverEstado():Boolean{
        return almacenamiento.getBoolean(SHARED_ESTADO,false)
    }

    fun guardarUsuario(estado:String){
        almacenamiento.edit().putString(SHARED_NOMBRE,estado).apply()
    }

    fun devolverUsuario():String{
        return almacenamiento.getString(SHARED_NOMBRE,"").toString()
    }



}