/**
* ComboBoxEntidades
* Versión 1.0
* 15/09/2013
*
* Copyright(c) 2007-2012, Boos IT.
* admin@boos.com.co
*
* http://boos.com.co/license
**/

package co.com.sisegfut.client.util.combox;

import co.com.sisegfut.client.datos.dominio.EntidadBase;
import co.com.sisegfut.client.util.rpc.RPCMaestroAsync;
import com.extjs.gxt.ui.client.data.*;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.FieldEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.LoadListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.google.gwt.user.client.rpc.AsyncCallback;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author josorio
 */
public abstract class ComboBoxEntidades<T extends Serializable> extends ComboBox<BeanModel> {

    private ListLoader<ListLoadResult<ModelData>> loader;
    private ListStore<BeanModel> storeCombo;
    private RPCMaestroAsync<T> servicioRPC;
    private String campoFiltro;
    private Long idElegido;

    /**
     * Combobox d eusuarios
     * @param tipo 1 para activos y 2 para borrados
     */
    public ComboBoxEntidades(final int tipo, String campoFiltro) {
        this(tipo);
        this.campoFiltro = campoFiltro;
        
        setMinChars(1);
        setTriggerAction(TriggerAction.ALL);
    }
   
    
    /**
     * Combobox d eusuarios
     * @param tipo 1 para activos y 2 para borrados
     */
    public ComboBoxEntidades(final int tipo) {
        this.campoFiltro = null;
        this.idElegido = null;
        
        // proxy and reader  
        RpcProxy<List<T>> proxy = new RpcProxy<List<T>>() {

            @Override
            public void load(Object loadConfig, AsyncCallback<List<T>> callback) {
                getServicioRPC().getEntidades(tipo, callback);
            }
        };

        BeanModelReader reader = new BeanModelReader();

        // loader and store  
        loader = new BaseListLoader<ListLoadResult<ModelData>>(proxy, reader);
        storeCombo = new ListStore<BeanModel>(loader);

        loader.addLoadListener(new LoadListener(){
            @Override
            public void loaderLoad(LoadEvent le) {
                if(idElegido!=null){
                    List<BeanModel> lista = storeCombo.getModels();
                    for (BeanModel beanModel : lista) {
                        EntidadBase entidad = (EntidadBase)beanModel.getBean();
                        if(entidad.getId().longValue()==idElegido.longValue()){
                            setValue(beanModel);
                            idElegido = null;
                            break;
                        }
                    }
                    unmask(); 
                }
            }
        });
        
        recargar();        
        
        setEmptyText("Seleccione un registro...");
        setDisplayField("label");
        setWidth(150);
        setStore(storeCombo);
        setTypeAhead(true);
        //setTriggerAction(TriggerAction.ALL);
        
        //Listener del combo cuando el cursor salga del foco. 
        addListener(Events.Blur, new Listener<FieldEvent>() {
            @Override
            public void handleEvent(FieldEvent be) {
                
                if(getValue()==null){
                    setValue(null); 
                    markInvalid("El valor ingresado no es un valor de la lista");  
                }else if(!((EntidadBase)getValue().getBean()).getLabel().equalsIgnoreCase(getRawValue())){                    
                    setRawValue(((EntidadBase)getValue().getBean()).getLabel());
                    markInvalid("El valor ingresado no es un valor de la lista");  
                }
            }
        });
    }
    
    protected abstract RPCMaestroAsync<T> getServicioRPC();

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
            EntidadBase entidad = (EntidadBase)beanModel.getBean();
            if(entidad.getId().longValue()==id){
                setValue(beanModel);
                break;
            }
        }
    }
    
    /**
     * Seleciona elemento despues de recargar combo
     * @param id 
     */
    public void seleccionarLoad(Long id){       
        mask("Cargando...");
        this.idElegido = id;        
        
        loader.load();
    }
    
    /**
     * Retorna la entidad elegida
     * @return 
     */
    public EntidadBase getEntidadElegida() {
        if (getSelection().size() > 0) {
            BeanModel beanModel = ((BeanModel) getSelection().get(0));
            return (EntidadBase) beanModel.getBean();
        }else{
            return null;
        }
    }


}
