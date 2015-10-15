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
 * @author ManuelAlejandro
 */
@Entity
@Table(name = "tipo_deportista")
public class TipoDeportista extends EntidadPerpetua implements BeanModelTag, Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "gen_tipo_deportista")
    @SequenceGenerator(name = "gen_tipo_deportista", sequenceName = "tipo_deportista_id_seq")
    private Long Id;
    
    @Column(name = "tipo_deportista", nullable = false, length = 80)
    private String nombreTipoDeportista;

    public TipoDeportista() {
        
    }

    public TipoDeportista(Long Id) {
        this.Id = Id;
    }

    public TipoDeportista(Long Id, String nombreTipoDeportista) {
        this.Id = Id;
        this.nombreTipoDeportista = nombreTipoDeportista;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    public String getNombreTipoDeportista() {
        return nombreTipoDeportista;
    }

    public void setNombreTipoDeportista(String nombreTipoDeportista) {
        this.nombreTipoDeportista = nombreTipoDeportista;
    }
    
    @Override
    public String getLabel() {
    return nombreTipoDeportista;
    }
}
