/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.sisegfut.client.datos.dominio;

import com.extjs.gxt.ui.client.data.BeanModelTag;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author fhurtado
 */
@Entity
@Table(name = "control_asistencia")
public class ControlAsistencia  implements BeanModelTag, Serializable{
     @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "gen_control_asistencia")
    @SequenceGenerator(name = "gen_control_asistencia", sequenceName = "control_asistencia_id_seq")
    private Long Id;
     
      @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha", nullable = false, length = 150)
    private Date fecha;
    
    @Column(name = "lugar", nullable = false, length = 80)
    private String lugar;
    
    @Column(name = "actividad", nullable = false, length = 80)
    private String actividad;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "categoria", nullable = false)
    private Categoria categoria;
    
    @Column(name = "observaciones", nullable = true, length = 300)
    private String observaciones;

    public ControlAsistencia() {
    }

    public ControlAsistencia(Long IdDep) {
        this.Id=IdDep;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

    public String getActividad() {
        return actividad;
    }

    public void setActividad(String actividad) {
        this.actividad = actividad;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    
    
    
    
}
