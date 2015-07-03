/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.client.datos.dominio.dto;

import com.extjs.gxt.ui.client.data.BeanModelTag;
import java.io.Serializable;

/**
 *
 * @author fhurtado
 */
public class DTOAsistencia implements BeanModelTag, Serializable{
    
    
    private Long idAsistencia;
    private Long idControlAsistencia;
    private Long idDeportista;
    private boolean asistio;
    private String falto;
    private String nombreDeportista;
    private String posicion;
    private String telefono;
    private String documento;

    public DTOAsistencia() {
    }

    public Long getIdAsistencia() {
        return idAsistencia;
    }

    public void setIdAsistencia(Long idAsistencia) {
        this.idAsistencia = idAsistencia;
    }

    public Long getIdControlAsistencia() {
        return idControlAsistencia;
    }

    public void setIdControlAsistencia(Long idControlAsistencia) {
        this.idControlAsistencia = idControlAsistencia;
    }

    public boolean isAsistio() {
        return asistio;
    }

    public void setAsistio(boolean asistio) {
        this.asistio = asistio;
    }

    public String getFalto() {
        return falto;
    }

    public void setFalto(String falto) {
        this.falto = falto;
    }


    public String getNombreDeportista() {
        return nombreDeportista;
    }

    public void setNombreDeportista(String nombreDeportista) {
        this.nombreDeportista = nombreDeportista;
    }

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }
    
    
    
    
}
