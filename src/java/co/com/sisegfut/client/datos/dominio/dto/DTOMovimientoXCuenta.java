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
public class DTOMovimientoXCuenta implements Serializable{
    
    private String fechaMovimiento;
    private String tipoMovimiento;
    private String categoria;
    private String ingreso;
    private String egreso;
    private String descripcion;

    public DTOMovimientoXCuenta(String fechaMovimiento, String tipoMovimiento, String categoria, String ingreso, String egreso, String descripcion) {
        this.fechaMovimiento = fechaMovimiento;
        this.tipoMovimiento = tipoMovimiento;
        this.categoria = categoria;
        this.ingreso = ingreso;
        this.egreso = egreso;
        this.descripcion = descripcion;
    }


    public DTOMovimientoXCuenta() {
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

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
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
