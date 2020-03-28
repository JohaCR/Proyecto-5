package com.example.proyectofragmentos.adaptador;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import com.example.proyectofragmentos.R;
import com.example.proyectofragmentos.clases.Estudiante;
import com.example.proyectofragmentos.vistas.FragmentoEstudiantes;

import java.util.ArrayList;

public final class AdaptadorEstudiante extends Adapter {
    private final ArrayList<Estudiante> listaEstudiante;
    private final FragmentoEstudiantes context;
    private final RecyclerView recyclerView;


    public void onBindViewHolder(AdaptadorEstudiante.MyViewHolder myViewHolder, int position) {

        Estudiante estudiante = this.listaEstudiante.get(position);
        TextView textViewCedula = myViewHolder.textViewCedula;
        textViewCedula.setText(estudiante.getCedula());

        TextView textViewNombre = myViewHolder.textViewNombre;
        textViewNombre.setText(estudiante.getNombre());

        TextView textViewApellido = myViewHolder.textViewApellido;
        textViewApellido.setText(estudiante.getApellido());

        ImageButton buttonEditar = myViewHolder.buttonEditar;
        buttonEditar.setOnClickListener((OnClickListener)(new OnClickListener() {
            public final void onClick(View it) {
                context.irAEditar();
            }
        }));

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.estudiante_ly, parent, false);
        return new MyViewHolder(view);
    }

    public void onBindViewHolder(ViewHolder var1, int var2) {
        this.onBindViewHolder((AdaptadorEstudiante.MyViewHolder)var1, var2);
    }

    public int getItemCount() {
        return this.listaEstudiante.size();
    }



    public AdaptadorEstudiante(ArrayList listaEstudiante, FragmentoEstudiantes context, RecyclerView recyclerView) {
        this.listaEstudiante = listaEstudiante;
        this.context = context;
        this.recyclerView = recyclerView;
    }


    public final class MyViewHolder extends ViewHolder {
                private TextView textViewCedula;
                private TextView textViewNombre;
                private TextView textViewApellido;
                private ImageButton buttonEditar;


        public MyViewHolder(View view) {
            super(view);
            this.textViewCedula = view.findViewById(R.id.textViewCedula);
            this.textViewNombre = view.findViewById(R.id.textViewNombre);
            this.textViewApellido = view.findViewById(R.id.textViewApellido);
            this.buttonEditar = view.findViewById(R.id.imageButtonEditar);

        }
    }
}
