package co.com.sisegfut.server.rpc.usuario;

import co.com.sisegfut.client.util.rpc.RPCLogin;
import co.com.sisegfut.server.datos.dao.DaoUsuario;
import co.com.sisegfut.client.datos.dominio.Usuarios;
import co.com.sisegfut.client.util.Sumador;
import co.com.sisegfut.server.comm.GwtRpcEndPoint;
import co.com.sisegfut.server.util.Cifrado;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

/**
 *
 * @author anfeh_000
 */
//@Controller
//@GwtRpcEndPoint
public class RPCLoginImpl extends RemoteServiceServlet implements RPCLogin
{
    @Autowired
    private DaoUsuario daoUsuarios;
    @Autowired
    private Usuarios usuarioSession;

    /**
     * Verifica si un usuario puede loguearse en el sistema o no, en caso
     * de ser los datos válidos se creará una sesión en el servidor
     * @param correo correo del usuario para intentar el login
     * @param clave Clave del usuario a loguear sin encriptar (se cifra usando Cifrado.SHA256)
     * @return Nulo si el usuario no existe, en caso de existir, un objeto
     *         de la clase Usuario con los datos del mismo
     */
    public Usuarios login( String correo, String clave )
    {

        Usuarios usuarioHib = daoUsuarios.getActivoByDocumentoAndClave( correo, Cifrado.getStringMessageDigest(clave, Cifrado.SHA256) );

        if( usuarioHib != null )
        {
            usuarioSession.copiarDatos( usuarioHib );
        }     
        return usuarioHib;
    }
    
    /**
     * Verifica si un usuario puede loguearse en el sistema o no, en caso
     * de ser los datos válidos se creará una sesión en el servidor
     * @param documento Documento del usuario para intentar el login
     * @param clave Clave del usuario a loguear sin encriptar (se cifra usando Cifrado.SHA256)
     * @return Nulo si el usuario no existe, en caso de existir, un objeto
     *         de la clase Usuario con los datos del mismo
     */
    public Usuarios loginMail( String email, String clave )
    {

        Usuarios usuarioHib = daoUsuarios.getActivoByEmailAndClave( email, Cifrado.getStringMessageDigest(clave, Cifrado.SHA256) );

        if( usuarioHib != null )
        {
            usuarioSession.copiarDatos( usuarioHib );
        }
        
        return usuarioHib;
    }

    /**
     * Retorna el objeto usuario que se tiene en la sesión
     * @return
     */
    public Usuarios obtenerDatosUsuario()
    {
        Usuarios usuario = new Usuarios();
        usuario.copiarDatos( usuarioSession );
        return usuario;
    }
    @Override
    public Integer estadoSesion() {
        if (usuarioSession == null || usuarioSession.getId()==null || usuarioSession.getId()== 0) {            
            System.out.println("Finalizó la sesion");
            return -1;
        }
        return 0;
    }

}
