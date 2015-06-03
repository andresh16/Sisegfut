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
public class DTOCompetencia implements BeanModelTag, Serializable {

    private Date fecha;
    private String compromiso;
    private String lugar;
    private String torneo;
    private String finalizo;
    private Long idCompetencia;
    private Long idJugadorComodin;

    private String observaciones;
    private Long idRival;
    private Long idtorneo;

    public DTOCompetencia() {
    }

    public Date getFecha() {
        return fecha;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public Long getIdtorneo() {
        return idtorneo;
    }

    public void setIdtorneo(Long idtorneo) {
        this.idtorneo = idtorneo;
    }
    

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Long getIdRival() {
        return idRival;
    }

    public void setIdRival(Long idRival) {
        this.idRival = idRival;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getCompromiso() {
        return compromiso;
    }

    public void setCompromiso(String compromiso) {
        this.compromiso = compromiso;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public Long getIdCompetencia() {
        return idCompetencia;
    }

    public void setIdCompetencia(Long idCompetencia) {
        this.idCompetencia = idCompetencia;
    }

    public String getFinalizo() {
        return finalizo;
    }

    public void setFinalizo(String finalizo) {
        this.finalizo = finalizo;
    }

    public Long getIdJugadorComodin() {
        return idJugadorComodin;
    }

    public void setIdJugadorComodin(Long idJugadorComodin) {
        this.idJugadorComodin = idJugadorComodin;
    }

    public String getTorneo() {
        return torneo;
    }

    public void setTorneo(String torneo) {
        this.torneo = torneo;
    }

}
