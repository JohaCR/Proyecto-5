package com.example.proyectofragmentos.vistas;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.proyectofragmentos.R;
import com.example.proyectofragmentos.adaptador.AdaptadorArchivo;
import com.example.proyectofragmentos.adaptador.Singleton;
import com.example.proyectofragmentos.clases.Estudiante;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditarEstudiante#newInstance} factory method to
 * create an instance of this fragment.
 */


/*
 Fragmento que maneja el layout editar estudiante, se rehusa esta clase para editar los estudiantes y para crearlos
 */

public class EditarEstudiante extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    EditText et_cedula;
    EditText et_apellido;
    EditText et_nombre;
    TextView titulo;


    public EditarEstudiante() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EditarEstudiante.
     */
    // TODO: Rename and change types and number of parameters
    public static EditarEstudiante newInstance(String param1, String param2) {
        EditarEstudiante fragment = new EditarEstudiante();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    /*
     * Código autogenerado, que hace un override del método OnCreate
     * */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    /*
    * Código autogenerado, en el que se establecen los valores que tendran los elementos del layout
    * */

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_editar_estudiante, container, false);

        et_cedula = rootView.findViewById(R.id.editTextCedula);
        et_apellido = rootView.findViewById(R.id.editTextApellido);
        et_nombre = rootView.findViewById(R.id.editTextNombre);
        ImageButton bt_guardar = rootView.findViewById(R.id.imageButtonOKEditarEstudiante);
        ImageButton bt_cancelar = rootView.findViewById(R.id.imageButtonCancelarEditarEstudiante);
        titulo  = rootView.findViewById(R.id.textViewTituloEditarEstudiante);

        /*Condicional que define que valores tendran los elementos del layout dependiendo
        de si se va a editar un estudiante o a crear un estudiante.*/
        if(!mParam1.equals("")){
            final Estudiante estudiante = Singleton.getInstance().estudiantes.get(Integer.parseInt(mParam1));
            et_cedula.setText(estudiante.getCedula());
            et_apellido.setText(estudiante.getApellido());
            et_nombre.setText(estudiante.getNombre());
            titulo.setText("Editar Estudiante");
            bt_guardar.setOnClickListener((View.OnClickListener)(new View.OnClickListener() {
                public final void onClick(View it) {
                    editarEstudiante(estudiante);
                    irAMateriasParaInscribirse();
                }
            }));
        }else{
            et_cedula.setText("");
            et_apellido.setText("");
            et_nombre.setText("");
            titulo.setText("Crear Estudiante");
            bt_guardar.setOnClickListener((View.OnClickListener)(new View.OnClickListener() {
                public final void onClick(View it) {
                    guardarEstudiante();
                    irAMateriasParaInscribirse();
                }
            }));
        }

        /*Botón que permite cancelar la creación o edición y regresar al fragmento anterior */
        bt_cancelar.setOnClickListener((View.OnClickListener)(new View.OnClickListener() {
            public final void onClick(View it){
                irAListaEstudiantes();
            }
        }));

        return rootView;
    }


    /*Funcion que permite reemplazar el fragmento actual EditarEstudiante por el fragmento
    FragmentoEstudiantes para volver a la lista de estudiantes.
    * */
    public void irAListaEstudiantes(){
        FragmentoEstudiantes fragmentoEstudiantes = FragmentoEstudiantes.newInstance("", "");
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.root_frame_est, fragmentoEstudiantes);
        fragmentTransaction.addToBackStack(null).commit();
    }

    /*Funcion que permite guardar los datos de un nuevo estudiante*/
    public void guardarEstudiante(){
        String cedula = et_cedula.getText().toString();
        String apellido = et_apellido.getText().toString();
        String nombre = et_nombre.getText().toString();
        Estudiante nuevo_estudiante = new Estudiante(cedula, nombre, apellido);
        nuevo_estudiante.guardarEnArchivo();
        Singleton.getInstance().estudiantes.add(nuevo_estudiante);
    }

    /*Funcion que permite guardar los datos editados de un estudiante */
    public void editarEstudiante(Estudiante estudiante){
        estudiante.setCedula(et_cedula.getText().toString());
        estudiante.setApellido(et_apellido.getText().toString());
        estudiante.setNombre(et_nombre.getText().toString());
        new AdaptadorArchivo().eliminarArchivoMaterias();
    }

    /*Funcion que permite ir al fragmento en el cual se pueden agregar o quitar materias*/

    public void irAMateriasParaInscribirse(){
        AgregarYQuitarMaterias inscribirseEnMaterias = AgregarYQuitarMaterias.newInstance("","");
        FragmentTransaction fragmentTransaction = getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.root_frame_est,inscribirseEnMaterias);
        fragmentTransaction.addToBackStack(null).commit();
    }

}
