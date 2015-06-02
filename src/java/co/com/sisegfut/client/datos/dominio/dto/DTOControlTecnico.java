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
public class DTOControlTecnico implements BeanModelTag, Serializable{
    
    private Date fecha;
    private String nombrerecepcion30seg;
    private String nombreprecisionpase15seg;
    private String nombreprecisiondisparoempeine;
    private String nombrepotenciaremate;
    private String nombrecontrolbalon50seg;
    private String nombreconduccion;
    private String nombrecabeceodefensivo;
    private String nombrecabeceoofensivo;
    private String nombreaceleracion;
    private Long IdCtrlTecnico;
    

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Long getIdCtrlTecnico() {
        return IdCtrlTecnico;
    }

    public void setIdCtrlTecnico(Long IdCtrlTecnico) {
        this.IdCtrlTecnico = IdCtrlTecnico;
    }
    
    

    public String getNombrerecepcion30seg() {
        return nombrerecepcion30seg;
    }

    public void setNombrerecepcion30seg(String nombrerecepcion30seg) {
        this.nombrerecepcion30seg = nombrerecepcion30seg;
    }

    public String getNombreprecisionpase15seg() {
        return nombreprecisionpase15seg;
    }

    public void setNombreprecisionpase15seg(String nombreprecisionpase15seg) {
        this.nombreprecisionpase15seg = nombreprecisionpase15seg;
    }

    public String getNombreprecisiondisparoempeine() {
        return nombreprecisiondisparoempeine;
    }

    public void setNombreprecisiondisparoempeine(String nombreprecisiondisparoempeine) {
        this.nombreprecisiondisparoempeine = nombreprecisiondisparoempeine;
    }

    public String getNombrepotenciaremate() {
        return nombrepotenciaremate;
    }

    public void setNombrepotenciaremate(String nombrepotenciaremate) {
        this.nombrepotenciaremate = nombrepotenciaremate;
    }

    public String getNombrecontrolbalon50seg() {
        return nombrecontrolbalon50seg;
    }

    public void setNombrecontrolbalon50seg(String nombrecontrolbalon50seg) {
        this.nombrecontrolbalon50seg = nombrecontrolbalon50seg;
    }

    public String getNombreconduccion() {
        return nombreconduccion;
    }

    public void setNombreconduccion(String nombreconduccion) {
        this.nombreconduccion = nombreconduccion;
    }

    public String getNombrecabeceodefensivo() {
        return nombrecabeceodefensivo;
    }

    public void setNombrecabeceodefensivo(String nombrecabeceodefensivo) {
        this.nombrecabeceodefensivo = nombrecabeceodefensivo;
    }

    public String getNombrecabeceoofensivo() {
        return nombrecabeceoofensivo;
    }

    public void setNombrecabeceoofensivo(String nombrecabeceoofensivo) {
        this.nombrecabeceoofensivo = nombrecabeceoofensivo;
    }

    public String getNombreaceleracion() {
        return nombreaceleracion;
    }

    public void setNombreaceleracion(String nombreaceleracion) {
        this.nombreaceleracion = nombreaceleracion;
    }
    
    
    
    
    
    
}
