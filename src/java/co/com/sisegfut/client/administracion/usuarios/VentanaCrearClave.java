/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.client.administracion.usuarios;

import co.com.sisegfut.client.util.PanelErrores;
import co.com.sisegfut.client.util.Resources;
import co.com.sisegfut.client.util.PanelExito;
import co.com.sisegfut.client.aaI18N.Main;
import co.com.sisegfut.client.datos.dominio.Usuarios;
import co.com.sisegfut.client.entidades.RespuestaRPC;
import co.com.sisegfut.client.util.rpc.RPCAdminUsuarios;
import co.com.sisegfut.client.util.rpc.RPCAdminUsuariosAsync;
import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.core.El;
import com.extjs.gxt.ui.client.core.XDOM;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.IconButtonEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.ComponentPlugin;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.ToolButton;
import com.extjs.gxt.ui.client.widget.form.FormButtonBinding;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.tips.ToolTipConfig;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

/**
 *
 * @
 */
public class VentanaCrearClave extends Window implements AsyncCallback<RespuestaRPC<Integer>>{
//    private PanelInformativo pnlInformativo;
    private PanelErrores pnlErrores;
    private PanelExito pnlExito;
    /** Contiene los textos a presentar en la interfaz web segun el idioma*/
    private Main myConstants;
    private FormPanel formulario;
    private FormData formData;
    private TextField<String> txtUsuario;
    private TextField<String> txtPasswordNuevo;
    private TextField<String> txtPasswordConfirmado;
    /** ventana modal para el mensaje de espera */
    private MessageBox boxWait;
    private Usuarios usuario;
    public static final String CREAR = "Crear contraseña";
    public static final String CAMBIAR = "Cambiar contraseña";

    public VentanaCrearClave(String encabezado) {
        myConstants = (Main) GWT.create(Main.class);
        
        pnlErrores = new PanelErrores();
        pnlExito = new PanelExito();        
        setHeading(encabezado);
        setWidth(470);
        setPlain(true);  
        setModal(true);  
        setBlinkModal(true);  
        setIcon(Resources.ICONS.iconoClave()); 
        
           ComponentPlugin plugin = new ComponentPlugin() {
            public void init(Component component) {
                component.addListener(Events.Render, new Listener<ComponentEvent>() {
                    public void handleEvent(ComponentEvent be) {
                        El elem = be.getComponent().el().findParent(".x-form-element", 3);
                        // should style in external CSS  rather than directly  
                        elem.appendChild(XDOM.create("<div style='color: #615f5f;padding: 1 0 2 0px;'>" + be.getComponent().getData("text") + "</div>"));
                    }
                });
            }
        };
        
//        pnlInformativo = new PanelInformativo("<h4>Para asignar una clave a usuario...</h4>", myConstants.ayudaNewClaveUsuario()); 
        
        formData = new FormData("-20");
        formulario = new FormPanel();
        formulario.setHeaderVisible(false); 
        formulario.setFrame(true); 
        
        txtUsuario = new TextField<String>();
        txtUsuario.setMaxLength(100); 
        txtUsuario.setToolTip(new ToolTipConfig("Usuario", "Descripcion del usuario"));
        txtUsuario.setFieldLabel("Usuario");
        txtUsuario.setAllowBlank(false);
        txtUsuario.setEnabled(false);         
        formulario.add(txtUsuario, formData);

        txtPasswordNuevo = new TextField<String>();
        txtPasswordNuevo.setMaxLength(100); 
        txtPasswordNuevo.addPlugin(plugin);
        txtPasswordNuevo.setData("text", "Campo sensible a las Mayusculas");
        txtPasswordNuevo.setToolTip(new ToolTipConfig("Contraseña nueva", "Digite la nueva contraseña"));
        txtPasswordNuevo.setFieldLabel("Contraseña nueva");
        txtPasswordNuevo.setAllowBlank(false);
        txtPasswordNuevo.setPassword(true);
        txtPasswordNuevo.getFocusSupport().setPreviousId(getButtonBar().getId());
        formulario.add(txtPasswordNuevo, formData);
        
        txtPasswordConfirmado = new TextField<String>();
        txtPasswordConfirmado.setMaxLength(100); 
        txtPasswordConfirmado.addPlugin(plugin);
        txtPasswordConfirmado.setData("text", "Campo sensible a las Mayusculas");
        txtPasswordConfirmado.setToolTip(new ToolTipConfig("Confirmar contraseña", "repita nuevamentela contraseña"));
        txtPasswordConfirmado.setFieldLabel("Confirmar contraseña");
        txtPasswordConfirmado.setAllowBlank(false);
        txtPasswordConfirmado.setPassword(true);
        formulario.add(txtPasswordConfirmado, formData);

        //creo y adiciono los botones del la ventana
        Button btnAceptar = new Button("Guardar",Resources.ICONS.iconoOk(),listenerCambioContrasenia());
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
                abrirVentana(myConstants.ayudaNewClaveUsuario());
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
     * Escucha el boton limpiar
     *
     * @return
     */
    private SelectionListener<ButtonEvent> listenerLimpiar() {
        return new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                cerrarVentana();
            }
        };
    }
    
    /**
     * Escucha el boton de login
     * @return 
     */
    private SelectionListener<ButtonEvent> listenerCambioContrasenia(){
        final AsyncCallback<RespuestaRPC<Integer>> esteObjeto = this;
        final RPCAdminUsuariosAsync svc = (RPCAdminUsuariosAsync) GWT.create(RPCAdminUsuarios.class);
        ServiceDefTarget endpoint = (ServiceDefTarget) svc;
        endpoint.setServiceEntryPoint("services/RPCAdminUsuarios");
        
        return new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                pnlErrores.setVisible( false );
                pnlExito.setVisible( false );

                if( formulario.isValid() &&
                    txtPasswordNuevo.getValue().compareTo( txtPasswordConfirmado.getValue() ) == 0 )
                {              
                    boxWait = MessageBox.wait("Progreso",
                            "Procesando los datos, por favor espere...", "Procesando...");
                    svc.cambiarClave( 
                            usuario,
                            txtPasswordNuevo.getValue(), 
                            esteObjeto );
                }
                else
                {
                    pnlErrores.limpiar();
                    pnlErrores.aniadir( "Debe escribir la misma clave en los " +
                            "campos de clave y confirmación" );
                    pnlErrores.setVisible( true );
                }
            }
        };
    }
    
//    /**
//     * Construye el objeto servicio apra realizar el llamado remoto
//     * @return Objeto de servicio construido
//     */
//    public static RPCAdministracion2UsuariosAsync getService()
//    {
//
//        return (RPCAdministracion2UsuariosAsync)PuertoRPC.setRpcEndPointUrl( GWT.create( RPCAdministracion2Usuarios.class ) );
//    }

    /**
     * Método a ejecutar en caso de error en el llamado
     * @param arg0 
     */
    public void onFailure( Throwable arg0 )
    {
        boxWait.close();
        pnlErrores.limpiar();
        pnlErrores.aniadir( "Se presentó un error de comunicación, por favor " +
                "inténtelo de nuevo o comuniquese con soporte técnico" );
        pnlErrores.setVisible( true );
    }

    /** 
     * En caso de éxito en el llamado
     * @param arg0 Objeto retornado por el método remoto
     */ 
    public void onSuccess( RespuestaRPC<Integer> respuesta )
    {
                boxWait.close();
                pnlExito.setVisible(false);
                pnlErrores.setVisible(false);

                if (respuesta.getResultado() == RespuestaRPC.RESULTADO_OK) {
                    //formulario.reset();
                    cerrarVentana();
                    pnlExito.definirTexto("La clave se ha cambiado correctamente");
                    Info.display("Cambio de clave","La clave se ha cambiado correctamente");
                    pnlExito.setVisible(true);
                    
                }
                else
                {
                    pnlErrores.limpiar();
                    pnlErrores.setVisible(true);
                    pnlErrores.aniadir(respuesta.getDescripcionError());
                }

    }
    
    public void recargar()
    {
        txtUsuario.setValue("");
        this.usuario=null;
    }

    
    /**
     * Setea la entidad a trajabar
     * @param usuarioTrabajo 
     */
    public void setEntidad(Usuarios usuarioTrabajo) {
        this.usuario=usuarioTrabajo;
        txtUsuario.setValue(""+usuarioTrabajo.getNombreCompleto());  
    }
    
    /**
     * cierra la ventana
     */
    private void cerrarVentana(){
        this.hide();
    }
}
