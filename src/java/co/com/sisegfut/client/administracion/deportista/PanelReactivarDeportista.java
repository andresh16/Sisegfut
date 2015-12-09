/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.client.administracion.deportista;

import co.com.sisegfut.client.aaI18N.Main;
import co.com.sisegfut.client.util.PanelErrores;
import co.com.sisegfut.client.util.PanelExito;
import co.com.sisegfut.client.util.Resources;
import co.com.sisegfut.client.util.combox.ComboBoxDeportista;
import co.com.sisegfut.client.util.rpc.RPCAdminDeportista;
import co.com.sisegfut.client.util.rpc.RPCAdminDeportistaAsync;
import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.binding.FormBinding;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormButtonBinding;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.FillLayout;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.MarginData;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

/**
 *
 * @author fhurtado
 */
public class PanelReactivarDeportista extends Window {

    FormPanel panel = new FormPanel();
    private FormBinding formBindings;
    private ComboBoxDeportista cbxDeportista;
    PanelAdminDeportista adminDeportista;
    private PanelErrores pnlErrores;
    private PanelExito pnlExito;

    private Main myConstants = (Main) GWT.create(Main.class);

    public PanelReactivarDeportista(PanelAdminDeportista panelPadre) {
        setSize(350, 145);
        adminDeportista = panelPadre;
        setPlain(true);
        setModal(true);
        setBlinkModal(true);
        setHeading("Reactivar deportista");
        setLayout(new FillLayout());
        setResizable(false);

        FormData formData = new FormData("-20");
        panel.setFrame(true);
        panel.setHeaderVisible(false);

        cbxDeportista = new ComboBoxDeportista(ComboBoxDeportista.INACTIVOS);

        cbxDeportista.setLabelSeparator("Deportista");
        cbxDeportista.setAllowBlank(false);

        Button btnReactivar = new Button("Reactivar", ListenerReactivar());
        btnReactivar.setArrowAlign(Style.ButtonArrowAlign.BOTTOM);
        btnReactivar.setIcon(Resources.ICONS.iconoRefrescar());

        pnlErrores = new PanelErrores();
        pnlExito = new PanelExito();

        panel.add(pnlErrores);
        panel.add(pnlExito);

        panel.add(cbxDeportista, formData);

        panel.addButton(btnReactivar);

        FormButtonBinding binding = new FormButtonBinding(panel);
        binding.addButton(btnReactivar);

        panel.setButtonAlign(Style.HorizontalAlignment.CENTER);
        formBindings = new FormBinding(panel, true);

        add(panel, new BorderLayoutData(Style.LayoutRegion.CENTER));

        add(panel, new MarginData(0));

        setFocusWidget(this.getButtonBar().getItem(0));
    }

    public SelectionListener<ButtonEvent> ListenerReactivar() {
        return new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                if (panel.isValid()) {

                    getServiceDeportista().reactivarEntidad(cbxDeportista.getDeportistaElegido().getId(), new AsyncCallback() {

                        @Override
                        public void onFailure(Throwable caught) {
                            MessageBox.alert("Error", "No se Reactivo el deportista", null);
                        }

                        @Override
                        public void onSuccess(Object result) {

                            Info.display("Reactivar", "Se Reactivo correctamente el deportista");
                            adminDeportista.cargar();
                        }

                    });

                    cbxDeportista.recargar();
                    cerrar();
                }
            }
        };
    }

    public RPCAdminDeportistaAsync getServiceDeportista() {
        RPCAdminDeportistaAsync svc = (RPCAdminDeportistaAsync) GWT.create(RPCAdminDeportista.class);
        ServiceDefTarget endpoint = (ServiceDefTarget) svc;
        endpoint.setServiceEntryPoint("services/RPCAdminDeportista");
        return svc;
    }

    public void cerrar() {
        this.hide();

    }

}
