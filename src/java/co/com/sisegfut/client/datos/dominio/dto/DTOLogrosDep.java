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
 * @author fhurtado
 */
public class DTOLogrosDep implements BeanModelTag, Serializable{
    
    private Long idLogro;
    private Long idCategoria;
    private String logroDeportivo;
    private String anioLogro;
    private String categoriaLogro;

    public Long getIdLogro() {
        return idLogro;
    }

    public void setIdLogro(Long idLogro) {
        this.idLogro = idLogro;
    }

    public Long getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(Long idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getLogroDeportivo() {
        return logroDeportivo;
    }

    public void setLogroDeportivo(String logroDeportivo) {
        this.logroDeportivo = logroDeportivo;
    }

    public String getAnioLogro() {
        return anioLogro;
    }

    public void setAnioLogro(String anioLogro) {
        this.anioLogro = anioLogro;
    }

    public String getCategoriaLogro() {
        return categoriaLogro;
    }

    public void setCategoriaLogro(String categoriaLogro) {
        this.categoriaLogro = categoriaLogro;
    }
    
    
}
