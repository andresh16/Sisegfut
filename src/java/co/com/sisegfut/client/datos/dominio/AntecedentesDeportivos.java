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
@Table(name = "antecedentes_deportivos")
public class AntecedentesDeportivos extends BaseModelData implements BeanModelTag, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "gen_antecedentes_dep")
    @SequenceGenerator(name = "gen_antecedentes_dep", sequenceName = "antecedentes_dep_id_seq")
    private Long Id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_deportista", nullable = false)
    private Deportista idDeportista;

    @Column(name = "club_anterior", nullable = false, length = 80)
    private String clubAnterior;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "categoria_anterior", nullable = false)
    private Categoria categoriaAnterior;

    @Column(name = "anno", nullable = false, length = 80)
    private String anno;

    public AntecedentesDeportivos() {
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

    public String getClubAnterior() {
        return clubAnterior;
    }

    public void setClubAnterior(String clubAnterior) {
        this.clubAnterior = clubAnterior;
    }

    public Categoria getCategoriaAnterior() {
        return categoriaAnterior;
    }

    public void setCategoriaAnterior(Categoria categoriaAnterior) {
        this.categoriaAnterior = categoriaAnterior;
    }

    public String getAnno() {
        return anno;
    }

    public void setAnno(String anno) {
        this.anno = anno;
    }

    public AntecedentesDeportivos(Long Id, Deportista idDeportista, String clubAnterior, Categoria categoriaAnterior, String anno) {
        this.Id = Id;
        this.idDeportista = idDeportista;
        this.clubAnterior = clubAnterior;
        this.categoriaAnterior = categoriaAnterior;
        this.anno = anno;
    }

}
