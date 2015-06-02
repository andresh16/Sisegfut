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
@Table(name = "estudios")
public class EstudiosRealizados extends BaseModelData implements BeanModelTag, Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "gen_estudios_realizados")
    @SequenceGenerator(name = "gen_estudios_realizados", sequenceName = "estudios_realizados_id_seq")
    private Long Id;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_personal", nullable = false)
    private Personal idPersonal;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "nivel_Educativo", nullable = false)
    private NivelEducativo nivelEducativo;
    
    @Column(name = "titulo", nullable = false, length = 80)
    private String titulo;
    
    @Column(name = "institucion", nullable = false, length = 80)
    private String institucion;
    
    @Column(name = "anio_graduacion", nullable = false, length = 80)
    private String anioGraduacion;

    public EstudiosRealizados() {
        
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

    
    public NivelEducativo getNivelEducativo() {
        return nivelEducativo;
    }

    public void setNivelEducativo(NivelEducativo nivelEducativo) {
        this.nivelEducativo = nivelEducativo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getInstitucion() {
        return institucion;
    }

    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }

    public String getAnioGraduacion() {
        return anioGraduacion;
    }

    public void setAnioGraduacion(String anioGraduacion) {
        this.anioGraduacion = anioGraduacion;
    }
    
    
    
}
