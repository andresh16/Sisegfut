/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.client.util.rpc;

import co.com.sisegfut.client.datos.dominio.Movimientos;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 *
 * @author fhurtado
 */
public interface RPCAdminMovimientoAsync extends RPCMaestroAsync<Movimientos> {
    
    public void guardar(Movimientos Mov,AsyncCallback<Void> callback);
    
    
    public void eliminarLogico(Movimientos mov,AsyncCallback<Void> callback);
}
