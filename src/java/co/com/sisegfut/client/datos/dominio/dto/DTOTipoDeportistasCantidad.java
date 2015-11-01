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
public class DTOTipoDeportistasCantidad implements Serializable{
    
    private String tipoDeportista;
    private Integer cantidad;

    public DTOTipoDeportistasCantidad() {
    }

    public DTOTipoDeportistasCantidad(String tipoDeportista, Integer cantidad) {
        this.tipoDeportista = tipoDeportista;
        this.cantidad = cantidad;
    }

    public String getTipoDeportista() {
        return tipoDeportista;
    }

    public void setTipoDeportista(String tipoDeportista) {
        this.tipoDeportista = tipoDeportista;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }
}
