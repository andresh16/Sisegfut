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
@Table(name = "situaciones_juego")
public class SituacionesJuegoCompe implements BeanModelTag, Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "gen_situaciones_compe")
    @SequenceGenerator(name = "gen_situaciones_compe", sequenceName = "situaciones_compe_id_seq")
    private Long Id;

    @Column(name = "tiempo_situacion", nullable = false, length = 50)
    private Integer tiempoSituacion;

    @Column(name = "equipo_situacion", nullable = false, length = 50)
    private String equipoSituacion;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_competencia", nullable = false)
    private Competencia idCompetencia;

    // Situaciones de juego //////////////////////////////////////////////////
    @Column(name = "falta_zona1", nullable = false)
    private Integer faltaZona1;
    @Column(name = "falta_zona2", nullable = false)
    private Integer faltaZona2;
    @Column(name = "faltaZona3", nullable = false)
    private Integer faltaZona3;

    @Column(name = "recuperacion_zona1", nullable = false)
    private Integer recuperacionZona1;
    @Column(name = "recuperacion_zona2", nullable = false)
    private Integer recuperacionZona2;
    @Column(name = "recuperacion_zona3", nullable = false)
    private Integer recuperacionZona3;

    @Column(name = "tiro_libre_zona1", nullable = false)
    private Integer tiroLibreZona1;
    @Column(name = "tiro_libre_zona2", nullable = false)
    private Integer tiroLibreZona2;
    @Column(name = "tiro_libre_zona3", nullable = false)
    private Integer tiroLibreZona3;

    @Column(name = "tiro_esquina", nullable = false)
    private Integer tiroEsquina;

    @Column(name = "fuera_lugar", nullable = false)
    private Integer fueraLugar;

    @Column(name = "penalty", nullable = false)
    private Integer penalty;

    @Column(name = "opcion_gol", nullable = false)
    private Integer opcionGol;

    @Column(name = "centrol_lateral", nullable = false)
    private Integer centrolLateral;

    @Column(name = "remates", nullable = false)
    private Integer remates;

    @Column(name = "entregas_erradas", nullable = false)
    private Integer entregasErradas;

    public SituacionesJuegoCompe() {
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    public Integer getTiempoSituacion() {
        return tiempoSituacion;
    }

    public void setTiempoSituacion(Integer tiempoSituacion) {
        this.tiempoSituacion = tiempoSituacion;
    }

    public String getEquipoSituacion() {
        return equipoSituacion;
    }

    public void setEquipoSituacion(String equipoSituacion) {
        this.equipoSituacion = equipoSituacion;
    }

    public Competencia getIdCompetencia() {
        return idCompetencia;
    }

    public void setIdCompetencia(Competencia idCompetencia) {
        this.idCompetencia = idCompetencia;
    }

    public Integer getFaltaZona1() {
        return faltaZona1;
    }

    public void setFaltaZona1(Integer faltaZona1) {
        this.faltaZona1 = faltaZona1;
    }

    public Integer getFaltaZona2() {
        return faltaZona2;
    }

    public void setFaltaZona2(Integer faltaZona2) {
        this.faltaZona2 = faltaZona2;
    }

    public Integer getFaltaZona3() {
        return faltaZona3;
    }

    public void setFaltaZona3(Integer faltaZona3) {
        this.faltaZona3 = faltaZona3;
    }

    public Integer getRecuperacionZona1() {
        return recuperacionZona1;
    }

    public void setRecuperacionZona1(Integer recuperacionZona1) {
        this.recuperacionZona1 = recuperacionZona1;
    }

    public Integer getRecuperacionZona2() {
        return recuperacionZona2;
    }

    public void setRecuperacionZona2(Integer recuperacionZona2) {
        this.recuperacionZona2 = recuperacionZona2;
    }

    public Integer getRecuperacionZona3() {
        return recuperacionZona3;
    }

    public void setRecuperacionZona3(Integer recuperacionZona3) {
        this.recuperacionZona3 = recuperacionZona3;
    }

    public Integer getTiroLibreZona1() {
        return tiroLibreZona1;
    }

    public void setTiroLibreZona1(Integer tiroLibreZona1) {
        this.tiroLibreZona1 = tiroLibreZona1;
    }

    public Integer getTiroLibreZona2() {
        return tiroLibreZona2;
    }

    public void setTiroLibreZona2(Integer tiroLibreZona2) {
        this.tiroLibreZona2 = tiroLibreZona2;
    }

    public Integer getTiroLibreZona3() {
        return tiroLibreZona3;
    }

    public void setTiroLibreZona3(Integer tiroLibreZona3) {
        this.tiroLibreZona3 = tiroLibreZona3;
    }

    public Integer getTiroEsquina() {
        return tiroEsquina;
    }

    public void setTiroEsquina(Integer tiroEsquina) {
        this.tiroEsquina = tiroEsquina;
    }

    public Integer getFueraLugar() {
        return fueraLugar;
    }

    public void setFueraLugar(Integer fueraLugar) {
        this.fueraLugar = fueraLugar;
    }

    public Integer getPenalty() {
        return penalty;
    }

    public void setPenalty(Integer penalty) {
        this.penalty = penalty;
    }

    public Integer getOpcionGol() {
        return opcionGol;
    }

    public void setOpcionGol(Integer opcionGol) {
        this.opcionGol = opcionGol;
    }

    public Integer getCentrolLateral() {
        return centrolLateral;
    }

    public void setCentrolLateral(Integer centrolLateral) {
        this.centrolLateral = centrolLateral;
    }

    public Integer getRemates() {
        return remates;
    }

    public void setRemates(Integer remates) {
        this.remates = remates;
    }

    public Integer getEntregasErradas() {
        return entregasErradas;
    }

    public void setEntregasErradas(Integer entregasErradas) {
        this.entregasErradas = entregasErradas;
    }

  

    
}
