

package co.com.sisegfut.client.adminCRUDgral;

import co.com.sisegfut.client.aaI18N.Main;
import co.com.sisegfut.client.datos.dominio.EntidadBase;
import co.com.sisegfut.client.datos.dominio.Usuarios;
import co.com.sisegfut.client.entidades.RespuestaRPC;
import co.com.sisegfut.client.util.ListenerBuscarEntidad;
import co.com.sisegfut.client.util.ListenerGuardado;
import co.com.sisegfut.client.util.Resources;
import co.com.sisegfut.client.util.rpc.RPCMaestroAsync;
import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.data.BeanModel;
import com.extjs.gxt.ui.client.event.*;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.ToolButton;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FlowData;
import com.extjs.gxt.ui.client.widget.toolbar.SeparatorToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;
import java.io.Serializable;

/**
 *
 * @author fhurtado
 */
public abstract class PanelAdministracionEntidades<T extends Serializable> 
                        extends ContentPanel implements ListenerGuardado<T>, ListenerBuscarEntidad<T> {

    protected PaginGridEntidades paginGridEntidades;
    protected LayoutContainer layoutContainerMain;
    protected BeanModel beanEntidad;
    /**
     * ventana modal para el mensaje de espera
     */
    protected MessageBox boxWait;
    /** Contiene los textos a presentar en la interfaz web segun el idioma*/
    protected Main myConstants;   
    
    protected String titulo;
    
    protected String ayuda;
    
    protected ToolBar toolBar;
    
    protected FormularioCRUD formularioCRUD;
    
    /**
     * Constructor
     * @param rPCMaestroAsync
     * @param titulo
     * @param ayuda
     * @param paginGridEntidades
     * @param formularioCRUD 
     */
    public PanelAdministracionEntidades(String titulo, String ayuda) {
        this.titulo = titulo;
        this.ayuda = ayuda;
    }
    
    @Override
    protected void onRender(Element parent, int index) {
        super.onRender(parent, index);
        setScrollMode(Style.Scroll.AUTOY);
        if(myConstants==null){
            myConstants = (Main) GWT.create(Main.class);
        }
        setHeading(titulo);
        
        setLayout(new FitLayout());

        layoutContainerMain = new LayoutContainer();
        layoutContainerMain.setSize("100%", "100%"); 

        toolBar = new ToolBar();

        Button btnCrear = new Button("Crear", Resources.ICONS.iconoCrear(), listenerVentanaCrear());
        Button btnModificar = new Button("Modificar", Resources.ICONS.iconoModificar(), listenerVentanaModificar());
        Button btnEliminar = new Button("Eliminar", Resources.ICONS.iconoEliminar(),listenerVentanaEliminar());
        //
        Button btnReactivar = new Button("Reactivar", Resources.ICONS.iconoCrearCuenta(),listenerVentanaReactivar());


        toolBar.add(btnCrear);
        toolBar.add(new SeparatorToolItem());
        toolBar.add(btnModificar);
        toolBar.add(new SeparatorToolItem());
        toolBar.add(btnEliminar);        
        toolBar.add(new SeparatorToolItem());
        toolBar.add(btnReactivar);
//        


//        layoutContainerMain.add(pnlInformativo, new FlowData(0));
        layoutContainerMain.add(toolBar, new FlowData(0));
        layoutContainerMain.add(crearPaginGridEntidades(), new FlowData(0));

        add(layoutContainerMain, new FlowData(0));
        
        //Agrego un listener para escuchar el cambio de tamanio del panel
        this.addListener(Events.Resize, new Listener<BoxComponentEvent>() {
            public void handleEvent(final BoxComponentEvent event) {
                paginGridEntidades.setHeight(event.getHeight()-55); 
            }
        });
        
        //Agrego boton al panel principal que permite desplegar la ayuda.
        getHeader().addTool(new ToolButton("x-tool-help", new SelectionListener<IconButtonEvent>() {

            @Override
            public void componentSelected(IconButtonEvent ce) {
                abrirVentana(ayuda);
            }
        }));
        
     //   paginGridEntidades.cargar();
    }
    
    /**
     * Abre ventana de ayuda. 
     */
    private void abrirVentana(String texto) {
        final Dialog simple = new Dialog();  
        simple.setHeading("Ayuda");  
        simple.setButtons(Dialog.OK);  
        simple.setBodyStyleName("pad-text");  
        simple.addText(texto);  
        simple.getItem(0).getFocusSupport().setIgnore(true);  
        simple.setScrollMode(Style.Scroll.AUTO);  
        simple.setHideOnButtonClick(true); 
        simple.setWidth(550); 
        //simple.setSize(550, 255);
        
        simple.show();                 
    }
    
    protected abstract PaginGridEntidades crearPaginGridEntidades();
    
    protected abstract FormularioCRUD crearFormularioCRUD();
    
    protected abstract String getTituloCrear();
    
    protected abstract String getAyudaCrear();
    
    protected abstract String getTituloModificar();
    
    protected abstract String getAyudaModificar();
    
    protected abstract void abrirRecuperar();
    
    protected abstract RPCMaestroAsync<T> getServicioRPC();

    /**
     * Listener para la abrir la ventana de creacion
     *
     * @return
     */
    private SelectionListener<ButtonEvent> listenerVentanaCrear() {   
        final ListenerGuardado listenerGuardado = this;
        return new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                VentanaCrear ventanaCrear = new VentanaCrear(listenerGuardado, crearFormularioCRUD(),
                        getTituloCrear(), getAyudaCrear());
                ventanaCrear.show();
            }
        };
    }

    /**
     * Listener para la abrir la ventana de creacion
     *
     * @return
     */
    private SelectionListener<ButtonEvent> listenerVentanaModificar() {
        final ListenerGuardado listenerGuardado = this;
        return new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                
                if (beanEntidad != null) {
                    VentanaModificar ventanaModificar = new VentanaModificar(listenerGuardado, crearFormularioCRUD(),
                            getTituloModificar(), getAyudaModificar()); 
                    ventanaModificar.setearEntidad(beanEntidad);
                    ventanaModificar.show();
                } else {
                    MessageBox.alert("Alerta", "Debe seleccionar primero un registro de la tabla", null);
                }
            }
        };
    }    
    
    
    /**
     * Listener para la abrir la ventana de reactivacion de usuarios eliminados logicamente
     *
     * @return
     */
    private SelectionListener<ButtonEvent> listenerVentanaReactivar() {
        final ListenerGuardado listenerGuardado = this;
        return new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                abrirRecuperar();
            }
        };
    }

    /**
     * Listener para la abrir la ventana de creacion
     *
     * @return
     */
    private SelectionListener<ButtonEvent> listenerVentanaEliminar() {
        final ListenerGuardado listenerGuardado = this;        
        
        
        return new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                if (beanEntidad != null) {
                    final Listener<MessageBoxEvent> l = new Listener<MessageBoxEvent>() {

                        public void handleEvent(MessageBoxEvent ce) {
                            Button btn = ce.getButtonClicked();
                            if(btn.getText().equalsIgnoreCase("yes") || btn.getText().equalsIgnoreCase("si") || btn.getText().equalsIgnoreCase("Sí")){
                                boxWait = MessageBox.wait("Progreso",
                                    "Procesando los datos, por favor espere...", "Procesando...");
                                //elimine
                                getServicioRPC().eliminarEntidad(
                                        (T)beanEntidad.getBean(), crearCallBackEliminado() );
                            }
                        }
                    };
                    MessageBox box = new MessageBox();
                    box.setButtons(MessageBox.YESNO);
                    box.setIcon(MessageBox.QUESTION);
                    box.setTitle("Eliminar registro?");
                    box.addCallback(l);
                    box.setMessage("Realmente desea eliminar el registro "+((EntidadBase)beanEntidad.getBean()).getLabel()+" ?");
                    box.show();
                } else {
                    MessageBox.alert("Alerta", "Debe seleccionar primero un usuario", null);
                }
            }
        };
    }
    
    @Override
    public RPCMaestroAsync<T> getRPCMaestroAsync(){
        return getServicioRPC();
    }
    
    

    public boolean permisoGuardado() {
        return true;
    }

    public void finalizaGuardado() {
        this.beanEntidad = null;
        MessageBox.alert("Información", "Se guardó los cambios correctamente", null);
        paginGridEntidades.cargar();
    }
    
    @Override
    public void cancelarGuardado() {
        this.beanEntidad = null;
        paginGridEntidades.cargar();
    }

    @Override
    public void onFind(BeanModel beanEntidad) {
        this.beanEntidad = beanEntidad;
    }

    @Override
    public void onFind(Long idEntidad) {
    }

    @Override
    public void onCancelFind() {
    }
    
    //------------------------------------------------------------------------------------


    /**
     * Asincollback de eliminado de la entidad
     * @return 
     */
    public AsyncCallback<RespuestaRPC<T>> crearCallBackEliminado()
    {
        return new AsyncCallback<RespuestaRPC<T>>()
        {
            /**
             * Método a ejecutar en caso de error en el llamado
             * @param arg0
             */
            public void onFailure(Throwable arg0) {
                boxWait.close();
                MessageBox.info("Información", "Se presentó un error de comunicación, por favor "
                        + "inténtelo de nuevo o reportelo con el area de soporte", null);
            }

            /**
             * En caso de éxito en el llamado
             * @param respuesta RespuestaRPC retornada por el método remoto
             */
            public void onSuccess(RespuestaRPC<T> respuesta)
            {
                boxWait.close();

                if (respuesta.getResultado() == RespuestaRPC.RESULTADO_OK)
                {
//                    MessageBox.info("Información", "Entidad eliminada correctamente", null);                    
                    finalizaGuardado();
                } 
                else
                {
                    MessageBox.info("Error", respuesta.getDescripcionError(), null);
                }
            }
        };
    }

    
}

