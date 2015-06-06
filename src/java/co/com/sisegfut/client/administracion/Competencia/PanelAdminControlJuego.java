/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.client.administracion.Competencia;

import co.com.sisegfut.client.aatest.TestData;
import co.com.sisegfut.client.aatest.model.Tarjeta;
import co.com.sisegfut.client.datos.dominio.CambiosCompe;
import co.com.sisegfut.client.datos.dominio.Competencia;
import co.com.sisegfut.client.datos.dominio.ControlDisciplinarioCompe;
import co.com.sisegfut.client.datos.dominio.Deportista;
import co.com.sisegfut.client.datos.dominio.Goles;
import co.com.sisegfut.client.entidades.RespuestaRPC;
import co.com.sisegfut.client.util.Resources;
import co.com.sisegfut.client.util.combox.ComboBoxConvocadosCompetencia;
import co.com.sisegfut.client.util.combox.ComboboxConvadosTipo;
import co.com.sisegfut.client.util.rpc.RPCAdminCambiosCompe;
import co.com.sisegfut.client.util.rpc.RPCAdminCambiosCompeAsync;
import co.com.sisegfut.client.util.rpc.RPCAdminCompetencia;
import co.com.sisegfut.client.util.rpc.RPCAdminCompetenciaAsync;
import co.com.sisegfut.client.util.rpc.RPCAdminControlDisciplinarioComp;
import co.com.sisegfut.client.util.rpc.RPCAdminControlDisciplinarioCompAsync;
import co.com.sisegfut.client.util.rpc.RPCAdminGoles;
import co.com.sisegfut.client.util.rpc.RPCAdminGolesAsync;
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
import com.extjs.gxt.ui.client.event.BaseEvent;
import com.extjs.gxt.ui.client.event.BoxComponentEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionChangedEvent;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.store.ListStore;
import com.extjs.gxt.ui.client.util.Margins;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.Label;
import com.extjs.gxt.ui.client.widget.LayoutContainer;
import com.extjs.gxt.ui.client.widget.MessageBox;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.form.ComboBox;
import com.extjs.gxt.ui.client.widget.form.ComboBox.TriggerAction;
import com.extjs.gxt.ui.client.widget.form.FormButtonBinding;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.extjs.gxt.ui.client.widget.form.Radio;
import com.extjs.gxt.ui.client.widget.form.RadioGroup;
import com.extjs.gxt.ui.client.widget.grid.ColumnConfig;
import com.extjs.gxt.ui.client.widget.grid.ColumnModel;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.grid.GridCellRenderer;
import com.extjs.gxt.ui.client.widget.grid.filters.GridFilters;
import com.extjs.gxt.ui.client.widget.layout.BorderLayout;
import com.extjs.gxt.ui.client.widget.layout.FillLayout;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.extjs.gxt.ui.client.widget.layout.RowData;
import com.extjs.gxt.ui.client.widget.layout.RowLayout;
import com.extjs.gxt.ui.client.widget.toolbar.SeparatorToolItem;
import com.extjs.gxt.ui.client.widget.toolbar.ToolBar;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Andres Hurtado
 */
public class PanelAdminControlJuego extends LayoutContainer {

    private ContentPanel panelControlJuego;
    private Long idCompetencia = null;
    private Long idJugadorComodin = null;
    private Long idCambios;
    private Long idGol;
    private Long idTarjeta;
    private Grid<BeanModel> gridCambios;
    private Grid<BeanModel> gridGoles;
    private Grid<BeanModel> gridTarjetas;
    private Button btnCrearCambios;
    private Button btnEliminarCambio;
    private Button btnCrearGol;
    private Button btnEliminarGol;
    private Button btnCrearTarjeta;
    private Button btnEliminarTarjeta;
    private PagingLoader<PagingLoadResult<ModelData>> loaderCambios;
    private PagingLoader<PagingLoadResult<ModelData>> loaderGoles;
    private PagingLoader<PagingLoadResult<ModelData>> loaderTarjetas;
    private NumberField nMinuto;
    private ComboboxConvadosTipo cbxTitulares;
    private ComboboxConvadosTipo cbxSuplentes;
    private ComboBoxConvocadosCompetencia cbxDeportista;
    private ComboBox<Tarjeta> cbxTarjeta;
    private FormButtonBinding bindingCambios;
    private FormButtonBinding bindingGoles;
    private FormButtonBinding bindingTarjetas;
    private String equipoGol;

    private Radio rdAnfitrion;
    private Radio rdRival;

    public PanelAdminControlJuego() {
        setScrollMode(Style.Scroll.AUTOY);
        BorderLayout layout = new BorderLayout();
        setLayout(layout);
        setStyleAttribute("padding", "0px");
        panelControlJuego = new ContentPanel();
        panelControlJuego.setStyleAttribute("padding", "0px");
        panelControlJuego.setLayout(new RowLayout(Style.Orientation.VERTICAL));
        panelControlJuego.setSize(500, 500);
        panelControlJuego.setFrame(true);
        panelControlJuego.setHeaderVisible(false);

        ContentPanel cpCambios = crearGridCambios();
//        cpCambios.setHeight(250);
        ContentPanel cpGoles = crearGridGoles();
        ContentPanel cpTarjetas = crearGridTarjetas();

        panelControlJuego.add(cpGoles, new RowData(1, 0.33, new Margins(0)));
        panelControlJuego.add(cpCambios, new RowData(1, 0.33, new Margins(0)));
        panelControlJuego.add(cpTarjetas, new RowData(1, 0.33, new Margins(0)));
        add(panelControlJuego);

        this.addListener(Events.Resize, new Listener<BoxComponentEvent>() {
            @Override
            public void handleEvent(final BoxComponentEvent event) {
                panelControlJuego.setWidth(event.getWidth());
                panelControlJuego.setHeight(event.getHeight() - 30);
            }
        });

    }

    ///// Grid de GOlES ///////////////////////////////////////////////////////////
    public ContentPanel crearGridGoles() {

        final RPCAdminGolesAsync svc = (RPCAdminGolesAsync) GWT.create(RPCAdminGoles.class);
        ServiceDefTarget endpoint = (ServiceDefTarget) svc;
        endpoint.setServiceEntryPoint("services/RPCAdminGoles");

        //Llamo el servicio RPC que se usara como proxy para cargarGridJugadores los datos de la entidad indicada
        RpcProxy<PagingLoadResult<Goles>> proxy = new RpcProxy<PagingLoadResult<Goles>>() {
            @Override
            protected void load(Object loadConfig, AsyncCallback<PagingLoadResult<Goles>> callback) {
                if (idCompetencia != null) {
                    svc.getGolesXCompetencia(idCompetencia, callback);
                } else {
                    svc.getGolesXCompetencia(0l, callback);
                }
            }
        };

        loaderGoles = new BasePagingLoader<PagingLoadResult<ModelData>>(proxy, new BeanModelReader()) {
            @Override
            protected Object newLoadConfig() {
                BasePagingLoadConfig config = new BaseFilterPagingLoadConfig();
                return config;
            }
        };
        loaderGoles.setRemoteSort(false);

        ListStore<BeanModel> storeTitulares = new ListStore<BeanModel>(loaderGoles);

        //Configuro las columnas de la tabla
        List<ColumnConfig> columns = new ArrayList<ColumnConfig>();
        columns.add(new ColumnConfig("idDeportista.label", "Nombre ", 100));
        columns.add(new ColumnConfig("equipoGol", "Equipo", 50));
        columns.add(new ColumnConfig("minutoGol", "Minuto", 50));

        ColumnModel cm = new ColumnModel(columns);
//        GridFilters filters = new GridFilters();
//
//        StringFilter nombreFilter = new StringFilter("idDeportista.label");
//
//        filters.addFilter(nombreFilter);

        gridGoles = new Grid<BeanModel>(storeTitulares, cm);

        gridGoles.getSelectionModel().setSelectionMode(Style.SelectionMode.SINGLE);

        gridGoles.getSelectionModel().addListener(Events.SelectionChange, new Listener<SelectionChangedEvent<BeanModel>>() {
            @Override
            public void handleEvent(SelectionChangedEvent<BeanModel> be) {
                // formBindings.unbind();
                if (be.getSelection().size() > 0) {

                    Goles goles = (Goles) be.getSelectedItem().getBean();

                    idGol = goles.getId();
                    equipoGol = goles.getEquipoGol();

//                    Info.display("Personal", "Selecciono el cambio " + p.getNombreCompleto() + " idpersonal" + idCambios);
                } else {
                }
            }
        });

        gridGoles.setLoadMask(true);
        gridGoles.setBorders(true);
//        gridGoles.addPlugin(filters);
        gridGoles.getView().setForceFit(true);
        gridGoles.setTrackMouseOver(false);

        ContentPanel cp = new ContentPanel();
        cp.setBodyBorder(true);
        cp.setScrollMode(Style.Scroll.AUTO);
        cp.setIcon(Resources.ICONS.table());
        cp.setHeaderVisible(false);
        cp.setHeading("Goles");
        cp.setLayout(new FillLayout());
        Label lbseleccione = new Label();
        lbseleccione.setText("<b>Goles </b>");

        btnCrearGol = new Button("Agregar gol", listenerCrearGol());
        btnCrearGol.setIcon(Resources.ICONS.iconoNuevaNota());
//        btnAgregar.setEnabled(false);

        btnEliminarGol = new Button("Eliminar", listenerEliminarGol());
        btnEliminarGol.setIcon(Resources.ICONS.iconoEliminar());
//        btnAgregar.setEnabled(false);

//        cbxPersonal.addListener(Events.SelectionChange, new Listener<BaseEvent>() {
//            @Override
//            public void handleEvent(BaseEvent be) {
//                if (!cbxPersonal.getEntidadElegida().getLabel().equalsIgnoreCase("") || !cbxPersonal.getEntidadElegida().getLabel().isEmpty()) {
//                    btnAgregar.setEnabled(true);
////                    cbxPersonal.setEditable(false);
//                }
//            }
//        });
        ToolBar toolBar = new ToolBar();
        toolBar.setSpacing(2);

        toolBar.add(lbseleccione);
        toolBar.add(new SeparatorToolItem());
        toolBar.add(btnCrearGol);
        toolBar.add(new SeparatorToolItem());
        toolBar.add(btnEliminarGol);
        cp.setTopComponent(toolBar);
        cp.add(gridGoles);
        return cp;
    }

    public ContentPanel crearGridCambios() {

        final RPCAdminCambiosCompeAsync svc = (RPCAdminCambiosCompeAsync) GWT.create(RPCAdminCambiosCompe.class);
        ServiceDefTarget endpoint = (ServiceDefTarget) svc;
        endpoint.setServiceEntryPoint("services/RPCAdminCambiosCompe");

        //Llamo el servicio RPC que se usara como proxy para cargarGridJugadores los datos de la entidad indicada
        RpcProxy<PagingLoadResult<CambiosCompe>> proxy = new RpcProxy<PagingLoadResult<CambiosCompe>>() {
            @Override
            protected void load(Object loadConfig, AsyncCallback<PagingLoadResult<CambiosCompe>> callback) {
                if (idCompetencia != null) {
                    svc.getCambiosXCompetenciaGrid(idCompetencia, callback);
                } else {
                    svc.getCambiosXCompetenciaGrid(0l, callback);
                }
            }
        };

        loaderCambios = new BasePagingLoader<PagingLoadResult<ModelData>>(proxy, new BeanModelReader()) {
            @Override
            protected Object newLoadConfig() {
                BasePagingLoadConfig config = new BaseFilterPagingLoadConfig();
                return config;
            }
        };
        loaderCambios.setRemoteSort(false);

        ListStore<BeanModel> storeTitulares = new ListStore<BeanModel>(loaderCambios);

        //Configuro las columnas de la tabla
        List<ColumnConfig> columns = new ArrayList<ColumnConfig>();
        columns.add(new ColumnConfig("idDeportisteEntra.label", "Entra ", 100));
        columns.add(new ColumnConfig("idDeportistaSale.label", "Sale ", 100));
        columns.add(new ColumnConfig("minutoCambiio", "Minuto", 50));

        ColumnModel cm = new ColumnModel(columns);
        GridFilters filters = new GridFilters();

//        StringFilter nombreFilter = new StringFilter("idDeportista.label");
//        filters.addFilter(nombreFilter);
        gridCambios = new Grid<BeanModel>(storeTitulares, cm);

        gridCambios.getSelectionModel().setSelectionMode(Style.SelectionMode.SINGLE);

        gridCambios.getSelectionModel().addListener(Events.SelectionChange, new Listener<SelectionChangedEvent<BeanModel>>() {
            @Override
            public void handleEvent(SelectionChangedEvent<BeanModel> be) {
                // formBindings.unbind();
                if (be.getSelection().size() > 0) {

                    CambiosCompe cambiosCompe = (CambiosCompe) be.getSelectedItem().getBean();

                    idCambios = cambiosCompe.getId();
//
//                    Info.display("Personal", "Selecciono el cambio " + p.getNombreCompleto() + " idpersonal" + idCambios);
                } else {
                }
            }
        });

        gridCambios.setLoadMask(true);
        gridCambios.setBorders(true);
        gridCambios.addPlugin(filters);
        gridCambios.getView().setForceFit(true);
        gridCambios.setTrackMouseOver(false);

        ContentPanel cp = new ContentPanel();
        cp.setBodyBorder(true);
        cp.setScrollMode(Style.Scroll.AUTO);
        cp.setIcon(Resources.ICONS.table());
        cp.setHeaderVisible(false);
        cp.setHeading("Sustituciones");
        cp.setLayout(new FillLayout());
        Label lbseleccione = new Label();
        lbseleccione.setText("<b>Sustituciones </b>");

        btnCrearCambios = new Button("Realizar sustitución", listenerCrearCambio());
        btnCrearCambios.setIcon(Resources.ICONS.iconoNuevaNota());
//        btnAgregar.setEnabled(false);

        btnEliminarCambio = new Button("", listenerEliminarCambio());
        btnEliminarCambio.setIcon(Resources.ICONS.iconoEliminar());
//        btnAgregar.setEnabled(false);

//        cbxPersonal.addListener(Events.SelectionChange, new Listener<BaseEvent>() {
//            @Override
//            public void handleEvent(BaseEvent be) {
//                if (!cbxPersonal.getEntidadElegida().getLabel().equalsIgnoreCase("") || !cbxPersonal.getEntidadElegida().getLabel().isEmpty()) {
//                    btnAgregar.setEnabled(true);
////                    cbxPersonal.setEditable(false);
//                }
//            }
//        });
        ToolBar toolBar = new ToolBar();
        toolBar.setSpacing(2);

        toolBar.add(lbseleccione);
        toolBar.add(new SeparatorToolItem());
        toolBar.add(btnCrearCambios);
        toolBar.add(new SeparatorToolItem());
        toolBar.add(btnEliminarCambio);
        cp.setTopComponent(toolBar);
        cp.add(gridCambios);
        return cp;
    }

    public ContentPanel crearGridTarjetas() {
        final RPCAdminControlDisciplinarioCompAsync svc = (RPCAdminControlDisciplinarioCompAsync) GWT.create(RPCAdminControlDisciplinarioComp.class);
        ServiceDefTarget endpoint = (ServiceDefTarget) svc;
        endpoint.setServiceEntryPoint("services/RPCAdminControlDisciplinarioComp");

        //Llamo el servicio RPC que se usara como proxy para cargarGridJugadores los datos de la entidad indicada
        RpcProxy<PagingLoadResult<ControlDisciplinarioCompe>> proxy = new RpcProxy<PagingLoadResult<ControlDisciplinarioCompe>>() {
            @Override
            protected void load(Object loadConfig, AsyncCallback<PagingLoadResult<ControlDisciplinarioCompe>> callback) {
                if (idCompetencia != null) {
                    svc.getTarjetasXCompetencia(idCompetencia, callback);
                } else {
                    svc.getTarjetasXCompetencia(0l, callback);
                }
            }
        };

        loaderTarjetas = new BasePagingLoader<PagingLoadResult<ModelData>>(proxy, new BeanModelReader()) {
            @Override
            protected Object newLoadConfig() {
                BasePagingLoadConfig config = new BaseFilterPagingLoadConfig();
                return config;
            }
        };
        loaderTarjetas.setRemoteSort(false);

        ListStore<BeanModel> storeTitulares = new ListStore<BeanModel>(loaderTarjetas);

        //Configuro las columnas de la tabla
        List<ColumnConfig> columns = new ArrayList<ColumnConfig>();
        columns.add(new ColumnConfig("idDeportista.label", "Nombre", 100));
//        columns.add(new ColumnConfig("tipoTarjeta", "", 50));
        columns.add(new ColumnConfig("equipotarjeta", "Equipo", 50));
        ColumnConfig column = new ColumnConfig();
        column.setId("tipoTarjeta");
        column.setAlignment(Style.HorizontalAlignment.CENTER);
        column.setHeader("Tarjeta");
        column.setWidth(50);
        column.setRenderer(new GridCellRenderer() {

            @Override
            public Object render(ModelData model, String property, com.extjs.gxt.ui.client.widget.grid.ColumnData config, int rowIndex, int colIndex, ListStore store, Grid grid) {
                String tipoTarjeta = model.get("tipoTarjeta");
                if (tipoTarjeta == null) {
                    return "";
                } else {
                    String style = "";
                    if (tipoTarjeta.equalsIgnoreCase("ROJA")) {
                        style = "red";
                    } else {
                        style = "orange";
                    }
//                    return "<img width='20' height='20' src=imagenes/iconos/"+tipoTarjeta+".png/>&nbsp;&nbsp;"+
                    return "<span style='color:" + style + "'><b>" + tipoTarjeta + "</b></span>";
                }
            }
        });
        columns.add(column);

        columns.add(new ColumnConfig("minutoTarjeta", "Minuto", 50));

        ColumnModel cm = new ColumnModel(columns);
//        GridFilters filters = new GridFilters();

//        StringFilter nombreFilter = new StringFilter("idDeportista.label");
//
//        filters.addFilter(nombreFilter);
        gridTarjetas = new Grid<BeanModel>(storeTitulares, cm);

        gridTarjetas.getSelectionModel().setSelectionMode(Style.SelectionMode.SINGLE);

        gridTarjetas.getSelectionModel().addListener(Events.SelectionChange, new Listener<SelectionChangedEvent<BeanModel>>() {
            @Override
            public void handleEvent(SelectionChangedEvent<BeanModel> be) {
                // formBindings.unbind();
                if (be.getSelection().size() > 0) {

                    ControlDisciplinarioCompe tarjetas = (ControlDisciplinarioCompe) be.getSelectedItem().getBean();

                    idTarjeta = tarjetas.getId();
//
//                    Info.display("Personal", "Selecciono el personal " + p.getNombreCompleto() + " idpersonal" + idCambios);
                } else {
                }
            }
        });

        gridTarjetas.setLoadMask(true);
        gridTarjetas.setBorders(true);
//        gridTarjetas.addPlugin(filters);
        gridTarjetas.getView().setForceFit(true);
        gridTarjetas.setTrackMouseOver(false);

        ContentPanel cp = new ContentPanel();
        cp.setBodyBorder(true);
        cp.setScrollMode(Style.Scroll.AUTO);
        cp.setIcon(Resources.ICONS.table());
        cp.setHeaderVisible(false);
        cp.setHeading("Tarjetas");
        cp.setLayout(new FillLayout());
        Label lbseleccione = new Label();
        lbseleccione.setText("<b>Tarjetas </b>");

        btnCrearTarjeta = new Button("Agregar", listenerCrearTarjeta());
        btnCrearTarjeta.setIcon(Resources.ICONS.iconoNuevaNota());
//        btnAgregar.setEnabled(false);

        btnEliminarTarjeta = new Button("", listenerEliminarTarjeta());
        btnEliminarTarjeta.setIcon(Resources.ICONS.iconoEliminar());
//        btnAgregar.setEnabled(false);

//        cbxPersonal.addListener(Events.SelectionChange, new Listener<BaseEvent>() {
//            @Override
//            public void handleEvent(BaseEvent be) {
//                if (!cbxPersonal.getEntidadElegida().getLabel().equalsIgnoreCase("") || !cbxPersonal.getEntidadElegida().getLabel().isEmpty()) {
//                    btnAgregar.setEnabled(true);
////                    cbxPersonal.setEditable(false);
//                }
//            }
//        });
        ToolBar toolBar = new ToolBar();
        toolBar.setSpacing(2);

        toolBar.add(lbseleccione);
        toolBar.add(new SeparatorToolItem());
        toolBar.add(btnCrearTarjeta);
        toolBar.add(new SeparatorToolItem());
        toolBar.add(btnEliminarTarjeta);
        cp.setTopComponent(toolBar);
        cp.add(gridTarjetas);
        return cp;
    }

    protected SelectionListener<ButtonEvent> listenerEliminarCambio() {
        return new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                if (idCambios != null) {
                    getServiceCambiosComp().eliminarEntidad(idCambios, new AsyncCallback<RespuestaRPC<CambiosCompe>>() {

                        @Override
                        public void onFailure(Throwable caught) {
                            MessageBox.alert("Alerta", "No fue posible eliminar la sustitución", null);
                        }

                        @Override
                        public void onSuccess(RespuestaRPC<CambiosCompe> result) {
                            Info.display("Elimino", "Se eliminó correctamente la sustitución seleccionada");
                            cargarGridCambios();
                        }
                    });

                } else {
                    MessageBox.alert("Alerta", "Debe selecccionar una sustitución de la tabla ", null);
                }
            }
        };

    }

    protected SelectionListener<ButtonEvent> listenerEliminarGol() {
        return new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {

                if (idGol != null) {
                    getServiceGolesComp().eliminarEntidad(idGol, new AsyncCallback<RespuestaRPC<Goles>>() {

                        @Override
                        public void onFailure(Throwable caught) {
                            MessageBox.alert("Alerta", "No fue posible eliminar el gol", null);
                        }

                        @Override
                        public void onSuccess(RespuestaRPC<Goles> result) {
                            Info.display("Elimino", "Se eliminó correctamente el gol");
                            cargarGridGoles();
                            if (equipoGol.equals("Politecnico JIC")) {
                                eliminarGolCompetencia(idCompetencia, true);
                            } else {
                                eliminarGolCompetencia(idCompetencia, false);
                            }

                        }
                    });

                } else {
                    MessageBox.alert("Alerta", "Debe selecccionar un gol de la tabla ", null);
                }
            }
        };

    }

    protected SelectionListener<ButtonEvent> listenerEliminarTarjeta() {
        return new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {

                if (idTarjeta != null) {
                    getServiceTarjetasComp().eliminarEntidad(idTarjeta, new AsyncCallback<RespuestaRPC<ControlDisciplinarioCompe>>() {

                        @Override
                        public void onFailure(Throwable caught) {
                            MessageBox.alert("Alerta", "No fue posible eliminar la tarjeta", null);
                        }

                        @Override
                        public void onSuccess(RespuestaRPC<ControlDisciplinarioCompe> result) {
                            Info.display("Elimino", "Se eliminó correctamente la tarjeta");
                            cargarGridTarjetas();
                        }
                    });

                } else {
                    MessageBox.alert("Alerta", "Debe selecccionar una tarjeta de la tabla ", null);
                }
            }
        };

    }

    protected SelectionListener<ButtonEvent> listenerCrearCambio() {
        return new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                AgregarCambioCompetencia();
            }
        };

    }

    protected SelectionListener<ButtonEvent> listenerCrearGol() {
        return new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                AgregarGol();
            }
        };

    }

    protected SelectionListener<ButtonEvent> listenerCrearTarjeta() {
        return new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                AgregarTarjeta();
            }

        };

    }

    public void AgregarCambioCompetencia() {
        final Window window = new Window();
        window.setClosable(false);
        window.setPlain(true);
        window.setModal(true);
        window.setBlinkModal(true);
        window.setSize("300", "200");
        window.setHeading("Realizar Sustitución");
        window.setLayout(new FillLayout());
        FormData formData = new FormData("10");;

        final FormPanel simple = new FormPanel();
        simple.setFrame(true);
        simple.setHeaderVisible(false);

        cbxTitulares = new ComboboxConvadosTipo(idCompetencia, "t");
        cbxTitulares.setTitle("Sale");
        cbxTitulares.setLabelSeparator("Sale");
        cbxTitulares.setAllowBlank(false);

        cbxSuplentes = new ComboboxConvadosTipo(idCompetencia, "s");
        cbxSuplentes.setTitle("Entra");
        cbxSuplentes.setLabelSeparator("Entra");
        cbxSuplentes.setAllowBlank(false);

        nMinuto = new NumberField();
        nMinuto.setFieldLabel("Minuto");
        nMinuto.setMaxValue(125);
        nMinuto.setMinValue(1);
        nMinuto.setMinLength(1);
        nMinuto.setMaxLength(3);
        nMinuto.setAllowBlank(false);
//        nMinuto.setValue(0);

        simple.add(cbxTitulares, formData);
        simple.add(cbxSuplentes, formData);
        simple.add(nMinuto, formData);

        window.add(simple);
        window.setButtonAlign(Style.HorizontalAlignment.CENTER);

        Button btnCambiar = new Button("Aceptar", new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                if (simple.isValid()) {
                    CambiosCompe cambiosCompe = new CambiosCompe();
                    cambiosCompe.setIdCompetencia(new Competencia(idCompetencia));
                    cambiosCompe.setIdDeportistaSale(cbxTitulares.getDeportistaElegido());
                    cambiosCompe.setIdDeportisteEntra(cbxSuplentes.getDeportistaElegido());
                    cambiosCompe.setMinutoCambiio(nMinuto.getValue().intValue());
                    realizarSustitucion(cambiosCompe);
                    window.hide();
                }
            }
        });
        btnCambiar.setIcon(Resources.ICONS.iconoRefrescar());
        Button btnCancelar = new Button("Cancelar", new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                window.hide();
            }
        });
        btnCancelar.setIcon(Resources.ICONS.iconoCancelar());

        bindingCambios = new FormButtonBinding(simple);
        bindingCambios.addButton(btnCambiar);

        window.addButton(btnCambiar);
        window.addButton(btnCancelar);
        window.setFocusWidget(window.getButtonBar().getItem(0));

        window.show();

    }

    public void AgregarTarjeta() {
        final Window window = new Window();
        window.setClosable(false);
        window.setPlain(true);
        window.setModal(true);
        window.setBlinkModal(true);
        window.setSize("320", "200");
        window.setHeading("Registrar Tarjeta");
        window.setLayout(new FillLayout());
        FormData formData = new FormData("10");;

        final FormPanel simple = new FormPanel();
        simple.setFrame(true);
        simple.setHeaderVisible(false);

        cbxDeportista = new ComboBoxConvocadosCompetencia(idCompetencia);
        cbxDeportista.setLabelSeparator("Jugador");
        cbxDeportista.setAllowBlank(false);

        ListStore<Tarjeta> tarjetas = new ListStore<Tarjeta>();
        tarjetas.add(TestData.getTarjetas());

        cbxTarjeta = new ComboBox<Tarjeta>();
        cbxTarjeta.setWidth(150);
        cbxTarjeta.setStore(tarjetas);
        cbxTarjeta.setLabelSeparator("Tarjeta");
        cbxTarjeta.setTemplate(getColorTarjeta());
        cbxTarjeta.setDisplayField("color");
        cbxTarjeta.setTypeAhead(true);
        cbxTarjeta.setAllowBlank(false);
        cbxTarjeta.setTriggerAction(TriggerAction.ALL);

        rdAnfitrion = new Radio();
        rdAnfitrion.setBoxLabel("POLITECNICO JIC");
        rdAnfitrion.setValue(true);

        rdRival = new Radio();
        rdRival.setBoxLabel("RIVAL");
        rdAnfitrion.setValue(true);

        RadioGroup rgEquipo = new RadioGroup();
        rgEquipo.setFieldLabel("Equipo");
        rgEquipo.add(rdAnfitrion);
        rgEquipo.add(rdRival);

        nMinuto = new NumberField();
        nMinuto.setFieldLabel("Minuto");
        nMinuto.setMaxValue(125);
        nMinuto.setMinValue(1);
        nMinuto.setMinLength(1);
        nMinuto.setMaxLength(3);
        nMinuto.setAllowBlank(false);

        rdAnfitrion.addListener(Events.Change, new Listener<BaseEvent>() {
            @Override
            public void handleEvent(BaseEvent be) {
                if (rdRival.getValue()) {
                    cbxDeportista.recargar();
                    cbxDeportista.disable();
                } else {
                    cbxDeportista.enable();
                    cbxDeportista.recargar();
                }

            }
        });
//        nMinuto.setValue(0);

        simple.add(cbxTarjeta, formData);
        simple.add(rgEquipo, formData);
        simple.add(cbxDeportista, formData);
        simple.add(nMinuto, formData);

        window.add(simple);
        window.setButtonAlign(Style.HorizontalAlignment.CENTER);

        Button btnTarjeta = new Button("Aceptar", new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                if (simple.isValid()) {
                    ControlDisciplinarioCompe controlDisciplinarioCompe = new ControlDisciplinarioCompe();
                    controlDisciplinarioCompe.setIdCompetencia(new Competencia(idCompetencia));
                    controlDisciplinarioCompe.setTipoTarjeta(cbxTarjeta.getValue().getColor());
                    if (rdAnfitrion.getValue()) {
                        controlDisciplinarioCompe.setEquipotarjeta("POLITECNICO JIC");
                        controlDisciplinarioCompe.setIdDeportista(cbxDeportista.getDeportistaElegido());
                    } else {
                        controlDisciplinarioCompe.setEquipotarjeta("RIVAL");
                        controlDisciplinarioCompe.setIdDeportista(new Deportista(idJugadorComodin));
                    }
                    controlDisciplinarioCompe.setMinutoTarjeta(nMinuto.getValue().intValue());
                    validarATargetasAmarillas(controlDisciplinarioCompe);
                    window.hide();

                }
            }
        });
        btnTarjeta.setIcon(Resources.ICONS.iconoGuardar());
        Button btnCancelar = new Button("Cancelar", new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                window.hide();
            }
        });
        btnCancelar.setIcon(Resources.ICONS.iconoCancelar());

        bindingGoles = new FormButtonBinding(simple);
        bindingGoles.addButton(btnTarjeta);

        window.addButton(btnTarjeta);
        window.addButton(btnCancelar);
        window.setFocusWidget(window.getButtonBar().getItem(0));

        window.show();

    }

    public void validarATargetasAmarillas(final ControlDisciplinarioCompe controlDisciplinarioCompe) {

        getServiceTarjetasComp().validarTarjetasDeportista(idCompetencia, controlDisciplinarioCompe.getIdDeportista().getId(), new AsyncCallback<Boolean>() {

            @Override
            public void onFailure(Throwable caught) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void onSuccess(Boolean result) {
                if (result) {
                    controlDisciplinarioCompe.setTipoTarjeta("ROJA");
                    MessageBox.info("Importante!", "El jugador " + cbxDeportista.getDeportistaElegido().getNombreCompleto() + " tenía una tarjeta AMARILLA acumulada, por ende se registró una tarjeta ROJA", null);
                }
                guardarTarjeta(controlDisciplinarioCompe);
            }
        });
    }

    //En la imagen color tarjeta
    private native String getColorTarjeta() /*-{ 
     return  [ 
     '<tpl for=".">', 
     '<div class="x-combo-list-item"><img width="20" height="20" src="imagenes/iconos/{color}.png "/>&nbsp;{color}</div>', 
     '</tpl>' 
     ].join(""); 
     }-*/;

    public void AgregarGol() {
        final Window window = new Window();
        window.setClosable(false);
        window.setPlain(true);
        window.setModal(true);
        window.setBlinkModal(true);
        window.setSize("320", "180");
        window.setHeading("Registrar Gol");
        window.setLayout(new FillLayout());
        FormData formData = new FormData("10");;

        final FormPanel simple = new FormPanel();
        simple.setFrame(true);
        simple.setHeaderVisible(false);

        rdAnfitrion = new Radio();
        rdAnfitrion.setBoxLabel("POLITECNICO JIC");
        rdAnfitrion.setValue(true);

        rdRival = new Radio();
        rdRival.setBoxLabel("RIVAL");
        rdAnfitrion.setValue(true);

        RadioGroup rgEquipo = new RadioGroup();
        rgEquipo.setFieldLabel("Equipo");
        rgEquipo.add(rdAnfitrion);
        rgEquipo.add(rdRival);

        cbxDeportista = new ComboBoxConvocadosCompetencia(idCompetencia);
        cbxDeportista.setLabelSeparator("Jugador");
        cbxDeportista.setAllowBlank(false);

        rdAnfitrion.addListener(Events.Change, new Listener<BaseEvent>() {
            @Override
            public void handleEvent(BaseEvent be) {
                if (rdRival.getValue()) {
                    cbxDeportista.recargar();
                    cbxDeportista.disable();
                } else {
                    cbxDeportista.enable();
                    cbxDeportista.recargar();
                }

            }
        });

        nMinuto = new NumberField();
        nMinuto.setFieldLabel("Minuto");
        nMinuto.setMaxValue(125);
        nMinuto.setMinValue(1);
        nMinuto.setMinLength(1);
        nMinuto.setMaxLength(3);
        nMinuto.setAllowBlank(false);
//        nMinuto.setValue(0);

        simple.add(rgEquipo, formData);
        simple.add(cbxDeportista, formData);
        simple.add(nMinuto, formData);

        window.add(simple);
        window.setButtonAlign(Style.HorizontalAlignment.CENTER);

        Button btnGol = new Button("Aceptar", new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                if (simple.isValid()) {
                    Goles goles = new Goles();
                    goles.setIdCompetencia(new Competencia(idCompetencia));
                    goles.setMinutoGol(nMinuto.getValue().intValue());
                    if (rdAnfitrion.getValue()) {
                        goles.setEquipoGol("Politecnico JIC");
                        goles.setIdDeportista(cbxDeportista.getDeportistaElegido());
                    } else {
                        goles.setEquipoGol("Rival");
                        goles.setIdDeportista(new Deportista(idJugadorComodin));
                    }
                    registrarGol(goles);
                    window.hide();

                }
            }
        });
        btnGol.setIcon(Resources.ICONS.iconoGuardar());
        Button btnCancelar = new Button("Cancelar", new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                window.hide();
            }
        });
        btnCancelar.setIcon(Resources.ICONS.iconoCancelar());

        bindingGoles = new FormButtonBinding(simple);
        bindingGoles.addButton(btnGol);

        window.addButton(btnGol);
        window.addButton(btnCancelar);
        window.setFocusWidget(window.getButtonBar().getItem(0));

        window.show();

    }

    public void setIdCompetencia(Long idCompetencia) {
        this.idCompetencia = idCompetencia;
    }

    public void setIdJugadorComodin(Long idJugadorComodin) {
        this.idJugadorComodin = idJugadorComodin;
    }

    public void realizarSustitucion(CambiosCompe cambiosCompe) {

        getServiceCambiosComp().guardarEntidad(cambiosCompe, new AsyncCallback<RespuestaRPC<CambiosCompe>>() {

            @Override
            public void onFailure(Throwable caught) {
                Info.display("Fallo", "No fue posible realizar la sustitución");
            }

            @Override
            public void onSuccess(RespuestaRPC<CambiosCompe> result) {
                cargarGridCambios();
                Info.display("Exito", "Se realizó la sustitución correctamente");
            }
        });

    }

    public void registrarGol(Goles goles) {
        getServiceGolesComp().guardarEntidad(goles, new AsyncCallback<RespuestaRPC<Goles>>() {

            @Override
            public void onFailure(Throwable caught) {
                Info.display("Fallo", "No fue posible registrar el gol");
            }

            @Override
            public void onSuccess(RespuestaRPC<Goles> result) {
                cargarGridGoles();
                if (result.getObjetoRespuesta().getEquipoGol().equals("Politecnico JIC")) {
                    agregarGolCompetencia(idCompetencia, true);
                } else {
                    agregarGolCompetencia(idCompetencia, false);
                }
                Info.display("Exito", "Se registró el gol correctamente");
            }
        });

    }

    public void guardarTarjeta(ControlDisciplinarioCompe controlDisciplinarioCompe) {

        getServiceTarjetasComp().guardarEntidad(controlDisciplinarioCompe, new AsyncCallback<RespuestaRPC<ControlDisciplinarioCompe>>() {

            @Override
            public void onFailure(Throwable caught) {

                Info.display("Fallo", "No fue posible registrar la tarjeta");
            }

            @Override
            public void onSuccess(RespuestaRPC<ControlDisciplinarioCompe> result) {
                cargarGridTarjetas();
                Info.display("Exito", "Se registró la tarjeta correctamente");
            }
        });

    }

    public RPCAdminCambiosCompeAsync getServiceCambiosComp() {
        RPCAdminCambiosCompeAsync svc = (RPCAdminCambiosCompeAsync) GWT.create(RPCAdminCambiosCompe.class);
        ServiceDefTarget endpoint = (ServiceDefTarget) svc;
        endpoint.setServiceEntryPoint("services/RPCAdminCambiosCompe");
        return svc;
    }

    public RPCAdminControlDisciplinarioCompAsync getServiceTarjetasComp() {
        RPCAdminControlDisciplinarioCompAsync svc = (RPCAdminControlDisciplinarioCompAsync) GWT.create(RPCAdminControlDisciplinarioComp.class);
        ServiceDefTarget endpoint = (ServiceDefTarget) svc;
        endpoint.setServiceEntryPoint("services/RPCAdminControlDisciplinarioComp");
        return svc;
    }

    public RPCAdminGolesAsync getServiceGolesComp() {
        RPCAdminGolesAsync svc = (RPCAdminGolesAsync) GWT.create(RPCAdminGoles.class);
        ServiceDefTarget endpoint = (ServiceDefTarget) svc;
        endpoint.setServiceEntryPoint("services/RPCAdminGoles");
        return svc;
    }

    public void agregarGolCompetencia(Long idCompetencia, boolean golAnfitrion) {
        getServiceCompetencia().agregarGolCompetencia(idCompetencia, golAnfitrion, new AsyncCallback<Void>() {

            @Override
            public void onFailure(Throwable caught) {
                System.out.println("No fue posible agregar el gol " + caught);
            }

            @Override
            public void onSuccess(Void result) {
                System.out.println("Se agrega el gol correctamente");
            }
        });
    }

    public void eliminarGolCompetencia(Long idCompetencia, boolean golAnfitrion) {
        getServiceCompetencia().eliminarGolCompetencia(idCompetencia, golAnfitrion, new AsyncCallback<Void>() {

            @Override
            public void onFailure(Throwable caught) {
                System.out.println("No fue posible eliminar el gol " + caught);
            }

            @Override
            public void onSuccess(Void result) {
                System.out.println("Se elimina el gol correctamente");
            }
        });
    }

    public RPCAdminCompetenciaAsync getServiceCompetencia() {
        RPCAdminCompetenciaAsync svc = (RPCAdminCompetenciaAsync) GWT.create(RPCAdminCompetencia.class);
        ServiceDefTarget endpoint = (ServiceDefTarget) svc;
        endpoint.setServiceEntryPoint("services/RPCAdminCompetencia");
        return svc;
    }

    public void cargarGridTarjetas() {
        gridTarjetas.getStore().removeAll();
        loaderTarjetas.load(0, 50);
    }

    public void cargarGridCambios() {
        gridCambios.getStore().removeAll();
        loaderCambios.load(0, 50);
    }

    public void cargarGridGoles() {
        gridGoles.getStore().removeAll();
        loaderGoles.load(0, 50);
    }

    public void cargarControlJuego(Long IdComp, Long idComodin, boolean habilitar) {

        this.setIdCompetencia(IdComp);
        this.setIdJugadorComodin(idComodin);
        
        cargarGridTarjetas();
        cargarGridCambios();
        cargarGridGoles();

        btnCrearCambios.setEnabled(habilitar);
        btnCrearGol.setEnabled(habilitar);
        btnCrearTarjeta.setEnabled(habilitar);
        btnEliminarCambio.setEnabled(habilitar);
        btnEliminarGol.setEnabled(habilitar);
        btnEliminarTarjeta.setEnabled(habilitar);

    }

    public void reiniciarControlJuego() {
        this.setIdCompetencia(null);
        this.setIdJugadorComodin(null);
        
        cargarGridCambios();
        cargarGridGoles();
        cargarGridTarjetas();
        
        btnCrearCambios.enable();
        btnCrearGol.enable();
        btnCrearTarjeta.enable();
        
        btnEliminarCambio.enable();
        btnEliminarGol.enable();
        btnEliminarTarjeta.enable();
    }

}
