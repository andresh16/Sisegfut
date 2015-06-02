/**
* VentanaRecuperarUsuario
* Versión 1.0
* 15/09/2013
*
* Copyright(c) 2007-2012, Boos IT.
* admin@boos.com.co
*
* http://boos.com.co/license
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
 * @author josorio
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
    
    
    @Override
    protected ComboBoxEntidades<Usuarios> crearComboInactivos(){
        cmbEntidad = new ComboBoxUsuarios(EntidadPerpetua.TIPO_DELETED); 
        cmbEntidad.setToolTip(new ToolTipConfig("Usuario", "Seleccione el usuario eliminado que desea recuperar"));
        cmbEntidad.setFieldLabel("Usuario");
        cmbEntidad.setAllowBlank(false); 
        
        return cmbEntidad;
    }

    @Override
    protected String getAyuda() {
        return myConstants.ayudaReactivarUsuario();
    }
}
