/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.client.datos.dominio;

import com.extjs.gxt.ui.client.data.BeanModelTag;
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
import javax.persistence.UniqueConstraint;

/**
 *
 * @author fhurtado
 */
@Entity
@Table(name="cuentas", uniqueConstraints =
        {
            @UniqueConstraint(columnNames = "numerocuenta")   
        })
public class Cuentas extends EntidadPerpetua implements BeanModelTag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "gen_cuenta")
    @SequenceGenerator(name = "gen_cuenta", sequenceName = "cuentas_id_seq")
    private Long id;

    @Column(name = "numerocuenta", nullable = false, length = 100)
    private String numcuenta;
    
    @Column(name = "nombrecuenta", nullable = false, length = 100)
    private String nombrecuenta;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tipocuenta", nullable = false)
    private TipoCuenta tipocuenta;
    
    @Column(name = "saldo", nullable = false, length = 100)
    private Double saldo;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario", nullable = false)
    private Usuarios usuario;

    public Cuentas(Long id, String numcuenta, String nombrecuenta, TipoCuenta tipocuenta, Double saldo, Usuarios usuario) {
        this.id = id;
        this.numcuenta = numcuenta;
        this.nombrecuenta = nombrecuenta;
        this.tipocuenta = tipocuenta;
        this.saldo = saldo;
        this.usuario = usuario;
    }

  


    public Cuentas() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumcuenta() {
        return numcuenta;
    }

    public void setNumcuenta(String numcuenta) {
        this.numcuenta = numcuenta;
    }

    public String getNombrecuenta() {
        return nombrecuenta;
    }

    public void setNombrecuenta(String nombrecuenta) {
        this.nombrecuenta = nombrecuenta;
    }

    public TipoCuenta getTipocuenta() {
        return tipocuenta;
    }

    public void setTipocuenta(TipoCuenta tipocuenta) {
        this.tipocuenta = tipocuenta;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }


    


    @Override
    public String getLabel() {
        return getNombrecuenta();
   }
    
      
    
    
}
