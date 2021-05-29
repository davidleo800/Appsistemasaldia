package com.aldia.appsistemasaldia.data.model;

public class Model_Clients {
    private String id_Client, name_Client, phone1_client, phone2_client, address_Client, email_Client;

    public Model_Clients(String id_Client, String name_Client, String phone1_client, String phone2_client, String address_Client, String email_Client) {
        this.id_Client = id_Client;
        this.name_Client = name_Client;
        this.phone1_client = phone1_client;
        this.phone2_client = phone2_client;
        this.address_Client = address_Client;
        this.email_Client = email_Client;
    }

    public String getId_Client() {
        return id_Client;
    }

    public void setId_Client(String id_Client) {
        this.id_Client = id_Client;
    }

    public String getName_Client() {
        return name_Client;
    }

    public void setName_Client(String name_Client) {
        this.name_Client = name_Client;
    }

    public String getPhone1_client() {
        return phone1_client;
    }

    public void setPhone1_client(String phone1_client) {
        this.phone1_client = phone1_client;
    }

    public String getPhone2_client() {
        return phone2_client;
    }

    public void setPhone2_client(String phone2_client) {
        this.phone2_client = phone2_client;
    }

    public String getAddress_Client() {
        return address_Client;
    }

    public void setAddress_Client(String address_Client) {
        this.address_Client = address_Client;
    }

    public String getEmail_Client() {
        return email_Client;
    }

    public void setEmail_Client(String email_Client) {
        this.email_Client = email_Client;
    }
}
