package com.example.proyectofragmentos.adaptador;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;
import com.example.proyectofragmentos.R;
import com.example.proyectofragmentos.clases.Estudiante;
import com.example.proyectofragmentos.vistas.FragmentoEstudiantes;

import java.util.ArrayList;

public final class AdaptadorEstudiante extends Adapter {
    private ArrayList<Estudiante> listaEstudiante;
    private final Fragment context;
    private final RecyclerView recyclerView;
    private boolean esLista;

    /*
        Método del adaptador encargado de llenar todos los campos de cada elemento del RecyclerView y asignarle
        la funcionalidad correspondiente a sus botones.
     */
    public void onBindViewHolder(AdaptadorEstudiante.MyViewHolder myViewHolder, final int position) {

        final Estudiante estudiante = this.listaEstudiante.get(position);
        TextView textViewCedula = myViewHolder.textViewCedula;
        textViewCedula.setText(estudiante.getCedula());

        TextView textViewNombre = myViewHolder.textViewNombre;
        textViewNombre.setText(estudiante.getNombre());

        TextView textViewApellido = myViewHolder.textViewApellido;
        textViewApellido.setText(estudiante.getApellido());

        ImageButton buttonEditar = myViewHolder.buttonEditar;
        ImageButton buttonBorrar = myViewHolder.buttonBorrar;
        ImageButton buttonMaterias = myViewHolder.buttonMaterias;

        if(esLista){
            buttonEditar.setVisibility(View.INVISIBLE);
            buttonMaterias.setVisibility(View.INVISIBLE);
            buttonBorrar.setVisibility(View.INVISIBLE);
        }else{
            buttonEditar.setOnClickListener((OnClickListener)(new OnClickListener() {
                public final void onClick(View it) {
                    ((FragmentoEstudiantes) context).irAEditar(position);
                }
            }));

            buttonBorrar.setOnClickListener((OnClickListener)(new OnClickListener() {
                public final void onClick(View it) {
                    estudiante.eliminarEstudiante();
                    notifyItemRemoved(position);
                    notifyItemRangeChanged(position, listaEstudiante.size());
                }
            }));

            buttonMaterias.setOnClickListener((OnClickListener)(new OnClickListener() {
                public final void onClick(View it) {
                    ((FragmentoEstudiantes) context).irAMaterias(estudiante, position);
                }
            }));
        }

    }

    /*
        Override del método onCreateViewHolder en el que se define el layout que va a usar cada elemento
        del RecyclerView.
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.estudiante_ly, parent, false);
        return new MyViewHolder(view);
    }

    /*
        Override del método onBindViewHolder en el que se llama al método personalizado onBindViewHolder
        para llenar el layout de cada elemento del RecyclerView.
     */
    public void onBindViewHolder(ViewHolder var1, int var2) {
        this.onBindViewHolder((AdaptadorEstudiante.MyViewHolder)var1, var2);
    }

    /*
        Override del método getItemCount que retorna el tamaño del DataSource del RecyclerView.
     */
    public int getItemCount() {
        return this.listaEstudiante.size();
    }

    /*
        Método constructor del adaptador.
     */
    public AdaptadorEstudiante(ArrayList listaEstudiante, Fragment context, RecyclerView recyclerView, boolean esLista) {
        this.listaEstudiante = listaEstudiante;
        this.context = context;
        this.recyclerView = recyclerView;
        this.esLista = esLista;
    }

    /*
        Método Setter del ArrayList<Estudiante> listaEstudiante del adaptador.
     */
    public void setListaEstudiantes(ArrayList<Estudiante> listaEstudiante){
        this.listaEstudiante =  listaEstudiante;
    }

    /*
        Clase ViewHolder que guarda una referencia a los elementos del layout que va a tener cada elemento
        del RecyclerView.
     */
    public final class MyViewHolder extends ViewHolder {
                private TextView textViewCedula;
                private TextView textViewNombre;
                private TextView textViewApellido;
                private ImageButton buttonEditar;
                private ImageButton buttonBorrar;
                private ImageButton buttonMaterias;


        public MyViewHolder(View view) {
            super(view);
            this.textViewCedula = view.findViewById(R.id.textViewCedula);
            this.textViewNombre = view.findViewById(R.id.textViewNombre);
            this.textViewApellido = view.findViewById(R.id.textViewApellido);
            this.buttonEditar = view.findViewById(R.id.imageButtonEditar);
            this.buttonBorrar = view.findViewById(R.id.imageButtonBorrar);
            this.buttonMaterias = view.findViewById(R.id.imageButtonMaterias);
        }
    }
}
