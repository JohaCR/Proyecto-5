package com.example.proyectofragmentos.adaptador;

import android.content.Context;
import android.util.Log;

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

    /*
        Guarda el contaxto de la aplicación, necesario para poder manejar (leer y escribir) archivos.
    */
    public void setContext(Context context){
        this.context = context;
    }

    /*
        Elimina directamente el archivo de estudiantes, lo vuelve a crear y lo escribe con los datos que encuentran en el Singleton.
     */
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

    /*
        Elimina directamente el archivo de materias, lo vuelve a crear y lo escribe con los datos que encuentran en el Singleton.
     */
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

    /*
        Escribe en el archivo cuyo nombre de archivo es pasado como parámetro, si el archivo no existe, lo crea.
     */
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

    /*
        Retorna las materias que fueron leídas del archivo en una lista de materias.
     */
    public ArrayList<Materia> getMaterias() {
        leerMaterias();
        return materias;
    }

    /*
        Retorna los estudiantes que fueron leídos del archivo en una lista de estudiantes.
     */
    public ArrayList<Estudiante> getEstudiantes() {
        leerEstudiantes(null);
        return estudiantes;
    }

    /*
        Lee el archivo de materias, crea un objeto Materia, lee en el archivo de estudiantes en busca de los estudiantes inscritos
        y los agrega a su lista de estudiantes inscritos. Finalmente guarda la materia en la lista del Singleton.
     */
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

    /*
        Lee el archivo de estudiantes, crea un objeto Estudiante y lo agrega a la lista de estudiantes del Singleton o
        a la lista de estudiantes inscritos de un materia según los parámetros pasados.
     */
    private void leerEstudiantes(Materia materia){
        try {
            InputStream inputStream = context.openFileInput("estudiantes.csv");

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    String[] parametros = receiveString.split(",");
                    Estudiante estudiante = new Estudiante(parametros[1], parametros[2], parametros[3]);
                    if(materia == null){
                        if(parametros[0].equals("")) {
                            this.estudiantes.add(estudiante);
                        }
                    }else if(parametros[0].equals(materia.getCodigo())){
                        materia.getEstudiantesInscritos().add(estudiante);
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
