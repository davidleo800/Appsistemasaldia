package com.aldia.appsistemasaldia.ui.main;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aldia.appsistemasaldia.R;
import com.aldia.appsistemasaldia.data.model.tb_Details_Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class RecyclerViewAdapterProducts extends RecyclerView.Adapter<RecyclerViewAdapterProducts.productViewHolder> {
    Context context;
    List<tb_Details_Product> listProduct;

    public RecyclerViewAdapterProducts (Context context, List<tb_Details_Product> listProduct) {
        this.context = context;
        this.listProduct = listProduct;
    }

    @NonNull
    @Override
    public productViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_recyclerview, null, false);
        return new RecyclerViewAdapterProducts.productViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull productViewHolder holder, int position) {
        holder.tvIdProduct.setText(listProduct.get(position).getId_product());
        holder.tvProductName.setText(listProduct.get(position).getProduct_name());
        holder.tvPrice.setText(String.valueOf(listProduct.get(position).getAmount()));
        AtomicInteger cont = new AtomicInteger();
        // Suma valor en text view cant
        holder.btnMore.setOnClickListener(v ->{
            cont.set(cont.get() + 1);
            System.out.println(listProduct.get(position).getProduct_name());
            System.out.println(cont);
            holder.tvcant.setText(String.valueOf(cont));
            holder.tvTotal.setText(String.valueOf(listProduct.get(position).getAmount() * cont.doubleValue()));
        });
        // Resta valor en text view cant
        holder.btnLess.setOnClickListener(v ->{
            if (cont.get() > 0) {
                cont.set(cont.get() - 1);
                System.out.println(listProduct.get(position).getProduct_name());
                System.out.println(cont);
                holder.tvcant.setText(String.valueOf(cont));
                holder.tvTotal.setText(String.valueOf(listProduct.get(position).getAmount() * cont.doubleValue()));
            }
        });
    }

    @Override
    public int getItemCount() {
        return listProduct.size();
    }

    public class productViewHolder extends RecyclerView.ViewHolder {

        TextView tvIdProduct, tvProductName, tvPrice, tvcant, tvTotal;
        Button btnAdd, btnLess, btnMore;

        public productViewHolder(@NonNull View itemView) {
            super(itemView);
            tvIdProduct = itemView.findViewById(R.id.tvIdProduct);
            tvProductName = itemView.findViewById(R.id.tvProductName);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvcant = itemView.findViewById(R.id.tvcant);
            tvTotal = itemView.findViewById(R.id.tvTotal);
            btnLess = itemView.findViewById(R.id.btn_less);
            btnMore = itemView.findViewById(R.id.btn_more);

            // btnAdd = itemView.findViewById(R.id.btnAddProduct);
        }
    }

}
