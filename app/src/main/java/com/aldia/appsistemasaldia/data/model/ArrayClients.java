package com.aldia.appsistemasaldia.data.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/* Array que sera enviado y leido por por main actyivity MainDriver para ser listado */

public class ArrayClients {

    private static ArrayList<Model_Clients> model_clients = new ArrayList<>();

    public ArrayClients(){}

    public ArrayList<Model_Clients> getModel_Clients() {
        return model_clients;
    }

    public void setModel_ModelClients(ArrayList<Model_Clients> model_ModelClients) {
        this.model_clients = model_ModelClients;
    }


    public void ArraySetClients(String id_Client, String name_Client, String phone1_client, String phone2_client, String address_Client, String email_Client){
        Model_Clients md = new Model_Clients(id_Client, name_Client, phone1_client, phone2_client, address_Client, email_Client);
        model_clients.add(md);

    }
}
