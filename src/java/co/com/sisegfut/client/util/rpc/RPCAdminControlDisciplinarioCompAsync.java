/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.client.util.rpc;

import co.com.sisegfut.client.datos.dominio.ControlDisciplinarioCompe;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 *
 * @author fhurtado
 */
public interface RPCAdminControlDisciplinarioCompAsync extends RPCMaestroAsync<ControlDisciplinarioCompe>{
    
    public void getTarjetasXCompetencia(Long idCompetencia, AsyncCallback<PagingLoadResult<ControlDisciplinarioCompe>> callback);
    
    public void validarTarjetasDeportista(Long idCompetencia, Long idDeportista, AsyncCallback<Boolean> callback);
    
    
}
