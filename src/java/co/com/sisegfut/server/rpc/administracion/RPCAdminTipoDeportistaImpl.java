/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.server.rpc.administracion;

import co.com.sisegfut.client.datos.dominio.TipoDeportista;
import co.com.sisegfut.client.datos.dominio.Usuarios;
import co.com.sisegfut.client.util.rpc.RPCAdminTipoDeportista;
import co.com.sisegfut.server.datos.dao.DaoTipoDeportista;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author ManuelAlejandro
 */
public class RPCAdminTipoDeportistaImpl extends RPCMaestroImpl<TipoDeportista> implements RPCAdminTipoDeportista{
    
    private Usuarios usuarioSession;
    private DaoTipoDeportista daoTipoDeportista;
    
    @Autowired
    @Override
    public void setUsuarioSession(Usuarios usuarioSession) {
        this.usuarioSession = usuarioSession;
        super.setUsuarioSession(usuarioSession);
    }
    
    @Autowired
    public void setDaoTipoDeportista(DaoTipoDeportista daoTipoDeportista) {
        this.daoTipoDeportista = daoTipoDeportista;
        super.setDaoGenerico(daoTipoDeportista);
    }
    
    
    
}
