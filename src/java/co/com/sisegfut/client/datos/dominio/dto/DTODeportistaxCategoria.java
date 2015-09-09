/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.sisegfut.client.datos.dominio.dto;

/**
 *
 * @author fhurtado
 */
public class DTODeportistaxCategoria {
    
    private String fechaNacimiento;
    private String identificacion;
    private String nombres;
    private String apellidos;
    private String posicion;
  

    public DTODeportistaxCategoria() {
    }

    public DTODeportistaxCategoria(String fechaNacimiento, String identificacion, String nombres, String apellidos, String posicion) {
        this.fechaNacimiento = fechaNacimiento;
        this.identificacion = identificacion;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.posicion = posicion;
    }
    
    
    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
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

    public String getPosicion() {
        return posicion;
    }

    public void setPosicion(String posicion) {
        this.posicion = posicion;
    }
        
}
