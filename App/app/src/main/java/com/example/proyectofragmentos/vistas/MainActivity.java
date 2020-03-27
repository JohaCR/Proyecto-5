package com.example.proyectofragmentos.vistas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.proyectofragmentos.R;
import com.example.proyectofragmentos.clases.Materia;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static ArrayList<Materia> materias;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
