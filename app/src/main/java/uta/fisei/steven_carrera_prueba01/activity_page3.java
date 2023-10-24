package uta.fisei.steven_carrera_prueba01;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class activity_page3 extends AppCompatActivity {
    EditText name, lastname, dividendo, divisor, number;
    Button cerrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page3);

        iniciarTercera();
        clickCerrar();
    }

    private void clickCerrar() {
        cerrar = findViewById(R.id.button_cerrar3);

        cerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dividendo = findViewById(R.id.editText_Dividendo3);
                divisor = findViewById(R.id.editText_Divisor3);
                number = findViewById(R.id.editText_NumeroInvertido3);


                String divr = divisor.getText().toString();
                String divd = dividendo.getText().toString();
                String num = number.getText().toString();

                String send = divr + "_" + divd + "_" + num;


                Intent intent = new Intent();
                intent.setData(Uri.parse(send));
                setResult(Activity.RESULT_OK, intent);

                finish();

            }
        });
    }
    private void iniciarTercera() {
        name = findViewById(R.id.editText_Nombres3);
        lastname = findViewById(R.id.editText_Apellidos3);

        Bundle extras = getIntent().getExtras();

        String nombres = extras.getString("nombres");
        String apellidos = extras.getString("apellidos");

        name.setText(nombres);
        lastname.setText(apellidos);
    }
}