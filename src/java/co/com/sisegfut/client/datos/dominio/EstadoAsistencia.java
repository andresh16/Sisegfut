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
 * @author anfeh_000
 */
@Entity
@Table(name = "estado_asistencia")
public class EstadoAsistencia extends EntidadPerpetua  implements BeanModelTag, Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "gen_estado_asistencia")
    @SequenceGenerator(name = "gen_estado_asistencia", sequenceName = "estado_asistencia_id_seq")
    private Long Id;
    
    @Column(name = "nombree_estado_asistencia", nullable = false, length = 80)
    private String estadoAsistencia;

    public EstadoAsistencia() {
    }

    public EstadoAsistencia(Long Id, String estadoAsistencia) {
        this.Id = Id;
        this.estadoAsistencia = estadoAsistencia;
    }

    @Override
    public Long getId() {
        return Id;
    }

    @Override
    public void setId(Long Id) {
        this.Id = Id;
    }

    public String getEstadoAsistencia() {
        return estadoAsistencia;
    }

    public void setEstadoAsistencia(String estadoAsistencia) {
        this.estadoAsistencia = estadoAsistencia;
    }

    @Override
    public String getLabel() {
    return estadoAsistencia;
    }
    
    
    
}
