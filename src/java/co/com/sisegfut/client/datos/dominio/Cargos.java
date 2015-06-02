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
@Table(name = "cargos")
public class Cargos extends EntidadPerpetua implements BeanModelTag, Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "gen_cargos")
    @SequenceGenerator(name = "gen_cargos", sequenceName = "cargos_id_seq")
    private Long Id;
    
    @Column(name = "nombrecargo", nullable = false, length = 80)
    private String nombrecargo;

    public Cargos() {
    }

    public Cargos(Long Id, String nombrecargo) {
        this.Id = Id;
        this.nombrecargo = nombrecargo;
    }

    @Override
    public Long getId() {
        return Id;
    }

    @Override
    public void setId(Long Id) {
        this.Id = Id;
    }

    public String getNombrecargo() {
        return nombrecargo;
    }

    public void setNombrecargo(String nombrecargo) {
        this.nombrecargo = nombrecargo;
    }

    @Override
    public String getLabel() {
     return nombrecargo;
    }
    
    
}
