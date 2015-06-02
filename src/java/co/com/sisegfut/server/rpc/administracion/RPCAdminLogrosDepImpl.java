/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.sisegfut.server.rpc.administracion;

import co.com.sisegfut.client.datos.dominio.Categoria;
import co.com.sisegfut.client.datos.dominio.Deportista;
import co.com.sisegfut.client.datos.dominio.LogrosDeportivos;
import co.com.sisegfut.client.datos.dominio.Usuarios;
import co.com.sisegfut.client.datos.dominio.dto.DTOLogrosDep;
import co.com.sisegfut.client.util.rpc.RPCAdminLogrosDep;
import co.com.sisegfut.server.datos.dao.DaoLogrosDeportivos;
import com.extjs.gxt.ui.client.data.BasePagingLoadResult;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Andres Hurtado
 */
public class RPCAdminLogrosDepImpl extends RPCMaestroImpl<LogrosDeportivos> implements RPCAdminLogrosDep{
   
    private Usuarios usuarioSession;
    private DaoLogrosDeportivos daoLogrosDeportivos;
    
    @Autowired
    @Override
    public void setUsuarioSession(Usuarios usuarioSession) {
        this.usuarioSession = usuarioSession;
        super.setUsuarioSession(usuarioSession);
    }
    
    @Autowired
    public void setDaoAntDep(DaoLogrosDeportivos daoLogrosDep) {
        this.daoLogrosDeportivos = daoLogrosDep;
        super.setDaoGenerico(daoLogrosDep); 
    }
    
    @Transactional
    @Override
    public void guardarGridLogros(Long IdDep,List<LogrosDeportivos> logrosDeportivos) {
       
      
        for (LogrosDeportivos logrosDep : logrosDeportivos) {
            
            LogrosDeportivos nuevo = new LogrosDeportivos();
            
            nuevo.setIdDeportista(new Deportista(IdDep));
            nuevo.setId((Long)logrosDep.get("id"));
            nuevo.setLogroDeportivo((String) logrosDep.get("logroDeportivo"));
            
            nuevo.setCategoriaLogro(new Categoria((Long) logrosDep.get("categoriaLogro")));
            
            nuevo.setAnioLogro((String) logrosDep.get("anioLogro"));
            
            
            daoLogrosDeportivos.guardar(nuevo);
        }
        
    }

    @Override
    public PagingLoadResult<DTOLogrosDep> getLogrosDeportivos(Long idDeportista) {

        List<LogrosDeportivos> listLogro = new ArrayList<LogrosDeportivos>();
        List<DTOLogrosDep> listaRetorno = new ArrayList<DTOLogrosDep>();
        try {
            listLogro = daoLogrosDeportivos.LogroDepxDeportista(idDeportista);
            if (listLogro != null) {
                for (LogrosDeportivos valorLogro : listLogro) {
                    DTOLogrosDep agg = new DTOLogrosDep();
                    agg.setLogroDeportivo(valorLogro.getLogroDeportivo());
                    agg.setAnioLogro(valorLogro.getAnioLogro());
                    agg.setCategoriaLogro(valorLogro.getCategoriaLogro().getNombrecategoria());
                    agg.setIdCategoria(valorLogro.getCategoriaLogro().getId());
                    agg.setIdLogro(valorLogro.getId());
                    listaRetorno.add(agg);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(RPCAdminLogrosDepImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        PagingLoadResult<DTOLogrosDep> loadResult = new BasePagingLoadResult<DTOLogrosDep>(listaRetorno, 1, 1000);
        return loadResult;
    }

  
    
}
