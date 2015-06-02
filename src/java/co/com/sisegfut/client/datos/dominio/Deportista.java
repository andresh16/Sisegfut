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
@Table(name = "deportista", uniqueConstraints
        = {
            @UniqueConstraint(columnNames = "documento")
        })
public class Deportista extends EntidadPerpetua implements BeanModelTag, Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "gen_deportista")
    @SequenceGenerator(name = "gen_deportista", sequenceName = "deportista_id_seq")
    private Long Id;

    @Column(name = "documento", nullable = false, length = 15)
    private String documento;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tipo_documento", nullable = false)
    private TipoDocumento tipoDocumento;

    @Column(name = "nombres", nullable = false, length = 60)
    private String nombres;

    @Column(name = "apellidos", nullable = false, length = 60)
    private String apellidos;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecha_nacimiento", nullable = false)
    private Date fechaNacimiento;

    //0001707: correo electrónico debe ser opcional al crear usuarios
    @Column(name = "correo_electronico", nullable = false, length = 100)
    private String correoElectronico;

    @Column(name = "direccion", nullable = false, length = 50)
    private String direccion;

    @Column(name = "telefono", nullable = false, length = 15)
    private String telefono;

    @Column(name = "nombre_madre", nullable = false, length = 100)
    private String nombreMadre;

    @Column(name = "nombre_padre", nullable = false, length = 100)
    private String nombrePadre;

    @Column(name = "numero_padre", nullable = true, length = 15)
    private String numeroPadre;

    @Column(name = "numero_madre", nullable = true, length = 15)
    private String numeroMadre;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "categoria", nullable = false)
    private Categoria categoria;

    @Column(name = "numero_camisa", nullable = false, length = 3)
    private String numeroCamisa;

    @Column(name = "barrio", nullable = false, length = 100)
    private String barrio;

    @Column(name = "estatura", nullable = false, length = 5)
    private String estatura;

    @Column(name = "imc", nullable = false, length = 100)
    private String imc;

    @Column(name = "peso", nullable = false, length = 5)
    private String peso;

    @Column(name = "grasa", nullable = true, length = 10)
    private String grasa;

    @Column(name = "genero", nullable = false, length = 50)
    private String genero;

    @Column(name = "estrato", nullable = false, length = 2)
    private String estrato;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "posicion", nullable = false)
    private Posiciones posicion;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "eps", nullable = false)
    private Eps eps;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "nivel_Educativo", nullable = false)
    private NivelEducativo nivelEducativo;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "inst_educativa", nullable = false)
    private InstEducativa instEducativa;

    @Column(name = "jugador_comodin", nullable = false, length = 150)
    private boolean jugadorComodin;

    @GwtTransient
    @Column(name = "foto")
    @JsonIgnore(true)
    @Basic(fetch = FetchType.LAZY)
    private byte[] foto;
    //    @Cascade( { org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.DELETE_ORPHAN })
    @Column(name = "content_type_foto")
    private String contentTypeFoto;

    @Transient
    private String nombreCompleto;

    public Deportista() {
    }

    public Deportista(String nombres, Categoria categoria, boolean jugadorComodin) {
        this.documento = nombres;
        this.tipoDocumento = new TipoDocumento(1L);
        /////////////////
        this.nombres = nombres;
        this.apellidos=" ";
        this.categoria = categoria;
        this.jugadorComodin = jugadorComodin;
        ///////////////
        this.fechaNacimiento = new Date();
        this.correoElectronico = nombres+"@hotmail.com";
        this.direccion = "000";
        this.telefono = "00000000";
        this.nombreMadre = "Madre "+nombres;
        this.nombrePadre = "Padre"+nombres;
        this.numeroCamisa = "00";
        this.barrio = "Sin barrio";
        this.estatura = "00";
        this.imc = "000";
        this.peso = "00";
        this.grasa = "0";
        this.genero = "Masculino";
        this.estrato = "3";
        this.posicion = new Posiciones(3L);
        this.eps = new Eps(3L);
        this.nivelEducativo = new NivelEducativo(3L);
        this.instEducativa = new InstEducativa(1L);
    }

    public Deportista(String documento, TipoDocumento tipoDocumento, String nombres, String apellidos, Date fechaNacimiento, String correoElectronico, String direccion, String telefono, String nombreMadre, String nombrePadre, Categoria categoria, String numeroCamisa, String barrio, String estatura, String imc, String peso, String grasa, String genero, String estrato, Posiciones posicion, Eps eps, NivelEducativo nivelEducativo, InstEducativa instEducativa, boolean jugadorComodin) {

        this.direccion = direccion;
        this.telefono = telefono;
        this.nombreMadre = nombreMadre;
        this.nombrePadre = nombrePadre;
        this.categoria = categoria;
        this.numeroCamisa = numeroCamisa;
        this.barrio = barrio;
        this.estatura = estatura;
        this.imc = imc;
        this.peso = peso;
        this.grasa = grasa;
        this.genero = genero;
        this.estrato = estrato;
        this.posicion = posicion;
        this.eps = eps;
        this.nivelEducativo = nivelEducativo;
        this.instEducativa = instEducativa;
        this.jugadorComodin = jugadorComodin;
    }

    public Deportista(Long IdDep) {
        this.Id = IdDep;
    }

    @Override
    public Long getId() {
        return Id;
    }

    public boolean isJugadorComodin() {
        return jugadorComodin;
    }

    public void setJugadorComodin(boolean jugadorComodin) {
        this.jugadorComodin = jugadorComodin;
    }

    @Override
    public void setId(Long Id) {
        this.Id = Id;
    }

    public String getDocumento() {
        return documento;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
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

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
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

    public String getNombreMadre() {
        return nombreMadre;
    }

    public void setNombreMadre(String nombreMadre) {
        this.nombreMadre = nombreMadre;
    }

    public String getNombrePadre() {
        return nombrePadre;
    }

    public String getNumeroCamisa() {
        return numeroCamisa;
    }

    public void setNumeroCamisa(String numeroCamisa) {
        this.numeroCamisa = numeroCamisa;
    }

    public void setNombrePadre(String nombrePadre) {
        this.nombrePadre = nombrePadre;
    }

    public String getNumeroPadre() {
        return numeroPadre;
    }

    public void setNumeroPadre(String numeroPadre) {
        this.numeroPadre = numeroPadre;
    }

    public String getNumeroMadre() {
        return numeroMadre;
    }

    public void setNumeroMadre(String numeroMadre) {
        this.numeroMadre = numeroMadre;
    }

    public String getBarrio() {
        return barrio;
    }

    public void setBarrio(String barrio) {
        this.barrio = barrio;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public String getEstatura() {
        return estatura;
    }

    public void setEstatura(String estatura) {
        this.estatura = estatura;
    }

    public Posiciones getPosicion() {
        return posicion;
    }

    public void setPosicion(Posiciones posicion) {
        this.posicion = posicion;
    }

    public String getImc() {
        return imc;
    }

    public void setImc(String imc) {
        this.imc = imc;
    }

    public String getPeso() {
        return peso;
    }

    public void setPeso(String peso) {
        this.peso = peso;
    }

    public String getGrasa() {
        return grasa;
    }

    public void setGrasa(String grasa) {
        this.grasa = grasa;
    }

    public String getEstrato() {
        return estrato;
    }

    public void setEstrato(String estrato) {
        this.estrato = estrato;
    }

    public Eps getEps() {
        return eps;
    }

    public void setEps(Eps eps) {
        this.eps = eps;
    }

    public NivelEducativo getNivelEducativo() {
        return nivelEducativo;
    }

    public void setNivelEducativo(NivelEducativo nivelEducativo) {
        this.nivelEducativo = nivelEducativo;
    }

    public InstEducativa getInstEducativa() {
        return instEducativa;
    }

    public void setInstEducativa(InstEducativa instEducativa) {
        this.instEducativa = instEducativa;
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

    public String getNombreCompleto() {
        return getLabel();
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    @Override
    public String getLabel() {
        return getNombres() + " " + getApellidos();
    }

    @Override
    public String toString() {
        return getLabel();
    }

}
