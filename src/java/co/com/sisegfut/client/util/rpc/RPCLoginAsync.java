package co.com.sisegfut.client.util.rpc;

import co.com.sisegfut.client.datos.dominio.Usuarios;
import com.google.gwt.user.client.rpc.AsyncCallback;


/**
 * Declara métodos usados para el login mediante RPC
 * @author Juan David Botero <jdbotero@gmail.com>
 */
public interface RPCLoginAsync
{
    public void login( String correo, String clave, AsyncCallback<Usuarios> callback );
    public void loginMail( String email, String clave, AsyncCallback<Usuarios> callback );
    public void obtenerDatosUsuario( AsyncCallback<Usuarios> asyncCallback );
       /**
     * 
     * @param asyncCallback 
     */
    public void estadoSesion( AsyncCallback<Integer> asyncCallback );
}
