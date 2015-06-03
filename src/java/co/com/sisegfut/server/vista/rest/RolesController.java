package co.com.sisegfut.server.vista.rest;

import co.com.sisegfut.client.datos.dominio.Rol;
import co.com.sisegfut.client.datos.dominio.Usuarios;
import co.com.sisegfut.server.datos.dao.DaoRol;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author 
 */
@Controller
public class RolesController
{
    @Autowired
    private DaoRol daoRol;
    @Autowired
    private Usuarios usuarioSession;


    @RequestMapping( value = "/roles" )
    public ModelAndView obtenerRoles()
    {
        ModelAndView retorno = new ModelAndView();

        List<Rol> listaEntidades = daoRol.getActivos();
        if( listaEntidades == null )
        {
            listaEntidades = new ArrayList<Rol>();
        }
        else
        {
            if(!usuarioSession.isAdministradorSistema()){
                listaEntidades.remove(0);
            }
        }
        retorno.addObject( "cantidad", listaEntidades.size() );
        retorno.addObject( "datos", listaEntidades );

        return retorno;
    }
}
