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
public class DTODeportistasEstratoXCategoria {
    
    private String fechaNacimiento;
    private String identificacion;
    private String nombres;
    private String apellidos;
    private String estrato;

    public DTODeportistasEstratoXCategoria() {
    }

    public DTODeportistasEstratoXCategoria(String fechaNacimiento, String identificacion, String nombres, String apellidos, String estrato) {
        this.fechaNacimiento = fechaNacimiento;
        this.identificacion = identificacion;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.estrato = estrato;
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

    public String getEstrato() {
        return estrato;
    }

    public void setEstrato(String estrato) {
        this.estrato = estrato;
    }
    
    
}
