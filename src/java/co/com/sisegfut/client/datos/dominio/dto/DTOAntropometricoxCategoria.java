/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.client.datos.dominio.dto;

import java.util.Date;

/**
 *
 * @author ManuelAlejandro
 */
public class DTOAntropometricoxCategoria {

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
    private Long idAntropometrico;
    private String identificacion;
    
    public DTOAntropometricoxCategoria() {
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

    public Long getIdAntropometrico() {
        return idAntropometrico;
    }

    public void setIdAntropometrico(Long idAntropometrico) {
        this.idAntropometrico = idAntropometrico;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }
    
    
    
}
