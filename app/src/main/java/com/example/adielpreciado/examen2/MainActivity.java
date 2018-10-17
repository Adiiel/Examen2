package com.example.adielpreciado.examen2;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button btnagregarMiembro;
    ListView lista;
    SQLControlador dbconnecion;
    TextView tv_miemID, tv_miemNombre,tv_miemApellido,tv_miemTelefono,tv_miemDireccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbconnecion = new SQLControlador(this);
        dbconnecion.abrirBaseDeDatos();
        btnagregarMiembro = (Button) findViewById(R.id.btnAgregarMiembro);
        lista = (ListView) findViewById(R.id.listViewMiembros);
        //ACCION DEL BOTON AGREGAR MIEMBRO
        btnagregarMiembro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iagregar = new Intent(MainActivity.this, AgregarMiembro.class);
                startActivity(iagregar);
            }
        });
        // Tomar los datos desde la base de datos para poner en el curso y después en el adapter
        Cursor cursor = dbconnecion.leerDatos();

        String[] from = new String[]{
                DBhelper.MIEMBRO_ID,
                DBhelper.MIEMBRO_NOMBRE,
                DBhelper.MIEMBRO_APELLIDO,
                DBhelper.MIEMBRO_TELEFONO,
                DBhelper.MIEMBRO_DIRECCION
        };
        int[] to = new int[]{
                R.id.miembro_id,
                R.id.miembro_nombre,
                R.id.miembro_apellido,
                R.id.miembro_telefono,
                R.id.miembro_direccion

        };

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                MainActivity.this, R.layout.formato_fila, cursor, from, to);

        adapter.notifyDataSetChanged();
        lista.setAdapter(adapter);

        // acción cuando hacemos click en item para poder modificarlo o eliminarlo
        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {

                tv_miemID = (TextView) view.findViewById(R.id.miembro_id);
                tv_miemNombre = (TextView) view.findViewById(R.id.miembro_nombre);
                tv_miemApellido=(TextView)view.findViewById(R.id.miembro_apellido);
                tv_miemTelefono=(TextView)view.findViewById(R.id.miembro_telefono);
                tv_miemDireccion=(TextView)view.findViewById(R.id.miembro_direccion);

                String aux_miembroId = tv_miemID.getText().toString();
                String aux_miembroNombre = tv_miemNombre.getText().toString();
                String aux_miembroApellido= tv_miemApellido.getText().toString();
                String aux_miembroTelefono = tv_miemTelefono.getText().toString();
                String aux_miembroDireccion = tv_miemDireccion.getText().toString();

                Intent modify_intent = new Intent(getApplicationContext(), ModificarMiembro.class);
                modify_intent.putExtra("miembroId", aux_miembroId);
                modify_intent.putExtra("miembroNombre", aux_miembroNombre);
                modify_intent.putExtra("miembroApellido", aux_miembroApellido);
                modify_intent.putExtra("miembroTelefono", aux_miembroTelefono);
                modify_intent.putExtra("miembroDireccion", aux_miembroDireccion);
                startActivity(modify_intent);
            }
        });
    }  //termina el onCreate
} //termina clase
