package dev.android.appreservas

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_nuevo_menu.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NuevoMenuActivity : AppCompatActivity() {

    private val SELECCIONAR = 1
    private var uriImagen:Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nuevo_menu)
        cargar_imagen()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when {
            requestCode == SELECCIONAR && resultCode == Activity.RESULT_OK -> {
                uriImagen = data!!.data
                imgMenuNuevo.setImageURI(uriImagen)
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.opGuardarMenu -> {
                registrarMenu()
                true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.accion_guardar_menu, menu)
        return true
    }

    private fun cargar_imagen(){
        imgMenuNuevo.setOnClickListener {
            ControladorImagen.seleccionarFotoGaleria(this, SELECCIONAR)
        }
    }

    private fun registrarMenu(){

        val database = AppDatabase.getDatabase(this)

        val nombre = txtMenuNombre.text.toString()
        val detalle = txtMenuDetalle.text.toString()
        val precio = txtMenuPrecio.text.toString()

        val menu = dev.android.appreservas.clases.Menu(nombre, detalle, precio.toDouble(), R.drawable.placeholder)
        CoroutineScope(Dispatchers.IO).launch {
            val id = database.menus().insertarMenu(menu)[0]
            uriImagen?.let {
                ControladorImagen.guardarImagen(this@NuevoMenuActivity,id,it)
            }
            this@NuevoMenuActivity.finish()
        }
    }
}