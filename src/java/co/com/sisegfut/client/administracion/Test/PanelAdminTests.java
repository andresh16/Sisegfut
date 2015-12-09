/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package co.com.sisegfut.client.administracion.Test;

import static co.com.sisegfut.client.administracion.deportista.PanelInfoGeneral.ACTIVOS;
import co.com.sisegfut.client.datos.dominio.Deportista;
import co.com.sisegfut.client.datos.dominio.Usuarios;
import co.com.sisegfut.client.util.BeansLocales;
import co.com.sisegfut.client.util.Resources;
import co.com.sisegfut.client.util.combox.ComboBoxCategoria;
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
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.BoxComponentEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.GridEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.filters.GridFilters;
import com.extjs.gxt.ui.client.widget.grid.filters.NumericFilter;
import com.extjs.gxt.ui.client.widget.grid.filters.StringFilter;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.FillLayout;
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
 * @author Andres Hurtado
 */
public class PanelAdminTests extends LayoutContainer {

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
    private PanelAdminPestaniasTest panelAdminPestañasTest;
     private Long IdCategoriaElegida= null;


    @Override
    protected void onRender(Element parent, int index) {
        super.onRender(parent, index);

        BorderLayout layout = new BorderLayout();
        setLayout(layout);

//        setScrollMode(Style.Scroll.AUTOY);
        final ContentPanel cp = new ContentPanel();
        final ContentPanel cp2 = new ContentPanel();
        cp.setScrollMode(Style.Scroll.AUTO);
        
        cbxCategoria = new ComboBoxCategoria(ACTIVOS);
        cbxCategoria.setName("categoria.nombrecategoria");
        cbxCategoria.setToolTip(new ToolTipConfig("Categoría", "Filtrar por categoría"));
        cbxCategoria.setFieldLabel("<font color='red'>*</font> Categoría");
        cbxCategoria.setAllowBlank(false);
        //  cbxCategoria.setEditable(false);
        
         Button btnLimpiar = new Button("Limpiar", listenerLimpiar());
        btnLimpiar.setIcon(Resources.ICONS.iconoRefrescar());
       

//        setLayout(new FillLayout(Orientation.VERTICAL));
        ToolBar toolBar = new ToolBar();
        
        toolBar.setSpacing(2);
        
        toolBar.add(cbxCategoria);
        toolBar.add(new SeparatorToolItem());
        toolBar.add(btnLimpiar);
        
        cbxCategoria.addListener(Events.SelectionChange, new Listener<BaseEvent>() {
            @Override
            public void handleEvent(BaseEvent be) {
                IdCategoriaElegida=cbxCategoria.getCategoriaElegida().getId();
                cargar();
            }
        });
        panelAdminPestañasTest = new PanelAdminPestaniasTest();

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
//                FilterPagingLoadConfig f = (FilterPagingLoadConfig) loadConfig;
//                f.setSortDir(null);
//                svc.getConsulta(f, callback);
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
        //Configuro las columnas de la tabla
        List<ColumnConfig> columns = new ArrayList<ColumnConfig>();

//        ColumnConfig documento = new ColumnConfig("documento", "Documento", 50);
//        documento.setDateTimeFormat(DateTimeFormat.getFormat("MM/dd/yyyy"));
//        columns.add(documento);
        columns.add(new ColumnConfig("documento", "Documento ", 60));
        columns.add(new ColumnConfig("label", "Nombre completo ", 120));

        columns.add(new ColumnConfig("posicion.nombrePosicion", "Posición", 50));

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
        NumericFilter numberfilter = new NumericFilter("peso");  
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
        filters.addFilter(numberfilter);
        
        
//        filters.addFilter(listFilter);
        
        //filters.addFilter(documentoFilter);

        final Grid<BeanModel> grid = new Grid<BeanModel>(store, cm);

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
                    

                    dep = (Deportista) be.getSelectedItem().getBean();
                    panelAdminPestañasTest.recibirEntidad(dep);

//                    idDeportista = mov.getId();
//                    DtFecha.setValue(mov.getFechamov());
//                    cbxCuenta.seleccionar(mov.getNcuenta().getId());
////                    cbxCuentaTranferir.seleccionar(mov.getNcuenta_trans().getId());
//                    cbxTipoMovimiento.seleccionar(mov.getTipomov().getId());
//                    cbxCategoria.seleccionar(mov.getCategoria().getId());
//
//                    if (mov.getEgreso() != 0) {
//                        txtEgreso.setValue(mov.getEgreso().toString());
//                    } else {
//                        txtIngreso.setValue(mov.getIngreso().toString());
//                    }
//                    txtDescripcion.setValue(mov.getDescripcion());
                    // formBindings.bind((ModelData) be.getSelection().get(0));
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
        cp.setLayout(new FillLayout(Style.Orientation.VERTICAL));

        cp.setBottomComponent(PgtoolBar);
        grid.setStateId("pagingGridExample");
        grid.setStateful(true);
       
        //cp.add(grid, new RowData(.5, 1));
        cp.add(grid);

        panel2 = new ContentPanel();
        panel2.setLayout(new RowLayout(Style.Orientation.HORIZONTAL));
        panel2.setSize(500, 500);
        panel2.setFrame(true);
        panel2.setHeaderVisible(false);
//        panel2.setCollapsible(true);

        cp.setTopComponent(toolBar);
        cp.setHeading("Tabla deportista");
//        cp2.add(toolBar, new FlowData(0));
//        cp.setLayout(new RowLayout(Orientation.HORIZONTAL));
        cp.setCollapsible(true);
        dataWest = new BorderLayoutData(Style.LayoutRegion.WEST);
        dataWest.setMargins(new Margins(0, 0, 0, 0));
        dataWest.setSplit(true);
        panel2.add(cp, new RowData(0.25, 1, new Margins(0)));

//        formBindings = new FormBinding(panel, true);
        cp2.add(panelAdminPestañasTest);
        //cp2.setHeading("Perfil Deportista");
        cp2.setHeaderVisible(false);
        cp2.setLayout(new FillLayout(Style.Orientation.VERTICAL));
        cp2.setCollapsible(true);
        dataCenter = new BorderLayoutData(Style.LayoutRegion.CENTER);
        panel2.add(cp2, new RowData(0.75, 1, new Margins(0)));

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
//        loader.load(0, 50);
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
                System.out.println("Ingreso al limpiar");
                IdCategoriaElegida=null;
                cbxCategoria.recargar();
                cargar();

            }
        };
    }
 

   

    public RPCAdminDeportistaAsync getServiceDeportista() {
        RPCAdminDeportistaAsync svc = (RPCAdminDeportistaAsync) GWT.create(RPCAdminDeportista.class);
        ServiceDefTarget endpoint = (ServiceDefTarget) svc;
        endpoint.setServiceEntryPoint("services/RPCAdminDeportista");
        return svc;
    }

}
