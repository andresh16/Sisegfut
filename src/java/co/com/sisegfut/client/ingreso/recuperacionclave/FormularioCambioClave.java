/*
 * Para Boos IT
 * Ext GWT 2.2.5 - Ext for GWT
 * Copyright(c) 2007-2010, Ext JS, LLC.
 * licensing@extjs.com
 * 
 * http://extjs.com/license
 */
package co.com.sisegfut.client.ingreso.recuperacionclave;

import co.com.sisegfut.client.util.rpc.RPCRecuperarClave;
import co.com.sisegfut.client.util.rpc.RPCRecuperarClaveAsync;
import co.com.sisegfut.client.util.PanelErrores;
import co.com.sisegfut.client.util.PanelExito;
import co.com.sisegfut.client.util.PanelInformativo;
import co.com.sisegfut.client.util.Resources;
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
 * @author josorio
 */
public class FormularioCambioClave extends FormPanel {

    /**
     * Layout data from layaout form
     */
    private FormData formData;
    private PanelInformativo pnlInformativo;
    private PanelErrores pnlErrores;
    private PanelExito pnlExito;
    private TextField<String> txtLlaveRecibida;
    private TextField<String> txtClaveNueva;
    private TextField<String> txtClaveConfirm;
    /**
     * ventana modal para el mensaje de espera
     */
    private MessageBox boxWait;

    public FormularioCambioClave() {
        formData = new FormData("-20");

        setFrame(true);
        setHeading("Cambiar clave");

        pnlInformativo = new PanelInformativo("<h3>Cambio de clave</h3>"
                + "Por favor pegue en el campo Llave la llave que se le envió al correo, escriba en los dos campos siguientes la clave que desea tener en adelante y presione el botón \"Cambiar clave\"", null);

        pnlErrores = new PanelErrores();
        pnlExito = new PanelExito();

        add(pnlInformativo);
        add(pnlErrores);
        add(pnlExito);

        txtLlaveRecibida = new TextField<String>();
        txtLlaveRecibida.setMaxLength(100);
        txtLlaveRecibida.setToolTip(new ToolTipConfig("Llave recibida", "Debe pegar la llave que recibió por "
                + "correo electrónico"));
        txtLlaveRecibida.setFieldLabel("Llave recibida");
        txtLlaveRecibida.setAllowBlank(false);
        txtLlaveRecibida.getFocusSupport().setPreviousId(getButtonBar().getId());
        add(txtLlaveRecibida, formData);

        txtClaveNueva = new TextField<String>();
        txtClaveNueva.setPassword(true);
        txtClaveNueva.setMaxLength(100);
        txtClaveNueva.setToolTip(new ToolTipConfig("Clave nueva", "Escriba la "
                + "clave que desea usar en adelante para acceder al sistema "));
        txtClaveNueva.setFieldLabel("Clave nueva");
        txtClaveNueva.setAllowBlank(false);
        txtClaveNueva.getFocusSupport().setPreviousId(getButtonBar().getId());
        add(txtClaveNueva, formData);

        txtClaveConfirm = new TextField<String>();
        txtClaveConfirm.setPassword(true);
        txtClaveConfirm.setMaxLength(100);
        txtClaveConfirm.setToolTip(new ToolTipConfig("Confirmar clave", "Escriba de nuevo la clave que desea usar en adelante "
                + "para acceder al sistema (la misma que escribió en el "
                + "campo 'Clave')"));
        txtClaveConfirm.setFieldLabel("Confirmar clave");
        txtClaveConfirm.setAllowBlank(false);
        txtClaveConfirm.getFocusSupport().setPreviousId(getButtonBar().getId());
        add(txtClaveConfirm, formData);

        Button btnAceptar = new Button("Cambiar clave", Resources.ICONS.iconoClave(), listenerCambiarClave());
        addButton(btnAceptar);

        setButtonAlign(Style.HorizontalAlignment.CENTER);

        //Monitors the valid state of a form and enabled / disabled all buttons.
        FormButtonBinding binding = new FormButtonBinding(this);
        binding.addButton(btnAceptar);
    }

    /**
     * Listener del boton
     *
     * @return
     */
    private SelectionListener<ButtonEvent> listenerCambiarClave() {
        final RPCRecuperarClaveAsync svc = (RPCRecuperarClaveAsync) GWT.create(RPCRecuperarClave.class);
        ServiceDefTarget endpoint = (ServiceDefTarget) svc;
        endpoint.setServiceEntryPoint("services/RPCRecuperarClave");

        return new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                // Se chequea el formulario por errores
                // Si está bien
                if (isValid()) {
                    // Si las claves no son iguales
                    if (!txtClaveNueva.getValue().equals(
                            txtClaveConfirm.getValue())) {
                        pnlErrores.limpiar();
                        pnlErrores.setVisible(true);
                        pnlErrores.aniadir("La clave y su "
                                + "confirmación deben ser iguales");
                    } else // Si todo está ok
                    {
                        pnlErrores.setVisible(false);
                        boxWait = MessageBox.wait("Progreso",
                                "Procesando los datos, por favor espere...", "Procesando...");
                        svc.cambiarClave(
                                txtLlaveRecibida.getValue(),
                                txtClaveNueva.getValue(),
                                callbackCambioClave());
                    }
                } else // Si hay errores
                {
                    pnlErrores.limpiar();
                    pnlErrores.setVisible(true);
                    pnlErrores.aniadir("Debe llenar todos los campos");
                }
            }
        };
    }

//    /**
//     * Construye el objeto servicio apra realizar el llamado remoto
//     *
//     * @return Objeto de servicio construido
//     */
//    public static RPCRecuperarClaveAsync getService() {
//        return (RPCRecuperarClaveAsync) PuertoRPC.setRpcEndPointUrl(GWT.create(RPCRecuperarClave.class));
//    }
    private AsyncCallback callbackCambioClave() {
        // Callback asíncrono para manejar el resultado
        final AsyncCallback callbackCambioClave = new AsyncCallback() {

            public void onSuccess(Object result) {
                boxWait.close();
                int resultado = ((Integer) result).intValue();

                switch (resultado) {
                    // Si todo se insertó bien
                    case 0:
                        pnlErrores.setVisible(false);
                        reset();
                        MessageBox.info("Confirmación", "Su clave fue modificada "
                                + "exitosamente, haga click en la pestaña "
                                + "de entrada e ingrese al sistema con "
                                + "su documento y la clave que "
                                + "especificó", null);
                        break;
                    // Si la llave no es válida
                    case 1:
                        pnlErrores.limpiar();
                        pnlErrores.setVisible(true);
                        pnlErrores.aniadir("La llave no es válida");
                        break;
                    // Si se presentó un error
                    case 2:
                        pnlErrores.limpiar();
                        pnlErrores.setVisible(true);
                        pnlErrores.aniadir("Se presentó un error "
                                + "al cambiar la clave");
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

            public void onFailure(Throwable caught) {
                boxWait.close();
                pnlErrores.limpiar();
                pnlErrores.setVisible(true);
                pnlErrores.aniadir("Se presentó un error en el servidor");
            }
        };
        return callbackCambioClave;
    }
}
