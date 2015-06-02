/*
 * To change this template, choose Tools | Templates
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
import javax.persistence.Transient;

/**
 *
 * @author fhurtado
 */
@Entity
@Table(name="tipo_cuenta")
public class TipoCuenta implements BeanModelTag,Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "gen_tipocuenta")
    @SequenceGenerator(name = "gen_tipocuenta", sequenceName = "tipo_cuenta_seq")
    private Long Id;
    
    @Column(name = "nombreTipoCuenta", nullable = false, length = 30)
    private String nombreTipoCuenta;

    public TipoCuenta(Long Id_TipoDoc, String nombreTipoCuenta) {
        this.Id = Id_TipoDoc;
        this.nombreTipoCuenta = nombreTipoCuenta;
    }

    public TipoCuenta() {
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long Id) {
        this.Id = Id;
    }

   
    public String getNombreTipoCuenta() {
        return nombreTipoCuenta;
    }

    public void setNombreTipoCuenta(String nombreTipoCuenta) {
        this.nombreTipoCuenta = nombreTipoCuenta;
    }

  
    }