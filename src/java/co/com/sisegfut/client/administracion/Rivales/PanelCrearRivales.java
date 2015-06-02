/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.client.administracion.Rivales;

import co.com.sisegfut.client.datos.dominio.Deportista;
import co.com.sisegfut.client.datos.dominio.Rivales;
import co.com.sisegfut.client.entidades.RespuestaRPC;
import co.com.sisegfut.client.util.PanelErrores;
import co.com.sisegfut.client.util.PanelExito;
import co.com.sisegfut.client.util.Resources;
import co.com.sisegfut.client.util.combox.ComboBoxTorneo;
import co.com.sisegfut.client.util.rpc.RPCAdminDeportista;
import co.com.sisegfut.client.util.rpc.RPCAdminDeportistaAsync;
import co.com.sisegfut.client.util.rpc.RPCAdminRivales;
import co.com.sisegfut.client.util.rpc.RPCAdminRivalesAsync;
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
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

/**
 *
 * @author Malejandro
 */
public class PanelCrearRivales extends LayoutContainer {

    FormPanel panel = new FormPanel();
    private FormBinding formBindings;
    TextField<String> txtRival = new TextField<String>();
    private ComboBoxTorneo cbxTorneo;
    private PanelErrores pnlErrores;
    private PanelExito pnlExito;

    /**
     * Contiene los textos a presentar en la interfaz web segun el idioma
     */
    public PanelCrearRivales() {

    }

    @Override
    protected void onRender(Element parent, int index) {

        super.onRender(parent, index);
        // setLayout(new FillLayout());
//        myConstants = (Main) GWT.create(Main.class);

        panel.setFrame(true);
        panel.setHeaderVisible(false);
        txtRival.setName("nombre_rival");
        txtRival.setFieldLabel("Nombre Rival");
        txtRival.setToolTip("Digite un rival que desee guardar");

        txtRival.addListener(Events.OnBlur, new Listener<BaseEvent>() {
            @Override
            public void handleEvent(BaseEvent be) {
                txtRival.setValue(txtRival.getValue().toUpperCase());
            }
        });

        cbxTorneo = new ComboBoxTorneo(ComboBoxTorneo.ACTIVOS);
        cbxTorneo.setLabelSeparator("Torneo");
        cbxTorneo.setAllowBlank(false);

        Button btnCrear = new Button(" Crear", ListenerCrear(1));
        btnCrear.setArrowAlign(Style.ButtonArrowAlign.BOTTOM);
        btnCrear.setIcon(Resources.ICONS.iconoCrear());

        pnlErrores = new PanelErrores();
        pnlExito = new PanelExito();

        panel.add(pnlErrores);
        panel.add(pnlExito);

        panel.add(txtRival);
        panel.add(cbxTorneo);
        panel.addButton(btnCrear);

        FormButtonBinding binding = new FormButtonBinding(panel);
        binding.addButton(btnCrear);
        panel.setButtonAlign(Style.HorizontalAlignment.CENTER);
        formBindings = new FormBinding(panel, true);

        add(panel, new BorderLayoutData(Style.LayoutRegion.CENTER));

        cbxTorneo.addKeyListener(new KeyListener() {

            @Override
            public void componentKeyPress(ComponentEvent event) {
                if (event.getKeyCode() == 13) {
                    //(button.click or the method called in the button click event)
                    guardar();

                }
            }
        });

    }

    public SelectionListener<ButtonEvent> ListenerCrear(final int tipo) {
        return new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                guardar();
            }
        };
    }

    public void guardar() {
        pnlExito.setVisible(false);
        pnlErrores.setVisible(false);
        if (panel.isValid()) {

            Deportista deportistaComodin = new Deportista(txtRival.getValue().toUpperCase(), cbxTorneo.getTorneosElegido().getCategoria(), true);

            getServiceDeportista().guardarEntidad(deportistaComodin, new AsyncCallback<RespuestaRPC<Deportista>>() {

                @Override
                public void onFailure(Throwable caught) {
                   pnlErrores.limpiar();
                            pnlErrores.setVisible(true);
                            pnlErrores.aniadir("No guardo el jugador comodin");
                }

                @Override
                public void onSuccess(RespuestaRPC<Deportista> result) {

                    Rivales rivales = new Rivales();
                    rivales.setNombreRival(txtRival.getValue().toUpperCase());
                    rivales.setTorneo(cbxTorneo.getTorneosElegido());
                    rivales.setJugadorComodin(result.getObjetoRespuesta());
                    getServiceRivales().guardarEntidad(rivales, new AsyncCallback() {

                        @Override
                        public void onFailure(Throwable caught) {
                            pnlErrores.limpiar();
                            pnlErrores.setVisible(true);
                            pnlErrores.aniadir("No guardo el rival");
                        }

                        @Override
                        public void onSuccess(Object result) {
                            pnlExito.definirTexto("Guardo correctamente el rival");
                            pnlExito.setVisible(true);
                        }

                    });
                    panel.reset();
                }
            });

        } else // Si hay errores
        {
            pnlErrores.limpiar();
            pnlErrores.setVisible(true);
            pnlErrores.aniadir("Debe llenar todos los campos");
        }

    }

    public RPCAdminRivalesAsync getServiceRivales() {
        RPCAdminRivalesAsync svc = (RPCAdminRivalesAsync) GWT.create(RPCAdminRivales.class);
        ServiceDefTarget endpoint = (ServiceDefTarget) svc;
        endpoint.setServiceEntryPoint("services/RPCAdminRivales");
        return svc;
    }

    public RPCAdminDeportistaAsync getServiceDeportista() {
        RPCAdminDeportistaAsync svc = (RPCAdminDeportistaAsync) GWT.create(RPCAdminDeportista.class);
        ServiceDefTarget endpoint = (ServiceDefTarget) svc;
        endpoint.setServiceEntryPoint("services/RPCAdminDeportista");
        return svc;
    }

}
