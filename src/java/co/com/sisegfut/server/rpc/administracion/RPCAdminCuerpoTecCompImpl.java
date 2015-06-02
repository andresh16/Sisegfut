/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.server.rpc.administracion;

import co.com.sisegfut.client.datos.dominio.CuerpoTecnicoCompe;
import co.com.sisegfut.client.datos.dominio.Personal;
import co.com.sisegfut.client.datos.dominio.Usuarios;
import co.com.sisegfut.client.util.rpc.RPCAdminCuerpoTecComp;
import co.com.sisegfut.server.datos.dao.DaoCuerpoTecnicoCompe;
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
public class RPCAdminCuerpoTecCompImpl extends RPCMaestroImpl<CuerpoTecnicoCompe> implements RPCAdminCuerpoTecComp {

    private Usuarios usuarioSession;
    private DaoCuerpoTecnicoCompe daoCuerpoTecnicoCompe;

    @Autowired
    @Override
    public void setUsuarioSession(Usuarios usuarioSession) {
        this.usuarioSession = usuarioSession;
        super.setUsuarioSession(usuarioSession);
    }

    @Autowired
    public void setDaoCuerpoTec(DaoCuerpoTecnicoCompe daoCuerpoTecnicoCompe) {
        this.daoCuerpoTecnicoCompe = daoCuerpoTecnicoCompe;
        super.setDaoGenerico(daoCuerpoTecnicoCompe);
    }

    @Override
    public PagingLoadResult<Personal> getCuerpoTecnicoXCompetencia(Long idCompetencia) {
        List<Personal> listaRetorno = new ArrayList<Personal>();
        try {
            listaRetorno = daoCuerpoTecnicoCompe.personalXCompetencia(idCompetencia);

            PagingLoadResult<Personal> loadResult = new BasePagingLoadResult<Personal>(listaRetorno, 1, 1000);
            return loadResult;
        } catch (Exception ex) {
            Logger.getLogger(RPCAdminCuerpoTecCompImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    @Override
    public void eliminarCuerpoTecComp(Long idCompetencia, Long idPersonal) {
        try {
            
            List<CuerpoTecnicoCompe> listaRetorno = daoCuerpoTecnicoCompe.eliminarCuerpoTecCompe(idCompetencia, idPersonal);
            
            for (CuerpoTecnicoCompe listaPersonalCuerpoTec : listaRetorno) {
                daoCuerpoTecnicoCompe.eliminar(listaPersonalCuerpoTec);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        
    }

}
