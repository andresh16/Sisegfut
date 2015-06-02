/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.client.administracion.ControlAsistencia;

import static co.com.sisegfut.client.administracion.deportista.PanelInfoGeneral.ACTIVOS;
import co.com.sisegfut.client.datos.dominio.ControlAsistencia;
import co.com.sisegfut.client.datos.dominio.Deportista;
import co.com.sisegfut.client.datos.dominio.Usuarios;
import co.com.sisegfut.client.entidades.RespuestaRPC;
import co.com.sisegfut.client.util.BeansLocales;
import co.com.sisegfut.client.util.Resources;
import co.com.sisegfut.client.util.combox.ComboBoxCategoria;
import co.com.sisegfut.client.util.rpc.RPCAdminAsistencia;
import co.com.sisegfut.client.util.rpc.RPCAdminAsistenciaAsync;
import co.com.sisegfut.client.util.rpc.RPCAdminControlAsistencia;
import co.com.sisegfut.client.util.rpc.RPCAdminControlAsistenciaAsync;
import co.com.sisegfut.client.util.rpc.RPCAdminDeportista;
import co.com.sisegfut.client.util.rpc.RPCAdminDeportistaAsync;
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
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.ToolButton;
import com.extjs.gxt.ui.client.widget.form.CheckBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.FormButtonBinding;
import com.extjs.gxt.ui.client.widget.form.SimpleComboBox;
import com.extjs.gxt.ui.client.widget.grid.CellEditor;
import com.extjs.gxt.ui.client.widget.grid.CheckColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.EditorGrid;
import com.extjs.gxt.ui.client.widget.grid.filters.GridFilters;
import com.extjs.gxt.ui.client.widget.grid.filters.StringFilter;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.FillLayout;
import com.extjs.gxt.ui.client.widget.layout.RowData;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.extjs.gxt.ui.client.widget.tips.ToolTipConfig;
import com.extjs.gxt.ui.client.widget.toolbar.PagingToolBar;
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
public class PanelAdminControlAsistencia extends LayoutContainer {

    private FormBinding formBindings;
    private PagingLoader<PagingLoadResult<ModelData>> loader;

    Long id = 1l;
    protected MessageBox boxWait;
    private Usuarios usuarioLogeado;
    //Creo el toolbar de paginacion de el grid
    final PagingToolBar PgtoolBar = new PagingToolBar(50);
    private Deportista dep;
    BorderLayoutData dataWest;
    BorderLayoutData dataCenter;
    ContentPanel panel2;
    private ComboBoxCategoria cbxCategoria;
    RpcProxy<PagingLoadResult<Deportista>> proxy;
    ListStore<BeanModel> store;
    private EditorGrid<BeanModel> grid;
    private PanelFormularioControlAsistencia panelFormularioControlAsistencia;
    ContentPanel cp;
    private Long IdCategoriaElegida;

    @Override
    protected void onRender(Element parent, int index) {
        super.onRender(parent, index);

        BorderLayout layout = new BorderLayout();
        setLayout(layout);

//        setScrollMode(Style.Scroll.AUTOY);
        cp = new ContentPanel();
        final ContentPanel cp2 = new ContentPanel();
        cp.setScrollMode(Style.Scroll.AUTO);

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
        proxy = new RpcProxy<PagingLoadResult<Deportista>>() {
            @Override
            protected void load(Object loadConfig, AsyncCallback<PagingLoadResult<Deportista>> callback) {
                svc.getDeportistaxCategoria(IdCategoriaElegida, callback);
            }

        };

        loader = new BasePagingLoader<PagingLoadResult<ModelData>>(proxy, new BeanModelReader()) {
            @Override
            protected Object newLoadConfig() {
                BasePagingLoadConfig config = new BaseFilterPagingLoadConfig();
                return config;
            }

            @Override
            protected void onLoadSuccess(Object loadConfig, PagingLoadResult<ModelData> result) {

                for (ModelData jug : result.getData()) {
                    jug.set("asistio", true);
                }

                super.onLoadSuccess(loadConfig, result); //To change body of generated methods, choose Tools | Templates.

            }

        };
        loader.setRemoteSort(false);

        store = new ListStore<BeanModel>(loader);
        store.setMonitorChanges(true);

        PgtoolBar.bind(loader);
        //Configuro las columnas de la tabla
        List<ColumnConfig> columns = new ArrayList<ColumnConfig>();
        CheckBox checkBox = new CheckBox();

//        ColumnConfig documento = new ColumnConfig("documento", "Documento", 50);
//        documento.setDateTimeFormat(DateTimeFormat.getFormat("MM/dd/yyyy"));
//        columns.add(documento);
        CheckColumnConfig checkColumn = new CheckColumnConfig("asistio", "Asistio?", 30);
        CellEditor checkBoxEditor = new CellEditor(checkBox);
        checkColumn.setEditor(checkBoxEditor);
        columns.add(checkColumn);
        columns.add(new ColumnConfig("documento", "Documento ", 60));
        columns.add(new ColumnConfig("label", "Nombre completo ", 120));
        columns.add(new ColumnConfig("telefono", "Télefono", 50));
        columns.add(new ColumnConfig("posicion.nombrePosicion", "Posición", 50));

        final SimpleComboBox<String> comboFalto = new SimpleComboBox<String>();
        comboFalto.setForceSelection(true);
        comboFalto.setTriggerAction(ComboBox.TriggerAction.ALL);
        comboFalto.add("No Asiste");
        comboFalto.add("Lesionado");
        comboFalto.add("Permiso");
        comboFalto.add("Tarjeta ROJA");

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

        ColumnConfig columnfalto = new ColumnConfig();
        columnfalto.setId("falto");
        columnfalto.setHeader("Si falto?");
        columnfalto.setAlignment(Style.HorizontalAlignment.CENTER);
        columnfalto.setWidth(70);
        columnfalto.setEditor(editor);
        columns.add(columnfalto);

//        columns.add(new ColumnConfig("categoria.nombrecategoria", "Categoria", 50));
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
//        ListFilter listFilter = new ListFilter("categoria.nombrecategoria", typeStore);
//        listFilter.setDisplayProperty("categoria.nombrecategoria");
//        //StringFilter documentoFilter = new StringFilter("documento");
        filters.addFilter(nombreFilter);

//        filters.addFilter(listFilter);
        //filters.addFilter(documentoFilter);
        grid = new EditorGrid<BeanModel>(store, cm);
        grid.addPlugin(checkColumn);
//        grid.addPlugin(columnfalto);

        grid.getSelectionModel().setSelectionMode(Style.SelectionMode.SINGLE);

        /**
         * Escucho cuando se seleciona un deportista para cargar el formulario
         * manualmente
         */
        grid.getSelectionModel().addListener(Events.SelectionChange, new Listener<SelectionChangedEvent<BeanModel>>() {
            @Override
            public void handleEvent(SelectionChangedEvent<BeanModel> be) {
                // formBindings.unbind();
                if (be.getSelection().size() > 0) {

//          
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
//        panel2.setCollapsible(true);

//        cp.setTopComponent(toolBar);
        cp.setHeading("Deportistas");
//        cp2.add(toolBar, new FlowData(0));
//        cp.setLayout(new RowLayout(Orientation.HORIZONTAL));
        cp.setCollapsible(true);
        dataWest = new BorderLayoutData(Style.LayoutRegion.WEST);
        dataWest.setMargins(new Margins(0, 0, 0, 0));
        dataWest.setSplit(true);
        panel2.add(cp2, new RowData(0.4, 1, new Margins(0)));

//        formBindings = new FormBinding(panel, true);
        cp2.add(panelFormularioControlAsistencia);
        //cp2.setHeading("Perfil Deportista");
        cp2.setHeaderVisible(false);
        cp2.setLayout(new FillLayout(Style.Orientation.VERTICAL));
        cp2.setCollapsible(true);
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

        panel2.getHeader().addTool(new ToolButton("x-tool-help", new SelectionListener<IconButtonEvent>() {
            @Override
            public void componentSelected(IconButtonEvent ce) {
                abrirVentana("Control de asistencia");
            }
        }));

    }

    protected SelectionListener<ButtonEvent> listenerLimpiar() {
        return new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                limpiar();

            }
        };

    }

    protected SelectionListener<ButtonEvent> listenerGuardar() {
        return new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {

                if (panelFormularioControlAsistencia.isValid()) {
//                     panel2.mask("Guardando..");
                    if (!validarXY()) {
                        //mostrar mensaje
                        return;
                    }

                    getServiceControlAsistencia().guardarEntidad(panelFormularioControlAsistencia.ObtenerFormulario(new ControlAsistencia()), new AsyncCallback<RespuestaRPC<ControlAsistencia>>() {

                        @Override
                        public void onFailure(Throwable caught) {
                            MessageBox.alert("Error!", "No guardo Grid de asistencia", null);
                        }

                        @Override
                        public void onSuccess(RespuestaRPC<ControlAsistencia> result) {
                            Info.display("Exito!", "Se guardo correctamente el control de asistencia");
                            guardarGridAsistencia(result.getObjetoRespuesta().getId());
//                       limpiar();
                        }
                    });
                }
////                
//              
            }
        };

    }

    public void limpiar() {
        panelFormularioControlAsistencia.limpiar();
        id = null;
        store.removeAll();
        store.removeAll();

    }

    private boolean validarXY() {
        Boolean asistio;
        for (BeanModel jugador : grid.getStore().getModels()) {
            asistio = jugador.get("asistio");
            asistio = asistio != null && asistio;
            if (!asistio && jugador.get("falto") == null) {
                
                        MessageBox.alert("Error!", "Debe especificar por que no asistio el jugador "+jugador.get("label"), null);
                return false;
            }
        }
        return true;
    }

    public void guardarGridAsistencia(Long idControlAsistencia) {

//        System.out.println(store.getModels().get(0).get("idDeportista"));
        System.out.println(store.getModels().get(0).get("falto"));
        List<String[]> lista = new ArrayList<String[]>();
        for (BeanModel model : store.getModels()) {
            String[] vector = new String[3];

            vector[0] = model.get("id").toString();
            Boolean asistio = model.get("asistio", false);
            String asist;
            if (asistio == true) {
                asist = "true";
            } else {
                asist = "false";
            }
            vector[1] = asist;
            vector[2] = model.get("falto", "");
            lista.add(vector);
            System.out.println(lista.size());
        }
        getServiceAsistencia().guardarGridAsistencia(idControlAsistencia, lista, new AsyncCallback<Void>() {

            @Override
            public void onFailure(Throwable caught) {
                panel2.unmask();
                MessageBox.alert("Error!", "No guardo Grid de asistencia \n" + caught, null);
                panel2.unmask();
            }

            @Override
            public void onSuccess(Void result) {
                store.commitChanges();
                Info.display("Guardar", "Se guardo correctamente el grid de asistencia");

            }

        });
        panel2.unmask();

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
        loader.load(0, 1000);
        loader.load(0, 1000);
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
}
