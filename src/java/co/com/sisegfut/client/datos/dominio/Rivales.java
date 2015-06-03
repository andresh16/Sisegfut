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
 * @author ManuelAlejandro
 */
@Entity
@Table(name = "rivales")
public class Rivales extends EntidadPerpetua implements BeanModelTag, Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "gen_rivales")
    @SequenceGenerator(name = "gen_rivales", sequenceName = "rivales_id_seq")
    private Long Id;
    
    @Column(name = "nombre_rival", nullable = false, length = 80)
    private String nombreRival;
        
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "torneo", nullable = false)
    private Torneos torneo;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "jugador_comodin", nullable = false)
    private Deportista jugadorComodin;

    public Rivales() {
    }

    public Rivales(Long Id) {
        this.Id = Id;
    }
    
    

    public Rivales(Long Id, String nombreRival, Torneos torneo) {
        this.Id = Id;
        this.nombreRival = nombreRival;
        this.torneo = torneo;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    public String getNombreRival() {
        return nombreRival;
    }

    public void setNombreRival(String nombreRival) {
        this.nombreRival = nombreRival;
    }

    public Torneos getTorneo() {
        return torneo;
    }

    public void setTorneo(Torneos torneo) {
        this.torneo = torneo;
    }

    public Deportista getJugadorComodin() {
        return jugadorComodin;
    }

    public void setJugadorComodin(Deportista jugadorComodin) {
        this.jugadorComodin = jugadorComodin;
    }
    
    

    @Override
    public String getLabel() {
        return nombreRival;
    }
    
}
