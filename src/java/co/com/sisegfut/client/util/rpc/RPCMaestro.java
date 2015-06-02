/**
* RPCMaestro
* Versión 1.0
* 15/09/2013
*
* Copyright(c) 2007-2012, Boos IT.
* admin@boos.com.co
*
* http://boos.com.co/license
**/

package co.com.sisegfut.client.util.rpc;

import co.com.sisegfut.client.datos.dominio.EntidadPerpetua;
import co.com.sisegfut.client.entidades.RespuestaRPC;
import co.com.sisegfut.client.util.Pair;
import com.extjs.gxt.ui.client.data.FilterPagingLoadConfig;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.google.gwt.user.client.rpc.RemoteService;
import java.io.Serializable;
import java.util.List;


/**
 * 
 * @author josorio
 * @param <T> 
 */
public interface RPCMaestro<T extends Serializable> extends RemoteService{

    
    /**
     * Retorna la lista paginada segun los filtros enviados 
     * @param config
     * @return 
     */
    public PagingLoadResult<T> getConsulta(FilterPagingLoadConfig config);
    
    /**
     * Procesa la consulta armando la estructura de esta segun los parametros recibidos y retornando la lista.
     * @param config
     * @return 
     */
    public Pair<Long, List<T>> procesarConsulta(FilterPagingLoadConfig config);
    
    /**
     * Guarda la entidad entregada, si no existía lo crea
     *
     * @param usuario
     * @return Alguno de los siguientes valores:
     */
    public RespuestaRPC<T> guardarEntidad(T entidad);
    
    /**
     * Elimina la entidad indicada.
     * @param idEntidad
     * @return 
     */
    public RespuestaRPC<T> eliminarEntidad(Long idEntidad);
    
    /**
     * Elimina la entidad indicada.
     * @param entidad
     * @return 
     */
    public RespuestaRPC<T> eliminarEntidad(T entidad);
    
    /**
     * Reactiva una entidad previamente eliminada.
     * @param idEntidad
     * @return 
     */
    public RespuestaRPC<T> reactivarEntidad(Long idEntidad);
    
    /**
     * Lista las entidades segun el tipo
     * @param tipo EntidadPerpetua.TIPO_UNDELETED o EntidadPerpetua.TIPO_DELETED
     * @return 
     */
    public List<T> getEntidades(int tipo);
    
    /**
     * Lista las entidades segun el tipo, se usa filtros
     * @param loadConfig
     * @param campofiltro
     * @param tipo tipo EntidadPerpetua.TIPO_UNDELETED o EntidadPerpetua.TIPO_DELETED
     * @return 
     */
    public List<T> getEntidades(ModelData loadConfig, String campofiltro, int tipo);
    
}
