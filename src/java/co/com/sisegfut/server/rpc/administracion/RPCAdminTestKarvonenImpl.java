/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.server.rpc.administracion;

import co.com.sisegfut.client.datos.dominio.TestKarvonen;
import co.com.sisegfut.client.datos.dominio.Usuarios;
import co.com.sisegfut.client.datos.dominio.dto.DTOTestKarvonen;
import co.com.sisegfut.client.util.rpc.RPCAdminTestKarvonen;
import co.com.sisegfut.server.datos.dao.DaoTestKarvonen;
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
public class RPCAdminTestKarvonenImpl extends RPCMaestroImpl<TestKarvonen> implements RPCAdminTestKarvonen {

    private Usuarios usuarioSession;
    private DaoTestKarvonen daoTestKarvonen;

    @Autowired
    @Override
    public void setUsuarioSession(Usuarios usuarioSession) {
        this.usuarioSession = usuarioSession;
        super.setUsuarioSession(usuarioSession);
    }

    @Autowired
    public void setDaoTestKarvonen(DaoTestKarvonen daoTestKarvonen) {
        this.daoTestKarvonen = daoTestKarvonen;
        super.setDaoGenerico(daoTestKarvonen);
    }

    @Override
    public PagingLoadResult<DTOTestKarvonen> getTestKarvonen(Long idDeportista) {

        List<TestKarvonen> listTestKarv = new ArrayList<TestKarvonen>();
        List<DTOTestKarvonen> listaRetorno = new ArrayList<DTOTestKarvonen>();
        try {
            listTestKarv = daoTestKarvonen.TestKarvonenXDeportista(idDeportista);
            if (listTestKarv != null) {
                for (TestKarvonen valorTestKarv : listTestKarv) {
                    DTOTestKarvonen agg = new DTOTestKarvonen();
                    agg.setFecha(valorTestKarv.getFecha());
                    agg.setFcReposo(valorTestKarv.getFcReposo().toString());
                    agg.setPorcentaje(valorTestKarv.getPorcentaje().toString());
                    agg.setResKarvonen(valorTestKarv.getResultadoKarvonen());
                    agg.setIdKarvonen(valorTestKarv.getId());
                    listaRetorno.add(agg);
                }
            }

        } catch (Exception ex) {
            Logger.getLogger(RPCAdminControlTecnicoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        PagingLoadResult<DTOTestKarvonen> loadResult = new BasePagingLoadResult<DTOTestKarvonen>(listaRetorno, 1, 1000);
        return loadResult;

    }

}
