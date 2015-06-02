

package co.com.sisegfut.client.adminCRUDgral;

import co.com.sisegfut.client.administracion.usuarios.VentanaCrearClave;
import co.com.sisegfut.client.datos.dominio.Usuarios;
import co.com.sisegfut.client.entidades.RespuestaRPC;
import co.com.sisegfut.client.util.ListenerGuardado;
import co.com.sisegfut.client.util.PanelErrores;
import co.com.sisegfut.client.util.PanelExito;
import co.com.sisegfut.client.util.rpc.RPCMaestroAsync;
import com.extjs.gxt.ui.client.binding.FormBinding;
import com.extjs.gxt.ui.client.data.BeanModel;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.google.gwt.user.client.rpc.AsyncCallback;
import java.io.Serializable;

/**
 *
 * @author josorio
 */
public abstract class FormularioCRUD<T extends Serializable> extends FormPanel{

    /**
     * Layout data from layaout form
     */
    protected FormData formData;
    protected PanelErrores pnlErrores;
    protected PanelExito pnlExito;
    
    /**
     * ventana modal para el mensaje de espera
     */
    protected MessageBox boxWait;
    //Para relacionar la entidad con el formulario.
    protected FormBinding formBindings;
    /**
     * Entidad a trabajar
     */
    protected T entidad;
    protected ListenerGuardado listenerGuardado;
    private RPCMaestroAsync<T> rPCMaestroAsync;
    VentanaCrearClave ventanaCrearClave;
    private Usuarios usuarioLogeado;
    
    
    /**
     * Constructor
     * @param listenerGuardado 
     */
    public FormularioCRUD(ListenerGuardado listenerGuardado) {
        setHeaderVisible(false);
        this.listenerGuardado = listenerGuardado;
        this.rPCMaestroAsync = listenerGuardado.getRPCMaestroAsync();

        formData = new FormData("-20");

        setFrame(true);
       
        pnlErrores = new PanelErrores();
        pnlExito = new PanelExito();

        add(pnlErrores);
        add(pnlExito);
    }

    protected void setListenerGuardado(ListenerGuardado listenerGuardado) {
        this.listenerGuardado = listenerGuardado;
    }
     /**
     * Setea la entidad a trajabar
     * @param usuarioTrabajo 
     */
    public void setEntidad(Usuarios usuarioTrabajo) {
        this.usuarioLogeado=usuarioTrabajo;
    }
    
    /**
     * 
     * @param entidad 
     */    
    protected abstract void entidadAFormulario(T entidad);
    
    protected abstract T formularioAEntidad();
    
    protected abstract void setEntidadManual(T entidad);
    
    /**
     * Enlaza el formulario con el bean de un usuario
     * @param entidad
     */
    protected void setBind(BeanModel entidad) {
        unBind();
        reset();
        //deberia enlazar el formulario con la entidad ojo!.
        formBindings.bind((ModelData) entidad);
        //Saco la entidad del bean.
        this.entidad = (T) entidad.getBean();
        
    }
    
    /**
     * Desenlaza el formulario de cualquier bean
     */
    protected void unBind() {
        formBindings.unbind();
    }
    
    /**
     * Escucha el boton limpiar
     *
     * @return
     */   
    protected SelectionListener<ButtonEvent> listenerLimpiar() {
        return new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                unBind();
                reset();
                entidad=null;
                listenerGuardado.cancelarGuardado();
            }
        };
    }
    
    protected SelectionListener<ButtonEvent> listenerCambiar() {
        return new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
              ventanaCrearClave = new VentanaCrearClave(VentanaCrearClave.CAMBIAR);
              ventanaCrearClave.setEntidad(usuarioLogeado);
              ventanaCrearClave.show();
                
            }
        };
    }
    
    /**
     * Escucha el boton guardar
     *
     * @return
     */   
    protected SelectionListener<ButtonEvent> listenerGuardar() {
//        final RPCAdminUsuariosAsync svc = (RPCAdminUsuariosAsync) GWT.create(RPCAdminUsuarios.class);
//        ServiceDefTarget endpoint = (ServiceDefTarget) svc;
//        endpoint.setServiceEntryPoint("services/RPCAdminUsuarios");
        
        return new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                pnlErrores.setVisible(false);
                pnlExito.setVisible(false);

                // Si tenemos un objeto de verificación lo usamos
                if (listenerGuardado != null && !listenerGuardado.permisoGuardado()) {
                    return;
                }

                // Se chequea el formulario por errores
                // Si está bien
                if (isValid()) {
                    boxWait = MessageBox.wait("Progreso",
                            "Procesando los datos, por favor espere...", "Procesando...");
                    rPCMaestroAsync.guardarEntidad(formularioAEntidad(),
                            crearCallBackGuardarCambios());
                    pnlExito.definirTexto("Se guardó con exito.");
                } else // Si hay errores
                {
                    pnlErrores.limpiar();
                    pnlErrores.setVisible(true);
                    pnlErrores.aniadir("Debe llenar los campos obligatorios faltantes");
                }
            }
        };
    }
    
    
    
    /**
     * Crea el objeto de notificación de llegada de respuesta del servidor
     *
     * @return Objeto creado
     */
    protected AsyncCallback<RespuestaRPC<T>> crearCallBackGuardarCambios() {

        return new AsyncCallback<RespuestaRPC<T>>() {

            @Override
            public void onFailure(Throwable arg0) {
                boxWait.close();

                pnlExito.setVisible(false);
                pnlErrores.limpiar();
                pnlErrores.aniadir("Se presentó un error de comunicación, "
                        + "por favor inténtelo de nuevo");
                pnlErrores.aniadir(arg0.toString());
                pnlErrores.setVisible(true);
            }

            @Override
            public void onSuccess(RespuestaRPC<T> respuesta) {
                boxWait.close();

                if (respuesta.getResultado() == RespuestaRPC.RESULTADO_OK) {
                    entidad = respuesta.getObjetoRespuesta();
                    //ya no reseteare el formulario despues de guardar
//                    getForm().reset();

                    pnlExito.definirTexto("El registro se guardó "
                            + "exitósamente");
                    pnlExito.setVisible(true);
                    // Si hay listener lo ejecuto
                    if (listenerGuardado != null) {
                        listenerGuardado.finalizaGuardado();
                    }
                } else {
                    pnlErrores.limpiar();
                    pnlErrores.setVisible(true);
                    pnlErrores.aniadir(respuesta.getDescripcionError());

                }
            }
        };
    }
    
    ////
}
