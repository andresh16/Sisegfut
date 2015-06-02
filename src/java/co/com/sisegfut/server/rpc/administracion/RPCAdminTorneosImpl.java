/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.sisegfut.server.rpc.administracion;

import co.com.sisegfut.client.datos.dominio.Torneos;
import co.com.sisegfut.client.datos.dominio.Usuarios;
import co.com.sisegfut.client.util.rpc.RPCAdminTorneos;
import co.com.sisegfut.server.datos.dao.DaoTorneos;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Malejandro
 */
public class RPCAdminTorneosImpl  extends RPCMaestroImpl<Torneos> implements RPCAdminTorneos{
    private Usuarios usuarioSession;
    private DaoTorneos daoTorneos;

    @Autowired
    @Override
    public void setUsuarioSession(Usuarios usuarioSession) {
        this.usuarioSession = usuarioSession;
        super.setUsuarioSession(usuarioSession);
    }
    
    @Autowired
    public void setDaoTorneos(DaoTorneos daoTorneos) {
        this.daoTorneos = daoTorneos;
        super.setDaoGenerico(daoTorneos);
    }
    
}
