package com.pc2.proyecto_final.modelo

import android.content.ContentValues
import android.content.Context
import com.pc2.proyecto_final.entidades.solicitud
import com.pc2.proyecto_final.entidades.usuarios
import com.pc2.proyecto_final.util.BaseDatos
import java.lang.Exception

class SolicitudDAO(context: Context) {

    private var base: BaseDatos = BaseDatos(context)

    fun registrarSolicitud(solicitud: solicitud):String{
        var respuesta=""
        val db = base.writableDatabase

        try {
            val valores = ContentValues()
            valores.put("ruc",solicitud.ruc)
            valores.put("trabajo",solicitud.trabajo)
            valores.put("cantidad",solicitud.cantidad)
            valores.put("fecha",solicitud.fecha)
            valores.put("material",solicitud.material)
            valores.put("ancho",solicitud.ancho)
            valores.put("largo",solicitud.largo)
            valores.put("tira",solicitud.tira)
            valores.put("retira",solicitud.retira)
            valores.put("barnizt",solicitud.barnizt)
            valores.put("barnizr",solicitud.barnizr)
            valores.put("plastt",solicitud.plastt)
            valores.put("plastr",solicitud.plastr)
            valores.put("troquelado",solicitud.troquelado)
            valores.put("doblado",solicitud.doblado)

            //db.insert("personas",null,valores)
            val r = db.insert("solicitud",null,valores)

            if (r == -1L) {
                respuesta = "Ocurrió un error al insertar"
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