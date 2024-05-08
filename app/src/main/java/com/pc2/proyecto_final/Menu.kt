package com.pc2.proyecto_final

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.pc2.proyecto_final.util.BaseDatos

class Menu : AppCompatActivity() {

    private lateinit var btnPrincipal: ImageButton
    private lateinit var btnCliente: ImageButton
    private lateinit var btnSolicitud: ImageButton
    private lateinit var txtDatos : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_menu)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val intent = getIntent();
        var snombre = intent.getStringExtra("nombre");
        var sapellido = intent.getStringExtra("apellido");

        txtDatos=findViewById(R.id.textView12)
        txtDatos.setText("Bienvenido, "+snombre+" "+ sapellido)

        irPrincipal()
        irSolicitud()
        irCliente()
    }

    fun irPrincipal (){
        btnPrincipal=findViewById(R.id.btnprincipal)

        btnPrincipal.setOnClickListener{
            val intent = Intent (this,Menu::class.java)
            startActivity(intent)
        }

    }

    fun irSolicitud (){
        btnSolicitud=findViewById(R.id.btnsolicitud)

        btnSolicitud.setOnClickListener{
            val intent = Intent (this,Solicitud::class.java)
            startActivity(intent)
        }

    }

    fun irCliente (){
        btnCliente=findViewById(R.id.btncliente)

        btnCliente.setOnClickListener{
            val intent = Intent (this,Clientes::class.java)
            startActivity(intent)
        }

    }
}