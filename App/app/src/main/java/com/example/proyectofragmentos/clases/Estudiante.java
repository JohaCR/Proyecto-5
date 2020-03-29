package com.example.proyectofragmentos.clases;

import com.example.proyectofragmentos.MainActivity;
import com.example.proyectofragmentos.adaptador.AdaptadorArchivo;
import com.example.proyectofragmentos.adaptador.Singleton;

import java.util.ArrayList;

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
        Singleton.getInstance().estudiantes.remove(this);
        for (Materia materia: Singleton.getInstance().materias
             ) {
            materia.quitarEstudiante(this);
        }
        new AdaptadorArchivo().eliminarArchivoEstudiantes();
    }

    public String toString(){

        return this.cedula + "," + this.nombre + "," + this.apellido;
    }

    public ArrayList<Materia> getMaterias(){
        ArrayList<Materia> materiasDelEstudiante = new ArrayList<>();
        for (Materia materia: Singleton.getInstance().materias
        ) {
            for (Estudiante estudiante : materia.getEstudiantesInscritos()
            ){
                if(estudiante.getCedula().equals(this.cedula)){
                    materiasDelEstudiante.add(materia);
                }
            }
        }
        return materiasDelEstudiante;
    }

}
