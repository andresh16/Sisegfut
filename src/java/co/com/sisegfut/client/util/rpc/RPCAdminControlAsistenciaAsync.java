/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.client.util.rpc;

import co.com.sisegfut.client.datos.dominio.ControlAsistencia;
import co.com.sisegfut.client.datos.dominio.dto.DTOControlAsistencia;
import co.com.sisegfut.client.datos.dominio.dto.DTOReporteAsistenciaXMes;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.google.gwt.user.client.rpc.AsyncCallback;
import java.util.Date;
import java.util.List;

/**
 *
 * @author fhurtado
 */
public interface RPCAdminControlAsistenciaAsync extends RPCMaestroAsync<ControlAsistencia> {

    public void obtenerCtlAsistenciaFiltro(Date fechaInicial, Date fechaFinal, Long idCategoria, String actividad, AsyncCallback<PagingLoadResult<DTOControlAsistencia>> callback);

    public void obtenerReporteAsistenciaxMes(Integer mes, Integer anio, Long idCategoria, AsyncCallback<List<DTOReporteAsistenciaXMes>> callback);

    public void validarControlAsistenciaDiaCat(Date fecha, Long IdCategoria, String actividad, AsyncCallback<Boolean> callback);
}
