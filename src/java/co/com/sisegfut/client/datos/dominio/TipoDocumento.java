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
@Table(name="tipo_documento")
public class TipoDocumento extends EntidadPerpetua implements BeanModelTag{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "gen_tipo_documento")
    @SequenceGenerator(name = "gen_tipo_documento", sequenceName = "tipo_documento_seq")
    private Long Id;
    
    @Column(name = "nombre_tipoDocumento", nullable = false, length = 30)
    private String nombreTipoDocumento;

    @Override
    public Long getId() {
        return Id;
    }

    @Override
    public void setId(Long Id) {
        this.Id = Id;
    }

    public String getNombreTipoDocumento() {
        return nombreTipoDocumento;
    }

    public TipoDocumento(Long Id) {
        this.Id = Id;
    }

    public TipoDocumento() {
    }

    public void setNombreTipoDocumento(String nombreTipoDocumento) {
        this.nombreTipoDocumento = nombreTipoDocumento;
    }



  
    @Override
    public String getLabel() {
  return nombreTipoDocumento;
    }
    
}
