/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.client.util.combox;

import co.com.sisegfut.client.datos.dominio.Usuarios;
import co.com.sisegfut.client.datos.dominio.dto.DTOCompetencia;
import co.com.sisegfut.client.util.BeansLocales;
import co.com.sisegfut.client.util.rpc.RPCAdminCompetencia;
import co.com.sisegfut.client.util.rpc.RPCAdminCompetenciaAsync;
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
public class ComboBoxCompetencia extends ComboBox<BeanModel>{
    
    private ListLoader<ListLoadResult<ModelData>> loader;
    private ListStore<BeanModel> storeCombo;
    private Usuarios usuarioLogueado;
    public static final Integer ACTIVOS = 1;
    public static final Integer INACTIVOS = 2;
    private Integer tipo;

    public ComboBoxCompetencia(final int tipo) {
        
        this.tipo = tipo;
        usuarioLogueado = BeansLocales.getUsuario();
        
       final RPCAdminCompetenciaAsync svc = (RPCAdminCompetenciaAsync) GWT.create(RPCAdminCompetencia.class);
        ServiceDefTarget endpoint = (ServiceDefTarget) svc;
        endpoint.setServiceEntryPoint("services/RPCAdminCompetencia");
        
        // proxy and reader  
        RpcProxy<List<DTOCompetencia>> proxy = new RpcProxy<List<DTOCompetencia>>() {

            @Override
            public void load(Object loadConfig, AsyncCallback<List<DTOCompetencia>> callback) {
                svc.getCompetencias(tipo, callback);
            }
        };
        
        // loader and store  
        loader = new BaseListLoader<ListLoadResult<ModelData>>(proxy, new BeanModelReader());
        storeCombo = new ListStore<BeanModel>(loader);

        recargar();        
        
        setEmptyText("Seleccione una compentencia...");
        setDisplayField("compromiso");
        setWidth(150);
        setTemplate(getCompeteciaTemplate());
        setStore(storeCombo);
        setTypeAhead(true);
        setTriggerAction(TriggerAction.ALL);
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
            DTOCompetencia entidad = (DTOCompetencia)beanModel.getBean();
            if(entidad.getIdCompetencia().longValue()==id){
                setValue(beanModel);
                break;
            }
        }
    }
    /**
     * Retorna la entidad elegida
     * @return 
     */
    public DTOCompetencia getCompetenciaElegida() {
        if (getSelection().size() > 0) {
            BeanModel beanModel = ((BeanModel) getSelection().get(0));
            return (DTOCompetencia) beanModel.getBean();
        }else{
            return null;
        }
    }
    
    
    // En la imagen averiguar como traer el id del tipo de cuenta
       private native String getCompeteciaTemplate() /*-{ 
     return  [ 
     '<tpl for=".">', 
     '<div class="x-combo-list-item" qtip="- Fecha {fecha}<br> - Lugar: {lugar} <br> - Observaciones: {observaciones}" qtitle="Marcador {compromiso}">{compromiso}</div>', 
     '</tpl>' 
     ].join(""); 
     }-*/;
    
       //Para ponerle imagen al combo
     //  <img width="20" height="20" src="imagenes/tiposcuentas/{id}TipoCuenta.png "/>
    
    
    
    
    
}
