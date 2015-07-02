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
 * @author fhurtado
 */
public class DTOControlAsistencia implements BeanModelTag, Serializable{
    
    private Long IdCategoria;
    private Long IdPlanillaAsistencia;
    private Date fecha;
    private String lugar;
    private String actividad;
    private String categoria;
    private String observaciones;

    public DTOControlAsistencia() {
    }

    public Long getIdPlanillaAsistencia() {
        return IdPlanillaAsistencia;
    }

    public void setIdPlanillaAsistencia(Long IdPlanillaAsistencia) {
        this.IdPlanillaAsistencia = IdPlanillaAsistencia;
    }
    
    public Long getIdCategoria() {
        return IdCategoria;
    }

    public void setIdCategoria(Long IdCategoria) {
        this.IdCategoria = IdCategoria;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    
    
}
