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
 * @author ManuelAlejandro
 */
public class Posicion implements Serializable{
    
    private String id;// = "" + lastId++ ;
    private String posicion;
    private double cantidad;

    public Posicion(String id, String posicion, double cantidad) {
        this.id = id;
        this.posicion = posicion;
        this.cantidad = cantidad;
    }

    public Posicion() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }       

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }
    
    
    public static List<Posicion> getPosiciones(){
    
        List<Posicion> posiciones = new ArrayList<Posicion>();
        for (int i = 0; i < 4; i++) {
            posiciones.add(new Posicion((i+1)+"","Posición "+(i+1), Math.floor(Math.max(Math.random() * 10, 1))));
        }
        
        return posiciones;
    }
    
      public static List<Posicion> getData(int size, double min, double scale) {
    List<Posicion> data = new ArrayList<Posicion>();
    for (int i = 0; i < size; i++) {
      data.add(new Posicion((i+1)+"","Posición "+(i+1), Math.floor(Math.max(Math.random() * scale, min))));
    }
    return data;
  }
    
}
