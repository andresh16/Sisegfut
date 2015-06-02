/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.sisegfut.server.rpc.administracion;

import co.com.sisegfut.client.datos.dominio.ControlAsistencia;
import co.com.sisegfut.client.datos.dominio.Usuarios;
import co.com.sisegfut.client.util.rpc.RPCAdminControlAsistencia;
import co.com.sisegfut.server.datos.dao.DaoAsistencia;
import co.com.sisegfut.server.datos.dao.DaoControlAsistencia;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author fhurtado
 */
public class RPCAdminControlAsistenciaImpl extends RPCMaestroImpl<ControlAsistencia> implements RPCAdminControlAsistencia{
       private Usuarios usuarioSession;
    private DaoControlAsistencia daoControlAsistencia;
    
    @Autowired
    @Override
    public void setUsuarioSession(Usuarios usuarioSession) {
        this.usuarioSession = usuarioSession;
        super.setUsuarioSession(usuarioSession);
    }
    
    @Autowired
    public void setDaoAntDep(DaoControlAsistencia daoControlAsistencia) {
        this.daoControlAsistencia = daoControlAsistencia;
        super.setDaoGenerico(daoControlAsistencia); 
    }
}
