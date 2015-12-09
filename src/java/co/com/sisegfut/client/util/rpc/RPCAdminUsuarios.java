
package co.com.sisegfut.client.util.rpc;

import co.com.sisegfut.client.datos.dominio.Rol;
import co.com.sisegfut.client.datos.dominio.Usuarios;
import co.com.sisegfut.client.entidades.RespuestaRPC;
import java.util.List;

/**
 *
 * @author anfeh_000
 */
public interface RPCAdminUsuarios extends RPCMaestro<Usuarios> { 

    /**
     * Para cambiar la clave de un usuario
     * @param idUsuario
     * @param clave
     * @return 
     */
    public RespuestaRPC<Integer> cambiarClave(Long idUsuario, String clave);
    /**
     * Para cambiar la clave de un usuario.
     * @param usuario
     * @param clave
     * @return 
     */
    public RespuestaRPC<Integer> cambiarClave(Usuarios usuario, String clave);
    /**
     * Para obtener los datos del usuario logueado.
     * @param idUsuario
     * @return 
     */
    public RespuestaRPC<Usuarios> obtenerDatosUsuario(Long idUsuario);
    /**
     * Para listar los roles activos.
     * @return 
     */
    public List<Rol> getRoles();
    /**
     * Para listar los usuario segun el rol
     * @param rol
     * @return 
     */
    public List<Usuarios> getUsuariosRol(long rol);
}
