/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.server.rpc.administracion;

import co.com.sisegfut.client.datos.dominio.TestCooper;
import co.com.sisegfut.client.datos.dominio.Usuarios;
import co.com.sisegfut.client.datos.dominio.dto.DTOTestCooper;
import co.com.sisegfut.client.util.rpc.RPCAdminTestCooper;
import co.com.sisegfut.server.datos.dao.DaoTestCooper;
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
public class RPCAdminTestCooperImpl extends RPCMaestroImpl<TestCooper> implements RPCAdminTestCooper {

    private Usuarios usuarioSession;
    private DaoTestCooper daoTestCooper;

    @Autowired
    @Override
    public void setUsuarioSession(Usuarios usuarioSession) {
        this.usuarioSession = usuarioSession;
        super.setUsuarioSession(usuarioSession);
    }

    @Autowired
    public void setDaoTestCooper(DaoTestCooper daoTestCooper) {
        this.daoTestCooper = daoTestCooper;
        super.setDaoGenerico(daoTestCooper);
    }

    @Override
    public PagingLoadResult<DTOTestCooper> getTestCooper(Long idDeportista) {

        List<TestCooper> listTestCooper = new ArrayList<TestCooper>();
        List<DTOTestCooper> listaRetorno = new ArrayList<DTOTestCooper>();
        try {
            listTestCooper = daoTestCooper.TestCooperXDeportista(idDeportista);
            if (listTestCooper != null) {
                for (TestCooper valorTestCooper : listTestCooper) {
                    DTOTestCooper agg = new DTOTestCooper();
                    agg.setFecha(valorTestCooper.getFecha());
                    agg.setDistancia(valorTestCooper.getDistancia().toString());
                    agg.setConsumOxigeno(valorTestCooper.getConsumOxigeno());
                    agg.setCondicionFisica(valorTestCooper.getCondicionFisica());
                    agg.setVo2max(valorTestCooper.getVo2max());
                    agg.setVelocidad(valorTestCooper.getVelocidad());
                    agg.setIdCooper(valorTestCooper.getId());
                    listaRetorno.add(agg);
                }
            }

        } catch (Exception ex) {
            Logger.getLogger(RPCAdminControlTecnicoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        PagingLoadResult<DTOTestCooper> loadResult = new BasePagingLoadResult<DTOTestCooper>(listaRetorno, 1, 1000);
        return loadResult;
    }

}
