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
import co.com.sisegfut.client.util.combox.ComboBoxRival;
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
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

/**
 *
 * @author Malejandro
 */
public class PanelModificarRivales extends LayoutContainer {

    FormPanel panel = new FormPanel();
    private FormBinding formBindings;
    private ComboBoxTorneo cbxTorneo;
    private ComboBoxRival cbxRival;

    TextField<String> txtRival = new TextField<String>();

    private PanelErrores pnlErrores;
    private PanelExito pnlExito;

    public PanelModificarRivales() {
    }

    @Override
    protected void onRender(Element parent, int index) {

        super.onRender(parent, index);

        FormData formData = new FormData("-20");
        // setLayout(new FillLayout());
        panel.setFrame(true);
        panel.setHeaderVisible(false);
        txtRival.setName("rival");
        txtRival.setFieldLabel("Nombre Rival");
        txtRival.setAllowBlank(false);
        txtRival.setToolTip("Digite un rival que desee guardar");

        cbxRival = new ComboBoxRival(ComboBoxRival.ACTIVOS);
        cbxRival.setLabelSeparator("Rival:");
        cbxRival.setAllowBlank(false);

        cbxTorneo = new ComboBoxTorneo(ComboBoxTorneo.ACTIVOS);
        cbxTorneo.setLabelSeparator("Torneo:");
        cbxTorneo.setAllowBlank(false);

        // 
        Button btnModificar = new Button(" Modificar", ListenerModificar());
        btnModificar.setArrowAlign(Style.ButtonArrowAlign.BOTTOM);
        btnModificar.setIcon(Resources.ICONS.iconoModificar());

        pnlErrores = new PanelErrores();
        pnlExito = new PanelExito();
        panel.add(pnlErrores);
        panel.add(pnlExito);

        panel.add(cbxRival, formData);
        panel.add(txtRival, formData);
        panel.add(cbxTorneo, formData);
        panel.addButton(btnModificar);

        FormButtonBinding binding = new FormButtonBinding(panel);
        binding.addButton(btnModificar);
        panel.setButtonAlign(Style.HorizontalAlignment.CENTER);

        formBindings = new FormBinding(panel, true);

        cbxRival.addListener(Events.SelectionChange, new Listener<BaseEvent>() {
            @Override
            public void handleEvent(BaseEvent be) {

                txtRival.setValue(cbxRival.getRivalElegido().getNombreRival());//  
                cbxTorneo.seleccionar(cbxRival.getRivalElegido().getTorneo().getId());

            }
        });
        txtRival.addListener(Events.OnBlur, new Listener<BaseEvent>() {
            @Override
            public void handleEvent(BaseEvent be) {

                txtRival.setValue(txtRival.getValue().toUpperCase());//             

            }
        });
        /**
         * Para escuchar la tecla enter
         */
        cbxRival.addKeyListener(new KeyListener() {

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

    public RPCAdminRivalesAsync getServiceRivales() {
        RPCAdminRivalesAsync svc = (RPCAdminRivalesAsync) GWT.create(RPCAdminRivales.class);
        ServiceDefTarget endpoint = (ServiceDefTarget) svc;
        endpoint.setServiceEntryPoint("services/RPCAdminRivales");
        return svc;
    }

    public void modificar() {
        pnlExito.setVisible(false);
        pnlErrores.setVisible(false);
        if (panel.isValid()) {

            Deportista deportistaComodin = new Deportista(txtRival.getValue(), cbxTorneo.getTorneosElegido().getCategoria(), true);
            deportistaComodin.setId(cbxRival.getRivalElegido().getJugadorComodin().getId());
            getServiceDeportista().guardarEntidad(deportistaComodin, new AsyncCallback<RespuestaRPC<Deportista>>() {

                @Override
                public void onFailure(Throwable caught) {
                    pnlErrores.limpiar();
                    pnlErrores.setVisible(true);
                    pnlErrores.aniadir("No Modificó el jugador comodín");
                }

                @Override
                public void onSuccess(RespuestaRPC<Deportista> result) {
                    Rivales rival = new Rivales();
                    rival.setId(cbxRival.getRivalElegido().getId());
                    rival.setTorneo(cbxTorneo.getTorneosElegido());
                    rival.setNombreRival(txtRival.getValue().toUpperCase());
                    rival.setJugadorComodin(cbxRival.getRivalElegido().getJugadorComodin());

                    getServiceRivales().guardarEntidad(rival, new AsyncCallback() {

                        @Override
                        public void onFailure(Throwable caught) {
                            pnlErrores.limpiar();
                            pnlErrores.setVisible(true);
                            pnlErrores.aniadir("No Modificó el torneo");
                        }

                        @Override
                        public void onSuccess(Object result) {
                            pnlExito.definirTexto("Se Modificó correctamente el rival");
                            pnlExito.setVisible(true);
                        }

                    });
                    txtRival.reset();
                    cbxRival.recargar();
                    cbxTorneo.recargar();
                }
            });

        } else {
            pnlErrores.limpiar();
            pnlErrores.aniadir("Debe llenar todos los campos");
            pnlErrores.setVisible(true);
        }
    }

    public RPCAdminDeportistaAsync getServiceDeportista() {
        RPCAdminDeportistaAsync svc = (RPCAdminDeportistaAsync) GWT.create(RPCAdminDeportista.class);
        ServiceDefTarget endpoint = (ServiceDefTarget) svc;
        endpoint.setServiceEntryPoint("services/RPCAdminDeportista");
        return svc;
    }
}
