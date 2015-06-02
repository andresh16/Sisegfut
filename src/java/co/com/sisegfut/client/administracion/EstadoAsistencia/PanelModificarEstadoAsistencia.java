///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//
//package co.com.sisegfut.client.administracion.EstadoAsistencia;
//
//import co.com.sisegfut.client.datos.dominio.EstadoAsistencia;
//import co.com.sisegfut.client.util.Resources;
//import co.com.sisegfut.client.util.combox.ComboBoxEstadoAsistencia;
//import co.com.sisegfut.client.util.rpc.RPCAdminEstadoAsistencia;
//import co.com.sisegfut.client.util.rpc.RPCAdminEstadoAsistenciaAsync;
//import com.extjs.gxt.ui.client.Style;
//import com.extjs.gxt.ui.client.binding.FormBinding;
//import com.extjs.gxt.ui.client.event.BaseEvent;
//import com.extjs.gxt.ui.client.event.ButtonEvent;
//import com.extjs.gxt.ui.client.event.Events;
//import com.extjs.gxt.ui.client.event.Listener;
//import com.extjs.gxt.ui.client.event.SelectionListener;
//import com.extjs.gxt.ui.client.widget.LayoutContainer;
//import com.extjs.gxt.ui.client.widget.MessageBox;
//import com.extjs.gxt.ui.client.widget.button.Button;
//import com.extjs.gxt.ui.client.widget.form.FormButtonBinding;
//import com.extjs.gxt.ui.client.widget.form.FormPanel;
//import com.extjs.gxt.ui.client.widget.form.TextField;
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
//public class PanelModificarEstadoAsistencia extends LayoutContainer{
//    
//    FormPanel panel = new FormPanel();
//    private FormBinding formBindings;
//    private ComboBoxEstadoAsistencia cbxEstadoAsistencia;
//    TextField<String> txtEstadoAsistencia = new TextField<String>();
//
//    public PanelModificarEstadoAsistencia() {
//    }
//    
//    @Override
//    protected void onRender(Element parent, int index) {
//
//        super.onRender(parent, index);
//        
//        
//        FormData formData = new FormData("-20");
//        // setLayout(new FillLayout());
//        panel.setFrame(true);
//        panel.setHeaderVisible(false);
//        txtEstadoAsistencia.setName("EstadoAsistencia");
//        txtEstadoAsistencia.setFieldLabel("Estado Asistencia");
//        txtEstadoAsistencia.setAllowBlank(false);
//        txtEstadoAsistencia.setToolTip("Digite un Estado Asistencia que desee guardar");
//        
//        cbxEstadoAsistencia=new ComboBoxEstadoAsistencia(ComboBoxEstadoAsistencia.ACTIVOS);
//        
//        cbxEstadoAsistencia.setLabelSeparator("Estado Asistencia");
//        cbxEstadoAsistencia.setAllowBlank(false);
//        
//       // 
//
//        Button btnModificar = new Button(" Modificar", ListenerModificar());
//        btnModificar.setArrowAlign(Style.ButtonArrowAlign.BOTTOM);
//        btnModificar.setIcon(Resources.ICONS.iconoModificar());
//        
//        panel.add(cbxEstadoAsistencia, formData);
//        panel.add(txtEstadoAsistencia, formData);
//        panel.addButton(btnModificar);
//
//        FormButtonBinding binding = new FormButtonBinding(panel);
//        binding.addButton(btnModificar);
//        
//        panel.setButtonAlign(Style.HorizontalAlignment.CENTER);
//
//        formBindings = new FormBinding(panel, true);
//
// 
//        cbxEstadoAsistencia.addListener(Events.SelectionChange, new Listener<BaseEvent>() {
//            @Override
//            public void handleEvent(BaseEvent be) {
//
//               txtEstadoAsistencia.setValue(cbxEstadoAsistencia.getEstadoAsistenciaElegido().getEstadoAsistencia().toString());
//                // MessageBox.info("Combo tipo mov", "Entro al evento cambio selecion", null);
//
//            }
//        });
//        
//        add(panel, new BorderLayoutData(Style.LayoutRegion.CENTER));
//
//    }
//    public SelectionListener<ButtonEvent> ListenerModificar() {
//        return new SelectionListener<ButtonEvent>() {
//            @Override
//            public void componentSelected(ButtonEvent ce) {
//                if (panel.isValid()) {
//                    
//                   EstadoAsistencia estadoAsistencia =new EstadoAsistencia();
//                   estadoAsistencia.setId(cbxEstadoAsistencia.getEstadoAsistenciaElegido().getId());
//                   estadoAsistencia.setEstadoAsistencia(txtEstadoAsistencia.getValue());
//                    
//                    getServiceEstadoAsistencia().guardarEntidad(estadoAsistencia, new AsyncCallback() {
//
//                       @Override
//                       public void onFailure(Throwable caught) {
//                      MessageBox.alert("Error", "No Modifico el Estado Asistencia", null);}
//
//                       @Override
//                       public void onSuccess(Object result) {
//                       MessageBox.alert("Modificar", "Se Modifico correctamente el Estado Asistencia", null);}
//                       
//                   });
//                    txtEstadoAsistencia.reset();
//                    cbxEstadoAsistencia.recargar();
//                }
//            }
//        };
//    }
//
//    
//
//
//  public RPCAdminEstadoAsistenciaAsync getServiceEstadoAsistencia() {
//        RPCAdminEstadoAsistenciaAsync svc = (RPCAdminEstadoAsistenciaAsync) GWT.create(RPCAdminEstadoAsistencia.class);
//        ServiceDefTarget endpoint = (ServiceDefTarget) svc;
//        endpoint.setServiceEntryPoint("services/RPCAdminEstadoAsistencia");
//        return svc;
//    }
//    
//}
