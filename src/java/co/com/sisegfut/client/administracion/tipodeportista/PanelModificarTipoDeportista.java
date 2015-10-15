/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.client.administracion.tipodeportista;

import co.com.sisegfut.client.datos.dominio.TipoDeportista;
import co.com.sisegfut.client.util.PanelErrores;
import co.com.sisegfut.client.util.PanelExito;
import co.com.sisegfut.client.util.Resources;
import co.com.sisegfut.client.util.combox.ComboBoxTipoDeportista;
import co.com.sisegfut.client.util.rpc.RPCAdminTipoDeportista;
import co.com.sisegfut.client.util.rpc.RPCAdminTipoDeportistaAsync;
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
public class PanelModificarTipoDeportista extends LayoutContainer {

    FormPanel panel = new FormPanel();
    private FormBinding formBindings;
    private ComboBoxTipoDeportista cbxTipoDeportista;
    TextField<String> txtTipoDeportista = new TextField<String>();
    private PanelErrores pnlErrores;
    private PanelExito pnlExito;

    public PanelModificarTipoDeportista() {
    }

    @Override
    protected void onRender(Element parent, int index) {

        super.onRender(parent, index);

        FormData formData = new FormData("-20");
        // setLayout(new FillLayout());
        panel.setFrame(true);
        panel.setHeaderVisible(false);
        txtTipoDeportista.setName("tipoDeportista");
        txtTipoDeportista.setFieldLabel("Nombre Tipo Deportista");
        txtTipoDeportista.setAllowBlank(false);
        txtTipoDeportista.setToolTip("Digite un tipo de deportista que desee guardar");

        cbxTipoDeportista = new ComboBoxTipoDeportista(ComboBoxTipoDeportista.ACTIVOS);

        cbxTipoDeportista.setLabelSeparator("Tipo Deportista");
        cbxTipoDeportista.setAllowBlank(false);

        // 
        Button btnModificar = new Button(" Modificar", ListenerModificar());
        btnModificar.setArrowAlign(Style.ButtonArrowAlign.BOTTOM);
        btnModificar.setIcon(Resources.ICONS.iconoModificar());

        pnlErrores = new PanelErrores();
        pnlExito = new PanelExito();
        panel.add(pnlErrores);
        panel.add(pnlExito);
        panel.add(cbxTipoDeportista, formData);
        panel.add(txtTipoDeportista, formData);
        panel.addButton(btnModificar);

        FormButtonBinding binding = new FormButtonBinding(panel);
        binding.addButton(btnModificar);
        panel.setButtonAlign(Style.HorizontalAlignment.CENTER);

        formBindings = new FormBinding(panel, true);

        cbxTipoDeportista.addListener(Events.SelectionChange, new Listener<BaseEvent>() {
            @Override
            public void handleEvent(BaseEvent be) {

                txtTipoDeportista.setValue(cbxTipoDeportista.getTipoDeportistaElegido().getNombreTipoDeportista().toString());
                // MessageBox.info("Combo tipo mov", "Entro al evento cambio selecion", null);

            }
        });
        /**
         * Para escuchar la tecla enter
         */
        txtTipoDeportista.addKeyListener(new KeyListener() {

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

    public RPCAdminTipoDeportistaAsync getServiceTipoDepotista() {
        RPCAdminTipoDeportistaAsync svc = (RPCAdminTipoDeportistaAsync) GWT.create(RPCAdminTipoDeportista.class);
        ServiceDefTarget endpoint = (ServiceDefTarget) svc;
        endpoint.setServiceEntryPoint("services/RPCAdminTipoDeportista");
        return svc;
    }

    public void modificar() {
        pnlExito.setVisible(false);
        pnlErrores.setVisible(false);
        if (panel.isValid()) {

            TipoDeportista tipoDeportista = new TipoDeportista();
            tipoDeportista.setId(cbxTipoDeportista.getTipoDeportistaElegido().getId());
            tipoDeportista.setNombreTipoDeportista(txtTipoDeportista.getValue().toUpperCase());

            getServiceTipoDepotista().guardarEntidad(tipoDeportista, new AsyncCallback() {

                @Override
                public void onFailure(Throwable caught) {
                    pnlErrores.limpiar();
                    pnlErrores.setVisible(true);
                    pnlErrores.aniadir("No Modifico el tipo de deportista");
                }

                @Override
                public void onSuccess(Object result) {
                    pnlExito.definirTexto("Se Modificó correctamente el tipo de deportista");
                    pnlExito.setVisible(true);
                }

            });
            txtTipoDeportista.reset();
            cbxTipoDeportista.recargar();
        } else {
            pnlErrores.limpiar();
            pnlErrores.aniadir("Debe llenar el campo");
            pnlErrores.setVisible(true);
        }
    }

}
