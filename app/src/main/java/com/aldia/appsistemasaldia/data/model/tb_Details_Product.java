package com.aldia.appsistemasaldia.data.model;

import java.math.BigDecimal;

public class tb_Details_Product {
    String Id_product;
    String product_name;
    Double amount;

    public tb_Details_Product(String id_product, String product_name, Double amount) {
        Id_product = id_product;
        this.product_name = product_name;
        this.amount = amount;
    }

    public String getId_product() {
        return Id_product;
    }

    public void setId_product(String id_product) {
        Id_product = id_product;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
