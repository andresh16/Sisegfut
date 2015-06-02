/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.server.rpc.administracion;

import co.com.sisegfut.client.datos.dominio.Categoria;
import co.com.sisegfut.client.datos.dominio.Usuarios;
import co.com.sisegfut.client.util.rpc.RPCAdminCategoria;
import co.com.sisegfut.server.datos.dao.DaoCategoria;
import com.extjs.gxt.ui.client.data.ModelData;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author fhurtado
 */
public class RPCAdminCategoriaImpl extends RPCMaestroImpl<Categoria> implements RPCAdminCategoria {
    
    private DaoCategoria daocategoria;
    private Usuarios usuarioSession;

    @Autowired
    @Override
    public void setUsuarioSession(Usuarios usuarioSession) {
        this.usuarioSession = usuarioSession;
        super.setUsuarioSession(usuarioSession);
    }
    
    @Autowired
    public void setDaoCategoria(DaoCategoria daocategoria) {
        this.daocategoria = daocategoria;
        this.setDaoGenerico(daocategoria);
    }

  
    

    
    @Override
    public List<Categoria> getCategorias(ModelData loadConfig,Long usuarioLogeado) {
        try {
            ModelData m = (ModelData) loadConfig;
            //Get query string
            String query = (String) m.get("query");
        
            return daocategoria.getCategorias(query, usuarioLogeado);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Categoria getGuardarCategorias(Categoria categoria) {
       
        Categoria categoriaSave=new Categoria();
        try {
            daocategoria.guardar(categoria);
            categoriaSave=categoria;
        } catch (Exception e) {
            e.printStackTrace();
            
        }
         return categoriaSave;
         }

    @Override
    public List<Categoria> getCategorias2(Long IdUserLogin) {
        
       return daocategoria.getCategoria2(IdUserLogin);
          
          }
}
