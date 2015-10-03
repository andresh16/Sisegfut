/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.client.administracion.Test;

import co.com.sisegfut.client.aaI18N.Main;
import co.com.sisegfut.client.datos.dominio.ControlTecnico;
import co.com.sisegfut.client.datos.dominio.Deportista;
import co.com.sisegfut.client.datos.dominio.dto.DTOControlTecnico;
import co.com.sisegfut.client.entidades.RespuestaRPC;
import co.com.sisegfut.client.util.Resources;
import co.com.sisegfut.client.util.rpc.RPCAdminControlTecnico;
import co.com.sisegfut.client.util.rpc.RPCAdminControlTecnicoAsync;
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
import com.extjs.gxt.ui.client.widget.form.DateField;
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
 * @author Camilo
 */
public final class PanelControlTecnico extends LayoutContainer {

    FormPanel panel = new FormPanel();
    private Long idDeportista;
    private PagingLoader<PagingLoadResult<ModelData>> loader;
    ListStore<ControlTecnico> store;
    Grid<ControlTecnico> grid;
    final PagingToolBar PgtoolBar = new PagingToolBar(50);
    private FormBinding formBindings;
    DateField DtFecha = new DateField();
    NumberField txtRecepcion30seg = new NumberField();
    NumberField txtPrecisionPase15seg = new NumberField();
    NumberField txtPrecisionDisparoEmpeine = new NumberField();
    NumberField txtPotenciaRemate = new NumberField();
    NumberField txtControlBalon50seg = new NumberField();
    NumberField txtConduccion = new NumberField();
    NumberField txtCabeceoDefensivo = new NumberField();
    NumberField txtCabeceoOfensivo = new NumberField();
    NumberField txtAceleracion = new NumberField();
    public static final Integer ACTIVOS = 1;
    public static final Integer INACTIVOS = 2;
    PanelAdminTests adminTest;
    private Image foto = new Image();
    ContentPanel contenedorFotoUsuario;
    Label lbNombreDep = new Label();
    FormPanel form = new FormPanel();

    private Long idCtrlTecnico;
    private DTOControlTecnico controlTecnico = new DTOControlTecnico();
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
        myConstants = (Main) GWT.create(Main.class);
        final RPCAdminControlTecnicoAsync svc = (RPCAdminControlTecnicoAsync) GWT.create(RPCAdminControlTecnico.class);
        ServiceDefTarget endpoint = (ServiceDefTarget) svc;
        endpoint.setServiceEntryPoint("services/RPCAdminControlTecnico");

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
        RpcProxy<PagingLoadResult<DTOControlTecnico>> proxy = new RpcProxy<PagingLoadResult<DTOControlTecnico>>() {

            @Override
            protected void load(Object loadConfig, AsyncCallback<PagingLoadResult<DTOControlTecnico>> callback) {
                svc.getControlTecDeportivos(idDeportista, callback);
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

        store = new ListStore<ControlTecnico>(loader);
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

//        column.setNumberFormat(Number);
//        column.setRenderer(new GridCellRenderer() {
//
//            @Override
//            public Object render(ModelData model, String property, com.extjs.gxt.ui.client.widget.grid.ColumnData config, int rowIndex, int colIndex, ListStore store, Grid grid) {
//                String fecha = model.get("fecha");
//                if (fecha == null) {
//                    return "";
//                } else {
//                    return fecha;
//                }
//            }
//        });
        configs.add(column);

        column = new ColumnConfig();
        column.setId("nombrerecepcion30seg");
        column.setHeader("R30");
        column.setWidth(50);
        column.setAlignment(Style.HorizontalAlignment.LEFT);
        configs.add(column);
        column.setRenderer(new GridCellRenderer() {

            @Override
            public Object render(ModelData model, String property, com.extjs.gxt.ui.client.widget.grid.ColumnData config, int rowIndex, int colIndex, ListStore store, Grid grid) {
                String re50 = model.get("nombrerecepcion30seg");
                if (re50 == null) {
                    return "";
                } else {
                    return re50;
                }
            }
        });

        column = new ColumnConfig();
        column.setId("nombreprecisionpase15seg");
        column.setHeader("Pp15");
        column.setWidth(50);
        column.setAlignment(Style.HorizontalAlignment.LEFT);
        configs.add(column);
        column.setRenderer(new GridCellRenderer() {

            @Override
            public Object render(ModelData model, String property, com.extjs.gxt.ui.client.widget.grid.ColumnData config, int rowIndex, int colIndex, ListStore store, Grid grid) {
                String pp15 = model.get("nombreprecisionpase15seg");
                if (pp15 == null) {
                    return "";
                } else {
                    return pp15;
                }
            }
        });
        column = new ColumnConfig();
        column.setId("nombreprecisiondisparoempeine");
        column.setHeader("DE");
        column.setAlignment(Style.HorizontalAlignment.LEFT);
        column.setWidth(50);
        configs.add(column);
        column.setRenderer(new GridCellRenderer() {

            @Override
            public Object render(ModelData model, String property, com.extjs.gxt.ui.client.widget.grid.ColumnData config, int rowIndex, int colIndex, ListStore store, Grid grid) {
                String DE = model.get("nombreprecisiondisparoempeine");
                if (DE == null) {
                    return "";
                } else {
                    return DE;
                }
            }
        });

        column = new ColumnConfig();
        column.setId("nombrepotenciaremate");
        column.setHeader("PR");
        column.setAlignment(Style.HorizontalAlignment.LEFT);
        column.setWidth(50);
        configs.add(column);
        column.setRenderer(new GridCellRenderer() {

            @Override
            public Object render(ModelData model, String property, com.extjs.gxt.ui.client.widget.grid.ColumnData config, int rowIndex, int colIndex, ListStore store, Grid grid) {
                String pr = model.get("nombrepotenciaremate");
                if (pr == null) {
                    return "";
                } else {
                    return pr;
                }
            }
        });

        column = new ColumnConfig();
        column.setId("nombrecontrolbalon50seg");
        column.setHeader("CtrolB50");
        column.setAlignment(Style.HorizontalAlignment.LEFT);
        column.setWidth(50);
        configs.add(column);
        column.setRenderer(new GridCellRenderer() {

            @Override
            public Object render(ModelData model, String property, com.extjs.gxt.ui.client.widget.grid.ColumnData config, int rowIndex, int colIndex, ListStore store, Grid grid) {
                String cb = model.get("nombrecontrolbalon50seg");
                if (cb == null) {
                    return "";
                } else {
                    return cb;
                }
            }
        });

        column = new ColumnConfig();
        column.setId("nombreconduccion");
        column.setHeader("Conducción");
        column.setAlignment(Style.HorizontalAlignment.LEFT);
        column.setWidth(50);
        configs.add(column);
        column.setRenderer(new GridCellRenderer() {

            @Override
            public Object render(ModelData model, String property, com.extjs.gxt.ui.client.widget.grid.ColumnData config, int rowIndex, int colIndex, ListStore store, Grid grid) {
                String condi = model.get("nombreconduccion");
                if (condi == null) {
                    return "";
                } else {
                    return condi;
                }
            }
        });

        column = new ColumnConfig();
        column.setId("nombrecabeceodefensivo");
        column.setHeader("CabDef");
        column.setAlignment(Style.HorizontalAlignment.LEFT);
        column.setWidth(50);
        configs.add(column);
        column.setRenderer(new GridCellRenderer() {

            @Override
            public Object render(ModelData model, String property, com.extjs.gxt.ui.client.widget.grid.ColumnData config, int rowIndex, int colIndex, ListStore store, Grid grid) {
                String CabDef = model.get("nombrecabeceodefensivo");
                if (CabDef == null) {
                    return "";
                } else {
                    return CabDef;
                }
            }
        });

        column = new ColumnConfig();
        column.setId("nombrecabeceoofensivo");
        column.setHeader("CabOfen");
        column.setAlignment(Style.HorizontalAlignment.LEFT);
        column.setWidth(50);
        configs.add(column);
        column.setRenderer(new GridCellRenderer() {

            @Override
            public Object render(ModelData model, String property, com.extjs.gxt.ui.client.widget.grid.ColumnData config, int rowIndex, int colIndex, ListStore store, Grid grid) {
                String CabOfen = model.get("nombrecabeceoofensivo");
                if (CabOfen == null) {
                    return "";
                } else {
                    return CabOfen;
                }
            }
        });

        column = new ColumnConfig();
        column.setId("nombreaceleracion");
        column.setHeader("Acelera");
        column.setAlignment(Style.HorizontalAlignment.LEFT);
        column.setWidth(50);
        configs.add(column);
        column.setRenderer(new GridCellRenderer() {

            @Override
            public Object render(ModelData model, String property, com.extjs.gxt.ui.client.widget.grid.ColumnData config, int rowIndex, int colIndex, ListStore store, Grid grid) {
                String Acelera = model.get("nombreaceleracion");
                if (Acelera == null) {
                    return "";
                } else {
                    return Acelera;
                }
            }
        });

        ColumnModel cm = new ColumnModel(configs);

        ContentPanel cpGrid = new ContentPanel();
        cpGrid.setHeaderVisible(true);
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

        grid = new Grid<ControlTecnico>(store, cm);
        grid.setView(new BufferView());

        grid.getSelectionModel().setSelectionMode(Style.SelectionMode.SINGLE);

        grid.getSelectionModel().addListener(Events.SelectionChange, new Listener<SelectionChangedEvent<BeanModel>>() {
            @Override
            public void handleEvent(SelectionChangedEvent<BeanModel> be) {
                // formBindings.unbind();
                if (be.getSelection().size() > 0) {

                    controlTecnico = (DTOControlTecnico) be.getSelectedItem().getBean();

                    idCtrlTecnico = controlTecnico.getIdCtrlTecnico();

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
                abrirVentana(myConstants.ayudaPanelPIControlTecnico());
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
        idDeportista = id;
        cargarfoto(idDeportista);
        cargar();
    }

    protected SelectionListener<ButtonEvent> listenerLimpiar() {
        return new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                limpiar();
            }
        };

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
//        layout.setLabelAlign(LabelAlign.TOP);
        Columna1.setLayout(layout);

        DtFecha.setName("fecha");
        DtFecha.setAllowBlank(false);
        DtFecha.setEnabled(false);
        DtFecha.setMinValue(new Date());
        DtFecha.setEditable(false);

        DtFecha.setValue(new Date());

        DtFecha.setFieldLabel("<font color='red'>*</font> Fecha ");
//        Columna1.add(DtFecha, formData);

        txtRecepcion30seg.setName("recepcion");
        txtRecepcion30seg.setFieldLabel("<font color='red'>*</font>Recepción 30 Seg");
        txtRecepcion30seg.setRegex("^[.0-9]*$");
        txtRecepcion30seg.setEmptyText("");
        txtRecepcion30seg.setMaxLength(3);
        txtRecepcion30seg.setAllowBlank(false);
        txtRecepcion30seg.getMessages().setRegexText("El campo no puede contener letras ni caracteres especiales .");
//        txtDocumento.setEnabled(false);
        Columna1.add(txtRecepcion30seg, formData);

        txtPrecisionPase15seg.setName("precisionpase");
        txtPrecisionPase15seg.setFieldLabel("<font color='red'>*</font>Precision Pase 15 seg");
        txtPrecisionPase15seg.setAllowBlank(false);
//        txtNombres.setRegex("^[.0-9]*$");
        txtPrecisionPase15seg.setEmptyText("");
        txtPrecisionPase15seg.setMaxLength(3);
//        txtNombres.getMessages().setRegexText("El campo no puede contener letras ni caracteres especiales .");
//        txtNombres.setEnabled(false);
        Columna1.add(txtPrecisionPase15seg, formData);

        txtPrecisionDisparoEmpeine.setName("empeine");
        txtPrecisionDisparoEmpeine.setFieldLabel("<font color='red'>*</font>Precisión Disparo Empeine");
        txtPrecisionDisparoEmpeine.setAllowBlank(false);
        txtPrecisionDisparoEmpeine.setMaxLength(3);
//        txtNombres.setRegex("^[.0-9]*$");
        txtPrecisionDisparoEmpeine.setEmptyText("");
//        txtNombres.getMessages().setRegexText("El campo no puede contener letras ni caracteres especiales .");
//        txtApellidos.setEnabled(false);
        Columna1.add(txtPrecisionDisparoEmpeine, formData);

        txtPotenciaRemate.setName("remate");
        txtPotenciaRemate.setAllowBlank(false);
        txtPotenciaRemate.setFieldLabel("<font color='red'>*</font>Potencia Remate");
        txtPotenciaRemate.setRegex("^[.0-9]*$");
        txtPotenciaRemate.setEmptyText("");
        txtPotenciaRemate.setMaxLength(3);
        txtPotenciaRemate.getMessages().setRegexText("El campo no puede contener letras ni caracteres especiales .");
//        txtDireccion.setEnabled(false);
        Columna1.add(txtPotenciaRemate, formData);

        ///////////////////// Columna 2 //////////////////////////// 
        LayoutContainer Columna2 = new LayoutContainer();
        Columna2.setStyleAttribute("paddingLeft", "0px");
        layout = new FormLayout();
        layout.setLabelAlign(FormPanel.LabelAlign.LEFT);

//        VBoxLayout layout2 = new VBoxLayout();  
//        layout2.setPadding(new Padding(20));  
//        layout2.setVBoxLayoutAlign(VBoxLayoutAlign.CENTER);  
//        Columna2.setLayout(layout2); 
        Columna2.setLayout(layout);

        txtControlBalon50seg.setName("controlbalon");
        txtControlBalon50seg.setAllowBlank(false);
        txtControlBalon50seg.setFieldLabel("<font color='red'>*</font>Control Balón 50 seg");
        txtControlBalon50seg.setRegex("^[.0-9]*$");
        txtControlBalon50seg.setEmptyText("");
        txtControlBalon50seg.setMaxLength(3);
        txtControlBalon50seg.getMessages().setRegexText("El campo no puede contener letras ni caracteres especiales .");
//        txtDireccion.setEnabled(false);
        Columna2.add(txtControlBalon50seg, formData);

        txtConduccion.setName("conduccion");
        txtConduccion.setFieldLabel("<font color='red'>*</font> Conducción");
        txtConduccion.setMaxLength(3);
        txtConduccion.setRegex("^[.0-9]*$");
        txtConduccion.setEmptyText("");
        txtConduccion.setAllowBlank(false);
        txtConduccion.getMessages().setRegexText("El campo no puede contener letras ni caracteres especiales .");
//        txttxtPesoPeso.setEnabled(false);
        Columna2.add(txtConduccion, formData);

        txtCabeceoDefensivo.setName("defensivo");
        txtCabeceoDefensivo.setFieldLabel("<font color='red'>*</font> Cabeceo Defensivo");
        txtCabeceoDefensivo.setMaxLength(3);
        txtCabeceoDefensivo.setRegex("^[.0-9]*$");
        txtCabeceoDefensivo.setEmptyText("");
        txtCabeceoDefensivo.setAllowBlank(false);
        txtCabeceoDefensivo.getMessages().setRegexText("El campo no puede contener letras ni caracteres especiales .");
//        txtEstatura.setEnabled(false);
        Columna2.add(txtCabeceoDefensivo, formData);

        txtCabeceoOfensivo.setName("ofensivo");
        txtCabeceoOfensivo.setFieldLabel("<font color='red'>*</font>Cabeceo Ofensivo");
        txtCabeceoOfensivo.setMaxLength(3);
        txtCabeceoOfensivo.setRegex("^[.0-9]*$");
        txtCabeceoOfensivo.setEmptyText("");
        txtCabeceoOfensivo.setAllowBlank(false);
        txtCabeceoOfensivo.getMessages().setRegexText("El campo no puede contener letras ni caracteres especiales .");
//        txtEstatura.setEnabled(false);
        Columna2.add(txtCabeceoOfensivo, formData);

        txtAceleracion.setName("aceleracion");
        txtAceleracion.setFieldLabel("<font color='red'>*</font> Aceleración");
        txtAceleracion.setMaxLength(3);
        txtAceleracion.setRegex("^[.0-9]*$");
        txtAceleracion.setEmptyText("");
        txtAceleracion.setAllowBlank(false);
        txtAceleracion.getMessages().setRegexText("El campo no puede contener letras ni caracteres especiales .");
//        txtEstatura.setEnabled(false);
        Columna2.add(txtAceleracion, formData);

        ///////////////////// Columna 3 //////////////////////////// 
        LayoutContainer Columna3 = new LayoutContainer();
        Columna2.setStyleAttribute("paddingLeft", "0px");
        layout = new FormLayout();
        layout.setLabelAlign(FormPanel.LabelAlign.LEFT);
        Columna3.setLayout(layout);

        Columna3.add(contenedorFotoUsuario);

        main.add(Columna1, new ColumnData(.33));
        main.add(Columna2, new ColumnData(.33));
        main.add(Columna3, new ColumnData(.33));
//        main.add(Columna4, new ColumnData(.25));

        form.add(main, new FormData("100%"));

        return form;
    }

    protected SelectionListener<ButtonEvent> listenerGuardar() {
        return new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {

                if (idDeportista != null) {
                    if (form.isValid()) {

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

                    if (idCtrlTecnico != null) {
                        MessageBox.confirm("Confirmación", "Seguro quiere eliminar el control técnico", new Listener<MessageBoxEvent>() {
                            @Override
                            public void handleEvent(MessageBoxEvent be) {

                                String respb = be.getButtonClicked().getText();

                                if (respb.equalsIgnoreCase("yes") || respb.equalsIgnoreCase("sí")) {

                                    getServiceControlTecnico().eliminarEntidad(idCtrlTecnico, new AsyncCallback<RespuestaRPC<ControlTecnico>>() {

                                        @Override
                                        public void onFailure(Throwable caught) {
                                            Info.display("Eliminar", "No Elimino el control técnico" + idCtrlTecnico);
                                        }

                                        @Override
                                        public void onSuccess(RespuestaRPC<ControlTecnico> result) {
                                            Info.display("Eliminar", "Se eliminó correctamente el control técnico");
                                            limpiar();
                                        }
                                    });

                                }

                            }
                        });
                    } else {
                        MessageBox.alert("Advertencia!", "Debe seleccionar un control técnico a eliminar", null);
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

                    String base = GWT.getModuleBaseURL() + "../html/reportes/HistoricoControlTecnico/";
                    // deportista seleccionado
                    redireccionarA(base + idDeportista);

                } else {
                    MessageBox.alert("Alerta", "Debe seleccionar primero un deportista", null);
                }

            }
        };
    }

    public ControlTecnico obtenerDatosFormulario() {

        ControlTecnico controltecnico = new ControlTecnico();
        controltecnico.setIdDeportista(new Deportista(idDeportista));
        controltecnico.setNombrerecepcion30seg(txtRecepcion30seg.getValue().intValue());
        controltecnico.setNombreprecisionpase15seg(txtPrecisionPase15seg.getValue().intValue());
        controltecnico.setNombreprecisiondisparoempeine(txtPrecisionDisparoEmpeine.getValue().intValue());
        controltecnico.setNombrepotenciaremate(txtPotenciaRemate.getValue().intValue());
        controltecnico.setNombrecontrolbalon50seg(txtControlBalon50seg.getValue().intValue());
        controltecnico.setNombrecabeceodefensivo(txtCabeceoDefensivo.getValue().intValue());
        controltecnico.setNombrecabeceoofensivo(txtCabeceoOfensivo.getValue().intValue());
        controltecnico.setNombreconduccion(txtConduccion.getValue().intValue());
        controltecnico.setNombreaceleracion(txtAceleracion.getValue().intValue());
        controltecnico.setFecha(DtFecha.getValue());

        return controltecnico;
    }

      public void limpiar() {
        form.reset();
        idCtrlTecnico = null;
        cargar();
    }
    public void limpiarDeportista() {
        form.reset();
        idCtrlTecnico = null;
        idDeportista=null;
        cargar();
        cargarFotoNoDiposible();
        lbNombreDep.setText("");
    }

    public void cargar() {
        try {
            loader.load(0, 1000);
            loader.load(0, 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void guardar(ControlTecnico controltecnico) {

        getServiceControlTecnico().guardarEntidad(controltecnico, new AsyncCallback<RespuestaRPC<ControlTecnico>>() {

            @Override
            public void onFailure(Throwable caught) {
                MessageBox.alert("Error", "No guardo el onrol técnico", null);
            }

            @Override
            public void onSuccess(RespuestaRPC<ControlTecnico> result) {
                Info.display("Guardar", "Guardo correctamente el conrol técnico ");
                cargar();
                form.reset();
            }
        });
    }

    public RPCAdminControlTecnicoAsync getServiceControlTecnico() {
        RPCAdminControlTecnicoAsync svc = (RPCAdminControlTecnicoAsync) GWT.create(RPCAdminControlTecnico.class);
        ServiceDefTarget endpoint = (ServiceDefTarget) svc;
        endpoint.setServiceEntryPoint("services/RPCAdminControlTecnico");
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
