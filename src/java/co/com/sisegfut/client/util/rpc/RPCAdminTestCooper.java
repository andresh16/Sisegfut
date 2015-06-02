/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.sisegfut.client.util.rpc;

import co.com.sisegfut.client.datos.dominio.TestCooper;
import co.com.sisegfut.client.datos.dominio.dto.DTOTestCooper;
import co.com.sisegfut.client.datos.dominio.dto.DTOTestKarvonen;
import com.extjs.gxt.ui.client.data.PagingLoadResult;

/**
 *
 * @author Andres Hurtado
 */
public interface RPCAdminTestCooper extends RPCMaestro<TestCooper>{
    
    public PagingLoadResult<DTOTestCooper> getTestCooper(Long idDeportista);
    
}
