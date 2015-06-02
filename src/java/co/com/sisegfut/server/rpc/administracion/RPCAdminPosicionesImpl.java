/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.sisegfut.server.rpc.administracion;

import co.com.sisegfut.client.datos.dominio.Posiciones;
import co.com.sisegfut.client.datos.dominio.Usuarios;
import co.com.sisegfut.client.util.rpc.RPCAdminPosiciones;
import co.com.sisegfut.server.datos.dao.DaoPosiciones;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author fhurtado
 */
public class RPCAdminPosicionesImpl extends RPCMaestroImpl<Posiciones> implements RPCAdminPosiciones{
    
     private Usuarios usuarioSession;
    private DaoPosiciones daoPosiciones;

    @Autowired
    @Override
    public void setUsuarioSession(Usuarios usuarioSession) {
        this.usuarioSession = usuarioSession;
        super.setUsuarioSession(usuarioSession);
    }
    
    @Autowired
    public void setDaoPosiciones(DaoPosiciones daoPosiciones) {
        this.daoPosiciones = daoPosiciones;
        super.setDaoGenerico(daoPosiciones);
    }
    
}
