/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.client.datos.dominio;

import com.extjs.gxt.ui.client.data.BaseModelData;
import com.extjs.gxt.ui.client.data.BeanModelTag;
import com.extjs.gxt.ui.client.data.ModelData;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.Map;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author Andres Hurtado
 */
@Entity
@Table(name = "competencia")
public class Competencia extends EntidadPerpetua implements BeanModelTag, Serializable,ModelData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "gen_competencia")
    @SequenceGenerator(name = "gen_competencia", sequenceName = "competencia_id_seq")
    private Long Id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Fecha", nullable = false, length = 50)
    private Date fecha;

    @Column(name = "anfitrion", nullable = false, length = 50)
    private String anfitrion;

    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "rival", nullable = false)
    private Rivales rival;

    @Column(name = "lugar", nullable = false, length = 50)
    private String lugar;
    
    @Column(name = "goles_anfitrion", nullable = false, length = 50)
    private Integer golesAnfitrion;
    
    @Column(name = "goles_rival", nullable = false, length = 50)
    private Integer golesRival;
    
    @Column(name = "finaliza_compentcia", nullable = false)
    private boolean finalizaCompentcia;
    
    @Lob
    @Column(name = "observacion")
    private String observacion;

//    @ManyToOne(fetch = FetchType.EAGER)
//    @JoinColumn(name = "categoria", nullable = false)
//    private Categoria categoria;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "torneo", nullable = false)
    private Torneos torneo;

    @Transient
    private String compromiso;

    public Competencia() {
    }

    public Competencia(Long idCompetencia) {
        setId(idCompetencia);
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

    public String getAnfitrion() {
        return anfitrion;
    }

    public void setAnfitrion(String anfitrion) {
        this.anfitrion = anfitrion;
    }

    public Rivales getRival() {
        return rival;
    }

    public void setRival(Rivales rival) {
        this.rival = rival;
    }

    

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }

//    public Categoria getCategoria() {
//        return categoria;
//    }
//
//    public void setCategoria(Categoria categoria) {
//        this.categoria = categoria;
//    }

    public Torneos getTorneo() {
        return torneo;
    }

    public void setTorneo(Torneos torneo) {
        this.torneo = torneo;
    }

    public String getCompromiso() {
        String compromiso = getAnfitrion() + "= "+getGolesAnfitrion()+" VS " + getRival().getNombreRival()+"= "+getGolesRival();
        return compromiso;
    }

    public void setCompromiso(String compromiso) {
        this.compromiso = compromiso;
    }

  

    public Integer getGolesAnfitrion() {
        return golesAnfitrion;
    }

    public void setGolesAnfitrion(Integer golesAnfitrion) {
        this.golesAnfitrion = golesAnfitrion;
    }

    public Integer getGolesRival() {
        return golesRival;
    }

    public void setGolesRival(Integer golesRival) {
        this.golesRival = golesRival;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public boolean isFinalizaCompentcia() {
        return finalizaCompentcia;
    }

    public void setFinalizaCompentcia(boolean finalizaCompentcia) {
        this.finalizaCompentcia = finalizaCompentcia;
    }

    @Override
    public String getLabel() {
        return getCompromiso();
    }

    @Override
    public <X> X get(String property) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Map<String, Object> getProperties() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<String> getPropertyNames() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <X> X remove(String property) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public <X> X set(String property, X value) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    

}
