/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.sisegfut.client.datos.dominio;

import com.extjs.gxt.ui.client.data.BeanModelTag;
import com.google.gwt.user.client.rpc.GwtTransient;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
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
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 *
 * @author Andres Hurtado
 */
@Entity
@Table(name = "personal", uniqueConstraints
        = {
            @UniqueConstraint(columnNames = "documento")
        })
public class Personal extends EntidadPerpetua implements BeanModelTag, Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "gen_personal")
    @SequenceGenerator(name = "gen_personal", sequenceName = "personal_id_seq")
    private Long Id;
    
    @Column(name = "documento", nullable = false, length = 50)
    private String documento;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tipo_documento", nullable = false)
    private TipoDocumento tipoDocumento;
    
    @Column(name = "nombres", nullable = false, length = 150)
    private String nombres;
    @Column(name = "apellidos", nullable = false, length = 150)
    private String apellidos;
    
    //0001707: correo electrónico debe ser opcional al crear usuarios
    @Column(name = "correo_electronico", unique = true, length = 150)
    private String correoElectronico;
    
    @Column(name = "direccion", nullable = false, length = 150)
    private String direccion;
    
    @Column(name = "telefono", nullable = false, length = 150)
    private String telefono;
    
    @Column(name = "celular", nullable = true, length = 150)
    private String celular;    
        
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cargos", nullable = false)
    private Cargos cargo;
    
    @Column(name = "genero", nullable = false, length = 150)
    private String genero;
    
    @Column(name = "barrio", nullable = false, length = 150)
    private String barrio;
    
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_nacimiento", nullable = false, length = 150)
    private Date fechaNacimiento;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "nivel_Educativo", nullable = false)
    private NivelEducativo nivelEducativo;
    
    @Column(name = "lunes", nullable = true)
    private Boolean lunes;
    
    @Column(name = "martes", nullable = true)
    private Boolean martes;
    
    @Column(name = "miercoles", nullable = true)
    private Boolean miercoles;
    
    @Column(name = "jueves", nullable = true)
    private Boolean jueves;
    
    @Column(name = "viernes", nullable = true)
    private Boolean viernes;    
    
    @Column(name = "sabado", nullable = true)
    private Boolean sabado;   
    
    @Temporal(TemporalType.TIME)
    @Column(name = "hora_inicio", nullable = true)
    private Date horaInicio;
    
    @Temporal(TemporalType.TIME)
    @Column(name = "hora_fin", nullable = true)
    private Date horaFin;
    
    @GwtTransient
    @Column(name = "foto")
    @JsonIgnore(true)
    @Basic(fetch = FetchType.LAZY)
    private byte[] foto;
    //    @Cascade( { org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.DELETE_ORPHAN })
    @Column(name = "content_type_foto")
    private String contentTypeFoto;

    public Personal() {
    }

    public Personal(Long Id) {
        this.Id = Id;
    }


    @Override
    public Long getId() {
        return Id;
    }

    public Boolean getLunes() {
        return lunes;
    }

    public void setLunes(Boolean lunes) {
        this.lunes = lunes;
    }

    public Boolean getMartes() {
        return martes;
    }

    public void setMartes(Boolean martes) {
        this.martes = martes;
    }

    public Boolean getMiercoles() {
        return miercoles;
    }

    public void setMiercoles(Boolean miercoles) {
        this.miercoles = miercoles;
    }

    public Boolean getJueves() {
        return jueves;
    }

    public void setJueves(Boolean jueves) {
        this.jueves = jueves;
    }

    public Boolean getViernes() {
        return viernes;
    }

    public void setViernes(Boolean viernes) {
        this.viernes = viernes;
    }

    public Boolean getSabado() {
        return sabado;
    }

    public void setSabado(Boolean sabado) {
        this.sabado = sabado;
    }

    public Date getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Date getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(Date horaFin) {
        this.horaFin = horaFin;
    }

    
    
    @Override
    public void setId(Long Id) {
        this.Id = Id;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public Cargos getCargo() {
        return cargo;
    }

    public void setCargo(Cargos cargo) {
        this.cargo = cargo;
    }
    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
        

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }


    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public NivelEducativo getNivelEducativo() {
        return nivelEducativo;
    }

    public void setNivelEducativo(NivelEducativo nivelEducativo) {
        this.nivelEducativo = nivelEducativo;
    }
        
    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
    }

    public String getContentTypeFoto() {
        return contentTypeFoto;
    }

    public void setContentTypeFoto(String contentTypeFoto) {
        this.contentTypeFoto = contentTypeFoto;
    }
    
    public String crearNombreCompleto() {
        return nombres + " " + apellidos;
    }
    @Transient
    public String getNombreCompleto(){
        return getNombres() + " " + getApellidos();
    }

    @Override
    public String getLabel() {
        return getNombreCompleto();
    }
    
     @Override
    public String toString() {
        return getLabel();
    }
    
    
}
