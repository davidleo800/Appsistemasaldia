package com.aldia.appsistemasaldia.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.aldia.appsistemasaldia.R;
import com.aldia.appsistemasaldia.ui.main.MainAdmin;
import com.aldia.appsistemasaldia.ui.main.MainDirver;

public class LastSession extends AppCompatActivity {

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_session);

        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        new Handler().postDelayed(() -> {
            SharedPreferences preferences = getSharedPreferences("preferencesLogin", Context.MODE_PRIVATE);
            Boolean session = preferences.getBoolean("Session", false);
            int typeUser = preferences.getInt("type_user",0);
            if(session){

                if(typeUser == 1){
                    Intent intent = new Intent(getApplicationContext(), MainAdmin.class);
                    startActivity(intent);
                    finish();
                }else if(typeUser == 2){
                    Intent intent = new Intent(getApplicationContext(), MainDirver.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(LastSession.this, "No encontro sesion",Toast.LENGTH_LONG).show();
                }
            }else{
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, 2000);

    }
}