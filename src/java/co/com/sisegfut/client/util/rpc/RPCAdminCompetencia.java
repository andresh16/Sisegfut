/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.client.util.rpc;

import co.com.sisegfut.client.datos.dominio.Competencia;
import co.com.sisegfut.client.datos.dominio.dto.DTOCompetencia;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import java.util.Date;

/**
 *
 * @author fhurtado
 */
public interface RPCAdminCompetencia extends RPCMaestro<Competencia>{
    
    public void agregarGolCompetencia(Long IdCompetencia,boolean golAnfitrion);
    
    public void eliminarGolCompetencia(Long IdCompetencia,boolean golAnfitrion);
    
    public Competencia obtenerCompetenciaxId(Long IdCompetencia);
    
    public PagingLoadResult<DTOCompetencia> obtenerCompetenciaFiltro(Date fechaCompetencia, Long idTorneo, Long idRival);
    
    
}
