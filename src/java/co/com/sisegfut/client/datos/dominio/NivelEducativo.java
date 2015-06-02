/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.sisegfut.client.datos.dominio;

import com.extjs.gxt.ui.client.data.BeanModelTag;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Andres Hurtado
 */
@Entity
@Table(name = "nivel_educativo")
public class NivelEducativo extends EntidadPerpetua implements BeanModelTag, Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "gen_nivel_educativo")
    @SequenceGenerator(name = "gen_nivel_educativo", sequenceName = "nivel_educativo_id_seq")
    private Long Id;
    
    @Column(name = "nombre_nivel_educativo", nullable = false, length = 80)
    private String nombreNivelEducativo;

    public NivelEducativo() {
    }

    public NivelEducativo(Long Id, String nombreNivelEducativo) {
        this.Id = Id;
        this.nombreNivelEducativo = nombreNivelEducativo;
    }

    public NivelEducativo(Long Id) {
        this.Id = Id;
    }
    
    

    @Override
    public Long getId() {
        return Id;
    }

    @Override
    public void setId(Long Id) {
        this.Id = Id;
    }

    public String getNombreNivelEducativo() {
        return nombreNivelEducativo;
    }

    public void setNombreNivelEducativo(String nombreNivelEducativo) {
        this.nombreNivelEducativo = nombreNivelEducativo;
    }

    @Override
    public String getLabel() {
    return nombreNivelEducativo;
    }
    
    
}
