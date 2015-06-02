/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.sisegfut.server.rpc.administracion;

import co.com.sisegfut.client.datos.dominio.EstadoAsistencia;
import co.com.sisegfut.client.datos.dominio.Usuarios;
import co.com.sisegfut.client.util.rpc.RPCAdminEstadoAsistencia;
import co.com.sisegfut.server.datos.dao.DaoEstadoAsistencia;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author anfeh_000
 */
public class RPCAdminEstadoAsistenciaImpl extends RPCMaestroImpl<EstadoAsistencia> implements RPCAdminEstadoAsistencia{
    
     private Usuarios usuarioSession;
    private DaoEstadoAsistencia daoEstadoAsistencia;

    @Autowired
    @Override
    public void setUsuarioSession(Usuarios usuarioSession) {
        this.usuarioSession = usuarioSession;
        super.setUsuarioSession(usuarioSession);
    }
    
    @Autowired
    public void setDaoEps(DaoEstadoAsistencia daoEstadoAsistencia) {
        this.daoEstadoAsistencia = daoEstadoAsistencia;
        super.setDaoGenerico(daoEstadoAsistencia);
    }
    
}
