package com.example.proyectofragmentos.clases;

import com.example.proyectofragmentos.adaptador.AdaptadorArchivo;
import com.example.proyectofragmentos.adaptador.Singleton;

import java.util.ArrayList;

public class Materia {
    private String codigo;
    private String nombre;
    private String profesor;
    private ArrayList<Estudiante> estudiantesInscritos;

    /*
        Método constructor de la clase Materia.
     */
    public Materia(String codigo, String nombre, String profesor){
        this.codigo = codigo;
        this.nombre = nombre;
        this.profesor = profesor;
        this.estudiantesInscritos = new ArrayList<Estudiante>();
    }

    /*
        Método usado para escribir el objeto actual en el archivo de materias.
     */
    public void guardarEnArchivo(){
        new AdaptadorArchivo().escribirArchivo("materias.csv", this.toString());
    }

    /*
        Método getter del atributo String codigo.
     */
    public String getCodigo() {
        return codigo;
    }

    /*
        Método setter del atributo String codigo.
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    /*
        Método getter del atributo String nombre.
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
        Método getter del atributo String profesor.
     */
    public String getProfesor() {
        return profesor;
    }

    /*
        Método setter del atributo String profesor.
     */
    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }

    /*
        Método getter del atributo ArrayList<Estudiante> estudiantesInscritos.
     */
    public ArrayList<Estudiante> getEstudiantesInscritos() {
        return estudiantesInscritos;
    }

    /*
        Método setter del atributo ArrayList<Estudiante> estudiantesInscritos.
     */
    public void setEstudiantesInscritos(ArrayList<Estudiante> estudiantesInscritos) {
        this.estudiantesInscritos = estudiantesInscritos;
    }

    /*
        Método que agrega a un estudiante a la lista de estudiantes inscritos en esta materia y lo
        registra guardandolo en el archivo.
     */
    public void agregarEstudiante(Estudiante estudiante){

        estudiantesInscritos.add(estudiante);
        new AdaptadorArchivo().escribirArchivo("estudiantes.csv", this.codigo + "," + estudiante.toString());
    }

    /*
        Método que elimina a un estudiante de la lista de estudiantes inscritos en esta materia.
     */
    public void quitarEstudiante(Estudiante estudiante){
        int indiceAEliminar = -1;
        for (Estudiante estudianteAEliminar: this.estudiantesInscritos
             ) {
            if(estudianteAEliminar.getCedula().equals(estudiante.getCedula())){
                indiceAEliminar = this.estudiantesInscritos.indexOf(estudianteAEliminar);
            }
        }
        estudiantesInscritos.remove(indiceAEliminar);
    }

    /*
        Método que devulve un booleano indicando si el Estudiante pasado como parámetro está inscrito
        en esta materia.
     */
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

    /*
        Método que elimina el objeto de la lista de materias del Singleton y luego lo elimina del
        archivo de materias.
     */
    public void eliminarMateria(){
        Singleton.getInstance().materias.remove(this);
        new AdaptadorArchivo().eliminarArchivoMaterias();
    }

    /*
        Override del método toString() que devuelve el objeto en la forma que será escrito en el
        archivo.
     */
    public String toString(){
        return this.codigo + "," + this.nombre + "," + this.profesor;
    }
    
}
