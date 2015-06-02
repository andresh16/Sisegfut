//
///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package co.com.sisegfut.client.administracion.EstadoAsistencia;
//
//import co.com.sisegfut.client.aaI18N.Main;
//import co.com.sisegfut.client.datos.dominio.EstadoAsistencia;
//import co.com.sisegfut.client.util.Resources;
//import co.com.sisegfut.client.util.rpc.RPCAdminEstadoAsistencia;
//import co.com.sisegfut.client.util.rpc.RPCAdminEstadoAsistenciaAsync;
//import com.extjs.gxt.ui.client.Style;
//import com.extjs.gxt.ui.client.binding.FormBinding;
//import com.extjs.gxt.ui.client.event.ButtonEvent;
//import com.extjs.gxt.ui.client.event.SelectionListener;
//import com.extjs.gxt.ui.client.widget.LayoutContainer;
//import com.extjs.gxt.ui.client.widget.MessageBox;
//import com.extjs.gxt.ui.client.widget.button.Button;
//import com.extjs.gxt.ui.client.widget.form.FormButtonBinding;
//import com.extjs.gxt.ui.client.widget.form.FormPanel;
//import com.extjs.gxt.ui.client.widget.form.TextField;
//import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
//import com.google.gwt.core.client.GWT;
//import com.google.gwt.user.client.Element;
//import com.google.gwt.user.client.rpc.AsyncCallback;
//import com.google.gwt.user.client.rpc.ServiceDefTarget;
//
///**
// *
// * @author anfeh_000
// */
//public class PanelCrearEstadoAsistencia extends LayoutContainer {
//
//    FormPanel panel = new FormPanel();
//    private FormBinding formBindings;
//    TextField<String> txtEstadoAsistencia = new TextField<String>();
//
//    /**
//     * Contiene los textos a presentar en la interfaz web segun el idioma
//     */
//    private Main myConstants;
//
//    public PanelCrearEstadoAsistencia() {
//    }
//
//    @Override
//    protected void onRender(Element parent, int index) {
//
//        super.onRender(parent, index);
//        // setLayout(new FillLayout());
//        myConstants = (Main) GWT.create(Main.class);
//
//        panel.setFrame(true);
//        panel.setHeaderVisible(false);
//        txtEstadoAsistencia.setName("EstadoAsistencia");
//        txtEstadoAsistencia.setFieldLabel("Estado Asistencia");
//        txtEstadoAsistencia.setAllowBlank(false);
//        txtEstadoAsistencia.setToolTip("Digite un Estado Asistencia que desee guardar");
//
//        Button btnCrear = new Button(" Crear", ListenerCrear(1));
//        btnCrear.setArrowAlign(Style.ButtonArrowAlign.BOTTOM);
//        btnCrear.setIcon(Resources.ICONS.iconoCrear());
//
//        panel.add(txtEstadoAsistencia);
//        panel.addButton(btnCrear);
//
//        FormButtonBinding binding = new FormButtonBinding(panel);
//        binding.addButton(btnCrear);
//        
//        panel.setButtonAlign(Style.HorizontalAlignment.CENTER);
//
//        formBindings = new FormBinding(panel, true);
//
//        
//
//        add(panel, new BorderLayoutData(Style.LayoutRegion.CENTER));
//
//    }
//
//    public SelectionListener<ButtonEvent> ListenerCrear(final int tipo) {
//        return new SelectionListener<ButtonEvent>() {
//            @Override
//            public void componentSelected(ButtonEvent ce) {
//                if (panel.isValid()) {
//                    EstadoAsistencia estadoAsistencia =new EstadoAsistencia();
//                   estadoAsistencia.setEstadoAsistencia(txtEstadoAsistencia.getValue());
//                    
//                    getServiceEstadoAsistencia().guardarEntidad(estadoAsistencia, new AsyncCallback() {
//
//                       @Override
//                       public void onFailure(Throwable caught) {
//                      MessageBox.alert("Error", "No guardo el Estado Asistencia", null);}
//
//                       @Override
//                       public void onSuccess(Object result) {
//                       MessageBox.alert("Crear", "Guardo correctamente el Estado Asistencia", null);}
//                       
//                   });
//                    txtEstadoAsistencia.reset();
//                }
//            }
//        };
//    }
//
//    public RPCAdminEstadoAsistenciaAsync getServiceEstadoAsistencia() {
//        RPCAdminEstadoAsistenciaAsync svc = (RPCAdminEstadoAsistenciaAsync) GWT.create(RPCAdminEstadoAsistencia.class);
//        ServiceDefTarget endpoint = (ServiceDefTarget) svc;
//        endpoint.setServiceEntryPoint("services/RPCAdminEstadoAsistencia");
//        return svc;
//    }
//}
