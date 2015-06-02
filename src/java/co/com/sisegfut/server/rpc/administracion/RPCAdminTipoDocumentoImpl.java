/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.sisegfut.server.rpc.administracion;

import co.com.sisegfut.client.datos.dominio.TipoDocumento;
import co.com.sisegfut.client.datos.dominio.Usuarios;
import co.com.sisegfut.client.util.rpc.RPCAdminTipoDocumento;
import co.com.sisegfut.server.datos.dao.DaoTipoDocumento;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author fhurtado
 */
public class RPCAdminTipoDocumentoImpl extends RPCMaestroImpl<TipoDocumento> implements RPCAdminTipoDocumento{
    
    private Usuarios usuarioSession;
    private DaoTipoDocumento daoTipoDocumento;

    @Autowired
    @Override
    public void setUsuarioSession(Usuarios usuarioSession) {
        this.usuarioSession = usuarioSession;
        super.setUsuarioSession(usuarioSession);
    }
    
    @Autowired
    public void setDaoTipoDocumento(DaoTipoDocumento daoTipoDocumento) {
        this.daoTipoDocumento = daoTipoDocumento;
        super.setDaoGenerico(daoTipoDocumento);
    }
    
}
