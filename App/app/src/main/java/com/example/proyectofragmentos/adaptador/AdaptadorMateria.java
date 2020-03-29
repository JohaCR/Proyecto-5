package com.example.proyectofragmentos.adaptador;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectofragmentos.R;
import com.example.proyectofragmentos.clases.Materia;
import com.example.proyectofragmentos.vistas.FragmentoMaterias;

import java.util.ArrayList;

public class AdaptadorMateria extends RecyclerView.Adapter {
    private ArrayList<Materia> listaMateria;
    private final Fragment context;
    private final RecyclerView recyclerView;

    /*
        Método del adaptador encargado de llenar todos los campos de cada elemento del RecyclerView y asignarle
        la funcionalidad correspondiente a sus botones.
     */
    public void onBindViewHolder(AdaptadorMateria.MyViewHolder myViewHolder, final int position) {
        final Materia materia = this.listaMateria.get(position);
        TextView textViewCodigo = myViewHolder.textViewCodigo;
        textViewCodigo.setText(materia.getCodigo());

        TextView textViewNombreMateria = myViewHolder.textViewNombreMateria;
        textViewNombreMateria.setText(materia.getNombre());

        TextView textViewProfesor = myViewHolder.textViewProfesor;
        textViewProfesor.setText(materia.getProfesor());

        ImageButton buttonEditarMateria = myViewHolder.buttonEditarMateria;
        buttonEditarMateria.setOnClickListener((View.OnClickListener)(new View.OnClickListener() {
            public final void onClick(View it) {
                ((FragmentoMaterias) context).irAEditar(position);
            }
        }));

        ImageButton buttonBorrarMateria = myViewHolder.buttonBorrarMateria;
        buttonBorrarMateria.setOnClickListener((View.OnClickListener)(new View.OnClickListener() {
            public final void onClick(View it) {
                materia.eliminarMateria();
                notifyItemRemoved(position);
                notifyItemRangeChanged(position, listaMateria.size());
            }
        }));

        ImageButton buttonEstudiantes = myViewHolder.buttonEstudiantes;
        buttonEstudiantes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((FragmentoMaterias) context).irAEstudiantes(position);
            }
        });
    }

    /*
        Override del método onCreateViewHolder en el que se define el layout que va a usar cada elemento
        del RecyclerView.
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.materia_ly, parent, false);
        return new AdaptadorMateria.MyViewHolder(view);
    }

    /*
        Override del método onBindViewHolder en el que se llama al método personalizado onBindViewHolder
        para llenar el layout de cada elemento del RecyclerView.
     */
    public void onBindViewHolder(RecyclerView.ViewHolder var1, int var2) {
        this.onBindViewHolder((AdaptadorMateria.MyViewHolder)var1, var2);
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
    public AdaptadorMateria(ArrayList listaMateria, Fragment context, RecyclerView recyclerView) {
        this.listaMateria = listaMateria;
        this.context = context;
        this.recyclerView = recyclerView;
    }

    /*
        Método Setter del ArrayList<Estudiante> listaEstudiante del adaptador.
     */
    public void setListaMateria(ArrayList<Materia> materias){
        this.listaMateria = materias;
    }

    /*
        Clase ViewHolder que guarda una referencia a los elementos del layout que va a tener cada elemento
        del RecyclerView.
     */
    public final class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewCodigo;
        private TextView textViewNombreMateria;
        private TextView textViewProfesor;
        private ImageButton buttonEditarMateria;
        private ImageButton buttonBorrarMateria;
        private ImageButton buttonEstudiantes;

        public MyViewHolder(View view) {
            super(view);
            this.textViewCodigo = view.findViewById(R.id.textViewCodigo);
            this.textViewNombreMateria = view.findViewById(R.id.textViewNombreMateria);
            this.textViewProfesor = view.findViewById(R.id.textViewProfesor);
            this.buttonEditarMateria = view.findViewById(R.id.imageButtonEditarMateria);
            this.buttonBorrarMateria = view.findViewById(R.id.imageButtonBorrarMateria);
            this.buttonEstudiantes = view.findViewById(R.id.imageButtonEstudiantesDeMateria);
        }
    }
}
