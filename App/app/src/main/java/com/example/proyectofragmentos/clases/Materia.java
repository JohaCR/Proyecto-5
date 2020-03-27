package com.example.proyectofragmentos.clases;

import com.example.proyectofragmentos.vistas.MainActivity;

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
        MainActivity.materias.add(this);
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
        MainActivity.materias.remove(this);
    }
    
}
