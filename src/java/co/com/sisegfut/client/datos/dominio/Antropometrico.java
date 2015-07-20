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
@Table(name = "antropometrico")
public class Antropometrico extends BaseModelData implements BeanModelTag, Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "gen_antropometrico")
    @SequenceGenerator(name = "gen_antropometrico", sequenceName = "antropometrico_id_seq")
    private Long Id;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_deportista", nullable = false)
    private Deportista idDeportista;
    
    @Column(name = "Per_Brazo_Relajado", nullable = false, length = 5)
    private Integer perbrazorelajado;
    
    @Column(name = "Per_Abdominal", nullable = false, length = 5)
    private Integer perabdominal;
    
    @Column(name = "Per_Cadera", nullable = false, length = 5)
    private Integer percadera;
    
    @Column(name = "Per_Pantorrilla", nullable = false, length = 5)
    private Integer perpantorrilla;
    
    @Column(name = "Pli_Subescapular", nullable = false, length = 5)
    private Integer plisubescapular;
    
    @Column(name = "Pli_Tricipital", nullable = false, length = 5)
    private Integer plitricipital;
    
    @Column(name = "Pli_Supraescapular", nullable = false, length = 5)
    private Integer plisupraescapular;
    
    @Column(name = "Pli_Abdominal", nullable = false, length = 5)
    private Integer pliabdominal;
    
    @Column(name = "porcentaje_grasa", nullable = false, length = 50)
    private String porcentajeGrasa;
    
    @Column(name = "peso_macro", nullable = false, length = 50)
    private String pesoMacro;
    
    @Column(name = "peso_graso", nullable = false, length = 50)
    private String pesoGraso;
    
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Fecha", nullable = false, length = 50)
    private Date fecha;

    public Antropometrico() {
        
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

    public Integer getPerbrazorelajado() {
        return perbrazorelajado;
    }

    public void setPerbrazorelajado(Integer perbrazorelajado) {
        this.perbrazorelajado = perbrazorelajado;
    }

    public Integer getPlisupraescapular() {
        return plisupraescapular;
    }

    public void setPlisupraescapular(Integer plisupraescapular) {
        this.plisupraescapular = plisupraescapular;
    }


    public Integer getPerabdominal() {
        return perabdominal;
    }

    public void setPerabdominal(Integer perabdominal) {
        this.perabdominal = perabdominal;
    }

    public Integer getPercadera() {
        return percadera;
    }

    public void setPercadera(Integer percadera) {
        this.percadera = percadera;
    }

    public Integer getPerpantorrilla() {
        return perpantorrilla;
    }

    public void setPerpantorrilla(Integer perpantorrilla) {
        this.perpantorrilla = perpantorrilla;
    }

    public Integer getPlisubescapular() {
        return plisubescapular;
    }

    public void setPlisubescapular(Integer plisubescapular) {
        this.plisubescapular = plisubescapular;
    }

    public Integer getPlitricipital() {
        return plitricipital;
    }

    public void setPlitricipital(Integer plitricipital) {
        this.plitricipital = plitricipital;
    }


    public Integer getPliabdominal() {
        return pliabdominal;
    }

    public void setPliabdominal(Integer pliabdominal) {
        this.pliabdominal = pliabdominal;
    }

    public String getPorcentajeGrasa() {
        return porcentajeGrasa;
    }

    public void setPorcentajeGrasa(String porcentajeGrasa) {
        this.porcentajeGrasa = porcentajeGrasa;
    }

    public String getPesoMacro() {
        return pesoMacro;
    }

    public void setPesoMacro(String pesoMacro) {
        this.pesoMacro = pesoMacro;
    }

    public String getPesoGraso() {
        return pesoGraso;
    }

    public void setPesoGraso(String pesoGraso) {
        this.pesoGraso = pesoGraso;
    }

    

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
       
}
