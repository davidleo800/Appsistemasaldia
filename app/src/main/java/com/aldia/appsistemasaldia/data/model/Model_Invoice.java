package com.aldia.appsistemasaldia.data.model;

import java.util.ArrayList;

public class Model_Invoice {

    private String id_Inv;
    private String id_Client;
    private String date_Current;
    private String products;
    private String valor;
    private String Observaciones;
    private String id_user;

    public Model_Invoice(String id_Inv, String id_Client, String date_Current, String products, String valor, String observaciones, String id_user) {
        this.id_Inv = id_Inv;
        this.id_Client = id_Client;
        this.date_Current = date_Current;
        this.products = products;
        this.valor = valor;
        Observaciones = observaciones;
        this.id_user = id_user;
    }

    public String getId_Inv() {
        return id_Inv;
    }

    public void setId_Inv(String id_Inv) {
        this.id_Inv = id_Inv;
    }

    public String getId_Client() {
        return id_Client;
    }

    public void setId_Client(String id_Client) {
        this.id_Client = id_Client;
    }

    public String getDate_Current() {
        return date_Current;
    }

    public void setDate_Current(String date_Current) {
        this.date_Current = date_Current;
    }

    public String getProducts() {
        return products;
    }

    public void setProducts(String products) {
        this.products = products;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getObservaciones() {
        return Observaciones;
    }

    public void setObservaciones(String observaciones) {
        Observaciones = observaciones;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }
}
