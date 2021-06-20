package com.aldia.appsistemasaldia.data.model;

import android.content.Context;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import androidx.appcompat.app.AlertDialog;

import com.aldia.appsistemasaldia.R;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.google.android.material.textfield.MaterialAutoCompleteTextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

public class GetClients {
    ArrayList<Model_Clients> ListClients = new ArrayList<>();

    public void getListClients(Context context, String url, MaterialAutoCompleteTextView tietClient) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST,url,
                response -> {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray jsonArray = jsonObject.getJSONArray("Clients");
                        ArrayClients arrayClients = new ArrayClients();
                        ArrayList<String> arr = new ArrayList<>();

                        // datos = jsonArray;
                        for(int i = 0 ; i < jsonArray.length() ; i++) {
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                            ListClients.add(
                                    new Model_Clients(
                                            jsonObject1.getString("Id_Client"),
                                            jsonObject1.getString("Nombre"),
                                            jsonObject1.getString("tel1"),
                                            jsonObject1.getString("tel2"),
                                            jsonObject1.getString("direccion"),
                                            jsonObject1.getString("email")
                                    )
                            );
                            arrayClients.ArraySetClients(
                                    ListClients.get(i).getId_Client(),
                                    ListClients.get(i).getName_Client(),
                                    ListClients.get(i).getPhone1_client(),
                                    ListClients.get(i).getPhone2_client(),
                                    ListClients.get(i).getAddress_Client(),
                                    ListClients.get(i).getEmail_Client());
                            arr.add(ListClients.get(i).getName_Client());
                        }
                        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, arr);
                        tietClient.setAdapter(adapter);
                        String[] array = arr.toArray(new String[0]);
                        tietClient.setValidator(new AutoCompleteTextView.Validator() {
                            @Override
                            public boolean isValid(CharSequence text) {
                                return contains(array, text.toString());
                            }

                            @Override
                            public CharSequence fixText(CharSequence invalidText) {
                                AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.Theme_AppCompat_DayNight_Dialog);
                                builder.setTitle("Error");
                                builder.setMessage("Ingrese un cliente registrado, de lo contrario seleccione '+' para registrarlo");
                                builder.setPositiveButton("Aceptar", null);
                                AlertDialog dialog = builder.create();
                                dialog.show();
                                return "";
                            }
                        });

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, Throwable::printStackTrace);
        VolleySingleton.getInstanceVolley(context).addToRequestQueue(stringRequest);
    }

    public static boolean contains(final String[] arr, final String key) {
        return Arrays.asList(arr).contains(key);
    }
}
