/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.client.util.combox;

import co.com.sisegfut.client.datos.dominio.TipoCuenta;
import co.com.sisegfut.client.util.rpc.RPCAdminUsuarios;
import co.com.sisegfut.client.util.rpc.RPCAdminUsuariosAsync;
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
public class ComboBoxTipoCuenta extends ComboBox<BeanModel>{
    
    private ListLoader<ListLoadResult<ModelData>> loader;
    private ListStore<BeanModel> storeCombo;

    public ComboBoxTipoCuenta() {
        
        
       final RPCAdminUsuariosAsync svc = (RPCAdminUsuariosAsync) GWT.create(RPCAdminUsuarios.class);
        ServiceDefTarget endpoint = (ServiceDefTarget) svc;
        endpoint.setServiceEntryPoint("services/RPCAdminUsuarios");
        
        // proxy and reader  
        RpcProxy<List<TipoCuenta>> proxy = new RpcProxy<List<TipoCuenta>>() {

            @Override
            public void load(Object loadConfig, AsyncCallback<List<TipoCuenta>> callback) {
                svc.getTipoCuenta(callback);
            }
        };

        BeanModelReader reader = new BeanModelReader();

        // loader and store  
        loader = new BaseListLoader<ListLoadResult<ModelData>>(proxy, reader);
        storeCombo = new ListStore<BeanModel>(loader);

        recargar();        
        
        setEmptyText("Seleccione un Tipo de Cuenta...");
        setDisplayField("nombreTipoCuenta");
        setWidth(150);
        setTemplate(getTipoCuentaTemplate());
        setStore(storeCombo);
        setTypeAhead(true);
        //setTriggerAction(TriggerAction.ALL);
    }

    /**
     * Recarga el store del combobox
     */
    public void recargar() {
         clearSelections();
       // removeEmptyText();
       storeCombo.removeAll();
        loader.load();
    }

    /**
     * Selecciona una entidad del combo
     * @param id 
     */
    public void seleccionar(Long id){
        List<BeanModel> lista = storeCombo.getModels();
        for (BeanModel beanModel : lista) {
            TipoCuenta entidad = (TipoCuenta)beanModel.getBean();
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
    public TipoCuenta getTipoCuentaElegido() {
        if (getSelection().size() > 0) {
            BeanModel beanModel = ((BeanModel) getSelection().get(0));
            return (TipoCuenta) beanModel.getBean();
        }else{
            return null;
        }
    }
    
    private native String getTipoCuentaTemplate() /*-{ 
     return  [ 
     '<tpl for=".">', 
     '<div class="x-combo-list-item"><img width="20" height="20" src="imagenes/tiposcuentas/{id}TipoCuenta.png "/>&nbsp; {nombreTipoCuenta}</div>', 
     '</tpl>' 
     ].join(""); 
     }-*/;
    
}
