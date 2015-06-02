/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.client.administracion.posiciones;

import co.com.sisegfut.client.datos.dominio.Posiciones;
import co.com.sisegfut.client.util.PanelErrores;
import co.com.sisegfut.client.util.PanelExito;
import co.com.sisegfut.client.util.Resources;
import co.com.sisegfut.client.util.combox.ComboBoxPosiciones;
import co.com.sisegfut.client.util.rpc.RPCAdminPosiciones;
import co.com.sisegfut.client.util.rpc.RPCAdminPosicionesAsync;
import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.binding.FormBinding;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.KeyListener;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormButtonBinding;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

/**
 *
 * @author anfeh_000
 */
public class PanelModificarPosiciones extends LayoutContainer {

    FormPanel panel = new FormPanel();
    private FormBinding formBindings;
    private ComboBoxPosiciones cbxPosiciones;
    TextField<String> txtPosiciones = new TextField<String>();

    private PanelErrores pnlErrores;
    private PanelExito pnlExito;

    public PanelModificarPosiciones() {
    }

    @Override
    protected void onRender(Element parent, int index) {

        super.onRender(parent, index);

        FormData formData = new FormData("-20");
        // setLayout(new FillLayout());
        panel.setFrame(true);
        panel.setHeaderVisible(false);
        txtPosiciones.setName("posiciones");
        txtPosiciones.setFieldLabel("Posición");
        txtPosiciones.setAllowBlank(false);
        txtPosiciones.setToolTip("Digite una posición que desee guardar");

        cbxPosiciones = new ComboBoxPosiciones(ComboBoxPosiciones.ACTIVOS);

        cbxPosiciones.setLabelSeparator("Posición");
        cbxPosiciones.setAllowBlank(false);

       // 
        Button btnModificar = new Button(" Modificar", ListenerModificar());
        btnModificar.setArrowAlign(Style.ButtonArrowAlign.BOTTOM);
        btnModificar.setIcon(Resources.ICONS.iconoModificar());

        pnlErrores = new PanelErrores();
        pnlExito = new PanelExito();
        panel.add(pnlErrores);
        panel.add(pnlExito);

        panel.add(cbxPosiciones, formData);
        panel.add(txtPosiciones, formData);
        panel.addButton(btnModificar);

        FormButtonBinding binding = new FormButtonBinding(panel);
        binding.addButton(btnModificar);
        panel.setButtonAlign(Style.HorizontalAlignment.CENTER);
        formBindings = new FormBinding(panel, true);

        cbxPosiciones.addListener(Events.SelectionChange, new Listener<BaseEvent>() {
            @Override
            public void handleEvent(BaseEvent be) {

                txtPosiciones.setValue(cbxPosiciones.getPosicionesElegido().getNombrePosicion().toString());
                // MessageBox.info("Combo tipo mov", "Entro al evento cambio selecion", null);

            }
        });
        cbxPosiciones.addListener(Events.OnFocus, new Listener<BaseEvent>() {
            @Override
            public void handleEvent(BaseEvent be) {

                cbxPosiciones.recargar();

            }
        });
        /**
         * Para escuchar la tecla enter
         */
        txtPosiciones.addKeyListener(new KeyListener() {

            @Override
            public void componentKeyPress(ComponentEvent event) {
                if (event.getKeyCode() == 13) {
                    //(button.click or the method called in the button click event)  
                    modificar();
                }
            }
        });

        add(panel, new BorderLayoutData(Style.LayoutRegion.CENTER));

    }

    public SelectionListener<ButtonEvent> ListenerModificar() {
        return new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                modificar();
            }
        };
    }

    public RPCAdminPosicionesAsync getServicePosicion() {
        RPCAdminPosicionesAsync svc = (RPCAdminPosicionesAsync) GWT.create(RPCAdminPosiciones.class);
        ServiceDefTarget endpoint = (ServiceDefTarget) svc;
        endpoint.setServiceEntryPoint("services/RPCAdminPosiciones");
        return svc;
    }

    public void modificar() {
        pnlExito.setVisible(false);
        pnlErrores.setVisible(false);
        if (panel.isValid()) {

            Posiciones posiciones = new Posiciones();
            posiciones.setId(cbxPosiciones.getPosicionesElegido().getId());
            posiciones.setNombrePosicion(txtPosiciones.getValue().toUpperCase());

            getServicePosicion().guardarEntidad(posiciones, new AsyncCallback() {

                @Override
                public void onFailure(Throwable caught) {
                    pnlErrores.limpiar();
                    pnlErrores.setVisible(true);
                    pnlErrores.aniadir("No Modifico la Posición");
                }

                @Override
                public void onSuccess(Object result) {
                    pnlExito.definirTexto("Se Modificó correctamente la Posición");
                    pnlExito.setVisible(true);
                }

            });
            txtPosiciones.reset();
            cbxPosiciones.recargar();
        } else {
            pnlErrores.limpiar();
            pnlErrores.aniadir("Debe llenar el campo");
            pnlErrores.setVisible(true);
        }
    }
}
