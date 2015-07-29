/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.server.rpc.administracion;

import co.com.sisegfut.client.datos.dominio.CambiosCompe;
import co.com.sisegfut.client.datos.dominio.Goles;
import co.com.sisegfut.client.datos.dominio.Usuarios;
import co.com.sisegfut.client.util.rpc.RPCAdminGoles;
import co.com.sisegfut.server.datos.dao.DaoGolesCompe;
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
public class RPCAdminGolesImpl extends RPCMaestroImpl<Goles> implements RPCAdminGoles {

    private Usuarios usuarioSession;
    private DaoGolesCompe daoGolesCompe;

    @Autowired
    @Override
    public void setUsuarioSession(Usuarios usuarioSession) {
        this.usuarioSession = usuarioSession;
        super.setUsuarioSession(usuarioSession);
    }

    @Autowired
    public void setDaoGoles(DaoGolesCompe daoGolesCompe) {
        this.daoGolesCompe = daoGolesCompe;
        super.setDaoGenerico(daoGolesCompe);
    }

    @Override
    public PagingLoadResult<Goles> getGolesXCompetencia(Long idCompetencia) {
        try {
            List<Goles> listaRetorno = new ArrayList<Goles>();
            listaRetorno = daoGolesCompe.getGolesXCompetencia(idCompetencia);

            PagingLoadResult<Goles> loadResult = new BasePagingLoadResult<Goles>(listaRetorno, 1, 1000);
            return loadResult;

        } catch (Exception ex) {
            Logger.getLogger(RPCAdminGolesImpl.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    @Override
    public Boolean validarMinutoGolCompetencia(Long idCompetencia, Integer Minuto) {
        try {
            return daoGolesCompe.validarMinutoGolCompetencia(idCompetencia, Minuto);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    
    }

}
