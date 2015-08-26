/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.client.datos.dominio.dto;

import com.extjs.gxt.ui.client.data.BeanModelTag;
import java.io.Serializable;

/**
 *
 * @author ManuelAlejandro
 */
public class DTOAntropometricoxDeportista implements BeanModelTag, Serializable{
    
    private String fecha;
    private String perbrazorelajado;
    private String perabdominal;
    private String percadera;
    private String perpantorrilla;
    private String plisubescapular;
    private String plitricipital;
    private String plisupraescapular;
    private String pliabdominal;
    private String porcentajeGrasa;
    private String pesoGraso;
    private String pesoMacro;
    private String identificacion;
    private String nombreCompleto;

    public DTOAntropometricoxDeportista() {
    }

    public DTOAntropometricoxDeportista(String fecha, String perbrazorelajado, String perabdominal, String percadera, String perpantorrilla, String plisubescapular, String plitricipital, String plisupraescapular, String pliabdominal, String porcentajeGrasa, String pesoGraso, String pesoMacro) {
        this.fecha = fecha;
        this.perbrazorelajado = perbrazorelajado;
        this.perabdominal = perabdominal;
        this.percadera = percadera;
        this.perpantorrilla = perpantorrilla;
        this.plisubescapular = plisubescapular;
        this.plitricipital = plitricipital;
        this.plisupraescapular = plisupraescapular;
        this.pliabdominal = pliabdominal;
        this.porcentajeGrasa = porcentajeGrasa;
        this.pesoGraso = pesoGraso;
        this.pesoMacro = pesoMacro;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getPerbrazorelajado() {
        return perbrazorelajado;
    }

    public void setPerbrazorelajado(String perbrazorelajado) {
        this.perbrazorelajado = perbrazorelajado;
    }

    public String getPerabdominal() {
        return perabdominal;
    }

    public void setPerabdominal(String perabdominal) {
        this.perabdominal = perabdominal;
    }

    public String getPercadera() {
        return percadera;
    }

    public void setPercadera(String percadera) {
        this.percadera = percadera;
    }

    public String getPerpantorrilla() {
        return perpantorrilla;
    }

    public void setPerpantorrilla(String perpantorrilla) {
        this.perpantorrilla = perpantorrilla;
    }

    public String getPlisubescapular() {
        return plisubescapular;
    }

    public void setPlisubescapular(String plisubescapular) {
        this.plisubescapular = plisubescapular;
    }

    public String getPlitricipital() {
        return plitricipital;
    }

    public void setPlitricipital(String plitricipital) {
        this.plitricipital = plitricipital;
    }

    public String getPlisupraescapular() {
        return plisupraescapular;
    }

    public void setPlisupraescapular(String plisupraescapular) {
        this.plisupraescapular = plisupraescapular;
    }

    public String getPliabdominal() {
        return pliabdominal;
    }

    public void setPliabdominal(String pliabdominal) {
        this.pliabdominal = pliabdominal;
    }

    public String getPorcentajeGrasa() {
        return porcentajeGrasa;
    }

    public void setPorcentajeGrasa(String porcentajeGrasa) {
        this.porcentajeGrasa = porcentajeGrasa;
    }

    public String getPesoGraso() {
        return pesoGraso;
    }

    public void setPesoGraso(String pesoGraso) {
        this.pesoGraso = pesoGraso;
    }

    public String getPesoMacro() {
        return pesoMacro;
    }

    public void setPesoMacro(String pesoMacro) {
        this.pesoMacro = pesoMacro;
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
