/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.sisegfut.client.util.rpc;

import co.com.sisegfut.client.datos.dominio.AntecedentesDeportivos;
import co.com.sisegfut.client.datos.dominio.dto.DTOAntecedentesDeportivos;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import java.util.List;


/**
 *
 * @author fhurtado
 */
public interface RPCAdminAntDeportivos extends RPCMaestro<AntecedentesDeportivos>{
    
    public void guardarGridAntDep(Long IdDep,List<AntecedentesDeportivos> antecedentesDeportivoses);
    
    /**
     * Método que obtiene los antecedentes de un jugador
     * @param idDeportista
     * @return List<AntecedentesDeportivos>
     */
        public PagingLoadResult<DTOAntecedentesDeportivos> getAntecedentesDeportivos(Long idDeportista);
}
