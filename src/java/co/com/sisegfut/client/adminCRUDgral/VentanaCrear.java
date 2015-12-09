/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.client.adminCRUDgral;

import co.com.sisegfut.client.aaI18N.Main;
import co.com.sisegfut.client.administracion.usuarios.FormularioUsuarios;
import co.com.sisegfut.client.administracion.usuarios.FormularioUsuarios;
import co.com.sisegfut.client.util.ListenerGuardado;
import co.com.sisegfut.client.util.PanelInformativo;
import co.com.sisegfut.client.util.Resources;
import co.com.sisegfut.client.util.rpc.RPCMaestroAsync;
import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.event.IconButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.ToolButton;
import com.google.gwt.core.client.GWT;

/**
 *
 * @author fhurtado
 */
public class VentanaCrear extends Window implements ListenerGuardado{

//    private PanelInformativo pnlInformativo;
    private FormularioCRUD formulario;
    /** Contiene los textos a presentar en la interfaz web segun el idioma*/
    private Main myConstants;
    private ListenerGuardado listenerGuardado;
    
    public VentanaCrear(ListenerGuardado listenerGuardado, FormularioCRUD formularioEntidad,
            String titulo, final String ayuda) {
        myConstants = (Main) GWT.create(Main.class);
        this.listenerGuardado=listenerGuardado;
        
        setWidth(470);   
        setPlain(true);  
        setModal(true);  
        setBlinkModal(true);  
        setHeading("Crear"); 

        setResizable(false);
        setIcon(Resources.ICONS.iconoCrear()); 
        
        setHeading(titulo);
        
        formulario = formularioEntidad;
        formulario.setListenerGuardado(this); 
         
        add(formulario); 
        
        //Agrego boton al panel principal que permite desplegar la ayuda.
        getHeader().addTool(new ToolButton("x-tool-help", new SelectionListener<IconButtonEvent>() {

            @Override
            public void componentSelected(IconButtonEvent ce) {
                abrirVentana(ayuda);
            }
        }));
    }
    
    /**
     * Abre ventana de ayuda. 
     */
    private void abrirVentana(String texto) {
        final Dialog simple = new Dialog();  
        simple.setHeading("Ayuda");  
        simple.setButtons(Dialog.OK);  
        simple.setBodyStyleName("pad-text");  
        simple.addText(texto);  
        simple.getItem(0).getFocusSupport().setIgnore(true);  
        simple.setScrollMode(Style.Scroll.AUTO);  
        simple.setHideOnButtonClick(true); 
        simple.setWidth(550); 
        //simple.setSize(550, 255);
        
        simple.show();                 
    }

    public boolean permisoGuardado() {
        return true;
    }

    public void finalizaGuardado() {
        listenerGuardado.finalizaGuardado();
        cerrarVentana();
    }

    public void cancelarGuardado() {
        listenerGuardado.cancelarGuardado();
        cerrarVentana();
    }
    
    private void cerrarVentana(){
        this.hide();
    }

    @Override
    public RPCMaestroAsync getRPCMaestroAsync() {
        return listenerGuardado.getRPCMaestroAsync();
    }
    
    
}
