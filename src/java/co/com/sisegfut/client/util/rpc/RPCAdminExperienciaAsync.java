/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.sisegfut.client.util.rpc;

import co.com.sisegfut.client.datos.dominio.Experiencia;
import co.com.sisegfut.client.datos.dominio.dto.DTOExperiencia;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.google.gwt.user.client.rpc.AsyncCallback;
import java.util.List;

/**
 *
 * @author Camilo
 */
public interface RPCAdminExperienciaAsync extends RPCMaestroAsync<Experiencia>{
  
    /**
     * Método que obtiene l
     * @param idPersonal
     * @param callback 
     */
    public void getExperiencia(Long idPersonal, AsyncCallback<PagingLoadResult<DTOExperiencia>> callback);
}
