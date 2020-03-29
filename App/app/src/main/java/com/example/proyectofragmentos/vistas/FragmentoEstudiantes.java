package com.example.proyectofragmentos.vistas;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.proyectofragmentos.R;
import com.example.proyectofragmentos.adaptador.AdaptadorEstudiante;
import com.example.proyectofragmentos.adaptador.AdaptadorMateria;
import com.example.proyectofragmentos.adaptador.Singleton;
import com.example.proyectofragmentos.clases.Estudiante;
import com.example.proyectofragmentos.clases.Materia;

import java.util.ArrayList;

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



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.container = container;
        View rootView = inflater.inflate(R.layout.fragment_fragmento_estudiantes,container, false);
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.rv_estudiantes);
//        Estudiante e1 = new Estudiante("11111", "Ana", "ramirez");
//        Estudiante e2 = new Estudiante("2222", "Pedro", "Jimenez");
//        Estudiante e3 = new Estudiante("333", "Maria", "Lopez");

        ArrayList<Estudiante> estudiantes = Singleton.getInstance().estudiantes;

        iniciarRecyclerView(estudiantes, this, recyclerView);

        materias = Singleton.getInstance().materias;

        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Log.i("testingxd", "entre aquí");
            recyclerViewMaterias = (RecyclerView) rootView.findViewById(R.id.rv_land_materias);
            iniciarRecyclerViewMaterias(this.materias, this, recyclerViewMaterias);
        }

        return rootView;
    }

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

    public void irAEditar(){

    }

    public void eliminar(){

    }

    public void irAMaterias(Estudiante estudiante){

        int orientation = getResources().getConfiguration().orientation;
        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            this.materias = estudiante.getMaterias();
            adaptadorMateria.setListaMateria(this.materias);
            adaptadorMateria.notifyDataSetChanged();
        }else{
            Log.i("testingxd", "yendo a materia en vertical");
        }

    }






}
