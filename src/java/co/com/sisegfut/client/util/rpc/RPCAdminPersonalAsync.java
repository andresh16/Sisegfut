/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.sisegfut.client.util.rpc;

import co.com.sisegfut.client.datos.dominio.Personal;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 *
 * @author anfeh_000
 */
public interface RPCAdminPersonalAsync extends RPCMaestroAsync<Personal>{
    
    public void updateConFoto(Personal per, AsyncCallback<Personal> callback);
}
