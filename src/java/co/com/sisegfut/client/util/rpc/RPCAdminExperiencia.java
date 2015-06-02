/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.sisegfut.client.util.rpc;

import co.com.sisegfut.client.datos.dominio.Experiencia;
import co.com.sisegfut.client.datos.dominio.dto.DTOExperiencia;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import java.util.List;

/**
 *
 * @author Camilo
 */
public interface RPCAdminExperiencia extends RPCMaestro<Experiencia>{
        
    /**
     * Método que obtiene los antecedentes de un jugador
     * @param idPersonal
     * @return List<Experiencia>
     */
        public PagingLoadResult<DTOExperiencia> getExperiencia(Long idPersonal);
}
