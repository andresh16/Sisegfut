/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.client.util.combox;

import co.com.sisegfut.client.datos.dominio.Rol;
import co.com.sisegfut.client.util.rpc.RPCAdminUsuarios;
import co.com.sisegfut.client.util.rpc.RPCAdminUsuariosAsync;
import com.extjs.gxt.ui.client.data.*;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import java.util.List;

/**
 *
 * @author josorio
 */
public class ComboBoxRoles extends ComboBox<BeanModel> {

    private ListLoader<ListLoadResult<ModelData>> loader;
    private ListStore<BeanModel> storeCombo;

    public ComboBoxRoles() {

        final RPCAdminUsuariosAsync svc = (RPCAdminUsuariosAsync) GWT.create(RPCAdminUsuarios.class);
        ServiceDefTarget endpoint = (ServiceDefTarget) svc;
        endpoint.setServiceEntryPoint("services/RPCAdminUsuarios");
        
        // proxy and reader  
        RpcProxy<List<Rol>> proxy = new RpcProxy<List<Rol>>() {

            @Override
            public void load(Object loadConfig, AsyncCallback<List<Rol>> callback) {
                svc.getRoles(callback);
            }
        };

        BeanModelReader reader = new BeanModelReader();

        // loader and store  
        loader = new BaseListLoader<ListLoadResult<ModelData>>(proxy, reader);
        storeCombo = new ListStore<BeanModel>(loader);

        recargar();        
        
        setEmptyText("Seleccione un rol...");
        setDisplayField("label");
        setWidth(150);
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
            Rol entidad = (Rol)beanModel.getBean();
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
    public Rol getRolElegido() {
        if (getSelection().size() > 0) {
            BeanModel beanModel = ((BeanModel) getSelection().get(0));
            return (Rol) beanModel.getBean();
        }else{
            return null;
        }
    }

//    /**
//     * Construye el objeto servicio para realizar el llamado remoto
//     *
//     * @return Objeto de servicio construido
//     */
//    public static RPCAdministracion2UsuariosAsync getService() {
//        return (RPCAdministracion2UsuariosAsync) PuertoRPC.setRpcEndPointUrl(GWT.create(RPCAdministracion2Usuarios.class));
//    }
}
