/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.sisegfut.client.util.combox;

import co.com.sisegfut.client.datos.dominio.Personal;
import co.com.sisegfut.client.util.rpc.RPCAdminPersonal;
import co.com.sisegfut.client.util.rpc.RPCAdminPersonalAsync;
import co.com.sisegfut.client.util.rpc.RPCMaestroAsync;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

/**
 *
 * @author anfeh_000
 */
public class ComboBoxPersonal extends ComboBoxEntidades<Personal>{
    
     
    
      /**
     * Combobox de personal
     * @param tipo 1 para activos y 2 para borrados
     */
    public ComboBoxPersonal(final int tipo) {
        super(tipo);
        
        setEmptyText("Seleccione un personal...");
    }

    @Override
    protected RPCMaestroAsync<Personal> getServicioRPC() {
   final RPCAdminPersonalAsync svc = (RPCAdminPersonalAsync) GWT.create(RPCAdminPersonal.class);
        ServiceDefTarget endpoint = (ServiceDefTarget) svc;
        endpoint.setServiceEntryPoint("services/RPCAdminPersonal");
        
        return svc;
    }

 
}
