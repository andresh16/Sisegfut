/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.client.administracion.Test;

import co.com.sisegfut.client.aaI18N.Main;
import co.com.sisegfut.client.datos.dominio.Antropometrico;
import co.com.sisegfut.client.datos.dominio.Deportista;
import co.com.sisegfut.client.datos.dominio.dto.DTOAntropometrico;
import co.com.sisegfut.client.entidades.RespuestaRPC;
import co.com.sisegfut.client.util.Resources;
import co.com.sisegfut.client.util.rpc.RPCAdminAntropometrico;
import co.com.sisegfut.client.util.rpc.RPCAdminAntropometricoAsync;
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
import com.extjs.gxt.ui.client.widget.form.DateField;
import com.extjs.gxt.ui.client.widget.form.FieldSet;
import com.extjs.gxt.ui.client.widget.form.FormButtonBinding;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.FormPanel.LabelAlign;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.grid.BufferView;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.extjs.gxt.ui.client.widget.grid.HeaderGroupConfig;
import com.extjs.gxt.ui.client.widget.layout.BoxLayout.BoxLayoutPack;
import com.extjs.gxt.ui.client.widget.layout.ColumnData;
import com.extjs.gxt.ui.client.widget.layout.ColumnLayout;
import com.extjs.gxt.ui.client.widget.layout.FillLayout;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.extjs.gxt.ui.client.widget.layout.HBoxLayout;
import com.extjs.gxt.ui.client.widget.layout.HBoxLayout.HBoxLayoutAlign;
import com.extjs.gxt.ui.client.widget.layout.RowData;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.extjs.gxt.ui.client.widget.toolbar.PagingToolBar;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.NumberFormat;
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
 * @author Malejandro
 */
public final class PanelAntropometrico extends LayoutContainer {

    FormPanel panel = new FormPanel();
    private PagingLoader<PagingLoadResult<ModelData>> loader;
    ListStore<Antropometrico> store;
    Grid<Antropometrico> grid;
    final PagingToolBar PgtoolBar = new PagingToolBar(50);
    private FormBinding formBindings;
    NumberField txtPerBrazoRelajado = new NumberField();
    NumberField txtPerAbdominal = new NumberField();
    NumberField txtPerCadera = new NumberField();
    NumberField txtPerPantorrilla = new NumberField();
    NumberField txtPliSubescapular = new NumberField();
    NumberField txtPliTricipital = new NumberField();
    NumberField txtPliSupraescapular = new NumberField();
    NumberField txtPliAbdominal = new NumberField();
    TextField<String> txtGrasa = new TextField<String>();
    TextField<String> txtPG = new TextField<String>();
    TextField<String> txtPM = new TextField<String>();
    DateField DtFecha = new DateField();
    public static final Integer ACTIVOS = 1;
    public static final Integer INACTIVOS = 2;
    PanelAdminTests adminTest;
    Long idDeportista = null;
    int cifras = (int) Math.pow(10, 2);//sirve para mostrar solo 2 decimales
    private Image foto = new Image();
    private Deportista dep;

    FormPanel form = new FormPanel();

    Label LblPesoGraso = new Label("<b>Peso Graso: </b><br>");
    Label LblPesoMagro = new Label("<b>Peso Magro: </b><br>");
    Label LblPorcentajeGrasa = new Label("<b>% Grasa: </b><br>");

    ContentPanel contenedorFotoUsuario;

    Label lbNombreDep = new Label();

    private Long idAntropometrico;
    private DTOAntropometrico antropometrico = new DTOAntropometrico();

    /**
     * Contiene los textos a presentar en la interfaz web segun el idioma
     */
    private Main myConstants = (Main) GWT.create(Main.class);

    @Override
    protected void onRender(Element parent, int index) {
        super.onRender(parent, index);
        setScrollMode(Style.Scroll.AUTOY);
        setLayoutData(new FillLayout());
        setLayout(new RowLayout(Style.Orientation.VERTICAL));

        final RPCAdminAntropometricoAsync svc = (RPCAdminAntropometricoAsync) GWT.create(RPCAdminAntropometrico.class);
        ServiceDefTarget endpoint = (ServiceDefTarget) svc;
        endpoint.setServiceEntryPoint("services/RPCAdminAntropometrico");

        //Valido que el servicio RPC este activo.
        if (svc == null) {
            MessageBox box = new MessageBox();
            box.setButtons(MessageBox.OK);
            box.setIcon(MessageBox.INFO);
            box.setTitle("Control tecnico");
            box.setMessage("No se ha detectado ningún servicio RPC");
            box.show();
            return;
        }
        //Llamo el servicio RPC que se usara como proxy para cargar los datos de la entidad indicada
        RpcProxy<PagingLoadResult<DTOAntropometrico>> proxy = new RpcProxy<PagingLoadResult<DTOAntropometrico>>() {

            @Override
            protected void load(Object loadConfig, AsyncCallback<PagingLoadResult<DTOAntropometrico>> callback) {
                svc.getAntropometrico(idDeportista, callback);
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

        store = new ListStore<Antropometrico>(loader);
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
        column.setId("pliabdominal");
        column.setHeader("Abdominal");
        column.setWidth(50);
        column.setAlignment(Style.HorizontalAlignment.LEFT);
        configs.add(column);
        column.setRenderer(new GridCellRenderer() {

            @Override
            public Object render(ModelData model, String property, com.extjs.gxt.ui.client.widget.grid.ColumnData config, int rowIndex, int colIndex, ListStore store, Grid grid) {
                String pliabdominal = model.get("pliabdominal");
                if (pliabdominal == null) {
                    return "";
                } else {

                    return pliabdominal;
                }
            }

        });

        column = new ColumnConfig();
        column.setId("plisubescapular");
        column.setHeader("Subescapular");
        column.setWidth(50);
        column.setAlignment(Style.HorizontalAlignment.LEFT);
        configs.add(column);
        column.setRenderer(new GridCellRenderer() {

            @Override
            public Object render(ModelData model, String property, com.extjs.gxt.ui.client.widget.grid.ColumnData config, int rowIndex, int colIndex, ListStore store, Grid grid) {
                String plisubescapular = model.get("plisubescapular");
                if (plisubescapular == null) {
                    return "";
                } else {
                    return plisubescapular;
                }
            }
        });

        column = new ColumnConfig();
        column.setId("plisupraescapular");
        column.setHeader("Supraescapular");
        column.setWidth(50);
        column.setAlignment(Style.HorizontalAlignment.LEFT);
        configs.add(column);
        column.setRenderer(new GridCellRenderer() {

            @Override
            public Object render(ModelData model, String property, com.extjs.gxt.ui.client.widget.grid.ColumnData config, int rowIndex, int colIndex, ListStore store, Grid grid) {
                String plisupraescapular = model.get("plisupraescapular");
                if (plisupraescapular == null) {
                    return "";
                } else {
                    return plisupraescapular;
                }
            }
        });

        column = new ColumnConfig();
        column.setId("plitricipital");
        column.setHeader("Tricipital");
        column.setWidth(50);
        column.setAlignment(Style.HorizontalAlignment.LEFT);
        configs.add(column);
        column.setRenderer(new GridCellRenderer() {

            @Override
            public Object render(ModelData model, String property, com.extjs.gxt.ui.client.widget.grid.ColumnData config, int rowIndex, int colIndex, ListStore store, Grid grid) {
                String plitricipital = model.get("plitricipital");
                if (plitricipital == null) {
                    return "";
                } else {
                    return plitricipital;
                }
            }
        });
        //Pliegues Cutaneos

        column = new ColumnConfig();
        column.setId("perabdominal");
        column.setHeader("Abdominal");
        column.setWidth(50);
        column.setAlignment(Style.HorizontalAlignment.LEFT);
        configs.add(column);
        column.setRenderer(new GridCellRenderer() {

            @Override
            public Object render(ModelData model, String property, com.extjs.gxt.ui.client.widget.grid.ColumnData config, int rowIndex, int colIndex, ListStore store, Grid grid) {
                String perabdominal = model.get("perabdominal");
                if (perabdominal == null) {
                    return "";
                } else {
                    return perabdominal;
                }
            }
        });

        column = new ColumnConfig();
        column.setId("perbrazorelajado");
        column.setHeader("Brazo relajado");
        column.setWidth(50);
        column.setAlignment(Style.HorizontalAlignment.LEFT);
        configs.add(column);
        column.setRenderer(new GridCellRenderer() {

            @Override
            public Object render(ModelData model, String property, com.extjs.gxt.ui.client.widget.grid.ColumnData config, int rowIndex, int colIndex, ListStore store, Grid grid) {
                String perbrazorelajado = model.get("perbrazorelajado");
                if (perbrazorelajado == null) {
                    return "";
                } else {
                    return perbrazorelajado;
                }
            }
        });

        column = new ColumnConfig();
        column.setId("percadera");
        column.setHeader("Cadera");
        column.setWidth(50);
        column.setAlignment(Style.HorizontalAlignment.LEFT);
        configs.add(column);
        column.setRenderer(new GridCellRenderer() {

            @Override
            public Object render(ModelData model, String property, com.extjs.gxt.ui.client.widget.grid.ColumnData config, int rowIndex, int colIndex, ListStore store, Grid grid) {
                String percadera = model.get("percadera");
                if (percadera == null) {
                    return "";
                } else {
                    return percadera;
                }
            }
        });
        column = new ColumnConfig();
        column.setId("perpantorrilla");
        column.setHeader("Pantorrilla");
        column.setWidth(50);
        column.setAlignment(Style.HorizontalAlignment.LEFT);
        configs.add(column);
        column.setRenderer(new GridCellRenderer() {

            @Override
            public Object render(ModelData model, String property, com.extjs.gxt.ui.client.widget.grid.ColumnData config, int rowIndex, int colIndex, ListStore store, Grid grid) {
                String perpantorrilla = model.get("perpantorrilla");
                if (perpantorrilla == null) {
                    return "";
                } else {
                    return perpantorrilla;
                }
            }
        });

        ColumnModel cm = new ColumnModel(configs);

        ContentPanel cpGrid = new ContentPanel();
        cpGrid.setHeaderVisible(true);
        cm.addHeaderGroup(0, 1, new HeaderGroupConfig("Perímetros", 1, 4));
        
        
        cm.addHeaderGroup(0, 5, new HeaderGroupConfig("Pliegues Cutáneos", 1, 4));

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

        grid = new Grid<Antropometrico>(store, cm);
        grid.setView(new BufferView());

        grid.getSelectionModel().setSelectionMode(Style.SelectionMode.SINGLE);

        grid.getSelectionModel().addListener(Events.SelectionChange, new Listener<SelectionChangedEvent<BeanModel>>() {
            @Override
            public void handleEvent(SelectionChangedEvent<BeanModel> be) {
                // formBindings.unbind();
                if (be.getSelection().size() > 0) {

                    antropometrico = (DTOAntropometrico) be.getSelectedItem().getBean();

                    idAntropometrico = antropometrico.getIdAntropometrico();
                    form.reset();
                    txtGrasa.setValue(antropometrico.getPorcentajeGrasa());
                    txtPG.setValue(antropometrico.getPesoGraso());
                    txtPM.setValue(antropometrico.getPesoMacro());

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
               abrirVentana(myConstants.ayudaPanelPIAntropometrico());
            }
        }));

        toolBar.add(btnGuardar);
        toolBar.add(btnEliminar);
        toolBar.add(btnLimpiar);
        toolBar.add(btnReporte);
        toolBar.add(lbNombreDep);

        cpForm.setTopComponent(toolBar);

        add(cpForm, new RowData(1, 0.6, new Margins(0)));
        add(cpGrid, new RowData(1, 0.4, new Margins(0)));

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

    public void cargar() {
        try {
            loader.load(0, 1000);
            loader.load(0, 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    protected SelectionListener<ButtonEvent> listenerLimpiar() {
        return new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                limpiar();
            }
        };

    }

    protected SelectionListener<ButtonEvent> listenerCalcular() {
        return new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                if (txtPliAbdominal.getValue() != null && txtPliSubescapular.getValue() != null && txtPliSupraescapular.getValue() != null && txtPliTricipital.getValue() != null) {
                    mostrasResultados();
                } else {
                    MessageBox.alert("Error", "Debe diligenciar todos los campos de pliegues cutáneos", null);
                }
            }
        };

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

    protected SelectionListener<ButtonEvent> listenerEliminar() {
        return new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                if (idDeportista != null) {

                    if (idAntropometrico != null) {
                        MessageBox.confirm("Confirmación", "Seguro quiere eliminar el test Antropométrico", new Listener<MessageBoxEvent>() {
                            @Override
                            public void handleEvent(MessageBoxEvent be) {

                                String respb = be.getButtonClicked().getText();

                                if (respb.equalsIgnoreCase("yes") || respb.equalsIgnoreCase("sí")) {

                                    getServiceAntropometrico().eliminarEntidad(idAntropometrico, new AsyncCallback<RespuestaRPC<Antropometrico>>() {

                                        @Override
                                        public void onFailure(Throwable caught) {
                                            Info.display("Eliminar", "No Elimino el test Antropométrico" + idAntropometrico);
                                        }

                                        @Override
                                        public void onSuccess(RespuestaRPC<Antropometrico> result) {
                                            Info.display("Eliminar", "Se eliminó correctamente el test Antropométrico");
                                            limpiar();
                                        }
                                    });

                                }

                            }
                        });
                    } else {
                        MessageBox.alert("Advertencia!", "Debe seleccionar un test antropométrico a eliminar", null);
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

                    String base = GWT.getModuleBaseURL() + "../html/reportes/HistoricoAntropometrico/";
                    // deportista seleccionado
                    redireccionarA(base + idDeportista);

                } else {
                    MessageBox.alert("Alerta", "Debe seleccionar primero un deportista", null);
                }

            }
        };
    }

    public Antropometrico obtenerDatosFormulario() {

        Antropometrico antropometrico = new Antropometrico();
        antropometrico.setIdDeportista(new Deportista(idDeportista));

        antropometrico.setPerabdominal(txtPerAbdominal.getValue().intValue());
        antropometrico.setPerbrazorelajado(txtPerBrazoRelajado.getValue().intValue());
        antropometrico.setPercadera(txtPerCadera.getValue().intValue());
        antropometrico.setPerpantorrilla(txtPerPantorrilla.getValue().intValue());
        antropometrico.setPliabdominal(txtPliAbdominal.getValue().intValue());
        antropometrico.setPlisubescapular(txtPliSubescapular.getValue().intValue());
        antropometrico.setPlitricipital(txtPliTricipital.getValue().intValue());
        antropometrico.setPlisupraescapular(txtPliSupraescapular.getValue().intValue());
        antropometrico.setPorcentajeGrasa(txtGrasa.getValue().toString());
        antropometrico.setPesoGraso(txtPG.getValue().toString());
        antropometrico.setPesoMacro(txtPM.getValue().toString());
        antropometrico.setFecha(new Date());

        return antropometrico;
    }

    public void limpiar() {
        form.reset();
        idAntropometrico = null;
        cargar();
    }
    public void limpiarDeportista() {
        form.reset();
        idAntropometrico = null;
        idDeportista=null;
        cargar();
        cargarFotoNoDiposible();
        lbNombreDep.setText("");
    }

    public void cargarCampos(Antropometrico ant) {

        txtPerAbdominal.setValue(ant.getPerabdominal());
        txtPerBrazoRelajado.setValue(ant.getPerbrazorelajado());
        txtPerCadera.setValue(ant.getPercadera());
        txtPerPantorrilla.setValue(ant.getPerpantorrilla());

        txtPliAbdominal.setValue(ant.getPliabdominal());
        txtPliSubescapular.setValue(ant.getPlisubescapular());
        txtPliSupraescapular.setValue(ant.getPlisupraescapular());
        txtPliTricipital.setValue(ant.getPlitricipital());

    }

    public FormPanel crearFormulario() {
        form.setScrollMode(Style.Scroll.AUTOX);
        form.setScrollMode(Style.Scroll.AUTOY);
        form.setFrame(true);
        form.setHeaderVisible(false);

        // Layout Main que contiene todas las columnas 
        FormData formData = new FormData("-20");
        LayoutContainer main = new LayoutContainer();
        main.setLayout(new ColumnLayout());
        // main.setHeight(100);
        main.setAutoHeight(afterRender);
        lbNombreDep.setPosition(500, 30);
        form.add(lbNombreDep);

        contenedorFotoUsuario = new ContentPanel();
        contenedorFotoUsuario.setHeaderVisible(false);
        contenedorFotoUsuario.setVisible(true);
//        contenedorFotoUsuario.setToolTip("La foto debe tener un tamaño de 4x4");

        foto.setWidth("150px");
        foto.setHeight("160px");
        contenedorFotoUsuario.add(foto);

//        LayoutFoto.add(contenedorFotoUsuario);
//        add(LayoutFoto, new FormData("100%"));
        ///////////////////// Columna 1 ////////////////////////////  /////////
        LayoutContainer Columna1 = new LayoutContainer();
        Columna1.setStyleAttribute("paddingRight", "10px");

        FormLayout layout = new FormLayout();
//        layout.setLabelAlign(LabelAlign.TOP);
        Columna1.setLayout(layout);
//        Columna1.add(LayoutFoto, new FormData("100%"));

        FieldSet flPerimetros = new FieldSet();
        flPerimetros.setHeading("Perímetros");
        layout = new FormLayout();
        layout.setLabelWidth(115);
        flPerimetros.setLayout(layout);

        txtPerBrazoRelajado.setName("brazorelajado");
        txtPerBrazoRelajado.setFieldLabel("<font color='red'>*</font> Brazo Relajado");
        txtPerBrazoRelajado.setRegex("^[.0-9]*$");
        txtPerBrazoRelajado.setMaxLength(3);
        txtPerBrazoRelajado.setEmptyText("");
        txtPerBrazoRelajado.setAllowBlank(false);
        txtPerBrazoRelajado.getMessages().setRegexText("El campo no puede contener letras ni caracteres especiales .");
//        txtDocumento.setEnabled(false);
        flPerimetros.add(txtPerBrazoRelajado, formData);

        txtPerAbdominal.setName("nombres");
        txtPerAbdominal.setFieldLabel("<font color='red'>*</font> Abdominal");
        txtPerAbdominal.setAllowBlank(false);
        txtPerAbdominal.setMaxLength(6);
//        txtNombres.setRegex("^[.0-9]*$");
        txtPerAbdominal.setEmptyText("");
//        txtNombres.getMessages().setRegexText("El campo no puede contener letras ni caracteres especiales .");
//        txtNombres.setEnabled(false);
        flPerimetros.add(txtPerAbdominal, formData);

        txtPerCadera.setName("cadera");
        txtPerCadera.setFieldLabel("<font color='red'>*</font> Cadera");
        txtPerCadera.setAllowBlank(false);
        txtPerCadera.setMaxLength(3);
//        txtNombres.setRegex("^[.0-9]*$");
        txtPerCadera.setEmptyText("");
//        txtNombres.getMessages().setRegexText("El campo no puede contener letras ni caracteres especiales .");
//        txtApellidos.setEnabled(false);
        flPerimetros.add(txtPerCadera, formData);

        txtPerPantorrilla.setName("pantorrilla");
        txtPerPantorrilla.setAllowBlank(false);
        txtPerPantorrilla.setFieldLabel("<font color='red'>*</font> Pantorrilla");
        txtPerPantorrilla.setRegex("^[.0-9]*$");
        txtPerPantorrilla.setEmptyText("");
        txtPerPantorrilla.setMaxLength(6);
        txtPerPantorrilla.getMessages().setRegexText("El campo no puede contener letras ni caracteres especiales .");
//        txtDireccion.setEnabled(false);
        flPerimetros.add(txtPerPantorrilla, formData);

        ///////////////////// Columna 2 //////////////////////////// 
//    fieldSet.setCollapsible(true);
        LayoutContainer Columna2 = new LayoutContainer();
        Columna2.setStyleAttribute("paddingLeft", "0px");
        layout = new FormLayout();
        layout.setLabelAlign(LabelAlign.LEFT);

//        VBoxLayout layout2 = new VBoxLayout();  
//        layout2.setPadding(new Padding(20));  
//        layout2.setVBoxLayoutAlign(VBoxLayoutAlign.CENTER);  
//        Columna2.setLayout(layout2); 
        Columna2.setLayout(layout);

        FieldSet flPliegues = new FieldSet();
        flPliegues.setHeading("Pliegues Cutáneos");
        layout = new FormLayout();
        layout.setLabelWidth(115);
        flPliegues.setLayout(layout);

//        Columna2.add(LblPorcentajeGrasa);
//        LayoutFoto.add(LblPesoMagro);
//        LayoutFoto.add(LblPesoGraso);
        txtPliAbdominal.setFieldLabel("<font color='red'>*</font> Abdominal");
        txtPliAbdominal.setFormat(NumberFormat.getDecimalFormat());
        txtPliAbdominal.setMaxLength(6);
        txtPliAbdominal.setAllowBlank(false);
//        txttxtPesoPeso.setEnabled(false);
        flPliegues.add(txtPliAbdominal, formData);

        txtPliSubescapular.setFieldLabel("<font color='red'>*</font> Subescapular");
        txtPliSubescapular.setMaxLength(6);
        txtPliSubescapular.setFormat(NumberFormat.getDecimalFormat());
        txtPliSubescapular.setAllowBlank(false);
//        txtEstatura.setEnabled(false);
        flPliegues.add(txtPliSubescapular, formData);

        txtPliSupraescapular.setFieldLabel("<font color='red'>*</font> Supraescapular");
        txtPliSupraescapular.setFormat(NumberFormat.getDecimalFormat());
        txtPliSupraescapular.setMaxLength(6);
        txtPliSupraescapular.setAllowBlank(false);
//        txtEstatura.setEnabled(false);
        flPliegues.add(txtPliSupraescapular, formData);

        txtPliTricipital.setFieldLabel("<font color='red'>*</font> Tricipital");
        txtPliTricipital.setMaxLength(6);
        txtPliTricipital.setFormat(NumberFormat.getDecimalFormat());
        txtPliTricipital.setAllowBlank(false);
//        txtEstatura.setEnabled(false);
        flPliegues.add(txtPliTricipital, formData);

        txtPliTricipital.addListener(Events.Change, new Listener<BaseEvent>() {
            @Override
            public void handleEvent(BaseEvent be) {
               if(form.isValid()){
                mostrasResultados();
               }
            }
        });
        txtPliSupraescapular.addListener(Events.Change, new Listener<BaseEvent>() {
            @Override
            public void handleEvent(BaseEvent be) {
               if(form.isValid()){
                mostrasResultados();
               }
            }
        });
        txtPliTricipital.addListener(Events.Change, new Listener<BaseEvent>() {
            @Override
            public void handleEvent(BaseEvent be) {
               if(form.isValid()){
                mostrasResultados();
               }
            }
        });
        txtPliSubescapular.addListener(Events.Change, new Listener<BaseEvent>() {
            @Override
            public void handleEvent(BaseEvent be) {
               if(form.isValid()){
                mostrasResultados();
               }
            }
        });
        
        Columna1.add(flPerimetros);
        Columna2.add(flPliegues);

        ///////////////////// Columna 3 //////////////////////////// 
//    fieldSet.setCollapsible(true);
        LayoutContainer Columna3 = new LayoutContainer();
        Columna3.setStyleAttribute("paddingLeft", "0px");
        layout = new FormLayout();
        Columna3.setLayout(layout);

        /////////////////////// Foto /////////////////////////////////////////////
        LayoutContainer LayoutFoto = new LayoutContainer();
        LayoutFoto.setStyleAttribute("paddingRight", "10px");

        HBoxLayout HBlayout = new HBoxLayout();
        HBlayout.setPadding(new Padding(5));
        HBlayout.setHBoxLayoutAlign(HBoxLayoutAlign.TOP);
        HBlayout.setPack(BoxLayoutPack.CENTER);
        LayoutFoto.setLayout(HBlayout);
//        LayoutFoto.setLayout(new VBoxLayout(VBoxLayout.VBoxLayoutAlign.CENTER));
        LayoutFoto.add(contenedorFotoUsuario);
        Columna3.add(LayoutFoto);

        main.add(Columna1, new ColumnData(.35));
        main.add(Columna2, new ColumnData(.35));
        main.add(Columna3, new ColumnData(.3));
//        main.add(Columna3, new ColumnData(.25));
//        main.add(Columna4, new ColumnData(.25));

        form.add(main, new FormData("100%"));

        FieldSet flResultados = new FieldSet();
        flResultados.setHeading("Resultados");
        layout = new FormLayout();

        layout.setLabelPad(40);
        layout.setLabelWidth(115);
        flResultados.setLayout(new ColumnLayout());

        Label grasa = new Label();
        Label PG = new Label();
        Label PM = new Label();

        grasa.setText("&nbsp;&nbsp;&nbsp;&nbsp&nbsp;&nbsp;&nbsp;&nbsp&nbsp;&nbsp;&nbsp;&nbsp&nbsp;&nbsp;&nbsp;&nbsp;<b>% Grasa: </b>&nbsp;&nbsp;&nbsp;&nbsp;");
        PG.setText("&nbsp;&nbsp;&nbsp;&nbsp;<b>Peso Graso: </b>&nbsp;&nbsp;&nbsp;&nbsp;");
        PM.setText("&nbsp;&nbsp;&nbsp;&nbsp;<b>Peso Magro: </b>&nbsp;&nbsp;&nbsp;&nbsp;");

        txtGrasa.setReadOnly(true);
//        txtGrasa.setFormat(NumberFormat.getPercentFormat());
        txtGrasa.setWidth(80);
        txtPG.setReadOnly(true);
        txtPG.setWidth(80);
//        txtPG.setFormat(NumberFormat.getDecimalFormat());
        txtPM.setReadOnly(true);
        txtPM.setWidth(80);
//        txtPM.setFormat(NumberFormat.getDecimalFormat());

        flResultados.add(grasa);
        flResultados.add(txtGrasa);
        flResultados.add(PG);
        flResultados.add(txtPG);
        flResultados.add(PM);
        flResultados.add(txtPM);

//        flResultados.add(FPResultados);
        form.add(flResultados);
        return form;
    }

    public void guardar(final Antropometrico antropometrico) {

        getServiceAntropometrico().guardarEntidad(antropometrico, new AsyncCallback() {

            @Override
            public void onFailure(Throwable caught) {
                MessageBox.alert("Error", "No guardo las medidas antropométricas", null);
            }

            @Override
            public void onSuccess(Object result) {
                actualizarGrasaDeportista(idDeportista, antropometrico.getPorcentajeGrasa());
                Info.display("Guardar", "Guardó correctamente las medidas antropométricas");
                cargar();
                form.reset();
            }
        });
    }
    public void actualizarGrasaDeportista(Long idDep,String grasa){
    
          getServiceDeportista().actulizarGrasa(idDep, grasa, new AsyncCallback<Void>() {

              @Override
              public void onFailure(Throwable caught) {
                Info.display("Error", "No fue posible actualizar el porcentaje de grasa");
                
              }

              @Override
              public void onSuccess(Void result) {
               Info.display("Actulizar", "Se actualizó el porcentaje de grasa");
              }
          });
    
    
    }
    
    public RPCAdminDeportistaAsync getServiceDeportista() {
        RPCAdminDeportistaAsync svc = (RPCAdminDeportistaAsync) GWT.create(RPCAdminDeportista.class);
        ServiceDefTarget endpoint = (ServiceDefTarget) svc;
        endpoint.setServiceEntryPoint("services/RPCAdminDeportista");
        return svc;
    }

    public RPCAdminAntropometricoAsync getServiceAntropometrico() {
        RPCAdminAntropometricoAsync svc = (RPCAdminAntropometricoAsync) GWT.create(RPCAdminAntropometrico.class);
        ServiceDefTarget endpoint = (ServiceDefTarget) svc;
        endpoint.setServiceEntryPoint("services/RPCAdminAntropometrico");
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

    public String calcularGrasa(Double p1, Double p2, Double p3, Double p4) {
        //FAULKNER = (S4 Pliegues X 0.153) + 5.783
        Double grasa = 0.0;
        grasa = ((p1 + p2 + p3 + p4) * 0.153) + 5.783;
        return grasa.toString();
    }

    public Double calcularGrasaD(Double p1, Double p2, Double p3, Double p4) {
        //FAULKNER = (S4 Pliegues X 0.153) + 5.783
        Double grasa = 0.0;
        grasa = ((p1 + p2 + p3 + p4) * 0.153) + 5.783;
        grasa = Math.rint(grasa * cifras) / cifras;
        return grasa;
    }

    public Double calcularPesoMagro(Double peso, Double pesoGraso) {
        //PESO MAGRO = PESO TOTAL - PESO GRASO
        Double PM = 0.0;
        PM = peso - pesoGraso;
        PM = Math.rint(PM * cifras) / cifras;
        return PM;
    }

    public Double calcularPesoGraso(Double peso, Double porcentGrasa) {
        //PESO GRASO = PESO TOTAL * PORCENTAJE DE GRASA / 100
        Double PG = 0.0;
        PG = (peso * porcentGrasa) / 100;
        PG = Math.rint(PG * cifras) / cifras;
        return PG;
    }
    
    public void mostrasResultados() {
        Double resultadosAntropometrico = 0.0;
        resultadosAntropometrico = calcularGrasaD(txtPliAbdominal.getValue().doubleValue(), txtPliSubescapular.getValue().doubleValue(), txtPliSupraescapular.getValue().doubleValue(), txtPliTricipital.getValue().doubleValue());
        txtGrasa.setValue(resultadosAntropometrico.toString());
        resultadosAntropometrico = calcularPesoGraso(76.0, resultadosAntropometrico);
        txtPG.setValue(resultadosAntropometrico.toString());
        resultadosAntropometrico = calcularPesoMagro(76.0, resultadosAntropometrico);
        txtPM.setValue(resultadosAntropometrico.toString());
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
