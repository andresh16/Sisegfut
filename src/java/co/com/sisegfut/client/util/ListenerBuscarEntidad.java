/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.client.util;

import com.extjs.gxt.ui.client.data.BeanModel;

/**
 * @author 
 */
public interface ListenerBuscarEntidad<T extends Object> {
    public void onFind(T entidad);
    
    public void onFind(Long idEntidad); 
    
    public void onFind(BeanModel beanEntidad);
    
    public void onCancelFind();
}
