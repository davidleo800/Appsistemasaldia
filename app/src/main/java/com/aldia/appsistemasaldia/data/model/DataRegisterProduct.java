package com.aldia.appsistemasaldia.data.model;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DataRegisterProduct {

    public void RegisterProduct(String Id_Client, String products, String valor,
                                    String obs, String Id_user, Context context, View view, ProgressDialog progressDialog,
                                        String url) {

        progressDialog.setMessage("Registrando");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                url, response -> {
            if (response.trim().equals("OK")) {
                snakcBar(view, "Datos registrados");
            } else {
                showToast(context, "Error al registrar datos, comuniquese con su administrador");
            }
            progressDialog.dismiss();
        }, error -> {
            progressDialog.dismiss();
            showToast(context, "Error de servicio "+error.toString());
        }){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> parametros = new HashMap<>();
                parametros.put("Id_Client", Id_Client);
                parametros.put("products", products);
                parametros.put("valor", valor);
                parametros.put("observaciones", obs);
                parametros.put("Id_user", Id_user);
                return parametros;
            }
        };
        VolleySingleton.getInstanceVolley(context).addToRequestQueue(stringRequest);
    }

    public static void showToast(Context mContext, String message){
        Toast.makeText(mContext, message, Toast.LENGTH_LONG).show();
    }

    public static void snakcBar(View view, String message){
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).show();
    }

}
