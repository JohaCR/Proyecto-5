package com.example.proyectofragmentos.adaptador;

import android.content.Context;

import com.example.proyectofragmentos.clases.Estudiante;
import com.example.proyectofragmentos.clases.Materia;

import java.util.ArrayList;

public final class Singleton {

    private static Singleton instance;
    public ArrayList<Materia> materias;
    public ArrayList<Estudiante> estudiantes;

    private Singleton(Context contex) {
        new AdaptadorArchivo().leerMaterias(contex);
    }

    public static Singleton getInstance(Context context) {
        if (instance == null) {
            instance = new Singleton(context);
        }
        return instance;
    }

}
