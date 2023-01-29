package dev.android.appreservas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import dev.android.appreservas.clases.AppReservas.Companion.preferencias
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_opciones.*

class OpcionesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_opciones)
        clickBotones()
        texto()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater:MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_superior, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.opCerrarSesion ->{
                preferencias.guardarEstado(false)
                this.finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun texto(){

        if (preferencias.devolverEstado() == false){
            txtBienvenido.setText("")
        }else{
            txtBienvenido.setText(" Bienvenido " + preferencias.devolverUsuario())
        }


    }

    private fun clickBotones(){
        btnMenu.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }
        btnReservas.setOnClickListener {
            val intent = Intent(this, ReservasActivity::class.java)
            startActivity(intent)
        }
        btnBusquedas.setOnClickListener {
            val intent = Intent(this, BusquedasActivity::class.java)
            startActivity(intent)
        }
    }
}