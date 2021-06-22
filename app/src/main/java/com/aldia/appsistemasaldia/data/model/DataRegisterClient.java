package com.aldia.appsistemasaldia.data.model;

import android.app.ProgressDialog;
import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.snackbar.Snackbar;

import java.util.HashMap;
import java.util.Map;

public class DataRegisterClient {

    public void RegisterClient(String Id_Client, String name_Client, String phone1_client,
                                String phone2_client, String address_Client,String email_Client, Context context, View view, ProgressDialog progressDialog,
                                String url) {

        progressDialog.setMessage("Registrando");
        progressDialog.show();
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                url, response -> {
            if (response.trim().equals("OK")) {
                snackBar(view, "Datos registrados");
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
                parametros.put("Nombre", name_Client);
                parametros.put("tel1", phone1_client);
                parametros.put("tel2", phone2_client);
                parametros.put("direccion", address_Client);
                parametros.put("email", email_Client);
                return parametros;
            }
        };
        VolleySingleton.getInstanceVolley(context).addToRequestQueue(stringRequest);
    }

    public static void showToast(Context mContext, String message){
        Toast.makeText(mContext, message, Toast.LENGTH_LONG).show();
    }

    public static void snackBar(View view, String message){
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).show();
    }

}
