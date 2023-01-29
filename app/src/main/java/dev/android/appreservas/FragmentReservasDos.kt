package dev.android.appreservas

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import java.lang.RuntimeException
import java.text.NumberFormat


class FragmentReservasDos : Fragment() {


    interface ComunicadorFragmentos {
        fun enviarDatos(dato:String, apellido:String)
        fun enviarTelefono(telefono:String)
        fun enviarMesa(mesa:String)
    }

    private var activityContenedora:ComunicadorFragmentos? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reservas_dos, container, false)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is ComunicadorFragmentos)
            activityContenedora = context
        else throw RuntimeException(
            context.toString() +
                    "debe implementarse la interfaz ComunicadorFragmentos"
        )
    }

    override fun onDetach() {
        super.onDetach()
        activityContenedora = null
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val btn: Button = requireView().findViewById(R.id.btnConfirmar)
        btn.setOnClickListener {
            val et: EditText = requireView().findViewById(R.id.txtNombreFragmento)
            val et2: EditText = requireView().findViewById(R.id.txtMesaFragmento)
            val et3 :EditText = requireView().findViewById(R.id.txtApellidoFragmento)
            val et4 :EditText = requireView().findViewById(R.id.txtTelefonoFragmento)
            activityContenedora!!.enviarDatos(et.text.toString(), et3.text.toString())
            activityContenedora!!.enviarMesa(et2.text.toString())
            activityContenedora!!.enviarTelefono(et4.text.toString())
        }
    }




}