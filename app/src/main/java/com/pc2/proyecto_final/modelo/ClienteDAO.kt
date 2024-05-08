package com.pc2.proyecto_final.modelo

import android.R.string
import android.content.ContentValues
import android.content.Context
import android.widget.EditText
import com.pc2.proyecto_final.entidades.clientes
import com.pc2.proyecto_final.entidades.usuarios
import com.pc2.proyecto_final.util.BaseDatos
import java.lang.Exception

class ClienteDAO(context: Context) {

    private var base: BaseDatos = BaseDatos(context)
    private lateinit var txtruc: EditText
    private lateinit var txtrazon: EditText
    public var sRuc: String=""
    public var sRazon: String=""

    fun validarCliente(clientes: clientes): String{
        var respuesta=""
        val db = base.writableDatabase
        val ruc= clientes.ruc

        var r = db.rawQuery(" SELECT ruc , razon FROM clientes WHERE ruc= '"+ ruc +"'", null)

        if (r.moveToFirst()){
            sRuc=r.getString(0)
            sRazon=r.getString(1)

            respuesta="El cliente ya esta registrado"
            return  respuesta
        }else {
            respuesta = "1"
            return respuesta
        }
    }

    fun registrarCliente(clientes: clientes):String{
        var respuesta=""
        val db = base.writableDatabase

        try {
            val valores = ContentValues()
            valores.put("ruc",clientes.ruc)
            valores.put("razon",clientes.razon)
            valores.put("direccion",clientes.direccion)

            val r = db.insert("clientes",null,valores)

            if (r == -1L) {
                respuesta = "Ocurrió un error al grabar"
            }
            else{
                respuesta="Se Grabó exitosamente"
            }

        }catch (e: Exception) {
            respuesta = e.message.toString()
        }finally {
            db.close()
        }
        return respuesta
    }
}