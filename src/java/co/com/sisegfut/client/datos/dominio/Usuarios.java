package co.com.sisegfut.client.datos.dominio;


import com.extjs.gxt.ui.client.data.BeanModelTag;
import com.google.gwt.user.client.rpc.GwtTransient;
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
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import org.codehaus.jackson.annotate.JsonIgnore;

/**
 * @author anfeh_000
 */
@Entity
@Table(name = "usuario", uniqueConstraints =
        {
            @UniqueConstraint(columnNames = "correo_electronico"),
            @UniqueConstraint(columnNames = "documento")    
        })
public class Usuarios extends EntidadPerpetua implements BeanModelTag {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "gen_usuario")
    @SequenceGenerator(name = "gen_usuario", sequenceName = "usuario_id_seq")
    private Long id;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "rol", nullable = false)
    private Rol rol;
    @Column(name = "documento", nullable = false, length = 50)
    private String documento;
    @Column(name = "clave", nullable = false, length = 100)
    private String clave;
    @Column(name = "nombres", nullable = false, length = 150)
    private String nombres;
    @Column(name = "apellidos", nullable = false, length = 150)
    private String apellidos;
    
    //0001707: correo electrónico debe ser opcional al crear usuarios
    @Column(name = "correo_electronico", unique = true, length = 150)
    private String correoElectronico;
    @Column(name = "llave_cambio_clave", length = 50)
    private String llaveCambioClave;
    
    @GwtTransient
    @Column(name = "foto")
    @JsonIgnore(true)
    @Basic(fetch = FetchType.LAZY)
    private byte[] foto;
    //    @Cascade( { org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.DELETE_ORPHAN })
    @Column(name = "content_type_foto")
    private String contentTypeFoto;
    
    

    /**
     * Setea en nulo los atributos del objeto. este método se invoca para anular la
     * sesión del usuario
     */
    public void resetear() {
        this.id = null;
        this.rol = null;
        this.documento = null;
        this.clave = null;
        this.nombres = null;
        this.apellidos = null;
        this.correoElectronico = null;
        this.llaveCambioClave = null;
        this.fechaInactivado = null;
        this.clave = null;
    }
    

    public String crearNombreCompleto() {
        return nombres + " " + apellidos;
    }

    public Usuarios() {
    }

    public Usuarios(Long id, Rol rol, String documento, String clave, String nombres, String apellidos, String correoElectronico) {
        this.id = id;
        this.rol = rol;
        this.documento = documento;
        this.clave = clave;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.correoElectronico = correoElectronico;
    }

    
    @Override
    public Long getId() {
        return this.id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

        public Rol getRol() {
        return this.rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }    

        public String getDocumento() {
        return this.documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

        public String getClave() {
        return this.clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

        public String getNombres() {
        return this.nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

        public String getApellidos() {
        return this.apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    @Transient
    public String getNombreCompleto(){
        return getNombres() + " " + getApellidos();
    }

        public String getCorreoElectronico() {
        return this.correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    @Transient
    public boolean isAdminClub() {
        return this.getRol().getId() == Rol.ROL_ADMINISTRADOR_CLUB;
    }
    @Transient
    public boolean isAdminEntrenador() {
        return this.getRol().getId() == Rol.ROL_ADMINISTRADOR_ENTRENADOR;
    }
    

        public String getLlaveCambioClave() {
        return this.llaveCambioClave;
    }

    public void setLlaveCambioClave(String llaveCambioClave) {
        this.llaveCambioClave = llaveCambioClave;
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

    
//        public Date getFechaInactivado() {
//        return this.fechaInactivado;
//    }
//
//    public void setFechaInactivado(Date fechaInactivado) {
//        this.fechaInactivado = fechaInactivado;
//    }


    public void copiarDatos(Usuarios usuario) {
        this.id = usuario.getId();
        this.rol = usuario.getRol();
        this.documento = usuario.getDocumento();
        this.clave = usuario.getClave();
        this.nombres = usuario.getNombres();
        this.apellidos = usuario.getApellidos();
        this.correoElectronico = usuario.getCorreoElectronico();
        this.llaveCambioClave = usuario.getLlaveCambioClave();
        this.fechaInactivado = usuario.getFechaInactivado();
    }

    @Override
    public String toString() {
        return getLabel();
    }

    @Transient
    @Override
    public String getLabel() {
        return getNombreCompleto();
    }
    public String getLabel2() {
        return getNombres() + "\n " + getApellidos();
    }
    
    
    
}
