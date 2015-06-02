/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.sisegfut.client.util;

import co.com.sisegfut.client.datos.dominio.Usuarios;
import co.com.sisegfut.client.util.rpc.RPCLogin;
import co.com.sisegfut.client.util.rpc.RPCLoginAsync;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

/**
 *
 * @author sisegfut
 */
public class BeansLocales
{
    private static Usuarios usuario;

    static
    {
        usuario = null;
    }

    public static void setUsuario( Usuarios usr )
    {
        usuario = usr;
    }


    public static Usuarios getUsuario()
    {
        // Si no tenemos el usuario lo obtenemos
        if( usuario == null )
        {
            final RPCLoginAsync svc = (RPCLoginAsync) GWT.create(RPCLogin.class);
            ServiceDefTarget endpoint = (ServiceDefTarget) svc;
            endpoint.setServiceEntryPoint("services/RPCLogin");
            svc.obtenerDatosUsuario(
                    new AsyncCallback<Usuarios>()
                    {
                        public void onFailure(Throwable caught)
                        {
                            MessageBox.alert( "Alerta","Se presentó un error, no fue posible obtener el usuario desde el servidor", null );
                        }

                        public void onSuccess( Usuarios respuesta )
                        {
                            usuario = new Usuarios();
                            usuario.copiarDatos( respuesta );
                        }
                    });
        }

        return usuario;
    }

//    private static RPCLoginAsync getService()
//    {
//        return (RPCLoginAsync)PuertoRPC.setRpcEndPointUrl( GWT.create( RPCLogin.class ) );
//    }
}
