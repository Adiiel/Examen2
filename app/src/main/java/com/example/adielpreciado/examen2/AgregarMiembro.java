package com.example.adielpreciado.examen2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AgregarMiembro extends Activity implements View.OnClickListener {
    EditText edtnombre, edtapellido, edttelefono, edtdireccion;
    Button btnAgregar, read_bt;
    SQLControlador dbconeccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agregar_miembro);
        edtnombre = (EditText) findViewById(R.id.et_miembro_id);
        edtapellido = (EditText) findViewById(R.id.edtapellido);
        edttelefono = (EditText) findViewById(R.id.edtelefono);
        edtdireccion = (EditText) findViewById(R.id.edtdireccion);
        btnAgregar = (Button) findViewById(R.id.btnAgregarId);

        dbconeccion = new SQLControlador(this);
        dbconeccion.abrirBaseDeDatos();
        btnAgregar.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.btnAgregarId:
                String name = edtnombre.getText().toString();
                String apellido = edtapellido.getText().toString();
                String telefono = edttelefono.getText().toString();
                String direccion = edtdireccion.getText().toString();
                dbconeccion.insertarDatos(name,apellido,telefono,direccion);
                Intent main = new Intent(AgregarMiembro.this, MainActivity.class)
                        .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(main);
                break;

            default:
                break;
        }
    }
}

