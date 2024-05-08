package com.pc2.proyecto_final

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.pc2.proyecto_final.entidades.clientes
import com.pc2.proyecto_final.entidades.usuarios

class RegSolicitud : AppCompatActivity() {
    private lateinit var txtRuc: EditText
    private lateinit var txtRazon: EditText
    private lateinit var txtTrabajo: EditText
    private lateinit var txtCantidad: EditText
    private lateinit var txtFecha: EditText
    private lateinit var btnSiguiente: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_reg_solicitud)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        // recepciona la variable del formulario anterior

        val intent = getIntent();
        var sRuc = intent.getStringExtra("ruc");
        txtRuc=findViewById(R.id.txtruccli)
        txtRuc.setText(sRuc)

        var sRazon = intent.getStringExtra("razon");
        txtRazon=findViewById(R.id.txtcliente)
        txtRazon.setText(sRazon)

        asignarReferencias()

    }
    fun asignarReferencias(){
        txtRuc = findViewById(R.id.txtruccli)
        txtTrabajo=findViewById(R.id.txttrabajo)
        txtCantidad=findViewById(R.id.txtcantidad)
        txtFecha=findViewById(R.id.dtpfecha)
        btnSiguiente=findViewById(R.id.btnsiguiente)

        btnSiguiente.setOnClickListener {
            capturaDatos()
        }
    }


    fun capturaDatos(){

        val ruc=txtRuc.text.toString()
        val trabajo=txtTrabajo.text.toString()
        val cantidad=txtCantidad.text.toString()
        val fecha=txtFecha.text.toString()
        var valida=true


        if (ruc.isEmpty()){
            valida=false
            txtRuc.setError("Ruc es obligatorio")
        }

        if (trabajo.isEmpty()){
            valida=false
            txtTrabajo.setError("Trabajo es obligatorio")
        }

        if (cantidad.isEmpty()){
            valida=false
            txtCantidad.setError("Cantidad es obligatorio")
        }

        if (fecha.isEmpty()){
            valida=false
            txtFecha.setError("Fecha es obligatorio")
        }

        if (valida){
            enviarvariables()
        }
    }

    fun enviarvariables(){
        val ruc=txtRuc.text.toString()
        val trabajo=txtTrabajo.text.toString()
        val cantidad=txtCantidad.text.toString()
        val fecha=txtFecha.text.toString()

        val intent = Intent(this, SolicitudDetalle::class.java)

        // envia la variable al otro formulario
        intent.putExtra("ruc",ruc)
        intent.putExtra("trabajo",trabajo)
        intent.putExtra("cantidad",cantidad)
        intent.putExtra("fecha",fecha)

        startActivity(intent)
    }





}