package com.example.adielpreciado.examen2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBhelper extends SQLiteOpenHelper {

    // Información de la tabla
    public static final String TABLE_MEMBER = "miembros";
    public static final String MIEMBRO_ID = "_id";
    public static final String MIEMBRO_NOMBRE = "nombre";
    public static final String MIEMBRO_APELLIDO = "apellido";
    public static final String MIEMBRO_DIRECCION = "direccion";
    public static final String MIEMBRO_TELEFONO = "telfono";
    // información del a base de datos
    static final String DB_NAME = "DBMIEMBRO";
    static final int DB_VERSION = 1;

    // Información de la base de datos
    private static final String CREATE_TABLE = "create table " + TABLE_MEMBER +
            "(" + MIEMBRO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + MIEMBRO_NOMBRE
            + " TEXT NOT NULL, "+MIEMBRO_APELLIDO + " TEXT NOT NULL,"+MIEMBRO_TELEFONO+" TEXT NOT NULL," +
            ""+MIEMBRO_DIRECCION+" TEXT NOT NULL);";

    public DBhelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEMBER);
        onCreate(db);
    }
}
