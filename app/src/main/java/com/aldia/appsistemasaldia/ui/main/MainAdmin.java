package com.aldia.appsistemasaldia.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.aldia.appsistemasaldia.R;
import com.aldia.appsistemasaldia.data.model.GetInvoice;
import com.aldia.appsistemasaldia.ui.login.LoginActivity;

import java.util.ArrayList;

public class MainAdmin extends AppCompatActivity {

    private Toolbar toolbar;
    ArrayList<String> listData;
    RecyclerView recyclerView;
    String Id_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_admin);
        // Set Toolbar
        toolbar = findViewById(R.id.toolbarAdmin);
        setSupportActionBar(toolbar);

        recyclerView = findViewById(R.id.rvAdminFactura);

        // Obtener datos de sesion
        Bundle extras = getIntent().getExtras();
        String nombre;
        String apell;
        SharedPreferences preferencesSesion = getSharedPreferences("preferencesSesion", Context.MODE_PRIVATE);
        nombre = preferencesSesion.getString("Nombre","");
        apell = preferencesSesion.getString("Apellido","");
        Id_user = preferencesSesion.getString("Id_User","");
        /*if (apell.equals("") || nombre.equals("") || Id_user.equals("")) {
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
        }*/
        // Fin Obtener datos de sesion

        GetInvoice getInvoice = new GetInvoice();
        getInvoice.getListInvoices(this, getString(R.string.URL_GetInvoice), recyclerView);

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
            //SharedPreferences preferences = getSharedPreferences("preferencesLogin2", Context.MODE_PRIVATE);
            //preferences.edit().clear().commit();
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            finish();
        }// If we got here, the user's action was not recognized.
        // Invoke the superclass to handle it.
        return super.onOptionsItemSelected(item);
    }


}