/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.sisegfut.client.administracion.personal;

import co.com.sisegfut.client.aaI18N.Main;
import co.com.sisegfut.client.util.PanelErrores;
import co.com.sisegfut.client.util.PanelExito;
import co.com.sisegfut.client.util.Resources;
import co.com.sisegfut.client.util.combox.ComboBoxPersonal;
import co.com.sisegfut.client.util.rpc.RPCAdminPersonal;
import co.com.sisegfut.client.util.rpc.RPCAdminPersonalAsync;
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
public class PanelReactivarPersonal extends Window{

    
     FormPanel panel = new FormPanel();
     private FormBinding formBindings;
    private ComboBoxPersonal cbxPersonal;
     PanelAdminPersonal adminPersonal ;
      private PanelErrores pnlErrores;
    private PanelExito pnlExito;
    
    private Main myConstants = (Main) GWT.create(Main.class);
    
    public PanelReactivarPersonal(PanelAdminPersonal panelPadre) {
        setSize(350, 145);
       adminPersonal=panelPadre;
        setPlain(true);
        setModal(true);
        setBlinkModal(true);
        setHeading("Reactivar deportista");
        setLayout(new FillLayout());
        setResizable(false);
 
        
         FormData formData = new FormData("-20");
         panel.setFrame(true);
        panel.setHeaderVisible(false);
        
        cbxPersonal=new ComboBoxPersonal(2);
        
        cbxPersonal.setLabelSeparator("Deportista");
        cbxPersonal.setAllowBlank(false);
        
         Button btnReactivar = new Button("Reactivar", ListenerReactivar());
        btnReactivar.setArrowAlign(Style.ButtonArrowAlign.BOTTOM);
        btnReactivar.setIcon(Resources.ICONS.iconoRefrescar());
        
         pnlErrores = new PanelErrores();
        pnlExito = new PanelExito();

        panel.add(pnlErrores);
        panel.add(pnlExito);
        
        panel.add(cbxPersonal, formData);
        
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
                    
                    getServicePersonal().reactivarEntidad(cbxPersonal.getEntidadElegida().getId(), new AsyncCallback() {

                       @Override
                       public void onFailure(Throwable caught) {
                      MessageBox.alert("Error", "No se Reactivo el personal", null);}

                       @Override
                       public void onSuccess(Object result) {
                           
                      Info.display("Reactivar", "Se Reactivo correctamente el personal");
                       adminPersonal.cargar();
                       }
                       
                   });
                    
                    cbxPersonal.recargar();
                   cerrar();
                }
            }
        };
    }

    


  public RPCAdminPersonalAsync getServicePersonal() {
        RPCAdminPersonalAsync svc = (RPCAdminPersonalAsync) GWT.create(RPCAdminPersonal.class);
        ServiceDefTarget endpoint = (ServiceDefTarget) svc;
        endpoint.setServiceEntryPoint("services/RPCAdminPersonal");
        return svc;
    }
    
    public void cerrar(){
    this.close();
    
    }
    
}
