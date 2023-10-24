package uta.fisei.steven_carrera_prueba01;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class activity_page2 extends AppCompatActivity {
    EditText nombre, apellido, dividendo, divisor, numero;
    Button siguiente, cerrar;

    ActivityResultLauncher<Intent> activityResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {

                    if (result.getResultCode() == Activity.RESULT_OK){
                        Intent data = result.getData();
                        cerrar.setEnabled(true);
                        String[] resultado = data.getDataString().split("_");
                        dividendo.setText(resultado[1]);
                        divisor.setText(resultado[0]);
                        numero.setText(resultado[2]);
                    }
                }
            });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page2);

        iniciarPagina();
    }

    private void iniciarPagina() {
        nombre = findViewById(R.id.editText_Nombres1);
        apellido = findViewById(R.id.editText_Apellidos2);
        dividendo = findViewById(R.id.editText_Dividendo2);
        divisor = findViewById(R.id.editText_Divisor2);
        numero = findViewById(R.id.editText_NumeroInvertido2);
        siguiente = findViewById(R.id.button_Siguiente2);
        cerrar = findViewById(R.id.button_cerrar2);

        clickSiguiente();
        clickCerrar();
    }

    private void clickCerrar() {
        cerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String send = nombre.getText().toString() + "_"+ apellido.getText().toString() + "_" + dividendo.getText().toString() + "_" + divisor.getText().toString() + "_" + numero.getText().toString();

                Intent intent = new Intent();
                intent.setData(Uri.parse(send));
                setResult(Activity.RESULT_OK, intent);

                finish();
            }
        });
    }

    private void clickSiguiente() {
        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombres = nombre.getText().toString();
                String apellidos = apellido.getText().toString();

                Intent toThird = new Intent(activity_page2.this, activity_page3.class);

                toThird.putExtra("nombres", nombres);
                toThird.putExtra("apellidos", apellidos);

                activityResult.launch(toThird);;
            }
        });
    }
}