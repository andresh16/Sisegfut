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
import javax.persistence.Transient;

/**
 *
 * @author Malejandro
 */
@Entity
@Table(name = "torneos")
public class Torneos extends EntidadPerpetua implements BeanModelTag, Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "gen_torneos")
    @SequenceGenerator(name = "gen_torneos", sequenceName = "torneos_id_seq")
    private Long Id;
    
    @Column(name = "nombre_torneo", nullable = false, length = 80)
    private String nombreTorneo;
    
    @Column(name = "anno", nullable = false, length = 80)
    private String anno;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "categoria", nullable = false)
    private Categoria categoria;
    
    @Transient
    private String torneoCompleto;

    public Torneos() {
        
    }

    public Torneos(Long Id) {
        this.Id = Id;
    }
    

    public Torneos(Long Id, String nombreTorneo, String anno, Categoria categoria) {
        this.Id = Id;
        this.nombreTorneo = nombreTorneo;
        this.anno = anno;
        this.categoria = categoria;
    }

    
    public Long getId() {
        return Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    public String getNombreTorneo() {
        return nombreTorneo;
    }

    public void setNombreTorneo(String nombreTorneo) {
        this.nombreTorneo = nombreTorneo;
    }

    public String getAnno() {
        return anno;
    }

    public void setAnno(String anno) {
        this.anno = anno;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    @Override
    public String getLabel() {
        return nombreTorneo+" - "+getAnno();
    }
    
    
    
}
