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
@Table(name = "test_cooper")
public class TestCooper extends BaseModelData implements BeanModelTag, Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "gen_testCooper")
    @SequenceGenerator(name = "gen_testCooper", sequenceName = "testCooper_id_seq")
    private Long Id;

    @Column(name = "distancia", nullable = false, length = 3)
    private Integer distancia;

    @Column(name = "consumo_oxigeno", nullable = false, length = 10)
    private String consumOxigeno;
    
    @Column(name = "condicion_fisica", nullable = false, length = 10)
    private String condicionFisica;
    
    @Column(name = "velocidad", nullable = false, length = 10)
    private String velocidad;
    
    @Column(name = "vo2max", nullable = false, length = 10)
    private String vo2max;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Fecha", nullable = false, length = 50)
    private Date fecha;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_deportista", nullable = false)
    private Deportista idDeportista;

    public TestCooper() {
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    public String getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(String velocidad) {
        this.velocidad = velocidad;
    }

    public String getVo2max() {
        return vo2max;
    }

    public void setVo2max(String vo2max) {
        this.vo2max = vo2max;
    }
    public Integer getDistancia() {
        return distancia;
    }

    public void setDistancia(Integer distancia) {
        this.distancia = distancia;
    }

    public String getConsumOxigeno() {
        return consumOxigeno;
    }
    

    public void setConsumOxigeno(String consumOxigeno) {
        this.consumOxigeno = consumOxigeno;
    }

    public String getCondicionFisica() {
        return condicionFisica;
    }

    public void setCondicionFisica(String condicionFisica) {
        this.condicionFisica = condicionFisica;
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
