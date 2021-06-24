package com.aldia.appsistemasaldia.ui.main;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.aldia.appsistemasaldia.R;
import com.aldia.appsistemasaldia.data.model.ArrayClients;
import com.aldia.appsistemasaldia.data.model.ArrayProductsFac;
import com.aldia.appsistemasaldia.data.model.DataRegisterProduct;
import com.aldia.appsistemasaldia.data.model.GetClients;
import com.aldia.appsistemasaldia.data.model.GetProducts;
import com.aldia.appsistemasaldia.data.model.mailerApi.MailAPI;
import com.aldia.appsistemasaldia.ui.ViewActivityIntern.RegisterClient;
import com.aldia.appsistemasaldia.ui.login.LoginActivity;
import com.aldia.appsistemasaldia.ui.login.RegisterUser;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;


public class MainDirver extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView tvName, tvLastname;
    private TextInputLayout tiClient, tiObs;
    // private TextInputEditText tietClient;
    // private AppCompatAutoCompleteTextView tietClient;
    private MaterialAutoCompleteTextView tietClient;
    String Id_user;
    private ProgressDialog progressDialog;
    private Button btnAgregar, btnAddClient;
    private CoordinatorLayout coorLayout;
    private ListView listViewItems;
    private RecyclerView rvProducts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dirver);

        // text views
        tvName = findViewById(R.id.tvName);
        tvLastname = findViewById(R.id.tvLastname);

        // textfield.TextInputLayout Client
        tiClient = findViewById(R.id.tiClient);
        // textfield.TextInputLayout Observaciones
        tiObs = findViewById(R.id.tiObs);
        // textfield.TextInputEditText
        // tietClient = findViewById(R.id.tietClient);
        // AppCompatAutoCompleteTextView
        tietClient = findViewById(R.id.tietClient);

        // Button btnAgregar
        btnAgregar = findViewById(R.id.btnAgregar);
        // Button btnAgregarClient
        btnAddClient = findViewById(R.id.btnAddClient);

        // coordinatorLayout defined
        coorLayout = findViewById(R.id.coordinatorLayoutDriver);
        // Set Toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // RecyclerView
        rvProducts = findViewById(R.id.rvProducts);
        // Procesa si se hizo un cambio en los textField
        textwatcherValidacion();

        // listar productos
        GetProducts getProducts = new GetProducts();
        getProducts.getListProducts(this, getString(R.string.URL_GetProduct), rvProducts);


        // Obtener datos de sesion
        Bundle extras = getIntent().getExtras();
        String nombre;
        String apell;
        SharedPreferences preferencesSesion = getSharedPreferences("preferencesSesion", Context.MODE_PRIVATE);
        nombre = preferencesSesion.getString("Nombre","");
        apell = preferencesSesion.getString("Apellido","");
        Id_user = preferencesSesion.getString("Id_User","");

        if (apell.equals("") || nombre.equals("") || Id_user.equals("")) {
            nombre = extras.getString("Nombre");
            apell = extras.getString("Apellido");
            Id_user = extras.getString("Id_User");
            tvName.setText(nombre);
            tvLastname.setText(apell);
            SharedPreferences preferences = getSharedPreferences("preferencesSesion", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("Nombre", nombre);
            editor.putString("Apellido", apell);
            editor.putString("Id_User", Id_user);
            editor.apply();
        } else {
            tvName.setText(nombre);
            tvLastname.setText(apell);
        }
        // Fin Obtener datos de sesion

        //Button Registrar
        btnAgregar.setOnClickListener(v -> {
            if(Objects.requireNonNull(tiClient.getEditText()).getText().toString().equals("")){
                tiClient.setError("Complete este campo");
            }else {
                progressDialog.setMessage("Cargando datos");
                progressDialog.show();
                registerProduct();
                hideKeyboard();
            }
        });

        btnAddClient.setOnClickListener(v ->{
            Intent intent = new Intent (v.getContext(), RegisterClient.class);
            startActivity(intent);
            //startActivityForResult(intent, 0);
        });

        // Fill AppCompatAutoCompleteTextView
        GetClients getClients = new GetClients();
        getClients.getListClients(this, getString(R.string.URL_GetClients), tietClient);


        // Progress dialog
        progressDialog= new ProgressDialog(this);
    }


    public void registerProduct(){

        ArrayClients arrayClients = new ArrayClients();
        // System.out.println(arrayClients.getModel_Clients().get(1).getId_Client());
        String emailTarget = "";
        for (int i = 0; i <arrayClients.getModel_Clients().size(); i++) {
            if(arrayClients.getModel_Clients().get(i).getId_Client().equals(Objects.requireNonNull(tiClient.getEditText()).getText().toString())){
                emailTarget = arrayClients.getModel_Clients().get(i).getEmail_Client();
                break;
            }
        }

        AlertDialog.Builder dialogo1 = new AlertDialog.Builder(this);
        dialogo1.setTitle("¿Desea registrar la compra de estos productos?");

        ArrayProductsFac arr = new ArrayProductsFac();
        double total = 0;
        StringBuilder produtosfinales = new StringBuilder();
        ArrayList<String> arraNew = new ArrayList<>();
        for (int i = 0; i < arr.getModel_ProductsFactura().size(); i++) {
            total += arr.getModel_ProductsFactura().get(i).getAmount();
            arraNew.add("{\"Id\": \""+arr.getModel_ProductsFactura().get(i).getId_product()+"\", "+
                    "\"Name\": \""+arr.getModel_ProductsFactura().get(i).getProduct_name()+"\", "+
                    "\"Cant\": \""+arr.getModel_ProductsFactura().get(i).getCant()+"\", "+
                    "\"weight\": \""+arr.getModel_ProductsFactura().get(i).getWeight()+"\", "+
                    "\"Amount\": \""+arr.getModel_ProductsFactura().get(i).getAmount()+"\"}");
            produtosfinales.append("Id: ").append(arr.getModel_ProductsFactura().get(i).getId_product())
                    .append(" Nombre: ").append(arr.getModel_ProductsFactura().get(i).getProduct_name())
                    .append(" Cant: ").append(arr.getModel_ProductsFactura().get(i).getCant())
                    .append(" Peso: ").append(arr.getModel_ProductsFactura().get(i).getWeight())
                    .append(" Precio: ").append(arr.getModel_ProductsFactura().get(i).getAmount()).append("\n");
        }
        dialogo1.setMessage(""+produtosfinales+"\n"+"Total: "+total);


        dialogo1.setCancelable(false);
        double finalTotal = total;

        if(total == 0){
            progressDialog.dismiss();
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Error al intentar registrar factura");
            builder.setMessage("Debe seleccionar al menos un producto");
            builder.setPositiveButton("Aceptar", null);

            AlertDialog dialog = builder.create();
            dialog.show();
        }else {
            String finalEmailTarget = emailTarget;
            dialogo1.setPositiveButton("Confirmar", (dialogo11, id) -> {
                System.out.println(arraNew.toString());
                System.out.println(finalTotal);


                DataRegisterProduct dataRegisterProduct = new DataRegisterProduct();
                // System.out.println(json);
                // String jsonStr = json.toString();
                dataRegisterProduct.RegisterProduct(
                        tietClient.getText().toString(),
                        arraNew.toString(),
                        String.valueOf(finalTotal),
                        Objects.requireNonNull(tiObs.getEditText()).getText().toString(),
                        String.valueOf(Id_user),
                        getApplicationContext(),
                        coorLayout,
                        progressDialog,
                        getString(R.string.URL_RegisterProduct)
                );

                MailAPI mailAPI = new MailAPI(getApplicationContext(),
                        finalEmailTarget,
                        "(NO REPLY) FACTURA App sistemas al día",
                        produtosfinales+"\n"+tiObs.getEditText().getText().toString()+"\n"+"Total: "+ finalTotal);
                mailAPI.execute();
                tietClient.setText("");
            });
            dialogo1.setNegativeButton("Cancelar", (dialogo112, id) -> {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(), "Operación cancelada", Toast.LENGTH_LONG).show();
            });
            dialogo1.show();
        }


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.logout) {// User chose the "Settings" item, show the app settings UI...
            SharedPreferences.Editor preferences = getSharedPreferences("preferencesLogin", MODE_PRIVATE).edit();
            SharedPreferences.Editor preferencesSesion = getSharedPreferences("preferencesSesion", MODE_PRIVATE).edit();
            preferencesSesion.clear().apply();
            preferences.clear().apply();
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            finish();
        }// If we got here, the user's action was not recognized.
        // Invoke the superclass to handle it.
        return super.onOptionsItemSelected(item);
    }

    public void textwatcherValidacion() {

        tietClient.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                tiClient.setError(null);
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