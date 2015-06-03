/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.server.rpc.administracion;

import co.com.sisegfut.client.datos.dominio.SituacionesJuegoCompe;
import co.com.sisegfut.client.datos.dominio.Usuarios;
import co.com.sisegfut.client.util.rpc.RPCAdminSituacionesJuego;
import co.com.sisegfut.server.datos.dao.DaoSituacionesJuego;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author anfeh_000
 */
public class RPCAdminSituacionesJuegoImpl extends RPCMaestroImpl<SituacionesJuegoCompe> implements RPCAdminSituacionesJuego {

    private Usuarios usuarioSession;
    private DaoSituacionesJuego daoSituacionesJuegos;

    @Autowired
    @Override
    public void setUsuarioSession(Usuarios usuarioSession) {
        this.usuarioSession = usuarioSession;
        super.setUsuarioSession(usuarioSession);
    }

    @Autowired
    public void setDaoSituacionesJuego(DaoSituacionesJuego daoSituacionesJuegos) {
        this.daoSituacionesJuegos = daoSituacionesJuegos;
        super.setDaoGenerico(daoSituacionesJuegos);
    }

    @Override
    public List<SituacionesJuegoCompe> getSituacionesXCompetencia(Long idCompetencia) {
        List<SituacionesJuegoCompe> situacionesJuego = new ArrayList<SituacionesJuegoCompe>();

        try {
            situacionesJuego = daoSituacionesJuegos.getSituacionesJxCompetencia(idCompetencia);
        } catch (Exception ex) {
            Logger.getLogger(RPCAdminSituacionesJuegoImpl.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }

        return situacionesJuego;
    }

}
