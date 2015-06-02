/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.client.aatest.model;

import com.extjs.gxt.ui.client.data.BaseModelData;

/**
 *
 * @author fhurtado
 */
public class Tarjeta extends BaseModelData{

    public Tarjeta(String color) {
        setColor(color);
    }

    private void setColor(String color) {
       set("color",color);
    }
    
    public String getColor() {
    return get("color");
  }
    
}
