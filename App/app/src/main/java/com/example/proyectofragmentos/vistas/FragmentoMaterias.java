package com.example.proyectofragmentos.vistas;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.proyectofragmentos.R;
import com.example.proyectofragmentos.clases.Materia;
import com.example.proyectofragmentos.adaptador.AdaptadorMateria;
import com.example.proyectofragmentos.clases.Materia;
import com.example.proyectofragmentos.clases.Materia;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentoMaterias#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentoMaterias extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_fragmento_materias,container, false);
        RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.rv_materias);
        Materia e1 = new Materia("11111", "Programacion II", "Andres Ramirez");
        Materia e2 = new Materia("2222", "Base de datos", "Juana Loaiza");
        Materia e3 = new Materia("333", "Gestion de TICs", "Martha Aguirre");

        ArrayList<Materia> materias = new ArrayList<>();
        materias.add(e1);
        materias.add(e2);
        materias.add(e3);

        iniciarRecyclerView(materias,this,recyclerView);

        return rootView;
    }

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

}
