/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.client.util.combox;

import co.com.sisegfut.client.datos.dominio.Categoria;
import co.com.sisegfut.client.datos.dominio.Usuarios;
import co.com.sisegfut.client.util.BeansLocales;
import co.com.sisegfut.client.util.rpc.RPCAdminCategoria;
import co.com.sisegfut.client.util.rpc.RPCAdminCategoriaAsync;
import co.com.sisegfut.client.util.rpc.RPCMaestroAsync;
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
public class ComboBoxAutoCategoria extends ComboBox {

    private ListLoader<ListLoadResult<ModelData>> loader;
    private ListStore<BeanModel> storeCombo;
     private Usuarios usuarioLogueado;


    public ComboBoxAutoCategoria(int tipo) {
        
        
        
        
     usuarioLogueado = BeansLocales.getUsuario();
     
        final RPCAdminCategoriaAsync svc = (RPCAdminCategoriaAsync) GWT.create(RPCAdminCategoria.class);
        ServiceDefTarget endpoint = (ServiceDefTarget) svc;
        endpoint.setServiceEntryPoint("services/RPCAdminCategoria");

        RpcProxy<List<Categoria>> proxy = new RpcProxy<List<Categoria>>() {
            @Override
            public void load(Object loadConfig, AsyncCallback<List<Categoria>> callback) {
                                   
                svc.getCategorias((ModelData)loadConfig,usuarioLogueado.getId(), callback);
            }
        };

        BeanModelReader reader = new BeanModelReader();

        // loader and store  
        loader = new BaseListLoader<ListLoadResult<ModelData>>(proxy, reader);
        storeCombo = new ListStore<BeanModel>(loader);

        recargar();

        setEmptyText("Escriba o busque su categoria...");
        setDisplayField("label");
        setWidth(150);
        setHideTrigger(true);
        setStore(storeCombo);
        setMinChars(1);
        setTypeAhead(true);
        setTriggerAction(TriggerAction.ALL);
        
        
        
    }

    protected RPCMaestroAsync<Categoria> getServicioRPC() {
        final RPCAdminCategoriaAsync svc = (RPCAdminCategoriaAsync) GWT.create(RPCAdminCategoria.class);
        ServiceDefTarget endpoint = (ServiceDefTarget) svc;
        endpoint.setServiceEntryPoint("services/RPCAdminCategoria");

        return null;
    }

    /**
     * Selecciona una entidad del combo
     *
     * @param id
     */
    public void seleccionar(Long id) {
        List<BeanModel> lista = storeCombo.getModels();
        for (BeanModel beanModel : lista) {
            Categoria entidad = (Categoria) beanModel.getBean();
            if (entidad.getId().longValue() == id) {
                setValue(beanModel);
                break;
            }
        }
    }

    /**
     * Retorna la entidad elegida
     *
     * @return Categorias
     */
    public Categoria getCategoriaElegida() {
        if (getSelection().size() > 0) {
            BeanModel beanModel = ((BeanModel) getSelection().get(0));
            return (Categoria) beanModel.getBean();
        } else {
            return null;
        }
    }
   

    
    
    public void recargar() {
    clearSelections();
       // removeEmptyText();
       storeCombo.removeAll();
        loader.load();
    }
}
