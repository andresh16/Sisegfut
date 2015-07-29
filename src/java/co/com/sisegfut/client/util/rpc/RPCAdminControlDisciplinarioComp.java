/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.client.util.rpc;

import co.com.sisegfut.client.datos.dominio.ControlDisciplinarioCompe;
import com.extjs.gxt.ui.client.data.PagingLoadResult;

/**
 *
 * @author fhurtado
 */
public interface RPCAdminControlDisciplinarioComp extends RPCMaestro<ControlDisciplinarioCompe>{
    
    public PagingLoadResult<ControlDisciplinarioCompe> getTarjetasXCompetencia(Long idCompetencia);
    
    public boolean validarTarjetasDeportista(Long idCompetencia, Long idDeportista);
    
    public Boolean validarMinutoTarCompetencia(Long idCompetencia, Integer Minuto);
    
}
