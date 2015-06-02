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
 * @author Andres Hurtado
 */
public class DTOTestKarvonen implements BeanModelTag, Serializable {

    private Date fecha;
    private String porcentaje;
    private String fcReposo;
    private String resKarvonen;
    private Long idKarvonen;

    public DTOTestKarvonen() {
    }

    public Date getFecha() {
        return fecha;
    }
    

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Long getIdKarvonen() {
        return idKarvonen;
    }

    public void setIdKarvonen(Long idKarvonen) {
        this.idKarvonen = idKarvonen;
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
    
    
    
    

}
