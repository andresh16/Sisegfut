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
@Table(name = "eps")
public class Eps extends EntidadPerpetua implements BeanModelTag, Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "gen_eps")
    @SequenceGenerator(name = "gen_eps", sequenceName = "eps_id_seq")
    private Long Id;
    
    @Column(name = "nombre_eps", nullable = false, length = 80)
    private String nombreEps;

    public Eps() {
    }

    public Eps(Long Id, String nombreEps) {
        this.Id = Id;
        this.nombreEps = nombreEps;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    public Eps(Long Id) {
        this.Id = Id;
    }

    public String getNombreEps() {
        return nombreEps;
    }

    public void setNombreEps(String nombreEps) {
        this.nombreEps = nombreEps;
    }

    @Override
    public String getLabel() {
    return nombreEps;
    }
    
    
    
}
