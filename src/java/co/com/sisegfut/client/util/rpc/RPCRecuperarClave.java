/*
 * RPCRecuperarClave.java
 * Created on 23 de marzo de 2008, 05:55 PM
 */
package co.com.sisegfut.client.util.rpc;

import com.google.gwt.user.client.rpc.RemoteService;

/**
 * Declara métodos para crear una cuenta de usuario
 * @author Juan David Botero <jdbotero@gmail.com>
 */
public interface RPCRecuperarClave extends RemoteService
{
    public Integer solicitarLlave( String correo );
    
    public Integer solicitarLlaveMail( String correo);

    public Integer cambiarClave( String llave, String clave );
}
