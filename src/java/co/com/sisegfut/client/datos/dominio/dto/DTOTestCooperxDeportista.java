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
public class DTOTestCooperxDeportista implements BeanModelTag, Serializable{
    
    private Date fecha;
    private String distancia;
    private String consumOxigeno;
    private String condicionFisica;
    private String velocidad;
    private String vo2max;
    private String nombres;
    private String apellidos;
    private String documento;

    public DTOTestCooperxDeportista() {
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDistancia() {
        return distancia;
    }

    public void setDistancia(String distancia) {
        this.distancia = distancia;
    }

    public String getConsumOxigeno() {
        return consumOxigeno;
    }

    public void setConsumOxigeno(String consumOxigeno) {
        this.consumOxigeno = consumOxigeno;
    }

    public String getCondicionFisica() {
        return condicionFisica;
    }

    public void setCondicionFisica(String condicionFisica) {
        this.condicionFisica = condicionFisica;
    }

    public String getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(String velocidad) {
        this.velocidad = velocidad;
    }

    public String getVo2max() {
        return vo2max;
    }

    public void setVo2max(String vo2max) {
        this.vo2max = vo2max;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }
    
    
}
