package com.pc2.proyecto_final

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.pc2.proyecto_final.entidades.clientes
import com.pc2.proyecto_final.entidades.usuarios
import com.pc2.proyecto_final.modelo.ClienteDAO
import com.pc2.proyecto_final.modelo.UsuarioDAO

class Clientes : AppCompatActivity() {

    private lateinit var txtruc: EditText
    private lateinit var txtrazon: EditText
    private lateinit var txtdireccion: EditText
    private lateinit var btngrabacliente : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_clientes)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        asignarReferencias()

        /* val spinner : Spinner = findViewById(R.id.myspinner)
        val items = listOf("Soles","Dolares")

        val adapter = ArrayAdapter(this,android.R.layout.simple_spinner_item,items)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spinner.adapter=adapter

        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{


            override fun onItemSelected(parent: AdapterView<*>?,view: View?,position: Int,id: Long) {
                val selectedItem = items[position]
                Toast.makeText(this@Clientes,"$selectedItem",Toast.LENGTH_SHORT).show()

            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }


        } */


    }

    fun asignarReferencias(){
        txtruc = findViewById(R.id.txtruc)
        txtrazon= findViewById(R.id.txtrazon)
        txtdireccion= findViewById(R.id.txtdireccion)
         //txtmoneda=findViewById(R.id.myspinner)

        btngrabacliente=findViewById(R.id.btnregcliente)

        btngrabacliente.setOnClickListener {
            capturaDatos()
        }
    }

    fun capturaDatos(){

        val ruc=txtruc.text.toString()
        val razon=txtrazon.text.toString()
        val direccion=txtdireccion.text.toString()
       // val moneda=txtmoneda.text.toString()
        var valida=true

        if (ruc.isEmpty()){
            valida=false
            txtruc.setError("RUC es obligatorio")
        }

        if (razon.isEmpty()){
            valida=false
            txtrazon.setError("Razon Social es obligatorio")
        }

        if (direccion.isEmpty()){
            valida=false
            txtdireccion.setError("Dirección es obligatorio")
        }

        /*if (moneda.isEmpty()){
            valida=false
            txtmoneda.setError("Moneda es obligatorio")
        }*/

        if (valida){
            val objeto = clientes()
            objeto.ruc=ruc
            objeto.razon=razon
            objeto.direccion=direccion
            //objeto.moneda=moneda
            registrar(objeto)
        }
    }

    fun registrar (objeto: clientes) {
        val clientesDAO = ClienteDAO(this)
        val mensaje = clientesDAO.validarCliente(objeto)
        //val mensaje= clientesDAO.registrarCliente(objeto)
        //mostrarMensaje(mensaje)

        if (mensaje.equals("1")){
            val mensaje1 = clientesDAO.registrarCliente(objeto)
            mostrarMensaje(mensaje1)
        }else{
            mostrarMensajeError(mensaje)
        }
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

    fun mostrarMensajeError(mensaje:String){

        val ventana= AlertDialog.Builder(this)
        ventana.setTitle("Mensaje Informativo")
        ventana.setMessage(mensaje)
        ventana.setPositiveButton("Aceptar", DialogInterface.OnClickListener{ dialog, which ->
            val intent = Intent (this,Clientes::class.java)
            startActivity(intent)
        })

        ventana.create().show()
    }
}