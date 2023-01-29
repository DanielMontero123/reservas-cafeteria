package dev.android.appreservas

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import dev.android.appreservas.clases.Menu
import dev.android.appreservas.interfaces.MenuDAO

@Database(entities = [Menu::class], version = 1)
abstract class AppDatabase: RoomDatabase() {

    abstract fun menus():MenuDAO

    companion object{

        @Volatile
        private var INSTANCIA:AppDatabase? = null

        fun getDatabase(contexto: Context):AppDatabase {
            val tempInstancia = INSTANCIA
            if(tempInstancia != null){
                return tempInstancia
            }

            synchronized(this){
                val instancia = Room.databaseBuilder(
                    contexto.applicationContext,
                    AppDatabase::class.java,
                    "app_reservas"
                ).build()

                INSTANCIA = instancia

                return instancia
            }
        }

    }

}