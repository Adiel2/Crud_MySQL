package com.example.crud;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;

import java.util.ArrayList;

public class ConsultaSpinner extends AppCompatActivity {
    private Spinner sp_options;
    private TextView tv_cod, tv_descripcion, tv_precio;


    ConexionSQLite conexion = new ConexionSQLite(this);
    Dto datos = new Dto();

    private FloatingActionMenu menu;
    private FloatingActionButton item1, item2, item3;

    modal_Toast_Custom modal = new modal_Toast_Custom();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta_spinner);

        sp_options = (Spinner)findViewById(R.id.sp_options);
        tv_cod = (TextView)findViewById(R.id.tv_cod);
        tv_descripcion = (TextView)findViewById(R.id.tv_descripcion);
        tv_precio = (TextView)findViewById(R.id.tv_precio);

        menu = findViewById(R.id.menu_fab);
        item1 = findViewById(R.id.item1);
        item2 = findViewById(R.id.item2);
        item3 = findViewById(R.id.item3);

        menu.setOnMenuToggleListener(new FloatingActionMenu.OnMenuToggleListener() {
            @Override
            public void onMenuToggle(boolean opened) {
                if (opened){
                    Toast.makeText(ConsultaSpinner.this, "Menu Abierto",Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(ConsultaSpinner.this, "Menu Cerrado", Toast.LENGTH_SHORT).show();
                }
            }
        });
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
                    public void onClick(View v) {
                if (menu.isOpened()) {
                    menu.close(true);
                }
            }
        });

        item1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ConsultaSpinner.this,MainActivity.class);
                startActivity(intent);
            }
        });

        item2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modal.dialogAbout(ConsultaSpinner.this);
            }
        });


        item3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });




//conexion.obtenerListaArticulos();
        conexion.consultaListaArticulos();

        ArrayAdapter<CharSequence> adaptador = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, conexion.obtenerListaArticulos());
        sp_options.setAdapter(adaptador);
        sp_options.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position,
                                       long l) {
                if (position!=0){
                    tv_cod.setText("Código: "+conexion.consultaListaArticulos().get(position-
                            1).getCodigo());
                    tv_descripcion.setText("Descripción: "+conexion.consultaListaArticulos().get(position-1).getDescripcion());
                            tv_precio.setText("Precio: "+String.valueOf(conexion.consultaListaArticulos().get(position-1).getPrecio()));
                }else{
                    tv_cod.setText("Código: ");
                    tv_descripcion.setText("Descripción: ");
                    tv_precio.setText("Precio: ");
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

}
