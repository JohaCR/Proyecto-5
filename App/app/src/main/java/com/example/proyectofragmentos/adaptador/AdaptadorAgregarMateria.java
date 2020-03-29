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
import com.example.proyectofragmentos.vistas.AgregarYQuitarMaterias;

import java.util.ArrayList;

public class AdaptadorAgregarMateria extends RecyclerView.Adapter {
    private ArrayList<Materia> listaMateria;
    private final Fragment context;
    private final RecyclerView recyclerView;
    private boolean sePuedeAgregar;


    public void onBindViewHolder(AdaptadorAgregarMateria.MyViewHolder myViewHolder, final int position) {

        final Materia materia = this.listaMateria.get(position);
        TextView textViewCodigoAgregar = myViewHolder.textViewCodigoAgregar;
        textViewCodigoAgregar.setText(materia.getCodigo());

        TextView textViewNombreMateriaAgregar = myViewHolder.textViewNombreMateriaAgregar;
        textViewNombreMateriaAgregar.setText(materia.getNombre());

        TextView textViewProfesorAgregar = myViewHolder.textViewProfesorAgregar;
        textViewProfesorAgregar.setText(materia.getProfesor());

        ImageButton inscribirse = myViewHolder.inscribirse;

        if(sePuedeAgregar){
            inscribirse.setBackground(context.getActivity().getDrawable(R.drawable.plus));
        }else{
            inscribirse.setBackground(context.getActivity().getDrawable(R.drawable.minus));
        }

        inscribirse.setOnClickListener((View.OnClickListener)(new View.OnClickListener() {
            public final void onClick(View it) {
                if(sePuedeAgregar){
                    ((AgregarYQuitarMaterias) context).agregarMateria(position);
                }else{
                    ((AgregarYQuitarMaterias) context).quitarMateria(position);
                }
            }
        }));


    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.materia_agregar_ly, parent, false);
        return new AdaptadorAgregarMateria.MyViewHolder(view);
    }

    public void onBindViewHolder(RecyclerView.ViewHolder var1, int var2) {
        this.onBindViewHolder((AdaptadorAgregarMateria.MyViewHolder)var1, var2);
    }

    public int getItemCount() {
        if(listaMateria != null){
            return this.listaMateria.size();
        }
        return 0;
    }



    public AdaptadorAgregarMateria(ArrayList listaMateria, Fragment context, RecyclerView recyclerView, boolean sePuedeAgregar) {
        this.listaMateria = listaMateria;
        this.context = context;
        this.recyclerView = recyclerView;
        this.sePuedeAgregar = sePuedeAgregar;
    }

    public void setListaMateria(ArrayList<Materia> materias){
        this.listaMateria = materias;
    }


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