/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.client.datos.dominio.dto;

import java.io.Serializable;

/**
 *
 * @author ManuelAlejandro
 */
public class DTOPosicionesCantidad implements Serializable{
    
    private String posicion;
    private Integer cantidad;

    public DTOPosicionesCantidad() {
    }

    public DTOPosicionesCantidad(String posicion, Integer cantidad) {
        this.posicion = posicion;
        this.cantidad = cantidad;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
    
    
    
}
