/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.sisegfut.server.rpc.administracion;

import co.com.sisegfut.client.datos.dominio.Experiencia;
import co.com.sisegfut.client.datos.dominio.Usuarios;
import co.com.sisegfut.client.datos.dominio.dto.DTOExperiencia;
import co.com.sisegfut.client.util.rpc.RPCAdminExperiencia;
import co.com.sisegfut.server.datos.dao.DaoExperiencia;
import com.extjs.gxt.ui.client.data.BasePagingLoadResult;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Camilo
 */
public class RPCAdminExperienciaImpl extends RPCMaestroImpl<Experiencia> implements RPCAdminExperiencia {

    private Usuarios usuarioSession;
    private DaoExperiencia daoExperiencia;

    @Autowired
    @Override
    public void setUsuarioSession(Usuarios usuarioSession) {
        this.usuarioSession = usuarioSession;
        super.setUsuarioSession(usuarioSession);
    }

    @Autowired
    public void setDaoExp(DaoExperiencia daoExperiencia) {
        this.daoExperiencia = daoExperiencia;
        super.setDaoGenerico(daoExperiencia);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public PagingLoadResult<DTOExperiencia> getExperiencia(Long idPersonal) {

        List<Experiencia> listExp = new ArrayList<Experiencia>();
        List<DTOExperiencia> listaRetorno = new ArrayList<DTOExperiencia>();
        try {
            listExp = daoExperiencia.ExperienciaxPersonal(idPersonal);
            if (listExp != null) {
                for (Experiencia valorAntecedente : listExp) {
                    DTOExperiencia agg = new DTOExperiencia();
                    agg.setEmpresas(valorAntecedente.getEmpresa());
                    agg.setTiempoLaborado(valorAntecedente.getTiempoLaborado());
                    agg.setIdCargo(valorAntecedente.getCargo().getId());
                    agg.setCargo(valorAntecedente.getCargo().getNombrecargo());
                    agg.setIdExperiencia(valorAntecedente.getId());
                    listaRetorno.add(agg);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(RPCAdminExperienciaImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        PagingLoadResult<DTOExperiencia> loadResult = new BasePagingLoadResult<DTOExperiencia>(listaRetorno, 1, 1000);
        return loadResult;
    }
}
