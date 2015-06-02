package co.com.sisegfut.client.util.rpc;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * Declara métodos para la creación de cuentas
 * @author Juan David Botero <jdbotero@gmail.com>
 */
public interface RPCRecuperarClaveAsync
{
    public void solicitarLlave( String correo, AsyncCallback asyncCallback );
    public void solicitarLlaveMail( String correo, AsyncCallback asyncCallback );
    public void cambiarClave( String llave,
            String clave, AsyncCallback asyncCallback );
}
