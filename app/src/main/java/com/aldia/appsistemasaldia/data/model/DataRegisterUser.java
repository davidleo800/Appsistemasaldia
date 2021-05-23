package com.aldia.appsistemasaldia.data.model;


import android.content.Context;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.snackbar.Snackbar;

import java.util.HashMap;
import java.util.Map;


public class DataRegisterUser {

    JsonObjectRequest jsonObjectRequest;

    public void RegisterUser(String Id, String Name, String lastName, int typeUser, Context context, View view, String url) {

        String req;

        req =  url+"?"+
                "Id_User="+Id+
                "&Nombre="+Name+
                "&Apellido="+lastName+
                "&type_user="+typeUser;
             // url = url.replace(" ", "%20");
             jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, req, null,
                     response -> {
                         snakcBar(view, "Se registró exitosamente");
                    }, error -> {
                        System.out.println(error);
                        showToast(context, "Error al registrar"+error);
                    });
             VolleySingleton.getInstanceVolley(context).addToRequestQueue(jsonObjectRequest);
        }

    public void validateUser(String Id, String Name, String lastName, int typeUser, Context context, View view, String urlRegister, String urlLogin){
        StringRequest stringRequest = new StringRequest(Request.Method.POST,
                urlLogin, response -> {
                    if(!response.isEmpty()){
                        showToast(context, "Usuario existente, por favor ingrese uno nuevo");
                    }else{
                        // no existe usuario
                        RegisterUser(Id, Name, lastName, typeUser, context, view, urlRegister);
                    }

                }, error -> {
                showToast(context, "Error al registrar"+error);
        }){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> parametros = new HashMap<>();
                parametros.put("Id_User", Id);
                return parametros;
            }
        };
        VolleySingleton.getInstanceVolley(context).addToRequestQueue(stringRequest);
    }

    public static void showToast(Context mContext,String message){
        Toast.makeText(mContext, message, Toast.LENGTH_LONG).show();
    }

    public static void snakcBar(View view,String message){
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).show();
    }
}
