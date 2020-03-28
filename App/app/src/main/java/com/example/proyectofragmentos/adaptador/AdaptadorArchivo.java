package com.example.proyectofragmentos.adaptador;

import android.content.Context;
import android.util.Log;

import com.example.proyectofragmentos.MainActivity;
import com.example.proyectofragmentos.clases.Estudiante;
import com.example.proyectofragmentos.clases.Materia;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class AdaptadorArchivo {

    public ArrayList<Materia> materias;
    public ArrayList<Estudiante> estudiantes;
    public static Context context;

    public AdaptadorArchivo(){
        this.materias = new ArrayList<>();
        this.estudiantes = new ArrayList<>();
    }

    public void setContext(Context context){
        this.context = context;
    }

    public void eliminarArchivoEstudiantes(){
        String nombreArchivo = "estudiantes.csv";
        File dir = context.getFilesDir();
        File archivo = new File(dir, nombreArchivo);
        archivo.delete();
        for (Estudiante estudiante: Singleton.getInstance().estudiantes
             ) {
            escribirArchivo(nombreArchivo, "," + estudiante.toString());
        }
        for (Materia materia: Singleton.getInstance().materias
            ){
            for (Estudiante estudiante: materia.getEstudiantesInscritos()
                 ) {
                escribirArchivo(nombreArchivo, materia.getCodigo() + "," + estudiante.toString());
            }
        }
    }

    public void eliminarArchivoMaterias(){
        String nombreArchivo = "materias.csv";
        File dir = context.getFilesDir();
        File archivo = new File(dir, nombreArchivo);
        archivo.delete();
        for (Materia materia: Singleton.getInstance().materias
        ){
            escribirArchivo(nombreArchivo, materia.toString());

        }
        eliminarArchivoEstudiantes();
    }

    public void escribirArchivo(String nombreArchivo, String datos){
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(nombreArchivo, Context.MODE_APPEND));
            outputStreamWriter.append(datos);
            outputStreamWriter.append("\n");
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("archivo", "Error al escribir en el arcivo: " + e.toString());
        }
    }

    public ArrayList<Materia> getMaterias() {
        leerMaterias();
        return materias;
    }

    public ArrayList<Estudiante> getEstudiantes() {
        return estudiantes;
    }

    private void leerMaterias(){
        try {
            InputStream inputStream = context.openFileInput("materias.csv");

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    String[] parametros = receiveString.split(",");
                    Materia materia = new Materia(parametros[0], parametros[1], parametros[2]);
                    leerEstudiantes(materia);
                    this.materias.add(materia);
                }
                inputStream.close();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("archivo", "Archivo no encontrado: " + e.toString());
        } catch (IOException e) {
            Log.e("archivo", "Error al leer el archivo: " + e.toString());
        }
    }

    private void leerEstudiantes(Materia materia){
        try {
            InputStream inputStream = context.openFileInput("estudiantes.csv");

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    String[] parametros = receiveString.split(",");
                    if(parametros[0].equals(materia.getCodigo())){
                        Estudiante estudiante = new Estudiante(parametros[1], parametros[2], parametros[3]);
                        materia.agregarEstudiante(estudiante);
                    }else if(parametros[0].equals("")){
                        Estudiante estudiante = new Estudiante(parametros[1], parametros[2], parametros[3]);
                        this.estudiantes.add(estudiante);
                    }
                }
                inputStream.close();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("archivo", "Archivo no encontrado: " + e.toString());
        } catch (IOException e) {
            Log.e("archivo", "Error al leer el archivo: " + e.toString());
        }
    }

}
