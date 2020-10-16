package edu.dami.guiameapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.app.DatePickerDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {


    DatePicker simpleDatePicker;
    Button submit;

    public static final String FULLNAME_KEY = "FULLNAME";
    public static final String EMAIL_KEY = "EMAIL";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        simpleDatePicker = (DatePicker) findViewById(R.id.simpleDatePicker);
        submit = (Button) findViewById(R.id.submitButton);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dia = "Dia = " + simpleDatePicker.getDayOfMonth();
                String mes = "Mes = " + (simpleDatePicker.getMonth() + 1);
                String Año = "Año = " + simpleDatePicker.getYear();

                Toast.makeText(getApplicationContext(), dia + "\n" + mes + "\n" + Año, Toast.LENGTH_LONG).show();
            }
        });
        setup();
    }


    private void setup() {
        Intent startIntent = getIntent();
        if(startIntent == null) {
            Toast.makeText(
                    this,
                    "Algo salió mal al obtener los datos :(",
                    Toast.LENGTH_SHORT
            ).show();
            return;
        }

        String fullname = startIntent.getStringExtra(FULLNAME_KEY);
        if(TextUtils.isEmpty(fullname)) {
            fullname = "Usuario";
        }
        if(getSupportActionBar() != null) {
            getSupportActionBar().setTitle(getString(R.string.welcome_user_title, fullname));
        }

        String email = startIntent.getStringExtra(EMAIL_KEY);
        if(TextUtils.isEmpty(email)) {
            Toast.makeText(
                    this,
                    R.string.cannot_get_email,
                    Toast.LENGTH_SHORT
            ).show();
            return;
        }

        //TextView tvConfirmDesc = findViewById(R.id.tv_confirm_desc);
        //tvConfirmDesc.setText(getString(R.string.confirm_desc, email));
    }
}