/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.client.administracion.Competencia;

import co.com.sisegfut.client.aaI18N.Main;
import static co.com.sisegfut.client.administracion.deportista.PanelInfoGeneral.ACTIVOS;
import co.com.sisegfut.client.datos.dominio.Competencia;
import co.com.sisegfut.client.datos.dominio.ConvocadosCompe;
import co.com.sisegfut.client.datos.dominio.Deportista;
import co.com.sisegfut.client.util.Resources;
import co.com.sisegfut.client.util.combox.ComboBoxCategoria;
import co.com.sisegfut.client.util.rpc.RPCAdminConvocadosComp;
import co.com.sisegfut.client.util.rpc.RPCAdminConvocadosCompAsync;
import co.com.sisegfut.client.util.rpc.RPCAdminDeportista;
import co.com.sisegfut.client.util.rpc.RPCAdminDeportistaAsync;
import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.Style.SelectionMode;
import com.extjs.gxt.ui.client.data.BaseFilterPagingLoadConfig;
import com.extjs.gxt.ui.client.data.BaseListLoader;
import com.extjs.gxt.ui.client.data.BasePagingLoadConfig;
import com.extjs.gxt.ui.client.data.BasePagingLoader;
import com.extjs.gxt.ui.client.data.BeanModel;
import com.extjs.gxt.ui.client.data.BeanModelReader;
import com.extjs.gxt.ui.client.data.ListLoadResult;
import com.extjs.gxt.ui.client.data.ListLoader;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.PagingLoadConfig;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.extjs.gxt.ui.client.data.PagingLoader;
import com.extjs.gxt.ui.client.data.RpcProxy;
import com.extjs.gxt.ui.client.dnd.DND;
import com.extjs.gxt.ui.client.dnd.GridDragSource;
import com.extjs.gxt.ui.client.dnd.GridDropTarget;
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.BoxComponentEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.DNDEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.GridEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.Label;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.TabItem;
import com.extjs.gxt.ui.client.widget.TabPanel;
import com.extjs.gxt.ui.client.widget.Text;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.grid.CheckBoxSelectionModel;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.RowNumberer;
import com.extjs.gxt.ui.client.widget.grid.filters.GridFilters;
import com.extjs.gxt.ui.client.widget.grid.filters.StringFilter;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.FillLayout;
import com.extjs.gxt.ui.client.widget.layout.RowData;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.extjs.gxt.ui.client.widget.tips.ToolTipConfig;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Andres Hurtado
 */
public class PanelAdminConvocados extends LayoutContainer {

    ///Grid Convocados
    public Grid<BeanModel> gridTitulares;
    ListLoader<ListLoadResult<ModelData>> loaderTitulares;
    public Grid<BeanModel> gridSuplentes;
    ListLoader<ListLoadResult<ModelData>> loaderSuplentes;
    //// Fin Grid Convocados

    ///Grid Jugadores
    private PagingLoader<PagingLoadResult<ModelData>> loaderJugadores;
    private Grid<BeanModel> gridJugadores;

    private Long IdCategoriaElegida = null;
    private ListLoader loaderSituaciones;
    private ContentPanel panelSelecConvocados;
    private ContentPanel panelConvocados;
    //// Fin Grid Jugadores
    private FieldSet fsConvocados = new FieldSet();
    private Button btnGuardarConvocados;
    private Button btnEditar;
    public ComboBoxCategoria cbxCategoria = new ComboBoxCategoria(ACTIVOS);
    private Long idCompetencia = null;
    PanelAdminPestComp adminPestComp;
    private Main myConstants;

    TabItem tabItemTitulares = new TabItem("Titulates");
    TabItem tabItemSuplentes = new TabItem("Suplentes");
    TabPanel tabpanelConvocados = new TabPanel();

//    PanelAdminPestComp adminPestComp = new PanelAdminPestComp();
    public PanelAdminConvocados(PanelAdminPestComp adminPestana) {

        myConstants = (Main) GWT.create(Main.class);
        adminPestComp = adminPestana;
        setScrollMode(Style.Scroll.AUTOY);
        BorderLayout layout = new BorderLayout();
        setLayout(layout);
        setStyleAttribute("padding", "0px");
        panelSelecConvocados = new ContentPanel();
        panelSelecConvocados.setStyleAttribute("padding", "0px");
        panelSelecConvocados.setLayout(new RowLayout(Style.Orientation.HORIZONTAL));
        panelSelecConvocados.setSize(500, 500);
        panelSelecConvocados.setFrame(true);
        panelSelecConvocados.disable();
        panelSelecConvocados.setHeaderVisible(false);
        ContentPanel jugadores = crearGridJugadores();

        Text label1 = new Text("Test Label 1");
        label1.addStyleName("pad-text");
        label1.setStyleAttribute("backgroundColor", "white");
        label1.setBorders(true);

        LayoutContainer convocados = crearConvocados();

        panelSelecConvocados.add(jugadores, new RowData(0.5, 1, new Margins(0)));
        panelSelecConvocados.add(convocados, new RowData(0.5, 1, new Margins(0)));
        add(panelSelecConvocados);

        btnGuardarConvocados = new Button("Siguiente", listenerGuardarConvocado());
        btnGuardarConvocados.setIconAlign(Style.IconAlign.RIGHT);
        btnGuardarConvocados.setIcon(Resources.ICONS.iconoLogin());

        btnEditar = new Button("Editar", listenerLimpiar());
        btnEditar.setIconAlign(Style.IconAlign.LEFT);
        btnEditar.setIcon(Resources.ICONS.iconoModificar());
        btnEditar.setEnabled(false);

//        panelSelecConvocados.addButton(btnEditar);
        panelSelecConvocados.addButton(btnGuardarConvocados);
        panelSelecConvocados.setButtonAlign(Style.HorizontalAlignment.CENTER);

        this.addListener(Events.Resize, new Listener<BoxComponentEvent>() {
            @Override
            public void handleEvent(final BoxComponentEvent event) {
                panelSelecConvocados.setWidth(event.getWidth());
                panelSelecConvocados.setHeight(event.getHeight() - 50);
            }
        });

    }

    public void setIdCompetencia(Long idCompetencia) {
        this.idCompetencia = idCompetencia;
    }

    public ContentPanel crearGridJugadores() {

        final RPCAdminDeportistaAsync svc = (RPCAdminDeportistaAsync) GWT.create(RPCAdminDeportista.class);
        ServiceDefTarget endpoint = (ServiceDefTarget) svc;
        endpoint.setServiceEntryPoint("services/RPCAdminDeportista");

        //Llamo el servicio RPC que se usara como proxy para cargarGridJugadores los datos de la entidad indicada
        RpcProxy<PagingLoadResult<Deportista>> proxy = new RpcProxy<PagingLoadResult<Deportista>>() {

            @Override
            protected void load(Object loadConfig, AsyncCallback<PagingLoadResult<Deportista>> callback) {
                if (IdCategoriaElegida != null) {
                    svc.getDeportistaxCategoria(IdCategoriaElegida, callback);
                } else {
//                    FilterPagingLoadConfig f = (FilterPagingLoadConfig) loadConfig;
//                    f.setSortDir(null);
//                    svc.getConsulta(f, callback);
                    svc.getDeportistaxCategoria(0L, callback);
                }
            }
        };

//        loaderSituaciones= new 
        loaderJugadores = new BasePagingLoader<PagingLoadResult<ModelData>>(proxy, new BeanModelReader()) {

            @Override
            protected void onLoadSuccess(Object loadConfig, PagingLoadResult<ModelData> result) {

                List<ModelData> eliminar = new ArrayList<ModelData>();
                for (ModelData jugador : result.getData()) {
                    for (BeanModel titular : gridTitulares.getStore().getModels()) {
                        if (((Long) jugador.get("id")).longValue() == ((Long) titular.get("id")).longValue()) {
                            eliminar.add(jugador);
                        }
                    }
                    for (BeanModel suplente : gridSuplentes.getStore().getModels()) {
                        if (((Long) jugador.get("id")).longValue() == ((Long) suplente.get("id")).longValue()) {
                            eliminar.add(jugador);
                        }
                    }
                }

                result.getData().removeAll(eliminar);
                super.onLoadSuccess(loadConfig, result);

                //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            protected Object newLoadConfig() {
                BasePagingLoadConfig config = new BaseFilterPagingLoadConfig();
                return config;
            }
        };
        loaderJugadores.setRemoteSort(false);

        ListStore<BeanModel> store = new ListStore<BeanModel>(loaderJugadores);
        store.setMonitorChanges(true);

        //Configuro las columnas de la tabla
        List<ColumnConfig> columns = new ArrayList<ColumnConfig>();

        final CheckBoxSelectionModel<BeanModel> sm = new CheckBoxSelectionModel<BeanModel>();
        // selection model supports the SIMPLE selection mode  
        sm.setSelectionMode(SelectionMode.SIMPLE);

        columns.add(sm.getColumn());

        columns.add(new ColumnConfig("numeroCamisa", "#", 20));
        columns.add(new ColumnConfig("label", "Nombre completo ", 100));

        columns.add(new ColumnConfig("posicion.nombrePosicion", "Posición", 50));

        ColumnModel cm = new ColumnModel(columns);
        GridFilters filters = new GridFilters();

        StringFilter nombreFilter = new StringFilter("label");

        filters.addFilter(nombreFilter);

        gridJugadores = new Grid<BeanModel>(store, cm);

//        gridJugadores.getSelectionModel().setSelectionMode(Style.SelectionMode.MULTI);
        gridJugadores.addListener(Events.Attach, new Listener<GridEvent<BeanModel>>() {
            @Override
            public void handleEvent(GridEvent<BeanModel> be) {
                PagingLoadConfig config = new BaseFilterPagingLoadConfig();
                config.setOffset(0);
                config.setLimit(50);

                Map<String, Object> state = gridJugadores.getState();
                if (state.containsKey("offset")) {
                    int offset = (Integer) state.get("offset");
                    int limit = (Integer) state.get("limit");
                    config.setOffset(offset);
                    config.setLimit(limit);
                }

                loaderJugadores.load(config);
            }
        });

        gridJugadores.setLoadMask(true);
        gridJugadores.setBorders(true);
        gridJugadores.addPlugin(filters);
        gridJugadores.addPlugin(sm);
        gridJugadores.setSelectionModel(sm);
        gridJugadores.getView().setForceFit(true);
        gridJugadores.setTrackMouseOver(false);
        new GridDragSource(gridJugadores);
        new GridDropTarget(gridJugadores);

        Button btnLimpiar = new Button("Limpiar", listenerLimpiar());
        btnLimpiar.setIcon(Resources.ICONS.iconoRefrescar());

        ContentPanel cpju = new ContentPanel();
        cpju.setHeading("Convocar Jugadores");
        cpju.setScrollMode(Style.Scroll.AUTO);
        cpju.setWidth(250);
        cpju.setLayout(new FillLayout());
        Label lbFiltro = new Label();
        lbFiltro.setText("<b>Filtrar :</b>");

        cbxCategoria.setName("categoria.nombrecategoria");
        cbxCategoria.setToolTip(new ToolTipConfig("Categoria", "Seleccione una categoria"));
        cbxCategoria.setFieldLabel("<font color='red'>*</font> Categoria");
        cbxCategoria.setAllowBlank(false);
//        Button btnLimpiar = new Button("Limpiar", listenerLimpiar());
//        btnLimpiar.setIcon(Resources.ICONS.iconoRefrescar());

//        setLayout(new FillLayout(Orientation.VERTICAL));
        ToolBar toolBar = new ToolBar();

        toolBar.setSpacing(2);
        toolBar.add(lbFiltro);
//        toolBar.add(new SeparatorToolItem());
        toolBar.add(cbxCategoria);

//        toolBar.add(btnLimpiar); 
        cpju.setTopComponent(toolBar);

        cbxCategoria.addListener(Events.SelectionChange, new Listener<BaseEvent>() {
            @Override
            public void handleEvent(BaseEvent be) {
                Info.display("Categoria", "Filtro por la categoria " + cbxCategoria.getCategoriaElegida().getNombrecategoria());
                cargarJugadoresXCategoria(cbxCategoria.getCategoriaElegida().getId());
            }
        });

        cpju.add(gridJugadores);
        return cpju;
    }

    public void cargarJugadoresXCategoria(Long idCategoria) {
        IdCategoriaElegida = idCategoria;
        cargarGridJugadores();
    }

    public LayoutContainer crearConvocados() {

        LayoutContainer lcConvocados = new LayoutContainer();
        BorderLayout layout = new BorderLayout();
        lcConvocados.setLayout(layout);
        lcConvocados.setStyleAttribute("padding", "0px");

        panelConvocados = new ContentPanel();
        panelConvocados.setHeading("Convocados");
        panelConvocados.setStyleAttribute("padding", "0px");
        panelConvocados.setLayout(new RowLayout(Style.Orientation.VERTICAL));
        panelConvocados.setSize(250, 250);
        panelConvocados.setFrame(true);
        panelConvocados.setHeaderVisible(true);

//        final ContentPanel cpTitulares = crearTitulares();
//        final ContentPanel cpSuplentes = crearSuplentes();
        tabpanelConvocados.setLayoutData(new FillLayout());
        tabpanelConvocados.setHeight(600);

        tabItemTitulares.setLayout(new FillLayout());
        tabItemTitulares.add(crearTitulares());
        tabItemTitulares.setIcon(Resources.ICONS.table());
        tabItemSuplentes.setLayout(new FillLayout());
        tabItemSuplentes.setIcon(Resources.ICONS.table());
        tabItemSuplentes.add(crearSuplentes());

        tabpanelConvocados.add(tabItemTitulares);
        tabpanelConvocados.add(tabItemSuplentes);
        tabpanelConvocados.setTabScroll(true);

//        panelConvocados.add(cpTitulares, new RowData(1, 0.6, new Margins(0)));
//        panelConvocados.add(cpSuplentes, new RowData(1, 0.7, new Margins(0)));
        panelConvocados.add(tabpanelConvocados, new RowData(1, 1, new Margins(0)));

        lcConvocados.add(panelConvocados);

        lcConvocados.addListener(Events.Resize, new Listener<BoxComponentEvent>() {
            @Override
            public void handleEvent(final BoxComponentEvent event) {
                panelConvocados.setWidth(event.getWidth());
                panelConvocados.setHeight(event.getHeight());
                tabpanelConvocados.setWidth(event.getWidth());
                tabpanelConvocados.setHeight(event.getHeight() - 20);
            }
        });

//         panelConvocados.addListener(Events.Resize, new Listener<BoxComponentEvent>() {
//            @Override
//            public void handleEvent(final BoxComponentEvent event) {
//                cpSuplentes.setHeight(event.getWidth()-cpTitulares.getHeight());
//            }
//        });
        return lcConvocados;
    }

    public void cargarGridJugadores() {
        loaderJugadores.load(0, 50);
        loaderJugadores.load(0, 50);
//        loader.load(0, 50);
    }

    public ContentPanel crearTitulares() {

        final RPCAdminConvocadosCompAsync svc = (RPCAdminConvocadosCompAsync) GWT.create(RPCAdminConvocadosComp.class);
        ServiceDefTarget endpoint = (ServiceDefTarget) svc;
        endpoint.setServiceEntryPoint("services/RPCAdminConvocadosComp");

        RpcProxy<List<Deportista>> proxy = new RpcProxy<List<Deportista>>() {
            @Override
            public void load(Object loadConfig, AsyncCallback<List<Deportista>> callback) {
                if (idCompetencia != null) {
                    svc.getConvocadosXTipo(idCompetencia, "t", callback);
                } else {
                    svc.getConvocadosXTipo(0l, "t", callback);
                }
            }
        };
        // loader and store  
        loaderTitulares = new BaseListLoader<ListLoadResult<ModelData>>(proxy, new BeanModelReader());

        ListStore<BeanModel> storeTitulares = new ListStore<BeanModel>(loaderTitulares);

        //Configuro las columnas de la tabla
        List<ColumnConfig> columns = new ArrayList<ColumnConfig>();
        final RowNumberer r = new RowNumberer();

        columns.add(r);
        columns.add(new ColumnConfig("numeroCamisa", "#", 20));
        columns.add(new ColumnConfig("label", "Nombre completo ", 100));

        columns.add(new ColumnConfig("posicion.nombrePosicion", "Posición", 50));

        ColumnModel cm = new ColumnModel(columns);

        gridTitulares = new Grid<BeanModel>(storeTitulares, cm);

        gridTitulares.getSelectionModel().setSelectionMode(Style.SelectionMode.MULTI);

        gridTitulares.setLoadMask(true);
        gridTitulares.setBorders(true);
        gridTitulares.addPlugin(r);
        gridTitulares.getView().setForceFit(true);
        gridTitulares.setTrackMouseOver(false);

        new GridDragSource(gridTitulares);
        GridDropTarget t1 = new GridDropTarget(gridTitulares) {
            @Override
            protected void onDragEnter(DNDEvent e) {
                List<Deportista> deportistas = e.getData();

                int cantidad = gridTitulares.getStore().getModels().size() + deportistas.size();

                if (gridTitulares.getStore().getModels().size() >= 11 || deportistas.size() > 11 || cantidad > 11) {
                    e.preventDefault();
                    e.setCancelled(true);
                    MessageBox.alert("Advertencia", "¡Excedió la cantidad de titulares permitidos (11)!", null);
                    return;
                }
//                MessageBox.alert("Error", "E data: "+deportistas.size(),null);;
                r.setSortable(true);
                super.onDragEnter(e); //To change body of generated methods, choose Tools | Templates.
            }

        };
        t1.setFeedback(DND.Feedback.INSERT);

        ContentPanel cp = new ContentPanel();
        cp.setBodyBorder(true);
        cp.setScrollMode(Style.Scroll.AUTO);
//        cp.setIcon(Resources.ICONS.table());
        cp.setHeaderVisible(false);
//        cp.setHeading("Titulares");
        cp.setLayout(new FillLayout());
        cp.add(gridTitulares);
        return cp;
    }

    public ContentPanel crearSuplentes() {

        final RPCAdminConvocadosCompAsync svc = (RPCAdminConvocadosCompAsync) GWT.create(RPCAdminConvocadosComp.class);
        ServiceDefTarget endpoint = (ServiceDefTarget) svc;
        endpoint.setServiceEntryPoint("services/RPCAdminConvocadosComp");

        RpcProxy<List<Deportista>> proxy = new RpcProxy<List<Deportista>>() {
            @Override
            public void load(Object loadConfig, AsyncCallback<List<Deportista>> callback) {
                if (idCompetencia != null) {
                    svc.getConvocadosXTipo(idCompetencia, "s", callback);
                } else {
                    svc.getConvocadosXTipo(0l, "s", callback);
                }
            }
        };

        // loader and store  
        loaderSuplentes = new BaseListLoader<ListLoadResult<ModelData>>(proxy, new BeanModelReader());

        ListStore<BeanModel> storeSuplentes = new ListStore<BeanModel>(loaderSuplentes);

        //Configuro las columnas de la tabla
        List<ColumnConfig> columns = new ArrayList<ColumnConfig>();
        final RowNumberer r = new RowNumberer();

        columns.add(r);
        columns.add(new ColumnConfig("numeroCamisa", "#", 20));
        columns.add(new ColumnConfig("label", "Nombre completo ", 100));

        columns.add(new ColumnConfig("posicion.nombrePosicion", "Posición", 50));

        ColumnModel cm = new ColumnModel(columns);

        gridSuplentes = new Grid<BeanModel>(storeSuplentes, cm);

        gridSuplentes.getSelectionModel().setSelectionMode(Style.SelectionMode.MULTI);

        gridSuplentes.setLoadMask(true);
        gridSuplentes.setBorders(true);
        gridSuplentes.addPlugin(r);
        gridSuplentes.getView().setForceFit(true);
        gridSuplentes.setTrackMouseOver(false);
        new GridDragSource(gridSuplentes);
        GridDropTarget t1 = new GridDropTarget(gridSuplentes) {
            @Override
            protected void onDragEnter(DNDEvent e) {
                List<Deportista> deportistas = e.getData();

                int cantidad = gridSuplentes.getStore().getModels().size() + deportistas.size();

                if (gridSuplentes.getStore().getModels().size() >= 15 || deportistas.size() > 15 || cantidad > 15) {
                    e.preventDefault();
                    e.setCancelled(true);
                    MessageBox.alert("Error", "Excedió la cantidad de suplentes permitidos (15)", null);
                    return;
                }
                r.setSortable(true);
                super.onDragEnter(e); //To change body of generated methods, choose Tools | Templates.
            }

        };
        t1.setFeedback(DND.Feedback.INSERT);

//        gridSuplentes.addListener(Events.SelectionChange, new Listener<BaseEvent>() {
//
//            @Override
//            public void handleEvent(BaseEvent be) {
//               
//                
//            }
//        });
//
//        gridSuplentes.addListener(Events.SelectionChange, new Listener<BaseEvent>() {
//            @Override
//            public void handleEvent(BaseEvent be) {
////                Info.display("Categoria", "Filtro por la categoria " + cbxCategoria.getCategoriaElegida().getNombrecategoria());
//                cargarJugadoresXCategoria(cbxCategoria.getCategoriaElegida().getId());
//            }
//        });
        ContentPanel cp = new ContentPanel();
        cp.setBodyBorder(true);
        cp.setScrollMode(Style.Scroll.AUTO);
//        cp.setIcon(Resources.ICONS.table());
        cp.setHeaderVisible(false);
//        cp.setHeading("Suplentes");
        cp.setLayout(new FillLayout());
        cp.add(gridSuplentes);

        return cp;
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
//                

            }
        };
    }

    protected SelectionListener<ButtonEvent> listenerGuardarConvocado() {
        return new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {

                if (gridTitulares.getStore().getModels().isEmpty() || gridTitulares.getStore().getModels().size() < 7) {
                    MessageBox.alert("Error", "Debe Ingresar como mínimo 7 jugadores titulares ", null);
//                } 
//                else if (gridSuplentes.getStore().getModels().isEmpty() || gridSuplentes.getStore().getModels().size()<6) {
//                    MessageBox.alert("Error", "Debe ingresar como mínimo 5 jugadores suplentes ", null);
                } else {
                    List<ConvocadosCompe> convocadosCompe = new ArrayList<ConvocadosCompe>();
                    List<BeanModel> depTitulares = gridTitulares.getStore().getModels();
                    for (BeanModel deportista : depTitulares) {
                        ConvocadosCompe cc = new ConvocadosCompe();
                        Long idDep = (Long) deportista.get("id");
                        cc.setIdDeportista(new Deportista(idDep));
                        cc.setTipoConvocado("t");
                        cc.setIdCompetencia(new Competencia(idCompetencia));
                        convocadosCompe.add(cc);
                    }
                    List<BeanModel> depSuplentes = gridSuplentes.getStore().getModels();
                    for (BeanModel deportista : depSuplentes) {
                        ConvocadosCompe cc = new ConvocadosCompe();
                        Long idDep = (Long) deportista.get("id");
                        cc.setIdDeportista(new Deportista(idDep));
                        cc.setTipoConvocado("s");
                        cc.setIdCompetencia(new Competencia(idCompetencia));
                        convocadosCompe.add(cc);
                    }
                    getServiceConvocados().guardarConvocadosComp(convocadosCompe, new AsyncCallback<Void>() {

                        @Override
                        public void onFailure(Throwable caught) {

                            Info.display("Error", "No Guardo");
                        }

                        @Override
                        public void onSuccess(Void result) {
                            Info.display("Exito", "Guardo correctamente los convocados");
                            habilitarCampos();
                        }
                    });
                }
            }
        };

    }

    public void limpiarGrids() {
        gridSuplentes.getStore().removeAll();
        gridTitulares.getStore().removeAll();
//    cargarGridSuplente();
//    cargarGridTitulares();
    }

    public void habilitarCampos() {
        gridTitulares.setEnabled(false);
        gridSuplentes.setEnabled(false);
        btnGuardarConvocados.disable();
        btnEditar.enable();
        adminPestComp.tabItemSituaciones.enable();
        adminPestComp.getTabpanel().setSelection(adminPestComp.tabItemSituaciones);
        adminPestComp.tabItemControlJuego.enable();
        adminPestComp.tabItemSituaciones.focus();
    }

    public RPCAdminConvocadosCompAsync getServiceConvocados() {
        RPCAdminConvocadosCompAsync svc = (RPCAdminConvocadosCompAsync) GWT.create(RPCAdminConvocadosComp.class);
        ServiceDefTarget endpoint = (ServiceDefTarget) svc;
        endpoint.setServiceEntryPoint("services/RPCAdminConvocadosComp");
        return svc;
    }

    public void cargarGridTitulares() {
        loaderTitulares.load();
    }

    public void cargarGridSuplente() {
        loaderSuplentes.load();
    }

    public void cargarConvocadosCompetencia(Long idCompetencia, boolean habilitar) {

        setIdCompetencia(idCompetencia);
        cargarGridSuplente();
        cargarGridTitulares();
//        gridTitulares.setEnabled(habilitar);
//        gridSuplentes.setEnabled(habilitar);
        gridJugadores.setEnabled(habilitar);
        cbxCategoria.setEnabled(habilitar);

        btnGuardarConvocados.setVisible(habilitar);

    }

    public void reiniciarConvocados() {

//        gridTitulares.enable();
//        gridSuplentes.enable();
        gridJugadores.enable();
        limpiarGrids();
        cbxCategoria.enable();
        cbxCategoria.recargar();
        cbxCategoria.reset();
        cargarGridJugadores();
        cargarGridSuplente();
        cargarGridTitulares();
        btnGuardarConvocados.enable();
        btnGuardarConvocados.setVisible(true);
        setIdCompetencia(null);

    }

}
