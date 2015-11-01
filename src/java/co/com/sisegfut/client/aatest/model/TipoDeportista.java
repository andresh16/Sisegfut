/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.client.aatest.model;

import java.io.Serializable;

/**
 *
 * @author ManuelAlejandro
 */
public class TipoDeportista implements Serializable{
    
    private String id;// = "" + lastId++ ;
    private String tipoDeportista;
//    private double cantidad;
    private int cantidad;

    public TipoDeportista() {
    }

    public TipoDeportista(String id, String tipoDeportista, int cantidad) {
        this.id = id;
        this.tipoDeportista = tipoDeportista;
        this.cantidad = cantidad;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTipoDeportista() {
        return tipoDeportista;
    }

    public void setTipoDeportista(String tipoDeportista) {
        this.tipoDeportista = tipoDeportista;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    
    
}
