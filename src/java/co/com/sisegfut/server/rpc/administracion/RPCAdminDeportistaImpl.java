/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.server.rpc.administracion;

import co.com.sisegfut.client.datos.dominio.Deportista;
import co.com.sisegfut.client.datos.dominio.Usuarios;
import co.com.sisegfut.client.util.rpc.RPCAdminDeportista;
import co.com.sisegfut.server.datos.dao.DaoDeportista;
import co.com.sisegfut.server.util.Formatos;
import com.extjs.gxt.ui.client.data.BasePagingLoadResult;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author anfeh_000
 */
public class RPCAdminDeportistaImpl extends RPCMaestroImpl<Deportista> implements RPCAdminDeportista {

    private Usuarios usuarioSession;
    private DaoDeportista daoDeportista;

    @Autowired
    @Override
    public void setUsuarioSession(Usuarios usuarioSession) {
        this.usuarioSession = usuarioSession;
        super.setUsuarioSession(usuarioSession);
    }

    @Autowired
    public void setDaoDeportista(DaoDeportista daoDeportista) {
        this.daoDeportista = daoDeportista;
        super.setDaoGenerico(daoDeportista);
    }

    @Override
    public Deportista updateConFoto(Deportista dep) {
        try {
            Deportista deportista = daoDeportista.getById(dep.getId());
            dep.setFoto(deportista.getFoto());
            dep.setContentTypeFoto(deportista.getContentTypeFoto());
            daoDeportista.guardar(dep);
            return dep;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public PagingLoadResult<Deportista> getDeportistaxCategoria(Long idCategoria) {

        List<Deportista> listaRetorno = new ArrayList<Deportista>();
        try {
            listaRetorno = daoDeportista.deportistaXCategoria(idCategoria);
        } catch (Exception ex) {
            Logger.getLogger(RPCAdminAsistenciaImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        PagingLoadResult<Deportista> loadResult = new BasePagingLoadResult<Deportista>(listaRetorno, 1, 1000);
        return loadResult;

    }

    @Override
    public String CalcularFCM(Date fechaNacimiento) {
        Long fcm = Math.round(Formatos.calcularFCM(Formatos.getEdad(fechaNacimiento)));
        return fcm.toString();
    }

    @Override
    public Integer CalcularEdad(Date fechaNacimiento) {
        return Formatos.getEdad(fechaNacimiento);
    }

    @Override
    public void actulizarGrasa(Long idDep, String grasa) {

        try {
            Deportista deportista = daoDeportista.getById(idDep);
            deportista.setGrasa(grasa);
            daoDeportista.guardar(deportista);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public PagingLoadResult<Deportista> getDeportistas() {

    List<Deportista> listaRetorno = new ArrayList<Deportista>();
        try {
            listaRetorno = daoDeportista.getDeportistas();
        } catch (Exception ex) {
            Logger.getLogger(RPCAdminAsistenciaImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        PagingLoadResult<Deportista> loadResult = new BasePagingLoadResult<Deportista>(listaRetorno, 1, 1000);
        return loadResult;
    }
    
    

}
