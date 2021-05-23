package com.aldia.appsistemasaldia.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.aldia.appsistemasaldia.R;
import com.aldia.appsistemasaldia.data.model.DataRegisterProduct;
import com.aldia.appsistemasaldia.data.model.GetProducts;
import com.aldia.appsistemasaldia.ui.login.LoginActivity;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;


public class MainDirver extends AppCompatActivity {

    private Toolbar toolbar;
    private EditText etDate;
    private TextView tvName, tvLastname;
    private TextInputLayout tiClient, tiProduct, tiObs;
    String Id_user;
    private ProgressDialog progressDialog;
    private Button btnAgregar;
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
        // textfield.TextInputLayout Product
        tiProduct = findViewById(R.id.tiProduct);
        // textfield.TextInputLayout Observaciones
        tiObs = findViewById(R.id.tiObs);
        // Button btnAgregar
        btnAgregar = findViewById(R.id.btnAgregar);

        // coordinatorLayout defined
        coorLayout = findViewById(R.id.coordinatorLayoutDriver);
        // Set Toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // RecyclerView
        rvProducts = findViewById(R.id.rvProducts);


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
            editor.commit();
        } else {
            tvName.setText(nombre);
            tvLastname.setText(apell);
        }
        // Fin Obtener datos de sesion


        btnAgregar.setOnClickListener(v -> {
            progressDialog.setMessage("Cargando datos");
            progressDialog.show();
            registerProduct();

        });


        // Progress dialog
        progressDialog= new ProgressDialog(this);
    }

    public void registerProduct(){

        int cantidad = 1;
        String ref = "8";
        BigDecimal valor = new java.math.BigDecimal("20000");

        JSONArray jsonArrayProducto = new JSONArray();
        JSONObject jsonObjectProducto = new JSONObject();
        try {
            jsonObjectProducto.put("Id_Client", tiClient.getEditText().getText().toString());
            jsonObjectProducto.put("Id_product", tiProduct.getEditText().getText().toString());
            jsonObjectProducto.put("cantidad", cantidad);
            jsonObjectProducto.put("valor", valor);
            jsonObjectProducto.put("observaciones", tiObs.getEditText().getText().toString());
            jsonObjectProducto.put("Id_user", String.valueOf(Id_user));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        jsonArrayProducto.put(jsonObjectProducto);

        JSONObject json = new JSONObject();
        try {
            json.put("Product", jsonArrayProducto);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        DataRegisterProduct dataRegisterProduct = new DataRegisterProduct();
        System.out.println(json);
        String jsonStr = json.toString();
        dataRegisterProduct.RegisterProduct(
                ref,
                tiClient.getEditText().getText().toString(),
                tiProduct.getEditText().getText().toString(),
                Integer.toString(cantidad),
                valor.toString(),
                tiObs.getEditText().getText().toString(),
                String.valueOf(Id_user),
                this,
                coorLayout,
                progressDialog,
                getString(R.string.URL_RegisterProduct)
        );

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                // User chose the "Settings" item, show the app settings UI...
                SharedPreferences preferences = getSharedPreferences("preferencesLogin", Context.MODE_PRIVATE);
                preferences.edit().clear().commit();
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

}