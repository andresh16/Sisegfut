/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.client.administracion.Competencia;

import co.com.sisegfut.client.aaI18N.Main;
import static co.com.sisegfut.client.administracion.deportista.PanelInfoGeneral.ACTIVOS;
import co.com.sisegfut.client.datos.dominio.Competencia;
import co.com.sisegfut.client.datos.dominio.CuerpoTecnicoCompe;
import co.com.sisegfut.client.datos.dominio.Personal;
import co.com.sisegfut.client.datos.dominio.dto.DTOCompetencia;
import co.com.sisegfut.client.entidades.RespuestaRPC;
import co.com.sisegfut.client.util.Resources;
import co.com.sisegfut.client.util.combox.ComboBoxPersonal;
import co.com.sisegfut.client.util.combox.ComboBoxPersonalCompetencia;
import co.com.sisegfut.client.util.combox.ComboBoxRival;
import co.com.sisegfut.client.util.combox.ComboBoxTorneo;
import co.com.sisegfut.client.util.rpc.RPCAdminCompetencia;
import co.com.sisegfut.client.util.rpc.RPCAdminCompetenciaAsync;
import co.com.sisegfut.client.util.rpc.RPCAdminCuerpoTecComp;
import co.com.sisegfut.client.util.rpc.RPCAdminCuerpoTecCompAsync;
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
import com.extjs.gxt.ui.client.widget.Label;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.ToolButton;
import com.extjs.gxt.ui.client.widget.form.DateField;
import com.extjs.gxt.ui.client.widget.form.FormButtonBinding;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.extjs.gxt.ui.client.widget.form.TextArea;
import com.extjs.gxt.ui.client.widget.form.TextField;
import com.extjs.gxt.ui.client.widget.grid.BufferView;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.extjs.gxt.ui.client.widget.grid.filters.GridFilters;
import com.extjs.gxt.ui.client.widget.grid.filters.StringFilter;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.BorderLayoutData;
import com.extjs.gxt.ui.client.widget.layout.ColumnLayout;
import com.extjs.gxt.ui.client.widget.layout.FillLayout;
import com.extjs.gxt.ui.client.widget.layout.FormData;
//import com.extjs.gxt.ui.client.widget.layout.ColumnData;
import com.extjs.gxt.ui.client.widget.layout.FormLayout;
import com.extjs.gxt.ui.client.widget.layout.RowData;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.extjs.gxt.ui.client.widget.tips.ToolTipConfig;
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
    private DateField DtFecha2 = new DateField();
    private FormPanel fpCompromiso;
    private ContentPanel cpCuerpoTecCom;
    PanelAdminPestComp adminPestComp = new PanelAdminPestComp();
    PanelAdminCuerpoTecnico adminCuerpoTecnico = new PanelAdminCuerpoTecnico();
    private ContentPanel cp;
    private ContentPanel cp2;
    private Button btnGuardarComp;
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

    private Date fechaFiltroComp = null;
    private Long IdTorneo = null;
    private Long idRival = null;
    private Long idJugadorComodin = null;
    private Button btnEditarCompetencia;
    private Button btnConsularCompetencia;

    private Main myConstants;
    private DTOCompetencia dTOCompetencia = new DTOCompetencia();

    @Override
    protected void onRender(Element parent, int index) {
        super.onRender(parent, index);

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
//        adminPestComp.tabpanel.setEnabled(false);

        fpCompromiso = crearFormulario();

        btnGuardarComp = new Button("Guardar Siguiente", listenerGuardarCompromiso());
        btnGuardarComp.setIconAlign(IconAlign.RIGHT);
        btnGuardarComp.setIcon(Resources.ICONS.iconoLogin());

        btnBuscarComp = new Button("Buscar Competencia", listenerBuscar());
        btnBuscarComp.setIcon(Resources.ICONS.iconoBuscar());

        ToolBar toolBar = new ToolBar();
        toolBar.setSpacing(2);
//        toolBar.add(new SeparatorToolItem());
//        toolBar.add(btnBuscarComp);
//        fpCompromiso.setTopComponent(toolBar);    

        binding = new FormButtonBinding(fpCompromiso);
        binding.addButton(btnGuardarComp);

//        fpCompromiso.addButton(btnEditar);
        fpCompromiso.addButton(btnBuscarComp);
        fpCompromiso.addButton(btnGuardarComp);

        fpCompromiso.setButtonAlign(Style.HorizontalAlignment.CENTER);

        cpCuerpoTecCom = adminCuerpoTecnico;
        cpCuerpoTecCom.disable();

        cp.add(fpCompromiso, new RowData(1, 0.4, new Margins(0)));
        cp.add(cpCuerpoTecCom, new RowData(1, 0.6, new Margins(0)));

//        
        comboBoxTorneo.addListener(Events.SelectionChange, new Listener<BaseEvent>() {
            @Override
            public void handleEvent(BaseEvent be) {
                Info.display("Categoria", "Filtró por la categoría " + comboBoxTorneo.getTorneosElegido().getCategoria().getNombrecategoria());
                adminPestComp.cargarJugadores(comboBoxTorneo.getTorneosElegido().getCategoria().getId());
            }
        });
        cp2.add(adminPestComp);

        panel2 = new ContentPanel();
        panel2.setHeading("Control de competencia");
        panel2.setStyleAttribute("padding", "0px");
        panel2.setIcon(Resources.ICONS.iconoChronometer());
        panel2.setLayout(new RowLayout(Style.Orientation.HORIZONTAL));
        panel2.setSize(500, 500);
        panel2.setFrame(true);
        panel2.setHeaderVisible(true);

//        dataWest = new BorderLayoutData(Style.LayoutRegion.WEST);
//        dataWest.setMargins(new Margins(0, 0, 0, 0));
//        dataWest.setCollapsible(true);
////        dataWest.setSplit(true);
////        dataWest.setFloatable(true);  
        panel2.add(cp, new RowData(0.4, 1, new Margins(0)));

//        dataCenter = new BorderLayoutData(Style.LayoutRegion.CENTER);
//        dataCenter.setCollapsible(true);
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
                    cbxRival.recargar();
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
            }
        };

    }

    public FormPanel crearFormularioBusqueda() {

        FormPanel fpCompromiso = new FormPanel();
        fpCompromiso.setScrollMode(Style.Scroll.AUTO);
        fpCompromiso.setFrame(true);
        fpCompromiso.setHeaderVisible(false);
//        form.setPadding(5);
        fpCompromiso.setSize("100%", "100%");

        // Layout Main que contiene todas las columnas 
        FormData formData = new FormData("-10");
        LayoutContainer main = new LayoutContainer();
        main.setLayout(new ColumnLayout());
        // main.setHeight(100);
        main.setAutoHeight(true);
        ///////////////////// Columna 1 ////////////////////////////  
        LayoutContainer Columna1 = new LayoutContainer();
        Columna1.setStyleAttribute("padding", "10px");

        FormLayout layout = new FormLayout();
        layout.setLabelAlign(FormPanel.LabelAlign.TOP);
        Columna1.setLayout(layout);

        DtFecha2.setName("fechaCompromiso");
        DtFecha.setFieldLabel("<font color='red'>*</font> Fecha ");
        DtFecha2.setLabelSeparator("Fecha competencia ");
//        DtFecha2.setAllowBlank(false);
//        DtFecha2.setFormatValue(true);

        Columna1.add(DtFecha2, formData);

        ///////////////////// Columna 2 //////////////////////////// 
        LayoutContainer Columna2 = new LayoutContainer();
        Columna2.setStyleAttribute("padding", "10px");
        layout = new FormLayout();
        layout.setLabelAlign(FormPanel.LabelAlign.TOP);
        Columna2.setLayout(layout);

        comboBoxTorneo2 = new ComboBoxTorneo(ComboBoxTorneo.ACTIVOS);
        comboBoxTorneo2.setLabelSeparator("Torneo");
        comboBoxTorneo2.setEditable(false);
        comboBoxTorneo2.setForceSelection(true);

        comboBoxTorneo2.addListener(Events.SelectionChange, new Listener<BaseEvent>() {
            @Override
            public void handleEvent(BaseEvent be) {
                if (comboBoxTorneo2.getValue() != null) {
                    cbxRival2.setIdTorneoElegido(comboBoxTorneo2.getTorneosElegido().getId());
                    cbxRival2.recargar();
                    cbxRival2.enable();
                } else {
                    cbxRival2.disable();
                }
            }
        });
        Columna2.add(comboBoxTorneo2, formData);

        ///////////////////// Columna 3 //////////////////////////// 
        LayoutContainer Columna3 = new LayoutContainer();
        Columna3.setStyleAttribute("padding", "10px");
        layout = new FormLayout();
        layout.setLabelAlign(FormPanel.LabelAlign.TOP);
        Columna3.setLayout(layout);

        cbxRival2 = new ComboBoxRival(ACTIVOS);
        cbxRival2.setLabelSeparator("Rival");
        cbxRival2.disable();
        cbxRival2.setEditable(false);
        Columna3.add(cbxRival2, formData);

        main.add(Columna1, new com.extjs.gxt.ui.client.widget.layout.ColumnData(.33));
        main.add(Columna2, new com.extjs.gxt.ui.client.widget.layout.ColumnData(.33));
        main.add(Columna3, new com.extjs.gxt.ui.client.widget.layout.ColumnData(.33));

        fpCompromiso.add(main, new FormData("100%"));

        Button btnBuscarCompetencia = new Button("Buscar", new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
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
        });
        Button btnLimpiarForCompetencia = new Button("Limpiar", new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                fechaFiltroComp = null;
                DtFecha2.reset();
                IdTorneo = null;
                comboBoxTorneo2.recargar();

                idRival = null;
                cbxRival2.recargar();
                cbxRival2.disable();
                cargarGridCompetencia();
            }
        });
        fpCompromiso.addButton(btnBuscarCompetencia);
        fpCompromiso.addButton(btnLimpiarForCompetencia);
        fpCompromiso.setButtonAlign(Style.HorizontalAlignment.CENTER);

        return fpCompromiso;

    }

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
                            adminPestComp.panelAdminConvocados.cbxCategoria.seleccionar(comboBoxTorneo.getTorneosElegido().getCategoria().getId());
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
        binding.removeButton(btnGuardarComp);
        adminPestComp.tabpanel.setEnabled(true);
        adminPestComp.tabItemSituaciones.disable();
        adminPestComp.tabItemControlJuego.disable();
        fpCompromiso.setEnabled(false);
        cpCuerpoTecCom.setEnabled(true);
        btnGuardarComp.setEnabled(false);
        btnGuardarCompetencia.enable();
        adminCuerpoTecnico.cbxPersonal.recargar();
    }

    public Competencia obtenerFormulario() {
        Competencia competencia = new Competencia();

        competencia.setAnfitrion("POLITECNICO JIC");
        competencia.setRival(cbxRival.getRivalElegido());
        competencia.setFecha(DtFecha.getValue());
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
                wBuscar.setSize(700, 450);
                wBuscar.setPlain(true);
                wBuscar.setModal(true);
                wBuscar.setClosable(false);
                wBuscar.setBlinkModal(true);
                wBuscar.setHeading("Buscar competencia");
                wBuscar.setLayout(new FillLayout());

                wBuscar.add(crearGridCompetencia());

                btnEditarCompetencia = new Button("Editar");
                btnEditarCompetencia.disable();
                btnConsularCompetencia = new Button("Consultar");
                btnConsularCompetencia.disable();
                Button btnCancelarBusCompetencia = new Button("Cancelar", new SelectionListener<ButtonEvent>() {

                    @Override
                    public void componentSelected(ButtonEvent ce) {
                        wBuscar.hide();
                        fechaFiltroComp = null;
                        DtFecha2.reset();
                        IdTorneo = null;
                        comboBoxTorneo2.recargar();
                        idRival = null;
                        cbxRival2.recargar();
                        cargarGridCompetencia();

                    }
                });

                wBuscar.getHeader().addTool(new ToolButton("x-tool-help", new SelectionListener<IconButtonEvent>() {
                    @Override
                    public void componentSelected(IconButtonEvent ce) {
                        abrirVentanaAyuda(myConstants.ayudaPanelCompetenciaBuscar());
                    }
                }));
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
        column.setDateTimeFormat(DateTimeFormat.getFormat("dd MMMM yyyy"));
        configs.add(column);

        column = new ColumnConfig();
        column.setId("compromiso");
        column.setAlignment(Style.HorizontalAlignment.CENTER);
        column.setHeader("Compromiso");
        column.setWidth(120);
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
        column.setAlignment(Style.HorizontalAlignment.CENTER);
        column.setHeader("Torneo");
        column.setWidth(80);
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
        column.setAlignment(Style.HorizontalAlignment.CENTER);
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
        column.setWidth(30);
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

        ContentPanel cpForm = new ContentPanel();
        cpForm.setHeaderVisible(false);
        cpForm.setLayout(new FillLayout(Style.Orientation.HORIZONTAL));
        cpForm.setFrame(true);
        cpForm.setBodyBorder(false);
        cpForm.setBorders(false);

        FormPanel panel = crearFormularioBusqueda();
//        panel.setLayout(new FillLayout(Style.Orientation.HORIZONTAL));
        cpForm.add(panel);

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
                    } else {
                        btnConsularCompetencia.disable();
                        btnEditarCompetencia.enable();
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

        lcPanelBuscarCompetencia.add(cpForm, new RowData(1, 0.4, new Margins(0)));
        lcPanelBuscarCompetencia.add(cpGrid, new RowData(1, 0.6, new Margins(0)));

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

    public void limpiarCompetencia() {
        this.mask("Reiniciando competencia....");
        idCompetencia = null;
        adminCuerpoTecnico.cargarGridCuerpoTecComp();
        adminCuerpoTecnico.cbxPersonal.recargar();
        binding.addButton(btnGuardarComp);
        adminPestComp.tabpanel.setEnabled(false);
        fpCompromiso.setEnabled(true);
        DtFecha.reset();
        comboBoxTorneo.recargar();
        cbxRival.recargar();
        cbxRival.setIdTorneoElegido(null);
        txtLugar.reset();
        cpCuerpoTecCom.setEnabled(false);
        btnGuardarComp.setEnabled(true);
        btnGuardarCompetencia.disable();
        adminCuerpoTecnico.cbxPersonal.recargar();
        adminCuerpoTecnico.setIdCompetencia(null);
        adminPestComp.panelAdminConvocados.setIdCompetencia(null);
        adminPestComp.panelAdminConvocados.limpiarGrids();
        adminPestComp.panelAdminControlDisciplinario.setIdCompetencia(null);
        adminPestComp.panelAdminControlDisciplinario.setIdJugadorComodin(null);
        adminPestComp.panelAdminSituaciones.setIdCompetencia(null);
        this.unmask();
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

}
