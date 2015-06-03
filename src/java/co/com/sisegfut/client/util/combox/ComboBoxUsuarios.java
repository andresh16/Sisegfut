/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.client.util.combox;

import co.com.sisegfut.client.datos.dominio.Usuarios;
import co.com.sisegfut.client.util.rpc.RPCAdminUsuarios;
import co.com.sisegfut.client.util.rpc.RPCAdminUsuariosAsync;
import co.com.sisegfut.client.util.rpc.RPCMaestroAsync;
import com.extjs.gxt.ui.client.data.*;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import java.util.List;

/**
 *
 * @author 
 */
public class ComboBoxUsuarios extends ComboBoxEntidades<Usuarios> {

    /**
     * Combobox d eusuarios
     * @param tipo 1 para activos y 2 para borrados
     */
    public ComboBoxUsuarios(final int tipo) {
        super(tipo);
        
        setEmptyText("Seleccione un usuario...");
    }

    @Override
    protected RPCMaestroAsync<Usuarios> getServicioRPC() {
        final RPCAdminUsuariosAsync svc = (RPCAdminUsuariosAsync) GWT.create(RPCAdminUsuarios.class);
        ServiceDefTarget endpoint = (ServiceDefTarget) svc;
        endpoint.setServiceEntryPoint("services/RPCAdminUsuarios");
        
        return svc;
    }

    


}
