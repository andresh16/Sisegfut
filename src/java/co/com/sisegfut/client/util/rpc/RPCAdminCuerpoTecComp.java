/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.client.util.rpc;

import co.com.sisegfut.client.datos.dominio.CuerpoTecnicoCompe;
import co.com.sisegfut.client.datos.dominio.Personal;
import com.extjs.gxt.ui.client.data.PagingLoadResult;

/**
 *
 * @author fhurtado
 */
public interface RPCAdminCuerpoTecComp extends RPCMaestro<CuerpoTecnicoCompe>{
    
    public PagingLoadResult<Personal> getCuerpoTecnicoXCompetencia(Long idCompetencia);
    
    public void eliminarCuerpoTecComp(Long idCompetencia, Long idPersonal);
    
}
