/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.client.util.rpc;

import co.com.sisegfut.client.datos.dominio.CuerpoTecnicoCompe;
import co.com.sisegfut.client.datos.dominio.Personal;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.google.gwt.user.client.rpc.AsyncCallback;
import java.util.List;

/**
 *
 * @author fhurtado
 */
public interface RPCAdminCuerpoTecCompAsync extends RPCMaestroAsync<CuerpoTecnicoCompe>{
    
    
    public void getCuerpoTecnicoXCompetencia(Long idCompetencia, AsyncCallback<PagingLoadResult<Personal>> callback);
    
    
    public void getPersonalCompetencia(Long idCompetencia, AsyncCallback<List<Personal>> callback);
    
    public void eliminarCuerpoTecComp(Long idCompetencia, Long idPersonal, AsyncCallback<Void> callback);
}
