package com.example.proyectofragmentos.adaptador;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectofragmentos.R;
import com.example.proyectofragmentos.clases.Materia;

import java.util.ArrayList;

public class AdaptadorAgregarMateria extends RecyclerView.Adapter {
    private ArrayList<Materia> listaMateria;
    private final Fragment context;
    private final RecyclerView recyclerView;

     /*
        Método del adaptador encargado de llenar todos los campos de cada elemento del RecyclerView y asignarle
        la funcionalidad correspondiente a sus botones.
     */

    public void onBindViewHolder(AdaptadorAgregarMateria.MyViewHolder myViewHolder, final int position) {

        final Materia materia = this.listaMateria.get(position);
        TextView textViewCodigoAgregar = myViewHolder.textViewCodigoAgregar;
        textViewCodigoAgregar.setText(materia.getCodigo());

        TextView textViewNombreMateriaAgregar = myViewHolder.textViewNombreMateriaAgregar;
        textViewNombreMateriaAgregar.setText(materia.getNombre());

        TextView textViewProfesorAgregar = myViewHolder.textViewProfesorAgregar;
        textViewProfesorAgregar.setText(materia.getProfesor());

        ImageButton inscribirse = myViewHolder.inscribirse;
        inscribirse.setOnClickListener((View.OnClickListener)(new View.OnClickListener() {
            public final void onClick(View it) {
                /*codigo para inscribirse */
            }
        }));


    }

    /*
        Override del método onCreateViewHolder en el que se define el layout que va a usar cada elemento
        del RecyclerView.
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.materia_ly, parent, false);
        return new AdaptadorAgregarMateria.MyViewHolder(view);
    }

    /*
        Override del método onBindViewHolder en el que se llama al método personalizado onBindViewHolder
        para llenar el layout de cada elemento del RecyclerView.
     */
    public void onBindViewHolder(RecyclerView.ViewHolder var1, int var2) {
        this.onBindViewHolder((AdaptadorAgregarMateria.MyViewHolder)var1, var2);
    }

    /*
        Override del método getItemCount que retorna el tamaño del DataSource del RecyclerView.
     */
    public int getItemCount() {
        if(listaMateria != null){
            return this.listaMateria.size();
        }
        return 0;
    }


    /*
        Método constructor del adaptador.
     */
    public AdaptadorAgregarMateria(ArrayList listaMateria, Fragment context, RecyclerView recyclerView) {
        this.listaMateria = listaMateria;
        this.context = context;
        this.recyclerView = recyclerView;
    }
    /*
    Método Setter del ArrayList<Materi> listaMateria del adaptador.
    * */
    public void setListaMateria(ArrayList<Materia> materias){
        this.listaMateria = materias;
    }


      /*
        Clase ViewHolder que guarda una referencia a los elementos del layout que va a tener cada elemento
        del RecyclerView.
     */
    public final class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewCodigoAgregar;
        private TextView textViewNombreMateriaAgregar;
        private TextView textViewProfesorAgregar;
        private ImageButton inscribirse;


        public MyViewHolder(View view) {
            super(view);
            this.textViewCodigoAgregar = view.findViewById(R.id.textViewCodigoAgregar);
            this.textViewNombreMateriaAgregar = view.findViewById(R.id.textViewNombreMateriaAgregar);
            this.textViewProfesorAgregar = view.findViewById(R.id.textViewProfesorAgregar);
            this.inscribirse = view.findViewById(R.id.imageButtonInscribirse);

        }
    }
}