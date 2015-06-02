/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.client.datos.dominio;

import java.io.Serializable;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

/**
 * Clase abstracta que trabaja como superclase para crear las entidades JPA
 * @author josorio
 */
@MappedSuperclass
public abstract class EntidadBase implements Serializable {
    
    public abstract Long getId();
    
    public abstract void setId(Long id);
    
    @Transient
    public abstract String getLabel();
}
