/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.server.rpc.administracion;

import co.com.sisegfut.client.datos.dominio.ControlAsistencia;
import co.com.sisegfut.client.datos.dominio.Usuarios;
import co.com.sisegfut.client.datos.dominio.dto.DTOControlAsistencia;
import co.com.sisegfut.client.datos.dominio.dto.DTOReporteAsistenciaXMes;
import co.com.sisegfut.client.util.rpc.RPCAdminControlAsistencia;
import co.com.sisegfut.server.datos.dao.DaoControlAsistencia;
import com.extjs.gxt.ui.client.data.BasePagingLoadResult;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author fhurtado
 */
public class RPCAdminControlAsistenciaImpl extends RPCMaestroImpl<ControlAsistencia> implements RPCAdminControlAsistencia {

    private Usuarios usuarioSession;
    private DaoControlAsistencia daoControlAsistencia;

    @Autowired
    @Override
    public void setUsuarioSession(Usuarios usuarioSession) {
        this.usuarioSession = usuarioSession;
        super.setUsuarioSession(usuarioSession);
    }

    @Autowired
    public void setDaoAntDep(DaoControlAsistencia daoControlAsistencia) {
        this.daoControlAsistencia = daoControlAsistencia;
        super.setDaoGenerico(daoControlAsistencia);
    }

    @Override
    public PagingLoadResult<DTOControlAsistencia> obtenerCtlAsistenciaFiltro(Date fechaInicial, Date fechaFinal, Long idCategoria, String actividad) {

        List<ControlAsistencia> listPlanillasAsistencia = new ArrayList<ControlAsistencia>();
        List<DTOControlAsistencia> listaRetorno = new ArrayList<DTOControlAsistencia>();

        listPlanillasAsistencia = daoControlAsistencia.obtenerPlanillaAsistenciaFiltro(fechaInicial, fechaFinal, idCategoria, actividad);
        if (listPlanillasAsistencia != null) {
            for (ControlAsistencia ctrlAsistencia : listPlanillasAsistencia) {
                DTOControlAsistencia agg = new DTOControlAsistencia();
                agg.setIdPlanillaAsistencia(ctrlAsistencia.getId());
                agg.setIdCategoria(ctrlAsistencia.getCategoria().getId());
                agg.setCategoria(ctrlAsistencia.getCategoria().getNombrecategoria());
                agg.setActividad(ctrlAsistencia.getActividad());
                agg.setLugar(ctrlAsistencia.getLugar());
                agg.setObservaciones(ctrlAsistencia.getObservaciones());
                agg.setFecha(ctrlAsistencia.getFecha());
                listaRetorno.add(agg);
            }
        }

        PagingLoadResult<DTOControlAsistencia> loadResult = new BasePagingLoadResult<DTOControlAsistencia>(listaRetorno, 1, 100);
        return loadResult;

    }

    @Override
    public List<DTOReporteAsistenciaXMes> obtenerReporteAsistenciaxMes(Integer mes, Integer anio, Long idCategoria) {
   
        try {
            List<DTOReporteAsistenciaXMes> listaRetorno = daoControlAsistencia.obtenerReporteAsistenciaxMes(mes, anio, idCategoria);
        return listaRetorno;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        
    }
}
