package com.aldia.appsistemasaldia.ui.ViewActivityIntern;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.aldia.appsistemasaldia.R;
import com.aldia.appsistemasaldia.data.model.DataRegisterClient;
import com.aldia.appsistemasaldia.ui.login.LoginActivity;
import com.aldia.appsistemasaldia.ui.main.MainDirver;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.Objects;

public class RegisterClient extends AppCompatActivity {


    private MaterialToolbar materialToolbar;

    private TextInputLayout tiIdClient, tiNameClient, tiTel1Client, tiTel2Client, tiAddressClient, tiEmailClient;
    private TextInputEditText tietIdClient, tietNameClient, tietTel1Client, tietTel2Client, tietAddressClient, tietEmailClient;

    private Button btnRegClient;

    private CoordinatorLayout coorLayout;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_client);

        btnRegClient = findViewById(R.id.btnRegClient);

        // define materialToolbar
        materialToolbar = findViewById(R.id.topAppBarRegClient);

        // define TextInputLayout
        tiIdClient = findViewById(R.id.tiIdClient);
        tiNameClient = findViewById(R.id.tiNameClient);
        tiTel1Client = findViewById(R.id.tiTel1Client);
        tiTel2Client = findViewById(R.id.tiTel2Client);
        tiAddressClient = findViewById(R.id.tiAddressClient);
        tiEmailClient = findViewById(R.id.tiEmailClient);

        // define TextInputEditText
        tietIdClient = findViewById(R.id.tietIdClient);
        tietNameClient = findViewById(R.id.tietNameClient);
        tietTel1Client = findViewById(R.id.tietTel1Client);
        tietTel2Client = findViewById(R.id.tietTel2Client);
        tietAddressClient = findViewById(R.id.tietAddressClient);
        tietEmailClient = findViewById(R.id.tietEmailClient);

        // coordinatorLayout defined
        coorLayout = findViewById(R.id.coordinatorLayoutregclient);

        // progressDialog defined
        progressDialog = new ProgressDialog(this);


        materialToolbar.setNavigationOnClickListener(v -> {
            Intent intent = new Intent (v.getContext(), MainDirver.class);
            startActivity(intent);
            //startActivityForResult(intent, 0);
        });

        btnRegClient.setOnClickListener(v -> {

            // Handle of errors of inputs
            if(Objects.requireNonNull(tiIdClient.getEditText()).getText().toString().equals("") ||
                    Objects.requireNonNull(tiNameClient.getEditText()).getText().toString().equals("") ||
                    Objects.requireNonNull(tiTel1Client.getEditText()).getText().toString().equals("") ||
                    Objects.requireNonNull(tiTel2Client.getEditText()).getText().toString().equals("") ||
                    Objects.requireNonNull(tiAddressClient.getEditText()).getText().toString().equals("") ||
                    Objects.requireNonNull(tiEmailClient.getEditText()).getText().toString().equals("")){
                if(tiIdClient.getEditText().getText().toString().equals(""))
                    tiIdClient.setError("Complete este campo");
                else  tiIdClient.setError(null);
                if(tiNameClient.getEditText().getText().toString().equals(""))
                    tiNameClient.setError("Complete este campo");
                else tiNameClient.setError(null);
                if(tiTel1Client.getEditText().getText().toString().equals(""))
                    tiTel1Client.setError("Complete este campo");
                else tiTel1Client.setError(null);
                if(tiTel2Client.getEditText().getText().toString().equals(""))
                    tiTel2Client.setError("Complete este campo");
                else tiTel2Client.setError(null);
                if(tiAddressClient.getEditText().getText().toString().equals(""))
                    tiAddressClient.setError("Complete este campo");
                else tiAddressClient.setError(null);
                if(tiEmailClient.getEditText().getText().toString().equals(""))
                    tiEmailClient.setError("Complete este campo");
                else tiEmailClient.setError(null);
            }else {
                progressDialog.setMessage("Cargando datos");
                progressDialog.show();
                registerClient();
                hideKeyboard();
            }

        });

        // Detecta cambios en los TextInputEditText
        textwatcherValidacion();

    }

    public void registerClient(){

        DataRegisterClient dataRegisterClient = new DataRegisterClient();
        dataRegisterClient.RegisterClient(
                tiIdClient.getEditText().getText().toString(),
                tiNameClient.getEditText().getText().toString(),
                tiTel1Client.getEditText().getText().toString(),
                tiTel2Client.getEditText().getText().toString(),
                tiAddressClient.getEditText().getText().toString(),
                tiEmailClient.getEditText().getText().toString(),
                getApplicationContext(),
                coorLayout,
                progressDialog,
                getString(R.string.URL_RegisterClients)
        );
        progressDialog.dismiss();

    }


    private void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }


    public void textwatcherValidacion() {

        tietIdClient.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tiIdClient.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        tietNameClient.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tiNameClient.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        tietTel1Client.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tiTel1Client.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        tietTel2Client.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tiTel2Client.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        tietAddressClient.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tiAddressClient.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        tietEmailClient.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tiEmailClient.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }


}