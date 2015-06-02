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
 * @author Camilo
 */
public class DTOExperiencia implements BeanModelTag, Serializable{
    
    private Long idExperiencia;
    private Long idCargo;
    private String cargo;
    private String empresas;
    private String tiempoLaborado;

    public Long getIdExperiencia() {
        return idExperiencia;
    }

    public void setIdExperiencia(Long idExperiencia) {
        this.idExperiencia = idExperiencia;
    }

    public Long getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(Long idCargo) {
        this.idCargo = idCargo;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getEmpresas() {
        return empresas;
    }

    public void setEmpresas(String empresas) {
        this.empresas = empresas;
    }

    public String getTiempoLaborado() {
        return tiempoLaborado;
    }

    public void setTiempoLaborado(String tiempoLaborado) {
        this.tiempoLaborado = tiempoLaborado;
    }
    
}
