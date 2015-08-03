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
public class DTOReporteAsistenciaXMes implements BeanModelTag, Serializable{
    
    private String nombreDeportista;
    private String diasAsistenciaTotal;
    private String porcentajeAsistenciaTotal;

    public DTOReporteAsistenciaXMes() {
    }

    public String getNombreDeportista() {
        return nombreDeportista;
    }

    public void setNombreDeportista(String nombreDeportista) {
        this.nombreDeportista = nombreDeportista;
    }

    public String getDiasAsistenciaTotal() {
        return diasAsistenciaTotal;
    }

    public void setDiasAsistenciaTotal(String diasAsistenciaTotal) {
        this.diasAsistenciaTotal = diasAsistenciaTotal;
    }

    public String getPorcentajeAsistenciaTotal() {
        return porcentajeAsistenciaTotal;
    }

    public void setPorcentajeAsistenciaTotal(String porcentajeAsistenciaTotal) {
        this.porcentajeAsistenciaTotal = porcentajeAsistenciaTotal;
    }
    

    
    
}
