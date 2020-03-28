package com.example.proyectofragmentos;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyectofragmentos.adaptador.Singleton;

public class materia_rv extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.materia_ly);
        int idMateria = intent.getIntExtra("idMateria", 0);
        llenarInformacionMateria(idMateria);

    }

    public void llenarInformacionMateria(int idMateria) {

        TextView cedula = findViewById(R.id.textViewCedula);
        cedula.setText(Singleton.getInstance().materias.get(idMateria).getCodigo());

        TextView nombre = findViewById(R.id.textViewNombre);
        nombre.setText(Singleton.getInstance().materias.get(idMateria).getNombre());

        TextView apellido = findViewById(R.id.textViewApellido);
        apellido.setText(Singleton.getInstance().materias.get(idMateria).getProfesor());

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

    private void irAEditar(){

    }

    private void irAMaterias(){

    }

}
