package dev.android.appreservas

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_detalle_menu.*
import kotlinx.android.synthetic.main.activity_nuevo_menu.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DetalleMenuActivity : AppCompatActivity() {

    private lateinit var database: AppDatabase
    private lateinit var menu: dev.android.appreservas.clases.Menu
    private lateinit var menuLiveData: LiveData<dev.android.appreservas.clases.Menu>
    private val EDITAR = 2

    private var uriImagen: Uri? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_menu)
        cargarMenu()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.opEliminarMenu -> {
                eliminarMenu()
                true
            }
            R.id.opEditarMenu -> {
                editarMenu()
                true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater:MenuInflater = menuInflater
        inflater.inflate(R.menu.acciones_menu, menu)
        return true
    }


    private fun cargarMenu(){
        database = AppDatabase.getDatabase(this)
        val idMenu = intent.getIntExtra("id", 0)

        menuLiveData = database.menus().obtenerMenu(idMenu)
        menuLiveData.observe(this, Observer {
            menu = it

            txtDetalleMenuNombre.setText(menu.nombre)
            txtDetalleMenuDetalle.setText(menu.detalle)
            txtDetalleMenuPrecio.setText("$ ${menu.precio}")

            val uriImagen = ControladorImagen.obtenerUriImagen(this,idMenu.toLong())
            imgDetalleMenu.setImageURI(uriImagen)
        })
    }


    private fun eliminarMenu(){

        menuLiveData.removeObservers(this)
        CoroutineScope(Dispatchers.IO).launch {
            database.menus().eliminarMenu(menu)
            this@DetalleMenuActivity.finish()
        }
    }

    private fun editarMenu() {

        database = AppDatabase.getDatabase(this)

        menu.nombre = txtDetalleMenuNombre.text.toString()
        menu.detalle = txtDetalleMenuDetalle.text.toString()

        var idMenu = menu.idMenu
        CoroutineScope(Dispatchers.IO).launch {
            database.menus().actualizarMenu(menu)

            uriImagen?.let {
                ControladorImagen.guardarImagen(this@DetalleMenuActivity, idMenu.toLong(), it)
            }
            this@DetalleMenuActivity.finish()
        }
    }

}