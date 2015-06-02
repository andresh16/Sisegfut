/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.sisegfut.server.rpc.administracion;

import co.com.sisegfut.client.datos.dominio.Eps;
import co.com.sisegfut.client.datos.dominio.Usuarios;
import co.com.sisegfut.client.util.rpc.RPCAdminEps;
import co.com.sisegfut.server.datos.dao.DaoEps;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author anfeh_000
 */
public class RPCAdminEpsImpl extends RPCMaestroImpl<Eps> implements RPCAdminEps{
    
      private Usuarios usuarioSession;
    private DaoEps daoEps;

    @Autowired
    @Override
    public void setUsuarioSession(Usuarios usuarioSession) {
        this.usuarioSession = usuarioSession;
        super.setUsuarioSession(usuarioSession);
    }
    
    @Autowired
    public void setDaoEps(DaoEps daoEps) {
        this.daoEps = daoEps;
        super.setDaoGenerico(daoEps);
    }
}
