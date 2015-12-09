/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.client.datos.dominio;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Clase abstracta que trabaja como superclase para crear las entidades JPA y tiene un campo de fecha inactivado para manejar borrado logico
 * @author anfeh_000
 */
@MappedSuperclass
public abstract class EntidadPerpetua extends EntidadBase{
    
    public static final int TIPO_UNDELETED=1;
    public static final int TIPO_DELETED=2;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fechainactivado", length = 29)
    protected Date fechaInactivado;
    
    public Date getFechaInactivado() {
        return fechaInactivado;
    }

    public void setFechaInactivado(Date fechaInactivado) {
        this.fechaInactivado = fechaInactivado;
    }
}
