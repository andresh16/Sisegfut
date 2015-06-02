/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.sisegfut.server.rpc.administracion;

import co.com.sisegfut.client.datos.dominio.AntecedentesDeportivos;
import co.com.sisegfut.client.datos.dominio.ControlTecnico;
import co.com.sisegfut.client.datos.dominio.Usuarios;
import co.com.sisegfut.client.datos.dominio.dto.DTOAntecedentesDeportivos;
import co.com.sisegfut.client.datos.dominio.dto.DTOControlTecnico;
import co.com.sisegfut.client.util.rpc.RPCAdminControlTecnico;
import co.com.sisegfut.server.datos.dao.DaoControlTecnico;
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
public class RPCAdminControlTecnicoImpl extends RPCMaestroImpl<ControlTecnico> implements RPCAdminControlTecnico{
     private Usuarios usuarioSession;
    private DaoControlTecnico daoControlTecnico;

    @Autowired
    @Override
    public void setUsuarioSession(Usuarios usuarioSession) {
        this.usuarioSession = usuarioSession;
        super.setUsuarioSession(usuarioSession);
    }
    
    @Autowired
    public void setDaoControlTec(DaoControlTecnico daoControlTec) {
        this.daoControlTecnico = daoControlTec;
        super.setDaoGenerico(daoControlTec);
    }

    @Override
    public PagingLoadResult<DTOControlTecnico> getControlTecDeportivos(Long idDeportista) {
        
        
        List<ControlTecnico> listCtrlTec = new ArrayList<ControlTecnico>();
        List<DTOControlTecnico> listaRetorno = new ArrayList<DTOControlTecnico>();
         try {
             listCtrlTec = daoControlTecnico.CtrlTecXDeportista(idDeportista);
             if (listCtrlTec != null) {
                for (ControlTecnico valorCtrolTec : listCtrlTec) {
                    DTOControlTecnico agg = new DTOControlTecnico();
                    agg.setFecha(valorCtrolTec.getFecha());
                    agg.setNombreaceleracion(valorCtrolTec.getNombreaceleracion().toString());
                    agg.setNombrecabeceodefensivo(valorCtrolTec.getNombrecabeceodefensivo().toString());
                    agg.setNombrecabeceoofensivo(valorCtrolTec.getNombrecabeceoofensivo().toString());
                    agg.setNombreconduccion(valorCtrolTec.getNombreconduccion().toString());
                    agg.setNombrecontrolbalon50seg(valorCtrolTec.getNombrecontrolbalon50seg().toString());
                    agg.setNombrepotenciaremate(valorCtrolTec.getNombrepotenciaremate().toString());
                    agg.setNombreprecisiondisparoempeine(valorCtrolTec.getNombreprecisiondisparoempeine().toString());
                    agg.setNombreprecisionpase15seg(valorCtrolTec.getNombreprecisionpase15seg().toString());
                    agg.setNombrerecepcion30seg(valorCtrolTec.getNombrerecepcion30seg().toString());
                    agg.setIdCtrlTecnico(valorCtrolTec.getId());
                    listaRetorno.add(agg);
                }
            }
             
             
             
         } catch (Exception ex) {
             Logger.getLogger(RPCAdminControlTecnicoImpl.class.getName()).log(Level.SEVERE, null, ex);
         }
        
        
        PagingLoadResult<DTOControlTecnico> loadResult = new BasePagingLoadResult<DTOControlTecnico>(listaRetorno, 1, 1000);
        return loadResult;
    }
}
