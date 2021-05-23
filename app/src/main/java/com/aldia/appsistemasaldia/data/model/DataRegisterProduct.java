package com.aldia.appsistemasaldia.data.model;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.Toast;

import com.aldia.appsistemasaldia.ui.main.MainAdmin;
import com.aldia.appsistemasaldia.ui.main.MainDirver;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public class DataRegisterProduct {

    public void RegisterProduct(String ref, String Id_Client, String Id_product, String cantidad, String valor,
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
                parametros.put("ref", ref);
                parametros.put("Id_Client", Id_Client);
                parametros.put("Id_product", Id_product);
                parametros.put("cantidad", cantidad);
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
