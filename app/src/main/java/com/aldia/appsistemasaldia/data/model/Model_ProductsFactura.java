package com.aldia.appsistemasaldia.data.model;


public class Model_ProductsFactura {
    private String Id_product;
    private String product_name;
    private Double weight;
    private Double amount;
    private int cant;

    public Model_ProductsFactura(String Id_product, String product_name, Double weight, Double amount, int cant) {
        this.Id_product = Id_product;
        this.product_name = product_name;
        this.amount = amount;
        this.cant = cant;
        this.weight = weight;
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

    public Double getWeight() {
        return weight;
    }

    public void setweight(Double weight) {
        this.weight = weight;
    }

    public int getCant() {
        return cant;
    }

    public void setCant(int cant) {
        this.cant = cant;
    }
}
