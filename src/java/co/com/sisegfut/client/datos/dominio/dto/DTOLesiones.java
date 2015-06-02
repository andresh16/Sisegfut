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
public class DTOLesiones implements BeanModelTag, Serializable{
   
    private Date fechaLesion;
    private String nombreLesion;
    private Long idLesion;
    private Long IdDeportista;

    public DTOLesiones() {
    }

    public String getNombreLesion() {
        return nombreLesion;
    }

    public void setNombreLesion(String nombreLesion) {
        this.nombreLesion = nombreLesion;
    }

    public Date getFechaLesion() {
        return fechaLesion;
    }

    public void setFechaLesion(Date fechaLesion) {
        this.fechaLesion = fechaLesion;
    }

   

    public Long getIdLesion() {
        return idLesion;
    }

    public void setIdLesion(Long idLesion) {
        this.idLesion = idLesion;
    }

    public Long getIdDeportista() {
        return IdDeportista;
    }

    public void setIdDeportista(Long IdDeportista) {
        this.IdDeportista = IdDeportista;
    }
    
    
}
