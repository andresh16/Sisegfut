/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.client.adminCRUDgral;

import co.com.sisegfut.client.util.PanelErrores;
import co.com.sisegfut.client.util.Resources;
import co.com.sisegfut.client.util.ListenerGuardado;
import co.com.sisegfut.client.util.PanelExito;
import co.com.sisegfut.client.aaI18N.Main;
import co.com.sisegfut.client.entidades.RespuestaRPC;
import co.com.sisegfut.client.util.combox.ComboBoxEntidades;
import co.com.sisegfut.client.util.rpc.RPCMaestroAsync;
import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.IconButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.ToolButton;
import com.extjs.gxt.ui.client.widget.form.FormButtonBinding;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import java.io.Serializable;

/**
 *
 * @author josorio
 */
public abstract class VentanaRecuperar<T extends Serializable> extends Window implements ListenerGuardado{
//    private PanelInformativo pnlInformativo;
    private PanelErrores pnlErrores;
    private PanelExito pnlExito;
    /** Contiene los textos a presentar en la interfaz web segun el idioma*/
    protected Main myConstants;
    private FormPanel formulario;
    private FormData formData;
    protected ComboBoxEntidades<T> cmbEntidad;
    /**
     * ventana modal para el mensaje de espera
     */
    private MessageBox boxWait;
    private ListenerGuardado listenerGuardado;

    /**
     * Constructor
     * @param listenerGuardado 
     */
    public VentanaRecuperar(ListenerGuardado listenerGuardado, String titulo) {
        myConstants = (Main) GWT.create(Main.class);
        this.listenerGuardado = listenerGuardado;
        
        pnlErrores = new PanelErrores();
        pnlExito = new PanelExito();        
        
        setWidth(470);          
        setPlain(true);  
        setModal(true);  
        setBlinkModal(true);  
        setHeading("Crear");
  
        setResizable(false);
        setIcon(Resources.ICONS.iconoUsuario()); 
        
        setHeading(titulo); 
        
        formData = new FormData("-20");
        formulario = new FormPanel();
        formulario.setHeaderVisible(false); 
        formulario.setFrame(true); 
        
        cmbEntidad = crearComboInactivos();     
        formulario.add(cmbEntidad, formData);
        
        Button btnAceptar = new Button("Reactivar",Resources.ICONS.iconoOk(),listenerCambio());
        Button btnLimpiar = new Button("Cancelar", Resources.ICONS.iconoCancelar(), listenerLimpiar());

        addButton(btnAceptar);
        addButton(btnLimpiar);

        setButtonAlign(Style.HorizontalAlignment.CENTER);

        //Monitors the valid state of a form and enabled / disabled all buttons.
        FormButtonBinding binding = new FormButtonBinding(formulario);
        binding.addButton(btnAceptar);
        
//        add(pnlInformativo); 
        add(pnlErrores);
        add(pnlExito);
        add(formulario); 
        
        //Agrego boton al panel principal que permite desplegar la ayuda.
        getHeader().addTool(new ToolButton("x-tool-help", new SelectionListener<IconButtonEvent>() {

            @Override
            public void componentSelected(IconButtonEvent ce) {
                abrirVentana(getAyuda());
            }
        }));
    }
    
    /**
     * Para crear el combo de entidades inactivas
     * @return 
     */
    protected abstract ComboBoxEntidades<T> crearComboInactivos();
    
    /**
     * Para retornar la ayuda de la ventana.
     * @return 
     */
    protected abstract String getAyuda();    
    
    
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
     * Escucha el boton limpiar
     *
     * @return
     */
    private SelectionListener<ButtonEvent> listenerLimpiar() {
        return new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                cancelarGuardado();
            }
        };
    }
    
    /**
     * Escucha el boton de login
     * @return 
     */
    private SelectionListener<ButtonEvent> listenerCambio(){        
        
        return new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {                
                pnlErrores.setVisible(false);
                pnlExito.setVisible(false);
                if (formulario.isValid()) {
                    boxWait = MessageBox.wait("Progreso",
                            "Procesando los datos, por favor espere...", "Procesando...");
                    listenerGuardado.getRPCMaestroAsync().reactivarEntidad(
                            cmbEntidad.getEntidadElegida().getId(), crearCallBackReactivado() );
                } else {
                    pnlErrores.limpiar();
                    pnlErrores.aniadir("Debe seleccionar la entidad "
                            + "a reactivar");
                    pnlErrores.setVisible(true);
                }
            }
        };
    }    


    public AsyncCallback<RespuestaRPC<T>> crearCallBackReactivado()
    {
        return new AsyncCallback<RespuestaRPC<T>>()
        {
            /**
             * Método a ejecutar en caso de error en el llamado
             * @param arg0
             */
            public void onFailure(Throwable arg0) {
                boxWait.close();
                pnlErrores.limpiar();
                pnlErrores.aniadir("Se presentó un error de comunicación, por favor "
                        + "inténtelo de nuevo");
                pnlErrores.setVisible(true);
            }

            /**
             * En caso de éxito en el llamado
             * @param respuesta RespuestaRPC retornada por el método remoto
             */
            public void onSuccess(RespuestaRPC<T> respuesta)
            {
                boxWait.close();
                pnlExito.setVisible(false);
                pnlErrores.setVisible(false);

                if (respuesta.getResultado() == RespuestaRPC.RESULTADO_OK)
                {
                    pnlExito.definirTexto("Entidad reactivada correctamente");
                    pnlExito.setVisible( true );
                    finalizaGuardado();
                    //refresco el combobox
                    cmbEntidad.setValue(null); 
                    cmbEntidad.recargar();
                } 
                else
                {
                    pnlErrores.limpiar();
                    pnlErrores.setVisible(true);
                    pnlErrores.aniadir(respuesta.getDescripcionError());
                }
            }
        };
    }
    
    protected void recargar(){
        cmbEntidad.recargar();
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
    
    private void cerrarVentana(){
        this.hide();
    }

    @Override
    public RPCMaestroAsync getRPCMaestroAsync() {
        return listenerGuardado.getRPCMaestroAsync();
    }
}
