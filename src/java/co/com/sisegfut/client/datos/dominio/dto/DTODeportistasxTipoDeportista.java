/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.client.datos.dominio.dto;

/**
 *
 * @author ManuelAlejandro
 */
public class DTODeportistasxTipoDeportista {
    
    private String identificacion;
    private String nombres;
    private String apellidos;
    private String tipoDeportista;

    public DTODeportistasxTipoDeportista() {
    }

    public DTODeportistasxTipoDeportista(String identificacion, String nombres, String apellidos, String tipoDeportista) {
        this.identificacion = identificacion;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.tipoDeportista = tipoDeportista;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
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

    public String getTipoDeportista() {
        return tipoDeportista;
    }

    public void setTipoDeportista(String tipoDeportista) {
        this.tipoDeportista = tipoDeportista;
    }
    
    
    
}
