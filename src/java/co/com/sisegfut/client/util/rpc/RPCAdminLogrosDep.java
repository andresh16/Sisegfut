/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.sisegfut.client.util.rpc;

import co.com.sisegfut.client.datos.dominio.LogrosDeportivos;
import co.com.sisegfut.client.datos.dominio.dto.DTOLogrosDep;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import java.util.List;

/**
 *
 * @author Andres Hurtado
 */
public interface RPCAdminLogrosDep extends RPCMaestro<LogrosDeportivos>{
    
     public void guardarGridLogros(Long IdDep,List<LogrosDeportivos> logrosDeportivos);
     
      public PagingLoadResult<DTOLogrosDep> getLogrosDeportivos(Long idDeportista);
    
}
