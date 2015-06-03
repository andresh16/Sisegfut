/**
* VentanaRecuperarUsuario
* 
* 
*
*
* 
**/

package co.com.sisegfut.client.administracion.usuarios;

import co.com.sisegfut.client.adminCRUDgral.VentanaRecuperar;
import co.com.sisegfut.client.datos.dominio.EntidadPerpetua;
import co.com.sisegfut.client.datos.dominio.Usuarios;
import co.com.sisegfut.client.util.ListenerGuardado;
import co.com.sisegfut.client.util.combox.ComboBoxEntidades;
import co.com.sisegfut.client.util.combox.ComboBoxUsuarios;
import com.extjs.gxt.ui.client.widget.tips.ToolTipConfig;

/**
 *
 * @
 */
public class VentanaRecuperarUsuario extends VentanaRecuperar<Usuarios>{

    /**
     * Constructor.
     * @param listenerGuardado
     * @param titulo 
     */
    public VentanaRecuperarUsuario(ListenerGuardado listenerGuardado, String titulo) {
        super(listenerGuardado, titulo);
    }
    
    /**
     * 
     * @return 
     */
    @Override
    protected ComboBoxEntidades<Usuarios> crearComboInactivos(){
        cmbEntidad = new ComboBoxUsuarios(EntidadPerpetua.TIPO_DELETED); 
        cmbEntidad.setToolTip(new ToolTipConfig("Usuario", "Seleccione el usuario eliminado que desea recuperar"));
        cmbEntidad.setFieldLabel("Usuario");
        cmbEntidad.setAllowBlank(false); 
        
        return cmbEntidad;
    }
    /**
     * 
     * @return 
     */
    @Override
    protected String getAyuda() {
        return myConstants.ayudaReactivarUsuario();
    }
}
