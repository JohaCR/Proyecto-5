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
import com.example.proyectofragmentos.adaptador.Singleton;
import com.example.proyectofragmentos.clases.Estudiante;
import com.example.proyectofragmentos.clases.Materia;
import com.example.proyectofragmentos.adaptador.AdaptadorMateria;


import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentoMaterias#newInstance} factory method to
 * create an instance of this fragment.
 */

/*Fragmento sobre el cual se muestra la lista de todas las materias disponibles en un Recycler View
Con las opciones para editar la materia, borrarla y ver sus alumnos.
* */
public class FragmentoMaterias extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ArrayList<Estudiante> estudiantes;
    private AdaptadorEstudiante adaptadorEstudiante;

    public FragmentoMaterias() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment fragmentoMaterias.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentoMaterias newInstance(String param1, String param2) {
        FragmentoMaterias fragment = new FragmentoMaterias();
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

    /*Override de la funcion onCreateView
    * En esta funcion se define como se mostraran las materias dependiendo de la orientacion del telefono
    * Ademas se inicia el recycler view de materias.
    * */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_fragmento_materias,container, false);
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.rv_materias);
        ArrayList<Materia> materias = new ArrayList<>();
        if(mParam1.equals("")) {
             materias = Singleton.getInstance().materias;
        }else{
            materias = Singleton.getInstance().estudiantes.get(Integer.parseInt(mParam1)).getMaterias();
        }
        iniciarRecyclerView(materias,this,recyclerView);

        estudiantes = new ArrayList<>();

        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            RecyclerView recyclerViewEstudiantes = (RecyclerView) rootView.findViewById(R.id.rv_land_estudiantes);
            iniciarRecyclerViewEstudiantes(this.estudiantes, this, recyclerViewEstudiantes);
        }
        ImageButton btnCrear = rootView.findViewById(R.id.imageButtonAgregarMateria);
        btnCrear.setOnClickListener((View.OnClickListener) (new View.OnClickListener() {
            public final void onClick(View it){
                irANuevo();
            }
        }));
        return rootView;
    }

    /*Funcion que crea un nuevo adaptador materia con los siguientes atributos: la lista de materias,
     el recycler view de materias y el fragmento.
    * */
    public void iniciarRecyclerView(ArrayList<Materia> listaMaterias, FragmentoMaterias fragmento, androidx.recyclerview.widget.RecyclerView recycler_view){

        AdaptadorMateria adaptadorMateria = new AdaptadorMateria(
                listaMaterias,
                fragmento,
                recycler_view
        );

        recycler_view.setAdapter(adaptadorMateria);
        recycler_view.setItemAnimator(new DefaultItemAnimator());
        recycler_view.setLayoutManager(new LinearLayoutManager(getActivity()));

    }

    /*Funcion que crea un adaptador estudiantes para mostrar la lista de estudiantes que tiene una materia
    tiene como atributos: la lista de estudiantes de la materia, el fragmento que se va a usar y el recycler view de estudiantes.
    * */
    public void iniciarRecyclerViewEstudiantes(ArrayList<Estudiante> listaEstudiantes, FragmentoMaterias fragmento, androidx.recyclerview.widget.RecyclerView recycler_view){

        AdaptadorEstudiante adaptadorEstudiante = new AdaptadorEstudiante(
                listaEstudiantes,
                fragmento,
                recycler_view
        );

        recycler_view.setAdapter(adaptadorEstudiante);
        recycler_view.setItemAnimator(new DefaultItemAnimator());
        recycler_view.setLayoutManager(new LinearLayoutManager(getActivity()));

        this.adaptadorEstudiante = adaptadorEstudiante;
    }

    /*
    Funcion que permite ir al fragmento en donde se crean nuevas materias.
    * */
    public void irANuevo(){
        EditarMateria fragmentosEditarMateria = EditarMateria.newInstance("","");
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.root_frame_mat, fragmentosEditarMateria);
        fragmentTransaction.addToBackStack(null).commit();
    }

    /*
    * Funcion que permite ir al fragmento en donde se puede editar una materia
    * */
    public void irAEditar(int indice){
        EditarMateria fragmentosEditarMateria = EditarMateria.newInstance("" + indice,"");
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.root_frame_mat, fragmentosEditarMateria);
        fragmentTransaction.addToBackStack(null).commit();
    }
    /*
    Funcion que permite navegar hacia la lista de estudiantes de una materia.
    * */
    public void irAEstudiantes(int indice){
        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            this.estudiantes = Singleton.getInstance().materias.get(indice).getEstudiantesInscritos();
            adaptadorEstudiante.setListaEstudiantes(this.estudiantes);
            adaptadorEstudiante.notifyDataSetChanged();
        }else{
            FragmentoEstudiantes fragmentoEstudiantes = FragmentoEstudiantes.newInstance("" + indice, "");
            FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.root_frame_mat, fragmentoEstudiantes);
            fragmentTransaction.addToBackStack(null).commit();
        }
    }

}
