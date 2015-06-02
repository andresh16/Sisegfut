/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.client.util.combox;

import co.com.sisegfut.client.datos.dominio.Personal;
import co.com.sisegfut.client.util.rpc.RPCAdminCuerpoTecComp;
import co.com.sisegfut.client.util.rpc.RPCAdminCuerpoTecCompAsync;
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
public class ComboBoxPersonalCompetencia extends ComboBox<BeanModel> {

    private ListLoader<ListLoadResult<ModelData>> loader;
    private ListStore<BeanModel> storeCombo;
    private Long idCompetencia = null;

    public ComboBoxPersonalCompetencia() {

        final RPCAdminCuerpoTecCompAsync svc = (RPCAdminCuerpoTecCompAsync) GWT.create(RPCAdminCuerpoTecComp.class);
        ServiceDefTarget endpoint = (ServiceDefTarget) svc;
        endpoint.setServiceEntryPoint("services/RPCAdminCuerpoTecComp");

        // proxy and reader  
        RpcProxy<List<Personal>> proxy = new RpcProxy<List<Personal>>() {

            @Override
            public void load(Object loadConfig, AsyncCallback<List<Personal>> callback) {
                svc.getPersonalCompetencia(idCompetencia, callback);
            }
        };

        BeanModelReader reader = new BeanModelReader();

        // loader and store  
        loader = new BaseListLoader<ListLoadResult<ModelData>>(proxy, reader);
        loader.setRemoteSort(false);
        storeCombo = new ListStore<BeanModel>(loader);

//        recargar();
        setEmptyText("Seleccione una persona");
        setDisplayField("label");
        setWidth(150);
        setStore(storeCombo);
        setTypeAhead(true);
        //setTriggerAction(TriggerAction.ALL);
    }

    public void setIdCompetencia(Long idCompetencia) {
        this.idCompetencia = idCompetencia;
    }
    
    

    /**
     * Recarga el store del combobox
     */
    public void recargar() {
        storeCombo.removeAll();
        loader.load();
//        loader.load();
    }

    /**
     * Selecciona una entidad del combo
     *
     * @param id
     */
    public void seleccionar(Long id) {

        List<BeanModel> lista = storeCombo.getModels();
        for (BeanModel beanModel : lista) {
            Personal entidad = (Personal) beanModel.getBean();
            if (entidad.getId().longValue() == id) {
                setValue(beanModel);
                break;
            }
        }
    }

    /**
     * Retorna la entidad elegida
     *
     * @return
     */
    public Personal getPersonaElegida() {
        if (getSelection().size() > 0) {
            BeanModel beanModel = ((BeanModel) getSelection().get(0));
            return (Personal) beanModel.getBean();
        } else {
            return null;
        }
    }

}
