package com.example.proyectofragmentos.clases;



import android.util.Log;

import com.example.proyectofragmentos.MainActivity;
import com.example.proyectofragmentos.adaptador.AdaptadorArchivo;
import com.example.proyectofragmentos.adaptador.Singleton;

import java.util.ArrayList;

public class Materia {
    private String codigo;
    private String nombre;
    private String profesor;
    private ArrayList<Estudiante> estudiantesInscritos;

    public Materia(String codigo, String nombre, String profesor){
        this.codigo = codigo;
        this.nombre = nombre;
        this.profesor = profesor;
        this.estudiantesInscritos = new ArrayList<Estudiante>();
    }

    public void guardarEnArchivo(){
        new AdaptadorArchivo().escribirArchivo("materias.csv", this.toString());
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getProfesor() {
        return profesor;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }

    public ArrayList<Estudiante> getEstudiantesInscritos() {
        return estudiantesInscritos;
    }

    public void setEstudiantesInscritos(ArrayList<Estudiante> estudiantesInscritos) {
        this.estudiantesInscritos = estudiantesInscritos;
    }

    public void agregarEstudiante(Estudiante estudiante){

        estudiantesInscritos.add(estudiante);
        Log.i("testingxd", "inscrito");
        new AdaptadorArchivo().escribirArchivo("estudiantes.csv", this.codigo + "," + estudiante.toString());
    }

    public void quitarEstudiante(Estudiante estudiante){
        estudiantesInscritos.remove(estudiante);
    }

    public boolean estaInscrito(Estudiante estudianteARevisar){
        boolean estaInscrito = false;
        for (Estudiante estudiante: this.estudiantesInscritos
             ) {
            if(estudiante == estudianteARevisar){
                estaInscrito = true;
            }
        }
        return estaInscrito;
    }

    public void eliminarMateria(){
        Singleton.getInstance().materias.remove(this);
        new AdaptadorArchivo().eliminarArchivoMaterias();
    }

    public String toString(){
        return this.codigo + "," + this.nombre + "," + this.profesor;
    }
    
}
