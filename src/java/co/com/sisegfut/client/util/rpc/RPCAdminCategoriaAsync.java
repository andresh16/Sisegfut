/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.client.util.rpc;

import co.com.sisegfut.client.datos.dominio.Categoria;
import com.extjs.gxt.ui.client.data.ModelData;
import com.google.gwt.user.client.rpc.AsyncCallback;
import java.util.List;

/**
 *
 * @author fhurtado
 */
public interface RPCAdminCategoriaAsync extends RPCMaestroAsync<Categoria> {

    public void getCategorias(ModelData modelData, Long idUsuarioLogeado, AsyncCallback<List<Categoria>> callback);

    public void getCategorias2(Long IdUserLogin,AsyncCallback<List<Categoria>> callback);
    
    public void getGuardarCategorias(Categoria categoria, AsyncCallback<Categoria> callback);
}
