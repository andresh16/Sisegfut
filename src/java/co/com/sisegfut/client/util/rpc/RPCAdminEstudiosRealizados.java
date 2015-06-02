/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.sisegfut.client.util.rpc;

import co.com.sisegfut.client.datos.dominio.EstudiosRealizados;
import co.com.sisegfut.client.datos.dominio.dto.DTOEstudiosRealizados;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import java.util.List;

/**
 *
 * @author Camilo
 */
public interface RPCAdminEstudiosRealizados extends RPCMaestro<EstudiosRealizados>{
    
      /**
     * Método que obtiene los antecedentes de un personal
     * @param idPersonal
     * @return List<Estudios>
     */
        public PagingLoadResult<DTOEstudiosRealizados> getEstudios(Long idPersonal);
}
