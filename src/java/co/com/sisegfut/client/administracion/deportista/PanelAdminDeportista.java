/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.client.administracion.deportista;

import static co.com.sisegfut.client.administracion.deportista.PanelInfoGeneral.ACTIVOS;
import co.com.sisegfut.client.datos.dominio.Deportista;
import co.com.sisegfut.client.datos.dominio.Usuarios;
import co.com.sisegfut.client.entidades.RespuestaRPC;
import co.com.sisegfut.client.util.BeansLocales;
import co.com.sisegfut.client.util.Resources;
import co.com.sisegfut.client.util.combox.ComboBoxCategoria;
import co.com.sisegfut.client.util.rpc.RPCAdminDeportista;
import co.com.sisegfut.client.util.rpc.RPCAdminDeportistaAsync;
import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.Style.LayoutRegion;
import com.extjs.gxt.ui.client.Style.Orientation;
import com.extjs.gxt.ui.client.Style.SelectionMode;
import com.extjs.gxt.ui.client.binding.FormBinding;
import com.extjs.gxt.ui.client.data.BaseFilterPagingLoadConfig;
import com.extjs.gxt.ui.client.data.BasePagingLoadConfig;
import com.extjs.gxt.ui.client.data.BasePagingLoader;
import com.extjs.gxt.ui.client.data.BeanModel;
import com.extjs.gxt.ui.client.data.BeanModelReader;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.extjs.gxt.ui.client.data.PagingLoader;
import com.extjs.gxt.ui.client.data.RpcProxy;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.BoxComponentEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.GridEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.filters.GridFilters;
import com.extjs.gxt.ui.client.widget.grid.filters.ListFilter;
import com.extjs.gxt.ui.client.widget.grid.filters.StringFilter;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.ColumnData;
import com.extjs.gxt.ui.client.widget.layout.ColumnLayout;
import com.extjs.gxt.ui.client.widget.layout.FillLayout;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.extjs.gxt.ui.client.widget.layout.RowData;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.extjs.gxt.ui.client.widget.tips.ToolTipConfig;
import com.extjs.gxt.ui.client.widget.toolbar.PagingToolBar;
import com.extjs.gxt.ui.client.widget.toolbar.SeparatorToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author fhurtado
 */
public class PanelAdminDeportista extends LayoutContainer {

    private FormBinding formBindings;
    private PagingLoader<PagingLoadResult<ModelData>> loader;
    

    Long id = null;
    Long IdCategoriaElegida=null;
    protected MessageBox boxWait;
    private Usuarios usuarioLogeado;
    TextField<String> txtCampoBusqueda = new TextField<String>();
    //Creo el toolbar de paginacion de el grid
    final PagingToolBar PgtoolBar = new PagingToolBar(50);
    private Deportista dep;
    BorderLayoutData dataWest;
    BorderLayoutData dataCenter;
    ContentPanel panel2;
    private PanelAdminPestDeportista panelInfoDeportista;
    private PanelReactivarDeportista reactivarDeportista;
    ComboBoxCategoria cbxCategoria;

    @Override
    protected void onRender(Element parent, int index) {
        super.onRender(parent, index);

        BorderLayout layout = new BorderLayout();
        setLayout(layout);
       setStyleAttribute("padding", "0px");
        cbxCategoria = new ComboBoxCategoria(ComboBoxCategoria.ACTIVOS);

//        setScrollMode(Style.Scroll.AUTOY);
        final ContentPanel cp = new ContentPanel();
        final ContentPanel cp2 = new ContentPanel();
        cp.setScrollMode(Style.Scroll.AUTO);
         cp.setLayout(new RowLayout(Orientation.VERTICAL));
        cp.setSize(250, 250);
//        cp.setLayout(new FillLayout(Orientation.VERTICAL));

//        setLayout(new FillLayout(Orientation.VERTICAL));
        ToolBar toolBar = new ToolBar();
        toolBar.setSpacing(2);

          FormData formData = new FormData("0");
        LayoutContainer main = new LayoutContainer();
        main.setLayout(new ColumnLayout());
        // main.setHeight(100);
        
        main.setAutoHeight(afterRender);

        ///////////////////// Columna 1 ////////////////////////////  
        LayoutContainer Columna1 = new LayoutContainer();
        Columna1.setStyleAttribute("paddingRight", "0px");
        FormLayout layoutfrm = new FormLayout();
        Columna1.setWidth(200);
//        layoutfrm.setLabelAlign(FormPanel.LabelAlign.LEFT);
        Columna1.setLayout(layoutfrm);
        
        txtCampoBusqueda.setName("filtrar");
//        txtCampoBusqueda.setFieldLabel("<b>Filtrar<b>");
        txtCampoBusqueda.setEmptyText("Filtrar");
        txtCampoBusqueda.setHideLabel(true);
        Columna1.add(txtCampoBusqueda, formData);
        
        Button btnBuscar = new Button("Filtrar", listenerBuscar());
        btnBuscar.setIcon(Resources.ICONS.iconoBuscar());
        
        cbxCategoria = new ComboBoxCategoria(ACTIVOS);
        cbxCategoria.setName("categoria.nombrecategoria");
        cbxCategoria.setToolTip(new ToolTipConfig("Categoría", "Filtrar por categoría"));
        cbxCategoria.setFieldLabel("Filtrar Categoría");
        cbxCategoria.setAllowBlank(false);
        
        Columna1.add(cbxCategoria, formData);
        
         ///////////////////// Columna 2 //////////////////////////// 
        LayoutContainer Columna2 = new LayoutContainer();
        Columna2.setStyleAttribute("paddingLeft", "0px");
        layoutfrm = new FormLayout();
//        layoutfrm.setLabelAlign(FormPanel.LabelAlign.LEFT);
        
        
        Columna2.add(btnBuscar, formData);
//        Columna2.add(cbxCategoria, formData);
        
        main.add(Columna1, new ColumnData(.5));
        main.add(Columna2, new ColumnData(.5));
//        toolBar.add(cbxCategoria);
//        toolBar.add(new SeparatorToolItem());
        cbxCategoria.addListener(Events.SelectionChange, new Listener<BaseEvent>() {
            @Override
            public void handleEvent(BaseEvent be) {
                IdCategoriaElegida=cbxCategoria.getCategoriaElegida().getId();
                cargar();
            }
        });
        cp.add(main,new RowData(1,0.12, new Margins(0)));
        
        
        toolBar.add(new SeparatorToolItem());
        
        Button btnEliminar = new Button(" Eliminar", listenerEliminar());
        btnEliminar.setIcon(Resources.ICONS.iconoEliminar());
        toolBar.add(btnEliminar);
        toolBar.add(new SeparatorToolItem());
        Button btnReactivar = new Button("Reactivar", listenerReactivar());
        btnReactivar.setIcon(Resources.ICONS.iconoRefrescar());
        toolBar.add(btnReactivar);
        toolBar.add(new SeparatorToolItem());
        Button btnFoto = new Button("Cargar foto", listenerCargarFoto());
        btnFoto.setIcon(Resources.ICONS.iconoSubirFoto());
        toolBar.add(btnFoto);
        toolBar.add(new SeparatorToolItem());

        Button btnReporte = new Button("Generar PDF", ListenerGenerarReporte());
        btnReporte.setIcon(Resources.ICONS.iconoPDF());
        toolBar.add(btnReporte);

        panelInfoDeportista = new PanelAdminPestDeportista(PanelAdminDeportista.this);

        // panel.setButtonAlign(Style.HorizontalAlignment.CENTER);
        //Monitors the valid state of a form and enabled / disabled all buttons.
//        FormButtonBinding binding = new FormButtonBinding(panel);
//        binding.addButton(btnReactivar);
//        add(toolBar, new FlowData(0));
        usuarioLogeado = BeansLocales.getUsuario();

        final RPCAdminDeportistaAsync svc = (RPCAdminDeportistaAsync) GWT.create(RPCAdminDeportista.class);
        ServiceDefTarget endpoint = (ServiceDefTarget) svc;
        endpoint.setServiceEntryPoint("services/RPCAdminDeportista");

        //Valido que el servicio RPC este activo.
        if (svc == null) {
            MessageBox box = new MessageBox();
            box.setButtons(MessageBox.OK);
            box.setIcon(MessageBox.INFO);
            box.setTitle("Deportistas");
            box.setMessage("No se ha detectado ningun servicio RPC");
            box.show();
            return;
        }
        //Llamo el servicio RPC que se usara como proxy para cargar los datos de la entidad indicada
        RpcProxy<PagingLoadResult<Deportista>> proxy = new RpcProxy<PagingLoadResult<Deportista>>() {
            @Override
            protected void load(Object loadConfig, AsyncCallback<PagingLoadResult<Deportista>> callback) {
                if (IdCategoriaElegida!=null){
                 svc.getDeportistaxCategoria(IdCategoriaElegida, callback);
                }else{
                    svc.getDeportistas(callback);
                }
            }
           
        };

        loader = new BasePagingLoader<PagingLoadResult<ModelData>>(proxy, new BeanModelReader()) {
            @Override
            protected Object newLoadConfig() {
                BasePagingLoadConfig config = new BaseFilterPagingLoadConfig();
                return config;
            }
        };
        loader.setRemoteSort(false);

        ListStore<BeanModel> store = new ListStore<BeanModel>(loader);
        store.setMonitorChanges(true);

        PgtoolBar.bind(loader);
        PgtoolBar.enable();
        //Configuro las columnas de la tabla
        List<ColumnConfig> columns = new ArrayList<ColumnConfig>();

//        ColumnConfig documento = new ColumnConfig("documento", "Documento", 50);
//        documento.setDateTimeFormat(DateTimeFormat.getFormat("MM/dd/yyyy"));
//        columns.add(documento);
        columns.add(new ColumnConfig("documento", "Documento ", 20));
        columns.add(new ColumnConfig("label", "Nombre completo ", 100));

        columns.add(new ColumnConfig("posicion.nombrePosicion", "Posición", 50));

        columns.add(new ColumnConfig("categoria.nombrecategoria", "Categoria", 80));

//
//        ColumnConfig Egreso = new ColumnConfig("egreso", "Egreso", 80);
//        Egreso.setNumberFormat(NumberFormat.getCurrencyFormat("USD"));
//        columns.add(Egreso);
//
//        columns.add(new ColumnConfig("descripcion", "Descripcion", 200));
        ColumnModel cm = new ColumnModel(columns);
        GridFilters filters = new GridFilters();

        StringFilter nombreFilter = new StringFilter("label");
//        NumericFilter numberfilter = new NumericFilter("peso");  
        ListStore<BeanModel> listacategoria = cbxCategoria.getStore();

        ListFilter listFilter = new ListFilter("categoria.nombrecategoria", listacategoria);
        listFilter.setDisplayProperty("nombrecategoria");

        //StringFilter documentoFilter = new StringFilter("documento");
        filters.addFilter(nombreFilter);
        filters.addFilter(listFilter);

//        filters.addFilter(listFilter);
        //filters.addFilter(documentoFilter);
        final Grid<BeanModel> grid = new Grid<BeanModel>(store, cm);

        grid.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
// este le sirve?? no espere miro algo
        /**
         * Escucho cuando se seleciona un deportista para cargar el formulario
         * manualmente
         */
        grid.getSelectionModel().addListener(Events.SelectionChange, new Listener<SelectionChangedEvent<BeanModel>>() {
            @Override
            public void handleEvent(SelectionChangedEvent<BeanModel> be) {
                // formBindings.unbind();
                if (be.getSelection().size() > 0) {

                    dep = (Deportista) be.getSelectedItem().getBean();
                    panelInfoDeportista.recibirEntidad(dep);

                } else {
                    formBindings.unbind();
                }
            }
        });

        grid.addListener(Events.Attach, new Listener<GridEvent<BeanModel>>() {
            @Override
            public void handleEvent(GridEvent<BeanModel> be) {
                PagingLoadConfig config = new BaseFilterPagingLoadConfig();
                config.setOffset(0);
                config.setLimit(50);

                Map<String, Object> state = grid.getState();
                if (state.containsKey("offset")) {
                    int offset = (Integer) state.get("offset");
                    int limit = (Integer) state.get("limit");
                    config.setOffset(offset);
                    config.setLimit(limit);
                }

                loader.load(config);
            }
        });

        grid.setLoadMask(true);
        grid.setBorders(true);
        grid.addPlugin(filters);
        grid.getView().setForceFit(true);
        grid.setTrackMouseOver(false);  
        
        
        cp.setBottomComponent(PgtoolBar);
        grid.setStateId("pagingGridExample");
        grid.setStateful(true);

        //cp.add(grid, new RowData(.5, 1));
        cp.add(grid, new RowData(1, 0.88, new Margins(0)));
        cp.setScrollMode(Style.Scroll.AUTO);
   
        panel2 = new ContentPanel();
        panel2.setLayout(new RowLayout(Orientation.HORIZONTAL));
        panel2.setSize(500, 500);
        panel2.setFrame(true);
        panel2.setHeaderVisible(false);
//        panel2.setCollapsible(true);

        cp.setTopComponent(toolBar);
        cp.setHeading("Tabla deportista");
//        cp2.add(toolBar, new FlowData(0));
//        cp.setLayout(new RowLayout(Orientation.HORIZONTAL));
        dataWest = new BorderLayoutData(LayoutRegion.WEST);
        dataWest.setMargins(new Margins(0, 0, 0, 0));
        dataWest.setSplit(true);
        panel2.add(cp, new RowData(0.44, 1, new Margins(0)));

//        formBindings = new FormBinding(panel, true);
        cp2.add(panelInfoDeportista);
        //cp2.setHeading("Perfil Deportista");
        cp2.setHeaderVisible(false);
        cp2.setLayout(new FillLayout(Orientation.VERTICAL));
        cp2.setCollapsible(true);

        dataCenter = new BorderLayoutData(LayoutRegion.CENTER);
        panel2.add(cp2, new RowData(0.56, 1, new Margins(0)));

        add(panel2);

        //Agrego un listener para escuchar el cambio de tamanio del panel
        this.addListener(Events.Resize, new Listener<BoxComponentEvent>() {
            public void handleEvent(final BoxComponentEvent event) {
                panel2.setWidth(event.getWidth());
                panel2.setHeight(event.getHeight());
                cp2.setHeight(event.getHeight() - 10);
//                if (event.getHeight() - 160 > 100) {
//                    cp.setHeight(event.getHeight()-10);
//                    cp2.setHeight(event.getHeight()-10);
//                } else {
//                    cp.setHeight(100);
//                }
            }
        });

    }

    public void cargar() {
        loader.load(0, 50);
        loader.load(0, 50);
        PgtoolBar.enable();
        
//        loader.load(0, 50);
    }

//    /**
//     * Escucha el boton limpiar
//     *
//     * @return
//     */
//    protected SelectionListener<ButtonEvent> listenerlimpiar() {
//        return new SelectionListener<ButtonEvent>() {
//            @Override
//            public void componentSelected(ButtonEvent ce) {
//                System.out.println("Ingrese al limpiar");
//                limpiar();
//
//            }
//        };
//    }
    /**
     * Escucha el boton eliminar, este elimina un movimiento logicamente.
     *
     * @return
     */
    public SelectionListener<ButtonEvent> listenerEliminar() {
        return new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                if (dep != null) {
                    MessageBox.confirm("Confirmación", "Seguro que desea eliminar a el deportista " + dep.getNombreCompleto(), new Listener<MessageBoxEvent>() {
                        @Override
                        public void handleEvent(MessageBoxEvent be) {

                            String respb = be.getButtonClicked().getText();

                            if (respb.equalsIgnoreCase("yes") || respb.equalsIgnoreCase("sí")) {

                                eliminar(dep);
                            }

                        }
                    });

                } else {
                    MessageBox.alert("Alerta", "Debe seleccionar primero un deportista", null);
                }

            }
        };
    }
    
    public SelectionListener<ButtonEvent> listenerBuscar() {
        return new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
//                if (dep != null) {
//
//                } else {
//                    MessageBox.alert("Alerta", "Debe seleccionar primero un deportista", null);
//                }

            }
        };
    }

    public SelectionListener<ButtonEvent> listenerReactivar() {
        return new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {

                reactivarDeportista = new PanelReactivarDeportista(PanelAdminDeportista.this);
                reactivarDeportista.show();

            }
        };

    }

    public SelectionListener<ButtonEvent> listenerCargarFoto() {
        return new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                if (dep != null) {
                    String espacios = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
                            + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"
                            + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
                    PanelInfoGeneral panelInfoGeneral = new PanelInfoGeneral(PanelAdminDeportista.this);

                    PanelCargarFotoDep panelCargarFotoDep = new PanelCargarFotoDep(PanelAdminDeportista.this);
                    panelCargarFotoDep.setId(dep.getId());
                    panelCargarFotoDep.lbNombreDep.setText(espacios + "<b>" + dep.getNombreCompleto() + "</b>");
                    panelCargarFotoDep.show();

                } else {
                    MessageBox.alert("Alerta", "Debe seleccionar primero un deportista", null);
                }
            }
        };

    }

    public SelectionListener<ButtonEvent> ListenerGenerarReporte() {
        return new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {

                if (dep != null) {

                    String base = GWT.getModuleBaseURL() + "../html/reportes/ReporteDeportistaHV/";
                    // deportista seleccionado
                    redireccionarA(base + dep.getId());

                } else {
                    MessageBox.alert("Alerta", "Debe seleccionar primero un deportista", null);
                }

            }
        };
    }

    public void eliminar(Deportista deportista) {

        getServiceDeportista().eliminarEntidad(dep, new AsyncCallback<RespuestaRPC<Deportista>>() {

            @Override
            public void onFailure(Throwable caught) {
                MessageBox.alert("Error", "No se eliminó el deportista", null);
            }

            @Override
            public void onSuccess(RespuestaRPC<Deportista> result) {

                Info.display("Eliminar", "Se eliminó correctamente el deportista");
                loader.load(0, 50);
                loader.load(0, 50);
//              panelInfoDeportista.panelInfoGeneral.limpiar();
            }

        });

    }

    /**
     * Mediante una llamada nativa redirecciona el browser a la dirección
     * especificada, en el caso de descargar archivos el contenido del browser
     * se conserva y simplemente lanza el archivo ;)
     *
     * @param url URL a ser cargada
     */
    private static native void redireccionarA(String url)/*-{
     $wnd.location = url;
     }-*/;

    public RPCAdminDeportistaAsync getServiceDeportista() {
        RPCAdminDeportistaAsync svc = (RPCAdminDeportistaAsync) GWT.create(RPCAdminDeportista.class);
        ServiceDefTarget endpoint = (ServiceDefTarget) svc;
        endpoint.setServiceEntryPoint("services/RPCAdminDeportista");
        return svc;
    }

    public void recargarFoto(Long id) {
        panelInfoDeportista.panelInfoGeneral.muestraFoto(id);
    }

}
