package edu.dami.guiameapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.security.InvalidParameterException;

public class SignUpActivity extends AppCompatActivity {

    TextInputLayout tilFullname, tilEmail;
    EditText etFullname, etEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        setup();
    }

    private void setup() {
        tilFullname = findViewById(R.id.til_fullname);
        tilEmail = findViewById(R.id.til_email);

        etFullname = tilFullname.getEditText();
        etEmail = tilEmail.getEditText();

        Button btnSignUp = findViewById(R.id.btn_signup);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToMain();
            }
        });
    }

    private void navigateToMain() {
        if(!validateFields()) {
            return;
        }

        if(etFullname.getText() == null || etEmail.getText() == null)
            throw new InvalidParameterException();

        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra(MainActivity.FULLNAME_KEY, etFullname.getText().toString());
        intent.putExtra(MainActivity.EMAIL_KEY, etEmail.getText().toString());
        startActivity(intent);
    }

    private boolean validateFields() {
        if(etFullname.getText() == null || TextUtils.isEmpty(etFullname.getText().toString())) {
            showMessage("Favor ingresa tu nombre completo :)");
            return false;
        }
        if(etEmail.getText() == null || TextUtils.isEmpty(etEmail.getText().toString())) {
            showMessage("Favor ingresa tu email :)");
            return false;
        }

        showMessage("Todo bien, todo correcto, gracias!");
        return true;
    }

    private void showMessage(String message) {
        Toast.makeText(
                this,
                message,
                Toast.LENGTH_LONG
        ).show();
    }
}