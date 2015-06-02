/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.sisegfut.server.rpc.administracion;

import co.com.sisegfut.client.datos.dominio.EstudiosRealizados;
import co.com.sisegfut.client.datos.dominio.Usuarios;
import co.com.sisegfut.client.datos.dominio.dto.DTOEstudiosRealizados;
import co.com.sisegfut.client.util.rpc.RPCAdminEstudiosRealizados;
import co.com.sisegfut.server.datos.dao.DaoEstudiosRealizados;
import com.extjs.gxt.ui.client.data.BasePagingLoadResult;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Camilo
 */
public class RPCAdminEstudiosRealizadosImpl extends RPCMaestroImpl<EstudiosRealizados> implements RPCAdminEstudiosRealizados {

    private Usuarios usuarioSession;
    private DaoEstudiosRealizados daoEstudiosRealizados;

    @Autowired
    @Override
    public void setUsuarioSession(Usuarios usuarioSession) {
        this.usuarioSession = usuarioSession;
        super.setUsuarioSession(usuarioSession);
    }

    @Autowired
    public void setDaoEstRea(DaoEstudiosRealizados daoEstudiosRealizados) {
        this.daoEstudiosRealizados = daoEstudiosRealizados;
        super.setDaoGenerico(daoEstudiosRealizados);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public PagingLoadResult<DTOEstudiosRealizados> getEstudios(Long idPersonal) {

        List<EstudiosRealizados> listEst = new ArrayList<EstudiosRealizados>();
        List<DTOEstudiosRealizados> listaRetorno = new ArrayList<DTOEstudiosRealizados>();
        try {
            listEst = daoEstudiosRealizados.EstudiosRealizadosxPersonal(idPersonal);
            if (listEst != null) {
                for (EstudiosRealizados valorAntecedente : listEst) {
                    DTOEstudiosRealizados agg = new DTOEstudiosRealizados();
                    agg.setTitulo(valorAntecedente.getTitulo());
                    agg.setInstitucion(valorAntecedente.getInstitucion());
                    agg.setIdnivelEducativo(valorAntecedente.getNivelEducativo().getId());
                    agg.setNivelEducativo(valorAntecedente.getNivelEducativo().getNombreNivelEducativo());
                    agg.setAnioGraduacion(valorAntecedente.getAnioGraduacion());
                    agg.setIdEstudiosRealizados(valorAntecedente.getId());
                    
                    listaRetorno.add(agg);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(RPCAdminEstudiosRealizadosImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        PagingLoadResult<DTOEstudiosRealizados> loadResult = new BasePagingLoadResult<DTOEstudiosRealizados>(listaRetorno, 1, 1000);
        return loadResult;
    }
    
   }