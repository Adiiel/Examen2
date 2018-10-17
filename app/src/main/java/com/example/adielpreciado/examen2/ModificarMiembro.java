package com.example.adielpreciado.examen2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class ModificarMiembro extends Activity implements View.OnClickListener {
    EditText edtnombre, edtapellido, edttelefono, edtdireccion;
    Button btnActualizar, btnEliminar;
    long member_id;
    SQLControlador dbcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modificar_miembro);

        dbcon = new SQLControlador(this);
        dbcon.abrirBaseDeDatos();

        edtnombre = (EditText) findViewById(R.id.et_miembro_id);
        edtapellido = (EditText) findViewById(R.id.edtapellido);
        edttelefono = (EditText) findViewById(R.id.edtelefono);
        edtdireccion = (EditText) findViewById(R.id.edtdireccion);
        btnActualizar = (Button) findViewById(R.id.btnActualizar);
        btnEliminar = (Button) findViewById(R.id.btnEliminar);

        Intent i = getIntent();
        String memberID = i.getStringExtra("miembroId");
        String memberName = i.getStringExtra("miembroNombre");
        String menberApellido = i.getStringExtra("miembroApellido");
        String menberTelefono = i.getStringExtra("miembroTelefono");
        String memberDireccion = i.getStringExtra("miembroDireccion");
        member_id = Long.parseLong(memberID);

        edtnombre.setText(memberName);
        edtapellido.setText(menberApellido);
        edttelefono.setText(menberTelefono);
        edtdireccion.setText(memberDireccion);

        btnActualizar.setOnClickListener(this);
        btnEliminar.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.btnActualizar:
                String memName_upd = edtnombre.getText().toString();
                String menApellido_udp = edtapellido.getText().toString();
                String menTelefono_udp = edttelefono.getText().toString();
                String menDireccion_udp = edtdireccion.getText().toString();

                dbcon.actualizarDatos(member_id, memName_upd,menApellido_udp,menTelefono_udp, menDireccion_udp);
                this.returnHome();
                break;

            case R.id.btnEliminar:
                dbcon.deleteData(member_id);
                this.returnHome();
                break;
        }
    }
    public void returnHome(){
        Intent home_intent = new Intent(getApplicationContext(),
                MainActivity.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        startActivity(home_intent);
    }
}
