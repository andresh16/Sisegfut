/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.client.util;

import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.button.Button;

/**
 * Boton con la funcion especifica de abrir un dialogo con un texto de ayuda.
 * @author 
 */
public class BotonAyudaH extends Button{
    
    private String textoAyuda;

    /**
     * Constructor
     * @param textoAyuda 
     */
    public BotonAyudaH(String textoAyuda) {
        super("", Resources.ICONS.iconoAyuda());
        setIconAlign(Style.IconAlign.TOP); 
        this.textoAyuda=textoAyuda;
        addSelectionListener(listenerAyuda()); 
    }
    
    /**
     * Escucha el boton ayuda para abrir la ventana
     * @return 
     */
    private SelectionListener<ButtonEvent> listenerAyuda(){
        return new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                abrirVentana();
            }
        };
    }
    
    /**
     * Abre la ventana con el texto de la ayuda
     */
    private void abrirVentana() {
        final Dialog simple = new Dialog();
        simple.setHeading("Ayuda");
        simple.setButtons(Dialog.OK);
        simple.setBodyStyleName("pad-text");
        simple.addText(textoAyuda);
        simple.setWidth(600);
        //simple.getItem(0).getFocusSupport().setIgnore(true);
        simple.setScrollMode(Style.Scroll.AUTO);
        simple.setHideOnButtonClick(true);
        simple.setModal(true); 
        
        simple.show();
    }
    
}
