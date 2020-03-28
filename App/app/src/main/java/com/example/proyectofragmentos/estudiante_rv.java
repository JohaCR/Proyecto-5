package com.example.proyectofragmentos;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class estudiante_rv extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState){
        Intent intent = getIntent();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.estudiante_ly);
        int idEstudiante = intent.getIntExtra("idEstudiante",0);
        
    }

    public void llenarInformacionEstudiante(String idEstudiante){

    }

}
