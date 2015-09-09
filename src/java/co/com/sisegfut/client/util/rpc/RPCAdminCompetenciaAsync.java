/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.client.util.rpc;

import co.com.sisegfut.client.datos.dominio.Competencia;
import co.com.sisegfut.client.datos.dominio.dto.DTOCompetencia;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.google.gwt.user.client.rpc.AsyncCallback;
import java.util.Date;
import java.util.List;

/**
 *
 * @author fhurtado
 */
public interface RPCAdminCompetenciaAsync extends RPCMaestroAsync<Competencia>{
 
    public void agregarGolCompetencia(Long idCompetencia,boolean golAnfitrion, AsyncCallback<Void> callback);
   
    public void eliminarGolCompetencia(Long idCompetencia,boolean golAnfitrion, AsyncCallback<Void> callback);
    
    public void obtenerCompetenciaxId(Long idCompetencia, AsyncCallback<Competencia> callback);
    
    public void obtenerCompetenciaFiltro(Date fechaCompetencia, Long idTorneo, Long idRival, AsyncCallback<PagingLoadResult<DTOCompetencia>> callback);
    
    public void obtenerCompetencias(AsyncCallback<PagingLoadResult<DTOCompetencia>> callback);
   
    public void getCompetencias(Integer tipo, AsyncCallback<List<DTOCompetencia>> callback);
    
}
