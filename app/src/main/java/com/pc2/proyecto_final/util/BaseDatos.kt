package com.pc2.proyecto_final.util

import android.content.Context
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteDatabase

class BaseDatos (context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null,VERSION) {

    companion object {
        private const val DATABASE_NAME ="exitunobd.db"
        private const val VERSION=1
    }

    override fun onCreate(db: SQLiteDatabase){

        val sql="CREATE TABLE IF NOT EXISTS clientes"+
                "( id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                "ruc text NOT NULL, "+
                "razon text NOT NULL, "+
                "direccion text NOT NULL); "

        db.execSQL(sql)

        val sql1="CREATE TABLE IF NOT EXISTS usuarios"+
                "( id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                "nombre text NOT NULL, "+
                "apellido text NOT NULL, "+
                "correo text NOT NULL, " +
                "contrasena text NOT NULL, " +
                "contrasena2 text NOT NULL);"

        db.execSQL(sql1)

        val sql2="CREATE TABLE IF NOT EXISTS solicitud"+
                "( id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                "ruc text NOT NULL, "+
                "trabajo text NOT NULL, "+
                "cantidad text NOT NULL, " +
                "fecha text NOT NULL, " +
                "material text NOT NULL," +
                "ancho text NOT NULL,"+
                "largo text NOT NULL,"+
                "tira text NOT NULL,"+
                "retira text NOT NULL,"+
                "barnizt boolean NOT NULL,"+
                "barnizr boolean NOT NULL,"+
                "plastt boolean NOT NULL,"+
                "plastr boolean NOT NULL,"+
                "troquelado boolean NOT NULL,"+
                "doblado boolean NOT NULL);"

        db.execSQL(sql2)

    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int){
        db.execSQL("DROP TABLE IF EXISTS usuarios")
        onCreate(db)

        db.execSQL("DROP TABLE IF EXISTS clientes")
        onCreate(db)

        db.execSQL("DROP TABLE IF EXISTS solicitud")
        onCreate(db)

    }



}