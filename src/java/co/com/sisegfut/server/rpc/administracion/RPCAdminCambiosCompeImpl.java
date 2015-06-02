/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.server.rpc.administracion;

import co.com.sisegfut.client.datos.dominio.CambiosCompe;
import co.com.sisegfut.client.datos.dominio.Usuarios;
import co.com.sisegfut.client.util.rpc.RPCAdminCambiosCompe;
import co.com.sisegfut.server.datos.dao.DaoCambiosCompe;
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
public class RPCAdminCambiosCompeImpl extends RPCMaestroImpl<CambiosCompe> implements RPCAdminCambiosCompe {

    private Usuarios usuarioSession;
    private DaoCambiosCompe daoCambiosCompe;

    @Autowired
    @Override
    public void setUsuarioSession(Usuarios usuarioSession) {
        this.usuarioSession = usuarioSession;
        super.setUsuarioSession(usuarioSession);
    }

    @Autowired
    public void setDaoCambios(DaoCambiosCompe daoCambiosCompe) {
        this.daoCambiosCompe = daoCambiosCompe;
        super.setDaoGenerico(daoCambiosCompe);
    }

    @Override
    public PagingLoadResult<CambiosCompe> getCambiosXCompetenciaGrid(Long idCompetencia) {
        try {
            List<CambiosCompe> listaRetorno = new ArrayList<CambiosCompe>();
            listaRetorno = daoCambiosCompe.getCambiosXCompetencia(idCompetencia);

            PagingLoadResult<CambiosCompe> loadResult = new BasePagingLoadResult<CambiosCompe>(listaRetorno, 1, 1000);
            return loadResult;
        } catch (Exception ex) {
            Logger.getLogger(RPCAdminCambiosCompeImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    @Override
    public List<CambiosCompe> getCambiosXCompetenciaCombo(Long idCompetencia) {
        try {
            List<CambiosCompe> listaRetorno = new ArrayList<CambiosCompe>();
            listaRetorno = daoCambiosCompe.getCambiosXCompetencia(idCompetencia);
            return listaRetorno;
        } catch (Exception ex) {
            Logger.getLogger(RPCAdminCambiosCompeImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

}
