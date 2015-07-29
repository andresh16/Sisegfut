/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.server.rpc.administracion;

import co.com.sisegfut.client.datos.dominio.ControlDisciplinarioCompe;
import co.com.sisegfut.client.datos.dominio.Usuarios;
import co.com.sisegfut.client.util.rpc.RPCAdminControlDisciplinarioComp;
import co.com.sisegfut.server.datos.dao.DaoControlDisciplinarioComp;
import com.extjs.gxt.ui.client.data.BasePagingLoadResult;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author fhurtado
 */
public class RPCAdminControlDisciplinarioCompImpl extends RPCMaestroImpl<ControlDisciplinarioCompe> implements RPCAdminControlDisciplinarioComp {

    private Usuarios usuarioSession;
    private DaoControlDisciplinarioComp daoControlDisciplinarioComp;

    @Autowired
    @Override
    public void setUsuarioSession(Usuarios usuarioSession) {
        this.usuarioSession = usuarioSession;
        super.setUsuarioSession(usuarioSession);
    }

    @Autowired
    public void setDaoCargos(DaoControlDisciplinarioComp daoControlDisciplinarioComp) {
        this.daoControlDisciplinarioComp = daoControlDisciplinarioComp;
        super.setDaoGenerico(daoControlDisciplinarioComp);
    }

    @Override
    public PagingLoadResult<ControlDisciplinarioCompe> getTarjetasXCompetencia(Long idCompetencia) {
        try {
            List<ControlDisciplinarioCompe> listaRetorno = new ArrayList<ControlDisciplinarioCompe>();
            listaRetorno = daoControlDisciplinarioComp.getTarjetasXCompetencia(idCompetencia);

            PagingLoadResult<ControlDisciplinarioCompe> loadResult = new BasePagingLoadResult<ControlDisciplinarioCompe>(listaRetorno, 1, 1000);
            return loadResult;
        } catch (Exception ex) {
            Logger.getLogger(RPCAdminControlDisciplinarioCompImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public boolean validarTarjetasDeportista(Long idCompetencia, Long idDeportista) {
        boolean tieneAmarilla = false;
        List<ControlDisciplinarioCompe> listaRetorno = new ArrayList<ControlDisciplinarioCompe>();
        try {
            listaRetorno = daoControlDisciplinarioComp.getTarjetasXCompetencia(idCompetencia);
        } catch (Exception ex) {
            Logger.getLogger(RPCAdminControlDisciplinarioCompImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (ControlDisciplinarioCompe controlDisciplinarioCompe : listaRetorno) {
            if (controlDisciplinarioCompe.getIdDeportista().getId().equals(idDeportista) && controlDisciplinarioCompe.getTipoTarjeta().equalsIgnoreCase("AMARILLA")) {
                tieneAmarilla = true;
            }
        }
        return tieneAmarilla;
    }

    @Override
    public Boolean validarMinutoTarCompetencia(Long idCompetencia, Integer Minuto) {
        try {
            return daoControlDisciplinarioComp.validarMinutoTarCompetencia(idCompetencia, Minuto);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
