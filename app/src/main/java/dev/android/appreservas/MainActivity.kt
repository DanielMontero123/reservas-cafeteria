package dev.android.appreservas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import dev.android.appreservas.clases.AppReservas.Companion.preferencias
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnLoginClick()
        if(revisarEstado()){
            startActivity(Intent(this,OpcionesActivity::class.java))
        }
    }

    private fun btnLoginClick(){
        btnLogin.setOnClickListener {
            if(edtNombre.text.toString().equals("") || edtPassword.text.toString().equals("")){
                val toast = Toast.makeText(this, "Campos Vacíos", Toast.LENGTH_LONG)
                toast.show()
            } else {
                preferencias.guardarUsuario(edtNombre.text.toString())
                preferencias.guardarEstado(btnRecuerdame.isChecked())
                val intent = Intent(this, OpcionesActivity::class.java)
                startActivity(intent)
            }
        }
    }

    private fun revisarEstado():Boolean{
        return preferencias.devolverEstado()
    }

}