/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.sisegfut.server.rpc.administracion;

import co.com.sisegfut.client.datos.dominio.NivelEducativo;
import co.com.sisegfut.client.datos.dominio.Usuarios;
import co.com.sisegfut.client.util.rpc.RPCAdminNivelEducativo;
import co.com.sisegfut.server.datos.dao.DaoNivelEducativo;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author fhurtado
 */
public class RPCAdminNivelEducativoImpl extends RPCMaestroImpl<NivelEducativo> implements RPCAdminNivelEducativo{
    
     private Usuarios usuarioSession;
    private DaoNivelEducativo daoNivelEducativo;

    @Autowired
    @Override
    public void setUsuarioSession(Usuarios usuarioSession) {
        this.usuarioSession = usuarioSession;
        super.setUsuarioSession(usuarioSession);
    }
    
    @Autowired
    public void setDaoNivelEducativo(DaoNivelEducativo daoNivelEducativo) {
        this.daoNivelEducativo = daoNivelEducativo;
        super.setDaoGenerico(daoNivelEducativo);
    }
    
}
