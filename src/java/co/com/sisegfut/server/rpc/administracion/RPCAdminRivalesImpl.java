/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.server.rpc.administracion;

import co.com.sisegfut.client.datos.dominio.Rivales;
import co.com.sisegfut.client.datos.dominio.Usuarios;
import co.com.sisegfut.client.util.rpc.RPCAdminRivales;
import co.com.sisegfut.server.datos.dao.DaoRivales;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author ManuelAlejandro
 */
public class RPCAdminRivalesImpl extends RPCMaestroImpl<Rivales> implements RPCAdminRivales {

    private Usuarios usuarioSession;
    private DaoRivales daoRivales;

    @Autowired
    @Override
    public void setUsuarioSession(Usuarios usuarioSession) {
        this.usuarioSession = usuarioSession;
        super.setUsuarioSession(usuarioSession);
    }

    @Autowired
    public void setDaoRivales(DaoRivales daoRivales) {
        this.daoRivales = daoRivales;
        super.setDaoGenerico(daoRivales);
    }

    @Override
    public List<Rivales> getRivalesxTorneo(Long idTorneo) {

        return daoRivales.rivalesXTorneo(idTorneo);

    }
}
