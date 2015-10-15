package co.com.sisegfut.server.vista.rest;

import co.com.sisegfut.client.datos.dominio.Cargos;
import co.com.sisegfut.client.datos.dominio.Categoria;
import co.com.sisegfut.client.datos.dominio.Eps;
import co.com.sisegfut.client.datos.dominio.InstEducativa;
import co.com.sisegfut.client.datos.dominio.NivelEducativo;
import co.com.sisegfut.client.datos.dominio.Posiciones;
import co.com.sisegfut.client.datos.dominio.Rol;
import co.com.sisegfut.client.datos.dominio.TipoDeportista;
import co.com.sisegfut.client.datos.dominio.TipoDocumento;
import co.com.sisegfut.client.datos.dominio.Usuarios;
import co.com.sisegfut.server.datos.dao.DaoCargos;
import co.com.sisegfut.server.datos.dao.DaoCategoria;
import co.com.sisegfut.server.datos.dao.DaoEps;
import co.com.sisegfut.server.datos.dao.DaoInstEducativa;
import co.com.sisegfut.server.datos.dao.DaoNivelEducativo;
import co.com.sisegfut.server.datos.dao.DaoPosiciones;
import co.com.sisegfut.server.util.Cifrado;
import co.com.sisegfut.server.datos.dao.DaoRol;
import co.com.sisegfut.server.datos.dao.DaoSituacionesJuego;
import co.com.sisegfut.server.datos.dao.DaoTipoDeportista;
import co.com.sisegfut.server.datos.dao.DaoTipoDocumento;
import co.com.sisegfut.server.datos.dao.DaoUsuario;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by IntelliJ IDEA. User: hansospina Date: 6/20/11 Time: 10:48 AM To
 * change this template use File | Settings | File Templates.
 */
@Component
public class Bootstrap implements InitializingBean {

    private static Logger log = Logger.getLogger(Bootstrap.class);
    @Autowired
    private DaoUsuario daoUsuario;
    @Autowired
    private DaoRol daoRol;
    @Autowired
    private DaoTipoDocumento daoTipoDocumento;
    @Autowired
    private DaoNivelEducativo daoNivelEducativo;
    @Autowired
    private DaoPosiciones daoPosiciones;
    @Autowired
    private DaoEps daoEps;
    @Autowired
    private DaoSituacionesJuego daoSituacionesJuego;
    @Autowired
    private DaoInstEducativa daoInstEducativa;
    @Autowired
    private DaoCargos daoCargos ;
    @Autowired
    private DaoCategoria daoCategoria;
    @Autowired
    private DaoTipoDeportista daoTipoDeportista;


    /**
     * Invoked by a BeanFactory after it has set all bean properties supplied
     * (and satisfied BeanFactoryAware and ApplicationContextAware). <p>This
     * method allows the bean instance to perform initialization only possible
     * when all bean properties have been set and to throw an exception in the
     * event of misconfiguration.
     *
     * @throws Exception in the event of misconfiguration (such as failure to
     * set an essential property) or if initialization fails.
     */
    @Override
    @Transactional
    public void afterPropertiesSet() throws Exception {
        inicializarUsuriosGrupos();
    }
    

    private void inicializarUsuriosGrupos() {
        try {
            Rol g = daoRol.getById(Rol.ROL_ADMINISTRADOR_SISTEMA);
            // if there is data already created
            if (g != null) {
                return;
            }

            Rol admin = new Rol();
//            admin.setId(Rol.ROL_ADMINISTRADOR_SISTEMA);
            admin.setAutorizadoLogin(Boolean.TRUE);
            admin.setNombre("Administrador sistema");
            daoRol.guardar(admin);

            Rol adminClub = new Rol();
//            adminNegocio.setId(Rol.ROL_ADMINISTRADOR_NEGOCIO); 
            adminClub.setAutorizadoLogin(Boolean.TRUE);
            adminClub.setNombre("Administrador club");
            daoRol.guardar(adminClub);
            
            Rol preparador = new Rol();
//            adminNegocio.setId(Rol.ROL_ADMINISTRADOR_NEGOCIO); 
            preparador.setAutorizadoLogin(Boolean.TRUE);
            preparador.setNombre("Entrenador");
            daoRol.guardar(preparador);
            
/*
            Inicializo el maestro de Tipo de documento
            */       
            TipoDocumento tipoDocumento = new TipoDocumento();
            tipoDocumento.setNombreTipoDocumento("CÉDULA DE CUIDADANÍA");
            daoTipoDocumento.guardar(tipoDocumento);
            
            tipoDocumento = new TipoDocumento();
            tipoDocumento.setNombreTipoDocumento("TARJETA DE IDENTIDAD");
            daoTipoDocumento.guardar(tipoDocumento);
            
            tipoDocumento = new TipoDocumento();
            tipoDocumento.setNombreTipoDocumento("PASAPORTE");
            daoTipoDocumento.guardar(tipoDocumento);
            
            tipoDocumento = new TipoDocumento();
            tipoDocumento.setNombreTipoDocumento("CÉDULA DE EXTRANJERÍA");
            daoTipoDocumento.guardar(tipoDocumento);
            
            /*
            Eps
            */
            Eps eps = new Eps();
            eps.setNombreEps("SALUDCOOP");
            daoEps.guardar(eps);
            
            eps = new Eps();
            eps.setNombreEps("SURA");
            daoEps.guardar(eps);
            
            eps = new Eps();
            eps.setNombreEps("SUSALUD");
            daoEps.guardar(eps);
            
            eps = new Eps();
            eps.setNombreEps("CAFESALUD");
            daoEps.guardar(eps);
            
            eps = new Eps();
            eps.setNombreEps("COMFENALCO");
            daoEps.guardar(eps);
            
            eps = new Eps();
            eps.setNombreEps("CAPRECOM");
            daoEps.guardar(eps);
            
            eps = new Eps();
            eps.setNombreEps("SALUD TOTAL");
            daoEps.guardar(eps);
            
            eps = new Eps();
            eps.setNombreEps("COOMEVA");
            daoEps.guardar(eps);

            
            NivelEducativo nivelEducativo = new NivelEducativo();
            nivelEducativo.setNombreNivelEducativo("PRIMARIA");
            daoNivelEducativo.guardar(nivelEducativo);
            
            nivelEducativo = new NivelEducativo();
            nivelEducativo.setNombreNivelEducativo("BACHILLER");
            daoNivelEducativo.guardar(nivelEducativo);
            
            nivelEducativo = new NivelEducativo();
            nivelEducativo.setNombreNivelEducativo("UNIVERSITARIO");
            daoNivelEducativo.guardar(nivelEducativo);
            
            nivelEducativo = new NivelEducativo();
            nivelEducativo.setNombreNivelEducativo("TÉCNICO");
            daoNivelEducativo.guardar(nivelEducativo);
            
            nivelEducativo = new NivelEducativo();
            nivelEducativo.setNombreNivelEducativo("TECNÓLOGO");
            daoNivelEducativo.guardar(nivelEducativo);
            
            nivelEducativo = new NivelEducativo();
            nivelEducativo.setNombreNivelEducativo("PROFESIONAL");
            daoNivelEducativo.guardar(nivelEducativo);
            
            InstEducativa instEducativa = new InstEducativa();
            instEducativa.setNombreInstEducativa("POLITÉCNICO COLOMBIANO JIC");
            daoInstEducativa.guardar(instEducativa);
            
            Posiciones posiciones = new Posiciones();
            posiciones.setNombrePosicion("DELANTERO");
            daoPosiciones.guardar(posiciones);
            
            posiciones = new Posiciones();
            posiciones.setNombrePosicion("VOLANTE");
            daoPosiciones.guardar(posiciones);
            
            posiciones = new Posiciones();
            posiciones.setNombrePosicion("DEFENSA");
            daoPosiciones.guardar(posiciones);
            
            posiciones = new Posiciones();
            posiciones.setNombrePosicion("PORTERO");
            daoPosiciones.guardar(posiciones);
            
            Categoria categoria = new Categoria();
            categoria.setNombrecategoria("PRIMERA C");
            daoCategoria.guardar(categoria);
            
            categoria = new Categoria();
            categoria.setNombrecategoria("PRIMERA B");
            daoCategoria.guardar(categoria);
            
            categoria = new Categoria();
            categoria.setNombrecategoria("PRIMERA A");
            daoCategoria.guardar(categoria);
            
            categoria = new Categoria();
            categoria.setNombrecategoria("UNIVERSITARIO");
            daoCategoria.guardar(categoria);
            
            categoria = new Categoria();
            categoria.setNombrecategoria("PONY");
            daoCategoria.guardar(categoria);
            
            categoria = new Categoria();
            categoria.setNombrecategoria("PRE PONY");
            daoCategoria.guardar(categoria);
            
            Cargos cargos = new Cargos();
            cargos.setNombrecargo("DIRECTOR TÉCNICO");
            daoCargos.guardar(cargos);
            
            cargos = new Cargos();
            cargos.setNombrecargo("PREPARADOR FÍSICO");
            daoCargos.guardar(cargos);
            
            cargos = new Cargos();
            cargos.setNombrecargo("FISIOTERAPEUTA");
            daoCargos.guardar(cargos);
            
            TipoDeportista tipoDeportista = new TipoDeportista();
            tipoDeportista.setNombreTipoDeportista("ALTO RENDIMIENTO");
            daoTipoDeportista.guardar(tipoDeportista);
            
            tipoDeportista = new TipoDeportista();
            tipoDeportista.setNombreTipoDeportista("RENDIMIENTO");
            daoTipoDeportista.guardar(tipoDeportista);
            
            tipoDeportista = new TipoDeportista();
            tipoDeportista.setNombreTipoDeportista("LIGA");
            daoTipoDeportista.guardar(tipoDeportista);
            
            tipoDeportista = new TipoDeportista();
            tipoDeportista.setNombreTipoDeportista("UNIVERSITARIO");
            daoTipoDeportista.guardar(tipoDeportista);
            
            //Se ingresa un usuario admin de prueba
            Usuarios usuarioAdmin = new Usuarios();
            usuarioAdmin.setDocumento("sisegfut");
            usuarioAdmin.setClave(Cifrado.getStringMessageDigest("q1w2e3r4", Cifrado.SHA256));
            usuarioAdmin.setCorreoElectronico("sisegfut@gmail.com");
            usuarioAdmin.setNombres("Sisegfut");
            usuarioAdmin.setApellidos("Admin");
            //Rol administrador
            usuarioAdmin.setRol(admin);
            daoUsuario.guardar(usuarioAdmin);


            log.info("Se inicializó las tablas maestras del sistema.");
            System.out.println("Se inicializó las tablas maestras del sistema");
        } catch (Exception e) {
            System.out.println("No se inicializó el usuario del sistema."+e.getMessage());
            log.error("Error " + e.getMessage(), e);

        }
    }
    
}