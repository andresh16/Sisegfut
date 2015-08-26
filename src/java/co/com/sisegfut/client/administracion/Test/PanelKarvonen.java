/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.client.administracion.Test;

import co.com.sisegfut.client.aaI18N.Main;
import co.com.sisegfut.client.datos.dominio.Deportista;
import co.com.sisegfut.client.datos.dominio.TestKarvonen;
import co.com.sisegfut.client.datos.dominio.dto.DTOTestKarvonen;
import co.com.sisegfut.client.entidades.RespuestaRPC;
import co.com.sisegfut.client.util.Resources;
import co.com.sisegfut.client.util.rpc.RPCAdminDeportista;
import co.com.sisegfut.client.util.rpc.RPCAdminDeportistaAsync;
import co.com.sisegfut.client.util.rpc.RPCAdminTestKarvonen;
import co.com.sisegfut.client.util.rpc.RPCAdminTestKarvonenAsync;
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
import com.extjs.gxt.ui.client.util.Padding;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.Label;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.ToolButton;
import com.extjs.gxt.ui.client.widget.form.FormButtonBinding;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.extjs.gxt.ui.client.widget.grid.BufferView;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.extjs.gxt.ui.client.widget.layout.BoxLayout;
import com.extjs.gxt.ui.client.widget.layout.ColumnData;
import com.extjs.gxt.ui.client.widget.layout.ColumnLayout;
import com.extjs.gxt.ui.client.widget.layout.FillLayout;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.extjs.gxt.ui.client.widget.layout.HBoxLayout;
import com.extjs.gxt.ui.client.widget.layout.RowData;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.extjs.gxt.ui.client.widget.toolbar.PagingToolBar;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.Image;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Andres Hurtado
 */
public class PanelKarvonen extends LayoutContainer {

    FormPanel panel = new FormPanel();
    private FormBinding formBindings;
    NumberField txtFCReposo = new NumberField();
    NumberField txtPorcentaje = new NumberField();
    private PagingLoader<PagingLoadResult<ModelData>> loader;
    ListStore<TestKarvonen> store;
    Grid<TestKarvonen> grid;
    final PagingToolBar PgtoolBar = new PagingToolBar(50);
    Long idDeportista = null;
    private Image foto = new Image();
    FormPanel form = new FormPanel();
    private String fcm;

    ContentPanel contenedorFotoUsuario;

    Label lbNombreDep = new Label();

    private Long idKarvonen;
    private DTOTestKarvonen testKarvonen = new DTOTestKarvonen();
    /**
     * Contiene los textos a presentar en la interfaz web segun el
     * idDeportistaioma
     */
    private Main myConstants;

    @Override
    protected void onRender(Element parent, int index) {
        super.onRender(parent, index);
        setScrollMode(Style.Scroll.AUTOY);
        setLayoutData(new FillLayout());
        setLayout(new RowLayout(Style.Orientation.VERTICAL));

        final RPCAdminTestKarvonenAsync svc = (RPCAdminTestKarvonenAsync) GWT.create(RPCAdminTestKarvonen.class);
        ServiceDefTarget endpoint = (ServiceDefTarget) svc;
        endpoint.setServiceEntryPoint("services/RPCAdminTestKarvonen");

        //Valido que el servicio RPC este activo.
        if (svc == null) {
            MessageBox box = new MessageBox();
            box.setButtons(MessageBox.OK);
            box.setIcon(MessageBox.INFO);
            box.setTitle("Control tecnico");
            box.setMessage("No se ha detectado ningun servicio RPC");
            box.show();
            return;
        }
        //Llamo el servicio RPC que se usara como proxy para cargar los datos de la entidad indicada
        RpcProxy<PagingLoadResult<DTOTestKarvonen>> proxy = new RpcProxy<PagingLoadResult<DTOTestKarvonen>>() {

            @Override
            protected void load(Object loadConfig, AsyncCallback<PagingLoadResult<DTOTestKarvonen>> callback) {
                svc.getTestKarvonen(idDeportista, callback);
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

        store = new ListStore<TestKarvonen>(loader);
//        store.setMonitorChanges(true);

        PgtoolBar.bind(loader);
//        
        List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

        ColumnConfig column = new ColumnConfig();
        column.setId("fecha");
        column.setAlignment(Style.HorizontalAlignment.LEFT);
        column.setHeader("Fecha");
        column.setWidth(100);
        column.setDateTimeFormat(DateTimeFormat.getFormat("dd MMMM yyyy"));

        configs.add(column);

        column = new ColumnConfig();
        column.setId("fcReposo");
        column.setHeader("Frecuencia Cardiaca Reposo");
        column.setWidth(50);
        column.setAlignment(Style.HorizontalAlignment.LEFT);
        configs.add(column);
        column.setRenderer(new GridCellRenderer() {

            @Override
            public Object render(ModelData model, String property, com.extjs.gxt.ui.client.widget.grid.ColumnData config, int rowIndex, int colIndex, ListStore store, Grid grid) {
                String fcReposo = model.get("fcReposo");
                if (fcReposo == null) {
                    return "";
                } else {
                    return fcReposo;
                }
            }
        });

        column = new ColumnConfig();
        column.setId("porcentaje");
        column.setHeader("Trabajo %");
        column.setWidth(50);
        column.setAlignment(Style.HorizontalAlignment.LEFT);
        configs.add(column);
        column.setRenderer(new GridCellRenderer() {

            @Override
            public Object render(ModelData model, String property, com.extjs.gxt.ui.client.widget.grid.ColumnData config, int rowIndex, int colIndex, ListStore store, Grid grid) {
                String porcentaje = model.get("porcentaje");
                if (porcentaje == null) {
                    return "";
                } else {
                    return porcentaje;
                }
            }
        });
        column = new ColumnConfig();
        column.setId("resKarvonen");
        column.setHeader("Resultado Test Karvonen");
        column.setAlignment(Style.HorizontalAlignment.LEFT);
        column.setWidth(50);
        configs.add(column);
        column.setRenderer(new GridCellRenderer() {

            @Override
            public Object render(ModelData model, String property, com.extjs.gxt.ui.client.widget.grid.ColumnData config, int rowIndex, int colIndex, ListStore store, Grid grid) {
                String resKarvonen = model.get("resKarvonen");
                if (resKarvonen == null) {
                    return "";
                } else {
                    return resKarvonen;
                }
            }
        });

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
        cpForm.setHeaderVisible(false);
//        cpForm.setLayout(new RowLayout(Style.Orientation.VERTICAL));
        cpForm.setLayout(new FillLayout(Style.Orientation.HORIZONTAL));
        cpForm.setFrame(true);
        cpForm.setBodyBorder(false);
        cpForm.setBorders(false);

        Button btnGuardar = new Button(" Guardar", listenerGuardar());
        btnGuardar.setIcon(Resources.ICONS.iconoGuardar());

        Button btnLimpiar = new Button("Nuevo", listenerLimpiar());
        btnLimpiar.setIcon(Resources.ICONS.iconoNuevaCita());
        
        Button btnEliminar = new Button("Eliminar", listenerEliminar());
        btnEliminar.setIcon(Resources.ICONS.iconoEliminar());

        Button btnReporte = new Button("Histórico", ListenerGenerarReporte());
        btnReporte.setIcon(Resources.ICONS.iconoPDF());
        
        FormPanel panel = crearFormulario();
//        panel.setLayout(new FillLayout(Style.Orientation.HORIZONTAL));
        cpForm.add(panel);

        FormButtonBinding binding = new FormButtonBinding(panel);
        binding.addButton(btnGuardar);

        grid = new Grid<TestKarvonen>(store, cm);
        grid.setView(new BufferView());

        grid.getSelectionModel().setSelectionMode(Style.SelectionMode.SINGLE);
        
        
         grid.getSelectionModel().addListener(Events.SelectionChange, new Listener<SelectionChangedEvent<BeanModel>>() {
            @Override
            public void handleEvent(SelectionChangedEvent<BeanModel> be) {
                // formBindings.unbind();
                if (be.getSelection().size() > 0) {

                    testKarvonen = (DTOTestKarvonen) be.getSelectedItem().getBean();

                    idKarvonen = testKarvonen.getIdKarvonen();
                    
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
        cpGrid.getHeader().addTool(new ToolButton("x-tool-help", new SelectionListener<IconButtonEvent>() {
            @Override
            public void componentSelected(IconButtonEvent ce) {
//                abrirVentana("Guarda eps");
            }
        }));

        toolBar.add(btnGuardar);
        toolBar.add(btnEliminar);
        toolBar.add(btnLimpiar);
        toolBar.add(btnReporte);
        toolBar.add(lbNombreDep);

        cpForm.setTopComponent(toolBar);

        add(cpForm, new RowData(1, 0.5, new Margins(0)));
        add(cpGrid, new RowData(1, 0.5, new Margins(0)));

        if (idDeportista != null) {
            cargarfoto(idDeportista);
        } else {
            cargarFotoNoDiposible();
        }
    }

    public void setId(Long id) {
        this.idDeportista = id;
        cargarfoto(idDeportista);
        cargar();
    }

    public FormPanel crearFormulario() {
        form.setScrollMode(Style.Scroll.AUTOX);
        form.setScrollMode(Style.Scroll.AUTOY);
        form.setFrame(true);
        form.setHeaderVisible(false);

        // Layout Main que contiene todas las columnas 
        FormData formData = new FormData("-50");
        LayoutContainer main = new LayoutContainer();
        main.setLayout(new ColumnLayout());
        // main.setHeight(100);
        main.setAutoHeight(afterRender);
//        form.add(lbNombreDep);

        /////////////////////// Foto /////////////////////////////////////////////
        LayoutContainer LayoutFoto = new LayoutContainer();
        LayoutFoto.setStyleAttribute("paddingRight", "10px");

        HBoxLayout HBlayout = new HBoxLayout();
        HBlayout.setPadding(new Padding(5));
        HBlayout.setHBoxLayoutAlign(HBoxLayout.HBoxLayoutAlign.TOP);
        HBlayout.setPack(BoxLayout.BoxLayoutPack.CENTER);
        LayoutFoto.setLayout(HBlayout);

        contenedorFotoUsuario = new ContentPanel();
        contenedorFotoUsuario.setHeaderVisible(false);
        contenedorFotoUsuario.setVisible(true);
//        contenedorFotoUsuario.setToolTip("La foto debe tener un tamaño de 4x4");

        foto.setWidth("150px");
        foto.setHeight("160px");

        contenedorFotoUsuario.add(foto);
//        LayoutFoto.add(contenedorFotoUsuario);
//        add(LayoutFoto, new FormData("100%"));

        ///////////////////// Columna 1 ////////////////////////////  
        LayoutContainer Columna1 = new LayoutContainer();
        Columna1.setStyleAttribute("paddingRight", "10px");

        FormLayout layout = new FormLayout();
        layout.setLabelAlign(FormPanel.LabelAlign.TOP);
        Columna1.setLayout(layout);

        txtFCReposo.setName("fcReposo");
        txtFCReposo.setFieldLabel("<font color='red'>*</font>Frecuencia cardiaca en reposo");
        txtFCReposo.setRegex("^[.0-9]*$");
        txtFCReposo.setEmptyText("");
        txtFCReposo.setMaxLength(3);
        txtFCReposo.setAllowBlank(false);
        txtFCReposo.getMessages().setRegexText("El campo no puede contener letras ni caracteres especiales .");
//        txtDocumento.setEnabled(false);
        Columna1.add(txtFCReposo, formData);

        txtPorcentaje.setName("porcentaje");
        txtPorcentaje.setAllowBlank(false);
        txtPorcentaje.setFieldLabel("<font color='red'>*</font>Porcentaje de trabajo %");
        txtPorcentaje.setRegex("^[.0-9]*$");
        txtPorcentaje.setEmptyText("");
        txtPorcentaje.setMaxLength(3);
        txtPorcentaje.getMessages().setRegexText("El campo no puede contener letras ni caracteres especiales .");
//        txtDireccion.setEnabled(false);
        Columna1.add(txtPorcentaje, formData);

        ///////////////////// Columna 2 //////////////////////////// 
        LayoutContainer Columna2 = new LayoutContainer();
        Columna2.setStyleAttribute("paddingLeft", "0px");
        layout = new FormLayout();
        layout.setLabelAlign(FormPanel.LabelAlign.TOP);

        Columna2.setLayout(layout);

        Columna2.add(contenedorFotoUsuario);

        main.add(Columna1, new ColumnData(.5));
        main.add(Columna2, new ColumnData(.5));

        form.add(main, new FormData("100%"));

        return form;
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

                if (idDeportista != null) {
                    if (panel.isValid()) {

                        guardar(obtenerDatosFormulario(calcularKarvonen(txtFCReposo.getValue().intValue(), Integer.parseInt(fcm), txtPorcentaje.getValue().intValue())));
                    } else {
                        MessageBox.alert("Error", "Debe diligenciar todos los campos", null);
                    }

                } else {
                    MessageBox.alert("Advertencia!", "Debe seleccionar un deportista", null);
                }

            }
        };

    }
    
    
    protected SelectionListener<ButtonEvent> listenerEliminar() {
        return new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                if (idDeportista != null) {

                    if (idKarvonen != null) {
                        MessageBox.confirm("Confirmación", "Seguro quiere eliminar el test de karvonen", new Listener<MessageBoxEvent>() {
                            @Override
                            public void handleEvent(MessageBoxEvent be) {

                                String respb = be.getButtonClicked().getText();

                                if (respb.equalsIgnoreCase("yes") || respb.equalsIgnoreCase("sí")) {

                                    getServiceTestKarvonen().eliminarEntidad(idKarvonen, new AsyncCallback<RespuestaRPC<TestKarvonen>>() {

                                        @Override
                                        public void onFailure(Throwable caught) {
                                            Info.display("Eliminar", "No Elimino el test de karvonen" + idKarvonen);
                                        }

                                        @Override
                                        public void onSuccess(RespuestaRPC<TestKarvonen> result) {
                                            Info.display("Eliminar", "Se eliminó correctamente el test de karvonen");
                                            limpiar();
                                        }
                                    });

                                }

                            }
                        });
                    } else {
                        MessageBox.alert("Advertencia!", "Debe seleccionar un test de karvonen a eliminar", null);
                    }
                } else {
                    MessageBox.alert("Advertencia!", "Debe seleccionar un deportista", null);
                }
            }
        };
    }

    public void calcularFCM(Date fechaNacimientoDep) {
        getServiceDeportista().CalcularFCM(fechaNacimientoDep, new AsyncCallback<String>() {

            @Override
            public void onFailure(Throwable caught) {
                fcm = "";
            }

            @Override
            public void onSuccess(String result) {
                fcm = result;
            }
        });

    }

    public void guardar(TestKarvonen testKarvonen) {
        getServiceTestKarvonen().guardarEntidad(testKarvonen, new AsyncCallback<RespuestaRPC<TestKarvonen>>() {

            @Override
            public void onFailure(Throwable caught) {

                MessageBox.alert("Error", "No guardo el onrol técnico", null);
            }

            @Override
            public void onSuccess(RespuestaRPC<TestKarvonen> result) {
                Info.display("Guardar", "Guardo correctamente el test de karvonen ");
                cargar();
                form.reset();
            }
        });

    }
    
    public SelectionListener<ButtonEvent> ListenerGenerarReporte() {
        return new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {

                if (idDeportista != null) {

                    String base = GWT.getModuleBaseURL() + "../html/reportes/HistoricoTestKarvonen/";
                    // deportista seleccionado
                    redireccionarA(base + idDeportista);

                } else {
                    MessageBox.alert("Alerta", "Debe seleccionar primero un deportista", null);
                }

            }
        };
    }

    public TestKarvonen obtenerDatosFormulario(String resultadoKarvonen) {

        TestKarvonen testKarvonen = new TestKarvonen();
        testKarvonen.setIdDeportista(new Deportista(idDeportista));
        testKarvonen.setFecha(new Date());
        testKarvonen.setFcReposo(txtFCReposo.getValue().intValue());
        testKarvonen.setPorcentaje(txtPorcentaje.getValue().intValue());
        testKarvonen.setResultadoKarvonen(resultadoKarvonen);

        return testKarvonen;
    }

    public void cargar() {
        try {
            loader.load(0, 1000);
            loader.load(0, 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

  public void limpiar() {
        form.reset();
        idKarvonen = null;
        cargar();
    }
    public void limpiarDeportista() {
        form.reset();
        idKarvonen = null;
        idDeportista=null;
        cargar();
        cargarFotoNoDiposible();
        lbNombreDep.setText("");
    }

    public RPCAdminTestKarvonenAsync getServiceTestKarvonen() {
        RPCAdminTestKarvonenAsync svc = (RPCAdminTestKarvonenAsync) GWT.create(RPCAdminTestKarvonen.class);
        ServiceDefTarget endpoint = (ServiceDefTarget) svc;
        endpoint.setServiceEntryPoint("services/RPCAdminTestKarvonen");
        return svc;
    }

    public RPCAdminDeportistaAsync getServiceDeportista() {
        RPCAdminDeportistaAsync svc = (RPCAdminDeportistaAsync) GWT.create(RPCAdminDeportista.class);
        ServiceDefTarget endpoint = (ServiceDefTarget) svc;
        endpoint.setServiceEntryPoint("services/RPCAdminDeportista");
        return svc;
    }

    public void cargarfoto(Long idUsuario) {
        // Genero un diferenciador para evitar el caché
        Long milis = (new Date()).getTime();

        foto.setUrl(GWT.getHostPageBaseURL() + "html/foto/descargar/" + idUsuario + "?milis=" + milis);

    }

    public void cargarfotoThis() {
        // Genero un diferenciador para evitar el caché
        Long milis = (new Date()).getTime();

        foto.setUrl(GWT.getHostPageBaseURL() + "html/foto/descargar/" + idDeportista + "?milis=" + milis);

    }

    public void cargarFotoNoDiposible() {

        foto.setUrl("imagenes/fotoNoDisponible.jpg");
    }

    public String calcularKarvonen(Integer fcr, Integer fcm, Integer porcentaje) {

        Double resultadoKarvonen = 0.0;
        Double porcent = porcentaje / 100.0;
        resultadoKarvonen = fcr.doubleValue() + porcent * (fcm - fcr);
        Long resultaEntero = Math.round(resultadoKarvonen);
        return resultaEntero.toString();
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
}
