/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.client.util.rpc;

import co.com.sisegfut.client.datos.dominio.Movimientos;

/**
 *
 * @author fhurtado
 */
public interface RPCAdminMovimiento extends RPCMaestro<Movimientos>{
    
    public void guardar(Movimientos mov);
    
    public void eliminarLogico(Movimientos mov);
    
}
