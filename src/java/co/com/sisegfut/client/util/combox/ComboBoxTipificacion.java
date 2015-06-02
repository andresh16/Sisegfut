/**
 * ComboBoxTipificacion
 * Versión 1.0
 * 5/09/2013
 * Copyright(c) 2007-2013, Boos IT.
 * admin@boos.com.co
 *
 * http://boos.com.co/license
 **/

package co.com.sisegfut.client.util.combox;

import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.FieldEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author josorio
 */
public class ComboBoxTipificacion extends ComboBox<Tipificacion> {

    /**
     * Constructor.
     * @param tipos
     * @param nombreCampo 
     */
    public ComboBoxTipificacion(ArrayList<Tipificacion> tipos, String nombreCampo) {
        ListStore<Tipificacion> storeTipo = new ListStore<Tipificacion>();  
        storeTipo.add(tipos);  
  
        //combo.setEmptyText();  
        setFieldLabel(nombreCampo);
        setDisplayField("name");  
        setWidth(150);  
        setStore(storeTipo);  
        setTypeAhead(true);  
        setTriggerAction(TriggerAction.ALL);  
        setMinChars(1);
        
        //Listener del combo cuando el cursor salga del foco. 
        addListener(Events.Blur, new Listener<FieldEvent>() {
            @Override
            public void handleEvent(FieldEvent be) {
                
                if(getValue()==null){
                    setValue(null); 
                    markInvalid("El valor ingresado no es un valor de la lista");  
                }else if(!getValue().getName().equalsIgnoreCase(getRawValue())){
                    //setValue(null); 
                    setRawValue(getValue().getName());
                    markInvalid("El valor ingresado no es un valor de la lista");  
                }
            }
        });
    }

    public void actualizarStore(ArrayList<Tipificacion> tipos){
        getStore().removeAll();
        getStore().add(tipos); 
    }

    public void seleccionar(Integer tipo) {
        List<Tipificacion> lista = getStore().getModels();
        for (Tipificacion beanModel : lista) {
            if (beanModel.getId().intValue() == tipo.intValue()) {
                setValue(beanModel);
                break;
            }
        }
    }
    
    
    

    
}
