/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.client.datos.dominio;

import com.extjs.gxt.ui.client.data.BeanModelTag;
import java.io.Serializable;
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
@Table(name = "cuerpo_tecnico_competencia")
public class CuerpoTecnicoCompe implements BeanModelTag, Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "gen_cuerpo_tec")
    @SequenceGenerator(name = "gen_cuerpo_tec", sequenceName = "cuerpotec_id_seq")
    private Long Id;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_personal", nullable = false)
    private Personal idPersonal;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_competencia", nullable = false)
    private Competencia idCompetencia;
    
    

    public CuerpoTecnicoCompe() {
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    public Personal getIdPersonal() {
        return idPersonal;
    }

    public void setIdPersonal(Personal idPersonal) {
        this.idPersonal = idPersonal;
    }


    public Competencia getIdCompetencia() {
        return idCompetencia;
    }

    public void setIdCompetencia(Competencia idCompetencia) {
        this.idCompetencia = idCompetencia;
    }

    
}
