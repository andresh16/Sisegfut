/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.client.adminCRUDgral;


import co.com.sisegfut.client.aaI18N.Main;
import co.com.sisegfut.client.adminCRUDgral.FormularioCRUD;
import co.com.sisegfut.client.datos.dominio.Usuarios;
import co.com.sisegfut.client.util.ListenerGuardado;
import co.com.sisegfut.client.util.PanelInformativo;
import co.com.sisegfut.client.util.Resources;
import co.com.sisegfut.client.util.rpc.RPCMaestroAsync;
import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.data.BeanModel;
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
public class VentanaModificar  extends Window implements ListenerGuardado{
//    private PanelInformativo pnlInformativo;
    private FormularioCRUD formularioEntidad;
    /** Contiene los textos a presentar en la interfaz web segun el idioma*/
    private Main myConstants;
    private ListenerGuardado listenerGuardado;

    /**
     * Constructor
     * @param listenerGuardado 
     */
    public VentanaModificar(ListenerGuardado listenerGuardado, FormularioCRUD formularioEntidad,
            String titulo, final String ayuda) {
        myConstants = (Main) GWT.create(Main.class);
        
        setWidth(470);   
        this.listenerGuardado=listenerGuardado;
        setPlain(true);  
        setModal(true);  
        setBlinkModal(true);  
        setHeading("Modificar"); 
        setResizable(false);
        setIcon(Resources.ICONS.iconoCrear()); 
        
        setHeading(titulo);
                
        
        this.formularioEntidad = formularioEntidad; 
        formularioEntidad.setListenerGuardado(this); 
        
//        add(pnlInformativo); 
        add(formularioEntidad); 
        
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
    
    /**
     * usa el benamodel de la entidad para enlazarla con el formulario
     * @param entidad 
     */
    protected void setearEntidad(BeanModel entidad){
        formularioEntidad.setBind(entidad); 
    }
    /**
     * Setea de forma manual la entidad en el formulario para su edicion
     * @param entidad 
     */
    public void setEntidadManual(Usuarios entidad){
    
        setHeading("<h4>Actualizar datos </h4> "+entidad.getLabel()); 
        formularioEntidad.setEntidadManual(entidad);
         
        ///setHeading("Actualización de datos");
        //setHeight(420); 
    }
    
    public boolean permisoGuardado() {
        return true;
    }

    public void finalizaGuardado() {
        listenerGuardado.finalizaGuardado();
        cerrarVentana();
    }

    public void cancelarGuardado() {
        //listenerGuardado.cancelarGuardado();
        cerrarVentana();
    }
    
    @Override
    public RPCMaestroAsync getRPCMaestroAsync() {
        return listenerGuardado.getRPCMaestroAsync();
    }
    
    private void cerrarVentana(){
        this.hide();
    }
}
