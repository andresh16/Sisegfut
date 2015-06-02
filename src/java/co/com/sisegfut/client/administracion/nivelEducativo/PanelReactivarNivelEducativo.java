/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.client.administracion.nivelEducativo;

import co.com.sisegfut.client.util.PanelErrores;
import co.com.sisegfut.client.util.PanelExito;
import co.com.sisegfut.client.util.Resources;
import co.com.sisegfut.client.util.combox.ComboBoxNivelEducativo;
import co.com.sisegfut.client.util.rpc.RPCAdminEps;
import co.com.sisegfut.client.util.rpc.RPCAdminEpsAsync;
import co.com.sisegfut.client.util.rpc.RPCAdminNivelEducativo;
import co.com.sisegfut.client.util.rpc.RPCAdminNivelEducativoAsync;
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
public class PanelReactivarNivelEducativo extends LayoutContainer {

    FormPanel panel = new FormPanel();
    private FormBinding formBindings;
    private ComboBoxNivelEducativo cbxNivelEducativo;
    private PanelErrores pnlErrores;
    private PanelExito pnlExito;

    public PanelReactivarNivelEducativo() {
    }

    @Override
    protected void onRender(Element parent, int index) {

        super.onRender(parent, index);

        FormData formData = new FormData("-20");
        // setLayout(new FillLayout());
        panel.setFrame(true);
        panel.setHeaderVisible(false);

        cbxNivelEducativo = new ComboBoxNivelEducativo(ComboBoxNivelEducativo.INACTIVOS);

        cbxNivelEducativo.setLabelSeparator("Nivel Educativo");
        cbxNivelEducativo.setAllowBlank(false);

        // 
        Button btnReactivar = new Button("Reactivar", ListenerReactivar());
        btnReactivar.setArrowAlign(Style.ButtonArrowAlign.BOTTOM);
        btnReactivar.setIcon(Resources.ICONS.iconoRefrescar());
        pnlErrores = new PanelErrores();
        pnlExito = new PanelExito();

        panel.add(pnlErrores);
        panel.add(pnlExito);
        panel.add(cbxNivelEducativo, formData);

        panel.addButton(btnReactivar);

        FormButtonBinding binding = new FormButtonBinding(panel);
        binding.addButton(btnReactivar);
        panel.setButtonAlign(Style.HorizontalAlignment.CENTER);
        formBindings = new FormBinding(panel, true);

        add(panel, new BorderLayoutData(Style.LayoutRegion.CENTER));

        cbxNivelEducativo.addKeyListener(new KeyListener() {

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

    public RPCAdminNivelEducativoAsync getServiceNivelEducativo() {
        RPCAdminNivelEducativoAsync svc = (RPCAdminNivelEducativoAsync) GWT.create(RPCAdminNivelEducativo.class);
        ServiceDefTarget endpoint = (ServiceDefTarget) svc;
        endpoint.setServiceEntryPoint("services/RPCAdminNivelEducativo");
        return svc;
    }

    public void reactivar() {
        pnlExito.setVisible(false);
        pnlErrores.setVisible(false);
        if (panel.isValid()) {

            getServiceNivelEducativo().reactivarEntidad(cbxNivelEducativo.getNivelEducativoElegido().getId(), new AsyncCallback() {

                @Override
                public void onFailure(Throwable caught) {
                    pnlErrores.limpiar();
                    pnlErrores.setVisible(true);
                    pnlErrores.aniadir("No se Reactivo el Tipo de Documento");
                    MessageBox.alert("Error", "No se Reactivo el Nivel Educativo", null);
                }

                @Override
                public void onSuccess(Object result) {
                    MessageBox.alert("Reactivar", "Se Reactivo correctamente el Nivel Educativo", null);
                }

            });
            cbxNivelEducativo.recargar();
        } else // Si hay errores
        {
            pnlErrores.limpiar();
            pnlErrores.setVisible(true);
            pnlErrores.aniadir("Debe seleccionar un Nivel Educativo");
        }
    }

}
