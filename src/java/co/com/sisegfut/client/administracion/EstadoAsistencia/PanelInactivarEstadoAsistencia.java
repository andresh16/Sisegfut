///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//
//package co.com.sisegfut.client.administracion.EstadoAsistencia;
//
//import co.com.sisegfut.client.util.Resources;
//import co.com.sisegfut.client.util.combox.ComboBoxEstadoAsistencia;
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
//public class PanelInactivarEstadoAsistencia extends LayoutContainer{
//    
//    FormPanel panel = new FormPanel();
//    private FormBinding formBindings; 
//    private ComboBoxEstadoAsistencia cbxEstadoAsistencia;
//
//    public PanelInactivarEstadoAsistencia() {
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
//     
//        
//        cbxEstadoAsistencia=new ComboBoxEstadoAsistencia(ComboBoxEstadoAsistencia.ACTIVOS);
//        
//        cbxEstadoAsistencia.setLabelSeparator("Estado Asistencia");
//        cbxEstadoAsistencia.setAllowBlank(false);
//        
//       // 
//
//        Button btnInactivar = new Button("Inactivar", ListenerInactivar());
//        btnInactivar.setArrowAlign(Style.ButtonArrowAlign.BOTTOM);
//        btnInactivar.setIcon(Resources.ICONS.iconoEliminar());
//        
//        panel.add(cbxEstadoAsistencia, formData);
//        
//        panel.addButton(btnInactivar);
//
//        FormButtonBinding binding = new FormButtonBinding(panel);
//        binding.addButton(btnInactivar);
//        panel.setButtonAlign(Style.HorizontalAlignment.CENTER);
//
//        formBindings = new FormBinding(panel, true);
//        
//        add(panel, new BorderLayoutData(Style.LayoutRegion.CENTER));
//
//    }
//    
//    public SelectionListener<ButtonEvent> ListenerInactivar() {
//        return new SelectionListener<ButtonEvent>() {
//            @Override
//            public void componentSelected(ButtonEvent ce) {
//                if (panel.isValid()) {          
//                    
//                    getServiceEstadoAsistencia().eliminarEntidad(cbxEstadoAsistencia.getEstadoAsistenciaElegido().getId(), new AsyncCallback() {
//
//                       @Override
//                       public void onFailure(Throwable caught) {
//                      MessageBox.alert("Error", "No se Inactivo el Estado Asistencia", null);}
//
//                       @Override
//                       public void onSuccess(Object result) {
//                       MessageBox.alert("Inactivar", "Se Inactivo correctamente el Estado Asistencia", null);}
//                       
//                   });
//                    cbxEstadoAsistencia.recargar();
//                }
//            }
//        };
//    }
//
//    
//
//
//   public RPCAdminEstadoAsistenciaAsync getServiceEstadoAsistencia() {
//        RPCAdminEstadoAsistenciaAsync svc = (RPCAdminEstadoAsistenciaAsync) GWT.create(RPCAdminEstadoAsistencia.class);
//        ServiceDefTarget endpoint = (ServiceDefTarget) svc;
//        endpoint.setServiceEntryPoint("services/RPCAdminEstadoAsistencia");
//        return svc;
//    }
//    
//}
