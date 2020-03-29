package com.example.proyectofragmentos;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.proyectofragmentos.adaptador.AdaptadorAgregarMateria;
import com.example.proyectofragmentos.adaptador.AdaptadorEstudiante;
import com.example.proyectofragmentos.clases.Estudiante;
import com.example.proyectofragmentos.clases.Materia;
import com.example.proyectofragmentos.vistas.FragmentoEstudiantes;
import com.example.proyectofragmentos.vistas.FragmentoMaterias;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AgregarYQuitarMaterias#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AgregarYQuitarMaterias extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_agregar_y_quitar_materias, container, false);
    }


    public void iniciarRecyclerView(ArrayList<Materia> listaMaterias, AgregarYQuitarMaterias fragmento, androidx.recyclerview.widget.RecyclerView recycler_view){

        AdaptadorAgregarMateria adaptadorMateria = new AdaptadorAgregarMateria(
                listaMaterias,
                fragmento,
                recycler_view
        );

        recycler_view.setAdapter(adaptadorMateria);
        recycler_view.setItemAnimator(new DefaultItemAnimator());
        recycler_view.setLayoutManager(new LinearLayoutManager(getActivity()));

    }
}
