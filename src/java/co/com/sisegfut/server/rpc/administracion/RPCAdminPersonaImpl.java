/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.sisegfut.server.rpc.administracion;


import co.com.sisegfut.client.datos.dominio.Personal;
import co.com.sisegfut.client.datos.dominio.Usuarios;
import co.com.sisegfut.client.util.rpc.RPCAdminPersonal;
import co.com.sisegfut.server.datos.dao.DaoPersonal;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author anfeh_000
 */
public class RPCAdminPersonaImpl extends RPCMaestroImpl<Personal> implements RPCAdminPersonal{
    private Usuarios usuarioSession;
    private DaoPersonal daoPersonal;

    @Autowired
    @Override
    public void setUsuarioSession(Usuarios usuarioSession) {
        this.usuarioSession = usuarioSession;
        super.setUsuarioSession(usuarioSession);
    }
    
    @Autowired
    public void setDaoTipoDocumento(DaoPersonal daoPersonal) {
        this.daoPersonal = daoPersonal;
        super.setDaoGenerico(daoPersonal);
    }
    
    @Override
    public Personal updateConFoto(Personal per) {
        try {
            Personal personal = daoPersonal.getById(per.getId());
            per.setFoto(personal.getFoto());
            per.setContentTypeFoto(personal.getContentTypeFoto());
            daoPersonal.guardar(per);
            return per;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
