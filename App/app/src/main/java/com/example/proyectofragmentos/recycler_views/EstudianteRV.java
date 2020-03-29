package com.example.proyectofragmentos.recycler_views;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyectofragmentos.R;
import com.example.proyectofragmentos.adaptador.Singleton;
import com.example.proyectofragmentos.clases.Estudiante;


/*
En esta clase se define el layout que va a usar el recycler view de estudiantes, que sera estudiante_ly
* */
public class EstudianteRV extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.estudiante_ly);
        int idEstudiante = intent.getIntExtra("idEstudiante", 0);
        llenarInformacionEstudiante(idEstudiante);

    }

    /*
    Funcion que  permite llenar los datos del layout estudiante_ly
    * */
    public void llenarInformacionEstudiante(int idEstudiante) {

        TextView cedula = findViewById(R.id.textViewCedula);
        cedula.setText(Singleton.getInstance().estudiantes.get(idEstudiante).getCedula());

        TextView nombre = findViewById(R.id.textViewNombre);
        nombre.setText(Singleton.getInstance().estudiantes.get(idEstudiante).getNombre());

        TextView apellido = findViewById(R.id.textViewApellido);
        apellido.setText(Singleton.getInstance().estudiantes.get(idEstudiante).getApellido());

        ImageButton editar = findViewById(R.id.imageButtonEditar);
        editar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                irAEditar();
            }
        });

        ImageButton borrar = findViewById(R.id.imageButtonBorrar);
        borrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

            }
        });

        ImageButton materias = findViewById(R.id.imageButtonMaterias);
        materias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                irAMaterias();
            }
        });

    }

    private void irAEditar(){}

    private void irAMaterias(){}



}
