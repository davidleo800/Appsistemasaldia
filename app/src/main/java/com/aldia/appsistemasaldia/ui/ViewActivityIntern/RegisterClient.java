package com.aldia.appsistemasaldia.ui.ViewActivityIntern;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.aldia.appsistemasaldia.R;
import com.aldia.appsistemasaldia.ui.login.LoginActivity;
import com.aldia.appsistemasaldia.ui.main.MainDirver;
import com.google.android.material.appbar.MaterialToolbar;

public class RegisterClient extends AppCompatActivity {


    private MaterialToolbar materialToolbar;

    private Button btnRegClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_client);

        btnRegClient = findViewById(R.id.btnRegClient);

        // define back
        materialToolbar = findViewById(R.id.topAppBarRegClient);

        materialToolbar.setNavigationOnClickListener(v -> {
            Intent intent = new Intent (v.getContext(), MainDirver.class);
            startActivityForResult(intent, 0);
        });

        btnRegClient.setOnClickListener(v -> {

        });

    }
}