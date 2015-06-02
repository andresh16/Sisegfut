/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.sisegfut.client.util.rpc;

import co.com.sisegfut.client.datos.dominio.Lesiones;
import co.com.sisegfut.client.datos.dominio.dto.DTOLesiones;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.google.gwt.user.client.rpc.AsyncCallback;
import java.util.List;

/**
 *
 * @author Andres Hurtado
 */
public interface RPCAdminLesionAsync extends RPCMaestroAsync<Lesiones>{
    
    public void getLesion(Long idDeportista, AsyncCallback<PagingLoadResult<DTOLesiones>> callback);
}
