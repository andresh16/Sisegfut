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
@Table(name = "cambios_competencia")
public class CambiosCompe implements BeanModelTag, Serializable{
    
     @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "gen_cambios")
    @SequenceGenerator(name = "gen_cambios", sequenceName = "cambios_id_seq")
    private Long Id;
     
     @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_deportista_entra", nullable = false)
    private Deportista idDeportisteEntra;
     
     @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_deportista_sale", nullable = false)
    private Deportista idDeportistaSale;
     
     @Column(name = "minuto_cambio", nullable = false, length = 50)
    private Integer minutoCambiio;//
     
     @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_competencia", nullable = false)
    private Competencia idCompetencia;

    public CambiosCompe() {
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    public Deportista getIdDeportisteEntra() {
        return idDeportisteEntra;
    }

    public void setIdDeportisteEntra(Deportista idDeportisteEntra) {
        this.idDeportisteEntra = idDeportisteEntra;
    }

    public Deportista getIdDeportistaSale() {
        return idDeportistaSale;
    }

    public void setIdDeportistaSale(Deportista idDeportistaSale) {
        this.idDeportistaSale = idDeportistaSale;
    }

    public Integer getMinutoCambiio() {
        return minutoCambiio;
    }

    public void setMinutoCambiio(Integer minutoCambiio) {
        this.minutoCambiio = minutoCambiio;
    }

    public Competencia getIdCompetencia() {
        return idCompetencia;
    }

    public void setIdCompetencia(Competencia idCompetencia) {
        this.idCompetencia = idCompetencia;
    }
     
     
    
}
