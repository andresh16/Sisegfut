package co.com.sisegfut.server.rpc.usuario;

import co.com.sisegfut.client.util.rpc.RPCRecuperarClave;
import co.com.sisegfut.server.comm.GwtRpcEndPoint;
import co.com.sisegfut.server.datos.dao.DaoUsuario;
import co.com.sisegfut.client.datos.dominio.Usuarios;
import co.com.sisegfut.server.servicios.local.ServicioCorreosImpl;
import co.com.sisegfut.client.util.Sumador;
import co.com.sisegfut.server.util.Cifrado;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import java.net.MalformedURLException;

import javax.mail.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 *
 * @author fhurtado
 */
//@Controller
//@GwtRpcEndPoint
public class RPCRecuperarClaveImpl extends RemoteServiceServlet implements RPCRecuperarClave
{
    @Autowired
    private DaoUsuario daoUsuario;
    @Autowired
    private ServicioCorreosImpl servicioCorreos;

    /**
     * Envía una llave al correo del usuario especificado y la escribe en
     * la tabla de usuarios para realizar seteos de clave
     * @param documento Dirección de correo electrónico para recuperar la clave
     * @return Uno de los siguientes valores:
     * <ul>
     *  <li>0: Si todo termina exitosamente</li>
     *  <li>1: Si no se encuentra el usuario</li>
     *  <li>2: En caso de error al enviar el mail</li>
     * </ul>
     *
     */
    public Integer solicitarLlave( String correo )
    {
        // asumimos que se presentó un error
        Integer retorno = new Integer( 2 );

        // Busco el usuario
        Usuarios usuario = daoUsuario.getActivoByDocumento( correo );

        if ( usuario != null )
        {
            try
            {
                //Se valida que el usuario tenga un correo asociado
                //0001707: correo electrónico debe ser opcional al crear usuarios
                if (usuario.getCorreoElectronico() != null && !"".equals(usuario.getCorreoElectronico())) {

                    // Creo la llave
                    String llave = Sumador.suma(usuario.getCorreoElectronico() + usuario.getClave());

                    // Guardo la clave en la BD
                    usuario.setLlaveCambioClave(llave);
                    daoUsuario.guardar(usuario);

                    // Envío la clave
                    enviarLlaveCambioClave(usuario.getCorreoElectronico(), llave);
                    retorno = new Integer(0);
                }
                else{
                    retorno = new Integer(3);
                }
            }
            catch( MessagingException ex )
            {
                ex.printStackTrace();
                retorno = new Integer( 2 );
            }
            catch( MalformedURLException ex )
            {
                ex.printStackTrace();
                retorno = new Integer( 2 );
            }
        }
        else // Si no encontramos el usuario
        {
            retorno = new Integer( 1 );
        }
        return retorno;
    }

    /**
     * Envía una llave al correo del usuario especificado y la escribe en
     * la tabla de usuarios para realizar seteos de clave
     * @param documento Dirección de correo electrónico para recuperar la clave
     * @return Uno de los siguientes valores:
     * <ul>
     *  <li>0: Si todo termina exitosamente</li>
     *  <li>1: Si no se encuentra el usuario</li>
     *  <li>2: En caso de error al enviar el mail</li>
     * </ul>
     *
     */
    public Integer solicitarLlaveMail( String correo )
    {
        // asumimos que se presentó un error
        Integer retorno = new Integer( 2 );

        // Busco el usuario
        Usuarios usuario = daoUsuario.getActivoByCorreo2( correo );

        if ( usuario != null )
        {
            try
            {
                // Creo la llave
                String llave = Sumador.suma( usuario.getCorreoElectronico() + usuario.getClave() );

                // Guardo la clave en la BD
                usuario.setLlaveCambioClave( llave );
                daoUsuario.guardar( usuario );

                // Envío la clave
                enviarLlaveCambioClave( usuario.getCorreoElectronico(), llave );
                retorno = new Integer( 0 );
            }
            catch( MessagingException ex )
            {
                ex.printStackTrace();
                retorno = new Integer( 2 );
            }
            catch( MalformedURLException ex )
            {
                ex.printStackTrace();
                retorno = new Integer( 2 );
            }
        }
        else // Si no encontramos el usuario
        {
            retorno = new Integer( 1 );
        }
        return retorno;
    }
    /**
     * Envía por E-Mail la llave para el cambio de clave
     * @param direccionCorreo Dirección a la cual enviar el mail con la clave
     * @param llave Llave a ser enviada
     */
    private void enviarLlaveCambioClave( String direccionCorreo, String llave )
            throws MessagingException, MalformedURLException
    {
        // Preparo los textos a ser enviados
        StringBuffer mensajeHTML = new StringBuffer();
        mensajeHTML.append( "<b>Cordial saludo,</b>" );
        mensajeHTML.append( "<br/><br/>" );
        mensajeHTML.append( "Recibimos una solicitud de parte suya en " );
        mensajeHTML.append( "nuestro sistema con el fin de recuperar su " );
        mensajeHTML.append( "clave de acceso, para ello le anexamos en " );
        mensajeHTML.append( "este correo una llave que debe copiar y pegar en " );
        mensajeHTML.append( "la pantalla de acceso en el lugar donde se " );
        mensajeHTML.append( "indica en la pestaña 'Recuperación de clave' " );
        mensajeHTML.append( "y en los dos campos siguientes podrá escribir " );
        mensajeHTML.append( "una nueva clave para acceder su cuenta.<br/><br/>" );
        mensajeHTML.append( "Su llave es:<br/>" );
        mensajeHTML.append( "<b>" );
        mensajeHTML.append( llave );
        mensajeHTML.append( "</b>" );
        mensajeHTML.append( "<br/><br/>Cordialmente, soporte Sistema" );

        StringBuffer mensajeTXT = new StringBuffer();
        mensajeTXT.append( "Cordial saludo,\n\n" );
        mensajeTXT.append( "Recibimos una solicitud de parte suya en " );
        mensajeTXT.append( "nuestro sistema con el fin de recuperar la " );
        mensajeTXT.append( "contraseña de acceso, para ello le anexamos en " );
        mensajeTXT.append( "este correo una llave que debe copiar y pegar en " );
        mensajeTXT.append( "la pantalla de acceso en el lugar donde se " );
        mensajeTXT.append( "indica en la pestaña 'Recuperar contraseña' " );
        mensajeTXT.append( "y en los dos campos siguientes podrá escribir " );
        mensajeTXT.append( "una nueva contraseña para acceder su cuenta." );
        mensajeTXT.append( "Su llave es:\n\n" );
        mensajeTXT.append( llave );
        mensajeTXT.append( "\n\nCordialmente, soporte Sistema Sisegfut" );

        servicioCorreos.enviarCorreo( direccionCorreo, "Recuperar contraseña",
                mensajeTXT.toString(), mensajeHTML.toString(), null, null);
    }

    /**
     * Cambia la clave del usuario que tenga la llave dada como parámetro
     * @param llave
     * @param clave
     * @return Uno de los siguientes valores:
     * <ul>
     *  <li>0: Si todotermina correctamente</li>
     *  <li>1: Si la clave no es válida</li>
     *  <li>2: Si se presentan errores técnicos</li>
     * </ul>
     */
    public Integer cambiarClave( String llave, String clave )
    {
        // Asumimos error
        Integer retorno = new Integer( 2 );

        // Busco el usuario
        Usuarios usuario = daoUsuario.getByLlaveCambioClave( llave );

        if ( usuario != null )
        {
            // Hago el hash de la clave
            String claveHash = Cifrado.getStringMessageDigest(clave, Cifrado.SHA256) ;
            usuario.setClave( claveHash );
            usuario.setLlaveCambioClave( null );
            daoUsuario.guardar( usuario );

            retorno = new Integer( 0 );
        }
        else // Si no encontramos el usuario (la clave no es válida)
        {
            retorno = new Integer( 1 );
        }

        return retorno;
    }
}
