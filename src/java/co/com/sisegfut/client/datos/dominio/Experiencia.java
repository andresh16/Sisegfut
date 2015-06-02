/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.sisegfut.client.datos.dominio;

import com.extjs.gxt.ui.client.data.BaseModelData;
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
 * @author Camilo
 */
@Entity
@Table(name = "experiencia")
public class Experiencia extends BaseModelData implements BeanModelTag, Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "gen_experiencia")
    @SequenceGenerator(name = "gen_experiencia", sequenceName = "experiencia_id_seq")
    private Long Id;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_personal", nullable = false)
    private Personal idPersonal;
    
    @Column(name = "empresa", nullable = false, length = 80)
    private String empresa;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cargos", nullable = false)
    private Cargos cargo;
    
    @Column(name = "tiempo_laborado", nullable = false, length = 80)
    private String tiempoLaborado;

    public Experiencia() {
        
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


    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public Cargos getCargo() {
        return cargo;
    }

    public void setCargo(Cargos cargo) {
        this.cargo = cargo;
    }  

    public String getTiempoLaborado() {
        return tiempoLaborado;
    }

    public void setTiempoLaborado(String tiempoLaborado) {
        this.tiempoLaborado = tiempoLaborado;
    }
    
    
}
