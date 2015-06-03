package co.com.sisegfut.server.vista.rest;

import co.com.sisegfut.client.datos.dominio.Usuarios;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author 
 */
@Controller
public class LogoutController
{
    @Autowired
    private Usuarios usuarioSession;

    /**
     * Crea la url de logout y resetea los objetos de session
     * @return
     */
    @RequestMapping(value = "logout.html", method = RequestMethod.GET)
    public String logout(HttpServletRequest request)
    {
        usuarioSession.resetear();
        
        HttpSession session = request.getSession();
        //invalidamos la sesion http.
        session.invalidate();
        
        // Direcciono a la url en sessión
        String url = "redirect:/";
        return url;
    }
}
