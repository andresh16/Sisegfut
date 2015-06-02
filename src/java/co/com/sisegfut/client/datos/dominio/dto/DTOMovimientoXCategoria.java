/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.client.datos.dominio.dto;

import java.io.Serializable;

/**
 *
 * @author fhurtado
 */
public class DTOMovimientoXCategoria implements Serializable{
    
    private String fechaMovimiento;
    private String tipoMovimiento;
    private String nombreCuenta;
    private String ingreso;
    private String egreso;
    private String descripcion;

    public DTOMovimientoXCategoria(String fechaMovimiento, String tipoMovimiento, String nombreCuenta, String ingreso, String egreso, String descripcion) {
        this.fechaMovimiento = fechaMovimiento;
        this.tipoMovimiento = tipoMovimiento;
        this.nombreCuenta = nombreCuenta;
        this.ingreso = ingreso;
        this.egreso = egreso;
        this.descripcion = descripcion;
    }

    public DTOMovimientoXCategoria() {
    }

    public String getFechaMovimiento() {
        return fechaMovimiento;
    }

    public void setFechaMovimiento(String fechaMovimiento) {
        this.fechaMovimiento = fechaMovimiento;
    }

    public String getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(String tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
    }

    public String getNombreCuenta() {
        return nombreCuenta;
    }

    public void setNombreCuenta(String nombreCuenta) {
        this.nombreCuenta = nombreCuenta;
    }

    public String getIngreso() {
        return ingreso;
    }

    public void setIngreso(String ingreso) {
        this.ingreso = ingreso;
    }

    public String getEgreso() {
        return egreso;
    }

    public void setEgreso(String egreso) {
        this.egreso = egreso;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    
}
