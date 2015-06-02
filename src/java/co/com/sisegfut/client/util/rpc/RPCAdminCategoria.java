/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.client.util.rpc;

import co.com.sisegfut.client.datos.dominio.Categoria;
import com.extjs.gxt.ui.client.data.ModelData;

import java.util.List;

/**
 *
 * @author fhurtado
 */
public interface RPCAdminCategoria extends RPCMaestro<Categoria>{
    public List<Categoria> getCategorias(ModelData loadConfig,Long usuarioLogeado);
    
    public List<Categoria> getCategorias2(Long IdUserLogin);
    
    public Categoria getGuardarCategorias(Categoria categoria);
}
