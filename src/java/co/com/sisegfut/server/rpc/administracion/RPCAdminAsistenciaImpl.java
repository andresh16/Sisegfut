/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.server.rpc.administracion;

import co.com.sisegfut.client.datos.dominio.Asistencia;
import co.com.sisegfut.client.datos.dominio.ControlAsistencia;
import co.com.sisegfut.client.datos.dominio.Deportista;
import co.com.sisegfut.client.datos.dominio.Usuarios;
import co.com.sisegfut.client.util.rpc.RPCAdminAsistencia;
import co.com.sisegfut.server.datos.dao.DaoAsistencia;
import co.com.sisegfut.server.datos.dao.DaoDeportista;
import com.extjs.gxt.ui.client.data.BasePagingLoadResult;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author fhurtado
 */
public class RPCAdminAsistenciaImpl extends RPCMaestroImpl<Asistencia> implements RPCAdminAsistencia {

    private Usuarios usuarioSession;
    private DaoAsistencia daoAsistencia;
    private DaoDeportista daoDeportista;

    @Autowired
    @Override
    public void setUsuarioSession(Usuarios usuarioSession) {
        this.usuarioSession = usuarioSession;
        super.setUsuarioSession(usuarioSession);
    }

    @Autowired
    public void setDaoAsistencia(DaoAsistencia daoAsistencia) {
        this.daoAsistencia = daoAsistencia;
        super.setDaoGenerico(daoAsistencia);
    }
    
    @Autowired
    public void setDaoDeportista(DaoDeportista daoDeportista) {
        this.daoDeportista = daoDeportista;
    }

    @Override
    public void guardarGridAsistencia(Long IdControlAsistencia, List<String[]> ListaAsistencias) {

        boolean asistio = false;

        for (String[] asitencia : ListaAsistencias) {

            Asistencia nuevo = new Asistencia();

            nuevo.setId_control_asistencia(new ControlAsistencia(IdControlAsistencia));
//            nuevo.setId((Long)asitencia.get("id"));
            if (asitencia[1].equalsIgnoreCase("true")) {
                asistio = true;
            }else{
            asistio = false;
            }
//            nuevo.setAsistio(asistio);
//            nuevo.setFalto(asitencia[2]);
            nuevo.setIdDeportista(new Deportista(Long.parseLong(asitencia[0])));

            daoAsistencia.guardar(nuevo);
        }
    }

    @Override
    public List<Asistencia> getAsistenciaxId(Long IdControlAsistencia) {
        try {
             return daoAsistencia.getAsistenciaxId(IdControlAsistencia);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
       
        
    }

    @Override
    public PagingLoadResult<Asistencia> getDeportistasxCategoria(Long idCategoria) {
         List<Deportista> deportistas = new ArrayList<Deportista>();
         List<Asistencia> listaRetorno = new ArrayList<Asistencia>();
        try {
            deportistas = daoDeportista.deportistaXCategoria(idCategoria);
            
            for (Deportista deportista : deportistas) {
                Asistencia asistencia= new Asistencia();
                asistencia.setEstado("ASISTE");
                asistencia.setIdDeportista(deportista);
                asistencia.setId_control_asistencia(null);
                listaRetorno.add(asistencia);
            }
            
        } catch (Exception ex) {
            Logger.getLogger(RPCAdminAsistenciaImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        PagingLoadResult<Asistencia> loadResult = new BasePagingLoadResult<Asistencia>(listaRetorno, 1, 1000);
        return loadResult;
    }

    

 
}
