/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.client.util.rpc;

import co.com.sisegfut.client.datos.dominio.Goles;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 *
 * @author fhurtado
 */
public interface RPCAdminGolesAsync extends RPCMaestroAsync<Goles>{
    
    public void getGolesXCompetencia(Long idCompetencia, AsyncCallback<PagingLoadResult<Goles>> callback);
    
}
