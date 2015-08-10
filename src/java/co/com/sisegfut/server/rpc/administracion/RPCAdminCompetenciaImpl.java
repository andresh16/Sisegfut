/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.server.rpc.administracion;

import co.com.sisegfut.client.datos.dominio.Competencia;
import co.com.sisegfut.client.datos.dominio.Usuarios;
import co.com.sisegfut.client.datos.dominio.dto.DTOCompetencia;
import co.com.sisegfut.client.util.rpc.RPCAdminCompetencia;
import co.com.sisegfut.server.datos.dao.DaoCompetencia;
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
public class RPCAdminCompetenciaImpl extends RPCMaestroImpl<Competencia> implements RPCAdminCompetencia {

    private Usuarios usuarioSession;
    private DaoCompetencia daoCompetencia;

    @Autowired
    @Override
    public void setUsuarioSession(Usuarios usuarioSession) {
        this.usuarioSession = usuarioSession;
        super.setUsuarioSession(usuarioSession);
    }

    @Autowired
    public void setDaoCompetencia(DaoCompetencia daoCompetencia) {
        this.daoCompetencia = daoCompetencia;
        super.setDaoGenerico(daoCompetencia);
    }

    @Override
    public void agregarGolCompetencia(Long IdCompetencia, boolean golAnfitrion) {

        Competencia competencia = daoCompetencia.obtenerCompetenciaxId(IdCompetencia);
        if (golAnfitrion) {
            competencia.setGolesAnfitrion(competencia.getGolesAnfitrion() + 1);
        } else {
            competencia.setGolesRival(competencia.getGolesRival() + 1);
        }
        daoCompetencia.guardar(competencia);
    }

    @Override
    public Competencia obtenerCompetenciaxId(Long IdCompetencia) {
        Competencia competencia = daoCompetencia.obtenerCompetenciaxId(IdCompetencia);
        return competencia;
    }

    @Override
    public void eliminarGolCompetencia(Long IdCompetencia, boolean golAnfitrion) {
        Competencia competencia = daoCompetencia.obtenerCompetenciaxId(IdCompetencia);
        if (golAnfitrion) {
            competencia.setGolesAnfitrion(competencia.getGolesAnfitrion() - 1);
        } else {
            competencia.setGolesRival(competencia.getGolesRival() - 1);
        }
        daoCompetencia.guardar(competencia);
    }

    @Override
    public PagingLoadResult<DTOCompetencia> obtenerCompetenciaFiltro(Date fechaCompetencia, Long idTorneo, Long idRival) {
        List<Competencia> listCompetencias = new ArrayList<Competencia>();
        List<DTOCompetencia> listaRetorno = new ArrayList<DTOCompetencia>();

        if (fechaCompetencia != null || idTorneo != null || idRival != null) {
            listCompetencias = daoCompetencia.obtenerCompetenciaFiltro(fechaCompetencia, idTorneo, idRival);
            if (listCompetencias != null) {
                for (Competencia valorCompentencia : listCompetencias) {
                    DTOCompetencia agg = new DTOCompetencia();
                    agg.setFecha(valorCompentencia.getFecha());
                    agg.setCompromiso(valorCompentencia.getCompromiso());
                    agg.setLugar(valorCompentencia.getLugar());
                    agg.setTorneo(valorCompentencia.getTorneo().getLabel());
                    agg.setFinalizo(valorCompentencia.isFinalizaCompentcia() ? "SI" : "NO");
                    agg.setIdCompetencia(valorCompentencia.getId());
                    agg.setIdJugadorComodin(valorCompentencia.getRival().getJugadorComodin().getId());
                    agg.setIdRival(valorCompentencia.getRival().getId());
                    agg.setObservaciones(valorCompentencia.getObservacion());
                    agg.setIdtorneo(valorCompentencia.getTorneo().getId());
                    listaRetorno.add(agg);
                }
            }
        }

        PagingLoadResult<DTOCompetencia> loadResult = new BasePagingLoadResult<DTOCompetencia>(listaRetorno, 1, 100);
        return loadResult;
    }

    @Override
    public List<DTOCompetencia> getCompetencias(Integer tipo) {
        List<Competencia> listCompetencias = new ArrayList<Competencia>();
        List<DTOCompetencia> listaRetorno = new ArrayList<DTOCompetencia>();

        listCompetencias = daoCompetencia.listarInactivos();
        if (listCompetencias != null) {
            for (Competencia valorCompentencia : listCompetencias) {
                DTOCompetencia agg = new DTOCompetencia();
                agg.setFecha(valorCompentencia.getFecha());
                agg.setCompromiso(valorCompentencia.getCompromiso());
                agg.setLugar(valorCompentencia.getLugar());
                agg.setTorneo(valorCompentencia.getTorneo().getLabel());
                agg.setFinalizo(valorCompentencia.isFinalizaCompentcia() ? "SI" : "NO");
                agg.setIdCompetencia(valorCompentencia.getId());
                agg.setIdJugadorComodin(valorCompentencia.getRival().getJugadorComodin().getId());
                agg.setIdRival(valorCompentencia.getRival().getId());
                agg.setObservaciones(valorCompentencia.getObservacion());
                agg.setIdtorneo(valorCompentencia.getTorneo().getId());
                listaRetorno.add(agg);
            }
        }
        return listaRetorno;

    }
}
