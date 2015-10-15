/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.client.datos.dominio.dto;

import java.io.Serializable;

/**
 *
 * @author Andre
 */
public class DTOGolesDepTorneo implements Serializable{
    
    private String cantidadGoles;
    private String nombreCompletoJu;

    public DTOGolesDepTorneo() {
    }

    public DTOGolesDepTorneo(String cantidadGoles, String nombreCompletoJu) {
        this.cantidadGoles = cantidadGoles;
        this.nombreCompletoJu = nombreCompletoJu;
    }

    public String getCantidadGoles() {
        return cantidadGoles;
    }

    public void setCantidadGoles(String cantidadGoles) {
        this.cantidadGoles = cantidadGoles;
    }

    public String getNombreCompletoJu() {
        return nombreCompletoJu;
    }

    public void setNombreCompletoJu(String nombreCompletoJu) {
        this.nombreCompletoJu = nombreCompletoJu;
    }
    
    
    
}
