/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.client.administracion.Test;

import co.com.sisegfut.client.aaI18N.Main;
import co.com.sisegfut.client.datos.dominio.Deportista;
import co.com.sisegfut.client.datos.dominio.TestCooper;
import co.com.sisegfut.client.datos.dominio.TestKarvonen;
import co.com.sisegfut.client.datos.dominio.dto.DTOTestCooper;
import co.com.sisegfut.client.entidades.RespuestaRPC;
import co.com.sisegfut.client.util.Resources;
import co.com.sisegfut.client.util.rpc.RPCAdminDeportista;
import co.com.sisegfut.client.util.rpc.RPCAdminDeportistaAsync;
import co.com.sisegfut.client.util.rpc.RPCAdminTestCooper;
import co.com.sisegfut.client.util.rpc.RPCAdminTestCooperAsync;
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
import com.extjs.gxt.ui.client.widget.Dialog;
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
public class PanelCooper extends LayoutContainer {

    FormPanel panel = new FormPanel();
    private FormBinding formBindings;
    NumberField txtDistancia = new NumberField();
    private PagingLoader<PagingLoadResult<ModelData>> loader;
    ListStore<TestKarvonen> store;
    Grid<TestKarvonen> grid;
    final PagingToolBar PgtoolBar = new PagingToolBar(50);
    Long idDeportista = null;
    private Deportista dep;
    private Image foto = new Image();
    FormPanel form = new FormPanel();
    private Integer pesoDep;
    private Integer edadDep;
    private Long idCooper;
    private DTOTestCooper testCooper = new DTOTestCooper();
    private boolean hombre;

    ContentPanel contenedorFotoUsuario;

    Label lbNombreDep = new Label();

    /**
     * Contiene los textos a presentar en la interfaz web segun el idioma
     */
    private Main myConstants;

    @Override
    protected void onRender(Element parent, int index) {
        super.onRender(parent, index);
        setScrollMode(Style.Scroll.AUTOY);
        setLayoutData(new FillLayout());
        setLayout(new RowLayout(Style.Orientation.VERTICAL));

        final RPCAdminTestCooperAsync svc = (RPCAdminTestCooperAsync) GWT.create(RPCAdminTestCooper.class);
        ServiceDefTarget endpoint = (ServiceDefTarget) svc;
        endpoint.setServiceEntryPoint("services/RPCAdminTestCooper");

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
        RpcProxy<PagingLoadResult<DTOTestCooper>> proxy = new RpcProxy<PagingLoadResult<DTOTestCooper>>() {

            @Override
            protected void load(Object loadConfig, AsyncCallback<PagingLoadResult<DTOTestCooper>> callback) {
                svc.getTestCooper(idDeportista, callback);
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
        column.setWidth(60);
        column.setDateTimeFormat(DateTimeFormat.getFormat("dd MMMM yyyy"));
        configs.add(column);

        column = new ColumnConfig();
        column.setId("condicionFisica");
        column.setHeader("Condición Fisica");
        column.setWidth(50);
        column.setAlignment(Style.HorizontalAlignment.LEFT);
        configs.add(column);
        column.setRenderer(new GridCellRenderer() {

            @Override
            public Object render(ModelData model, String property, com.extjs.gxt.ui.client.widget.grid.ColumnData config, int rowIndex, int colIndex, ListStore store, Grid grid) {
                String condFisica = model.get("condicionFisica");
                if (condFisica == null) {
                    return "";
                } else {
                    String style = "";
                    if (condFisica.equalsIgnoreCase("MUY MALA")) {
                        style = "black";
                    } else if (condFisica.equalsIgnoreCase("MALA")) {
                        style = "red";
                    } else if (condFisica.equalsIgnoreCase("MEDIA")) {
                        style = "orange";
                    } else if (condFisica.equalsIgnoreCase("BUENA")) {
                        style = "green";
                    } else if (condFisica.equalsIgnoreCase("MUY BUENA")) {
                        style = "green";
                    } else if (condFisica.equalsIgnoreCase("EXCELENTE")) {
                        style = "green";
                    }
                    return "<span style='color:" + style + "'><b>" + condFisica + "</b></span>";
                }
            }

        });

        column = new ColumnConfig();
        column.setId("distancia");
        column.setHeader("Distancia Recorrida");
        column.setWidth(50);
        column.setAlignment(Style.HorizontalAlignment.LEFT);
        configs.add(column);
        column.setRenderer(new GridCellRenderer() {

            @Override
            public Object render(ModelData model, String property, com.extjs.gxt.ui.client.widget.grid.ColumnData config, int rowIndex, int colIndex, ListStore store, Grid grid) {
                String distancia = model.get("distancia");
                if (distancia == null) {
                    return "";
                } else {
                    return distancia;
                }
            }
        });

        column = new ColumnConfig();
        column.setId("vo2max");
        column.setHeader("Vo2Max (ml/kg/min)");
        column.setWidth(50);
        column.setAlignment(Style.HorizontalAlignment.LEFT);
        configs.add(column);
        column.setRenderer(new GridCellRenderer() {

            @Override
            public Object render(ModelData model, String property, com.extjs.gxt.ui.client.widget.grid.ColumnData config, int rowIndex, int colIndex, ListStore store, Grid grid) {
                String vo2max = model.get("vo2max");
                if (vo2max == null) {
                    return "";
                } else {
                    return vo2max;
                }
            }
        });

        column = new ColumnConfig();
        column.setId("consumOxigeno");
        column.setHeader("Consumo de Oxigeno Litros/Minutos");
        column.setWidth(50);
        column.setAlignment(Style.HorizontalAlignment.LEFT);
        configs.add(column);
        column.setRenderer(new GridCellRenderer() {

            @Override
            public Object render(ModelData model, String property, com.extjs.gxt.ui.client.widget.grid.ColumnData config, int rowIndex, int colIndex, ListStore store, Grid grid) {
                String consOxigeno = model.get("consumOxigeno");
                if (consOxigeno == null) {
                    return "";
                } else {
                    return consOxigeno;
                }
            }
        });

        column = new ColumnConfig();
        column.setId("velocidad");
        column.setHeader("Velocidad (Km/H)");
        column.setWidth(50);
        column.setAlignment(Style.HorizontalAlignment.LEFT);
        configs.add(column);
        column.setRenderer(new GridCellRenderer() {

            @Override
            public Object render(ModelData model, String property, com.extjs.gxt.ui.client.widget.grid.ColumnData config, int rowIndex, int colIndex, ListStore store, Grid grid) {
                String velocidad = model.get("velocidad");
                if (velocidad == null) {
                    return "";
                } else {
                    return velocidad;
                }
            }
        });

        ColumnModel cm = new ColumnModel(configs);

        ContentPanel cpGrid = new ContentPanel();
        cpGrid.setHeaderVisible(true);
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

                    testCooper = (DTOTestCooper) be.getSelectedItem().getBean();

                    idCooper = testCooper.getIdCooper();

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
                abrirVentana();
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

    protected SelectionListener<ButtonEvent> listenerGuardar() {
        return new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {

                if (idDeportista != null) {
                    if (panel.isValid()) {

                        guardar(obtenerDatosFormulario());
                    } else {
                        MessageBox.alert("Error", "Debe diligenciar todos los campos", null);
                    }
                } else {
                    MessageBox.alert("Advertencia!", "Debe seleccionar un deportista", null);
                }

            }
        };

    }

//    }
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
        idCooper = null;
        cargar();
    }
    public void limpiarDeportista() {
        form.reset();
        idCooper = null;
        idDeportista=null;
        cargar();
        cargarFotoNoDiposible();
        lbNombreDep.setText("");
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

        txtDistancia.setName("distancia");
        txtDistancia.setFieldLabel("<font color='red'>*</font>Distancia recorrida (mt) en 12 minutos");
        txtDistancia.setRegex("^[.0-9]*$");
        txtDistancia.setEmptyText("");
        txtDistancia.setMaxLength(4);
        txtDistancia.setMinLength(4);
        txtDistancia.setMinValue(1000);
        txtDistancia.setAllowBlank(false);
        txtDistancia.getMessages().setRegexText("El campo no puede contener letras ni caracteres especiales .");
//        txtDocumento.setEnabled(false);
        Columna1.add(txtDistancia, formData);

        ///////////////////// Columna 2 //////////////////////////// 
        LayoutContainer Columna2 = new LayoutContainer();
        Columna2.setStyleAttribute("paddingLeft", "0px");
        layout = new FormLayout();
        layout.setLabelAlign(FormPanel.LabelAlign.TOP);

//        VBoxLayout layout2 = new VBoxLayout();  
//        layout2.setPadding(new Padding(20));  
//        layout2.setVBoxLayoutAlign(VBoxLayoutAlign.CENTER);  
//        Columna2.setLayout(layout2); 
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

    protected SelectionListener<ButtonEvent> listenerEliminar() {
        return new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                if (idDeportista != null) {

                    if (idCooper != null) {
                        MessageBox.confirm("Confirmación", "Seguro quiere eliminar el test de cooper selecionado", new Listener<MessageBoxEvent>() {
                            @Override
                            public void handleEvent(MessageBoxEvent be) {

                                String respb = be.getButtonClicked().getText();

                                if (respb.equalsIgnoreCase("yes") || respb.equalsIgnoreCase("sí")) {

                                    getServiceTestCooper().eliminarEntidad(idCooper, new AsyncCallback<RespuestaRPC<TestCooper>>() {

                                        @Override
                                        public void onFailure(Throwable caught) {
                                            Info.display("Eliminar", "No Elimino el test de cooper" + idCooper);
                                        }

                                        @Override
                                        public void onSuccess(RespuestaRPC<TestCooper> result) {
                                            Info.display("Eliminar", "Se eliminó correctamente el test de cooper");
                                            limpiar();
                                        }
                                    });

                                }

                            }
                        });
                    } else {
                        MessageBox.alert("Advertencia!", "Debe seleccionar un test de cooper a eliminar", null);
                    }
                } else {
                    MessageBox.alert("Advertencia!", "Debe seleccionar un deportista", null);
                }
            }
        };

    }
    
    public SelectionListener<ButtonEvent> ListenerGenerarReporte() {
        return new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {

                if (idDeportista != null) {

                    String base = GWT.getModuleBaseURL() + "../html/reportes/HistoricoTestCooper/";
                    // deportista seleccionado
                    redireccionarA(base + idDeportista);

                } else {
                    MessageBox.alert("Alerta", "Debe seleccionar primero un deportista", null);
                }

            }
        };
    }
    public TestCooper obtenerDatosFormulario() {

        TestCooper testCooper = new TestCooper();
        testCooper.setIdDeportista(new Deportista(idDeportista));
        testCooper.setFecha(new Date());
        testCooper.setDistancia(txtDistancia.getValue().intValue());
        testCooper.setCondicionFisica(calcularCondicionFisica(edadDep, txtDistancia.getValue().intValue(), hombre));
        testCooper.setVo2max(calcularVo2Max(txtDistancia.getValue().intValue()).toString());
        testCooper.setConsumOxigeno(calcularConsumoOxigeno(calcularVo2Max(txtDistancia.getValue().intValue())));
        testCooper.setVelocidad(calcularVelocidad(txtDistancia.getValue().intValue()));
        return testCooper;
    }

    public RPCAdminTestCooperAsync getServiceTestCooper() {
        RPCAdminTestCooperAsync svc = (RPCAdminTestCooperAsync) GWT.create(RPCAdminTestCooper.class);
        ServiceDefTarget endpoint = (ServiceDefTarget) svc;
        endpoint.setServiceEntryPoint("services/RPCAdminTestCooper");
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

    public Integer getPeso() {
        return pesoDep;
    }

    public void setPeso(Integer peso) {
        this.pesoDep = peso;
    }

    public Integer getEdadDep() {
        return edadDep;
    }

    public void setEdadDep(Integer edadDep) {
        this.edadDep = edadDep;
    }

    public boolean isHombre() {
        return hombre;
    }

    public void setHombre(boolean hombre) {
        this.hombre = hombre;
    }

    public void calcularEdad(Date fechaNacimiento) {
        getServiceDeportista().CalcularEdad(fechaNacimiento, new AsyncCallback<Integer>() {

            @Override
            public void onFailure(Throwable caught) {
                edadDep = 0;
            }

            @Override
            public void onSuccess(Integer result) {
                edadDep = result;
            }
        });

    }

    public String calcularCondicionFisica(Integer edad, Integer distancia, Boolean hombre) {

        String condicionFisica = "";

        if (hombre) {
            if (edad > 13 && edad < 19) {
                if (distancia <= 2100) {
                    condicionFisica = "MUY MALA";
                } else if (distancia >= 2100 && distancia <= 2199) {
                    condicionFisica = "MALA";
                } else if (distancia >= 2200 && distancia <= 2499) {
                    condicionFisica = "MEDIA";
                } else if (distancia >= 2500 && distancia <= 2749) {
                    condicionFisica = "BUENA";
                } else if (distancia >= 2750 && distancia <= 2999) {
                    condicionFisica = "MUY BUENA";
                } else if (distancia >= 3000) {
                    condicionFisica = "EXCELENTE";
                }

            } else if (edad > 20 && edad < 29) {
                if (distancia <= 1950) {
                    condicionFisica = "MUY MALA";
                } else if (distancia >= 1950 && distancia <= 2099) {
                    condicionFisica = "MALA";
                } else if (distancia >= 2100 && distancia <= 2399) {
                    condicionFisica = "MEDIA";
                } else if (distancia >= 2400 && distancia <= 2649) {
                    condicionFisica = "BUENA";
                } else if (distancia >= 2650 && distancia <= 2849) {
                    condicionFisica = "MUY BUENA";
                } else if (distancia >= 2850) {
                    condicionFisica = "EXCELENTE";
                }

            } else if (edad > 30 && edad < 39) {
                if (distancia <= 1900) {
                    condicionFisica = "MUY MALA";
                } else if (distancia >= 1900 && distancia <= 2099) {
                    condicionFisica = "Mala";
                } else if (distancia >= 2100 && distancia <= 2349) {
                    condicionFisica = "MEDIA";
                } else if (distancia >= 2350 && distancia <= 2499) {
                    condicionFisica = "BUENA";
                } else if (distancia >= 2500 && distancia <= 2699) {
                    condicionFisica = "MUY BUENA";
                } else if (distancia >= 2700) {
                    condicionFisica = "EXCELENTE";
                }
            }
        } else {

        }

        return condicionFisica;
    }

    public void guardar(TestCooper testCooper) {

        getServiceTestCooper().guardarEntidad(testCooper, new AsyncCallback<RespuestaRPC<TestCooper>>() {

            @Override
            public void onFailure(Throwable caught) {

                MessageBox.alert("Error", "No guardo el control técnico", null);
            }

            @Override
            public void onSuccess(RespuestaRPC<TestCooper> result) {
                Info.display("Guardar", "Guardo correctamente el test de cooper ");
                cargar();
                form.reset();
            }
        });

    }

    public String calcularConsumoOxigeno(Double consumoOxigeno) {
        int cifras = (int) Math.pow(10, 2);
        consumoOxigeno = (consumoOxigeno * pesoDep) / 1000;
        consumoOxigeno = Math.rint(consumoOxigeno * cifras) / cifras;
        return consumoOxigeno.toString();
    }

    public Double calcularVo2Max(Integer distancia) {
        int cifras = (int) Math.pow(10, 2);
        Double consumoOxigeno = (distancia.doubleValue() - 504.9) / 44.73;
        consumoOxigeno = Math.rint(consumoOxigeno * cifras) / cifras;
        return consumoOxigeno;
    }

    public String calcularVelocidad(Integer distancia) {
        int cifras = (int) Math.pow(10, 2);
        Double velocidad = (distancia / 1000.0) * 5;
        velocidad = Math.rint(velocidad * cifras) / cifras;
        return velocidad.toString();
    }

    /**
     * Abre ventana de ayuda.
     */
    private void abrirVentana() {

        String texto
                = "	<table cellspacing='10'border='1'  align=\"center\">\n"
                //                + "	<style type=\"text/css\">\n"
                //                + "		table {     font-family: \"Arial\", \"Lucida Grande\"\n"
                //                + "	    font-size: 12px;    margin: 45px;     width: 480px; text-align: left;    border: 1px; }\n"
                //                + "\n"
                //                + "		tr {     font-size: 12px;     font-weight: normal;     padding: 8px;     background: #b9c9fe;\n"
                //                + "	    border-top: 4px solid #aabcfe;    border-bottom: 1px solid #fff;  border: 1px;}\n"
                //                + "\n"
                //                + "		td {    padding: 8px;     background: #e8edff;     border-bottom: 1px solid #fff;\n"
                //                + "	    color: #669;    border-top: 1px solid transparent; border: 1px }\n"
                //                + "\n"
                //                + "		tr:hover td { background: #d0dafd; color: #339; }\n"
                //                + "	</style>\n"
                + "		<tr>\n"
                + "			<caption colspan=\"7\"><center><b>Tabla de resultados del Test de Cooper</b></center></caption>\n"
                + "		</tr>\n"
                + "		<tr>\n"
                + "			<td colspan=\"7\"><center><b>Hombres (12 Minutos)</b></center></td>\n"
                + "		</tr>\n"
                + "		<tr>\n"
                + "			<td><center><b>Condici&oacute;n F&iacute;sica</b></center></td><td><b>13 a 19 a&ntilde;os</b></td><td><b>20 a 29 a&ntilde;os</b></td><td><b>30 a 39 a&ntilde;os</b></td>\n"
                + "		</tr>\n"
                + "		<tr   >\n"
                + "			<td><b>Muy mala</b></td><td>Menos de 2100m</td><td>Menos de 1950m</td><td>Menos de 1900m</td>\n"
                + "		</tr>\n"
                + "		<tr>\n"
                + "			<td><b>Mala</b></td><td>2100m a 2199m</td><td>1950m a 2099m</td><td>1900m a 2099m</td>\n"
                + "		</tr>\n"
                + "		<tr>\n"
                + "			<td><b>Media</b></td><td>2200m a 2499m</td><td>2100m a 2399m</td><td>2100m a 2349m</td>\n"
                + "		</tr>\n"
                + "		<tr>\n"
                + "			<td><b>Buena</b></td><td>2500m a 2749m</td><td>2400m a 2649m</td><td>2350m a 2499m</td>\n"
                + "		</tr>\n"
                + "		<tr>\n"
                + "			<td><b>Muy buena</b></td><td>2750m a 2999m</td><td>2650m a 2849m</td><td>2500m a 2699m</td>\n"
                + "		</tr>\n"
                + "		<tr>\n"
                + "			<td><b>Excelente</b></td><td>M&aacute;s de 3000m</td><td>M&aacute;s de 2850m</td><td>M&aacute;s de 2700m</td>\n"
                + "		</tr>\n"
                + "		<tr>\n"
                + "			<td colspan=\"7\"><center><b>Mujeres (12 Minutos)</b></center></td>\n"
                + "		</tr>\n"
                + "		<tr>\n"
                + "			<td><b>Muy mala</b></td><td>Menos de 1600m</td><td>Menos de 1550m</td><td>Menos de 1500m</td>\n"
                + "		</tr>\n"
                + "		<tr>\n"
                + "			<td><b>Mala</b></td><td>1600m a 1899m</td><td>1550m a 1799m</td><td>1500m a 1699m</td>\n"
                + "		</tr>\n"
                + "		<tr>\n"
                + "			<td><b>Media</b></td><td>1900m a 2099m</td><td>1800m a 1949m</td><td>1700m a 1899m</td>\n"
                + "		</tr>\n"
                + "		<tr>\n"
                + "			<td><b>Buena</b></td><td>2100m a 2299m</td><td>1950m a 2149m</td><td>1900m a 2099m</td>\n"
                + "		</tr>\n"
                + "		<tr>\n"
                + "			<td><b>Muy buena</b></td><td>2300m a 2449m</td><td>2150m a 2349m</td><td>2100m a 2249m</td>\n"
                + "		</tr>\n"
                + "		<tr>\n"
                + "			<td><b>Excelente</b></td><td>M&aacute;s de 2450m</td><td>M&aacute;s de 2350m</td><td>M&aacute;s de 2250m</td>\n"
                + "		</tr>\n"
                + "	</table>\n";

        final Dialog simple = new Dialog();
        simple.setHeading("Ayuda");
        simple.setButtons(Dialog.OK);
//        simple.setBodyStyleName("pad-text");
        simple.addText(texto);
        simple.getItem(0).getFocusSupport().setIgnore(true);
        simple.setScrollMode(Style.Scroll.AUTO);
        simple.setHideOnButtonClick(true);
        simple.setWidth(550);
        //simple.setSize(550, 255);

        simple.show();
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
