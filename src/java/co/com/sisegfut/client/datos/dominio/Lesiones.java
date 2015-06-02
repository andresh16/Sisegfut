/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.sisegfut.client.datos.dominio;

import com.extjs.gxt.ui.client.data.BaseModelData;
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
 * @author Andres Hurtado
 */
@Entity
@Table(name = "lesiones")
public class Lesiones extends BaseModelData implements BeanModelTag, Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "gen_lesion")
    @SequenceGenerator(name = "gen_lesion", sequenceName = "lesion_id_seq")
    private Long Id;
    
    @Column(name = "nombre_lesion", nullable = false, length = 80)
    private String nombreLesion;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_deportista", nullable = false)
    private Deportista idDeportista;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_lesion", nullable = false, length = 50)
    private Date fechaLesion;

    public Lesiones() {
    }

    public Lesiones(Long Id, String nombreLesion, Deportista idDeportista) {
        this.Id = Id;
        this.nombreLesion = nombreLesion;
        this.idDeportista = idDeportista;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    public String getNombreLesion() {
        return nombreLesion;
    }

    public void setNombreLesion(String nombreLesion) {
        this.nombreLesion = nombreLesion;
    }

    public Deportista getIdDeportista() {
        return idDeportista;
    }

    public void setIdDeportista(Deportista idDeportista) {
        this.idDeportista = idDeportista;
    }

    public Date getFechaLesion() {
        return fechaLesion;
    }

    public void setFechaLesion(Date fechaLesion) {
        this.fechaLesion = fechaLesion;
    }

    

    
    
}
