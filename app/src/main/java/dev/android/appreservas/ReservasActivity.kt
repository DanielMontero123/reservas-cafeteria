package dev.android.appreservas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.android.appreservas.databinding.ActivityReservasBinding


class ReservasActivity : AppCompatActivity(), FragmentReservasDos.ComunicadorFragmentos {

    lateinit var binding: ActivityReservasBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservas)

        binding = ActivityReservasBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnVisualizar.setOnClickListener {
            val transaccion = supportFragmentManager.beginTransaction()
            val fragmento = FragmentReservasUno()
            transaccion.replace(R.id.contenedor,fragmento)
            transaccion.addToBackStack(null)
            transaccion.commit()
        }

        binding.btnReservar.setOnClickListener {
            val transaccion = supportFragmentManager.beginTransaction()
            val fragmento = FragmentReservasDos()
            transaccion.replace(R.id.contenedor,fragmento)
            transaccion.addToBackStack(null)
            transaccion.commit()
        }

        binding.btnInformacion.setOnClickListener {
            val transaccion = supportFragmentManager.beginTransaction()
            val fragmento = FragmentReservasTres()
            transaccion.replace(R.id.contenedor,fragmento)
            transaccion.addToBackStack(null)
            transaccion.commit()
        }
    }


    override fun enviarDatos(dato: String, apellido: String) {
        binding.txtNombreReservas.setText(dato + " " + apellido)
    }

    override fun enviarTelefono(telefono: String) {
        binding.txtTelefonoReservas.setText(telefono)
    }

    override fun enviarMesa(mesa: String) {
        binding.txtMesaReservas.setText(mesa)
    }


}