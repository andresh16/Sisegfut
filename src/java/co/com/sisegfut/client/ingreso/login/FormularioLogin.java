/*
 * 
 * 
 * 
 * 
 * 
 * 
 */
package co.com.sisegfut.client.ingreso.login;

import co.com.sisegfut.client.util.rpc.RPCLoginAsync;
import co.com.sisegfut.client.util.rpc.RPCLogin;
import co.com.sisegfut.client.util.PanelInformativo;
import co.com.sisegfut.client.util.PanelErrores;
import co.com.sisegfut.client.util.Resources;
import co.com.sisegfut.client.util.PanelExito;
import co.com.sisegfut.client.util.BeansLocales;
import co.com.sisegfut.client.base.PanelBase;
import co.com.sisegfut.client.datos.dominio.Usuarios;
import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.Style.HorizontalAlignment;
import com.extjs.gxt.ui.client.core.El;
import com.extjs.gxt.ui.client.core.XDOM;
import com.extjs.gxt.ui.client.event.*;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.Component;
import com.extjs.gxt.ui.client.widget.ComponentPlugin;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.Viewport;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormButtonBinding;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.extjs.gxt.ui.client.widget.tips.ToolTipConfig;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.RootPanel;

/**
 *
 * @author 
 */
public class FormularioLogin extends FormPanel {

    /**
     * Layout data from layaout form
     */
    private FormData formData;
    private PanelInformativo pnlInformativo;
    private PanelErrores pnlErrores;
    private PanelExito pnlExito;
    private TextField<String> txtUsuario;
    private TextField<String> txtPassword;
    /**
     * ventana modal para el mensaje de espera
     */
    private MessageBox boxWait;

    public FormularioLogin() {

        formData = new FormData("-20");
        formData.setMargins(new Margins(0,0,0, 20));

       // setFrame(true);
//        setLabelAlign(LabelAlign.LEFT); 
        setHeaderVisible(false);
        setBodyBorder(false);
        
         ComponentPlugin plugin = new ComponentPlugin() {
            public void init(Component component) {
                component.addListener(Events.Render, new Listener<ComponentEvent>() {
                    public void handleEvent(ComponentEvent be) {
                        El elem = be.getComponent().el().findParent(".x-form-element", 3);
                        // should style in external CSS  rather than directly  
                        elem.appendChild(XDOM.create("<center><div style='color: #615f5f;padding: 1 0 2 0px;'>" + be.getComponent().getData("text") + "</div></center>"));
                    }
                });
            }
        };
        

        pnlInformativo = new PanelInformativo("<h3>Bienvenido</h3>"
                + "Para ingresar digite sus datos, si olvidó su clave puede recuperarla usando la pestaña ubicada en la parte superior de este recuadro.", null);

        pnlErrores = new PanelErrores();
        pnlExito = new PanelExito();

//        add(pnlInformativo);
        add(pnlErrores);
        add(pnlExito);

        
        FormLayout layout = new FormLayout();
        layout = new FormLayout();
//        layout.setLabelWidth(60);
        layout.setLabelAlign(LabelAlign.TOP);
        layout.setLabelSeparator("");
        
        setLayout(layout);
        
        txtUsuario = new TextField<String>();
//        txtUsuario.setValue("sisegfut@gmail.com");
        txtUsuario.setMaxLength(100);
        txtUsuario.addPlugin(plugin);
        txtUsuario.setData("text", "Ejem. ejemplo@dominio.com");
//        txtUsuario.setEmptyText("ejem. ejemplo@dominio.com");
        txtUsuario.setToolTip(new ToolTipConfig("Correo", "Digite el correo electrónico, ejem. ejemplo@dominio.com"));
        txtUsuario.setFieldLabel("<center><FONT SIZE=3 FACE='courier new'><b>Usuario</b></font></center>");
        txtUsuario.setRegex("^(\\w+)([-+.][\\w]+)*@(\\w[-\\w]*\\.){1,5}([A-Za-z]){2,4}$");
        txtUsuario.getMessages().setRegexText("Formato no valido, ej: ejemplo@dominio.com");
        txtUsuario.setAllowBlank(false);
        txtUsuario.getFocusSupport().setPreviousId(getButtonBar().getId());
        add(txtUsuario, formData);

        txtPassword = new TextField<String>();
        txtPassword.setMaxLength(100);
        txtPassword.addPlugin(plugin);
        txtPassword.setData("text", "Campo sensible a las mayusculas");
        txtPassword.setToolTip(new ToolTipConfig("Contraseña", "Digite la contraseña, campo sensible a las mayusculas"));
        txtPassword.setFieldLabel("<center><FONT SIZE=3 FACE='courier new'><b>Contraseña</b></font></center>");
        txtPassword.setAllowBlank(false);
        txtPassword.setPassword(true);
        add(txtPassword, formData);

        Button btnAceptar = new Button("Entrar", Resources.ICONS.iconoOk(), listenerLogin());
        addButton(btnAceptar);

        setButtonAlign(HorizontalAlignment.CENTER);

        //Monitors the valid state of a form and enabled / disabled all buttons.
        FormButtonBinding binding = new FormButtonBinding(this);
        binding.addButton(btnAceptar);

        /**
         * Para escuchar la tecla enter
         */
        txtPassword.addKeyListener(new KeyListener() {

            @Override
            public void componentKeyPress(ComponentEvent event) {
                if (event.getKeyCode() == 13) {
                    //(button.click or the method called in the button click event)  
                    validarLogin();
                }
            }
        });

    }

    /**
     * Escucha el boton de login
     *
     * @return
     */
    private SelectionListener<ButtonEvent> listenerLogin() {

        return new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                //MessageBox.alert("Mensaje", "Ejecuto boton " + ce.getButton().getId(), null);

                validarLogin();
            }
        };
    }

    private void validarLogin() {
        final RPCLoginAsync svc = (RPCLoginAsync) GWT.create(RPCLogin.class);
        ServiceDefTarget endpoint = (ServiceDefTarget) svc;
        endpoint.setServiceEntryPoint("services/RPCLogin");
        // Se chequea el formulario por errores
        // Si está bien
        String correo= txtUsuario.getValue().toLowerCase();
        
        if (isValid()) {
            boxWait = MessageBox.wait("Iniando sesión",
                    "Procesando los datos, por favor espere...", "Cargando...");
//                    RPCLoginAsync puertoRPC = getService();
            svc.login(
                    correo,
                    txtPassword.getValue(),//algoritmo de encripcion no funciona del lado del cliente
                    callback());
        } else // Si hay errores
        {
            pnlErrores.limpiar();
            pnlErrores.setVisible(true);
            pnlErrores.aniadir("Debe llenar todos los campos");
        }
    }

    /**
     * Callback del RPC
     *
     * @return
     */
    private AsyncCallback<Usuarios> callback() {
        // Callback para el logueo
        final AsyncCallback<Usuarios> callback = new AsyncCallback<Usuarios>() {

            public void onSuccess(Usuarios usuario) {
                boxWait.close();
                // Si el usuario es válido
                if (usuario != null) {
                    if (usuario.getRol().getAutorizadoLogin()) {
                        // Copio los datos al bean local
                        BeansLocales.setUsuario(usuario);
                        pnlErrores.setVisible(false);
                        
                        Viewport viewport;
                        viewport = new Viewport();
                        viewport.setLayout(new BorderLayout());
                        viewport.add(new PanelBase(usuario), new BorderLayoutData(Style.LayoutRegion.CENTER));
                        RootPanel.get().remove(0);
                        RootPanel.get().add(viewport);

                    } else {
                        pnlErrores.limpiar();
                        pnlErrores.setVisible(true);
                        pnlErrores.aniadir("Usted no tiene permisos para ingresar al sistema!");
                    }
                } else // Si los datos son incorrectos
                {
                    pnlErrores.limpiar();
                    pnlErrores.setVisible(true);
                    pnlErrores.aniadir(" Correo y/o contraseña incorrecta ");
                }
            }

            public void onFailure(Throwable caught) {
                boxWait.close();
                pnlErrores.limpiar();
                pnlErrores.setVisible(true);
                pnlErrores.aniadir("Se presentó un error "
                        + "de comunicación, intentelo de nuevo");
            }
        };

        return callback;
    }
//    /**
//     * Servicio RPC de login
//     * @return 
//     */
//    private RPCLoginAsync getService() {
//        return (RPCLoginAsync) PuertoRPC.setRpcEndPointUrl(GWT.create(RPCLogin.class));
//    }
}
