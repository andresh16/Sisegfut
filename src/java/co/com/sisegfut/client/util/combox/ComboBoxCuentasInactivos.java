/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.client.util.combox;

import co.com.sisegfut.client.datos.dominio.Cuentas;
import co.com.sisegfut.client.util.rpc.RPCAdminCuenta;
import co.com.sisegfut.client.util.rpc.RPCAdminCuentaAsync;
import co.com.sisegfut.client.util.rpc.RPCMaestroAsync;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

/**
 *
 * @author fhurtado
 */
public class ComboBoxCuentasInactivos extends ComboBoxEntidades<Cuentas>{
    
       /**
     * Combobox d eusuarios
     * @param tipo 1 para activos y 2 para borrados
     */
    public ComboBoxCuentasInactivos(final int tipo) {
        super(tipo);
        
        setEmptyText("Seleccione una cuenta...");
    }

    @Override
    protected RPCMaestroAsync<Cuentas> getServicioRPC() {
        final RPCAdminCuentaAsync svc = (RPCAdminCuentaAsync) GWT.create(RPCAdminCuenta.class);
        ServiceDefTarget endpoint = (ServiceDefTarget) svc;
        endpoint.setServiceEntryPoint("services/RPCAdminCuenta");
        
        return svc;
    }

    
    
}
