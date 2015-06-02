/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.client.administracion.tipodocumento;

import co.com.sisegfut.client.aaI18N.Main;
import co.com.sisegfut.client.datos.dominio.TipoDocumento;
import co.com.sisegfut.client.util.PanelErrores;
import co.com.sisegfut.client.util.PanelExito;
import co.com.sisegfut.client.util.Resources;
import co.com.sisegfut.client.util.rpc.RPCAdminTipoDocumento;
import co.com.sisegfut.client.util.rpc.RPCAdminTipoDocumentoAsync;
import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.binding.FormBinding;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.KeyListener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormButtonBinding;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

/**
 *
 * @author anfeh_000
 */
public class PanelCrearTipoDocumento extends LayoutContainer {

    FormPanel panel = new FormPanel();
    private FormBinding formBindings;
    TextField<String> txtTipoDocumento = new TextField<String>();
    private PanelErrores pnlErrores;
    private PanelExito pnlExito;

    /**
     * Contiene los textos a presentar en la interfaz web segun el idioma
     */
    private Main myConstants;

    public PanelCrearTipoDocumento() {
    }

    @Override
    protected void onRender(Element parent, int index) {

        super.onRender(parent, index);
        // setLayout(new FillLayout());
        myConstants = (Main) GWT.create(Main.class);

        panel.setFrame(true);
        panel.setHeaderVisible(false);
        txtTipoDocumento.setName("tipoDocumento");
        txtTipoDocumento.setFieldLabel("Tipo de Documento");
        txtTipoDocumento.setAllowBlank(false);
        txtTipoDocumento.setToolTip("Digite el Tipo de Documento que desee guardar");

        Button btnCrear = new Button("Crear", ListenerCrear());
        btnCrear.setArrowAlign(Style.ButtonArrowAlign.BOTTOM);
        btnCrear.setIcon(Resources.ICONS.iconoCrear());

        pnlErrores = new PanelErrores();
        pnlExito = new PanelExito();

        panel.add(pnlErrores);
        panel.add(pnlExito);

        panel.add(txtTipoDocumento);
        panel.addButton(btnCrear);

        FormButtonBinding binding = new FormButtonBinding(panel);
        binding.addButton(btnCrear);

        panel.setButtonAlign(Style.HorizontalAlignment.CENTER);

        formBindings = new FormBinding(panel, true);

        add(panel, new BorderLayoutData(Style.LayoutRegion.CENTER));

        /**
         * Para escuchar la tecla enter
         */
        txtTipoDocumento.addKeyListener(new KeyListener() {

            @Override
            public void componentKeyPress(ComponentEvent event) {
                if (event.getKeyCode() == 13) {
                    //(button.click or the method called in the button click event)
                    guardar();

                }
            }
        });

    }

    public SelectionListener<ButtonEvent> ListenerCrear() {
        return new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                guardar();
            }
        };
    }

    public RPCAdminTipoDocumentoAsync getServiceTipoDocumento() {
        RPCAdminTipoDocumentoAsync svc = (RPCAdminTipoDocumentoAsync) GWT.create(RPCAdminTipoDocumento.class);
        ServiceDefTarget endpoint = (ServiceDefTarget) svc;
        endpoint.setServiceEntryPoint("services/RPCAdminTipoDocumento");
        return svc;
    }

    public void guardar() {
        pnlExito.setVisible(false);
        pnlErrores.setVisible(false);
        if (panel.isValid()) {
            TipoDocumento tipoDocumento = new TipoDocumento();
            tipoDocumento.setNombreTipoDocumento(txtTipoDocumento.getValue());

            getServiceTipoDocumento().guardarEntidad(tipoDocumento, new AsyncCallback() {

                @Override
                public void onFailure(Throwable caught) {

                    pnlErrores.limpiar();
                    pnlErrores.setVisible(true);
                    pnlErrores.aniadir("No guardo el Tipo de Documento");

                }

                @Override
                public void onSuccess(Object result) {
                    // MessageBox.alert("Crear", "Guardo correctamente el Tipo de Documento", null);
                    pnlExito.definirTexto("Guardo correctamente el Tipo de Documento");
                    pnlExito.setVisible(true);
                }

            });
            txtTipoDocumento.reset();
        } else // Si hay errores
        {
            pnlErrores.limpiar();
            pnlErrores.setVisible(true);
            pnlErrores.aniadir("Debe llenar el campo");
        }
    }
}
