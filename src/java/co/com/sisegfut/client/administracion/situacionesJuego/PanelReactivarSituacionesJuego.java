///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package co.com.sisegfut.client.administracion.situacionesJuego;
//
//import co.com.sisegfut.client.util.PanelErrores;
//import co.com.sisegfut.client.util.PanelExito;
//import co.com.sisegfut.client.util.Resources;
//import co.com.sisegfut.client.util.combox.ComboBoxSituacionesJuego;
//import co.com.sisegfut.client.util.rpc.RPCAdminSituacionesJuego;
//import co.com.sisegfut.client.util.rpc.RPCAdminSituacionesJuegoAsync;
//import com.extjs.gxt.ui.client.Style;
//import com.extjs.gxt.ui.client.binding.FormBinding;
//import com.extjs.gxt.ui.client.event.ButtonEvent;
//import com.extjs.gxt.ui.client.event.ComponentEvent;
//import com.extjs.gxt.ui.client.event.KeyListener;
//import com.extjs.gxt.ui.client.event.SelectionListener;
//import com.extjs.gxt.ui.client.widget.LayoutContainer;
//import com.extjs.gxt.ui.client.widget.MessageBox;
//import com.extjs.gxt.ui.client.widget.button.Button;
//import com.extjs.gxt.ui.client.widget.form.FormButtonBinding;
//import com.extjs.gxt.ui.client.widget.form.FormPanel;
//import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
//import com.extjs.gxt.ui.client.widget.layout.FormData;
//import com.google.gwt.core.client.GWT;
//import com.google.gwt.user.client.Element;
//import com.google.gwt.user.client.rpc.AsyncCallback;
//import com.google.gwt.user.client.rpc.ServiceDefTarget;
//
///**
// *
// * @author anfeh_000
// */
//public class PanelReactivarSituacionesJuego extends LayoutContainer {
//
//    FormPanel panel = new FormPanel();
//    private FormBinding formBindings;
//    private ComboBoxSituacionesJuego cbxSituacionesJuego;
//    private PanelErrores pnlErrores;
//    private PanelExito pnlExito;
//
//    public PanelReactivarSituacionesJuego() {
//    }
//
//    @Override
//    protected void onRender(Element parent, int index) {
//
//        super.onRender(parent, index);
//
//        FormData formData = new FormData("-20");
//        // setLayout(new FillLayout());
//        panel.setFrame(true);
//        panel.setHeaderVisible(false);
//
//        cbxSituacionesJuego = new ComboBoxSituacionesJuego(ComboBoxSituacionesJuego.INACTIVOS);
//
//        cbxSituacionesJuego.setLabelSeparator("Situación de Juego");
//        cbxSituacionesJuego.setAllowBlank(false);
//
//        // 
//        Button btnReactivar = new Button("Reactivar", ListenerReactivar());
//        btnReactivar.setArrowAlign(Style.ButtonArrowAlign.BOTTOM);
//        btnReactivar.setIcon(Resources.ICONS.iconoRefrescar());
//        pnlErrores = new PanelErrores();
//        pnlExito = new PanelExito();
//
//        panel.add(pnlErrores);
//        panel.add(pnlExito);
//        panel.add(cbxSituacionesJuego, formData);
//
//        panel.addButton(btnReactivar);
//
//        FormButtonBinding binding = new FormButtonBinding(panel);
//        binding.addButton(btnReactivar);
//
//        panel.setButtonAlign(Style.HorizontalAlignment.CENTER);
//
//        formBindings = new FormBinding(panel, true);
//
//        add(panel, new BorderLayoutData(Style.LayoutRegion.CENTER));
//
//        cbxSituacionesJuego.addKeyListener(new KeyListener() {
//
//            @Override
//            public void componentKeyPress(ComponentEvent event) {
//                if (event.getKeyCode() == 13) {
//                    //(button.click or the method called in the button click event)
//                    System.out.println("Entro");
//
//                    reactivar();
//                }
//            }
//        });
//
//    }
//
//    public SelectionListener<ButtonEvent> ListenerReactivar() {
//        return new SelectionListener<ButtonEvent>() {
//            @Override
//            public void componentSelected(ButtonEvent ce) {
//                reactivar();
//            }
//        };
//    }
//
//    public RPCAdminSituacionesJuegoAsync getServiceSituacionesJuego() {
//        RPCAdminSituacionesJuegoAsync svc = (RPCAdminSituacionesJuegoAsync) GWT.create(RPCAdminSituacionesJuego.class);
//        ServiceDefTarget endpoint = (ServiceDefTarget) svc;
//        endpoint.setServiceEntryPoint("services/RPCAdminSituacionesJuego");
//        return svc;
//    }
//
//    public void reactivar() {
//        pnlExito.setVisible(false);
//        pnlErrores.setVisible(false);
//        if (panel.isValid()) {
//
//            getServiceSituacionesJuego().reactivarEntidad(cbxSituacionesJuego.getSituacionJuegoElegido().getId(), new AsyncCallback() {
//
//                @Override
//                public void onFailure(Throwable caught) {
//                    pnlErrores.limpiar();
//                    pnlErrores.setVisible(true);
//                    pnlErrores.aniadir("No se Reactivo la Situación de Juego");
//                }
//
//                @Override
//                public void onSuccess(Object result) {
//                    MessageBox.alert("Reactivar", "", null);
//                    pnlExito.definirTexto("Se Reactivo correctamente la Situación de Juego");
//                    pnlExito.setVisible(true);
//                }
//
//            });
//            cbxSituacionesJuego.recargar();
//        } else // Si hay errores
//        {
//            pnlErrores.limpiar();
//            pnlErrores.setVisible(true);
//            pnlErrores.aniadir("Debe seleccionar una situación de juego");
//        }
//    }
//
//}
