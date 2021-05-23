package com.aldia.appsistemasaldia.data.model;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.aldia.appsistemasaldia.ui.main.MainAdmin;
import com.aldia.appsistemasaldia.ui.main.MainDirver;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DataLogin {

    private int typeUser;
    private String name, lastname, Id_User;

    public void validateUser(String id, String url, Context context, ProgressDialog progressDialog, TextInputLayout etDocumento){
        progressDialog.setMessage("Iniciado sesión");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                url, response -> {
            if (!response.isEmpty()) {

                try {
                    JSONObject jsonRespuesta = new JSONObject(response);
                    typeUser = jsonRespuesta.getInt("type_user");
                    name = jsonRespuesta.getString("Nombre");
                    lastname = jsonRespuesta.getString("Apellido");
                    Id_User = jsonRespuesta.getString("Id_User");
                    savePreferences(context);
                    if (typeUser == 1) {
                        Intent intent = new Intent(context, MainAdmin.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.putExtra("Nombre", name);
                        intent.putExtra("Apellido", lastname);
                        intent.putExtra("Id_User", Id_User);
                        context.startActivity(intent);
                    } else {
                        Intent intent = new Intent(context, MainDirver.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.putExtra("Nombre", name);
                        intent.putExtra("Apellido", lastname);
                        intent.putExtra("Id_User", Id_User);
                        context.startActivity(intent);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            } else {
                progressDialog.dismiss();
                showToast(context, "Usuario incorrecto");
                etDocumento.setError("Usuario incorrecto");
            }

        }, error -> {
            progressDialog.dismiss();
            showToast(context, error.toString());
        }){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> parametros = new HashMap<>();
                parametros.put("Id_User", id);
                return parametros;
            }
        };
        VolleySingleton.getInstanceVolley(context).addToRequestQueue(stringRequest);
    }

    public void savePreferences(Context context){
        SharedPreferences preferences = context.getSharedPreferences("preferencesLogin", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("Nombre", name);
        editor.putString("Apellido", lastname);
        editor.putString("Id_User", Id_User);
        editor.putInt("type_user", typeUser);
        editor.putBoolean("Session", true);
        editor.commit();
    }


    public static void showToast(Context mContext,String message){
        Toast.makeText(mContext, message, Toast.LENGTH_LONG).show();
    }

}
