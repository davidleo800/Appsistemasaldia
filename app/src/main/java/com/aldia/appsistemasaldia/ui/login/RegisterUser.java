package com.aldia.appsistemasaldia.ui.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.aldia.appsistemasaldia.R;
import com.aldia.appsistemasaldia.data.model.DataRegisterUser;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class RegisterUser extends AppCompatActivity {

    private MaterialToolbar materialToolbar;
    private TextInputLayout tiId, tiNombre, tiApellido;
    private TextInputEditText tietId, tietNombre, tietApellido;
    private Button btnRegistrar;
    private RadioButton rbAdmin, rbDriver;
    private RadioGroup rgItems;
    private CoordinatorLayout coorLayout;
    ProgressDialog progressDialog;
    private int typeUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);
        // textField.TextInputLayout defined
        tiId = findViewById(R.id.tiId);
        tiNombre = findViewById(R.id.tiNom);
        tiApellido = findViewById(R.id.tiApell);
        // textInput.EditText defined
        tietId = findViewById(R.id.tietId);
        tietNombre = findViewById(R.id.tietNom);
        tietApellido = findViewById(R.id.tietApell);
        // RadioButton defined
        rbAdmin = findViewById(R.id.rbAdmin);
        rbDriver = findViewById(R.id.rbDriver);
        // RadioGroup defined
        rgItems = findViewById(R.id.rgItems);
        // Button defined
        btnRegistrar = findViewById(R.id.btnReg);
        // materialToolbar defined
        materialToolbar = findViewById(R.id.topAppBar);
        // coordinatorLayout defined
        coorLayout = findViewById(R.id.coordinatorLayoutreg);
        // progressDialog defined
        progressDialog = new ProgressDialog(this);

        // Procesa si se hizo un cambio en los textField
        textwatcherValidacion();

        // Comeback to Login
        materialToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), LoginActivity.class);
                startActivityForResult(intent, 0);
            }
        });

        // Action to Register user
        btnRegistrar.setOnClickListener(v -> registerUser());

    }

    public void registerUser () {
        typeUser = 0;
        if (rbAdmin.isChecked()){
            typeUser = 1;
        }
        if (rbDriver.isChecked()){
            typeUser = 2;
        }
        if(tiId.getEditText().getText().toString().equals("") ||
                tiNombre.getEditText().getText().toString().equals("") ||
                tiApellido.getEditText().getText().toString().equals("")){
            if(tiId.getEditText().getText().toString().equals(""))
                tiId.setError("Complete este campo");
            else  tiId.setError(null);
            if(tiNombre.getEditText().getText().toString().equals(""))
                tiNombre.setError("Complete este campo");
            else tiNombre.setError(null);
            if(tiApellido.getEditText().getText().toString().equals(""))
                tiApellido.setError("Complete este campo");
            else tiApellido.setError(null);
        }else {
            DataRegisterUser dataRegisterUser = new DataRegisterUser();
            dataRegisterUser.validateUser(
                    tiId.getEditText().getText().toString(),
                    tiNombre.getEditText().getText().toString(),
                    tiApellido.getEditText().getText().toString(),
                    typeUser,
                    getApplicationContext(),
                    coorLayout,
                    getString(R.string.URL_RegisterUser),
                    getString(R.string.URL_Login)
            );
            hideKeyboard();
            tietId.setText("");
            tietNombre.setText("");
            tietApellido.setText("");
        }


    }

    // Procesa si se hizo un cambio en los textfield
    public void textwatcherValidacion() {

        tietId.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tiId.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        tietNombre.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tiNombre.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        tietApellido.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tiApellido.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void hideKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

}