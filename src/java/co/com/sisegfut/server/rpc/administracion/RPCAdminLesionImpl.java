/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.server.rpc.administracion;

import co.com.sisegfut.client.datos.dominio.Lesiones;
import co.com.sisegfut.client.datos.dominio.Usuarios;
import co.com.sisegfut.client.datos.dominio.dto.DTOLesiones;
import co.com.sisegfut.client.util.rpc.RPCAdminLesion;
import co.com.sisegfut.server.datos.dao.DaoLesiones;
import com.extjs.gxt.ui.client.data.BasePagingLoadResult;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Andres Hurtado
 */
public class RPCAdminLesionImpl extends RPCMaestroImpl<Lesiones> implements RPCAdminLesion {

    private Usuarios usuarioSession;
    private DaoLesiones daoLesiones;

    @Autowired
    @Override
    public void setUsuarioSession(Usuarios usuarioSession) {
        this.usuarioSession = usuarioSession;
        super.setUsuarioSession(usuarioSession);
    }

    @Autowired
    public void setDaoLesion(DaoLesiones daoLesiones1) {
        this.daoLesiones = daoLesiones1;
        super.setDaoGenerico(daoLesiones1);
    }

   

    @Override
    public PagingLoadResult<DTOLesiones> getLesion(Long idDeportista) {
        List<Lesiones> listLesion = new ArrayList<Lesiones>();
         List<DTOLesiones> listaRetorno = new ArrayList<DTOLesiones>();
        try {
            listLesion = daoLesiones.AnteOsteoMuscularxDeportista(idDeportista);
            if (listLesion != null) {
                for (Lesiones valorLesion : listLesion) {
                    DTOLesiones agg = new DTOLesiones();
                    agg.setNombreLesion(valorLesion.getNombreLesion());
                    agg.setFechaLesion(valorLesion.getFechaLesion());
                    agg.setIdLesion(valorLesion.getId());
                    listaRetorno.add(agg);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(RPCAdminLesionImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        PagingLoadResult<DTOLesiones> loadResult = new BasePagingLoadResult<DTOLesiones>(listaRetorno, 1, 1000);
        return loadResult;
    }


}
