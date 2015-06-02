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
@Table(name = "inst_educativa")
public class InstEducativa extends EntidadPerpetua implements BeanModelTag, Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "gen_inst_educativa")
    @SequenceGenerator(name = "gen_inst_educativa", sequenceName = "inst_educativa_seq")
    private Long Id;
    
    @Column(name = "nombre_inst_educativa", nullable = false, length = 80)
    private String nombreInstEducativa;

    public InstEducativa() {
    }

    public InstEducativa(Long Id) {
        this.Id = Id;
    }

    public InstEducativa(Long Id, String nombreInstEducativa) {
        this.Id = Id;
        this.nombreInstEducativa = nombreInstEducativa;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    public String getNombreInstEducativa() {
        return nombreInstEducativa;
    }

    public void setNombreInstEducativa(String nombreInstEducativa) {
        this.nombreInstEducativa = nombreInstEducativa;
    }

    @Override
    public String getLabel() {
        return nombreInstEducativa;
    }
    
    
    
}
