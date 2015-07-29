/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.server.rpc.administracion;

import co.com.sisegfut.client.datos.dominio.AntecedentesDeportivos;
import co.com.sisegfut.client.datos.dominio.Antropometrico;
import co.com.sisegfut.client.datos.dominio.Usuarios;
import co.com.sisegfut.client.datos.dominio.dto.DTOAntecedentesDeportivos;
import co.com.sisegfut.client.datos.dominio.dto.DTOAntropometrico;
import co.com.sisegfut.client.util.rpc.RPCAdminAntropometrico;
import co.com.sisegfut.server.datos.dao.DaoAntropometrico;
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
public class RPCAdminAntropometricoImpl extends RPCMaestroImpl<Antropometrico> implements RPCAdminAntropometrico {

    private Usuarios usuarioSession;
    private DaoAntropometrico daoAntropometrico;

    @Autowired
    @Override
    public void setUsuarioSession(Usuarios usuarioSession) {
        this.usuarioSession = usuarioSession;
        super.setUsuarioSession(usuarioSession);
    }

    @Autowired
    public void setDaoAntropometrico(DaoAntropometrico daoAntro) {
        this.daoAntropometrico = daoAntro;
        super.setDaoGenerico(daoAntro);
    }

    @Override
    public PagingLoadResult<DTOAntropometrico> getAntropometrico(Long idDeportista) {
        List<Antropometrico> listAntropometrico = new ArrayList<Antropometrico>();
        List<DTOAntropometrico> listaRetorno = new ArrayList<DTOAntropometrico>();
        try {
            listAntropometrico = daoAntropometrico.AntropometricoxDeportista(idDeportista);
            if (listAntropometrico != null) {
                for (Antropometrico valorAntropometrico : listAntropometrico) {
                    DTOAntropometrico agg = new DTOAntropometrico();
                    agg.setFecha(valorAntropometrico.getFecha().toString());
                    agg.setPerabdominal(valorAntropometrico.getPerabdominal().toString());
                    agg.setPerbrazorelajado(valorAntropometrico.getPerbrazorelajado().toString());
                    agg.setPercadera(valorAntropometrico.getPercadera().toString());
                    agg.setPerpantorrilla(valorAntropometrico.getPerpantorrilla().toString());
                    agg.setPliabdominal(valorAntropometrico.getPliabdominal().toString());
                    agg.setPlisubescapular(valorAntropometrico.getPlisubescapular().toString());
                    agg.setPlisupraescapular(valorAntropometrico.getPlisupraescapular().toString());
                    agg.setPlitricipital(valorAntropometrico.getPlitricipital().toString());
                    agg.setPorcentajeGrasa(valorAntropometrico.getPorcentajeGrasa());
                    agg.setPesoGraso(valorAntropometrico.getPesoGraso());
                    agg.setPesoMacro(valorAntropometrico.getPesoMacro());
                    agg.setIdAntropometrico(valorAntropometrico.getId());

                    listaRetorno.add(agg);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(RPCAdminAntDeportivosImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        PagingLoadResult<DTOAntropometrico> loadResult = new BasePagingLoadResult<DTOAntropometrico>(listaRetorno, 1, 1000);
        return loadResult;
    }

}
