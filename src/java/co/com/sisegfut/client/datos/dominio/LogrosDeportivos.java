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
 * @author Andres Hurtado
 */
@Entity
@Table(name = "logros_deportivos")
public class LogrosDeportivos extends BaseModelData implements BeanModelTag, Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "gen_logros_deportivos")
    @SequenceGenerator(name = "gen_logros_deportivos", sequenceName = "logros_deportivos_id_seq")
    private Long Id;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_deportista", nullable = false)
    private Deportista idDeportista;
    
    @Column(name = "logro_deportivo", nullable = false, length = 80)
    private String logroDeportivo;
    
    @Column(name = "anio_logro", nullable = false, length = 80)
    private String anioLogro;
    
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "categoria_logro", nullable = false)
    private Categoria categoriaLogro;

    public LogrosDeportivos() {
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

    public String getLogroDeportivo() {
        return logroDeportivo;
    }

    public void setLogroDeportivo(String logroDeportivo) {
        this.logroDeportivo = logroDeportivo;
    }

    public String getAnioLogro() {
        return anioLogro;
    }

    public void setAnioLogro(String anioLogro) {
        this.anioLogro = anioLogro;
    }

    public Categoria getCategoriaLogro() {
        return categoriaLogro;
    }

    public void setCategoriaLogro(Categoria categoriaLogro) {
        this.categoriaLogro = categoriaLogro;
    }

 


    
    
    
}
