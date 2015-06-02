/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.sisegfut.client.util.rpc;

import co.com.sisegfut.client.datos.dominio.EstudiosRealizados;
import co.com.sisegfut.client.datos.dominio.dto.DTOEstudiosRealizados;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 *
 * @author Camilo
 */
public interface RPCAdminEstudiosRealizadosAsync extends RPCMaestroAsync<EstudiosRealizados>{
   
    /**
     * Método que obtiene los antecedentes de los deportivas
     * @param idPersonal
     * @param callback 
     */
    public void getEstudios(Long idPersonal, AsyncCallback<PagingLoadResult<DTOEstudiosRealizados>> callback);

}
