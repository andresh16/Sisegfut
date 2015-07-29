/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.client.datos.dominio.dto;

import com.extjs.gxt.ui.client.data.BeanModelTag;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author ManuelAlejandro
 */
public class DTOTestKarvonenxCategoria implements BeanModelTag, Serializable{
    
    private String fecha;
    private String porcentaje;
    private String fcReposo;
    private String resKarvonen;
    private Long idKarvonen;
    private String identificacion;
    private String nombreCompleto;

    public DTOTestKarvonenxCategoria() {
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(String porcentaje) {
        this.porcentaje = porcentaje;
    }

    public String getFcReposo() {
        return fcReposo;
    }

    public void setFcReposo(String fcReposo) {
        this.fcReposo = fcReposo;
    }

    public String getResKarvonen() {
        return resKarvonen;
    }

    public void setResKarvonen(String resKarvonen) {
        this.resKarvonen = resKarvonen;
    }

    public Long getIdKarvonen() {
        return idKarvonen;
    }

    public void setIdKarvonen(Long idKarvonen) {
        this.idKarvonen = idKarvonen;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }
    
    
    
}
