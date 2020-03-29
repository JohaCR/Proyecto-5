package com.example.proyectofragmentos.vistas;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.proyectofragmentos.R;
import com.example.proyectofragmentos.adaptador.AdaptadorAgregarMateria;
import com.example.proyectofragmentos.adaptador.Singleton;
import com.example.proyectofragmentos.clases.Materia;

import java.lang.reflect.Array;
import java.util.ArrayList;


/*
    Fragmento sobre el cual se muestran dos listas: la lista de materias tomadas por un estudiante y
    la lista de materias que aún puede tomar el estudiante.
 */
public class AgregarYQuitarMaterias extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ArrayList<Materia> materiasTomadas = new ArrayList<>();
    private ArrayList<Materia> materiasDisponibles = new ArrayList<>();
    private RecyclerView rv_materias_inscritas;
    private RecyclerView rv_materias_disponibles;
    private AdaptadorAgregarMateria adaptadorInscritas;
    private AdaptadorAgregarMateria adaptadorDisponibles;

    public AgregarYQuitarMaterias() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AgregarYQuitarMaterias.
     */
    // TODO: Rename and change types and number of parameters
    public static AgregarYQuitarMaterias newInstance(String param1, String param2) {
        AgregarYQuitarMaterias fragment = new AgregarYQuitarMaterias();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }



    }

    /*
        Método que hace override al método onCreateView en el que se cargan los RecyclerView así como
        las listas que son origen de los RecyclerView.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_agregar_y_quitar_materias, container, false);


        if(mParam1.equals("" + Singleton.getInstance().estudiantes.size())){
            materiasTomadas = new ArrayList<>();
        }else{
            int indice = Integer.parseInt(mParam1);
            materiasTomadas = Singleton.getInstance().estudiantes.get(indice).getMaterias();
        }

        for (Materia materia: Singleton.getInstance().materias
             ) {
            boolean laMateriaFueTomada = false;
            for (Materia materiaTomada: materiasTomadas
                 ) {
                if(materiaTomada.getCodigo().equals(materia.getCodigo())){
                    laMateriaFueTomada = true;
                }
            }
            if(!laMateriaFueTomada){
                materiasDisponibles.add(materia);
            }
        }

        rv_materias_inscritas = rootView.findViewById(R.id.materiasInscritasRV);
        rv_materias_disponibles = rootView.findViewById(R.id.materiasParaInscribirseRV);

        iniciarRecyclerView(materiasTomadas, this, rv_materias_inscritas, false);
        iniciarRecyclerView(materiasDisponibles, this, rv_materias_disponibles, true);

        ImageButton button_aceptar = rootView.findViewById(R.id.buttonAgregarMateriasEstudiante);
        button_aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irAListaEstudiantes();
            }
        });

        return rootView;
    }

    /*
        Funcion que crea un adaptador materias para mostrar la lista de materias tiene como atributos:
        la lista de materias del estudiante, el fragmento que se va a usar, el recycler view de materias
        y un booleano para validar si se trata de una materia disponible o una materia asignada.
       * */
    public void iniciarRecyclerView(ArrayList<Materia> listaMaterias, AgregarYQuitarMaterias fragmento, androidx.recyclerview.widget.RecyclerView recycler_view, boolean sePuedeAgregar){

        AdaptadorAgregarMateria adaptadorMateria = new AdaptadorAgregarMateria(
                listaMaterias,
                fragmento,
                recycler_view,
                sePuedeAgregar
        );

        recycler_view.setAdapter(adaptadorMateria);
        recycler_view.setItemAnimator(new DefaultItemAnimator());
        recycler_view.setLayoutManager(new LinearLayoutManager(getActivity()));
        if(sePuedeAgregar){
            adaptadorDisponibles = adaptadorMateria;
        }else{
            adaptadorInscritas = adaptadorMateria;
        }
    }

    /*
        Función que elimina la materia de la lista de materias disponibles y la agrega a la lista de
        materias asignadas para luego actualizar los RecyclerView.
     */
    public void agregarMateria(int indiceDisponible){
        if(mParam1.equals("" + Singleton.getInstance().estudiantes.size())){
            materiasDisponibles.get(indiceDisponible).agregarEstudiante(Singleton.getInstance().estudiantes.get(Integer.parseInt(mParam1) - 1));
        }else{
            materiasDisponibles.get(indiceDisponible).agregarEstudiante(Singleton.getInstance().estudiantes.get(Integer.parseInt(mParam1)));
        }
        materiasTomadas.add(materiasDisponibles.get(indiceDisponible));
        materiasDisponibles.remove(indiceDisponible);
        adaptadorInscritas.notifyDataSetChanged();
        adaptadorDisponibles.notifyDataSetChanged();
    }

    /*
        Función que elimina la materia de la lista de materias asignadas y la agrega a la lista de
        materias disponibles para luego actualizar los RecyclerView.
     */
    public void quitarMateria(int indiceTomada){
        materiasTomadas.get(indiceTomada).quitarEstudiante(Singleton.getInstance().estudiantes.get(Integer.parseInt(mParam1)));
        materiasDisponibles.add(materiasTomadas.get(indiceTomada));
        materiasTomadas.remove(indiceTomada);
        adaptadorDisponibles.notifyDataSetChanged();
        adaptadorInscritas.notifyDataSetChanged();
    }

    /*
        Función para ir al fragmento que lista todos los estudiantes
     */
    public void irAListaEstudiantes(){
        FragmentoEstudiantes fragmentoEstudiantes = FragmentoEstudiantes.newInstance("", "");
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.root_frame_est, fragmentoEstudiantes);
        fragmentTransaction.addToBackStack(null).commit();
    }
}
