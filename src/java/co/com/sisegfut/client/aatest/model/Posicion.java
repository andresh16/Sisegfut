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
    private int cantidad;



    public Posicion(String id, String posicion, int cantidad) {
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

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    
}
