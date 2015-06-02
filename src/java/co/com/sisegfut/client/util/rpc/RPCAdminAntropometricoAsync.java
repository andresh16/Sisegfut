/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.sisegfut.client.util.rpc;

import co.com.sisegfut.client.datos.dominio.Antropometrico;
import co.com.sisegfut.client.datos.dominio.dto.DTOAntropometrico;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 *
 * @author Andres Hurtado
 */
public interface RPCAdminAntropometricoAsync extends RPCMaestroAsync<Antropometrico>{
    public void getAntropometrico(Long idDeportista, AsyncCallback<PagingLoadResult<DTOAntropometrico>> callback);
}
