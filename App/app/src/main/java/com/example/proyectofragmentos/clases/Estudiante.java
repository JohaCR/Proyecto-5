package com.example.proyectofragmentos.clases;

import com.example.proyectofragmentos.vistas.MainActivity;

public class Estudiante {

    private String cedula;
    private String nombre;
    private String apellido;

    public Estudiante(String cedula, String nombre, String apellido){
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void eliminarEstudiante(){
        for (Materia materia: MainActivity.materias
             ) {
            materia.quitarEstudiante(this);
        }
    }

    public String toString(){
        return this.cedula + "," + this.nombre + "," + this.apellido;
    }

}
