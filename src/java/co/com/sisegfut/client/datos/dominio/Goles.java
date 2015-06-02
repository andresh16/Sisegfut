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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author fhurtado
 */
@Entity
@Table(name = "goles")
public class Goles implements BeanModelTag, Serializable{
    
     @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "gen_goles")
    @SequenceGenerator(name = "gen_goles", sequenceName = "goles_id_seq")
    private Long Id;
     
     @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_deportista", nullable = false)
    private Deportista idDeportista;
     
     @Column(name = "minuto_gol", nullable = false, length = 50)
    private Integer minutoGol;
     
     @Column(name = "equipo_gol", nullable = false, length = 50)
    private String equipoGol;// si el el poli o el rival
     
     @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_competencia", nullable = false)
    private Competencia idCompetencia;

    public Goles() {
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

    public Integer getMinutoGol() {
        return minutoGol;
    }

    public void setMinutoGol(Integer minutoGol) {
        this.minutoGol = minutoGol;
    }

    public String getEquipoGol() {
        return equipoGol;
    }

    public void setEquipoGol(String equipoGol) {
        this.equipoGol = equipoGol;
    }

    public Competencia getIdCompetencia() {
        return idCompetencia;
    }

    public void setIdCompetencia(Competencia idCompetencia) {
        this.idCompetencia = idCompetencia;
    }
    
     
     
}
