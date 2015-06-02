/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.sisegfut.client.util.rpc;

import co.com.sisegfut.client.datos.dominio.AntecedentesDeportivos;
import co.com.sisegfut.client.datos.dominio.dto.DTOAntecedentesDeportivos;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.google.gwt.user.client.rpc.AsyncCallback;
import java.util.List;

/**
 *
 * @author fhurtado
 */
public interface RPCAdminAntDeportivosAsync extends RPCMaestroAsync<AntecedentesDeportivos>{
    public void guardarGridAntDep(Long idDep,List<AntecedentesDeportivos> antecedentesDeportivoses,AsyncCallback<Void> callback);
    
    /**
     * Método que obtiene los antecedentes de los deportivas
     * @param idDeportista
     * @param callback 
     */
    public void getAntecedentesDeportivos(Long idDeportista, AsyncCallback<PagingLoadResult<DTOAntecedentesDeportivos>> callback);
}
