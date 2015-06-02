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
@Table(name = "test_karvonen")
public class TestKarvonen extends BaseModelData implements BeanModelTag, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "gen_testKarvonen")
    @SequenceGenerator(name = "gen_testKarvonen", sequenceName = "testKarvonen_id_seq")
    private Long Id;

    @Column(name = "fc_reposo", nullable = false, length = 3)
    private Integer fcReposo;

    @Column(name = "resultado_karvonen", nullable = false, length = 10)
    private String resultadoKarvonen;

    @Column(name = "porcentaje", nullable = false, length = 3)
    private Integer porcentaje;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Fecha", nullable = false, length = 50)
    private Date fecha;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_deportista", nullable = false)
    private Deportista idDeportista;

    public TestKarvonen() {
    }

    
    public Long getId() {
        return Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    public Integer getFcReposo() {
        return fcReposo;
    }

    public void setFcReposo(Integer fcReposo) {
        this.fcReposo = fcReposo;
    }

    public String getResultadoKarvonen() {
        return resultadoKarvonen;
    }

    public void setResultadoKarvonen(String resultadoKarvonen) {
        this.resultadoKarvonen = resultadoKarvonen;
    }

    public Integer getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(Integer porcentaje) {
        this.porcentaje = porcentaje;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Deportista getIdDeportista() {
        return idDeportista;
    }

    public void setIdDeportista(Deportista idDeportista) {
        this.idDeportista = idDeportista;
    }

    
    
}
