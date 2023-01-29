package dev.android.appreservas.clases

import android.app.Application

class AppReservas:Application() {
    companion object{
        lateinit var preferencias:Preferencias
    }

    override fun onCreate() {
        super.onCreate()
        preferencias = Preferencias(applicationContext)
    }
}