/*
 * To change this template, choose Tools | Templates
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
@Table(name = "categoria")
public class Categoria extends EntidadPerpetua implements BeanModelTag, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "gen_categoria")
    @SequenceGenerator(name = "gen_categoria", sequenceName = "categoria_id_seq")
    private Long Id;
    
    @Column(name = "nombre_categoria", nullable = false, length = 80)
    private String nombreCategoria;
    
        public Categoria(Long Id, String nombrecategoria) {
        this.Id = Id;
        this.nombreCategoria = nombrecategoria;
       
    }

    public Categoria() {
        
        
    }

    public Categoria(Long Id) {
        this.Id = Id;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

    public String getNombrecategoria() {
        return nombreCategoria;
    }

    public void setNombrecategoria(String nombrecategoria) {
        this.nombreCategoria = nombrecategoria;
    }

    

    @Override
    public String getLabel() {
        return getNombrecategoria();
    }
}
