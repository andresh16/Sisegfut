/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.client.administracion.instEducativa;

import co.com.sisegfut.client.datos.dominio.InstEducativa;
import co.com.sisegfut.client.util.PanelErrores;
import co.com.sisegfut.client.util.PanelExito;
import co.com.sisegfut.client.util.Resources;
import co.com.sisegfut.client.util.combox.ComboBoxInsEducativa;
import co.com.sisegfut.client.util.rpc.RPCAdminInstEducativa;
import co.com.sisegfut.client.util.rpc.RPCAdminInstEducativaAsync;
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
public class PanelModificarInstEducativa extends LayoutContainer {

    FormPanel panel = new FormPanel();
    private FormBinding formBindings;
    private ComboBoxInsEducativa cbxInsEducativa;
    TextField<String> txtInstEducativa = new TextField<String>();
    private PanelErrores pnlErrores;
    private PanelExito pnlExito;

    public PanelModificarInstEducativa() {
    }

    @Override
    protected void onRender(Element parent, int index) {

        super.onRender(parent, index);

        FormData formData = new FormData("-20");

        panel.setFrame(true);
        panel.setHeaderVisible(false);
        txtInstEducativa.setName("Inst_Educativa");
        txtInstEducativa.setFieldLabel("Inst. Educativa");
        txtInstEducativa.setAllowBlank(false);
        txtInstEducativa.setToolTip("Modifique la Inst. Educativa");

        cbxInsEducativa = new ComboBoxInsEducativa(ComboBoxInsEducativa.ACTIVOS);

        cbxInsEducativa.setLabelSeparator("Inst. Educativa");
        cbxInsEducativa.setAllowBlank(false);

       // 
        Button btnModificar = new Button(" Modificar", ListenerModificar());
        btnModificar.setArrowAlign(Style.ButtonArrowAlign.BOTTOM);
        btnModificar.setIcon(Resources.ICONS.iconoModificar());

        pnlErrores = new PanelErrores();
        pnlExito = new PanelExito();
        panel.add(pnlErrores);
        panel.add(pnlExito);
        panel.add(cbxInsEducativa, formData);
        panel.add(txtInstEducativa, formData);
        panel.addButton(btnModificar);

        FormButtonBinding binding = new FormButtonBinding(panel);
        binding.addButton(btnModificar);

        panel.setButtonAlign(Style.HorizontalAlignment.CENTER);

        formBindings = new FormBinding(panel, true);

        cbxInsEducativa.addListener(Events.SelectionChange, new Listener<BaseEvent>() {
            @Override
            public void handleEvent(BaseEvent be) {

                txtInstEducativa.setValue(cbxInsEducativa.getInstEducativaElegida().getNombreInstEducativa().toString());
                // MessageBox.info("Combo tipo mov", "Entro al evento cambio selecion", null);

            }
        });
        /**
         * Para escuchar la tecla enter
         */
        txtInstEducativa.addKeyListener(new KeyListener() {

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

    public RPCAdminInstEducativaAsync getServiceInstEducativo() {
        RPCAdminInstEducativaAsync svc = (RPCAdminInstEducativaAsync) GWT.create(RPCAdminInstEducativa.class);
        ServiceDefTarget endpoint = (ServiceDefTarget) svc;
        endpoint.setServiceEntryPoint("services/RPCAdminInstEducativa");
        return svc;
    }

    public void modificar() {
        pnlExito.setVisible(false);
        pnlErrores.setVisible(false);
        if (panel.isValid()) {

            InstEducativa instEducativa = new InstEducativa();
            instEducativa.setId(cbxInsEducativa.getInstEducativaElegida().getId());
            instEducativa.setNombreInstEducativa(txtInstEducativa.getValue());

            getServiceInstEducativo().guardarEntidad(instEducativa, new AsyncCallback() {

                @Override
                public void onFailure(Throwable caught) {
                    pnlErrores.limpiar();
                    pnlErrores.setVisible(true);
                    pnlErrores.aniadir("No Modifico la Inst. Educativa");
                }

                @Override
                public void onSuccess(Object result) {
                    pnlExito.definirTexto("Se Modifico correctamente la Inst. Educativa");
                    pnlExito.setVisible(true);
                }

            });
            txtInstEducativa.reset();
            cbxInsEducativa.recargar();
        } else {
            pnlErrores.limpiar();
            pnlErrores.aniadir("Debe llenar el campo");
            pnlErrores.setVisible(true);
        }
    }

}
