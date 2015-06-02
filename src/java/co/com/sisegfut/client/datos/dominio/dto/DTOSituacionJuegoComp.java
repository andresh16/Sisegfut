/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.sisegfut.client.datos.dominio.dto;

import com.extjs.gxt.ui.client.data.BaseModel;

/**
 *
 * @author Andres Hurtado
 */
    
public class DTOSituacionJuegoComp extends BaseModel {

    public DTOSituacionJuegoComp() {
    }

    public DTOSituacionJuegoComp(String situacion, String primertiempolocal, Integer cantidadprimertiempolocal, String segundotiempolocal, Integer cantidadsegundotiempolocal,
    String primertiemporival, Integer cantidadprimertiemporival, String segundotiemporival,Integer cantidadsegundotiemporival) {
        set("situacion", situacion);
        
        set("primertiempolocal", primertiempolocal);
        set("cantidadprimertiempolocal", cantidadprimertiempolocal);
        
        set("segundotiempolocal", segundotiempolocal);
        set("cantidadsegundotiempolocal", cantidadsegundotiempolocal);
        
        set("primertiemporival", primertiemporival);
        set("cantidadprimertiemporival", cantidadprimertiemporival);
        
        set("segundotiemporival", segundotiemporival);
        set("cantidadsegundotiemporival", cantidadsegundotiemporival);
    }

    public String getSituacion() {
        return (String) get("situacion");
    }

    public String getPrimertiempolocal() {
        return (String) get("primertiempolocal");
    }
    
    public Integer getCantidadprimertiempolocal() {
        return (Integer) get("cantidadprimertiempolocal");
    }
    
     public String getSegundotiempolocal() {
        return (String) get("segundotiempolocal");
    }
    
    public Integer getCantidadsegundotiempolocal() {
        return (Integer) get("cantidadsegundotiempolocal");
    }
    
     public String getPrimertiemporival() {
        return (String) get("primertiemporival");
    }
    
    public Integer getCantidadprimertiemporival() {
        return (Integer) get("cantidadprimertiemporival");
    }
    
    
     public String getSegundotiemporival() {
        return (String) get("primertiempolocal");
    }
    
    public Integer getCantidadsegundotiemporival() {
        return (Integer) get("cantidadsegundotiemporival");
    }
    
    
    public void setCantidadprimertiempolocal(Integer cantidad1L) {
    set("cantidadprimertiempolocal", cantidad1L);
  }

  

}

