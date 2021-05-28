package com.aldia.appsistemasaldia.ui.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aldia.appsistemasaldia.R;
import com.aldia.appsistemasaldia.data.model.Model_Invoice;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class RecyclerViewAdapterAdmin extends RecyclerView.Adapter<RecyclerViewAdapterAdmin.ViewHolderData> {

    Context context;
    ArrayList<Model_Invoice> listData;

    public RecyclerViewAdapterAdmin(Context context, ArrayList<Model_Invoice> listData) {
        this.context = context;
        this.listData = listData;
    }

    @NonNull
    @Override
    public ViewHolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_rv_admin, null, false);
        return new ViewHolderData(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderData holder, int position) {
        holder.tv_IdInv.setText(listData.get(position).getId_Inv());

        holder.tv_Obs.setText(listData.get(position).getObservaciones());
        holder.tv_total.setText(listData.get(position).getValor());
        try {

            JSONArray json = new JSONArray (listData.get(position).getProducts());
            JSONObject jsonobject = null;
            StringBuilder prod = new StringBuilder();
            //String prod = "";
            for (int i = 0; i < json.length(); i++) {
                jsonobject= json.getJSONObject(i);
                prod.append("Id: ").append(jsonobject.getString("Id"))
                        .append(" Prod: ").append(jsonobject.getString("Name"))
                        .append(" Cant: ").append(jsonobject.getString("Cant"))
                        .append(" Valor: ").append(jsonobject.getString("Amount")).append("\n");

            }
            holder.tv_products.setText(prod);





        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return listData.size();
    }

    public class ViewHolderData extends RecyclerView.ViewHolder {

        TextView tv_IdInv, tv_products, tv_Obs, tv_total;

        public ViewHolderData(@NonNull View itemView) {
            super(itemView);
            tv_IdInv = itemView.findViewById(R.id.tv_IdInv);
            tv_products = itemView.findViewById(R.id.tv_products);
            tv_Obs = itemView.findViewById(R.id.tv_Obs);
            tv_total = itemView.findViewById(R.id.tv_total);
        }
    }
}
