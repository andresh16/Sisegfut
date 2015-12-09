

package co.com.sisegfut.client.util.rpc;

import co.com.sisegfut.client.entidades.RespuestaRPC;
import co.com.sisegfut.client.util.Pair;
import com.extjs.gxt.ui.client.data.FilterPagingLoadConfig;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.google.gwt.user.client.rpc.AsyncCallback;
import java.io.Serializable;
import java.util.List;

/**
 * 
 * @author anfeh_000 
 * @param <T> 
 */
public interface RPCMaestroAsync<T extends Serializable> {

    public void getConsulta(FilterPagingLoadConfig config, AsyncCallback<PagingLoadResult<T>> callback);
    
    public void procesarConsulta(FilterPagingLoadConfig config, AsyncCallback<Pair<Long, List<T>>> callback);
    
    public void guardarEntidad(T entidad, AsyncCallback<RespuestaRPC<T>> callback);
    
    public void eliminarEntidad(Long idEntidad, AsyncCallback<RespuestaRPC<T>> callback);
    
    public void eliminarEntidad(T entidad, AsyncCallback<RespuestaRPC<T>> callback);
    
    public void reactivarEntidad(Long idEntidad, AsyncCallback<RespuestaRPC<T>> callback);
    
    public void getEntidades(int tipo, AsyncCallback<List<T>> callback);
    
    public void getEntidades(ModelData loadConfig, String campofiltro, int tipo, AsyncCallback<List<T>> callback);
    
}

