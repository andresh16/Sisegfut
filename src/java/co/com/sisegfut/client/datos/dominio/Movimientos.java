/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.client.datos.dominio;

import com.extjs.gxt.ui.client.data.BeanModelTag;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author fhurtado
 */
@Entity
@Table(name = "movimientos")
public class Movimientos extends EntidadPerpetua implements BeanModelTag {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "gen_movimiento")
    @SequenceGenerator(name = "gen_movimiento", sequenceName = "movimientos_id_seq")
    private Long id;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fechamov", nullable = false, length = 150)
    private Date fechamov;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ncuenta", nullable = false)
    private Cuentas ncuenta;
    
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tipomov", nullable = false)
    private TipoMovimiento tipomov;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "categoria", nullable = false)
    private Categoria categoria;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usuario", nullable = false)
    private Usuarios usuario;
    
    
    @Column(name = "ingreso", nullable = true, length = 150)
    private Double ingreso;
    
    @Column(name = "egreso", nullable = true, length = 150)
    private Double egreso;
    
    @Column(name = "descripcion", nullable = false, length = 150)
    private String descripcion;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ncuentatrans", nullable = true)
    private Cuentas ncuentatrans;

    public Movimientos() {
    }



    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechamov() {
        return fechamov;
    }

    public void setFechamov(Date fechamov) {
        this.fechamov = fechamov;
    }

    public Cuentas getNcuenta() {
        return ncuenta;
    }

    public void setNcuenta(Cuentas ncuenta) {
        this.ncuenta = ncuenta;
    }

    public TipoMovimiento getTipomov() {
        return tipomov;
    }

    public void setTipomov(TipoMovimiento tipomov) {
        this.tipomov = tipomov;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Usuarios getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuarios usuario) {
        this.usuario = usuario;
    }

    public Double getIngreso() {
        return ingreso;
    }

    public void setIngreso(Double ingreso) {
        this.ingreso = ingreso;
    }

    public Double getEgreso() {
        return egreso;
    }

    public void setEgreso(Double egreso) {
        this.egreso = egreso;
    }


    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

       public Cuentas getNcuentatrans() {
        return ncuentatrans;
    }

    public void setNcuentatrans(Cuentas ncuentatrans) {
        this.ncuentatrans = ncuentatrans;
    }
    @Override
    public String getLabel() {
       return null;
    }
    
}
