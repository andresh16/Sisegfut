/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.client.datos.dominio.dto;

import java.io.Serializable;

/**
 *
 * @author fhurtado
 */
public class DTODeportistaPosicion implements Serializable{
    
    private Integer cantidad;
    private String  posicion;

    public DTODeportistaPosicion() {
    }

    public DTODeportistaPosicion(Integer cantidad, String posicion) {
        this.cantidad = cantidad;
        this.posicion = posicion;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }
    
    
    
}
