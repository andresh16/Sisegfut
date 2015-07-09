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
public class ComboBoxCategoria extends ComboBox<BeanModel>{
    
    private ListLoader<ListLoadResult<ModelData>> loader;
    private ListStore<BeanModel> storeCombo;
    private Usuarios usuarioLogueado;
    public static final Integer ACTIVOS = 1;
    public static final Integer INACTIVOS = 2;
    private Integer tipo;
    
    public ComboBoxCategoria(final int tipo) {
        this.tipo = tipo;
        usuarioLogueado = BeansLocales.getUsuario();
        
       final RPCAdminCategoriaAsync svc = (RPCAdminCategoriaAsync) GWT.create(RPCAdminCategoria.class);
        ServiceDefTarget endpoint = (ServiceDefTarget) svc;
        endpoint.setServiceEntryPoint("services/RPCAdminCategoria");
        
        // proxy and reader  
        RpcProxy<List<Categoria>> proxy = new RpcProxy<List<Categoria>>() {

            @Override
            public void load(Object loadConfig, AsyncCallback<List<Categoria>> callback) {
                svc.getEntidades(tipo, callback);
            }
        };

        BeanModelReader reader = new BeanModelReader();

        // loader and store  
        loader = new BaseListLoader<ListLoadResult<ModelData>>(proxy, reader);
        storeCombo = new ListStore<BeanModel>(loader);

        recargar();        
        
        setEmptyText("Seleccione una categoría");
        setDisplayField("nombrecategoria");
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
            Categoria entidad = (Categoria)beanModel.getBean();
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
    public Categoria getCategoriaElegida() {
        if (getSelection().size() > 0) {
            BeanModel beanModel = ((BeanModel) getSelection().get(0));
            return (Categoria) beanModel.getBean();
        }else{
            return null;
        }
    }

       
}

