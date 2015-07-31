/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.client.datos.dominio.dto;

import co.com.sisegfut.client.aatest.model.Data2;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fhurtado
 */
public class DTOSituacionJuegoCompGraficas {
    
    
      private static final String[] situacionesJuego = new String[] {
       "Falta Z1", "Falta Z2", "Falta Z3", "Recuperación Z1", "Recuperación Z2", "Recuperación Z3","Tiro Libre Z1","Tiro Libre Z2","Tiro Libre Z3", "Centrao Lateral",
      "Entradas Erradas", "Fuera Lugar", "Opción Gol", "Penalty","Remate","Tiro Esquina", "Tarjeta Roja","Tarjeta Amarilla"};

  

  public static List<Data2> getSituacionesCompetenciaAleatorio(int size, double min, double scale) {
    List<Data2> data = new ArrayList<Data2>();
    for (int i = 0; i < size; i++) {
      data.add(new Data2(i+"",situacionesJuego[i % situacionesJuego.length], Math.floor(Math.max(Math.random() * scale, min)), Math.floor(Math.max(Math.random() * scale, min))));
    }
    return data;
  }
  public static List<Data2> getSituacionesCompetenciaVacio(int size, double min, double scale) {
    List<Data2> data = new ArrayList<Data2>();
    for (int i = 0; i < size; i++) {
      data.add(new Data2(i+"",situacionesJuego[i % situacionesJuego.length], 0, 0));
    }
    return data;
  }
  
  
    
    
}
