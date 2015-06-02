/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.sisegfut.server.rpc.administracion;

import co.com.sisegfut.client.datos.dominio.Cargos;
import co.com.sisegfut.client.datos.dominio.Usuarios;
import co.com.sisegfut.client.util.rpc.RPCAdminCargos;
import co.com.sisegfut.server.datos.dao.DaoCargos;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author fhurtado
 */
public class RPCAdminCargosImpl extends RPCMaestroImpl<Cargos> implements RPCAdminCargos{
    
    private Usuarios usuarioSession;
    private DaoCargos daoCargo;

    @Autowired
    @Override
    public void setUsuarioSession(Usuarios usuarioSession) {
        this.usuarioSession = usuarioSession;
        super.setUsuarioSession(usuarioSession);
    }
    
    @Autowired
    public void setDaoCargos(DaoCargos daoCargo) {
        this.daoCargo = daoCargo;
        super.setDaoGenerico(daoCargo);
    }
    
}
