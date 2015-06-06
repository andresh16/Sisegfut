/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.client.util.combox;

import co.com.sisegfut.client.datos.dominio.Rivales;
import co.com.sisegfut.client.datos.dominio.Usuarios;
import co.com.sisegfut.client.util.BeansLocales;
import co.com.sisegfut.client.util.rpc.RPCAdminRivales;
import co.com.sisegfut.client.util.rpc.RPCAdminRivalesAsync;
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
 * @author ManuelAlejandro
 */
public class ComboBoxRival extends ComboBox<BeanModel> {

    private ListLoader<ListLoadResult<ModelData>> loader;
    private ListStore<BeanModel> storeCombo;
    private Usuarios usuarioLogueado;
    public static final Integer ACTIVOS = 1;
    public static final Integer INACTIVOS = 2;
    private Integer tipo;
    public Long idTorneoElegido = null;

    public ComboBoxRival(final int tipo) {

        this.tipo = tipo;
        usuarioLogueado = BeansLocales.getUsuario();

        final RPCAdminRivalesAsync svc = (RPCAdminRivalesAsync) GWT.create(RPCAdminRivales.class);
        ServiceDefTarget endpoint = (ServiceDefTarget) svc;
        endpoint.setServiceEntryPoint("services/RPCAdminRivales");

        // proxy and reader  
        RpcProxy<List<Rivales>> proxy = new RpcProxy<List<Rivales>>() {

            @Override
            public void load(Object loadConfig, AsyncCallback<List<Rivales>> callback) {
                if (idTorneoElegido != null) {
                    svc.getRivalesxTorneo(idTorneoElegido, callback);
                } else {
                    svc.getEntidades(tipo, callback);
                }
            }
        };

        BeanModelReader reader = new BeanModelReader();

        // loader and store  
        loader = new BaseListLoader<ListLoadResult<ModelData>>(proxy, reader);
        storeCombo = new ListStore<BeanModel>(loader);

        recargar();

        setEmptyText("Seleccione un rival...");
        setDisplayField("nombreRival");
        setWidth(150);
        //setTemplate(getSaldoTemplate());
        setStore(storeCombo);
        setTypeAhead(true);
        //setTriggerAction(TriggerAction.ALL);
    }

    public Long getIdTorneoElegido() {
        return idTorneoElegido;
    }

    public void setIdTorneoElegido(Long idTorneoElegido) {
        this.idTorneoElegido = idTorneoElegido;
        recargar();
    }
    

    /**
     * Recarga el store del combobox
     */
    public void recargar() {
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
            Rivales entidad = (Rivales) beanModel.getBean();
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
    public Rivales getRivalElegido() {
        if (getSelection().size() > 0) {
            BeanModel beanModel = ((BeanModel) getSelection().get(0));
            return (Rivales) beanModel.getBean();
        } else {
            return null;
        }
    }
}
