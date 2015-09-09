/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.client.aatest.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fhurtado
 */
public class Estratos implements Serializable{
    
    private String id;// = "" + lastId++ ;
    private String estrato;
    private int cantidad;

    public Estratos(String id, String estrato, int cantidad) {
        this.id = id;
        this.estrato = estrato;
        this.cantidad = cantidad;
    }


    public Estratos() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEstrato() {
        return estrato;
    }

    public void setEstrato(String estrato) {
        this.estrato = estrato;
    }


    
    
//    public static List<Estratos> getEstratos(){
//    
//        List<Estratos> estratos = new ArrayList<Estratos>();
//        for (int i = 0; i < 6; i++) {
//            estratos.add(new Estratos((i+1)+"","Estrato "+(i+1), Math.floor(Math.max(Math.random() * 10, 1))));
//        }
//        
//        return estratos;
//    }
//    
//      public static List<Estratos> getData(int size, double min, double scale) {
//    List<Estratos> data = new ArrayList<Estratos>();
//    for (int i = 0; i < size; i++) {
//      data.add(new Estratos((i+1)+"","Estrato "+(i+1), Math.floor(Math.max(Math.random() * scale, min))));
//    }
//    return data;
//  }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    
    
}
