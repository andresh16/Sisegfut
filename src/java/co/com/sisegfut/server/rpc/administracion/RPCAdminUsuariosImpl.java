package co.com.sisegfut.server.rpc.administracion;

import co.com.sisegfut.client.datos.dominio.Rol;
import co.com.sisegfut.client.datos.dominio.Usuarios;
import co.com.sisegfut.client.entidades.RespuestaRPC;
import co.com.sisegfut.client.util.rpc.RPCAdminUsuarios;
import co.com.sisegfut.server.datos.dao.DaoRol;
import co.com.sisegfut.server.datos.dao.DaoUsuario;
import co.com.sisegfut.server.util.Cifrado;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author anfeh_000
 */
public class RPCAdminUsuariosImpl extends RPCMaestroImpl<Usuarios> implements RPCAdminUsuarios {

    private DaoRol daoRol;
    private Usuarios usuarioSession;
    private DaoUsuario daoUsuario;

    @Autowired
    public void setDaoUsuario(DaoUsuario daoUsuario) {
        this.daoUsuario = daoUsuario;
        this.setDaoGenerico(daoUsuario);
    }

    @Autowired
    public void setDaoRol(DaoRol daoRol) {
        this.daoRol = daoRol;
    }

    @Autowired
    @Override
    public void setUsuarioSession(Usuarios usuarioSession) {
        this.usuarioSession = usuarioSession;
        super.setUsuarioSession(usuarioSession);
    }

    @Override
    public RespuestaRPC<Usuarios> guardarEntidad(Usuarios usuario) {
        RespuestaRPC<Usuarios> retorno = new RespuestaRPC<Usuarios>();

        // Validamos que sea admin de sistema si desea almacenar un usuario administrador
        if (!usuarioSession.isAdminClub() && usuario.isAdminClub()) {
            retorno.setResultado(RespuestaRPC.RESULTADO_FALLO_PERMISOS_INSUFICIENTES);
            return retorno;
        }

        //TODO validar que no exista el correo ni la cedula
        // Si la clave está nula poner como clave la cédula
        if (usuario.getClave() == null || usuario.getClave().isEmpty()) {
            usuario.setClave(Cifrado.getStringMessageDigest(usuario.getDocumento(), Cifrado.SHA256));
        }
        //Validamos que la identificacion ingresada, sean única
        List<Usuarios> validacion;
        validacion = daoUsuario.getActivoByDocumentoAndNotId(usuario.getDocumento(), usuario.getId());
        if (!validacion.isEmpty()) {
            retorno.setResultado(RespuestaRPC.RESULTADO_FALLO);
            retorno.setDescripcionError("Ya existe un usuario con el documento suministrado");
            return retorno;
        }
        //Validamos que el email ingresado sea unico
        validacion = daoUsuario.getActivoByCorreoAndNotId(usuario.getCorreoElectronico(), usuario.getId());
        if (!validacion.isEmpty()) {
            retorno.setResultado(RespuestaRPC.RESULTADO_FALLO);
            retorno.setDescripcionError("Ya existe un usuario con el correo suministrado");
            return retorno;
        }

        /**
         * Ejecuto el proceso de guardado normal de la entidad.
         */
        return super.guardarEntidad(usuario);
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public RespuestaRPC<Usuarios> eliminarEntidad(Long idEntidad) {
        RespuestaRPC<Usuarios> retorno = new RespuestaRPC<Usuarios>();

        Usuarios borrar = daoUsuario.getById(idEntidad);

        // Validamos que sea admin de negocio si desae borrar un usuario administrador
        if (!usuarioSession.isAdminClub() && borrar.getRol().getId() == 1) {
            retorno.setResultado(RespuestaRPC.RESULTADO_FALLO_PERMISOS_INSUFICIENTES);
            return retorno;
        }

        //ejecuto el proceso maestro.
        return super.eliminarEntidad(idEntidad);
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public RespuestaRPC<Usuarios> eliminarEntidad(Usuarios entidad) {
        RespuestaRPC<Usuarios> retorno = new RespuestaRPC<Usuarios>();

        // Validamos que sea admin de negocio si desae borrar un usuario administrador
        if (!usuarioSession.isAdminClub() && entidad.getRol().getId() == 1) {
            retorno.setResultado(RespuestaRPC.RESULTADO_FALLO_PERMISOS_INSUFICIENTES);
            return retorno;
        }

        return super.eliminarEntidad(entidad);
    }

    /**
     * {@inheritdoc}
     */
    @Override
    public RespuestaRPC<Usuarios> reactivarEntidad(Long idUsuario) {
        RespuestaRPC<Usuarios> retorno = new RespuestaRPC<Usuarios>();

        Usuarios reactivar = daoUsuario.getById(idUsuario);
        // Validamos que sea admin de sistema si desae reactivar un usuario administrador
        if (!usuarioSession.isAdminClub() && reactivar.getRol().getId() == 1) {
            retorno.setResultado(RespuestaRPC.RESULTADO_FALLO_PERMISOS_INSUFICIENTES);
            return retorno;
        }

        return super.reactivarEntidad(idUsuario);
    }

    /**
     * Cambia la clave del usuario con el id dado y al valor dado
     *
     * @param idUsuario Id del usuario al cual cambiar la clave
     * @param clave Clave a ser asignada al usuario
     * @return Alguno de los siguientes valores: <ul> <li>0: La clave se cambió
     * con éxito</li> <li>1: El usuario no tiene privilegios</li> <li>2: En caso
     * de no haber sesión</li> <li>3: Error al realizar el cambio</li> </ul>
     */
    public RespuestaRPC<Integer> cambiarClave(Long idUsuario, String clave) {
        RespuestaRPC<Integer> retorno = new RespuestaRPC<Integer>();

        // Validamos session
        if (usuarioSession == null || usuarioSession.getId() == null || usuarioSession.getId().intValue() == 0) {
            retorno.setResultado(RespuestaRPC.RESULTADO_FALLO_NO_SESSION);
            return retorno;
        }

        try {

            Usuarios cambiar = daoUsuario.getById(idUsuario);
            // Validamos que sea admin de negocio si desae modificar un usuario administrador
            if (!usuarioSession.isAdminClub() && cambiar.getRol().getId() == 1) {
                retorno.setResultado(RespuestaRPC.RESULTADO_FALLO_PERMISOS_INSUFICIENTES);
                return retorno;
            }
            cambiar.setClave(Cifrado.getStringMessageDigest(clave, Cifrado.SHA256));
            daoUsuario.guardar(cambiar);
            retorno.setResultado(RespuestaRPC.RESULTADO_OK);
        } catch (Exception e) {
            e.printStackTrace();
            retorno.setResultado(RespuestaRPC.RESULTADO_EXCEPCION_SERVER);
        }

        return retorno;
    }

    /**
     * {@inheritdoc}
     */
    public RespuestaRPC<Integer> cambiarClave(Usuarios usuario, String clave) {
        RespuestaRPC<Integer> retorno = new RespuestaRPC<Integer>();

        // Validamos session
        if (usuarioSession == null || usuarioSession.getId() == null || usuarioSession.getId().intValue() == 0) {
            retorno.setResultado(RespuestaRPC.RESULTADO_FALLO_NO_SESSION);
            return retorno;
        }

        try {
            // Validamos que sea admin de negocio si desae modificar un usuario administrador
            if (!usuarioSession.isAdminClub() && usuario.getRol().getId() == 1) {
                retorno.setResultado(RespuestaRPC.RESULTADO_FALLO_PERMISOS_INSUFICIENTES);
                return retorno;
            }
            usuario.setClave(Cifrado.getStringMessageDigest(clave, Cifrado.SHA256));//La clave deberia venir encriptada desde el cliente pero el algoritmo de encripcion no se puede usar desde el lado del cliente.
            daoUsuario.guardar(usuario);
            retorno.setResultado(RespuestaRPC.RESULTADO_OK);
        } catch (Exception e) {
            e.printStackTrace();
            retorno.setResultado(RespuestaRPC.RESULTADO_EXCEPCION_SERVER);
        }

        return retorno;
    }

    /**
     *
     * @param idUsuario
     * @return Un objeto usuario con los datos del mismo, nulo en caso de error
     * o no encontrar el usuario
     */
    public RespuestaRPC<Usuarios> obtenerDatosUsuario(Long idUsuario) {
        RespuestaRPC<Usuarios> retorno = new RespuestaRPC<Usuarios>();

        // Validamos session
        if (usuarioSession == null || usuarioSession.getId() == null || usuarioSession.getId().intValue() == 0) {
            retorno.setResultado(RespuestaRPC.RESULTADO_FALLO_NO_SESSION);
            return retorno;
        }

        try {
            Usuarios usuario = daoUsuario.getById(idUsuario);
            retorno.setObjetoRespuesta(usuario);
            retorno.setResultado(RespuestaRPC.RESULTADO_OK);
        } catch (Exception e) {
            e.printStackTrace();
            retorno.setResultado(RespuestaRPC.RESULTADO_EXCEPCION_SERVER);
        }

        return retorno;
    }

    /**
     * {@inheritdoc}
     */
    public List<Rol> getRoles() {
        return daoRol.listar();
    }

    public List<Usuarios> getUsuariosRol(long rol) {

        return daoUsuario.getUsuariosActivosPropietarios(rol);

    }

}
