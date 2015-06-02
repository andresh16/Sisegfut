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
@Table(name = "control_disciplinario")
public class ControlDisciplinarioCompe implements BeanModelTag, Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "gen_control_disci")
    @SequenceGenerator(name = "gen_control_disci", sequenceName = "control_disci_id_seq")
    private Long Id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_deportista", nullable = false)
    private Deportista idDeportista;

    @Column(name = "tipo_tarjeta", nullable = false, length = 50)
    private String tipoTarjeta;//si es roja o amarilla

    @Column(name = "minuto_tarjeta", nullable = false, length = 50)
    private Integer minutoTarjeta;//si es roja o amarilla

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_competencia", nullable = false)
    private Competencia idCompetencia;
    
     @Column(name = "equipo_tarjeta", nullable = false, length = 50)
    private String equipotarjeta;// si el el poli o el rival

    public ControlDisciplinarioCompe() {
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

    public String getTipoTarjeta() {
        return tipoTarjeta;
    }

    public void setTipoTarjeta(String tipoTarjeta) {
        this.tipoTarjeta = tipoTarjeta;
    }

    public Integer getMinutoTarjeta() {
        return minutoTarjeta;
    }

    public void setMinutoTarjeta(Integer minutoTarjeta) {
        this.minutoTarjeta = minutoTarjeta;
    }

    public Competencia getIdCompetencia() {
        return idCompetencia;
    }

    public void setIdCompetencia(Competencia idCompetencia) {
        this.idCompetencia = idCompetencia;
    }

    public String getEquipotarjeta() {
        return equipotarjeta;
    }

    public void setEquipotarjeta(String equipotarjeta) {
        this.equipotarjeta = equipotarjeta;
    }
    
    
    

}
