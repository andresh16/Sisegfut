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
public class DTOEstratosCantidad implements Serializable{
    
    private String estrato;
    private Integer cantidad;

    public DTOEstratosCantidad(String estrato, Integer cantidad) {
        this.estrato = estrato;
        this.cantidad = cantidad;
    }

    public DTOEstratosCantidad() {
    }

    public String getEstrato() {
        return estrato;
    }

    public void setEstrato(String estrato) {
        this.estrato = estrato;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
    
    
    
}
