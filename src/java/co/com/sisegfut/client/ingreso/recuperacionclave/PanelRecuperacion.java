
package co.com.sisegfut.client.ingreso.recuperacionclave;

import co.com.sisegfut.client.util.PanelInformativo;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.google.gwt.user.client.Element;

/**
 *
 * @author anfeh_000
 */
public class PanelRecuperacion extends LayoutContainer{
    
    private PanelInformativo pnlInformativo;
    
    @Override
    protected void onRender(Element parent, int index) {
        super.onRender(parent, index);
        
        
        pnlInformativo = new PanelInformativo("<h3>Recuperar contraseña</h3>"+
            "Esta funcionalidad le permite definir una nueva contraseña si olvidó la que tenía, por favor primero solicite la llave para el cambio de contraseña, a su correo y luego con esta llave defina su nueva contraseña en el cuadro que está mas abajo",null);
        
        
//        add(pnlInformativo); 
        
        FormularioSolicitud formularioSolicitud = new FormularioSolicitud();        
        add(formularioSolicitud); 
        
        FormularioCambioClave formularioCambioClave = new FormularioCambioClave();
        add(formularioCambioClave);
    }
}
