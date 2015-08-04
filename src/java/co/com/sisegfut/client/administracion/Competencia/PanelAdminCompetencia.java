/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.client.administracion.Competencia;

import co.com.sisegfut.client.aaI18N.Main;
import static co.com.sisegfut.client.administracion.deportista.PanelInfoGeneral.ACTIVOS;
import co.com.sisegfut.client.datos.dominio.Competencia;
import co.com.sisegfut.client.datos.dominio.Rivales;
import co.com.sisegfut.client.datos.dominio.Torneos;
import co.com.sisegfut.client.datos.dominio.dto.DTOCompetencia;
import co.com.sisegfut.client.entidades.RespuestaRPC;
import co.com.sisegfut.client.util.Formatos;
import co.com.sisegfut.client.util.Resources;
import co.com.sisegfut.client.util.combox.ComboBoxRival;
import co.com.sisegfut.client.util.combox.ComboBoxTorneo;
import co.com.sisegfut.client.util.rpc.RPCAdminCompetencia;
import co.com.sisegfut.client.util.rpc.RPCAdminCompetenciaAsync;
import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.Style.IconAlign;
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
import com.extjs.gxt.ui.client.event.ComponentEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.GridEvent;
import com.extjs.gxt.ui.client.event.IconButtonEvent;
import com.extjs.gxt.ui.client.event.KeyListener;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.MessageBoxEvent;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.ToolButton;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.DateField;
import com.extjs.gxt.ui.client.widget.form.FormButtonBinding;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.form.Time;
import com.extjs.gxt.ui.client.widget.form.TimeField;
import com.extjs.gxt.ui.client.widget.grid.BufferView;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.ColumnLayout;
import com.extjs.gxt.ui.client.widget.layout.FillLayout;
import com.extjs.gxt.ui.client.widget.layout.FormData;
//import com.extjs.gxt.ui.client.widget.layout.ColumnData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.extjs.gxt.ui.client.widget.layout.RowData;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.extjs.gxt.ui.client.widget.toolbar.PagingToolBar;
import com.extjs.gxt.ui.client.widget.toolbar.SeparatorToolItem;
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
public class PanelAdminCompetencia extends LayoutContainer {

    private ContentPanel panel2;
    private BorderLayoutData dataWest;
    private BorderLayoutData dataCenter;
    private PagingLoader<PagingLoadResult<ModelData>> loaderCompetencia;
    private ListStore<Competencia> storeCompetencia;
    final PagingToolBar PgtoolBar = new PagingToolBar(50);
    private Grid<Competencia> gridCompetencia;
    private Date fechaActividad = new Date();

    // Formulario compromiso
//    private TextField<String> txtLocal = new TextField<String>();
//    private TextField<String> txtRival = new TextField<String>();
    private TextField<String> txtLugar = new TextField<String>();
    private NumberField txtGolesAnfitrion = new NumberField();
    private NumberField txtGolesRival = new NumberField();
    private TextArea txtObservaciones;

    private ComboBoxTorneo comboBoxTorneo;
    private ComboBoxTorneo comboBoxTorneo2;
//    private ComboBoxCategoria cbxCategoria = new ComboBoxCategoria(ACTIVOS);
    private DateField DtFecha = new DateField();
    private TimeField tmHora = new TimeField();
    private DateField DtFecha2 = new DateField();
    private FormPanel fpCompromiso;
    private ContentPanel cpCuerpoTecCom;
    PanelAdminPestComp adminPestComp = new PanelAdminPestComp();
    PanelAdminCuerpoTecnico adminCuerpoTecnico = new PanelAdminCuerpoTecnico();
    private ContentPanel cp;
    private ContentPanel cp2;
    private Button btnGuardarCompromiso;
    private Button btnEditar;
    private Button btnBuscarComp;
    private Button btnGuardarCompetencia;
    private Button btnNuevaCompetencia;
    private FormButtonBinding binding;
    private Long idCompetencia;
    private Competencia competenciaFinalizar;
    private Window wFinalizarCompetencia;
    private Window wBuscar;
    private ComboBoxRival cbxRival = null;
    private ComboBoxRival cbxRival2 = null;

    private Date fechaFiltroComp = new Date();
    private Long IdTorneo = null;
    private Long idRival = null;
    private Long idJugadorComodin = null;
    private Button btnEditarCompetencia;
    private Button btnConsularCompetencia;
    private Button btnEstadisticaCompetencia;
    private PanelEstadisticas panelEstadisticas = new PanelEstadisticas();

    private Main myConstants;
    private String observacionesCompetencia;
    private DTOCompetencia dTOCompetencia = new DTOCompetencia();

    @Override
    protected void onRender(Element parent, int index) {
        super.onRender(parent, index);
        MessageBox boxWait = MessageBox.wait("Competencia",
                "Cargando los datos, por favor espere...", "Cargando...");

        myConstants = (Main) GWT.create(Main.class);

        BorderLayout layout = new BorderLayout();
        setLayout(layout);
        setStyleAttribute("padding", "0px");

        cp = new ContentPanel();
        cp2 = new ContentPanel();

        cp.setStyleAttribute("padding", "0px");
        cp.setLayout(new RowLayout(Style.Orientation.VERTICAL));
        cp.setSize(500, 500);
        cp.setFrame(true);
        cp.setHeaderVisible(false);

        cp2.setHeaderVisible(false);
        cp2.setLayout(new FillLayout());
        cp2.setScrollMode(Style.Scroll.AUTO);
//        cp2.setEnabled(false);
        adminPestComp.tabpanelCompetencia.setEnabled(false);

        fpCompromiso = crearFormulario();

        btnGuardarCompromiso = new Button("Guardar Siguiente", listenerGuardarCompromiso());
        btnGuardarCompromiso.setIconAlign(IconAlign.RIGHT);
        btnGuardarCompromiso.setIcon(Resources.ICONS.iconoLogin());

        btnBuscarComp = new Button("Buscar Competencia", listenerBuscar());
        btnBuscarComp.setIcon(Resources.ICONS.iconoBuscar());

        ToolBar toolBar = new ToolBar();
        toolBar.setSpacing(2);
//        toolBar.add(new SeparatorToolItem());
//        toolBar.add(btnBuscarComp);
//        fpCompromiso.setTopComponent(toolBar);    

        binding = new FormButtonBinding(fpCompromiso);
        binding.addButton(btnGuardarCompromiso);

//        fpCompromiso.addButton(btnEditar);
        fpCompromiso.addButton(btnBuscarComp);
        fpCompromiso.addButton(btnGuardarCompromiso);

        fpCompromiso.setButtonAlign(Style.HorizontalAlignment.CENTER);

        cpCuerpoTecCom = adminCuerpoTecnico;
        cpCuerpoTecCom.disable();

        cp.add(fpCompromiso, new RowData(1, 0.5, new Margins(0)));
        cp.add(cpCuerpoTecCom, new RowData(1, 0.5, new Margins(0)));
        cp2.add(adminPestComp);

        panel2 = new ContentPanel();
        panel2.setHeading("Control de competencia");
        panel2.setStyleAttribute("padding", "0px");
        panel2.setIcon(Resources.ICONS.iconoChronometer());
        panel2.setLayout(new RowLayout(Style.Orientation.HORIZONTAL));
        panel2.setSize(500, 500);
        panel2.setFrame(true);
        panel2.setHeaderVisible(true);

        panel2.add(cp, new RowData(0.4, 1, new Margins(0)));

        panel2.add(cp2, new RowData(0.6, 1, new Margins(0)));

        add(panel2);

        //Agrego un listener para escuchar el cambio de tamanio del panel
        this.addListener(Events.Resize, new Listener<BoxComponentEvent>() {
            @Override
            public void handleEvent(final BoxComponentEvent event) {
                panel2.setWidth(event.getWidth());
                panel2.setHeight(event.getHeight());
                cp2.setHeight(event.getHeight() - 10);
            }
        });
        cp.addListener(Events.Resize, new Listener<BoxComponentEvent>() {
            @Override
            public void handleEvent(final BoxComponentEvent event) {
                cpCuerpoTecCom.setWidth(event.getWidth() - 20);
                fpCompromiso.setWidth(event.getWidth() - 20);
                cpCuerpoTecCom.setHeight(event.getHeight() - 100);
                fpCompromiso.setHeight(event.getHeight() - 500);
            }
        });
//        cp2.addListener(Events.Resize, new Listener<BoxComponentEvent>() {
//            @Override
//            public void handleEvent(final BoxComponentEvent event) {
////                cpControlGeneralJuego.setWidth(event.getWidth() - 20);
//                cpControlGeneralJuego.setHeight(event.getHeight() - 10);
//            }
//        });

        btnGuardarCompetencia = new Button("Finalizar competencia", listenerFinalizarCompetencia());
        btnGuardarCompetencia.setIconAlign(IconAlign.RIGHT);
        btnGuardarCompetencia.setIcon(Resources.ICONS.iconoGuardar());
        btnGuardarCompetencia.setEnabled(false);
        btnNuevaCompetencia = new Button("Nueva competencia", listenerNuevaCompetencia());
        btnNuevaCompetencia.setIconAlign(IconAlign.LEFT);
        btnNuevaCompetencia.setIcon(Resources.ICONS.iconoNuevaNota());
        btnNuevaCompetencia.setEnabled(true);

        panel2.addButton(btnNuevaCompetencia);
        panel2.addButton(btnGuardarCompetencia);
        panel2.setButtonAlign(Style.HorizontalAlignment.CENTER);
        boxWait.close();

    }

    ///////////////////////////// METODOS ////////////////////////////////////////////////////////////////////////////
    public FormPanel crearFormulario() {

        FormPanel form = new FormPanel();
        form.setScrollMode(Style.Scroll.AUTO);
        form.setFrame(true);
        form.setHeaderVisible(true);
        form.setHeading("Datos iniciales competencia");
//        form.setPadding(5);
        form.setSize("100%", "100%");
        ToolButton btnayudaCompetencia = new ToolButton("x-tool-help", new SelectionListener<IconButtonEvent>() {
            @Override
            public void componentSelected(IconButtonEvent ce) {
                abrirVentanaAyuda(myConstants.ayudaPanelCompetencia());
            }
        });
        btnayudaCompetencia.setTitle("Ayuda ");
        form.getHeader().addTool(btnayudaCompetencia);

        // Layout Main que contiene todas las columnas 
        FormData formData = new FormData("-50");
        LayoutContainer main = new LayoutContainer();
        main.setLayout(new ColumnLayout());
        // main.setHeight(100);
        main.setAutoHeight(afterRender);
//        form.add(lbNombreDep);
        ///////////////////// Columna 1 ////////////////////////////  
        LayoutContainer Columna1 = new LayoutContainer();
        Columna1.setStyleAttribute("padding", "0px");

        FormLayout layout = new FormLayout();
        layout.setLabelAlign(FormPanel.LabelAlign.TOP);
        Columna1.setLayout(layout);

        DtFecha.setName("fechaCompromiso");
        DtFecha.setAllowBlank(false);
        DtFecha.setFormatValue(true);
        DtFecha.setValue(new Date());
        DtFecha.setFieldLabel("<font color='red'>*</font> Fecha ");
        Columna1.add(DtFecha, formData);

        tmHora.setFieldLabel("<font color='red'>*</font> Hora");
//        tmHora.addPlugin(plugin);
//        tmHora.setData("text", "Seleccione la hora");
        tmHora.setEmptyText("Seleccione la hora");
        DateTimeFormat fmt = DateTimeFormat.getFormat("hh:mm aa");
        tmHora.setFormat(fmt);
//        tmHora.setValue(new Time(new Date()));
        tmHora.setIncrement(30);
        tmHora.setForceSelection(true);
        tmHora.setTriggerAction(ComboBox.TriggerAction.ALL);
        tmHora.setEditable(false);
        tmHora.setAllowBlank(false);
        Columna1.add(tmHora, formData);

        txtLugar.setName("local");
        txtLugar.setFieldLabel("<font color='red'>*</font> Lugar");
        txtLugar.setEmptyText("");
        txtLugar.setAllowBlank(false);
        Columna1.add(txtLugar, formData);
        ///////////////////// Columna 2 //////////////////////////// 
        LayoutContainer Columna2 = new LayoutContainer();
        Columna2.setStyleAttribute("padding", "0px");
        layout = new FormLayout();
        layout.setLabelAlign(FormPanel.LabelAlign.TOP);
        Columna2.setLayout(layout);

        comboBoxTorneo = new ComboBoxTorneo(ComboBoxTorneo.ACTIVOS);
        comboBoxTorneo.setLabelSeparator("<font color='red'>*</font> Torneo");
        comboBoxTorneo.setAllowBlank(false);
        comboBoxTorneo.setForceSelection(true);
        Columna2.add(comboBoxTorneo, formData);

        comboBoxTorneo.addListener(Events.SelectionChange, new Listener<BaseEvent>() {
            @Override
            public void handleEvent(BaseEvent be) {
                if (comboBoxTorneo.isValid()) {
                    cbxRival.setIdTorneoElegido(comboBoxTorneo.getTorneosElegido().getId());
                    cbxRival.enable();
                } else {
                    cbxRival.disable();
                }
            }
        });
        cbxRival = new ComboBoxRival(ACTIVOS);
        cbxRival.setLabelSeparator("<font color='red'>*</font> Rival");
        cbxRival.setAllowBlank(false);
        cbxRival.disable();
        Columna2.add(cbxRival, formData);

        main.add(Columna1, new com.extjs.gxt.ui.client.widget.layout.ColumnData(.5));
        main.add(Columna2, new com.extjs.gxt.ui.client.widget.layout.ColumnData(.5));

        form.add(main, new FormData("100%"));

        return form;
    }

    protected SelectionListener<ButtonEvent> listenerFinalizarCompetencia() {
        return new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
              if(adminCuerpoTecnico.validarHayCuerpoTecnicoComp(idCompetencia)){
                wFinalizarCompetencia = new Window();
                wFinalizarCompetencia.setSize(300, 160);
                wFinalizarCompetencia.setPlain(true);
                wFinalizarCompetencia.setModal(true);
                wFinalizarCompetencia.setClosable(false);
                wFinalizarCompetencia.setBlinkModal(true);
                wFinalizarCompetencia.setHeading("Observaciones");
                wFinalizarCompetencia.setLayout(new FillLayout());
                FormData formData = new FormData("10");

                txtObservaciones = new TextArea();
                txtObservaciones.setEmptyText("Ingrese las observaciones o comentarios de la competencia");

                wFinalizarCompetencia.add(txtObservaciones, formData);
                Button btnAceptar = new Button("Aceptar", listenerAceptarObservaciones());
                wFinalizarCompetencia.addButton(btnAceptar);
                wFinalizarCompetencia.setButtonAlign(Style.HorizontalAlignment.CENTER);
                wFinalizarCompetencia.setFocusWidget(wFinalizarCompetencia.getButtonBar().getItem(0));

                if (idCompetencia != null) {
                    getServiceCompetencia().obtenerCompetenciaxId(idCompetencia, new AsyncCallback<Competencia>() {

                        @Override
                        public void onFailure(Throwable caught) {
                            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                        }

                        @Override
                        public void onSuccess(Competencia result) {
                            competenciaFinalizar = result;
                            MessageBox.confirm("Confirmación", "¿Seguro que desea finalizar la competencia \n <br/>" + competenciaFinalizar.getCompromiso(), new Listener<MessageBoxEvent>() {
                                @Override
                                public void handleEvent(MessageBoxEvent be) {

                                    String respb = be.getButtonClicked().getText();

                                    if (respb.equalsIgnoreCase("yes") || respb.equalsIgnoreCase("sí")) {
                                        wFinalizarCompetencia.show();
                                    }

                                }
                            });
                        }
                    });

                } else {
                    MessageBox.alert("Alerta", "No tiene competencia ", null);
                }
            }else{
              MessageBox.alert("Cuerpo técnico", "Para poder finalizar la competencia debe tener por lo menos una persona en el cuerpo tecnico ", null);
              }
            }
        };

    }

//    public FormPanel crearFormularioBusqueda() {
//
//        FormPanel fpCompromiso = new FormPanel();
//        fpCompromiso.setScrollMode(Style.Scroll.AUTO);
//        fpCompromiso.setFrame(true);
//        fpCompromiso.setHeaderVisible(false);
////        form.setPadding(5);
//        fpCompromiso.setSize("100%", "100%");
//
//        // Layout Main que contiene todas las columnas 
//        FormData formData = new FormData("-10");
//        LayoutContainer main = new LayoutContainer();
//        main.setLayout(new ColumnLayout());
//        // main.setHeight(100);
//        main.setAutoHeight(true);
//        ///////////////////// Columna 1 ////////////////////////////  
//        LayoutContainer Columna1 = new LayoutContainer();
//        Columna1.setStyleAttribute("padding", "10px");
//
//        FormLayout layout = new FormLayout();
//        layout.setLabelAlign(FormPanel.LabelAlign.TOP);
//        Columna1.setLayout(layout);
//
//        DtFecha2.setName("fechaCompromiso");
//        DtFecha.setFieldLabel("<font color='red'>*</font> Fecha ");
//        DtFecha2.setLabelSeparator("Fecha competencia ");
//        DtFecha2.setValue(new Date());
////        DtFecha2.setAllowBlank(false);
////        DtFecha2.setFormatValue(true);
//
//        Columna1.add(DtFecha2, formData);
//
//        ///////////////////// Columna 2 //////////////////////////// 
//        LayoutContainer Columna2 = new LayoutContainer();
//        Columna2.setStyleAttribute("padding", "10px");
//        layout = new FormLayout();
//        layout.setLabelAlign(FormPanel.LabelAlign.TOP);
//        Columna2.setLayout(layout);
//
//        comboBoxTorneo2 = new ComboBoxTorneo(ComboBoxTorneo.ACTIVOS);
//        comboBoxTorneo2.setLabelSeparator("Torneo");
//        comboBoxTorneo2.setEditable(false);
//        comboBoxTorneo2.setForceSelection(true);
//
//        comboBoxTorneo2.addListener(Events.SelectionChange, new Listener<BaseEvent>() {
//            @Override
//            public void handleEvent(BaseEvent be) {
//                if (comboBoxTorneo2.getValue() != null) {
//                    cbxRival2.setIdTorneoElegido(comboBoxTorneo2.getTorneosElegido().getId());
//                    cbxRival2.enable();
//                } else {
//                    cbxRival2.disable();
//                }
//            }
//        });
//        Columna2.add(comboBoxTorneo2, formData);
//
//        ///////////////////// Columna 3 //////////////////////////// 
//        LayoutContainer Columna3 = new LayoutContainer();
//        Columna3.setStyleAttribute("padding", "10px");
//        layout = new FormLayout();
//        layout.setLabelAlign(FormPanel.LabelAlign.TOP);
//        Columna3.setLayout(layout);
//
//        cbxRival2 = new ComboBoxRival(ACTIVOS);
//        cbxRival2.setLabelSeparator("Rival");
//        cbxRival2.disable();
//        cbxRival2.setEditable(false);
//        Columna3.add(cbxRival2, formData);
//
//        main.add(Columna1, new com.extjs.gxt.ui.client.widget.layout.ColumnData(.33));
//        main.add(Columna2, new com.extjs.gxt.ui.client.widget.layout.ColumnData(.33));
//        main.add(Columna3, new com.extjs.gxt.ui.client.widget.layout.ColumnData(.33));
//
//        fpCompromiso.add(main, new FormData("100%"));
//
//        Button btnBuscarCompetencia = new Button("Buscar", new SelectionListener<ButtonEvent>() {
//
//            @Override
//            public void componentSelected(ButtonEvent ce) {
//                if (DtFecha2.getValue() != null) {
//                    DateTimeFormat fmt = DateTimeFormat.getFormat("dd-MM-yyyy");
//                    fechaFiltroComp = fmt.parse(fmt.format(DtFecha2.getValue()));
////                    fechaFiltroComp = DtFecha2.getValue();
//                }
//                if (comboBoxTorneo2.getValue() != null) {
//                    IdTorneo = comboBoxTorneo2.getTorneosElegido().getId();
//                }
//                if (cbxRival2.getValue() != null) {
//                    idRival = cbxRival2.getRivalElegido().getId();
//                }
//                cargarGridCompetencia();
//
//            }
//        });
//        Button btnLimpiarForCompetencia = new Button("Limpiar", new SelectionListener<ButtonEvent>() {
//
//            @Override
//            public void componentSelected(ButtonEvent ce) {
//                fechaFiltroComp = null;
//                DtFecha2.reset();
//                IdTorneo = null;
//                comboBoxTorneo2.recargar();
//
//                idRival = null;
//                cbxRival2.recargar();
//                cbxRival2.disable();
//                cargarGridCompetencia();
//            }
//        });
//        fpCompromiso.addButton(btnBuscarCompetencia);
//        fpCompromiso.addButton(btnLimpiarForCompetencia);
//        fpCompromiso.setButtonAlign(Style.HorizontalAlignment.CENTER);
//
//        return fpCompromiso;
//
//    }

    protected SelectionListener<ButtonEvent> listenerGuardarCompromiso() {
        return new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {

                if (fpCompromiso.isValid()) {

                    Competencia competencia = obtenerFormulario();
                    getServiceCompetencia().guardarEntidad(competencia, new AsyncCallback<RespuestaRPC<Competencia>>() {

                        @Override
                        public void onFailure(Throwable caught) {
                            MessageBox.alert("Alerta", "No fue posible guardar la competencia: ", null);
                        }

                        @Override
                        public void onSuccess(RespuestaRPC<Competencia> result) {
                            idCompetencia = result.getObjetoRespuesta().getId();
//                            Info.display("Competencia", "Se entrega el id competencia" + idCompetencia);

                            adminPestComp.panelAdminConvocados.setIdCompetencia(idCompetencia);
                            adminPestComp.cargarJugadores(comboBoxTorneo.getTorneosElegido().getCategoria().getId());
//                            adminPestComp.panelAdminConvocados.setIdCompetencia(1l);
                            adminPestComp.panelAdminControlDisciplinario.setIdCompetencia(idCompetencia);
                            adminPestComp.panelAdminControlDisciplinario.setIdJugadorComodin(cbxRival.getRivalElegido().getJugadorComodin().getId());
                            adminPestComp.panelAdminSituaciones.setIdCompetencia(idCompetencia);
                            adminCuerpoTecnico.setIdCompetencia(idCompetencia);
                            adminCuerpoTecnico.cbxPersonal.setIdCompetencia(idCompetencia);
                            habilitarPaneles();
                        }
                    });
                }

            }
        };
    }

    public void habilitarPaneles() {
        binding.removeButton(btnGuardarCompromiso);
        adminPestComp.tabpanelCompetencia.setEnabled(true);
        adminPestComp.tabItemSituaciones.disable();
        adminPestComp.tabItemControlJuego.disable();
        adminPestComp.panelAdminConvocados.cargarGridSuplente();
        adminPestComp.panelAdminConvocados.cargarGridTitulares();
        adminPestComp.panelAdminConvocados.cbxCategoria.seleccionar(comboBoxTorneo.getTorneosElegido().getCategoria().getId());
        fpCompromiso.setEnabled(false);
        cpCuerpoTecCom.setEnabled(true);
        btnGuardarCompromiso.setEnabled(false);
        btnGuardarCompetencia.enable();
        adminCuerpoTecnico.cbxPersonal.recargar();
    }

    public Competencia obtenerFormulario() {
        Competencia competencia = new Competencia();

        competencia.setAnfitrion("POLITECNICO JIC");
        competencia.setRival(cbxRival.getRivalElegido());

        fechaActividad = DtFecha.getValue();
        System.out.println("hora actividad" + Formatos.Hora(tmHora.getDateValue()));
        fechaActividad.setTime(tmHora.getDateValue().getTime());
        System.out.println("fecha  " + fechaActividad);

        competencia.setFecha(fechaActividad);
        competencia.setLugar(txtLugar.getValue());
        competencia.setTorneo(comboBoxTorneo.getTorneosElegido());
        competencia.setObservacion("");
        competencia.setGolesAnfitrion(0);
        competencia.setGolesRival(0);
        competencia.setFinalizaCompentcia(false);
        return competencia;
    }

    public void cargarGridCompetencia() {
        try {
            loaderCompetencia.load(0, 100);
            loaderCompetencia.load(0, 100);
        } catch (Exception e) {
            e.printStackTrace();
        }

//        loader.load(0, 50);
    }

    protected SelectionListener<ButtonEvent> listenerBuscar() {
        return new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                wBuscar = new Window();
                wBuscar.setSize(800, 450);
                wBuscar.setPlain(true);
                wBuscar.setModal(true);
                wBuscar.setClosable(false);
                wBuscar.setBlinkModal(true);
                wBuscar.setHeading("Buscar competencia");
                wBuscar.setLayout(new FillLayout());

                wBuscar.add(crearGridCompetencia());

                btnEditarCompetencia = new Button("Editar", Resources.ICONS.iconoModificar(), listenerEditarCompetencia());
                btnEditarCompetencia.disable();
                btnConsularCompetencia = new Button("Consultar", Resources.ICONS.iconoBuscar(), listenerConsultarCompetencia());
                btnConsularCompetencia.disable();

                Button btnCancelarBusCompetencia = new Button("Cancelar", Resources.ICONS.iconoCancelar(), new SelectionListener<ButtonEvent>() {

                    @Override
                    public void componentSelected(ButtonEvent ce) {
                        wBuscar.hide();
                        limpiarPanelBuscarCompetencia();

                    }
                });

                btnEstadisticaCompetencia = new Button("Ver Estadisticas", Resources.ICONS.iconoGraficaBarras1(), new SelectionListener<ButtonEvent>() {

                    @Override
                    public void componentSelected(ButtonEvent ce) {
                        panelEstadisticas.setIdCompetencia(dTOCompetencia.getIdCompetencia());
                        panelEstadisticas.show();
                    }
                });
                btnEstadisticaCompetencia.disable();

                wBuscar.getHeader().addTool(new ToolButton("x-tool-help", new SelectionListener<IconButtonEvent>() {
                    @Override
                    public void componentSelected(IconButtonEvent ce) {
                        abrirVentanaAyuda(myConstants.ayudaPanelCompetenciaBuscar());
                    }
                }));
                wBuscar.addButton(btnEstadisticaCompetencia);
                wBuscar.addButton(btnEditarCompetencia);
                wBuscar.addButton(btnConsularCompetencia);
                wBuscar.addButton(btnCancelarBusCompetencia);
//                
                wBuscar.setButtonAlign(Style.HorizontalAlignment.CENTER);
//                
                wBuscar.setFocusWidget(wBuscar.getButtonBar().getItem(0));

                wBuscar.show();

            }
        };

    }

    public LayoutContainer crearGridCompetencia() {

        LayoutContainer lcPanelBuscarCompetencia = new LayoutContainer();
        lcPanelBuscarCompetencia.setScrollMode(Style.Scroll.AUTOY);
        lcPanelBuscarCompetencia.setLayoutData(new FillLayout());
        lcPanelBuscarCompetencia.setLayout(new RowLayout(Style.Orientation.VERTICAL));

        final RPCAdminCompetenciaAsync svc = (RPCAdminCompetenciaAsync) GWT.create(RPCAdminCompetencia.class);
        ServiceDefTarget endpoint = (ServiceDefTarget) svc;
        endpoint.setServiceEntryPoint("services/RPCAdminCompetencia");

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
        RpcProxy<PagingLoadResult<DTOCompetencia>> proxy = new RpcProxy<PagingLoadResult<DTOCompetencia>>() {
            @Override
            protected void load(Object loadConfig, AsyncCallback<PagingLoadResult<DTOCompetencia>> callback) {
                svc.obtenerCompetenciaFiltro(fechaFiltroComp, IdTorneo, idRival, callback);
            }
        };

        loaderCompetencia = new BasePagingLoader<PagingLoadResult<ModelData>>(proxy, new BeanModelReader()) {
            @Override
            protected Object newLoadConfig() {
                BasePagingLoadConfig config = new BaseFilterPagingLoadConfig();
                return config;
            }
        };
        loaderCompetencia.setRemoteSort(true);

        storeCompetencia = new ListStore<Competencia>(loaderCompetencia);
//        store.setMonitorChanges(true);

        PgtoolBar.bind(loaderCompetencia);
//        
        List<ColumnConfig> configs = new ArrayList<ColumnConfig>();

        ColumnConfig column = new ColumnConfig();
        column.setId("fecha");
        column.setAlignment(Style.HorizontalAlignment.LEFT);
        column.setHeader("Fecha");
        column.setWidth(50);
        column.setDateTimeFormat(DateTimeFormat.getFormat("EEEE dd MMMM yyyy hh:mm aa"));
        configs.add(column);

        column = new ColumnConfig();
        column.setId("compromiso");
        column.setAlignment(Style.HorizontalAlignment.LEFT);
        column.setHeader("Compromiso");
        column.setWidth(150);
        column.setRenderer(new GridCellRenderer() {

            @Override
            public Object render(ModelData model, String property, com.extjs.gxt.ui.client.widget.grid.ColumnData config, int rowIndex, int colIndex, ListStore store, Grid grid) {
                String compromiso = model.get("compromiso");
                if (compromiso == null) {
                    return "";
                } else {
                    return compromiso;
                }
            }
        });
        configs.add(column);

        column = new ColumnConfig();
        column.setId("torneo");
        column.setAlignment(Style.HorizontalAlignment.LEFT);
        column.setHeader("Torneo");
        column.setWidth(60);
        column.setRenderer(new GridCellRenderer() {

            @Override
            public Object render(ModelData model, String property, com.extjs.gxt.ui.client.widget.grid.ColumnData config, int rowIndex, int colIndex, ListStore store, Grid grid) {
                String torneo = model.get("torneo");
                if (torneo == null) {
                    return "";
                } else {
                    return torneo;
                }
            }
        });
        configs.add(column);

        column = new ColumnConfig();
        column.setId("lugar");
        column.setAlignment(Style.HorizontalAlignment.LEFT);
        column.setHeader("Lugar");
        column.setWidth(80);
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

        column = new ColumnConfig();
        column.setId("finalizo");
        column.setAlignment(Style.HorizontalAlignment.CENTER);
        column.setHeader("Finalizo?");
        column.setWidth(45);
        column.setRenderer(new GridCellRenderer() {

            @Override
            public Object render(ModelData model, String property, com.extjs.gxt.ui.client.widget.grid.ColumnData config, int rowIndex, int colIndex, ListStore store, Grid grid) {
                String finalizo = model.get("finalizo");
                if (finalizo == null) {
                    return "";
                } else {
                    String style = "";
                    if (finalizo.equalsIgnoreCase("SI")) {
                        style = "green";
                    } else {
                        style = "red";
                    }
                    return "<span style='color:" + style + "'><b>" + finalizo + "</b></span>";
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

        ToolBar toolbarBuscar = new ToolBar();
        toolbarBuscar.setSpacing(10);
        
        DtFecha2.setValue(new Date());

        comboBoxTorneo2 = new ComboBoxTorneo(ComboBoxTorneo.ACTIVOS);
        comboBoxTorneo2.setLabelSeparator("Torneo");
        comboBoxTorneo2.setEditable(false);
        comboBoxTorneo2.setForceSelection(true);

        comboBoxTorneo2.addListener(Events.SelectionChange, new Listener<BaseEvent>() {
            @Override
            public void handleEvent(BaseEvent be) {
                if (comboBoxTorneo2.getValue() != null) {
                    cbxRival2.setIdTorneoElegido(comboBoxTorneo2.getTorneosElegido().getId());
                    cbxRival2.enable();
                } else {
                    cbxRival2.disable();
                }
            }
        });
        cbxRival2 = new ComboBoxRival(ACTIVOS);
        cbxRival2.setLabelSeparator("Rival");
        cbxRival2.disable();
        cbxRival2.setEditable(false);
        Button btnBuscarCompetencia = new Button("Buscar", Resources.ICONS.iconoBuscar(), new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                buscarCompetencia();
            }
        });
        Button btnLimpiarForCompetencia = new Button("Limpiar", Resources.ICONS.iconoLimpiar(), new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                fechaFiltroComp = null;
                DtFecha2.reset();
                IdTorneo = null;
                comboBoxTorneo2.recargar();
                idRival = null;
                cbxRival2.recargar();
                cbxRival2.disable();
                btnEstadisticaCompetencia.disable();
                btnEditarCompetencia.disable();
                btnConsularCompetencia.disable();
                cargarGridCompetencia();

            }
        });
        // se ejecuta cuando se presiona la tecla enter.
        DtFecha2.addKeyListener(new KeyListener() {

            @Override
            public void componentKeyPress(ComponentEvent event) {
                if (event.getKeyCode() == 13) {
                    buscarCompetencia();
                }
            }
        });
        comboBoxTorneo2.addKeyListener(new KeyListener() {

            @Override
            public void componentKeyPress(ComponentEvent event) {
                if (event.getKeyCode() == 13) {
                    buscarCompetencia();
                }
            }
        });
        cbxRival2.addKeyListener(new KeyListener() {

            @Override
            public void componentKeyPress(ComponentEvent event) {
                if (event.getKeyCode() == 13) {
                    buscarCompetencia();
                }
            }
        });

        toolbarBuscar.add(DtFecha2);
        toolbarBuscar.add(new SeparatorToolItem());
        toolbarBuscar.add(comboBoxTorneo2);
        toolbarBuscar.add(new SeparatorToolItem());
        toolbarBuscar.add(cbxRival2);
        toolbarBuscar.add(new SeparatorToolItem());
        toolbarBuscar.add(btnBuscarCompetencia);
        toolbarBuscar.add(new SeparatorToolItem());
        toolbarBuscar.add(btnLimpiarForCompetencia);

        cpGrid.setTopComponent(toolbarBuscar);

//        ContentPanel cpForm = new ContentPanel();
//        cpForm.setHeaderVisible(false);
//        cpForm.setLayout(new FillLayout(Style.Orientation.HORIZONTAL));
//        cpForm.setFrame(true);
//        cpForm.setBodyBorder(false);
//        cpForm.setBorders(false);
//
//        FormPanel panel = crearFormularioBusqueda();
////        panel.setLayout(new FillLayout(Style.Orientation.HORIZONTAL));
//        cpForm.add(panel);
        gridCompetencia = new Grid<Competencia>(storeCompetencia, cm);
        gridCompetencia.setView(new BufferView());

        gridCompetencia.getSelectionModel().setSelectionMode(Style.SelectionMode.SINGLE);

        /**
         * Escucho cuando se seleciona un movimiento para cargar el formulario
         * manualmente
         */
        gridCompetencia.getSelectionModel().addListener(Events.SelectionChange, new Listener<SelectionChangedEvent<BeanModel>>() {
            @Override
            public void handleEvent(SelectionChangedEvent<BeanModel> be) {
                // formBindings.unbind();
                if (be.getSelection().size() > 0) {

                    dTOCompetencia = (DTOCompetencia) be.getSelectedItem().getBean();

//                    MessageBox.info("Competencia", "Seleccionó: "
//                            + dTOCompetencia.getFecha() + " - "
//                            + dTOCompetencia.getCompromiso()
//                            + " - Id Competencia " + dTOCompetencia.getIdCompetencia()
//                            + " id Jugador comodin " + dTOCompetencia.getIdJugadorComodin(), null);
                    if (dTOCompetencia.getFinalizo().equalsIgnoreCase("SI")) {
                        btnConsularCompetencia.enable();
                        btnEditarCompetencia.disable();
                        btnEstadisticaCompetencia.enable();
                    } else {
                        btnConsularCompetencia.disable();
                        btnEditarCompetencia.enable();
                        btnEstadisticaCompetencia.disable();
                    }

//                    idLesion = dTOLesiones.getIdLesion();
//                    txtLesion.setValue(dTOLesiones.getNombreLesion());
//                    dtFechaLesion.setValue(dTOLesiones.getFechaLesion());
//                    
                    // formBindings.bind((ModelData) be.getSelection().get(0));
                } else {
//                    formBindings.unbind();
                }
            }
        });

        gridCompetencia.addListener(Events.Attach, new Listener<GridEvent<BeanModel>>() {
            @Override
            public void handleEvent(GridEvent<BeanModel> be) {
                PagingLoadConfig config = new BaseFilterPagingLoadConfig();
                config.setOffset(0);
                config.setLimit(50);

                Map<String, Object> state = gridCompetencia.getState();
                if (state.containsKey("offset")) {
                    int offset = (Integer) state.get("offset");
                    int limit = (Integer) state.get("limit");
                    config.setOffset(offset);
                    config.setLimit(limit);
                }

                loaderCompetencia.load(config);
            }
        });
        gridCompetencia.setLoadMask(true);
        gridCompetencia.setBorders(true);
        gridCompetencia.getView().setForceFit(true);

        gridCompetencia.setStateId("pagingGridExample");
        gridCompetencia.setStateful(true);

        cpGrid.add(gridCompetencia);

//        lcPanelBuscarCompetencia.add(cpForm, new RowData(1, 0.4, new Margins(0)));
        lcPanelBuscarCompetencia.add(cpGrid, new RowData(1, 1, new Margins(0)));

        return lcPanelBuscarCompetencia;
    }

    protected SelectionListener<ButtonEvent> listenerAceptarObservaciones() {
        return new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {

                String ob = (txtObservaciones.getValue() != null) ? txtObservaciones.getValue() : "";
                competenciaFinalizar.setFinalizaCompentcia(true);
                competenciaFinalizar.setObservacion(ob);
                wFinalizarCompetencia.hide();
                getServiceCompetencia().guardarEntidad(competenciaFinalizar, new AsyncCallback<RespuestaRPC<Competencia>>() {
                    @Override
                    public void onFailure(Throwable caught) {
                        MessageBox.alert("Error", "No se fue posible finalizar la competencia.", null);
                    }

                    @Override
                    public void onSuccess(RespuestaRPC<Competencia> result) {
                        limpiarCompetencia();
                    }
                });

            }
        };

    }

    protected SelectionListener<ButtonEvent> listenerNuevaCompetencia() {
        return new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                MessageBox.confirm("Confirmación", "¿Seguro que desea realizar una nueva competencia? ", new Listener<MessageBoxEvent>() {
                    @Override
                    public void handleEvent(MessageBoxEvent be) {

                        String respb = be.getButtonClicked().getText();

                        if (respb.equalsIgnoreCase("yes") || respb.equalsIgnoreCase("sí")) {
                            limpiarCompetencia();
                        }

                    }
                });
            }
        };

    }

    public void buscarCompetencia() {
        if (DtFecha2.getValue() != null) {
            DateTimeFormat fmt = DateTimeFormat.getFormat("dd-MM-yyyy");
            fechaFiltroComp = fmt.parse(fmt.format(DtFecha2.getValue()));
//                    fechaFiltroComp = DtFecha2.getValue();
        }
        if (comboBoxTorneo2.getValue() != null) {
            IdTorneo = comboBoxTorneo2.getTorneosElegido().getId();
        }
        if (cbxRival2.getValue() != null) {
            idRival = cbxRival2.getRivalElegido().getId();
        }
        cargarGridCompetencia();
    }

    public void limpiarCompetencia() {
        this.mask("Reiniciando competencia....");
        idCompetencia = null;
        binding.addButton(btnGuardarCompromiso);
        fpCompromiso.setEnabled(true);
        DtFecha.reset();
        tmHora.reset();
        comboBoxTorneo.recargar();
        cbxRival.setIdTorneoElegido(null);
        cbxRival.recargar();
        txtLugar.reset();
        cpCuerpoTecCom.setEnabled(false);
        btnGuardarCompromiso.setEnabled(true);
        btnGuardarCompetencia.disable();

        adminCuerpoTecnico.reiniciarCuerpoTecnicoCompe();

        adminPestComp.panelAdminConvocados.reiniciarConvocados();
        adminPestComp.panelAdminControlDisciplinario.reiniciarControlJuego();
        adminPestComp.panelAdminSituaciones.reiniciarSituaciones();
        adminPestComp.panelAdminConvocados.disable();
        adminPestComp.tabItemSituaciones.disable();
        adminPestComp.tabItemControlJuego.disable();
        adminPestComp.getTabpanel().setSelection(adminPestComp.tabItemConvocados);
        btnGuardarCompromiso.setVisible(true);
        cbxRival.recargar();
        cbxRival.disable();
//        adminPestComp.panelAdminSituaciones.habilitarBotonesSituaciones(true);
        this.unmask();
    }

    public void limpiarPanelBuscarCompetencia() {
        fechaFiltroComp = new Date();
        DtFecha2.setValue(fechaFiltroComp);
        btnEstadisticaCompetencia.disable();
        btnEditarCompetencia.disable();
        btnConsularCompetencia.disable();
        IdTorneo = null;
        comboBoxTorneo2.recargar();
        idRival = null;
        cbxRival2.recargar();
        cargarGridCompetencia();

    }

    public RPCAdminCompetenciaAsync getServiceCompetencia() {
        RPCAdminCompetenciaAsync svc = (RPCAdminCompetenciaAsync) GWT.create(RPCAdminCompetencia.class);
        ServiceDefTarget endpoint = (ServiceDefTarget) svc;
        endpoint.setServiceEntryPoint("services/RPCAdminCompetencia");
        return svc;
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

    public void cargarDatosCompetencia(Competencia competencia, boolean habilitar) {

        DtFecha.setValue(competencia.getFecha());
        tmHora.setDateValue(competencia.getFecha());
        comboBoxTorneo.seleccionar(competencia.getTorneo().getId());
        cbxRival.setIdTorneoElegido(competencia.getTorneo().getId());
        cbxRival.recargar();

//        cbxRival.seleccionar(competencia.getRival().getId());
        txtLugar.setValue(competencia.getLugar());
        setIdCompetencia(competencia.getId());
        btnGuardarCompromiso.setVisible(habilitar);
        cbxRival.seleccionar(competencia.getRival().getId());
//        txtObservaciones.setValue(competencia.getObservacion());
    }

    public void setIdCompetencia(Long idCompetencia) {
        this.idCompetencia = idCompetencia;
    }

    protected SelectionListener<ButtonEvent> listenerConsultarCompetencia() {
        return new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                wBuscar.hide();
                limpiarPanelBuscarCompetencia();
                MessageBox boxWait = MessageBox.wait("Competencia",
                        "Cargando los datos, por favor espere...", "Cargando...");

                Competencia competencia = new Competencia();
                competencia.setId(dTOCompetencia.getIdCompetencia());
                competencia.setFecha(dTOCompetencia.getFecha());
                competencia.setLugar(dTOCompetencia.getLugar());
                competencia.setRival(new Rivales(dTOCompetencia.getIdRival()));
                competencia.setTorneo(new Torneos(dTOCompetencia.getIdtorneo()));
                competencia.setObservacion(dTOCompetencia.getObservaciones());

                cargarDatosCompetencia(competencia, false);
                habilitarPanelesBusqueda(false);
                adminPestComp.panelAdminConvocados.cargarConvocadosCompetencia(dTOCompetencia.getIdCompetencia(), false);
                adminPestComp.panelAdminControlDisciplinario.cargarControlJuego(idCompetencia, dTOCompetencia.getIdJugadorComodin(), false);
                adminPestComp.panelAdminSituaciones.buscarSituacionesxCompetencia(dTOCompetencia.getIdCompetencia(), false);
                adminCuerpoTecnico.cargarCuerpoTecnicoCompetencia(dTOCompetencia.getIdCompetencia(), false);

                boxWait.close();
            }
        };

    }

    protected SelectionListener<ButtonEvent> listenerEditarCompetencia() {
        return new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                limpiarPanelBuscarCompetencia();
                wBuscar.hide();
                MessageBox boxWait = MessageBox.wait("Competencia",
                        "Cargando los datos, por favor espere...", "Cargando...");

                Competencia competencia = new Competencia();
                competencia.setId(dTOCompetencia.getIdCompetencia());
                competencia.setFecha(dTOCompetencia.getFecha());
                competencia.setLugar(dTOCompetencia.getLugar());
                competencia.setRival(new Rivales(dTOCompetencia.getIdRival()));
                competencia.setTorneo(new Torneos(dTOCompetencia.getIdtorneo()));
                competencia.setObservacion(dTOCompetencia.getObservaciones());

                cargarDatosCompetencia(competencia, true);
                habilitarPanelesBusqueda(true);
                adminPestComp.panelAdminConvocados.cargarConvocadosCompetencia(dTOCompetencia.getIdCompetencia(), true);
                adminPestComp.panelAdminControlDisciplinario.cargarControlJuego(idCompetencia, dTOCompetencia.getIdJugadorComodin(), true);
                adminPestComp.panelAdminSituaciones.buscarSituacionesxCompetencia(dTOCompetencia.getIdCompetencia(), true);
                adminCuerpoTecnico.cargarCuerpoTecnicoCompetencia(dTOCompetencia.getIdCompetencia(), true);

                boxWait.close();
            }
        };

    }

    public void habilitarPanelesBusqueda(boolean habilitar) {
//        binding.removeButton(btnGuardarCompromiso);

        adminPestComp.tabpanelCompetencia.enable();
        adminPestComp.tabItemSituaciones.enable();
        adminPestComp.tabItemControlJuego.enable();
        fpCompromiso.enable();
        cpCuerpoTecCom.enable();
        btnGuardarCompetencia.setEnabled(habilitar);

    }

}
