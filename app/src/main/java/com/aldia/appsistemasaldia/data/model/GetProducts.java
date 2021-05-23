package com.aldia.appsistemasaldia.data.model;

import android.content.Context;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.aldia.appsistemasaldia.ui.main.RecyclerViewAdapterProducts;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GetProducts {

    List<tb_Details_Product> ListProducts = new ArrayList<>();
    // JSONArray datos;
    RecyclerViewAdapterProducts recyclerViewAdapterProducts;

    public void getListProducts(Context context, String url, RecyclerView recyclerView) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST,url,
                response -> {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray jsonArray = jsonObject.getJSONArray("Products");
                        // datos = jsonArray;
                        for(int i = 0 ; i < jsonArray.length() ; i++) {
                            JSONObject jsonObject1 = jsonArray.getJSONObject(i);

                            ListProducts.add(
                                    new tb_Details_Product(
                                            jsonObject1.getString("Id_product"),
                                            jsonObject1.getString("product_name"),
                                            jsonObject1.getDouble("amount")
                                    )
                            );
                        }
                        // System.out.println(datos);
                        recyclerView.setLayoutManager(new LinearLayoutManager(context));
                        recyclerViewAdapterProducts = new RecyclerViewAdapterProducts(context, ListProducts);
                        recyclerView.setAdapter(recyclerViewAdapterProducts);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }, error -> error.printStackTrace());
        VolleySingleton.getInstanceVolley(context).addToRequestQueue(stringRequest);
    }

}
