/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.sisegfut.client.util;

/**
 *
 * @author josorio
 */
public class HTTP
{
    /**
     * Mediante una llamada nativa redirecciona el browser a la dirección
     * especificada, en el caso de descargar archivos el contenido del
     * browser se conserva y simplemente lanza el archivo ;)
     * @param url URL a ser cargada
     */
    public static native void redireccionarA( String url )/*-{
        $wnd.location = url;
    }-*/;
}
