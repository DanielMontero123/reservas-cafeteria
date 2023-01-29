package dev.android.appreservas.clases

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import dev.android.appreservas.ControladorImagen
import dev.android.appreservas.R
import kotlinx.android.synthetic.main.fila_menu.view.*

class MenuAdapter(
    private val contexto:Context,
    private val listaMenus:List<Menu>):ArrayAdapter<Menu>(contexto, 0, listaMenus) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val layout = LayoutInflater.from(contexto).inflate(R.layout.fila_menu, parent, false)
        val menu = listaMenus[position]
        layout.txtFilaMenu.setText(menu.nombre)
        layout.txtFilaPrecio.setText("$ ${menu.precio}")
        val uriImagen = ControladorImagen.obtenerUriImagen(contexto,menu.idMenu.toLong())
        layout.imgFilaMenu.setImageURI(uriImagen)

        return layout
    }
}

