/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.client.administracion.tipodocumento;

import co.com.sisegfut.client.datos.dominio.TipoDocumento;
import co.com.sisegfut.client.util.PanelErrores;
import co.com.sisegfut.client.util.PanelExito;
import co.com.sisegfut.client.util.Resources;
import co.com.sisegfut.client.util.combox.ComboBoxTipoDocumento;
import co.com.sisegfut.client.util.rpc.RPCAdminTipoDocumento;
import co.com.sisegfut.client.util.rpc.RPCAdminTipoDocumentoAsync;
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
public class PanelModificarTipoDocumento extends LayoutContainer {

    FormPanel panel = new FormPanel();
    private FormBinding formBindings;
    private PanelErrores pnlErrores;
    private PanelExito pnlExito;
    private ComboBoxTipoDocumento cbxTipoDocumento;
    TextField<String> txtTipoDocumento = new TextField<String>();

    public PanelModificarTipoDocumento() {
    }

    @Override
    protected void onRender(Element parent, int index) {

        super.onRender(parent, index);

        FormData formData = new FormData("-20");
        // setLayout(new FillLayout());
        panel.setFrame(true);
        panel.setHeaderVisible(false);
        txtTipoDocumento.setName("tipoDocumento");
        txtTipoDocumento.setFieldLabel("Tipo de Documento");
        txtTipoDocumento.setAllowBlank(false);
        txtTipoDocumento.setToolTip("Digite el Tipo de Documento que desee guardar");

        cbxTipoDocumento = new ComboBoxTipoDocumento(ComboBoxTipoDocumento.ACTIVOS);

        cbxTipoDocumento.setLabelSeparator("Tipo de Documento");
        cbxTipoDocumento.setAllowBlank(false);

        // 
        Button btnModificar = new Button(" Modificar", ListenerModificar());
//        btnModificar.setArrowAlign(Style.ButtonArrowAlign.BOTTOM);
        btnModificar.setIcon(Resources.ICONS.iconoModificar());
        pnlErrores = new PanelErrores();
        pnlExito = new PanelExito();
        panel.add(pnlErrores);
        panel.add(pnlExito);
        panel.add(cbxTipoDocumento, formData);
        panel.add(txtTipoDocumento, formData);
        //panel.add(btnModificar, formData);

        FormButtonBinding binding = new FormButtonBinding(panel);
        binding.addButton(btnModificar);
        panel.setButtonAlign(Style.HorizontalAlignment.CENTER);
        panel.addButton(btnModificar);

        formBindings = new FormBinding(panel, true);

        cbxTipoDocumento.addListener(Events.SelectionChange, new Listener<BaseEvent>() {
            @Override
            public void handleEvent(BaseEvent be) {

                txtTipoDocumento.setValue(cbxTipoDocumento.getTipoDocumentoElegido().getNombreTipoDocumento().toString());
                // MessageBox.info("Combo tipo mov", "Entro al evento cambio selecion", null);

            }
        });
        /**
         * Para escuchar la tecla enter
         */
        txtTipoDocumento.addKeyListener(new KeyListener() {

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

    public RPCAdminTipoDocumentoAsync getServiceTipoDocumento() {
        RPCAdminTipoDocumentoAsync svc = (RPCAdminTipoDocumentoAsync) GWT.create(RPCAdminTipoDocumento.class);
        ServiceDefTarget endpoint = (ServiceDefTarget) svc;
        endpoint.setServiceEntryPoint("services/RPCAdminTipoDocumento");
        return svc;
    }

    public void modificar() {
          pnlExito.setVisible(false);
            pnlErrores.setVisible(false);
        if (panel.isValid()) {

            TipoDocumento tipoDocumento = new TipoDocumento();
            tipoDocumento.setId(cbxTipoDocumento.getTipoDocumentoElegido().getId());
            tipoDocumento.setNombreTipoDocumento(txtTipoDocumento.getValue().toUpperCase());

            getServiceTipoDocumento().guardarEntidad(tipoDocumento, new AsyncCallback() {

                @Override
                public void onFailure(Throwable caught) {
                  //  MessageBox.alert("Error", "No Modifico el Tipo de Documento", null);
                    pnlErrores.limpiar();
                    pnlErrores.setVisible(true);
                    pnlErrores.aniadir("No Modifico el Tipo de Documento");
                }

                @Override
                public void onSuccess(Object result) {
                    //MessageBox.alert("Modificar", "Se Modifico correctamente el Tipo de Documento", null);
                    pnlExito.definirTexto("Se Modificó correctamente el Tipo de Documento");
                    pnlExito.setVisible(true);
                }

            });
            txtTipoDocumento.reset();
            cbxTipoDocumento.recargar();
        } else {
            pnlErrores.limpiar();
            pnlErrores.aniadir("Debe llenar el campo");
            pnlErrores.setVisible(true);
        }

    }

}
