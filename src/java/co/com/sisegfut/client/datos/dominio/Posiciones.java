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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Andres Hurtado
 */
@Entity
@Table(name = "posiciones")
public class Posiciones extends EntidadPerpetua implements BeanModelTag, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "gen_posiciones")
    @SequenceGenerator(name = "gen_posiciones", sequenceName = "posiciones_id_seq")
    private Long Id;

    @Column(name = "nombre_posicion", nullable = false, length = 80)
    private String nombrePosicion;

    public Posiciones() {
    }

    public Posiciones(Long Id, String nombrePosicion) {
        this.Id = Id;
        this.nombrePosicion = nombrePosicion;
    }

    public Posiciones(Long Id) {
        this.Id = Id;
    }

    
    public String getNombrePosicion() {
        return nombrePosicion;
    }

    public void setNombrePosicion(String nombrePosicion) {
        this.nombrePosicion = nombrePosicion;
    }

    @Override
    public Long getId() {
        return Id;
    }

    @Override
    public void setId(Long Id) {
        this.Id = Id;
    }

    @Override
    public String getLabel() {
        return nombrePosicion;
    }

}
