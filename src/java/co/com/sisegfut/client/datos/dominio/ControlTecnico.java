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
 * @author Malejandro
 */
@Entity
@Table(name = "control_tecnico")
public class ControlTecnico extends BaseModelData implements BeanModelTag, Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "gen_contoltecnico")
    @SequenceGenerator(name = "gen_contoltecnico", sequenceName = "contoltecnico_id_seq")
    private Long Id;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_deportista", nullable = false)
    private Deportista idDeportista;
    
    
    @Column(name = "Recepcion_30_Seg", nullable = false, length = 50)
    private Integer nombrerecepcion30seg;
    
    @Column(name = "Precision_Pase_15_Seg", nullable = false, length = 80)
    private Integer nombreprecisionpase15seg;
    
    @Column(name = "Precision_Disparo_Empeine", nullable = false, length = 50)
    private Integer nombreprecisiondisparoempeine;
    
    @Column(name = "Potencia_Remate", nullable = false, length = 50)
    private Integer nombrepotenciaremate;
    
    @Column(name = "Control_Balon_50_Seg", nullable = false, length = 80)
    private Integer nombrecontrolbalon50seg;
    
    @Column(name = "Conduccion", nullable = false, length = 50)
    private Integer nombreconduccion;
    
    @Column(name = "Cabeceo_Defensivo", nullable = false, length = 50)
    private Integer nombrecabeceodefensivo;
    
    @Column(name = "Cabeceo_Ofensivo", nullable = false, length = 50)
    private Integer nombrecabeceoofensivo;
    
    @Column(name = "Aceleracion", nullable = false, length = 50)
    private Integer nombreaceleracion;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Fecha", nullable = false, length = 50)
    private Date fecha;

    public ControlTecnico() {
    }

    
    public Long getId() {
        return Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    public Deportista getIdDeportista() {
        return idDeportista;
    }

    public void setIdDeportista(Deportista idDeportista) {
        this.idDeportista = idDeportista;
    }

    public Integer getNombrerecepcion30seg() {
        return nombrerecepcion30seg;
    }

    public void setNombrerecepcion30seg(Integer nombrerecepcion30seg) {
        this.nombrerecepcion30seg = nombrerecepcion30seg;
    }

    public Integer getNombreprecisionpase15seg() {
        return nombreprecisionpase15seg;
    }

    public void setNombreprecisionpase15seg(Integer nombreprecisionpase15seg) {
        this.nombreprecisionpase15seg = nombreprecisionpase15seg;
    }

    public Integer getNombreprecisiondisparoempeine() {
        return nombreprecisiondisparoempeine;
    }

    public void setNombreprecisiondisparoempeine(Integer nombreprecisiondisparoempeine) {
        this.nombreprecisiondisparoempeine = nombreprecisiondisparoempeine;
    }

    public Integer getNombrepotenciaremate() {
        return nombrepotenciaremate;
    }

    public void setNombrepotenciaremate(Integer nombrepotenciaremate) {
        this.nombrepotenciaremate = nombrepotenciaremate;
    }

    public Integer getNombrecontrolbalon50seg() {
        return nombrecontrolbalon50seg;
    }

    public void setNombrecontrolbalon50seg(Integer nombrecontrolbalon50seg) {
        this.nombrecontrolbalon50seg = nombrecontrolbalon50seg;
    }

    public Integer getNombreconduccion() {
        return nombreconduccion;
    }

    public void setNombreconduccion(Integer nombreconduccion) {
        this.nombreconduccion = nombreconduccion;
    }

    public Integer getNombrecabeceodefensivo() {
        return nombrecabeceodefensivo;
    }

    public void setNombrecabeceodefensivo(Integer nombrecabeceodefensivo) {
        this.nombrecabeceodefensivo = nombrecabeceodefensivo;
    }

    public Integer getNombrecabeceoofensivo() {
        return nombrecabeceoofensivo;
    }

    public void setNombrecabeceoofensivo(Integer nombrecabeceoofensivo) {
        this.nombrecabeceoofensivo = nombrecabeceoofensivo;
    }

    public Integer getNombreaceleracion() {
        return nombreaceleracion;
    }

    public void setNombreaceleracion(Integer nombreaceleracion) {
        this.nombreaceleracion = nombreaceleracion;
    }



    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    
  
}
