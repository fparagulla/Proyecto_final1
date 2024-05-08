package com.pc2.proyecto_final

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.pc2.proyecto_final.entidades.usuarios
import com.pc2.proyecto_final.modelo.UsuarioDAO

class Registrar : AppCompatActivity() {

    private lateinit var txtnombre: EditText
    private lateinit var txtapellido: EditText
    private lateinit var txtcorreo: EditText
    private lateinit var txtcontrasena: EditText
    private lateinit var txtcontrasena2: EditText
    private lateinit var btnRegistrar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registrar)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        asignarReferencias()
    }



    fun asignarReferencias(){
        txtnombre = findViewById(R.id.txtnombre)
        txtapellido= findViewById(R.id.txtapellidos)
        txtcorreo= findViewById(R.id.txtcorreo)
        txtcontrasena=findViewById(R.id.txtcontrasena)
        txtcontrasena2=findViewById(R.id.txtrepcontrasena)

        btnRegistrar=findViewById(R.id.btnregusuario)

        btnRegistrar.setOnClickListener {
            capturaDatos()
        }
    }

    fun capturaDatos(){

        val nombres=txtnombre.text.toString()
        val apellido=txtapellido.text.toString()
        val mail=txtcorreo.text.toString()
        val contrasena=txtcontrasena.text.toString()
        val contrasena2=txtcontrasena2.text.toString()
        var valida=true


        if (nombres.isEmpty()){
            valida=false
            txtnombre.setError("Nombres es obligatorio")
        }

        if (apellido.isEmpty()){
            valida=false
            txtapellido.setError("Apellidos son obligatorio")
        }

        if (mail.isEmpty()){
            valida=false
            txtcorreo.setError("Correo es obligatorio")
        }

        if (contrasena.isEmpty()){
            valida=false
            txtcontrasena.setError("Contraseña es obligatorio")
        }

        if (contrasena2.isEmpty()){
            valida=false
            txtcontrasena2.setError("Contraseña es obligatorio")
        }

        if (contrasena != contrasena2){
            valida=false
            txtcontrasena.setError("Las contraseñas no coinciden")
        }

        if (valida){
            val objeto = usuarios()
            objeto.nombre=nombres
            objeto.apellido=apellido
            objeto.correo=mail
            objeto.contrasena=contrasena
            objeto.contrasena2=contrasena2
            registrar(objeto)
        }
    }

    fun registrar (objeto:usuarios) {
        val usuarioDAO = UsuarioDAO(this)
        val mensaje = usuarioDAO.registrarUsuario(objeto)

        mostrarMensaje(mensaje)
    }

    fun mostrarMensaje(mensaje:String){
        val ventana= AlertDialog.Builder(this)
        ventana.setTitle("Mensaje Informativo")
        ventana.setMessage(mensaje)
        //ventana.setPositiveButton("Aceptar",null)
        ventana.setPositiveButton("Aceptar", DialogInterface.OnClickListener{ dialog, which ->
            val intent = Intent (this,MainActivity::class.java)
            startActivity(intent)
        })

        ventana.create().show()
    }
}

