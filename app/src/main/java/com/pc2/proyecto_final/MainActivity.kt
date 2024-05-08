package com.pc2.proyecto_final

import android.content.DialogInterface
import android.content.Intent
import android.database.Cursor
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.pc2.proyecto_final.entidades.usuarios
import com.pc2.proyecto_final.modelo.UsuarioDAO
import com.pc2.proyecto_final.util.BaseDatos

class MainActivity : AppCompatActivity() {

    private lateinit var btnRegistrar: Button
    private lateinit var txtUsuario: EditText
    private lateinit var txtPassword: EditText
    private lateinit var btnLogin: Button
    public var nombre: String=""
    public var apellido: String=""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        irRegistrar()
        asignarReferencias()

    }
    fun irRegistrar (){
        val txtlink = findViewById<TextView>(R.id.link)

        txtlink.setOnClickListener{
            val intent = Intent (this,Registrar::class.java)
            startActivity(intent)
        }


       /* btnRegistrar=findViewById(R.id.btnregistrar)

        btnRegistrar.setOnClickListener{
            val intent = Intent (this,Registrar::class.java)
            startActivity(intent)
        }*/

    }

    fun asignarReferencias(){
        txtUsuario = findViewById(R.id.txtusuario)
        txtPassword= findViewById(R.id.txtpassword)
        btnLogin=findViewById(R.id.btnlogin)

        btnLogin.setOnClickListener {
            capturaDatos()
        }
    }

    fun capturaDatos(){
        val usuario=txtUsuario.text.toString()
        val password=txtPassword.text.toString()
        var valida=true

        if (usuario.isEmpty()){
            valida=false
            txtUsuario.setError("Usuario es obligatorio")
        }

        if (password.isEmpty()){
            valida=false
            txtPassword.setError("Contraseña es obligatorio")
        }

        if (valida){
            val objeto = usuarios()
            objeto.correo=usuario
            objeto.contrasena=password
            validar(objeto)
        }
    }
    fun validar (objeto:usuarios) {
        val usuarioDAO = UsuarioDAO(this)
        val mensaje = usuarioDAO.ingresar(objeto)
        nombre=usuarioDAO.snombre
        apellido=usuarioDAO.sapellido

        mostrarMensaje(mensaje)
    }

    fun mostrarMensaje(mensaje:String){
       if (mensaje.equals("1")) {

           val intent = Intent(this, Menu::class.java)

           // envia la variable al otro formulario
           intent.putExtra("nombre",nombre)
           intent.putExtra("apellido",apellido)

           startActivity(intent)

       }
        else {

           val ventana = AlertDialog.Builder(this)
           ventana.setTitle("Mensaje Informativo")
           ventana.setMessage(mensaje)

           ventana.setNegativeButton("Aceptar", DialogInterface.OnClickListener { dialog, which ->
               val intent = Intent(this, MainActivity::class.java)
               startActivity(intent)
           })
           ventana.create().show()
       }
    }




}