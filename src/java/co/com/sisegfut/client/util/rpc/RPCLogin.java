package co.com.sisegfut.client.util.rpc;

import co.com.sisegfut.client.datos.dominio.Usuarios;
import com.google.gwt.user.client.rpc.RemoteService;

/**
 * Declara métodos usados para el login mediante RPC
 * @author Juan David Botero <jdbotero@gmail.com>
 */
public interface RPCLogin extends RemoteService
{
    /**
     * Verifica si un usuario puede loguearse en el sistema o no, en caso
     * de ser los datos válidos se creará una sesión en el servidor
     * @param documento Documento del usuario para intentar el login
     * @param clave Clave del usuario a loguear (cifrado usando Cifrado.SHA256)
     * @return Nulo si el usuario no existe, en caso de existir, un objeto
     *         de la clase Usuario con los datos del mismo
     */
    public Usuarios login( String correo, String clave );
    /**
     * Retorna el objeto usuario que se tiene en la sesión
     * @return
     */
    public Usuarios obtenerDatosUsuario();
    /**
     * Verifica si un usuario puede loguearse en el sistema o no, en caso
     * de ser los datos válidos se creará una sesión en el servidor
     * @param mail Documento del usuario para intentar el login
     * @param clave Clave del usuario a loguear (cifrado usando Cifrado.SHA256)
     * @return Nulo si el usuario no existe, en caso de existir, un objeto
     *         de la clase Usuario con los datos del mismo
     */
    public Usuarios loginMail( String email, String clave );
    
    public Integer estadoSesion();
}
