package com.aldia.appsistemasaldia.ui.main;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aldia.appsistemasaldia.R;
import com.aldia.appsistemasaldia.data.model.ArrayProductsFac;
import com.aldia.appsistemasaldia.data.model.tb_Details_Product;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class RecyclerViewAdapterProducts extends RecyclerView.Adapter<RecyclerViewAdapterProducts.productViewHolder> {
    Context context;
    List<tb_Details_Product> listProduct;
    ArrayProductsFac arrayProductsFac = new ArrayProductsFac();

    public RecyclerViewAdapterProducts (Context context, List<tb_Details_Product> listProduct) {
        this.context = context;
        this.listProduct = listProduct;
    }

    @NonNull
    @Override
    public productViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.items_recyclerview, null, false);

        return new productViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull productViewHolder holder, int position) {

        holder.tvIdProduct.setText(listProduct.get(position).getId_product());
        holder.tvProductName.setText(listProduct.get(position).getProduct_name());
        holder.tvPrice.setText(String.valueOf(listProduct.get(position).getAmount()));

        // AtomicInteger cont = new AtomicInteger();
        AtomicInteger cont = new AtomicInteger(Integer.parseInt(holder.tvcant.getText().toString()));

        // listener que Multiplica peso por valor
        holder.tietkg.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s == null || s.toString().isEmpty()) {
                    holder.tvTotal.setText(String.format("%s", 0));
                }else{
                    double result = Double.parseDouble(s.toString()) * listProduct.get(position).getAmount();
                    holder.tvTotal.setText(String.format("%s", result));
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
                if(s == null || s.toString().isEmpty()) {
                    holder.tvTotal.setText(String.format("%s", 0));
                }else{
                    double valKg;
                    if (Objects.requireNonNull(holder.tietkg.getText()).toString().equals("")){
                        valKg = 0;
                    } else {
                        valKg = Double.parseDouble(Objects.requireNonNull(holder.tietkg.getText()).toString());

                    }
                    arrayProductsFac.Array(listProduct.get(position).getId_product(),
                            listProduct.get(position).getProduct_name(),
                            valKg,
                            Double.parseDouble(holder.tvTotal.getText().toString()),
                            cont.intValue());
                }


            }

        });

        // Boton Suma valor en text view cant
        holder.btnMore.setOnClickListener(v ->{
            cont.set(cont.get() + 1);
            holder.tvcant.setText(String.valueOf(cont));
            // holder.tvTotal.setText(String.valueOf(listProduct.get(position).getAmount() * cont.doubleValue()));
            // Insertar en array list los productos
            double valKg;
            if (Objects.requireNonNull(holder.tietkg.getText()).toString().equals("")){
                valKg = 0;
            } else {
                valKg = Double.parseDouble(Objects.requireNonNull(holder.tietkg.getText()).toString());

            }
            arrayProductsFac.Array(listProduct.get(position).getId_product(),
                    listProduct.get(position).getProduct_name(),
                    valKg,
                    Double.parseDouble(holder.tvTotal.getText().toString()),
                    cont.intValue());
            System.out.println(cont);
        });

        // Boton Resta valor en text view cant
        holder.btnLess.setOnClickListener(v ->{
            if (cont.get() > 0) {
                cont.set(cont.get() - 1);
                holder.tvcant.setText(String.valueOf(cont));
                // holder.tvTotal.setText(String.valueOf(listProduct.get(position).getAmount() * cont.doubleValue()));
                // Insertar en array list los productos
                double valKg;
                if (Objects.requireNonNull(holder.tietkg.getText()).toString().equals("")){
                    valKg = 0;
                } else {
                    valKg = Double.parseDouble(Objects.requireNonNull(holder.tietkg.getText()).toString());

                }
                arrayProductsFac.Array(listProduct.get(position).getId_product(),
                        listProduct.get(position).getProduct_name(),
                        valKg,
                        Double.parseDouble(holder.tvTotal.getText().toString()),
                        cont.intValue());
            }
            System.out.println(cont);
        });

        // listener de cambia de valor en valor total
        holder.tvTotal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) { }
            @Override
            public void afterTextChanged(Editable s) {
                double valKg;
                double valTotal;
                if (Objects.requireNonNull(holder.tietkg.getText()).toString().equals("")){
                    valKg = 0;
                } else {
                    valKg = Double.parseDouble(Objects.requireNonNull(holder.tietkg.getText()).toString());
                }
                if (Objects.requireNonNull(holder.tvTotal.getText()).toString().equals("")){
                    valTotal = 0;
                } else {
                    valTotal = Double.parseDouble(Objects.requireNonNull(holder.tvTotal.getText()).toString());

                }
                    arrayProductsFac.Array(listProduct.get(position).getId_product(),
                            listProduct.get(position).getProduct_name(),
                            valKg,
                            valTotal,
                            cont.intValue());

            }
        });

        // listener de cambia de valor en cantidad
        holder.tvcant.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}
            @Override
            public void afterTextChanged(Editable s) {
                double valKg;
                double valTotal;
                int cantFinal;
                if (Objects.requireNonNull(holder.tietkg.getText()).toString().equals("")){
                    valKg = 0;
                } else {
                    valKg = Double.parseDouble(Objects.requireNonNull(holder.tietkg.getText()).toString());
                }
                if (Objects.requireNonNull(holder.tvTotal.getText()).toString().equals("")){
                    valTotal = 0;
                } else {
                    valTotal = Double.parseDouble(Objects.requireNonNull(holder.tvTotal.getText()).toString());

                }
                if (Objects.requireNonNull(holder.tvcant.getText()).toString().equals("")){
                    cantFinal = 0;
                } else {
                    cantFinal = Integer.parseInt(Objects.requireNonNull(holder.tvcant.getText()).toString());

                }
                cont.set(cantFinal);
                arrayProductsFac.Array(listProduct.get(position).getId_product(),
                        listProduct.get(position).getProduct_name(),
                        valKg,
                        valTotal,
                        cont.intValue());

            }
        });

    }

    @Override
    public int getItemCount() {
        return listProduct.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }


    public static class productViewHolder extends RecyclerView.ViewHolder {

        TextView tvIdProduct, tvProductName, tvPrice;
        EditText tvTotal, tvcant;
        TextInputLayout tikg;
        TextInputEditText tietkg;
        Button btnLess, btnMore;
        public productViewHolder(@NonNull View itemView) {
            super(itemView);
            tvIdProduct = itemView.findViewById(R.id.tvIdProduct);
            tvProductName = itemView.findViewById(R.id.tvProductName);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvcant = itemView.findViewById(R.id.tvcant);
            tvTotal = itemView.findViewById(R.id.tvTotal);
            btnLess = itemView.findViewById(R.id.btn_less);
            btnMore = itemView.findViewById(R.id.btn_more);
            tikg = itemView.findViewById(R.id.tikg);
            tietkg = itemView.findViewById(R.id.tietkg);
        }
    }

}
