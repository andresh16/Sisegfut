/*
 * To change this template, choose Tools | Templates
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
 * @author fhurtado
 */
   @Entity
   @Table(name="tipo_movimiento")
public class TipoMovimiento implements BeanModelTag,Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "gen_tipomov")
    @SequenceGenerator(name = "gen_tipomov", sequenceName = "tipo_movimiento_seq")
    private Long Id;
    
    @Column(name = "nombTipoMov", nullable = false, length = 30)
    private String nombTipoMov;

    public TipoMovimiento(Long Id, String nombTipoMov) {
        this.Id = Id;
        this.nombTipoMov = nombTipoMov;
    }

    public TipoMovimiento() {
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    
    public String getNombTipoMov() {
        return nombTipoMov;
    }

    public void setNombTipoMov(String nombTipoMov) {
        this.nombTipoMov = nombTipoMov;
    }

    
  
    }