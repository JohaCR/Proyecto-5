import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.proyectofragmentos.R;
import com.example.proyectofragmentos.adaptador.Singleton;

public class MateriaAgregarRV extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        Intent intent = getIntent();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.materia_agregar_ly);
        int idMateria = intent.getIntExtra("idMateria", 0);
        llenarInformacionMateria(idMateria);

    }

    public void llenarInformacionMateria(int idMateria) {

        TextView codigo = findViewById(R.id.textViewCodigoAgregar);
        codigo.setText(Singleton.getInstance().materias.get(idMateria).getCodigo());

        TextView nombreMateria = findViewById(R.id.textViewNombreMateriaAgregar);
        nombreMateria.setText(Singleton.getInstance().materias.get(idMateria).getNombre());

        TextView profesor = findViewById(R.id.textViewProfesorAgregar);
        profesor.setText(Singleton.getInstance().materias.get(idMateria).getProfesor());


        ImageButton inscribirse = findViewById(R.id.imageButtonInscribirse);
        inscribirse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

            }
        });


    }


}

