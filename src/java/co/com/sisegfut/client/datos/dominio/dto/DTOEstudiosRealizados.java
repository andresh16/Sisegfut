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
 * @author Camilo
 */
public class DTOEstudiosRealizados implements BeanModelTag, Serializable{
    
    private Long idEstudiosRealizados;
    private Long idNivelEducativo;
    private String institucion;
    private String nivelEducativo;
    private String titulo;
    private String anioGraduacion;

    public Long getIdEstudiosRealizados() {
        return idEstudiosRealizados;
    }

    public void setIdEstudiosRealizados(Long idEstudiosRealizados) {
        this.idEstudiosRealizados = idEstudiosRealizados;
    }

    public Long getIdnivelEducativo() {
        return idNivelEducativo;
    }

    public void setIdnivelEducativo(Long idNivelEducativo) {
        this.idNivelEducativo = idNivelEducativo;
    }

    public String getNivelEducativo() {
        return nivelEducativo;
    }

    public void setNivelEducativo(String nivelEducativo) {
        this.nivelEducativo = nivelEducativo;
    }

    public String getInstitucion() {
        return institucion;
    }

    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAnioGraduacion() {
        return anioGraduacion;
    }

    public void setAnioGraduacion(String anioGraduacion) {
        this.anioGraduacion = anioGraduacion;
    }
    
    
}
