package com.example.proyectofragmentos.vistas;


import android.content.res.Configuration;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.proyectofragmentos.R;
import com.example.proyectofragmentos.adaptador.AdaptadorEstudiante;
import com.example.proyectofragmentos.adaptador.AdaptadorMateria;
import com.example.proyectofragmentos.adaptador.Singleton;
import com.example.proyectofragmentos.clases.Estudiante;
import com.example.proyectofragmentos.clases.Materia;

import java.util.ArrayList;

/*Fragmento sobre el cual se muestra la lista de todas los estudiantes creados en un Recycler View
Con las opciones para editar el estudiante, borrarlo y ver sus materias.
* */

public class FragmentoEstudiantes extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ViewGroup container;
    private ArrayList<Materia> materias;
    private AdaptadorMateria adaptadorMateria;
    private RecyclerView recyclerViewMaterias;

    public FragmentoEstudiantes() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    public static FragmentoEstudiantes newInstance(String param1, String param2) {
        FragmentoEstudiantes fragment = new FragmentoEstudiantes();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    /*Override de la funcion onCreateView
     * En esta funcion se define como se mostraran los estudiantes dependiendo de la orientacion del telefono
     * Ademas se inicia el recycler view de estudiantes.
     * */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.container = container;
        View rootView = inflater.inflate(R.layout.fragment_fragmento_estudiantes,container, false);
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.rv_estudiantes);

        ArrayList<Estudiante> estudiantes = new ArrayList<>();

        if(mParam1.equals("")){
            estudiantes = Singleton.getInstance().estudiantes;
        }else{
            estudiantes = Singleton.getInstance().materias.get(Integer.parseInt(mParam1)).getEstudiantesInscritos();
        }

        iniciarRecyclerView(estudiantes, this, recyclerView);

        materias = new ArrayList<>();

        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            recyclerViewMaterias = (RecyclerView) rootView.findViewById(R.id.rv_land_materias);
            iniciarRecyclerViewMaterias(this.materias, this, recyclerViewMaterias);
        }

        ImageButton btnAgregar = rootView.findViewById(R.id.imageButtonAgregarEstudiante);

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            public final void onClick(View it) {
                irANuevo();
            }
        });

        return rootView;
    }


    /*Funcion que crea un nuevo adaptador estudiante con los siguientes atributos: la lista de estudiantes,
      el recycler view de estudiantes y el fragmento.
     * */
    public void iniciarRecyclerView(ArrayList<Estudiante> listaEstudiantes, FragmentoEstudiantes fragmento, androidx.recyclerview.widget.RecyclerView recycler_view){

        AdaptadorEstudiante adaptadorEstudiante = new AdaptadorEstudiante(
                listaEstudiantes,
                fragmento,
                recycler_view
        );

        recycler_view.setAdapter(adaptadorEstudiante);
        recycler_view.setItemAnimator(new DefaultItemAnimator());
        recycler_view.setLayoutManager(new LinearLayoutManager(getActivity()));

    }

    /*Funcion que crea un adaptador materias para mostrar la lista de materias que tiene un estudiante
   tiene como atributos: la lista de materias del estudiante, el fragmento que se va a usar y el recycler view de materias.
   * */
    public void iniciarRecyclerViewMaterias(ArrayList<Materia> listaMaterias, FragmentoEstudiantes fragmento, androidx.recyclerview.widget.RecyclerView recycler_view){

        AdaptadorMateria adaptadorMateria = new AdaptadorMateria(
                listaMaterias,
                fragmento,
                recycler_view
        );

        recycler_view.setAdapter(adaptadorMateria);
        recycler_view.setItemAnimator(new DefaultItemAnimator());
        recycler_view.setLayoutManager(new LinearLayoutManager(getActivity()));
        this.adaptadorMateria = adaptadorMateria;
    }
    /*
     * Funcion que permite ir al fragmento en donde se puede editar un estudiante
     * */
    public void irAEditar(int indice){
        EditarEstudiante fragmentosEditarEstudiante = EditarEstudiante.newInstance("" + indice,"");
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.root_frame_est, fragmentosEditarEstudiante);
        fragmentTransaction.addToBackStack(null).commit();
    }

    /*
    Funcion que permite ir al fragmento en donde se crean nuevos estudiantes.
    * */
    public void irANuevo(){
        EditarEstudiante fragmentosEditarEstudiante = EditarEstudiante.newInstance("","");
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.root_frame_est, fragmentosEditarEstudiante);
        fragmentTransaction.addToBackStack(null).commit();
    }

    /*
    Funcion que permite navegar hacia la lista de materias de un estudiante.
    * */
    public void irAMaterias(Estudiante estudiante, int indice){

        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            this.materias = estudiante.getMaterias();
            adaptadorMateria.setListaMateria(this.materias);
            adaptadorMateria.notifyDataSetChanged();
        }else{
            FragmentoMaterias fragmentoMaterias = FragmentoMaterias.newInstance("" + indice, "");
            FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.root_frame_est, fragmentoMaterias);
            fragmentTransaction.addToBackStack(null).commit();
        }

    }






}
