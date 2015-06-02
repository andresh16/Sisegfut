/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.sisegfut.server.rpc.administracion;

import co.com.sisegfut.client.datos.dominio.InstEducativa;
import co.com.sisegfut.client.datos.dominio.Usuarios;
import co.com.sisegfut.client.util.rpc.RPCAdminInstEducativa;
import co.com.sisegfut.server.datos.dao.DaoInstEducativa;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author fhurtado
 */
public class RPCAdminInstEducativaImpl extends RPCMaestroImpl<InstEducativa> implements RPCAdminInstEducativa{
     private Usuarios usuarioSession;
    private DaoInstEducativa daoInstEducativa;

    @Autowired
    @Override
    public void setUsuarioSession(Usuarios usuarioSession) {
        this.usuarioSession = usuarioSession;
        super.setUsuarioSession(usuarioSession);
    }
    
    @Autowired
    public void setDaoInstEducativa(DaoInstEducativa daoInstEducativa) {
        this.daoInstEducativa = daoInstEducativa;
        super.setDaoGenerico(daoInstEducativa);
    }
    
}
