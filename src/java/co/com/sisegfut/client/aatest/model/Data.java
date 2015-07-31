/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.client.aatest.model;

import java.io.Serializable;

/**
 *
 * @author Andres Hurtado
 */
public class Data implements Serializable {
//  private static long lastId = 0;

    private String id;// = "" + lastId++ ;
    private String name;
    private double cantidadAnfitrion;
    private double data2;
    private double data3;
    private double data4;
    private double data5;
    private double data6;
    private double data7;
    private double data8;
    private double data9;

    /**
     * Constructor. 
     */
    public Data() {
    }    
    

    /**
     * Constructor
     *
     * @param id
     * @param name
     * @param data1
     */
    public Data(String id, String name, double data1) {
        this.id = id;
        this.name = name;
        this.cantidadAnfitrion = data1;
    }
    
    public Data(String id, String name, double cantidadAnfitrion, double data2) {
        this.id=id;
        this.name = name;
        this.cantidadAnfitrion = cantidadAnfitrion;
        this.data2 = data2;
    }

    /**
     * Constructor
     *
     * @param id
     * @param name
     * @param data1
     * @param data2
     * @param data3
     * @param data4
     * @param data5
     * @param data6
     * @param data7
     * @param data8
     * @param data9
     */
    public Data(String id, String name, double data1, double data2, double data3, double data4, double data5, double data6,
            double data7, double data8, double data9) {
        super();
        this.id = id;
        this.name = name;
        this.cantidadAnfitrion = cantidadAnfitrion;
        this.data2 = data2;
        this.data3 = data3;
        this.data4 = data4;
        this.data5 = data5;
        this.data6 = data6;
        this.data7 = data7;
        this.data8 = data8;
        this.data9 = data9;
    }

    
    
    public long getCantidadAnfitrion1Int() {
        return (long) cantidadAnfitrion;
    }

    public double getData2() {
        return data2;
    }

    public double getData3() {
        return data3;
    }

    public double getData4() {
        return data4;
    }

    public double getData5() {
        return data5;
    }

    public double getData6() {
        return data6;
    }

    public double getData7() {
        return data7;
    }

    public double getData8() {
        return data8;
    }

    public double getData9() {
        return data9;
    }

    public String getName() {
        return name;
    }

    public double getCantidadAnfitrion() {
        return cantidadAnfitrion;
    }

    public void setCantidadAnfitrion(double cantidadAnfitrion) {
        this.cantidadAnfitrion = cantidadAnfitrion;
    }


    public void setData2(double data2) {
        this.data2 = data2;
    }

    public void setData3(double data3) {
        this.data3 = data3;
    }

    public void setData4(double data4) {
        this.data4 = data4;
    }

    public void setData5(double data5) {
        this.data5 = data5;
    }

    public void setData6(double data6) {
        this.data6 = data6;
    }

    public void setData7(double data7) {
        this.data7 = data7;
    }

    public void setData8(double data8) {
        this.data8 = data8;
    }

    public void setData9(double data9) {
        this.data9 = data9;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }
}
