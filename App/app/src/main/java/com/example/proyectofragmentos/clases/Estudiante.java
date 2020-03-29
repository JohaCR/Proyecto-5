package com.example.proyectofragmentos.clases;

import com.example.proyectofragmentos.adaptador.AdaptadorArchivo;
import com.example.proyectofragmentos.adaptador.Singleton;

import java.util.ArrayList;

public class Estudiante {

    private String cedula;
    private String nombre;
    private String apellido;

    /*
        Método constructor de la clase Estudiante.
     */
    public Estudiante(String cedula, String nombre, String apellido){
        this.cedula = cedula;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    /*
        Método usado para escribir el objeto actual en el archivo de estudiantes.
     */
    public void guardarEnArchivo(){
        new  AdaptadorArchivo().escribirArchivo("estudiantes.csv", "," + this.toString());
    }

    /*
        Método getter del atributo String cedula.
     */
    public String getCedula() {
        return cedula;
    }

    /*
        Método setter del atributo String cedula.
     */
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    /*
        Método getter del atributo String nombre
     */
    public String getNombre() {
        return nombre;
    }

    /*
        Método setter del atributo String nombre.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /*
        Método getter del atributo String apellido.
     */
    public String getApellido() {
        return apellido;
    }

    /*
        Método setter del atributo String apellido.
     */
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    /*
        Método que elimina el estudiante de la lista de estudiantes del Singleton, y luego busca en
        la lista de materias del Singleton eliminando al estudiante de todas las materias en las que
        está inscrito también.
     */
    public void eliminarEstudiante(){
        Singleton.getInstance().estudiantes.remove(this);
        for (Materia materia: Singleton.getInstance().materias
             ) {
            materia.quitarEstudiante(this);
        }
        new AdaptadorArchivo().eliminarArchivoEstudiantes();
    }

    /*
        Override del método toString() que devuelve el objeto en la forma que será escrito en el
        archivo.
     */
    public String toString(){

        return this.cedula + "," + this.nombre + "," + this.apellido;
    }

    /*
        Método que busca en la lista de materias del Singleton y retorna una lista con las materias
        en las que está inscrito el estudiante.
     */
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
