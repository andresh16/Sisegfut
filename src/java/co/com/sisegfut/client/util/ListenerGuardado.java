/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.sisegfut.client.util;

import co.com.sisegfut.client.util.rpc.RPCMaestroAsync;
import java.io.Serializable;

/**
 *
 * @author
 */
public interface ListenerGuardado<T extends Serializable>
{
    /**
     * Determina si se puede proceder con el guardado (true) o
     * debe ser cancelado (false)
     * @return
     */
    public boolean permisoGuardado();

    /**
     * Método a llamar cuando el guardado finaliza ok
     */
    public void finalizaGuardado();
    
    /**
     * Cuando se ejecute la cancelacion de un guardado.
     */
    public void cancelarGuardado();
    
    /**
     * Retorna el servicio RPC para gestionar los datos con el servidor.
     * @return 
     */
    public RPCMaestroAsync<T> getRPCMaestroAsync();
}
