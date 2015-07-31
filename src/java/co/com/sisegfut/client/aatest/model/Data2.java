/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.client.aatest.model;

import java.io.Serializable;

/**
 *
 * @author fhurtado
 */
    
    public class Data2 implements Serializable {
//  private static long lastId = 0;

    private String id;// = "" + lastId++ ;
    private String nombreSituacionJuego;
    private double cantidadAnfitrion;
    private double cantidadRival;

    public Data2() {
    }

    public Data2(String id, String nombreSituacionJuego, double cantidadAnfitrion, double cantidadRival) {
        this.id = id;
        this.nombreSituacionJuego = nombreSituacionJuego;
        this.cantidadAnfitrion = cantidadAnfitrion;
        this.cantidadRival = cantidadRival;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombreSituacionJuego() {
        return nombreSituacionJuego;
    }

    public void setNombreSituacionJuego(String nombreSituacionJuego) {
        this.nombreSituacionJuego = nombreSituacionJuego;
    }

    public double getCantidadAnfitrion() {
        return cantidadAnfitrion;
    }

    public void setCantidadAnfitrion(double cantidadAnfitrion) {
        this.cantidadAnfitrion = cantidadAnfitrion;
    }

    public double getCantidadRival() {
        return cantidadRival;
    }

    public void setCantidadRival(double cantidadRival) {
        this.cantidadRival = cantidadRival;
    }

    

    

    }

    
