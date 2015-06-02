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
 * @author Andres Hurtado
 */
public class DTOAntecedentesDeportivos implements BeanModelTag, Serializable{
    
    private Long idAntecedente;
    private Long idCategoria;
    private String clubAnterior;
    private String anno;
    private String categoriaAnterior;

    public DTOAntecedentesDeportivos() {
    }

    public String getClubAnterior() {
        return clubAnterior;
    }

    public void setClubAnterior(String clubAnterior) {
        this.clubAnterior = clubAnterior;
    }

    public String getAnno() {
        return anno;
    }

    public void setAnno(String anno) {
        this.anno = anno;
    }

    public String getCategoriaAnterior() {
        return categoriaAnterior;
    }

    public void setCategoriaAnterior(String categoriaAnterior) {
        this.categoriaAnterior = categoriaAnterior;
    }

    public Long getIdAntecedente() {
        return idAntecedente;
    }

    public void setIdAntecedente(Long idAntecedente) {
        this.idAntecedente = idAntecedente;
    }

    public Long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }
    
}