package com.aldia.appsistemasaldia.data.model;

import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aldia.appsistemasaldia.ui.main.RecyclerViewAdapterAdmin;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class GetInvoice {
    ArrayList<Model_Invoice> ListProducts = new ArrayList<>();
    // JSONArray datos;
    RecyclerViewAdapterAdmin recyclerViewAdapterAdmin;

    public void getListInvoices(Context context, String url, RecyclerView recyclerView) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST,url,
                response -> {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray jsonArray = jsonObject.getJSONArray("Invoice");
                        // datos = jsonArray;
                        for(int i = 0 ; i < jsonArray.length() ; i++) {
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                            ListProducts.add(
                                    new Model_Invoice(
                                            jsonObject1.getString("Id_fac"),
                                            jsonObject1.getString("Id_Client"),
                                            jsonObject1.getString("dateCurrent"),
                                            jsonObject1.getString("products"),
                                            jsonObject1.getString("valor"),
                                            jsonObject1.getString("observaciones"),
                                            jsonObject1.getString("Id_user")
                                    )
                            );
                        }
                        // System.out.println(datos);
                        recyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
                        recyclerViewAdapterAdmin = new RecyclerViewAdapterAdmin(context, ListProducts);
                        recyclerView.setAdapter(recyclerViewAdapterAdmin);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, error -> error.printStackTrace());
        VolleySingleton.getInstanceVolley(context).addToRequestQueue(stringRequest);
    }
}
