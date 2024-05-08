package com.pc2.proyecto_final

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.pc2.proyecto_final.entidades.clientes
import com.pc2.proyecto_final.entidades.usuarios
import com.pc2.proyecto_final.modelo.ClienteDAO

class Solicitud : AppCompatActivity() {

    private lateinit var btnBuscar: ImageButton
    private lateinit var txtRuc: EditText
    public var sRazon: String=""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_solicitud)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        asignarReferencias()
    }

    fun asignarReferencias(){
        txtRuc = findViewById(R.id.txtruc)
        btnBuscar=findViewById(R.id.btnbuscar)

        btnBuscar.setOnClickListener {
            capturaDatos()
        }
    }

    fun capturaDatos(){

        val ruc=txtRuc.text.toString()
        var valida=true

        if (ruc.isEmpty()){
            valida=false
            txtRuc.setError("RUC es obligatorio")
        }


        if (valida){
            val objeto = clientes()
            objeto.ruc=ruc
            validar(objeto)
        }
    }

    fun validar (objeto: clientes) {
        val clientesDAO = ClienteDAO(this)
        val mensaje = clientesDAO.validarCliente(objeto)
        sRazon= clientesDAO.sRazon
        mostrarMensaje(mensaje)
    }

    fun mostrarMensaje(mensaje:String){

        if (mensaje.equals("1")) {
            val ventana = AlertDialog.Builder(this)
            ventana.setTitle("Mensaje Informativo")
            ventana.setMessage("El cliente no existe, debes crearlo")
            ventana.setPositiveButton("Aceptar", DialogInterface.OnClickListener { dialog, which ->
                val intent = Intent(this, Clientes::class.java)
                startActivity(intent)
            })

            ventana.create().show()
        }
        else{

            val ruc=txtRuc.text.toString()

                val intent = Intent(this, RegSolicitud::class.java)
                // envia la variable al otro formulario
                intent.putExtra("ruc",ruc)
                intent.putExtra("razon",sRazon)

                startActivity(intent)


        }

    }




}