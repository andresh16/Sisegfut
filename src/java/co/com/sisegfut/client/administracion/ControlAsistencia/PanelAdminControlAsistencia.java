/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.client.administracion.ControlAsistencia;

import co.com.sisegfut.client.aaI18N.Main;
import static co.com.sisegfut.client.administracion.deportista.PanelInfoGeneral.ACTIVOS;
import co.com.sisegfut.client.datos.dominio.Asistencia;
import co.com.sisegfut.client.datos.dominio.Categoria;
import co.com.sisegfut.client.datos.dominio.ControlAsistencia;
import co.com.sisegfut.client.datos.dominio.Deportista;
import co.com.sisegfut.client.datos.dominio.Usuarios;
import co.com.sisegfut.client.datos.dominio.dto.DTOControlAsistencia;
import co.com.sisegfut.client.entidades.RespuestaRPC;
import co.com.sisegfut.client.util.BeansLocales;
import co.com.sisegfut.client.util.Formatos;
import co.com.sisegfut.client.util.Resources;
import co.com.sisegfut.client.util.combox.ComboBoxCategoria;
import co.com.sisegfut.client.util.rpc.RPCAdminAsistencia;
import co.com.sisegfut.client.util.rpc.RPCAdminAsistenciaAsync;
import co.com.sisegfut.client.util.rpc.RPCAdminControlAsistencia;
import co.com.sisegfut.client.util.rpc.RPCAdminControlAsistenciaAsync;
import com.extjs.gxt.ui.client.Style;
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
import com.extjs.gxt.ui.client.event.BoxComponentEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.GridEvent;
import com.extjs.gxt.ui.client.event.IconButtonEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.ToolButton;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.DateField;
import com.extjs.gxt.ui.client.widget.form.FormButtonBinding;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.Radio;
import com.extjs.gxt.ui.client.widget.form.RadioGroup;
import com.extjs.gxt.ui.client.widget.form.SimpleComboBox;
import com.extjs.gxt.ui.client.widget.grid.BufferView;
import com.extjs.gxt.ui.client.widget.grid.CellEditor;
import com.extjs.gxt.ui.client.widget.grid.CheckColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.EditorGrid;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.extjs.gxt.ui.client.widget.grid.filters.GridFilters;
import com.extjs.gxt.ui.client.widget.grid.filters.StringFilter;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.ColumnLayout;
import com.extjs.gxt.ui.client.widget.layout.FillLayout;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.extjs.gxt.ui.client.widget.layout.RowData;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.extjs.gxt.ui.client.widget.tips.ToolTipConfig;
import com.extjs.gxt.ui.client.widget.toolbar.PagingToolBar;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author fhurtado
 */
public class PanelAdminControlAsistencia extends LayoutContainer {

    private FormBinding formBindings;
    private PagingLoader<PagingLoadResult<ModelData>> loader;

    Long id = null;
    protected MessageBox boxWait;
    private Usuarios usuarioLogeado;
    //Creo el toolbar de paginacion de el grid
    final PagingToolBar PgtoolBar = new PagingToolBar(50);
    private Deportista dep;
    BorderLayoutData dataWest;
    BorderLayoutData dataCenter;
    ContentPanel panel2;
    private ComboBoxCategoria cbxCategoria;
    RpcProxy<PagingLoadResult<Asistencia>> proxy;
    ListStore<BeanModel> store;
    private EditorGrid<BeanModel> grid;
    private PanelFormularioControlAsistencia panelFormularioControlAsistencia;
    ContentPanel cp;
    private Long IdCategoriaElegida;

    private Window wBuscar;
    private Window wReporteAsistenciaMes;
    private DTOControlAsistencia dTOControlAsistencia;
    private Button btnBuscarAsistencia;
    private Button btnVerCtrolAsistencia;
    private PagingLoader<PagingLoadResult<ModelData>> loaderControlAsistencia;
    private ListStore<ControlAsistencia> storeCtrlAsistencia;
    private PagingToolBar PgtoolBarCtrolAsitencia = new PagingToolBar(50);
    private Grid<ControlAsistencia> gridCtrlAsistencia;

    private DateField DtFechaInicial = new DateField();
    private DateField DtFechaFinal = new DateField();
    private ComboBoxCategoria cbxCategoria2 = new ComboBoxCategoria(ComboBoxCategoria.ACTIVOS);
    private Radio rdCompetencia = new Radio();
    private Radio rdEntrenamiento = new Radio();
    private RadioGroup radioGroup = new RadioGroup();
    private Long idCategoria = null;
    private String actividad = "Entrenamiento";
    private FormButtonBinding bindingFormFiltros;
    private boolean consultarPlanillaasistencia = false;

    private Main myConstants;

    @Override
    protected void onRender(Element parent, int index) {
        super.onRender(parent, index);

        BorderLayout layout = new BorderLayout();
        setLayout(layout);

//        setScrollMode(Style.Scroll.AUTOY);
        cp = new ContentPanel();
        final ContentPanel cp2 = new ContentPanel();
        cp2.setLayout(new RowLayout(Style.Orientation.HORIZONTAL));
        cp.setScrollMode(Style.Scroll.AUTO);
        myConstants = (Main) GWT.create(Main.class);

        btnBuscarAsistencia = new Button("Buscar Planilla Asistencia", listenerBuscar());
        btnBuscarAsistencia.setIcon(Resources.ICONS.iconoBuscar());

        cbxCategoria = new ComboBoxCategoria(ACTIVOS);
        cbxCategoria.setName("categoria.nombrecategoria");
        cbxCategoria.setToolTip(new ToolTipConfig("Categoria", "Seleccione una categoria"));
        cbxCategoria.setFieldLabel("<font color='red'>*</font> Categoria");
        cbxCategoria.setAllowBlank(false);

        //  cbxCategoria.setEditable(false);
//        setLayout(new FillLayout(Orientation.VERTICAL));
        ToolBar toolBar = new ToolBar();

        toolBar.setSpacing(2);

        toolBar.add(cbxCategoria);
        panelFormularioControlAsistencia = new PanelFormularioControlAsistencia(this);

        // panel.setButtonAlign(Style.HorizontalAlignment.CENTER);
        //Monitors the valid state of a form and enabled / disabled all buttons.
//        FormButtonBinding binding = new FormButtonBinding(panel);
//        binding.addButton(btnReactivar);
//        add(toolBar, new FlowData(0));
        usuarioLogeado = BeansLocales.getUsuario();

        final RPCAdminAsistenciaAsync svc = (RPCAdminAsistenciaAsync) GWT.create(RPCAdminAsistencia.class);
        ServiceDefTarget endpoint = (ServiceDefTarget) svc;
        endpoint.setServiceEntryPoint("services/RPCAdminAsistencia");

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
        proxy = new RpcProxy<PagingLoadResult<Asistencia>>() {
            @Override
            protected void load(Object loadConfig, AsyncCallback<PagingLoadResult<Asistencia>> callback) {
                if (consultarPlanillaasistencia) {
                    svc.getAsistenciaxId(dTOControlAsistencia.getIdPlanillaAsistencia(), callback);
                } else {
                    svc.getDeportistasxCategoria(IdCategoriaElegida, callback);
                }
            }

        };

        loader = new BasePagingLoader<PagingLoadResult<ModelData>>(proxy, new BeanModelReader()) {
            @Override
            protected Object newLoadConfig() {
                BasePagingLoadConfig config = new BaseFilterPagingLoadConfig();
                return config;
            }

//            @Override
//            protected void onLoadSuccess(Object loadConfig, PagingLoadResult<ModelData> result) {
//
//                for (ModelData jug : result.getData()) {
//                    jug.set("falto", "ASISTE");
//                }
//
//                super.onLoadSuccess(loadConfig, result); //To change body of generated methods, choose Tools | Templates.
//
//            }
        };
        loader.setRemoteSort(false);

        store = new ListStore<BeanModel>(loader);
        store.setMonitorChanges(true);

        PgtoolBar.bind(loader);
        //Configuro las columnas de la tabla
        List<ColumnConfig> columns = new ArrayList<ColumnConfig>();

        final SimpleComboBox<String> comboFalto = new SimpleComboBox<String>();
        comboFalto.setForceSelection(true);
        comboFalto.setTriggerAction(ComboBox.TriggerAction.ALL);
        comboFalto.add("ASISTE");
        comboFalto.add("NO ASISTE");
        comboFalto.add("LESIONADO");
        comboFalto.add("PERMISO");
        comboFalto.add("TARJETA ROJA");

        CellEditor editor = new CellEditor(comboFalto) {
            @Override
            public Object preProcessValue(Object value) {
                if (value == null) {
                    return value;
                }
                return comboFalto.findModel(value.toString());

            }

            @Override
            public Object postProcessValue(Object value) {
                if (value == null) {
                    return value;
                }
                return ((ModelData) value).get("value");

            }
        };

        ColumnConfig asistio = new ColumnConfig();
        asistio.setId("estado");
        asistio.setHeader("Asistio?");
        asistio.setAlignment(Style.HorizontalAlignment.CENTER);
        asistio.setWidth(70);
        asistio.setEditor(editor);
        columns.add(asistio);
        asistio.setRenderer(new GridCellRenderer() {

            @Override
            public Object render(ModelData model, String property, com.extjs.gxt.ui.client.widget.grid.ColumnData config, int rowIndex, int colIndex, ListStore store, Grid grid) {
                String asiste = model.get("estado");
                if (asiste == null) {
                    return "";
                } else {
                    String style = "";
                    if (asiste.equalsIgnoreCase("ASISTE")) {
                        style = "green";
                    } else if (asiste.equalsIgnoreCase("NO ASISTE")) {
                        style = "red";
                    } else if (asiste.equalsIgnoreCase("LESIONADO")) {
                        style = "orange";
                    } else if (asiste.equalsIgnoreCase("PERMISO")) {
                        style = "gray";
                    } else if (asiste.equalsIgnoreCase("TARJETA ROJA")) {
                        style = "black";
                    }
                    return "<span style='color:" + style + "'><b>" + asiste + "</b></span>";
                }
            }

        });

//        CheckBox checkBox = new CheckBox();
//        CheckColumnConfig checkColumn = new CheckColumnConfig("asistio", "Asistio?", 30);
//        CellEditor checkBoxEditor = new CellEditor(checkBox);
//        checkColumn.setEditor(checkBoxEditor);
////        columns.add(checkColumn);
        columns.add(new ColumnConfig("idDeportista.documento", "Documento ", 60));
        columns.add(new ColumnConfig("idDeportista.label", "Nombre completo ", 120));
        columns.add(new ColumnConfig("idDeportista.telefono", "Télefono", 50));
        columns.add(new ColumnConfig("idDeportista.posicion.nombrePosicion", "Posición", 50));

        ColumnConfig idDep = new ColumnConfig();
        idDep.setId("idDeportista.id");
        idDep.setWidth(20);
        idDep.setHidden(true);
        columns.add(idDep);

        ColumnConfig id = new ColumnConfig();
        id.setId("id");
        id.setWidth(20);
        id.setHidden(true);
        columns.add(id);

//        columns.add(new ColumnConfig("fecha.nombrecategoria", "Categoria", 50));
//
//        ColumnConfig Egreso = new ColumnConfig("egreso", "Egreso", 80);
//        Egreso.setNumberFormat(NumberFormat.getCurrencyFormat("USD"));
//        columns.add(Egreso);
//
//        columns.add(new ColumnConfig("descripcion", "Descripcion", 200));
        ColumnModel cm = new ColumnModel(columns);
        GridFilters filters = new GridFilters();

        StringFilter nombreFilter = new StringFilter("label");

//        ListStore<ModelData> typeStore = new ListStore<ModelData>();
//        
//        typeStore.add();
////        typeStore.add(type("Media"));
////        typeStore.add(type("Medical"));
////        typeStore.add(type("Tech"));
//        ListFilter listFilter = new ListFilter("fecha.nombrecategoria", typeStore);
//        listFilter.setDisplayProperty("fecha.nombrecategoria");
//        //StringFilter documentoFilter = new StringFilter("documento");
        filters.addFilter(nombreFilter);

//        filters.addFilter(listFilter);
        //filters.addFilter(documentoFilter);
        grid = new EditorGrid<BeanModel>(store, cm);
//        grid.addPlugin(checkColumn);
//        grid.addPlugin(columnfalto);

        grid.getSelectionModel().setSelectionMode(Style.SelectionMode.SINGLE);

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

//                loader.load(config);
            }
        });

        grid.setLoadMask(true);
        grid.setBorders(true);
        grid.addPlugin(filters);
        grid.getView().setForceFit(true);
        cp.setLayout(new FillLayout(Style.Orientation.VERTICAL));

        cp.setBottomComponent(PgtoolBar);
        grid.setStateId("pagingGridExample");
        grid.setStateful(true);

        //cp.add(grid, new RowData(.5, 1));
        cp.add(grid);

        panel2 = new ContentPanel();
        panel2.setHeading("Control de Asistencia");
        panel2.setLayout(new RowLayout(Style.Orientation.HORIZONTAL));
        panel2.setSize(500, 500);

        panel2.setFrame(true);
        panel2.setHeaderVisible(true);
        cp.setHeaderVisible(true);
        dataWest = new BorderLayoutData(Style.LayoutRegion.WEST);
        dataWest.setMargins(new Margins(0, 0, 0, 0));
        dataWest.setSplit(true);
        panel2.add(cp2, new RowData(0.4, 1, new Margins(0)));

        cp2.add(panelFormularioControlAsistencia, new RowData(0.6, 1, new Margins(0)));

        cp2.setHeaderVisible(false);
        cp2.setLayout(new FillLayout(Style.Orientation.VERTICAL));
        dataCenter = new BorderLayoutData(Style.LayoutRegion.CENTER);
        panel2.add(cp, new RowData(0.6, 1, new Margins(0)));

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

        Button btnGuardar = new Button(" Guardar", listenerGuardar());
        btnGuardar.setIcon(Resources.ICONS.iconoGuardar());

        Button btnLimpiar = new Button("Nuevo", listenerLimpiar());
        btnLimpiar.setIcon(Resources.ICONS.iconoNuevaCita());

        FormButtonBinding binding = new FormButtonBinding(panelFormularioControlAsistencia);
        binding.addButton(btnGuardar);

        panel2.setButtonAlign(Style.HorizontalAlignment.CENTER);
        panel2.addButton(btnGuardar);
        panel2.addButton(btnLimpiar);
        panel2.addButton(btnBuscarAsistencia);

        panel2.getHeader().addTool(new ToolButton("x-tool-help", new SelectionListener<IconButtonEvent>() {
            @Override
            public void componentSelected(IconButtonEvent ce) {
                abrirVentana(myConstants.ayudaPanelAsistencia());
            }
        }));

        /// Ventana de buscar planilla Asistencia 
        wBuscar = new Window();
        wBuscar.setSize(700, 550);
        wBuscar.setPlain(true);
        wBuscar.setModal(true);
        wBuscar.setClosable(false);
        wBuscar.setBlinkModal(true);
        wBuscar.setHeading("Buscar planilla asistencia");
        wBuscar.setLayout(new FillLayout());

        wBuscar.add(crearGriControlAsistencia());

        btnVerCtrolAsistencia = new Button("Ver planilla", Resources.ICONS.iconoGrid(), listenerVerPlanillaAsistencia());

        Button btnCancelarBusCompetencia = new Button("Cancelar", new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                wBuscar.hide();
                limpiarCamposBusqueda();
            }
        });

//                btnEstadisticaCompetencia = new Button("Ver Estadisticas", new SelectionListener<ButtonEvent>() {
//
//                    @Override
//                    public void componentSelected(ButtonEvent ce) {
//                        panelEstadisticas.setIdCompetencia(dTOCompetencia.getIdCompetencia());
//                        panelEstadisticas.show();
//                    }
//                });
//                btnEstadisticaCompetencia.disable();
        wBuscar.getHeader().addTool(new ToolButton("x-tool-help", new SelectionListener<IconButtonEvent>() {
            @Override
            public void componentSelected(IconButtonEvent ce) {
//                        abrirVentana(myConstants.ayudaPanelAsistenciaBuscar());
            }
        }));
        wBuscar.addButton(btnVerCtrolAsistencia);
        wBuscar.addButton(btnCancelarBusCompetencia);
//                
        wBuscar.setButtonAlign(Style.HorizontalAlignment.CENTER);
//                
        wBuscar.setFocusWidget(wBuscar.getButtonBar().getItem(0));

    }

    protected SelectionListener<ButtonEvent> listenerLimpiar() {
        return new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                limpiarCampos();
            }
        };

    }

    protected SelectionListener<ButtonEvent> listenerVerPlanillaAsistencia() {
        return new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                ControlAsistencia controlAsistencia = new ControlAsistencia();
                controlAsistencia.setId(dTOControlAsistencia.getIdPlanillaAsistencia());
                controlAsistencia.setCategoria(new Categoria(dTOControlAsistencia.getIdCategoria()));
                controlAsistencia.setFecha(dTOControlAsistencia.getFecha());
                controlAsistencia.setActividad(dTOControlAsistencia.getActividad());
                controlAsistencia.setLugar(dTOControlAsistencia.getLugar());
                controlAsistencia.setObservaciones(dTOControlAsistencia.getObservaciones());
                panelFormularioControlAsistencia.cargarDatos(controlAsistencia);
                fechaAsistencia(Formatos.fechaLarga2(dTOControlAsistencia.getFecha()));
                wBuscar.hide();
                limpiarCamposBusqueda();
                consultarPlanillaasistencia = true;
                cargar();
//                consultarPlanillaasistencia = false;
            }
        };

    }

    protected SelectionListener<ButtonEvent> listenerGuardar() {
        return new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                if (!grid.getStore().getModels().isEmpty()) {
                    if (panelFormularioControlAsistencia.isValid()) {
//                     panel2.mask("Guardando..");
//                        if (!validarAsistencia()) {
//                            //mostrar mensaje
//                            return;
//                        }
//                        if (!validarFalto()) {
//                            //mostrar mensaje
//                            return;
//                        }

                        getServiceControlAsistencia().guardarEntidad(panelFormularioControlAsistencia.ObtenerFormulario(new ControlAsistencia()), new AsyncCallback<RespuestaRPC<ControlAsistencia>>() {

                            @Override
                            public void onFailure(Throwable caught) {
                                MessageBox.alert("Error!", "No guardo Grid de asistencia", null);
                            }

                            @Override
                            public void onSuccess(RespuestaRPC<ControlAsistencia> result) {
//                            Info.display("Exito!", "Se guardo correctamente el control de asistencia");
                                guardarGridAsistencia(result.getObjetoRespuesta().getId());
                            }
                        });
                    }
                } else {
                    MessageBox.alert("Guardar", "Debe seleccionar una categoría con deportistas", null);
                }
////                
//              
            }
        };

    }

    private boolean validarAsistencia() {
        Boolean asistio;
        for (BeanModel jugador : grid.getStore().getModels()) {
            asistio = jugador.get("asistio");
            asistio = asistio != null && asistio;
            if (!asistio && jugador.get("falto") == null) {

                MessageBox.alert("Error!", "Debe especificar por que no asistio el jugador " + jugador.get("label"), null);
                return false;
            }
        }
        return true;
    }

    private boolean validarFalto() {
        Boolean asistio;
        String falto;
        for (BeanModel jugador : grid.getStore().getModels()) {
            asistio = jugador.get("asistio");
            falto = jugador.get("falto");
            asistio = asistio != null && asistio;
            if (asistio && jugador.get("falto") != null) {

                MessageBox.alert("Error!", "Debe deschulear la asistencia para el jugador " + jugador.get("label") + " ya que esta marcado como " + jugador.get("falto"), null);
                return false;
            }
        }
        return true;
    }

    public void guardarGridAsistencia(Long idControlAsistencia) {

//        System.out.println(store.getModels().get(0).get("idDeportista"));
        List<String[]> lista = new ArrayList<String[]>();
        for (BeanModel model : store.getModels()) {

            Asistencia asistencia = new Asistencia();
            asistencia.setId((Long) model.get("id"));
            asistencia.setEstado(model.get("estado").toString());
            asistencia.setId_control_asistencia(new ControlAsistencia(idControlAsistencia));
            asistencia.setEstado(model.get("estado").toString());
            asistencia.setIdDeportista(new Deportista((Long) model.get("idDeportista.id")));
            getServiceAsistencia().guardarEntidad(asistencia, new AsyncCallback<RespuestaRPC<Asistencia>>() {

                @Override
                public void onFailure(Throwable caught) {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }

                @Override
                public void onSuccess(RespuestaRPC<Asistencia> result) {
                    throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                }
            });
        }
        store.commitChanges();
        limpiarCampos();
        Info.display("Guardar", "Se guardó correctamente la planilla de asistencia");
        panel2.unmask();
    }

    public void limpiarCampos() {
        cp.setHeading("");
        IdCategoriaElegida = null;
        consultarPlanillaasistencia = false;
        store.removeAll();
        store.removeAll();
        cargar(null);
        panelFormularioControlAsistencia.limpiar();
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

    public void cargar(Long idCat) {
        IdCategoriaElegida = idCat;
        loader.load(0, 100);
        loader.load(0, 100);
    }

    public void cargar() {
        loader.load(0, 100);
        loader.load(0, 100);
    }

    public RPCAdminControlAsistenciaAsync getServiceControlAsistencia() {
        RPCAdminControlAsistenciaAsync svc = (RPCAdminControlAsistenciaAsync) GWT.create(RPCAdminControlAsistencia.class);
        ServiceDefTarget endpoint = (ServiceDefTarget) svc;
        endpoint.setServiceEntryPoint("services/RPCAdminControlAsistencia");
        return svc;
    }

    public RPCAdminAsistenciaAsync getServiceAsistencia() {
        RPCAdminAsistenciaAsync sv = (RPCAdminAsistenciaAsync) GWT.create(RPCAdminAsistencia.class);
        ServiceDefTarget endpoint = (ServiceDefTarget) sv;
        endpoint.setServiceEntryPoint("services/RPCAdminAsistencia");
        return sv;
    }

    public void fechaAsistencia(String fecha) {

        cp.setHeading("Asistencia  <b>" + fecha.toUpperCase() + "</b>");
    }

    protected SelectionListener<ButtonEvent> listenerBuscar() {
        return new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {

                wBuscar.show();

            }
        };

    }

    public LayoutContainer crearGriControlAsistencia() {

        LayoutContainer lcPanelBuscarCompetencia = new LayoutContainer();
        lcPanelBuscarCompetencia.setScrollMode(Style.Scroll.AUTOY);
        lcPanelBuscarCompetencia.setLayoutData(new FillLayout());
        lcPanelBuscarCompetencia.setLayout(new RowLayout(Style.Orientation.VERTICAL));

        final RPCAdminControlAsistenciaAsync svc = (RPCAdminControlAsistenciaAsync) GWT.create(RPCAdminControlAsistencia.class);
        ServiceDefTarget endpoint = (ServiceDefTarget) svc;
        endpoint.setServiceEntryPoint("services/RPCAdminControlAsistencia");

        //Valido que el servicio RPC este activo.
        if (svc == null) {
            MessageBox box = new MessageBox();
            box.setButtons(MessageBox.OK);
            box.setIcon(MessageBox.INFO);
            box.setTitle("Lesiones");
            box.setMessage("No se ha detectado ningun servicio RPC");
            box.show();
//            return;
        }
        //Llamo el servicio RPC que se usara como proxy para cargar los datos de la entidad indicada
        RpcProxy<PagingLoadResult<DTOControlAsistencia>> proxy = new RpcProxy<PagingLoadResult<DTOControlAsistencia>>() {
            @Override
            protected void load(Object loadConfig, AsyncCallback<PagingLoadResult<DTOControlAsistencia>> callback) {
                svc.obtenerCtlAsistenciaFiltro(DtFechaInicial.getValue(), DtFechaFinal.getValue(), idCategoria, actividad, callback);
            }
        };

        loaderControlAsistencia = new BasePagingLoader<PagingLoadResult<ModelData>>(proxy, new BeanModelReader()) {
            @Override
            protected Object newLoadConfig() {
                BasePagingLoadConfig config = new BaseFilterPagingLoadConfig();
                return config;
            }
        };
        loaderControlAsistencia.setRemoteSort(true);

        storeCtrlAsistencia = new ListStore<ControlAsistencia>(loaderControlAsistencia);
//        store.setMonitorChanges(true);

        PgtoolBar.bind(loaderControlAsistencia);
//        
        List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

        ColumnConfig column = new ColumnConfig();
        column.setId("fecha");
        column.setAlignment(Style.HorizontalAlignment.LEFT);
        column.setHeader("Fecha ");
        column.setWidth(100);
        column.setDateTimeFormat(DateTimeFormat.getFormat("EEEE dd MMMM yyyy hh:mm aa"));
        configs.add(column);

        column = new ColumnConfig();
        column.setId("categoria");
        column.setAlignment(Style.HorizontalAlignment.CENTER);
        column.setHeader("Categoría");
        column.setWidth(80);
        column.setRenderer(new GridCellRenderer() {

            @Override
            public Object render(ModelData model, String property, com.extjs.gxt.ui.client.widget.grid.ColumnData config, int rowIndex, int colIndex, ListStore store, Grid grid) {
                String categoria = model.get("categoria");
                if (categoria == null) {
                    return "";
                } else {
                    return categoria;
                }
            }
        });
        configs.add(column);

        column = new ColumnConfig();
        column.setId("actividad");
        column.setAlignment(Style.HorizontalAlignment.CENTER);
        column.setHeader("Actividad");
        column.setWidth(60);
        column.setRenderer(new GridCellRenderer() {

            @Override
            public Object render(ModelData model, String property, com.extjs.gxt.ui.client.widget.grid.ColumnData config, int rowIndex, int colIndex, ListStore store, Grid grid) {
                String actividad = model.get("actividad");
                if (actividad == null) {
                    return "";
                } else {
                    return actividad;
                }
            }
        });
        configs.add(column);

        column = new ColumnConfig();
        column.setId("lugar");
        column.setAlignment(Style.HorizontalAlignment.CENTER);
        column.setHeader("Lugar");
        column.setWidth(100);
        column.setRenderer(new GridCellRenderer() {

            @Override
            public Object render(ModelData model, String property, com.extjs.gxt.ui.client.widget.grid.ColumnData config, int rowIndex, int colIndex, ListStore store, Grid grid) {
                String lugar = model.get("lugar");
                if (lugar == null) {
                    return "";
                } else {
                    return lugar;
                }
            }
        });
        configs.add(column);

        ColumnModel cm = new ColumnModel(configs);

        ContentPanel cpGrid = new ContentPanel();
        cpGrid.setHeaderVisible(false);
        cpGrid.setLayout(new FillLayout(Style.Orientation.VERTICAL));
        cpGrid.setFrame(true);
        cpGrid.setBodyBorder(false);
        cpGrid.setBorders(false);
        cpGrid.setIcon(Resources.ICONS.table());

        ContentPanel cpForm = new ContentPanel();
        cpForm.setHeaderVisible(false);
        cpForm.setLayout(new FillLayout(Style.Orientation.HORIZONTAL));
        cpForm.setFrame(true);
        cpForm.setBodyBorder(false);
        cpForm.setBorders(false);

        FormPanel panel = crearFormularioBusqueda();
        cpForm.add(panel);

        gridCtrlAsistencia = new Grid<ControlAsistencia>(storeCtrlAsistencia, cm);
        gridCtrlAsistencia.setView(new BufferView());

        gridCtrlAsistencia.getSelectionModel().setSelectionMode(Style.SelectionMode.SINGLE);

        /**
         * Escucho cuando se seleciona un movimiento para cargar el formulario
         * manualmente
         */
        gridCtrlAsistencia.getSelectionModel().addListener(Events.SelectionChange, new Listener<SelectionChangedEvent<BeanModel>>() {
            @Override
            public void handleEvent(SelectionChangedEvent<BeanModel> be) {
                // formBindings.unbind();
                if (be.getSelection().size() > 0) {

                    dTOControlAsistencia = (DTOControlAsistencia) be.getSelectedItem().getBean();
                    id = dTOControlAsistencia.getIdPlanillaAsistencia();
                } else {
                    formBindings.unbind();
                }
            }
        });

        gridCtrlAsistencia.addListener(Events.Attach, new Listener<GridEvent<BeanModel>>() {
            @Override
            public void handleEvent(GridEvent<BeanModel> be) {
                PagingLoadConfig config = new BaseFilterPagingLoadConfig();
                config.setOffset(0);
                config.setLimit(50);

                Map<String, Object> state = gridCtrlAsistencia.getState();
                if (state.containsKey("offset")) {
                    int offset = (Integer) state.get("offset");
                    int limit = (Integer) state.get("limit");
                    config.setOffset(offset);
                    config.setLimit(limit);
                }

                loaderControlAsistencia.load(config);
            }
        });
        gridCtrlAsistencia.setLoadMask(true);
        gridCtrlAsistencia.setBorders(true);
        gridCtrlAsistencia.getView().setForceFit(true);

        gridCtrlAsistencia.setStateId("pagingGridExample");
        gridCtrlAsistencia.setStateful(true);

        cpGrid.add(gridCtrlAsistencia);

        lcPanelBuscarCompetencia.add(cpForm, new RowData(1, 0.4, new Margins(0)));
        lcPanelBuscarCompetencia.add(cpGrid, new RowData(1, 0.6, new Margins(0)));

        return lcPanelBuscarCompetencia;
    }

    public FormPanel crearFormularioBusqueda() {

        FormPanel fpControlAsistencia = new FormPanel();
        fpControlAsistencia.setScrollMode(Style.Scroll.AUTO);
        fpControlAsistencia.setFrame(true);
        fpControlAsistencia.setHeaderVisible(false);
//        form.setPadding(5);
        fpControlAsistencia.setSize("100%", "100%");

        // Layout Main que contiene todas las columnas 
        FormData formData = new FormData("-10");
        LayoutContainer main = new LayoutContainer();
        main.setLayout(new ColumnLayout());
        // main.setHeight(100);
        main.setAutoHeight(true);
        ///////////////////// Columna 1 ////////////////////////////  
        LayoutContainer Columna1 = new LayoutContainer();
        Columna1.setStyleAttribute("padding", "10px");

        FormLayout layout = new FormLayout();
        layout.setLabelAlign(FormPanel.LabelAlign.TOP);
        Columna1.setLayout(layout);

        DtFechaInicial.setName("fechaInicial");
        DtFechaInicial.setValue(new Date());
        DtFechaInicial.setFieldLabel("<font color='red'>*</font> Fecha Inicial");
        DtFechaInicial.setAllowBlank(false);
        Columna1.add(DtFechaInicial, formData);
        DtFechaInicial.setFormatValue(true);
//        DtFecha2.setFormatValue(true);

        Columna1.add(DtFechaFinal, formData);
        DtFechaFinal.setName("fechaFinal");
        DtFechaFinal.setValue(new Date());
        DtFechaFinal.setFieldLabel("<font color='red'>*</font> Fecha Final");
        DtFechaFinal.setAllowBlank(false);
//        DtFecha2.setFormatValue(true);
        Columna1.add(DtFechaFinal, formData);

        ///////////////////// Columna 2 //////////////////////////// 
        LayoutContainer Columna2 = new LayoutContainer();
        Columna2.setStyleAttribute("padding", "10px");
        layout = new FormLayout();
        layout.setLabelAlign(FormPanel.LabelAlign.TOP);
        Columna2.setLayout(layout);

        cbxCategoria2.setEditable(false);
//        cbxCategoria2.setLabelSeparator("<font color='red'>*</font> Categor&iacute;a");
        cbxCategoria2.setLabelSeparator("Categor&iacute;a");
        cbxCategoria2.setForceSelection(true);
//        cbxCategoria2.setAllowBlank(false);
        Columna2.add(cbxCategoria2, formData);

        rdEntrenamiento.setBoxLabel("Entrenamiento");
        rdEntrenamiento.setValue(true);

        rdCompetencia.setBoxLabel("Competencia");
//        rdCompetencia.setValue(true);

        radioGroup.setFieldLabel("<font color='red'>*</font> Actividad");
        radioGroup.add(rdCompetencia);
        radioGroup.add(rdEntrenamiento);
        Columna2.add(radioGroup, formData);

        main.add(Columna1, new com.extjs.gxt.ui.client.widget.layout.ColumnData(.5));
        main.add(Columna2, new com.extjs.gxt.ui.client.widget.layout.ColumnData(.5));

        fpControlAsistencia.add(main, new FormData("100%"));

        Button btnBuscarCompetencia = new Button("Buscar", new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                //Comparo las dos fechas para saber si la fecha inicio es mayor
                //a la fecha fin, si es 1 es por que la fecha es mayor, si es -1 es por es menor.
                int fechaMayor = DtFechaInicial.getValue().compareTo(DtFechaFinal.getValue());
                if (fechaMayor > 0) {
                    MessageBox.alert("Error", "La fecha inicial no puede ser mayor a la fecha final", null);
                } else {
                    if (cbxCategoria2.getValue() != null) {
                        idCategoria = cbxCategoria2.getCategoriaElegida().getId();
                    } else {
                        idCategoria = null;
                    }
                    if (rdEntrenamiento.getValue()) {
                        actividad = "Entrenamiento";
                    } else {
                        actividad = "Competencia";
                    }
                    cargarGridCtrolAsistencia();
                }
            }
        });
        Button btnLimpiarForCompetencia = new Button("Limpiar", new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                wBuscar.hide();
                limpiarCamposBusqueda();
            }
        });

        bindingFormFiltros = new FormButtonBinding(fpControlAsistencia);
        bindingFormFiltros.addButton(btnBuscarCompetencia);
        fpControlAsistencia.addButton(btnBuscarCompetencia);
        fpControlAsistencia.addButton(btnLimpiarForCompetencia);
        fpControlAsistencia.setButtonAlign(Style.HorizontalAlignment.CENTER);

        return fpControlAsistencia;

    }

    public void limpiarCamposBusqueda() {
        cbxCategoria2.reset();
        cbxCategoria2.recargar();
        idCategoria = null;
        actividad = "Entrenamiento";
        rdEntrenamiento.setValue(true);
        DtFechaInicial.setValue(new Date());
        DtFechaFinal.setValue(new Date());
        cargarGridCtrolAsistencia();

    }

    public void cargarGridCtrolAsistencia() {
        try {
            loaderControlAsistencia.load(0, 100);
            loaderControlAsistencia.load(0, 100);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public FormPanel crearPanelReporteAsistencia() {
        
        FormPanel panel = new FormPanel();
        
        String [] meses={"Enero","Febrero","Marzo","Abril","Mayo","Junio",
            "Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"};

        ComboBox a = new ComboBox();
        a.setStore(store);
        
        SimpleComboBox<String> comboAnios = new SimpleComboBox<String>();

        comboAnios.setName("anio"); 
        comboAnios.setToolTip(new ToolTipConfig("Años", "Elija un año"));
        comboAnios.setFieldLabel("Año");
        comboAnios.setAllowBlank(false);
        
        panel.add(comboAnios);

        comboAnios.setForceSelection(true);
        comboAnios.setTriggerAction(ComboBox.TriggerAction.ALL);
        Date anio = new Date();
        Integer anioActual = Integer.parseInt(Formatos.anio(anio));
        for (int i = 1990; i <= anioActual; i++) {
            comboAnios.add("" + i);
        }
        comboAnios.setSimpleValue("2015");
        
        
        
      return panel;
    }

}
