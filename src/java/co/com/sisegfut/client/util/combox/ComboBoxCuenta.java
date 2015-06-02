/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.client.util.combox;

import co.com.sisegfut.client.datos.dominio.Cuentas;
import co.com.sisegfut.client.datos.dominio.Usuarios;
import co.com.sisegfut.client.util.BeansLocales;
import co.com.sisegfut.client.util.rpc.RPCAdminCuenta;
import co.com.sisegfut.client.util.rpc.RPCAdminCuentaAsync;
import com.extjs.gxt.ui.client.data.BaseListLoader;
import com.extjs.gxt.ui.client.data.BeanModel;
import com.extjs.gxt.ui.client.data.BeanModelReader;
import com.extjs.gxt.ui.client.data.ListLoadResult;
import com.extjs.gxt.ui.client.data.ListLoader;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.RpcProxy;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import java.util.List;

/**
 *
 * @author fhurtado
 */
public class ComboBoxCuenta extends ComboBox<BeanModel>{
    
    private ListLoader<ListLoadResult<ModelData>> loader;
    private ListStore<BeanModel> storeCombo;
    private Usuarios usuarioLogueado;
    
    public ComboBoxCuenta() {
        
        usuarioLogueado = BeansLocales.getUsuario();
        
       final RPCAdminCuentaAsync svc = (RPCAdminCuentaAsync) GWT.create(RPCAdminCuenta.class);
        ServiceDefTarget endpoint = (ServiceDefTarget) svc;
        endpoint.setServiceEntryPoint("services/RPCAdminCuenta");
        
        // proxy and reader  
        RpcProxy<List<Cuentas>> proxy = new RpcProxy<List<Cuentas>>() {

            @Override
            public void load(Object loadConfig, AsyncCallback<List<Cuentas>> callback) {
                svc.getCuentas(usuarioLogueado.getId(), callback);
            }
        };

        BeanModelReader reader = new BeanModelReader();

        // loader and store  
        loader = new BaseListLoader<ListLoadResult<ModelData>>(proxy, reader);
        storeCombo = new ListStore<BeanModel>(loader);

        recargar();        
        
        setEmptyText("Seleccione una cuenta...");
        setDisplayField("nombrecuenta");
        setWidth(150);
        setTemplate(getSaldoTemplate());
        setStore(storeCombo);
        setTypeAhead(true);
        //setTriggerAction(TriggerAction.ALL);
    }

    /**
     * Recarga el store del combobox
     */
    public void recargar() {
        //storeCombo.removeAll();
        loader.load();
    }

    /**
     * Selecciona una entidad del combo
     * @param id 
     */
    public void seleccionar(Long id){
        
        
        
        List<BeanModel> lista = storeCombo.getModels();
        for (BeanModel beanModel : lista) {
            Cuentas entidad = (Cuentas)beanModel.getBean();
            if(entidad.getId().longValue()==id){
                setValue(beanModel);
                break;
            }
        }
    }
    /**
     * Retorna la entidad elegida
     * @return 
     */
    public Cuentas getCuentaElegido() {
        if (getSelection().size() > 0) {
            BeanModel beanModel = ((BeanModel) getSelection().get(0));
            return (Cuentas) beanModel.getBean();
        }else{
            return null;
        }
    }
    
    
    // En la imagen averiguar como traer el id del tipo de cuenta
       private native String getSaldoTemplate() /*-{ 
     return  [ 
     '<tpl for=".">', 
     '<div class="x-combo-list-item" qtip=" " qtitle="Saldo Actual ${saldo} - N° de cuenta: {numcuenta}" >{nombrecuenta}</div>', 
     '</tpl>' 
     ].join(""); 
     }-*/;
    
       //Para ponerle imagen al combo
     //  <img width="20" height="20" src="imagenes/tiposcuentas/{id}TipoCuenta.png "/>
       
       
}


    
