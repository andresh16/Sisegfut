/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.client.datos.dominio.dto;

/**
 *
 * @author ManuelAlejandro
 */
public class DTOHVPersonal {
    
    private String titulo;
    private String institucionEducativa;
    private String escolaridad;
    private String annoGraduacion;
    private String empresa;
    private String cargo;
    private String tiempoLaborado;            
    
    public DTOHVPersonal() {
        
    }

    public String getTitulo() {
        return titulo;
    }

    public DTOHVPersonal(String titulo, String institucionEducativa, String escolaridad, String annoGraduacion, String empresa, String cargo, String tiempoLaborado) {
        this.titulo = titulo;
        this.institucionEducativa = institucionEducativa;
        this.escolaridad = escolaridad;
        this.annoGraduacion = annoGraduacion;
        this.empresa = empresa;
        this.cargo = cargo;
        this.tiempoLaborado = tiempoLaborado;
    }

    
    
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getInstitucionEducativa() {
        return institucionEducativa;
    }

    public void setInstitucionEducativa(String institucionEducativa) {
        this.institucionEducativa = institucionEducativa;
    }

    public String getEscolaridad() {
        return escolaridad;
    }

    public void setEscolaridad(String escolaridad) {
        this.escolaridad = escolaridad;
    }

    public String getAnnoGraduacion() {
        return annoGraduacion;
    }

    public void setAnnoGraduacion(String annoGraduacion) {
        this.annoGraduacion = annoGraduacion;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getTiempoLaborado() {
        return tiempoLaborado;
    }

    public void setTiempoLaborado(String tiempoLaborado) {
        this.tiempoLaborado = tiempoLaborado;
    }
    
    
    
 }
