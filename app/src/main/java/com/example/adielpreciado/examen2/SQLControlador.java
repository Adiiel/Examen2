package com.example.adielpreciado.examen2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class SQLControlador {
    private DBhelper dbhelper;
    private Context ourcontext;
    private SQLiteDatabase database;

    public SQLControlador(Context c) {
        ourcontext = c;
    }

    public SQLControlador abrirBaseDeDatos() throws SQLException {
        dbhelper = new DBhelper(ourcontext);
        database = dbhelper.getWritableDatabase();
        return this;
    }

    public void cerrar() {
        dbhelper.close();
    }

    public void insertarDatos(String name, String apellido, String telefono, String direccion) {
        ContentValues cv = new ContentValues();
        cv.put(DBhelper.MIEMBRO_NOMBRE, name);
        cv.put(DBhelper.MIEMBRO_APELLIDO, apellido);
        cv.put(DBhelper.MIEMBRO_TELEFONO, telefono);
        cv.put(DBhelper.MIEMBRO_DIRECCION, direccion);
        database.insert(DBhelper.TABLE_MEMBER, null, cv);
    }

    public Cursor leerDatos() {
        String[] todasLasColumnas = new String[]{
                DBhelper.MIEMBRO_ID, DBhelper.MIEMBRO_NOMBRE, DBhelper.MIEMBRO_APELLIDO, DBhelper.MIEMBRO_TELEFONO, DBhelper.MIEMBRO_DIRECCION
        };
        Cursor c = database.query(DBhelper.TABLE_MEMBER, todasLasColumnas, null, null, null, null, null);
        if (c != null) {
            c.moveToFirst();
        }
        return c;
    }

    public int actualizarDatos(long memberID, String memberName, String memberApellido, String memberTelefono, String memberDireccion) {
        ContentValues cvActualizar = new ContentValues();
        cvActualizar.put(DBhelper.MIEMBRO_NOMBRE,memberName);
        cvActualizar.put(DBhelper.MIEMBRO_APELLIDO,memberApellido);
        cvActualizar.put(DBhelper.MIEMBRO_TELEFONO,memberTelefono);
        cvActualizar.put(DBhelper.MIEMBRO_DIRECCION,memberDireccion);
        int i = database.update(DBhelper.TABLE_MEMBER,cvActualizar,DBhelper.MIEMBRO_ID + "="+ memberID, null);
        return i;
    }
    public void deleteData(long memberID){
        database.delete(DBhelper.TABLE_MEMBER,DBhelper.MIEMBRO_ID+"="+memberID,null);
    }
}
