/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.client.administracion.tipodocumento;

import co.com.sisegfut.client.util.PanelErrores;
import co.com.sisegfut.client.util.PanelExito;
import co.com.sisegfut.client.util.Resources;
import co.com.sisegfut.client.util.combox.ComboBoxEps;
import co.com.sisegfut.client.util.combox.ComboBoxTipoDocumento;
import co.com.sisegfut.client.util.rpc.RPCAdminTipoDocumento;
import co.com.sisegfut.client.util.rpc.RPCAdminTipoDocumentoAsync;
import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.binding.FormBinding;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.KeyListener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormButtonBinding;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
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
public class PanelInactivarTipoDocumento extends LayoutContainer {

    FormPanel panel = new FormPanel();
    private FormBinding formBindings;
    private ComboBoxTipoDocumento cbxTipoDocumento;
    private PanelErrores pnlErrores;
    private PanelExito pnlExito;

    public PanelInactivarTipoDocumento() {
    }

    @Override
    protected void onRender(Element parent, int index) {

        super.onRender(parent, index);

        FormData formData = new FormData("-20");
        // setLayout(new FillLayout());
        panel.setFrame(true);
        panel.setHeaderVisible(false);

        cbxTipoDocumento = new ComboBoxTipoDocumento(ComboBoxEps.ACTIVOS);

        cbxTipoDocumento.setLabelSeparator("Tipo de documento");
        cbxTipoDocumento.setAllowBlank(false);

        // 
        Button btnInactivar = new Button("Inactivar", ListenerInactivar());
        btnInactivar.setArrowAlign(Style.ButtonArrowAlign.BOTTOM);
        btnInactivar.setIcon(Resources.ICONS.iconoEliminar());

        pnlErrores = new PanelErrores();
        pnlExito = new PanelExito();

        panel.add(pnlErrores);
        panel.add(pnlExito);
        panel.add(cbxTipoDocumento, formData);

        panel.addButton(btnInactivar);

        FormButtonBinding binding = new FormButtonBinding(panel);
        binding.addButton(btnInactivar);
        panel.setButtonAlign(Style.HorizontalAlignment.CENTER);
        formBindings = new FormBinding(panel, true);

        add(panel, new BorderLayoutData(Style.LayoutRegion.CENTER));

        /**
         * Para escuchar la tecla enter
         */
        cbxTipoDocumento.addKeyListener(new KeyListener() {

            @Override
            public void componentKeyPress(ComponentEvent event) {
                if (event.getKeyCode() == 13) {
                    //(button.click or the method called in the button click event)
                    System.out.println("Entro");

                    inactivar();
                }
            }
        });

    }

    public SelectionListener<ButtonEvent> ListenerInactivar() {
        return new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                inactivar();
            }
        };
    }

    public RPCAdminTipoDocumentoAsync getServiceTipoDocumento() {
        RPCAdminTipoDocumentoAsync svc = (RPCAdminTipoDocumentoAsync) GWT.create(RPCAdminTipoDocumento.class);
        ServiceDefTarget endpoint = (ServiceDefTarget) svc;
        endpoint.setServiceEntryPoint("services/RPCAdminTipoDocumento");
        return svc;
    }

    public void inactivar() {
        pnlExito.setVisible(false);
        pnlErrores.setVisible(false);
        if (panel.isValid()) {

            getServiceTipoDocumento().eliminarEntidad(cbxTipoDocumento.getTipoDocumentoElegido().getId(), new AsyncCallback() {

                @Override
                public void onFailure(Throwable caught) {
                    pnlErrores.limpiar();
                    pnlErrores.setVisible(true);
                    pnlErrores.aniadir("No se Inactivo el Tipo de Documento");
                }

                @Override
                public void onSuccess(Object result) {
                    pnlExito.definirTexto("Se Inactivó correctamente el Tipo de Documento");
                    pnlExito.setVisible(true);
                }

            });
            cbxTipoDocumento.recargar();
        } else // Si hay errores
        {
            pnlErrores.limpiar();
            pnlErrores.setVisible(true);
            pnlErrores.aniadir("Debe seleccionar un tipo de documento");
        }

    }
}
