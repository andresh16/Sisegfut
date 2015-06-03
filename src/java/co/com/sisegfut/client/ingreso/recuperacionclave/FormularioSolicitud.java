package co.com.sisegfut.client.ingreso.recuperacionclave;

import co.com.sisegfut.client.util.rpc.RPCRecuperarClave;
import co.com.sisegfut.client.util.rpc.RPCRecuperarClaveAsync;
import co.com.sisegfut.client.util.PanelInformativo;
import co.com.sisegfut.client.util.PanelErrores;
import co.com.sisegfut.client.util.Resources;
import co.com.sisegfut.client.util.PanelExito;
import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.button.Button;
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
 * @author 
 */
public class FormularioSolicitud extends FormPanel{
    /**
     * Layout data from layaout form
     */
    private FormData formData;
    
    private PanelInformativo pnlInformativo;
    private PanelErrores pnlErrores;
    private PanelExito pnlExito;
    
    private TextField<String> txtUsuario;
    /** ventana modal para el mensaje de espera */
    private MessageBox boxWait;

    public FormularioSolicitud() {
        formData = new FormData("-20");

        setFrame(true);
        setHeading("Solicitud recuperación");
        
        pnlInformativo = new PanelInformativo("<h3>Solicitud de llave</h3>"+
"Para recuperar su clave es necesario confirmar que usted es realmente el dueño de la cuenta, por ello debe solicitar una llave a su correo electrónico, para hacerlo escriba sus datos y presione el botón de \"Solicitar llave\"",null);
        
        pnlErrores = new PanelErrores();
        pnlExito = new PanelExito();
        
        add(pnlInformativo); 
        add(pnlErrores); 
        add(pnlExito);
        
        txtUsuario = new TextField<String>();
        txtUsuario.setMaxLength(100); 
        txtUsuario.setRegex("^(\\w+)([-+.][\\w]+)*@(\\w[-\\w]*\\.){1,5}([A-Za-z]){2,4}$");
        txtUsuario.getMessages().setRegexText("Formato no valido, ej: ejemplo@dominio.com");
        txtUsuario.setToolTip(new ToolTipConfig("Correo", "Digite el correo"));
        txtUsuario.setFieldLabel("Correo");
        txtUsuario.setAllowBlank(false);
        txtUsuario.getFocusSupport().setPreviousId(getButtonBar().getId());
        add(txtUsuario, formData);
        
        Button btnAceptar = new Button("Solicitar llave",Resources.ICONS.iconoClave(),listenerSolicitar());
        addButton(btnAceptar);

        setButtonAlign(Style.HorizontalAlignment.CENTER);

        //Monitors the valid state of a form and enabled / disabled all buttons.
        FormButtonBinding binding = new FormButtonBinding(this);
        binding.addButton(btnAceptar);
    }
    /**
     * Listener del boton
     * @return 
     */
    private SelectionListener<ButtonEvent> listenerSolicitar(){
        final RPCRecuperarClaveAsync svc = (RPCRecuperarClaveAsync) GWT.create(RPCRecuperarClave.class);
        ServiceDefTarget endpoint = (ServiceDefTarget) svc;
        endpoint.setServiceEntryPoint("services/RPCRecuperarClave");
        
        return new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                if (isValid()) {
                    boxWait = MessageBox.wait("Progreso",  
                            "Procesando los datos, por favor espere...", "Procesando...");
                    svc.solicitarLlave(
                            txtUsuario.getValue(),
                            callbackSolicitudLlave());
                } else // Si hay errores
                {
                    pnlErrores.limpiar();
                    pnlErrores.setVisible(true);
                    pnlErrores.aniadir("Debe llenar todos los campos");
                }
            }
        };
    }
    /**
     * callbackSolicitudLlave
     * @return 
     */
    private AsyncCallback callbackSolicitudLlave(){
         // Callback asíncrono para manejar el resultado
        final AsyncCallback callbackSolicitudLlave = new AsyncCallback()
        {

            public void onSuccess(Object result)
            {
                int resultado = ((Integer) result).intValue();
                boxWait.close();

                switch (resultado)
                {
                    // Si la solicitud se procesó bien
                    case 0:
                        pnlErrores.setVisible(false);
                        //Reseteo el formulario.
                        reset();
                        MessageBox.info("Información","Se le envió un correo "
                                + "electrónico con una llave para "
                                + "modificar su clave",null);
                        break;
                    // Si el usuario no existe
                    case 1:
                        pnlErrores.limpiar();
                        pnlErrores.setVisible(true);
                        pnlErrores.aniadir("El usuario especificado no "
                                + "existe");
                        break;
                    // Si se presentó un error
                    case 2:
                        pnlErrores.limpiar();
                        pnlErrores.setVisible(true);
                        pnlErrores.aniadir("Se presentó un error "
                                + "al procesar la solicitud");
                        break;
                   //Si el usuario no tiene correo electrónico   
                   // 0001707: correo electrónico debe ser opcional al crear usuarios     
                   case 3:
                        pnlErrores.limpiar();
                        pnlErrores.setVisible(true);
                        pnlErrores.aniadir("El usuario ingresado no posee un correo electrónico asociado. "
                                + "Por favor, comuníquese con un administrador del sistema");
                        break;
                    // Si el resultado no es el esperado
                    default:
                        pnlErrores.limpiar();
                        pnlErrores.setVisible(true);
                        pnlErrores.aniadir("El servidor respondió "
                                + "de forma inesperada, por favor "
                                + "reporte este mensaje de error");
                }
            }
            // Si el llamado falla

            public void onFailure(Throwable caught)
            {
                boxWait.close();
                pnlErrores.limpiar();
                pnlErrores.setVisible(true);
                pnlErrores.aniadir("Se presentó un error "
                        + "de comunicación, intentelo de nuevo");
            }
        };
        return callbackSolicitudLlave;
    }
    
//    /**
//     * Construye el objeto servicio apra realizar el llamado remoto
//     * @return Objeto de servicio construido
//     */
//    public static RPCRecuperarClaveAsync getService()
//    {
//        return (RPCRecuperarClaveAsync) PuertoRPC.setRpcEndPointUrl(GWT.create(RPCRecuperarClave.class));
//    }
}
