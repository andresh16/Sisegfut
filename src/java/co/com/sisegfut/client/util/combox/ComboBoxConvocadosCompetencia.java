/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.client.util.combox;

import co.com.sisegfut.client.datos.dominio.Deportista;
import co.com.sisegfut.client.util.rpc.RPCAdminConvocadosComp;
import co.com.sisegfut.client.util.rpc.RPCAdminConvocadosCompAsync;
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
 * @author Andres Hurtado
 */
public class ComboBoxConvocadosCompetencia extends ComboBox<BeanModel> {

    private ListLoader<ListLoadResult<ModelData>> loader;
    private ListStore<BeanModel> storeCombo;
    

    public ComboBoxConvocadosCompetencia(final Long idCompetencia) {

        final RPCAdminConvocadosCompAsync svc = (RPCAdminConvocadosCompAsync) GWT.create(RPCAdminConvocadosComp.class);
        ServiceDefTarget endpoint = (ServiceDefTarget) svc;
        endpoint.setServiceEntryPoint("services/RPCAdminConvocadosComp");

        // proxy and reader  
        RpcProxy<List<Deportista>> proxy = new RpcProxy<List<Deportista>>() {

            @Override
            public void load(Object loadConfig, AsyncCallback<List<Deportista>> callback) {
                svc.getConvocados(idCompetencia, callback);
            }
        };

        BeanModelReader reader = new BeanModelReader();

        // loader and store  
        loader = new BaseListLoader<ListLoadResult<ModelData>>(proxy, reader);
        loader.setRemoteSort(false);
        storeCombo = new ListStore<BeanModel>(loader);

//        recargar();
        setEmptyText("Seleccione un deportista");
        setDisplayField("nombreCompleto");
        setWidth(150);
//        if (tipoConvocado.equalsIgnoreCase("t")) {
//            setTemplate(getCambioSale());
//        } else {
//            setTemplate(getCambioEntra());
//        }
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
     *
     * @param id
     */
    public void seleccionar(Long id) {

        List<BeanModel> lista = storeCombo.getModels();
        for (BeanModel beanModel : lista) {
            Deportista entidad = (Deportista) beanModel.getBean();
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
    public Deportista getDeportistaElegido() {
        if (getSelection().size() > 0) {
            BeanModel beanModel = ((BeanModel) getSelection().get(0));
            return (Deportista) beanModel.getBean();
        } else {
            return null;
        }
    }

    // En la imagen averiguar como traer el id del tipo de cuenta
    private native String getCambioEntra() /*-{ 
     return  [ 
     '<tpl for=".">', 
     '<div class="x-combo-list-item"><img width="20" height="20" src="imagenes/iconos/in.png "/>&nbsp;{nombreCompleto}</div>', 
     '</tpl>' 
     ].join(""); 
     }-*/;

    // En la imagen averiguar como traer el id del tipo de cuenta
    private native String getCambioSale() /*-{ 
     return  [ 
     '<tpl for=".">', 
     '<div class="x-combo-list-item"><img width="20" height="20" src="imagenes/iconos/out.png "/>&nbsp;{nombreCompleto}</div>', 
     '</tpl>' 
     ].join(""); 
     }-*/;

    //Para ponerle imagen al combo
    //  <img width="20" height="20" src="imagenes/iconos/in.png "/>
    //  <img width="20" height="20" src="imagenes/iconos/out.png "/>
}
