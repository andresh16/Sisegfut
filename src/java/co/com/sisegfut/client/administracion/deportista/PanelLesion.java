/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.client.administracion.deportista;

import co.com.sisegfut.client.aaI18N.Main;
import co.com.sisegfut.client.datos.dominio.Lesiones;
import co.com.sisegfut.client.datos.dominio.Deportista;
import co.com.sisegfut.client.datos.dominio.dto.DTOLesiones;
import co.com.sisegfut.client.entidades.RespuestaRPC;
import co.com.sisegfut.client.util.Formatos;
import co.com.sisegfut.client.util.Resources;
import co.com.sisegfut.client.util.rpc.RPCAdminLesion;
import co.com.sisegfut.client.util.rpc.RPCAdminLesionAsync;
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
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.GridEvent;
import com.extjs.gxt.ui.client.event.IconButtonEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
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
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.DateField;
import com.extjs.gxt.ui.client.widget.form.FormButtonBinding;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.SimpleComboBox;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.grid.BufferView;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.extjs.gxt.ui.client.widget.layout.ColumnData;
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
public class PanelLesion extends LayoutContainer {
    
    private Long idLesion;
    private Long idDeportista;
    private FormBinding formBindings;
    private PagingLoader<PagingLoadResult<ModelData>> loader;
    ListStore<Lesiones> store;
    Grid<Lesiones> grid;
    final PagingToolBar PgtoolBar = new PagingToolBar(50);
    private DTOLesiones dTOLesiones;
    private SimpleComboBox<String> comboAnios = new SimpleComboBox<String>();
    FormPanel form = new FormPanel();
    TextField<String> txtLesion = new TextField<String>();
    DateField dtFechaLesion = new DateField();
    private Main myConstants = (Main) GWT.create(Main.class);
    
    @Override
    protected void onRender(Element parent, int index) {
        super.onRender(parent, index);
        setScrollMode(Style.Scroll.AUTOY);
        setLayoutData(new FillLayout());
        setLayout(new RowLayout(Style.Orientation.VERTICAL));
        
        final RPCAdminLesionAsync svc = (RPCAdminLesionAsync) GWT.create(RPCAdminLesion.class);
        ServiceDefTarget endpoint = (ServiceDefTarget) svc;
        endpoint.setServiceEntryPoint("services/RPCAdminLesion");

        //Valido que el servicio RPC este activo.
        if (svc == null) {
            MessageBox box = new MessageBox();
            box.setButtons(MessageBox.OK);
            box.setIcon(MessageBox.INFO);
            box.setTitle("Lesiones");
            box.setMessage("No se ha detectado ningun servicio RPC");
            box.show();
            return;
        }
        //Llamo el servicio RPC que se usara como proxy para cargar los datos de la entidad indicada
        RpcProxy<PagingLoadResult<DTOLesiones>> proxy = new RpcProxy<PagingLoadResult<DTOLesiones>>() {
            @Override
            protected void load(Object loadConfig, AsyncCallback<PagingLoadResult<DTOLesiones>> callback) {
                svc.getLesion(idDeportista, callback);
            }
        };
        
        loader = new BasePagingLoader<PagingLoadResult<ModelData>>(proxy, new BeanModelReader()) {
            @Override
            protected Object newLoadConfig() {
                BasePagingLoadConfig config = new BaseFilterPagingLoadConfig();
                return config;
            }
        };
        loader.setRemoteSort(true);
        
        store = new ListStore<Lesiones>(loader);
//        store.setMonitorChanges(true);

        PgtoolBar.bind(loader);
//        
        List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
        
        ColumnConfig column = new ColumnConfig();
        column.setId("fechaLesion");
        column.setAlignment(Style.HorizontalAlignment.LEFT);
        column.setHeader("Fecha lesión");
        column.setWidth(100);
        column.setDateTimeFormat(DateTimeFormat.getFormat("dd MMMM yyyy"));
        configs.add(column);
        
        column = new ColumnConfig();
        column.setId("nombreLesion");
        column.setAlignment(Style.HorizontalAlignment.CENTER);
        column.setHeader("Lesión");
        column.setWidth(380);
        column.setRenderer(new GridCellRenderer() {
            
            @Override
            public Object render(ModelData model, String property, com.extjs.gxt.ui.client.widget.grid.ColumnData config, int rowIndex, int colIndex, ListStore store, Grid grid) {
                String lesion = model.get("nombreLesion");
                if (lesion == null) {
                    return "";
                } else {
                    return lesion;
                }
            }
        });
        configs.add(column);
        
        ColumnModel cm = new ColumnModel(configs);
        
        ContentPanel cpGrid = new ContentPanel();
        cpGrid.setHeaderVisible(false);
//      cpGrid.setLayout(new RowLayout(Style.Orientation.VERTICAL));
        cpGrid.setLayout(new FillLayout(Style.Orientation.VERTICAL));
        cpGrid.setFrame(true);
        cpGrid.setBodyBorder(false);
        cpGrid.setBorders(false);
        cpGrid.setIcon(Resources.ICONS.table());
        
        ContentPanel cpForm = new ContentPanel();
        cpForm.setHeaderVisible(true);
//        cpForm.setLayout(new RowLayout(Style.Orientation.VERTICAL));
        cpForm.setLayout(new FillLayout(Style.Orientation.HORIZONTAL));
        cpForm.setFrame(true);
        cpForm.setBodyBorder(false);
        cpForm.setBorders(false);
        
        cpForm.getHeader().addTool(new ToolButton("x-tool-help", new SelectionListener<IconButtonEvent>() {
            @Override
            public void componentSelected(IconButtonEvent ce) {
                abrirVentana(myConstants.ayudaPanelDeportistaAntecedentesOsteomusculares());
            }
        }));
        
        comboAnios.setForceSelection(true);
        comboAnios.setTriggerAction(ComboBox.TriggerAction.ALL);
        Date anio = new Date();
        Integer anioActual = Integer.parseInt(Formatos.anio(anio));
        for (int i = 1990; i <= anioActual; i++) {
            comboAnios.add("" + i);
        }
        
        Button btnGuardar = new Button("Guardar", listenerGuardar());
        btnGuardar.setIcon(Resources.ICONS.iconoNuevaNota());
        
        Button btnEliminar = new Button("Eliminar", listenerEliminar());
        btnEliminar.setIcon(Resources.ICONS.iconoEliminar());
        
        Button btnLimpiar = new Button("Limpiar", listenerLimpiar());
        btnLimpiar.setIcon(Resources.ICONS.iconoRefrescar());
        
        FormPanel panel = crearFormulario();
//        panel.setLayout(new FillLayout(Style.Orientation.HORIZONTAL));
        cpForm.add(panel);
        
        FormButtonBinding binding = new FormButtonBinding(panel);
        binding.addButton(btnGuardar);
        
        grid = new Grid<Lesiones>(store, cm);
        grid.setView(new BufferView());
        
        grid.getSelectionModel().setSelectionMode(Style.SelectionMode.SINGLE);

        /**
         * Escucho cuando se seleciona un movimiento para cargar el formulario
         * manualmente
         */
        grid.getSelectionModel().addListener(Events.SelectionChange, new Listener<SelectionChangedEvent<BeanModel>>() {
            @Override
            public void handleEvent(SelectionChangedEvent<BeanModel> be) {
                // formBindings.unbind();
                if (be.getSelection().size() > 0) {
                    
                    dTOLesiones = (DTOLesiones) be.getSelectedItem().getBean();
                    
                    idLesion = dTOLesiones.getIdLesion();
                    txtLesion.setValue(dTOLesiones.getNombreLesion());
                    dtFechaLesion.setValue(dTOLesiones.getFechaLesion());
//                    
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
        grid.getView().setForceFit(true);
        
        grid.setStateId("pagingGridExample");
        grid.setStateful(true);
        
        cpGrid.add(grid);
        ToolBar toolBar = new ToolBar();
        
        btnGuardar.addSelectionListener(new SelectionListener<ButtonEvent>() {
            
            @Override
            public void componentSelected(ButtonEvent ce) {
                
            }
            
        });
        toolBar.add(btnGuardar);
        toolBar.add(btnEliminar);
        toolBar.add(btnLimpiar);
        
        cpForm.setTopComponent(toolBar);
        
        add(cpForm, new RowData(1, 0.25, new Margins(0)));
        add(cpGrid, new RowData(1, 0.75, new Margins(0)));
        
    }
    
    public void setId(Long id) {
        this.idDeportista = id;
        cargar();
    }
    
    public void cargar() {
        try {
            loader.load(0, 100);
            loader.load(0, 100);
        } catch (Exception e) {
            e.printStackTrace();
        }

//        loader.load(0, 50);
    }
    
    protected SelectionListener<ButtonEvent> listenerGuardar() {
        return new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                
                Lesiones lesiones = new Lesiones();
                lesiones.setId(idLesion);
                lesiones.setNombreLesion(txtLesion.getValue().toUpperCase());
                lesiones.setFechaLesion(dtFechaLesion.getValue());
                lesiones.setIdDeportista(new Deportista(idDeportista));
                getServiceLesion().guardarEntidad(lesiones, new AsyncCallback<RespuestaRPC<Lesiones>>() {
                    
                    @Override
                    public void onFailure(Throwable caught) {
                        Info.display("Guardar", "No Guardo la lesion ");
                    }
                    
                    @Override
                    public void onSuccess(RespuestaRPC<Lesiones> result) {
                        Info.display("Guardar", "Guardo correctamente la lesion ");
                        Limpiar();
                    }
                });
            }
        };
        
    }
    
    protected SelectionListener<ButtonEvent> listenerEliminar() {
        return new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                if (idDeportista != null) {
                    if (idLesion != null) {
                        MessageBox.confirm("Confirmación", "Seguro quiere eliminar la lesión " + txtLesion.getValue(), new Listener<MessageBoxEvent>() {
                            @Override
                            public void handleEvent(MessageBoxEvent be) {
                                
                                String respb = be.getButtonClicked().getText();
                                
                                if (respb.equalsIgnoreCase("yes") || respb.equalsIgnoreCase("sí")) {
                                    
                                    getServiceLesion().eliminarEntidad(idLesion, new AsyncCallback<RespuestaRPC<Lesiones>>() {
                                        
                                        @Override
                                        public void onFailure(Throwable caught) {
                                            Info.display("Eliminar", "No eliminó la lesión  " + txtLesion.getValue());
                                        }
                                        
                                        @Override
                                        public void onSuccess(RespuestaRPC<Lesiones> result) {
                                            Info.display("Eliminar", "Se eliminó la lesión  " + txtLesion.getValue());
                                            Limpiar();
                                        }
                                    });
                                    
                                }
                                
                            }
                        });
                    } else {
                        MessageBox.alert("Advertencia!", "Debe seleccionar una lesión", null);
                    }
                } else {
                    MessageBox.alert("Advertencia!", "Debe seleccionar un deportista", null);
                }
            }
        };
        
    }
    
    protected SelectionListener<ButtonEvent> listenerLimpiar() {
        return new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                Limpiar();
            }
        };
        
    }
    
    public void Limpiar() {
        form.reset();
        idLesion = null;
        cargar();
        
    }
    
    public void limpiarDeportista() {
        form.reset();
        idLesion = null;
        idDeportista=null;
        cargar();
    }
    
    public FormPanel crearFormulario() {
        form.setScrollMode(Style.Scroll.AUTOX);
        form.setScrollMode(Style.Scroll.AUTOY);
        form.setFrame(true);
        form.setHeaderVisible(false);
        
        FormData formData = new FormData("0");
        LayoutContainer main = new LayoutContainer();
        main.setLayout(new ColumnLayout());
        // main.setHeight(100);
        main.setAutoHeight(afterRender);

        /////////////////// Columna 1 ////////////////////////////  
        LayoutContainer Columna1 = new LayoutContainer();
        Columna1.setStyleAttribute("paddingRight", "0px");
        
        FormLayout layout = new FormLayout();
        layout.setLabelAlign(FormPanel.LabelAlign.RIGHT);
        Columna1.setLayout(layout);
        
        dtFechaLesion.setName("fechaLesion");
        dtFechaLesion.setAllowBlank(false);
        dtFechaLesion.setMaxValue(new Date());//corregir se debe sumar un dia
        dtFechaLesion.setFieldLabel("Fecha Lesión");
        Columna1.add(dtFechaLesion, formData);

        ///////////////////// Columna 2 //////////////////////////// 
        LayoutContainer Columna2 = new LayoutContainer();
        Columna2.setStyleAttribute("paddingLeft", "0px");
        layout = new FormLayout();
        layout.setLabelAlign(FormPanel.LabelAlign.RIGHT);
        Columna2.setLayout(layout);
        
        txtLesion.setName("nombreLesion");
        txtLesion.setFieldLabel("Lesión");
        txtLesion.setEmptyText("");
        txtLesion.setAllowBlank(false);
        Columna2.add(txtLesion, formData);
        
        main.add(Columna1, new ColumnData(.5));
        main.add(Columna2, new ColumnData(.5));
        
        form.add(main, new FormData("100%"));
        
        return form;
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
    
    public RPCAdminLesionAsync getServiceLesion() {
        RPCAdminLesionAsync svc = (RPCAdminLesionAsync) GWT.create(RPCAdminLesion.class);
        ServiceDefTarget endpoint = (ServiceDefTarget) svc;
        endpoint.setServiceEntryPoint("services/RPCAdminLesion");
        return svc;
    }
    
}
