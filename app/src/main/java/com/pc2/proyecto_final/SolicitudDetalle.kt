package com.pc2.proyecto_final

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.pc2.proyecto_final.entidades.clientes
import com.pc2.proyecto_final.entidades.solicitud
import com.pc2.proyecto_final.entidades.usuarios
import com.pc2.proyecto_final.modelo.ClienteDAO
import com.pc2.proyecto_final.modelo.SolicitudDAO
import com.pc2.proyecto_final.modelo.UsuarioDAO

class SolicitudDetalle : AppCompatActivity() {
    private lateinit var txtruc: EditText
    private lateinit var txttrabajo: EditText
    private lateinit var txtcantidad: EditText
    private lateinit var txtfecha: EditText
    private lateinit var txtmaterial: EditText
    private lateinit var txtancho: EditText
    private lateinit var txtlargo: EditText
    private lateinit var txttira: EditText
    private lateinit var txtretira: EditText
    private lateinit var chkbarnizt: CheckBox
    private lateinit var chkbarnizr: CheckBox
    private lateinit var chkplast: CheckBox
    private lateinit var chkplasr: CheckBox
    private lateinit var chktroquelado: CheckBox
    private lateinit var chkdoblado: CheckBox
    private lateinit var btngrabar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_solicitud_detalle)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // recepciona la variable del formulario anterior

        /* val intent = getIntent();
        val sRuc = intent.getStringExtra("ruc");
        var sTrabajo = intent.getStringExtra("trabajo");
        var sCantidad = intent.getStringExtra("cantidad");
        var sFecha = intent.getStringExtra("fecha");*/


        asignarReferencias()
    }

    fun asignarReferencias(){
        txtmaterial = findViewById(R.id.txtmaterial)
        txtancho= findViewById(R.id.txtancho)
        txtlargo=findViewById(R.id.txtlargo)
        txttira=findViewById(R.id.txttira)
        txtretira=findViewById(R.id.txtretira)
        chkbarnizt=findViewById(R.id.chkbarnizt)
        chkbarnizr=findViewById(R.id.chkbarnizr)
        chkplast=findViewById(R.id.chkplast)
        chkplasr=findViewById(R.id.chkplasr)
        chktroquelado=findViewById(R.id.chktroquelado)
        chkdoblado=findViewById(R.id.chkdoblado)

        btngrabar=findViewById(R.id.btngrabsol)

        btngrabar.setOnClickListener {
            capturaDatos()
        }
    }

    fun capturaDatos(){

        val material=txtmaterial.text.toString()
        val ancho=txtancho.text.toString()
        val largo=txtlargo.text.toString()
        val tira=txttira.text.toString()
        val retira=txtretira.text.toString()
        val barnizt=chkbarnizt.isChecked.toString()
        val barnizr=chkbarnizr.isChecked.toString()
        val plastt=chkplast.isChecked.toString()
        val plastr=chkplasr.isChecked.toString()
        val troquelado=chktroquelado.isChecked.toString()
        val doblado=chkdoblado.isChecked.toString()

        var valida=true

        if (material.isEmpty()){
            valida=false
            txtmaterial.setError("Material es obligatorio")
        }

        if (ancho.isEmpty()){
            valida=false
            txtancho.setError("El ancho es obligatorio")
        }

        if (largo.isEmpty()){
            valida=false
            txtlargo.setError("El largo es obligatorio")
        }

        if (tira.isEmpty()){
            valida=false
            txttira.setError("la tira es obligatorio")
        }

        if (retira.isEmpty()){
            valida=false
            txtretira.setError("la retira es obligatorio")
        }


        if (valida){
            val objeto = solicitud()
            // recepciona la variable del formulario anterior

            val intent = getIntent();

            val sRuc = intent.getStringExtra("ruc");
            val sTrabajo = intent.getStringExtra("trabajo");
            val sCantidad = intent.getStringExtra("cantidad");
            val sFecha = intent.getStringExtra("fecha");


            objeto.ruc=sRuc.toString()
            objeto.trabajo=sTrabajo.toString()
            objeto.cantidad=sCantidad.toString()
            objeto.fecha=sFecha.toString()
            objeto.material=material
            objeto.ancho=ancho
            objeto.largo=largo
            objeto.tira=tira.toInt()
            objeto.retira=retira.toInt()
            objeto.barnizt=barnizt.toBoolean()
            objeto.barnizr=barnizr.toBoolean()
            objeto.plastt=plastt.toBoolean()
            objeto.plastr=plastr.toBoolean()
            objeto.troquelado=troquelado.toBoolean()
            objeto.doblado=doblado.toBoolean()

            registrar(objeto)
        }
    }

    fun registrar (objeto: solicitud) {
        val solicitudDAO = SolicitudDAO(this)
            val mensaje = solicitudDAO.registrarSolicitud(objeto)
            mostrarMensaje(mensaje)

    }

    fun mostrarMensaje(mensaje:String){

        val ventana= AlertDialog.Builder(this)
        ventana.setTitle("Mensaje Informativo")
        ventana.setMessage(mensaje)
        ventana.setPositiveButton("Aceptar", DialogInterface.OnClickListener{ dialog, which ->
            val intent = Intent (this,Menu::class.java)
            startActivity(intent)
        })

        ventana.create().show()
    }

}