/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.client.administracion.Competencia;

import co.com.sisegfut.client.aaI18N.Main;
import co.com.sisegfut.client.datos.dominio.Competencia;
import co.com.sisegfut.client.datos.dominio.CuerpoTecnicoCompe;
import co.com.sisegfut.client.datos.dominio.Personal;
import co.com.sisegfut.client.entidades.RespuestaRPC;
import co.com.sisegfut.client.util.Resources;
import co.com.sisegfut.client.util.combox.ComboBoxPersonalCompetencia;
import co.com.sisegfut.client.util.rpc.RPCAdminCuerpoTecComp;
import co.com.sisegfut.client.util.rpc.RPCAdminCuerpoTecCompAsync;
import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.data.BaseFilterPagingLoadConfig;
import com.extjs.gxt.ui.client.data.BasePagingLoadConfig;
import com.extjs.gxt.ui.client.data.BasePagingLoader;
import com.extjs.gxt.ui.client.data.BeanModel;
import com.extjs.gxt.ui.client.data.BeanModelReader;
import com.extjs.gxt.ui.client.data.ModelData;
import com.extjs.gxt.ui.client.data.PagingLoadResult;
import com.extjs.gxt.ui.client.data.PagingLoader;
import com.extjs.gxt.ui.client.data.RpcProxy;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.IconButtonEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.Label;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.ToolButton;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.filters.GridFilters;
import com.extjs.gxt.ui.client.widget.grid.filters.StringFilter;
import com.extjs.gxt.ui.client.widget.layout.FillLayout;
import com.extjs.gxt.ui.client.widget.tips.ToolTipConfig;
import com.extjs.gxt.ui.client.widget.toolbar.SeparatorToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fhurtado
 */
public class PanelAdminCuerpoTecnico extends ContentPanel {

    private Grid<BeanModel> gridCuerpoTecnico;
    private PagingLoader<PagingLoadResult<ModelData>> loaderCuerpoTecComp;
    private Button btnAgregar;
    private Button btnEliminar;
    public ComboBoxPersonalCompetencia cbxPersonal;
    private Main myConstants;
    private ToolBar toolBar;
    private Long idCompetencia;
    private Long idPersonalCuerpoTec = null;

    public PanelAdminCuerpoTecnico() {
        
        
        setBodyBorder(true);
        setScrollMode(Style.Scroll.AUTO);
        setIcon(Resources.ICONS.table());
        setHeaderVisible(true);
        setHeading("Cuerpo Técnico");
        setLayout(new FillLayout());
//        form.setPadding(5);
        setSize("100%", "100%");
        ToolButton btnayudaCompetencia = new ToolButton("x-tool-help", new SelectionListener<IconButtonEvent>() {
            @Override
            public void componentSelected(IconButtonEvent ce) {
                abrirVentanaAyuda(myConstants.ayudaPanelCompetenciaCuerpoTecnico());
            }
        });
        btnayudaCompetencia.setTitle("Ayuda ");
        getHeader().addTool(btnayudaCompetencia);

        Label lbseleccione = new Label();
        lbseleccione.setText("<b>Seleccione un personal: </b>");

        btnAgregar = new Button("Agregar", listenerAgregarCuerpoTecnico());
        btnAgregar.setIcon(Resources.ICONS.iconoNuevaNota());
//        btnAgregar.setEnabled(false);

        btnEliminar = new Button("", listenerEliminarCuerpoTecnico());
        btnEliminar.setIcon(Resources.ICONS.iconoEliminar());
//        btnAgregar.setEnabled(false);

        cbxPersonal = new ComboBoxPersonalCompetencia();
        cbxPersonal.setName("label");
        cbxPersonal.setToolTip(new ToolTipConfig("Personal", "Seleccione un personal"));
        cbxPersonal.setFieldLabel("<b>Personal</b>");
        cbxPersonal.setAllowBlank(false);

//        cbxPersonal.addListener(Events.SelectionChange, new Listener<BaseEvent>() {
//            @Override
//            public void handleEvent(BaseEvent be) {
//                if (!cbxPersonal.getPersonaElegida().getLabel().equalsIgnoreCase("") || !cbxPersonal.getPersonaElegida().getLabel().isEmpty()) {
//                    btnAgregar.setEnabled(true);
////                    cbxPersonal.setEditable(false);
//                }
//            }
//        });
        toolBar = new ToolBar();
        toolBar.setSpacing(2);

        toolBar.add(lbseleccione);
        toolBar.add(new SeparatorToolItem());
        toolBar.add(cbxPersonal);
        toolBar.add(new SeparatorToolItem());
        toolBar.add(btnAgregar);
        toolBar.add(new SeparatorToolItem());
        toolBar.add(btnEliminar);
        setTopComponent(toolBar);

        final RPCAdminCuerpoTecCompAsync svc = (RPCAdminCuerpoTecCompAsync) GWT.create(RPCAdminCuerpoTecComp.class);
        ServiceDefTarget endpoint = (ServiceDefTarget) svc;
        endpoint.setServiceEntryPoint("services/RPCAdminCuerpoTecComp");

        //Llamo el servicio RPC que se usara como proxy para cargarGridJugadores los datos de la entidad indicada
        RpcProxy<PagingLoadResult<Personal>> proxy = new RpcProxy<PagingLoadResult<Personal>>() {
            @Override
            protected void load(Object loadConfig, AsyncCallback<PagingLoadResult<Personal>> callback) {
                if (idCompetencia != null) {
                    svc.getCuerpoTecnicoXCompetencia(idCompetencia, callback);
                } else {
                    svc.getCuerpoTecnicoXCompetencia(0L, callback);
                }
            }
        };

        loaderCuerpoTecComp = new BasePagingLoader<PagingLoadResult<ModelData>>(proxy, new BeanModelReader()) {
            @Override
            protected Object newLoadConfig() {
                BasePagingLoadConfig config = new BaseFilterPagingLoadConfig();
                return config;
            }
        };
        loaderCuerpoTecComp.setRemoteSort(false);

        ListStore<BeanModel> storeCuerpoTec = new ListStore<BeanModel>(loaderCuerpoTecComp);

        //Configuro las columnas de la tabla
        List<ColumnConfig> columns = new ArrayList<ColumnConfig>();
        columns.add(new ColumnConfig("label", "Nombre completo ", 100));
        columns.add(new ColumnConfig("cargo.nombrecargo", "Cargo", 50));

        ColumnModel cm = new ColumnModel(columns);
        GridFilters filters = new GridFilters();

        StringFilter nombreFilter = new StringFilter("label");

        filters.addFilter(nombreFilter);

        gridCuerpoTecnico = new Grid<BeanModel>(storeCuerpoTec, cm);

        gridCuerpoTecnico.getSelectionModel().setSelectionMode(Style.SelectionMode.SINGLE);

        gridCuerpoTecnico.getSelectionModel().addListener(Events.SelectionChange, new Listener<SelectionChangedEvent<BeanModel>>() {
            @Override
            public void handleEvent(SelectionChangedEvent<BeanModel> be) {
                // formBindings.unbind();
                if (be.getSelection().size() > 0) {

                    Personal p = (Personal) be.getSelectedItem().getBean();

                    idPersonalCuerpoTec = p.getId();

                    Info.display("Personal", "Selecciono el personal " + p.getNombreCompleto() + " idpersonal" + idPersonalCuerpoTec);

                } else {
                }
            }
        });

        gridCuerpoTecnico.setLoadMask(true);
        gridCuerpoTecnico.setBorders(true);
//        gridCuerpoTecnico.addPlugin(filters);
        gridCuerpoTecnico.getView().setForceFit(true);
        gridCuerpoTecnico.setTrackMouseOver(false);
        add(gridCuerpoTecnico);

    }

    public void setIdCompetencia(Long idCompetencia) {
        this.idCompetencia = idCompetencia;
    }
    

    protected SelectionListener<ButtonEvent> listenerAgregarCuerpoTecnico() {
        return new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                if (cbxPersonal.getPersonaElegida() != null) {
                    CuerpoTecnicoCompe cuerpoTecnicoCompe = new CuerpoTecnicoCompe();
                    cuerpoTecnicoCompe.setIdCompetencia(new Competencia(idCompetencia));
                    cuerpoTecnicoCompe.setIdPersonal(cbxPersonal.getPersonaElegida());
                    if (idCompetencia != null) {
                        getServiceCuerpoTecComp().guardarEntidad(cuerpoTecnicoCompe, new AsyncCallback<RespuestaRPC<CuerpoTecnicoCompe>>() {

                            @Override
                            public void onFailure(Throwable caught) {
                                MessageBox.alert("Alerta", "No fue posible guardar el personal ", null);
                            }

                            @Override
                            public void onSuccess(RespuestaRPC<CuerpoTecnicoCompe> result) {
                                cbxPersonal.clear();
                                cbxPersonal.recargar();
                                cargarGridCuerpoTecComp();
                            }
                        });

                    }
                } else {
                    MessageBox.alert("Alerta", "Debe seleccionar un personal para agregar: ", null);
                }
            }
        };

    }

    protected SelectionListener<ButtonEvent> listenerEliminarCuerpoTecnico() {
        return new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {

                if (idPersonalCuerpoTec != null) {
                    getServiceCuerpoTecComp().eliminarCuerpoTecComp(idCompetencia, idPersonalCuerpoTec, new AsyncCallback<Void>() {

                        @Override
                        public void onFailure(Throwable caught) {
                            MessageBox.alert("Alerta", "No fue posible eliminar el personal", null);
                        }

                        @Override
                        public void onSuccess(Void result) {
                            Info.display("Elimino", "Se eliminó correctamente el personal seleccionado");
                            cargarGridCuerpoTecComp();
                        }
                    });

                } else {
                    MessageBox.alert("Alerta", "Seleccione un personal de la tabla de cuerpo técnico ", null);
                }
            }
        };

    }

    /**
     * Abre ventana de ayuda.
     */
    private void abrirVentanaAyuda(String texto) {
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

    public RPCAdminCuerpoTecCompAsync getServiceCuerpoTecComp() {
        RPCAdminCuerpoTecCompAsync svc = (RPCAdminCuerpoTecCompAsync) GWT.create(RPCAdminCuerpoTecComp.class);
        ServiceDefTarget endpoint = (ServiceDefTarget) svc;
        endpoint.setServiceEntryPoint("services/RPCAdminCuerpoTecComp");
        return svc;
    }

    public void cargarGridCuerpoTecComp() {
        gridCuerpoTecnico.getStore().removeAll();
        loaderCuerpoTecComp.load(0, 50);
        loaderCuerpoTecComp.load(0, 50);
    }
    
    public void cargarCuerpoTecnicoCompetencia(Long IdCompetencia, boolean habilitar){
        setIdCompetencia(idCompetencia);
        cargarGridCuerpoTecComp();
        btnAgregar.setEnabled(habilitar);
        btnEliminar.setEnabled(habilitar);
        cbxPersonal.setIdCompetencia(IdCompetencia);
        cbxPersonal.recargar();
        
    }

}
