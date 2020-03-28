package com.example.proyectofragmentos.adaptador;

import android.content.Context;

import com.example.proyectofragmentos.clases.Estudiante;
import com.example.proyectofragmentos.clases.Materia;

import java.util.ArrayList;

public final class Singleton {

    private static Singleton instancia;
    public ArrayList<Materia> materias;
    public ArrayList<Estudiante> estudiantes;

    private Singleton() {
        AdaptadorArchivo adaptadorArchivo = new AdaptadorArchivo();
        this.materias = adaptadorArchivo.getMaterias();
        this.estudiantes = adaptadorArchivo.getEstudiantes();
    }

    public static Singleton getInstance() {
        if (instancia == null) {
            instancia = new Singleton();
        }
        return instancia;
    }

}
