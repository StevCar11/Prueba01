package uta.fisei.steven_carrera_prueba01;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    int num;
    Button siguiente, resultados;
    EditText nombre, apellido, dividendo, divisor, parteEntera, residuo, numero;

    ActivityResultLauncher<Intent> activityResult = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {

                    if (result.getResultCode() == Activity.RESULT_OK){
                        nombre = findViewById(R.id.editText_Nombres1);
                        apellido = findViewById(R.id.editText_Apellidos1);
                        dividendo = findViewById(R.id.editText_Dividendo1);
                        divisor = findViewById(R.id.editText_Divisor1);
                        parteEntera = findViewById(R.id.editText_ParteEntera1);
                        residuo = findViewById(R.id.editText_Residuo1);
                        numero = findViewById(R.id.editText_NumeroInvertido1);

                        Intent data = result.getData();
                        String[] resultado = data.getDataString().split("_");
                        resultados.setEnabled(true);
                        nombre.setText(resultado[0]);
                        apellido.setText(resultado[1]);
                        dividendo.setText(resultado[2]);
                        divisor.setText(resultado[3]);
                        num = Integer.valueOf(resultado[4]);
                    }
                }
            });

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iniciarPrograma();
    }

    private void iniciarPrograma() {
        siguiente = findViewById(R.id.button_Siguiente1);
        resultados = findViewById(R.id.button_resultados);

        clickSiguiente();
        clickResultados();
    }

    private void clickResultados() {

        resultados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int divd = Integer.valueOf(dividendo.getText().toString());
                int divr = Integer.valueOf(divisor.getText().toString());

                int cos = 0;

                while (divd > 0) {

                    if (divd< divr) {
                        break;
                    }
                    divd = divd - divr;
                    cos++;
                }

                parteEntera.setText(String.valueOf(cos));
                residuo.setText(String.valueOf(divd));


                int invertido = 0, resto;


                while( num > 0 ) {

                    resto = num % 10;
                    invertido = invertido * 10 + resto;
                    num /= 10;
                }

                numero.setText(String.valueOf(invertido));
            }

        });
    }

    private void clickSiguiente() {
        siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toSecond = new Intent(MainActivity.this, activity_page2.class);
                activityResult.launch(toSecond);
            }
        });
    }
}