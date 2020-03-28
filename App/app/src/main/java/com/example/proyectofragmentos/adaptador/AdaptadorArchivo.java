package com.example.proyectofragmentos.adaptador;

import android.content.Context;
import android.util.Log;

import com.example.proyectofragmentos.MainActivity;
import com.example.proyectofragmentos.clases.Estudiante;
import com.example.proyectofragmentos.clases.Materia;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class AdaptadorArchivo {

    public void escribirArchivo(String nombreArchivo, String datos,Context context){
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput(nombreArchivo, Context.MODE_PRIVATE));
            outputStreamWriter.write(datos);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("archivo", "Error al escribir en el arcivo: " + e.toString());
        }
    }

    public void leerMaterias(Context context){
        try {
            InputStream inputStream = context.openFileInput("materias.csv");

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    String[] parametros = receiveString.split(",");
                    Materia materia = new Materia(parametros[0], parametros[1], parametros[2]);
                    leerEstudiantes(materia, context);
                    MainActivity.materias.add(materia);
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

    private void leerEstudiantes(Materia materia,Context context){
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
