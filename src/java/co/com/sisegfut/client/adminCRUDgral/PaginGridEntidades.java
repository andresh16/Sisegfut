
package co.com.sisegfut.client.adminCRUDgral;

import co.com.sisegfut.client.util.ListenerBuscarEntidad;
import co.com.sisegfut.client.util.Resources;
import co.com.sisegfut.client.util.rpc.RPCMaestroAsync;
import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.data.*;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.GridEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.grid.*;
import com.extjs.gxt.ui.client.widget.grid.filters.GridFilters;
import com.extjs.gxt.ui.client.widget.layout.FitLayout;
import com.extjs.gxt.ui.client.widget.layout.FlowData;
import com.extjs.gxt.ui.client.widget.toolbar.PagingToolBar;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;
import java.io.Serializable;
import java.util.Map;

/**
 *
 * @author fhurtado
 */
public abstract class PaginGridEntidades<T extends Serializable>
        extends LayoutContainer {

    /**
     * El cargador del grid
     */
    private PagingLoader<PagingLoadResult<ModelData>> loader;
    /**
     * Listener para cuendo se de click a un registro de la tabla
     */
    private ListenerBuscarEntidad<T> listenerBuscar;
    
    private RPCMaestroAsync<T> rPCMaestroAsync;
    
    private String titulo;
    
    private ColumnModel columnModel;
    
    private GridFilters filters;

    /**
     * Constructor
     *
     * @param listenerBuscar
     */
    public PaginGridEntidades(ListenerBuscarEntidad<T> listenerBuscar, 
            RPCMaestroAsync<T> rPCMaestroAsync, 
            String titulo) {
        this.listenerBuscar = listenerBuscar;
        this.rPCMaestroAsync = rPCMaestroAsync;
        this.titulo = titulo;
        this.columnModel = crearColumnModel();
        this.filters = crearFilters();
    }

    @Override
    protected void onRender(Element parent, int index) {
        super.onRender(parent, index);
        setLayout(new FitLayout());


        //Valido que el servicio RPC este activo.
        if (rPCMaestroAsync == null) {
            MessageBox box = new MessageBox();
            box.setButtons(MessageBox.OK);
            box.setIcon(MessageBox.INFO);
            box.setTitle("Informacion");
            box.setMessage("No se ha detectado ningun servicio RPC");
            box.show();
            return;
        }

        //Llamo el servicio RPC que se usara como proxy para cargar los datos de la entidad indicada
        RpcProxy<PagingLoadResult<T>> proxy = new RpcProxy<PagingLoadResult<T>>() {

            @Override
            public void load(Object loadConfig, AsyncCallback<PagingLoadResult<T>> callback) {
                rPCMaestroAsync.getConsulta((FilterPagingLoadConfig) loadConfig, callback);
            }
        };
        
        // Instancio el loader
        loader = new BasePagingLoader<PagingLoadResult<ModelData>>(proxy, new BeanModelReader()) {

            @Override
            protected Object newLoadConfig() {
                BasePagingLoadConfig config = new BaseFilterPagingLoadConfig();
                return config;
            }
        };
        loader.setRemoteSort(false);


        ListStore<BeanModel> store = new ListStore<BeanModel>(loader);

        //Creo el toolbar de paginacion de el grid
        final PagingToolBar toolBar = new PagingToolBar(50);
        toolBar.bind(loader);        

        //Instancio el grid que contendra los registros de la entidad
        final Grid<BeanModel> grid = new Grid<BeanModel>(store, columnModel);

        grid.setStateId("pagingGridExample");
        grid.setStateful(true);
        //Agrego el listener para escuchar los eventos de paginacion y reordenacion de el grid
        grid.addListener(Events.Attach, new Listener<GridEvent<BeanModel>>() {

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
                if (state.containsKey("sortField")) {
                    config.setSortField((String) state.get("sortField"));
                    config.setSortDir(Style.SortDir.valueOf((String) state.get("sortDir")));
                }
//                loader.load(config);
                cargar();
            }
        });
        grid.setLoadMask(true);
        grid.setBorders(true);
        //grid.setAutoExpandColumn("nombreCompleto");
        if(filters!=null){
            grid.addPlugin(filters);
        }
        grid.getView().setForceFit(true);

        //Agrego metodos al grid que le permita eleccionar una entidad de este
        grid.getSelectionModel().setSelectionMode(Style.SelectionMode.SINGLE);
        grid.getSelectionModel().addListener(Events.SelectionChange, new Listener<SelectionChangedEvent<BeanModel>>() {

            public void handleEvent(SelectionChangedEvent<BeanModel> be) {
                if (be.getSelection().size() > 0) {
                    BeanModel entidadModel = (be.getSelection().get(0));
                    //Usuarios usuario = (Usuarios)usuarioModel.getBean();
                    //Envio la entidad encontrada al listener
                    listenerBuscar.onFind(entidadModel);
                } else {
                    //Nothing
                }
            }
        });

        //Creo panel contenedor para el grid
        ContentPanel panel = new ContentPanel();
        panel.setFrame(true);
        panel.setCollapsible(false);
        panel.setAnimCollapse(false);
        panel.setIcon(Resources.ICONS.table());
        panel.setHeading(titulo);
        panel.setLayout(new FitLayout());
        panel.add(grid);
        panel.setBottomComponent(toolBar);
        grid.getAriaSupport().setLabelledBy(panel.getId());
        
        add(panel, new FlowData(0));
        cargar();

    }
    
    /**
     * Indica las columnas que va a tener el grid.
     * @return 
     */
    protected abstract ColumnModel crearColumnModel();
    /**
     * Indica los filtros que va a tener el grid si null no se genera filtros.
     * @return 
     */
    protected abstract GridFilters crearFilters();

    /**
     * Carga la tabla web con el contenido de la BD
     */
    public void cargar() {
        loader.load(0, 50);
    }
}
