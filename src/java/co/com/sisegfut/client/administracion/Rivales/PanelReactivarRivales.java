/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.client.administracion.Rivales;

import co.com.sisegfut.client.util.PanelErrores;
import co.com.sisegfut.client.util.PanelExito;
import co.com.sisegfut.client.util.Resources;
import co.com.sisegfut.client.util.combox.ComboBoxRival;
import co.com.sisegfut.client.util.rpc.RPCAdminRivales;
import co.com.sisegfut.client.util.rpc.RPCAdminRivalesAsync;
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
 * @author Malejandro
 */
public class PanelReactivarRivales extends LayoutContainer {

    FormPanel panel = new FormPanel();
    private FormBinding formBindings;
    private ComboBoxRival cbxRival;
    private PanelErrores pnlErrores;
    private PanelExito pnlExito;

    public PanelReactivarRivales() {
    }

    @Override
    protected void onRender(Element parent, int index) {

        super.onRender(parent, index);

        FormData formData = new FormData("-20");
        // setLayout(new FillLayout());
        panel.setFrame(true);
        panel.setHeaderVisible(false);

        cbxRival = new ComboBoxRival(ComboBoxRival.INACTIVOS);

        cbxRival.setLabelSeparator("Rivales");
        cbxRival.setAllowBlank(false);

        // 
        Button btnReactivar = new Button("Reactivar", ListenerReactivar());
        btnReactivar.setArrowAlign(Style.ButtonArrowAlign.BOTTOM);
        btnReactivar.setIcon(Resources.ICONS.iconoRefrescar());
        pnlErrores = new PanelErrores();
        pnlExito = new PanelExito();

        panel.add(pnlErrores);
        panel.add(pnlExito);

        panel.add(cbxRival, formData);

        panel.addButton(btnReactivar);

        FormButtonBinding binding = new FormButtonBinding(panel);
        binding.addButton(btnReactivar);

        panel.setButtonAlign(Style.HorizontalAlignment.CENTER);
        formBindings = new FormBinding(panel, true);

        add(panel, new BorderLayoutData(Style.LayoutRegion.CENTER));

        cbxRival.addKeyListener(new KeyListener() {

            @Override
            public void componentKeyPress(ComponentEvent event) {
                if (event.getKeyCode() == 13) {
                    //(button.click or the method called in the button click event)
                    System.out.println("Entro");

                    reactivar();
                }
            }
        });

    }

    public SelectionListener<ButtonEvent> ListenerReactivar() {
        return new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                reactivar();
            }
        };
    }

    public RPCAdminRivalesAsync getServiceRivales() {
        RPCAdminRivalesAsync svc = (RPCAdminRivalesAsync) GWT.create(RPCAdminRivales.class);
        ServiceDefTarget endpoint = (ServiceDefTarget) svc;
        endpoint.setServiceEntryPoint("services/RPCAdminRivales");
        return svc;
    }

    public void reactivar() {
        pnlExito.setVisible(false);
        pnlErrores.setVisible(false);
        if (panel.isValid()) {

            getServiceRivales().reactivarEntidad(cbxRival.getRivalElegido().getId(), new AsyncCallback() {

                @Override
                public void onFailure(Throwable caught) {
                    pnlErrores.limpiar();
                    pnlErrores.setVisible(true);
                    pnlErrores.aniadir("No se Reactivó el rival");
                }

                @Override
                public void onSuccess(Object result) {
                    pnlExito.definirTexto("Se Reactivo correctamente el rival");
                    pnlExito.setVisible(true);
                }

            });
            cbxRival.recargar();
        } else // Si hay errores
        {
            pnlErrores.limpiar();
            pnlErrores.setVisible(true);
            pnlErrores.aniadir("Debe seleccionar un rival");
        }
    }

}
