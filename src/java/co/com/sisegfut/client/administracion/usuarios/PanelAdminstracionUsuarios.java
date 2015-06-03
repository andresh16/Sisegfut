/**
 * PanelAdminstracionUsuarios 
 *
 * 
 * 
 * 
 *
 */
package co.com.sisegfut.client.administracion.usuarios;

import co.com.sisegfut.client.adminCRUDgral.FormularioCRUD;
import co.com.sisegfut.client.adminCRUDgral.PaginGridEntidades;
import co.com.sisegfut.client.adminCRUDgral.PanelAdministracionEntidades;
import co.com.sisegfut.client.datos.dominio.Usuarios;
import co.com.sisegfut.client.util.ListenerGuardado;
import co.com.sisegfut.client.util.Resources;
import co.com.sisegfut.client.util.rpc.RPCAdminUsuarios;
import co.com.sisegfut.client.util.rpc.RPCAdminUsuariosAsync;
import co.com.sisegfut.client.util.rpc.RPCMaestroAsync;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.toolbar.SeparatorToolItem;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.ServiceDefTarget;

/**
 *
 * @
 */
public class PanelAdminstracionUsuarios extends PanelAdministracionEntidades<Usuarios> {

    public PanelAdminstracionUsuarios(String titulo, String ayuda) {
        super(titulo, ayuda);
    }

    @Override
    protected void onRender(Element parent, int index) {
        super.onRender(parent, index);
        Button btnClave = new Button("Crear clave", Resources.ICONS.iconoClave(), listenerVentanaCambiarClave());

        toolBar.add(new SeparatorToolItem());
        toolBar.add(btnClave);
    }

    protected PaginGridEntidades crearPaginGridEntidades() {
        paginGridEntidades = new PaginGridUsuarios(this, getServicioRPC(), "Lista de usuarios");
        return paginGridEntidades;
    }

    protected FormularioCRUD crearFormularioCRUD() {
        formularioCRUD = new FormularioUsuarios(this);
        return formularioCRUD;
    }
    
    @Override
    protected String getTituloCrear(){
        return "Crear usuario";
    }
    
    @Override
    protected String getAyudaCrear(){
        return myConstants.ayudaCrearUsuario();
    }
    
    @Override
    protected String getTituloModificar(){
        return "Modificar usuario";
    }
    
    @Override
    protected String getAyudaModificar(){
        return myConstants.ayudaModificarUsuario();
    }

    @Override
    protected void abrirRecuperar() {
        VentanaRecuperarUsuario ventana = new VentanaRecuperarUsuario(this, "Recuperar usuario");
        ventana.show();
    }

    @Override
    protected RPCMaestroAsync<Usuarios> getServicioRPC() {
        final RPCAdminUsuariosAsync svc = (RPCAdminUsuariosAsync) GWT.create(RPCAdminUsuarios.class);
        ServiceDefTarget endpoint = (ServiceDefTarget) svc;
        endpoint.setServiceEntryPoint("services/RPCAdminUsuarios");

        return svc;
    }

    /**
     * Listener para la abrir la ventana de creacion
     *
     * @return
     */
    private SelectionListener<ButtonEvent> listenerVentanaCambiarClave() {
        final ListenerGuardado listenerGuardado = this;
        return new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                if (beanEntidad != null) {
                    VentanaCrearClave ventana = new VentanaCrearClave(VentanaCrearClave.CREAR);
                    ventana.setEntidad((Usuarios) beanEntidad.getBean());
                    ventana.show();
                } else {
                    MessageBox.alert("Alerta", "Debe seleccionar primero un usuario", null);
                }
            }
        };
    }

    @Override
    public void onFind(Usuarios entidad) {
        //NN
    }
}
