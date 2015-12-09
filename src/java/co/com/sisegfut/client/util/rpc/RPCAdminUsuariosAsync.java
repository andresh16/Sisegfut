package co.com.sisegfut.client.util.rpc;

import co.com.sisegfut.client.datos.dominio.Rol;
import co.com.sisegfut.client.datos.dominio.Usuarios;
import co.com.sisegfut.client.entidades.RespuestaRPC;
import com.google.gwt.user.client.rpc.AsyncCallback;
import java.util.List;

/**
 *
 * @author anfeh_000
 */
public interface RPCAdminUsuariosAsync extends RPCMaestroAsync<Usuarios> { 

    /**
     * Para cambiar la clave de un usuario
     * @param idUsuario
     * @param clave
     * @return 
     */
    public void cambiarClave(Long idUsuario, String clave, AsyncCallback<RespuestaRPC<Integer>> callback);
    /**
     * Para cambiar la clave de un usuario.
     * @param usuario
     * @param clave
     * @return 
     */
    public void cambiarClave(Usuarios usuario, String clave, AsyncCallback<RespuestaRPC<Integer>> callback);
    /**
     * Para obtener los datos del usuario logueado.
     * @param idUsuario
     * @return 
     */
    public void obtenerDatosUsuario(Long idUsuario, AsyncCallback<RespuestaRPC<Usuarios>> callback);
    /**
     * Para listar los roles activos.
     * @return 
     */
    public void getRoles(AsyncCallback<List<Rol>> callback);
        
    /**
     * Para listar los usuario segun el rol
     * @param rol
     * @return 
     */
    public void getUsuariosRol(long rol, AsyncCallback<List<Usuarios>> callback);
}
