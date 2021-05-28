package com.aldia.appsistemasaldia.ui.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.aldia.appsistemasaldia.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import com.aldia.appsistemasaldia.data.model.DataLogin;

public class LoginActivity extends AppCompatActivity {

    private TextView tvSignup;
    private TextInputLayout etDocumento;
    private TextInputEditText tiDocumento;
    private Button btIngresar;
    private ProgressDialog progressDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // textfield.TextInputLayout documento
        etDocumento = findViewById(R.id.etDoc);
        // textfield.TextInputEditText documento
        tiDocumento = findViewById(R.id.tietDoc_log);
        // textView registro
        tvSignup = findViewById(R.id.link_signup);
        // button Iniciar sesion
        btIngresar = findViewById(R.id.btIngresar);

        textwatcherValidacion();
        tvSignup.setOnClickListener(v -> {
            Intent intent = new Intent (v.getContext(), RegisterUser.class);
            startActivityForResult(intent, 0);
        });

        btIngresar.setOnClickListener(v -> {
            DataLogin dataLogin = new DataLogin();
            dataLogin.validateUser(
                    etDocumento.getEditText().getText().toString(),
                    getString(R.string.URL_Login),
                    this,
                    progressDialog,
                    etDocumento
            );
        });

        progressDialog= new ProgressDialog(this);

    }

    public void textwatcherValidacion() {

        tiDocumento.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                etDocumento.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }


}