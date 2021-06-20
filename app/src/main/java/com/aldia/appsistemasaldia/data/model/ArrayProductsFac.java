package com.aldia.appsistemasaldia.data.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
/* Array que sera enviado y leido por por main actyivity MainDriver para ser listado */
public class ArrayProductsFac {

    private static ArrayList<Model_ProductsFactura> model_ProductsFactura = new ArrayList<>();

    public ArrayProductsFac(){}

    public ArrayList<Model_ProductsFactura> getModel_ProductsFactura() {
        /*for (int i = 0; i < model_ProductsFactura.size(); i++) {
            System.out.println("Prod: "+model_ProductsFactura.get(i).getProduct_name()+
                    " Valor: "+model_ProductsFactura.get(i).getAmount()+
                    " Cantidad: "+model_ProductsFactura.get(i).getCant());
        }*/
        return model_ProductsFactura;
    }

    public String[] getProductFac(){

        String[] Datos = new String[model_ProductsFactura.size()];
        for (int i = 0; i < model_ProductsFactura.size(); i++) {
            Datos[i] = "Id producto: "+model_ProductsFactura.get(i).getId_product()+
                    " Producto: "+ model_ProductsFactura.get(i).getProduct_name()+
                    " Cantidad: "+model_ProductsFactura.get(i).getCant()+
                    " Peso: "+model_ProductsFactura.get(i).getWeight()+
                    " Valor: "+model_ProductsFactura.get(i).getAmount();
        }

        return Datos;
    }

    public void Array(String Id_product, String Product_name, Double Product_weight, Double valor, int cont){
        Model_ProductsFactura md = new Model_ProductsFactura(Id_product,
                Product_name,
                Product_weight,
                valor,
                cont);

        if( model_ProductsFactura.size() == 0){
            model_ProductsFactura.add(md);
        }
        for (int i = 0; i < model_ProductsFactura.size(); i++) {
            if(model_ProductsFactura.get(i).getId_product().equals(Id_product)){
                model_ProductsFactura.set(i, md);

            }else {
                model_ProductsFactura.add(md);
            }
            model_ProductsFactura.removeIf(b -> b.getCant() == 0);

        }
        Set<Model_ProductsFactura> hashSet = new HashSet<>(model_ProductsFactura);
        model_ProductsFactura.clear();
        model_ProductsFactura.addAll(hashSet);

        // getModel_ProductsFactura();
    }

}
