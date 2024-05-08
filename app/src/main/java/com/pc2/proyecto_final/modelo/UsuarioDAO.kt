package com.pc2.proyecto_final.modelo

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.util.Log
import android.widget.EditText
import com.pc2.proyecto_final.R
import com.pc2.proyecto_final.entidades.usuarios
import com.pc2.proyecto_final.util.BaseDatos
import java.lang.Exception

class UsuarioDAO(context: Context) {
    private var base: BaseDatos = BaseDatos(context)
    public var snombre: String=""
    public var sapellido: String=""

    fun registrarUsuario(usuarios: usuarios):String{
        var respuesta=""
        val db = base.writableDatabase

        try {
            val valores = ContentValues()
            valores.put("nombre",usuarios.nombre)
            valores.put("apellido",usuarios.apellido)
            valores.put("correo",usuarios.correo)
            valores.put("contrasena",usuarios.contrasena)
            valores.put("contrasena2",usuarios.contrasena2)
            //db.insert("personas",null,valores)
            val r = db.insert("usuarios",null,valores)

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

    fun ingresar(usuarios: usuarios): String{
        var respuesta=""
        val db = base.writableDatabase
        val login= usuarios.correo
        val password=usuarios.contrasena

        // var r = db.rawQuery("SELECT * FROM usuarios WHERE correo='"+login +"' and contrasena= '"+password+"'", null)
        var r = db.rawQuery(" SELECT correo, contrasena, nombre, apellido FROM usuarios WHERE correo= '"+ login +"'  and contrasena= '"+ password +"'", null)

        if (r.moveToFirst()){
            val slog=r.getString(0)
            val spass=r.getString(1)
            snombre=r.getString(2)
            sapellido=r.getString(3)

                respuesta="1"
                return  respuesta
        }else {
            respuesta = "Usuario o Contraseña no son correctos"
            return respuesta
        }
    }

}