/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.com.sisegfut.client.administracion.Competencia;

import co.com.sisegfut.client.aaI18N.Main;
import co.com.sisegfut.client.datos.dominio.Competencia;
import co.com.sisegfut.client.datos.dominio.SituacionesJuegoCompe;
import co.com.sisegfut.client.datos.dominio.dto.DTOSituacionJuegoComp;
import co.com.sisegfut.client.entidades.RespuestaRPC;
import co.com.sisegfut.client.util.Resources;
import co.com.sisegfut.client.util.rpc.RPCAdminSituacionesJuego;
import co.com.sisegfut.client.util.rpc.RPCAdminSituacionesJuegoAsync;
import com.extjs.gxt.ui.client.Style;
import com.extjs.gxt.ui.client.event.BoxComponentEvent;
import com.extjs.gxt.ui.client.event.ButtonEvent;
import com.extjs.gxt.ui.client.event.Events;
import com.extjs.gxt.ui.client.event.IconButtonEvent;
import com.extjs.gxt.ui.client.event.Listener;
import com.extjs.gxt.ui.client.event.SelectionListener;
import com.extjs.gxt.ui.client.widget.ContentPanel;
import com.extjs.gxt.ui.client.widget.Dialog;
import com.extjs.gxt.ui.client.widget.Info;
import com.extjs.gxt.ui.client.widget.Window;
import com.extjs.gxt.ui.client.widget.button.Button;
import com.extjs.gxt.ui.client.widget.button.ToolButton;
import com.extjs.gxt.ui.client.widget.form.FormPanel;
import com.extjs.gxt.ui.client.widget.form.NumberField;
import com.extjs.gxt.ui.client.widget.form.Radio;
import com.extjs.gxt.ui.client.widget.form.RadioGroup;
import com.extjs.gxt.ui.client.widget.grid.Grid;
import com.extjs.gxt.ui.client.widget.layout.FillLayout;
import com.extjs.gxt.ui.client.widget.layout.FormData;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.Element;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.rpc.ServiceDefTarget;
import com.google.gwt.user.client.ui.FlexTable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Andres Hurtado
 */
public class PanelAdminSituaciones extends ContentPanel {

    private Grid<DTOSituacionJuegoComp> gridSituaciones;
    Integer tiempo = 0;
    ////////////////////////////// Botones de situaciones de Juego //////////////////////////////

    private Button btnFz1;
    private Button btnFz2;
    private Button btnFz3;
    private Button btnRb1;
    private Button btnRb2;
    private Button btnRb3;
    private Button btnTl1;
    private Button btnTl2;
    private Button btnTl3;
    private Button btnFl;
    private Button btnTe;
    private Button btnOg;
    private Button btnRe;
    private Button btnPe;
    private Button btnEe;
    private Button btnAg;

    //////////////////////////////////////////////////////////////////////////////////////////////
    private List<DTOSituacionJuegoComp> listaValoresJuego;
////////////////////////////// campos de 1er y 2do de anfitrion /////////////////////////////////

    private FlexTable tableAnfitrion;
    private NumberField nFz1Primer;
    private NumberField nFz1Segun;

    private NumberField nFz2Primer;
    private NumberField nFz2Segun;

    private NumberField nFz3Primer;
    private NumberField nFz3Segun;

    private NumberField nRbz1Primer;
    private NumberField nRbz1Segun;

    private NumberField nRbz2Primer;
    private NumberField nRbz2Segun;

    private NumberField nRbz3Primer;
    private NumberField nRbz3Segun;

    private NumberField nTlz1Primer;
    private NumberField nTlz1Segun;

    private NumberField nTlz2Primer;
    private NumberField nTlz2Segun;

    private NumberField nTlz3Primer;
    private NumberField nTlz3Segun;

    private NumberField nFlPrimer;
    private NumberField nFlSegun;

    private NumberField nTePrimer;
    private NumberField nTeSegun;

    private NumberField nOgPrimer;
    private NumberField nOgSegun;

    private NumberField nRePrimer;
    private NumberField nReSegun;

    private NumberField nPePrimer;
    private NumberField nPeSegun;

    private NumberField nEePrimer;
    private NumberField nEeSegun;

    private NumberField nAgPrimer;
    private NumberField nAgSegun;

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////campos 1er y 2do tiempo para el rival //////////////////////////////////
    private NumberField nFz1PrimerR;
    private NumberField nFz1SegunR;

    private NumberField nFz2PrimerR;
    private NumberField nFz2SegunR;

    private NumberField nFz3PrimerR;
    private NumberField nFz3SegunR;

    private NumberField nRbz1PrimerR;
    private NumberField nRbz1SegunR;

    private NumberField nRbz2PrimerR;
    private NumberField nRbz2SegunR;

    private NumberField nRbz3PrimerR;
    private NumberField nRbz3SegunR;

    private NumberField nTlz1PrimerR;
    private NumberField nTlz1SegunR;

    private NumberField nTlz2PrimerR;
    private NumberField nTlz2SegunR;

    private NumberField nTlz3PrimerR;
    private NumberField nTlz3SegunR;

    private NumberField nFlPrimerR;
    private NumberField nFlSegunR;

    private NumberField nTePrimerR;
    private NumberField nTeSegunR;

    private NumberField nOgPrimerR;
    private NumberField nOgSegunR;

    private NumberField nRePrimerR;
    private NumberField nReSegunR;

    private NumberField nPePrimerR;
    private NumberField nPeSegunR;

    private NumberField nEePrimerR;
    private NumberField nEeSegunR;

    private NumberField nAgPrimerR;
    private NumberField nAgSegunR;
    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    private Radio rd1Tiempo;
    private Radio rd2Tiempo;
    private Radio rdAnfitrion;
    private Radio rdRival;
    private Button btnGuardarSituaciones;
    private Button btnReiniciar;
    private Long IdCompetencia;
    private ContentPanel cp;

    private Main myConstants = (Main) GWT.create(Main.class);

    @Override
    protected void onRender(Element parent, int index) {
        super.onRender(parent, index);
//        setScrollMode(Style.Scroll.AUTOY);
        setHeaderVisible(false);
        setLayout(new FillLayout());
        setBodyBorder(true);
        cp = crearSituacionesJuego();
        cp.setHeaderVisible(false);

        this.addListener(Events.Resize, new Listener<BoxComponentEvent>() {
            @Override
            public void handleEvent(final BoxComponentEvent event) {
                cp.setWidth(event.getWidth());
                cp.setHeight(event.getHeight() - 90);
            }
        });

        btnGuardarSituaciones = new Button("Guardar", listenerGuardarSistuaciones());
        btnGuardarSituaciones.setIconAlign(Style.IconAlign.RIGHT);
        btnGuardarSituaciones.setIcon(Resources.ICONS.iconoLogin());

        btnReiniciar = new Button("Reiniciar", listenerLimpiar());
        btnReiniciar.setIconAlign(Style.IconAlign.LEFT);
        btnReiniciar.setIcon(Resources.ICONS.iconoModificar());
        btnReiniciar.setEnabled(false);

        //Agrego boton al panel principal que permite desplegar la ayuda.
        getHeader().addTool(new ToolButton("x-tool-help", new SelectionListener<IconButtonEvent>() {
            @Override
            public void componentSelected(IconButtonEvent ce) {
                abrirVentana(myConstants.ayudaPanelSituacionesCompetencia());
            }
        }));

        cp.addButton(btnGuardarSituaciones);
        cp.setButtonAlign(Style.HorizontalAlignment.CENTER);

        add(cp);

    }

    protected SelectionListener<ButtonEvent> listenerGuardarSistuaciones() {
        return new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {

                getServiceSituaciones().getSituacionesXCompetencia(IdCompetencia, new AsyncCallback<List<SituacionesJuegoCompe>>() {

                    @Override
                    public void onFailure(Throwable caught) {
                        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                    }

                    @Override
                    public void onSuccess(List<SituacionesJuegoCompe> result) {

                        SituacionesJuegoCompe situaAnfrition1 = null;
                        SituacionesJuegoCompe situaAnfrition2 = null;
                        SituacionesJuegoCompe situaRival1 = null;
                        SituacionesJuegoCompe situaRival2 = null;

                        if (result.size() != 0 || !result.isEmpty()) {

                            for (SituacionesJuegoCompe SituacionesJuego : result) {
                                if (SituacionesJuego.getEquipoSituacion().equalsIgnoreCase("POLITECNICO JIC") && SituacionesJuego.getTiempoSituacion() == 1) {
                                    situaAnfrition1 = getSituacionesAnfrition1(SituacionesJuego.getId());
                                } else if (SituacionesJuego.getEquipoSituacion().equalsIgnoreCase("POLITECNICO JIC") && SituacionesJuego.getTiempoSituacion() == 2) {
                                    situaAnfrition2 = getSituacionesAnfrition1(SituacionesJuego.getId());
                                } else if (SituacionesJuego.getEquipoSituacion().equalsIgnoreCase("RIVAL") && SituacionesJuego.getTiempoSituacion() == 1) {
                                    situaRival1 = getSituacionesRival1(SituacionesJuego.getId());
                                } else if (SituacionesJuego.getEquipoSituacion().equalsIgnoreCase("RIVAL") && SituacionesJuego.getTiempoSituacion() == 2) {
                                    situaRival2 = getSituacionesRival2(SituacionesJuego.getId());
                                }
                            }
                        } else {
                            situaAnfrition1 = getSituacionesAnfrition1(null);
                            situaAnfrition2 = getSituacionesAnfrition2(null);
                            situaRival1 = getSituacionesRival1(null);
                            situaRival2 = getSituacionesRival2(null);
                        }

                        List<SituacionesJuegoCompe> listSituaciones = new ArrayList<SituacionesJuegoCompe>();

                        listSituaciones.add(situaAnfrition1);
                        listSituaciones.add(situaAnfrition2);
                        listSituaciones.add(situaRival1);
                        listSituaciones.add(situaRival2);

                        for (SituacionesJuegoCompe listSituacione : listSituaciones) {
                            guardarSituaciones(listSituacione);
                        }
                        Info.display("Exito", "Se guardaron correctamente las situaciones de juego");
                    }
                });

            }
        };

    }

    public void guardarSituaciones(SituacionesJuegoCompe situacionesJuegoCompe) {
        getServiceSituaciones().guardarEntidad(situacionesJuegoCompe, new AsyncCallback<RespuestaRPC<SituacionesJuegoCompe>>() {

            @Override
            public void onFailure(Throwable caught) {
                Info.display("Falló", "No se guardaron las situaciones de juego");
            }

            @Override
            public void onSuccess(RespuestaRPC<SituacionesJuegoCompe> result) {
                System.out.println("Exito, se guardaron correctamente las situaciones de juego");
            }
        });

    }

    public SituacionesJuegoCompe getSituacionesAnfrition1(Long idSituacion) {
        SituacionesJuegoCompe situaAnfrition1 = new SituacionesJuegoCompe();

        situaAnfrition1.setId(idSituacion);
        situaAnfrition1.setIdCompetencia(new Competencia(IdCompetencia));
        situaAnfrition1.setEquipoSituacion("POLITECNICO JIC");
        situaAnfrition1.setTiempoSituacion(1);
        /// Seteo las situaciones del primer tiempo del anfitrion
        situaAnfrition1.setFaltaZona1(nFz1Primer.getValue().intValue());
        situaAnfrition1.setFaltaZona2(nFz2Primer.getValue().intValue());
        situaAnfrition1.setFaltaZona3(nFz3Primer.getValue().intValue());
        situaAnfrition1.setRecuperacionZona1(nRbz1Primer.getValue().intValue());
        situaAnfrition1.setRecuperacionZona2(nRbz2Primer.getValue().intValue());
        situaAnfrition1.setRecuperacionZona3(nRbz3Primer.getValue().intValue());
        situaAnfrition1.setTiroLibreZona1(nTlz1Primer.getValue().intValue());
        situaAnfrition1.setTiroLibreZona2(nTlz2Primer.getValue().intValue());
        situaAnfrition1.setTiroLibreZona3(nTlz3Primer.getValue().intValue());
        situaAnfrition1.setTiroEsquina(nTePrimer.getValue().intValue());
        situaAnfrition1.setFueraLugar(nFlPrimer.getValue().intValue());
        situaAnfrition1.setPenalty(nPePrimer.getValue().intValue());
        situaAnfrition1.setOpcionGol(nOgPrimer.getValue().intValue());
        situaAnfrition1.setCentrolLateral(0);
        situaAnfrition1.setRemates(nRePrimer.getValue().intValue());
        situaAnfrition1.setEntregasErradas(nEePrimer.getValue().intValue());

        return situaAnfrition1;
    }

    public SituacionesJuegoCompe getSituacionesAnfrition2(Long idSituacion) {
        SituacionesJuegoCompe situaAnfrition2 = new SituacionesJuegoCompe();
        situaAnfrition2.setId(idSituacion);
        situaAnfrition2.setIdCompetencia(new Competencia(IdCompetencia));
        situaAnfrition2.setEquipoSituacion("POLITECNICO JIC");
        situaAnfrition2.setTiempoSituacion(2);

        /// Seteo las situaciones del segundo tiempo del anfitrion
        situaAnfrition2.setFaltaZona1(nFz1Segun.getValue().intValue());
        situaAnfrition2.setFaltaZona2(nFz2Segun.getValue().intValue());
        situaAnfrition2.setFaltaZona3(nFz3Segun.getValue().intValue());
        situaAnfrition2.setRecuperacionZona1(nRbz1Segun.getValue().intValue());
        situaAnfrition2.setRecuperacionZona2(nRbz2Segun.getValue().intValue());
        situaAnfrition2.setRecuperacionZona3(nRbz3Segun.getValue().intValue());
        situaAnfrition2.setTiroLibreZona1(nTlz1Segun.getValue().intValue());
        situaAnfrition2.setTiroLibreZona2(nTlz2Segun.getValue().intValue());
        situaAnfrition2.setTiroLibreZona3(nTlz3Segun.getValue().intValue());
        situaAnfrition2.setTiroEsquina(nTeSegun.getValue().intValue());
        situaAnfrition2.setFueraLugar(nFlSegun.getValue().intValue());
        situaAnfrition2.setPenalty(nPeSegun.getValue().intValue());
        situaAnfrition2.setOpcionGol(nOgSegun.getValue().intValue());
        situaAnfrition2.setCentrolLateral(0);
        situaAnfrition2.setRemates(nReSegun.getValue().intValue());
        situaAnfrition2.setEntregasErradas(nEeSegun.getValue().intValue());

        return situaAnfrition2;
    }

    public SituacionesJuegoCompe getSituacionesRival1(Long idSituacion) {
        SituacionesJuegoCompe situaRival1 = new SituacionesJuegoCompe();
        situaRival1.setId(idSituacion);
        situaRival1.setIdCompetencia(new Competencia(IdCompetencia));
        situaRival1.setEquipoSituacion("Rival");
        situaRival1.setTiempoSituacion(1);

        /// Seteo las situaciones del primer tiempo del Rival
        situaRival1.setFaltaZona1(nFz1PrimerR.getValue().intValue());
        situaRival1.setFaltaZona2(nFz2PrimerR.getValue().intValue());
        situaRival1.setFaltaZona3(nFz3PrimerR.getValue().intValue());
        situaRival1.setRecuperacionZona1(nRbz1PrimerR.getValue().intValue());
        situaRival1.setRecuperacionZona2(nRbz2PrimerR.getValue().intValue());
        situaRival1.setRecuperacionZona3(nRbz3PrimerR.getValue().intValue());
        situaRival1.setTiroLibreZona1(nTlz1PrimerR.getValue().intValue());
        situaRival1.setTiroLibreZona2(nTlz2PrimerR.getValue().intValue());
        situaRival1.setTiroLibreZona3(nTlz3PrimerR.getValue().intValue());
        situaRival1.setTiroEsquina(nTePrimerR.getValue().intValue());
        situaRival1.setFueraLugar(nFlPrimerR.getValue().intValue());
        situaRival1.setPenalty(nPePrimerR.getValue().intValue());
        situaRival1.setOpcionGol(nOgPrimerR.getValue().intValue());
        situaRival1.setCentrolLateral(0);
        situaRival1.setRemates(nRePrimerR.getValue().intValue());
        situaRival1.setEntregasErradas(nEePrimerR.getValue().intValue());

        return situaRival1;
    }

    public SituacionesJuegoCompe getSituacionesRival2(Long idSituacion) {
        SituacionesJuegoCompe situaRival2 = new SituacionesJuegoCompe();
        situaRival2.setId(idSituacion);
        situaRival2.setIdCompetencia(new Competencia(IdCompetencia));
        situaRival2.setEquipoSituacion("Rival");
        situaRival2.setTiempoSituacion(2);
        /// Seteo las situaciones del segundo tiempo del anfitrion
        situaRival2.setFaltaZona1(nFz1SegunR.getValue().intValue());
        situaRival2.setFaltaZona2(nFz2SegunR.getValue().intValue());
        situaRival2.setFaltaZona3(nFz3SegunR.getValue().intValue());
        situaRival2.setRecuperacionZona1(nRbz1SegunR.getValue().intValue());
        situaRival2.setRecuperacionZona2(nRbz2SegunR.getValue().intValue());
        situaRival2.setRecuperacionZona3(nRbz3SegunR.getValue().intValue());
        situaRival2.setTiroLibreZona1(nTlz1SegunR.getValue().intValue());
        situaRival2.setTiroLibreZona2(nTlz2SegunR.getValue().intValue());
        situaRival2.setTiroLibreZona3(nTlz3SegunR.getValue().intValue());
        situaRival2.setTiroEsquina(nTeSegunR.getValue().intValue());
        situaRival2.setFueraLugar(nFlSegunR.getValue().intValue());
        situaRival2.setPenalty(nPeSegunR.getValue().intValue());
        situaRival2.setOpcionGol(nOgSegunR.getValue().intValue());
        situaRival2.setCentrolLateral(0);
        situaRival2.setRemates(nReSegunR.getValue().intValue());
        situaRival2.setEntregasErradas(nEeSegunR.getValue().intValue());

        return situaRival2;
    }

    /**
     * Escucha el boton limpiar
     *
     * @return
     */
    protected SelectionListener<ButtonEvent> listenerLimpiar() {
        return new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                Info.display("Reiniciar", "Se reinicio");
            }
        };
    }

    public Long getIdCompetencia() {
        return IdCompetencia;
    }

    public void setIdCompetencia(Long IdCompetencia) {
        this.IdCompetencia = IdCompetencia;
    }

//    public ContentPanel crearPanelSituacionesJuego() {
//        ContentPanel cpSituacionesJuego = new ContentPanel();
//        cpSituacionesJuego.setBodyBorder(true);
//        cpSituacionesJuego.setScrollMode(Style.Scroll.AUTO);
//        cpSituacionesJuego.setIcon(Resources.ICONS.table());
//        cpSituacionesJuego.setHeaderVisible(true);
//        cpSituacionesJuego.setHeading("Situaciones de juego");
////        cpSituacionesJuego.setButtonAlign(HorizontalAlignment.CENTER);
//        cpSituacionesJuego.setLayout(new FillLayout());
////        cpSituacionesJuego.setSize(600, 300);
//
//        GridCellRenderer<DTOSituacionJuegoComp> buttonRenderer = new GridCellRenderer<DTOSituacionJuegoComp>() {
//
//            private boolean init;
//
//            @Override
//            public Object render(final DTOSituacionJuegoComp model, final String property, ColumnData config, final int rowIndex,
//                    final int colIndex, ListStore<DTOSituacionJuegoComp> store, final Grid<DTOSituacionJuegoComp> grid) {
//                if (!init) {
//                    init = true;
////                    grid.addListener(Events.OnClick, new Listener<GridEvent<DTOSituacionJuegoComp>>() {
////// espete esto no se para que lo utilizan
////                        public void handleEvent(GridEvent<DTOSituacionJuegoComp> be) {
////                            for (int i = 0; i < be.getGrid().getStore().getCount(); i++) {
////                                if (be.getGrid().getView().getWidget(i, be.getColIndex()) != null
////                                        && be.getGrid().getView().getWidget(i, be.getColIndex()) instanceof BoxComponent) {
////                                    ((BoxComponent) be.getGrid().getView().getWidget(i, be.getColIndex())).setWidth(be.getWidth() - 10);
////                                }
////                            }
////                        }
////                    });
//                }
//
//                Button b = new Button((String) model.get(property), Resources.ICONS.iconoNuevaCita(), new SelectionListener<ButtonEvent>() {
//                    @Override
//                    public void componentSelected(final ButtonEvent ce) {
//                        String id = grid.getColumnModel().getColumn(colIndex+1).getId();
//                        Integer val = model.get(id);
//                        model.set(id,val+1);
////                         model.setCantidadprimertiempolocal(model.getCantidadprimertiempolocal() + 1);
//                         grid.getView().refresh(false);
////                         Info.display("asdf", property+" PROP "+colIndex);
////                         Info.display(model.getSituacion(), "<ul><li>Botón " + model.get(property).toString() + " Cantidad 1Local  ----></li>" + model.getCantidadprimertiempolocal() 
////                                 + " -- store situacion--"+gridSituaciones.getStore().getLoadConfig());
////                        System.out.println(gridSituaciones.getStore().getCount());
////                        Info.display("prueba", "");
//
////                        MessageBox.confirm("Confirmación", "Desea agregar " + model.getSituacion() + " ?", new Listener<MessageBoxEvent>() {
////                            @Override
////                            public void handleEvent(MessageBoxEvent be) {
////
////                                String respb = be.getButtonClicked().getText();
////
////                                if (respb.equalsIgnoreCase("yes") || respb.equalsIgnoreCase("sí")) {
////                                    Info.display(model.getSituacion(), "<ul><li>Botón " + model.get(property).toString() + " ----></li>" + ce.getSource().toString() + "<-----");
////                            
////                                }
////
////                            }
////                        });
//                    }
//                });
//                b.setWidth(grid.getColumnModel().getColumnWidth(colIndex) - 10);
//                b.setToolTip("Click for more information");
//
//                return b;
//            }
//
//        };
//        List<ColumnConfig> configs = new ArrayList<ColumnConfig>();
//
//        ColumnConfig column = new ColumnConfig();
//        column.setId("situacion");
//        column.setHeader("<br><b><h3>Situación Juego</h3></b>");
//        column.setWidth(150);
//        column.setRowHeader(true);
//        column.setAlignment(Style.HorizontalAlignment.CENTER);
//        column.setMenuDisabled(true);
//        column.setResizable(false);
//        column.setSortable(false);
////        column.setRenderer(buttonRenderer);
//        configs.add(column);
//
//        column = new ColumnConfig();
//        column.setId("primertiempolocal");
//        column.setHeader("");
//        column.setMenuDisabled(true);
//        column.setRenderer(buttonRenderer);
//        column.setWidth(50);
//        column.setSortable(false);
//        column.setResizable(false);
//        column.setAlignment(Style.HorizontalAlignment.CENTER);
//        configs.add(column);
//
//        column = new ColumnConfig();
//        column.setMenuDisabled(true);
//        column.setId("cantidadprimertiempolocal");
//        column.setHeader("#");
//        column.setResizable(false);
//        column.setAlignment(Style.HorizontalAlignment.CENTER);
//        column.setWidth(50);
//        configs.add(column);
//
//        column = new ColumnConfig();
//        column.setResizable(false);
//        column.setMenuDisabled(true);
//        column.setId("segundotiempolocal");
//        column.setRenderer(buttonRenderer);
//        column.setHeader("");
//        column.setSortable(false);
//        column.setWidth(50);
//        column.setAlignment(Style.HorizontalAlignment.CENTER);
//        configs.add(column);
//
//        column = new ColumnConfig();
//        column.setResizable(false);
//        column.setMenuDisabled(true);
//        column.setId("cantidadsegundotiempolocal");
//        column.setHeader("#");
//        column.setWidth(50);
//        column.setAlignment(Style.HorizontalAlignment.CENTER);
//        configs.add(column);
//
//        column = new ColumnConfig();
//        column.setResizable(false);
//        column.setMenuDisabled(true);
//        column.setId("primertiemporival");
//        column.setSortable(false);
//        column.setHeader("+");
//        column.setAlignment(Style.HorizontalAlignment.CENTER);
//        column.setRenderer(buttonRenderer);
//        column.setWidth(50);
//        configs.add(column);
//
//        column = new ColumnConfig();
//        column.setResizable(false);
//        column.setId("cantidadprimertiemporival");
//        column.setHeader("#");
//        column.setAlignment(Style.HorizontalAlignment.CENTER);
//        column.setWidth(50);
//        configs.add(column);
//
//        column = new ColumnConfig();
//        column.setResizable(false);
//        column.setMenuDisabled(true);
//        column.setSortable(false);
//        column.setId("segundotiemporival");
//        column.setHeader("+");
//        column.setRenderer(buttonRenderer);
//        column.setAlignment(Style.HorizontalAlignment.CENTER);
//        column.setWidth(50);
//        configs.add(column);
//
//        column = new ColumnConfig();
//        column.setResizable(false);
//        column.setMenuDisabled(true);
//        column.setId("cantidadsegundotiemporival");
//        column.setHeader("#");
//        column.setAlignment(Style.HorizontalAlignment.CENTER);
//        column.setWidth(55);
//        configs.add(column);
//
//        final ListStore<DTOSituacionJuegoComp> store = new ListStore<DTOSituacionJuegoComp>();
//        listaValoresJuego = SituacionesJuego.getSituacionesJuegoCompetencia();
//        store.add(listaValoresJuego);
//
//        ColumnModel cm = new ColumnModel(configs);
//        cm.addHeaderGroup(0, 1, new HeaderGroupConfig("POLITECNICO", 1, 4));
//
//        cm.addHeaderGroup(1, 1, new HeaderGroupConfig("1er tiempo", 1, 2));
//        cm.addHeaderGroup(1, 3, new HeaderGroupConfig("2do tiempo", 1, 2));
//
//        cm.addHeaderGroup(0, 4, new HeaderGroupConfig("RIVAL", 1, 5));
//
//        cm.addHeaderGroup(1, 4, new HeaderGroupConfig("1er tiempo", 1, 2));
//        cm.addHeaderGroup(1, 6, new HeaderGroupConfig("2do tiempo", 1, 3));
//
//        gridSituaciones = new Grid<DTOSituacionJuegoComp>(store, cm);
//        gridSituaciones.setStyleAttribute("borderTop", "none");
////        grid.setAutoExpandColumn("situacion");
//        gridSituaciones.setBorders(true);
//        cpSituacionesJuego.add(gridSituaciones);
//
//        return cpSituacionesJuego;
//    }
    private ContentPanel crearSituacionesJuego() {

        ContentPanel cpSituacionesJuego = new ContentPanel();
        cpSituacionesJuego.setBodyBorder(true);
        cpSituacionesJuego.setScrollMode(Style.Scroll.AUTO);
        cpSituacionesJuego.setIcon(Resources.ICONS.table());
        cpSituacionesJuego.setHeaderVisible(false);
        cpSituacionesJuego.setLayout(new FillLayout());

        tableAnfitrion = new FlexTable();
        tableAnfitrion.setWidth("50px");
//        tableAnfitrion.setCellSpacing(6);
//        tableAnfitrion.setCellPadding(4);

//        tableAnfitrion.getCellFormatter();
//        tableAnfitrion.getColumnFormatter().setWidth(1, "5px");
//        tableAnfitrion.getColumnFormatter().setWidth(2, "300");
//        tableAnfitrion.getColumnFormatter().setWidth(3, "5px");
//        tableAnfitrion.getColumnFormatter().setWidth(4, "5px");
        SelectionListener<ButtonEvent> sl = new SelectionListener<ButtonEvent>() {

            @Override
            public void componentSelected(ButtonEvent ce) {
                String txt = ce.getButton().getText();
                tiempo = 0;
                if (txt.equals("Falta zona 1")) {
                    preguntaTiempoEquipo(nFz1Primer, nFz1Segun, nFz1PrimerR, nFz1SegunR, txt);
                } else if (txt.equals("Falta zona 2")) {
                    preguntaTiempoEquipo(nFz2Primer, nFz2Segun, nFz2PrimerR, nFz2SegunR, txt);
                } else if (txt.equals("Falta zona 3")) {
                    preguntaTiempoEquipo(nFz3Primer, nFz3Segun, nFz3PrimerR, nFz3SegunR, txt);
                } else if (txt.equals("Recuperación balon Zona 1")) {
                    preguntaTiempoEquipo(nRbz1Primer, nRbz1Segun, nRbz1PrimerR, nRbz1SegunR, txt);
                } else if (txt.equals("Recuperación balon Zona 2")) {
                    preguntaTiempoEquipo(nRbz2Primer, nRbz2Segun, nRbz2PrimerR, nRbz2SegunR, txt);
                } else if (txt.equals("Recuperación balon Zona 3")) {
                    preguntaTiempoEquipo(nRbz3Primer, nRbz3Segun, nRbz3PrimerR, nRbz3SegunR, txt);
                } else if (txt.equals("Tiro libre Zona 1")) {
                    preguntaTiempoEquipo(nTlz1Primer, nTlz1Segun, nTlz1PrimerR, nTlz1SegunR, txt);
                } else if (txt.equals("Tiro libre Zona 2")) {
                    preguntaTiempoEquipo(nTlz2Primer, nTlz2Segun, nTlz2PrimerR, nTlz2SegunR, txt);
                } else if (txt.equals("Tiro libre Zona 3")) {
                    preguntaTiempoEquipo(nTlz3Primer, nTlz3Segun, nTlz3PrimerR, nTlz3SegunR, txt);
                } else if (txt.equals("Fuera de lugar")) {
                    preguntaTiempoEquipo(nFlPrimer, nFlSegun, nFlPrimerR, nFlSegunR, txt);
                } else if (txt.equals("Tiro esquina")) {
                    preguntaTiempoEquipo(nTePrimer, nTeSegun, nTePrimerR, nTeSegunR, txt);
                } else if (txt.equals("Opciones gol")) {
                    preguntaTiempoEquipo(nOgPrimer, nOgSegun, nOgPrimerR, nOgSegunR, txt);
                } else if (txt.equals("Remates")) {
                    preguntaTiempoEquipo(nRePrimer, nReSegun, nRePrimerR, nReSegunR, txt);
                } else if (txt.equals("Penalty")) {
                    preguntaTiempoEquipo(nPePrimer, nPeSegun, nPePrimerR, nPeSegunR, txt);
                } else if (txt.equals("Entregas erradas")) {
                    preguntaTiempoEquipo(nEePrimer, nEeSegun, nEePrimerR, nEeSegunR, txt);
                } else if (txt.equals("Asistencias gol")) {
                    preguntaTiempoEquipo(nAgPrimer, nAgSegun, nAgPrimerR, nAgSegunR, txt);
                }

            }
        };
        int anchotbtn = 150;
        Button btnFz1 = new Button("Falta zona 1", sl);
        btnFz1.setWidth(anchotbtn);
        Button btnFz2 = new Button("Falta zona 2", sl);
        btnFz2.setWidth(anchotbtn);
        Button btnFz3 = new Button("Falta zona 3", sl);
        btnFz3.setWidth(anchotbtn);
        Button btnRb1 = new Button("Recuperación balon Zona 1", sl);
        btnRb1.setWidth(anchotbtn);
        Button btnRb2 = new Button("Recuperación balon Zona 2", sl);
        btnRb2.setWidth(anchotbtn);
        Button btnRb3 = new Button("Recuperación balon Zona 3", sl);
        btnRb3.setWidth(anchotbtn);
        Button btnTl1 = new Button("Tiro libre Zona 1", sl);
        btnTl1.setWidth(anchotbtn);
        Button btnTl2 = new Button("Tiro libre Zona 2", sl);
        btnTl2.setWidth(anchotbtn);
        Button btnTl3 = new Button("Tiro libre Zona 3", sl);
        btnTl3.setWidth(anchotbtn);
        Button btnFl = new Button("Fuera de lugar", sl);
        btnFl.setWidth(anchotbtn);
        Button btnTe = new Button("Tiro esquina", sl);
        btnTe.setWidth(anchotbtn);
        Button btnOg = new Button("Opciones gol", sl);
        btnOg.setWidth(anchotbtn);
        Button btnRe = new Button("Remates", sl);
        btnRe.setWidth(anchotbtn);
        Button btnPe = new Button("Penalty", sl);
        btnPe.setWidth(anchotbtn);
        Button btnEe = new Button("Entregas erradas", sl);
        btnEe.setWidth(anchotbtn);
        Button btnAg = new Button("Asistencias gol", sl);
        btnAg.setWidth(anchotbtn);
        int ancho = 30;
        nFz1Primer = new NumberField();
        nFz1Primer.setValue(0);
        nFz1Primer.setWidth(ancho);
        nFz1Segun = new NumberField();
        nFz1Segun.setValue(0);
        nFz1Segun.setWidth(ancho);

        nFz2Primer = new NumberField();
        nFz2Primer.setValue(0);
        nFz2Primer.setWidth(ancho);
        nFz2Segun = new NumberField();
        nFz2Segun.setValue(0);
        nFz2Segun.setWidth(ancho);

        nFz3Primer = new NumberField();
        nFz3Primer.setValue(0);
        nFz3Primer.setWidth(ancho);
        nFz3Segun = new NumberField();
        nFz3Segun.setValue(0);
        nFz3Segun.setWidth(ancho);

        nRbz1Primer = new NumberField();
        nRbz1Primer.setValue(0);
        nRbz1Primer.setWidth(ancho);
        nRbz1Segun = new NumberField();
        nRbz1Segun.setValue(0);
        nRbz1Segun.setWidth(ancho);

        nRbz2Primer = new NumberField();
        nRbz2Primer.setValue(0);
        nRbz2Primer.setWidth(ancho);
        nRbz2Segun = new NumberField();
        nRbz2Segun.setValue(0);
        nRbz2Segun.setWidth(ancho);

        nRbz3Primer = new NumberField();
        nRbz3Primer.setValue(0);
        nRbz3Primer.setWidth(ancho);
        nRbz3Segun = new NumberField();
        nRbz3Segun.setValue(0);
        nRbz3Segun.setWidth(ancho);

        nTlz1Primer = new NumberField();
        nTlz1Primer.setValue(0);
        nTlz1Primer.setWidth(ancho);
        nTlz1Segun = new NumberField();
        nTlz1Segun.setValue(0);
        nTlz1Segun.setWidth(ancho);

        nTlz2Primer = new NumberField();
        nTlz2Primer.setValue(0);
        nTlz2Primer.setWidth(ancho);
        nTlz2Segun = new NumberField();
        nTlz2Segun.setValue(0);
        nTlz2Segun.setWidth(ancho);

        nTlz3Primer = new NumberField();
        nTlz3Primer.setValue(0);
        nTlz3Primer.setWidth(ancho);
        nTlz3Segun = new NumberField();
        nTlz3Segun.setValue(0);
        nTlz3Segun.setWidth(ancho);

        nFlPrimer = new NumberField();
        nFlPrimer.setValue(0);
        nFlPrimer.setWidth(ancho);
        nFlSegun = new NumberField();
        nFlSegun.setValue(0);
        nFlSegun.setWidth(ancho);

        nTePrimer = new NumberField();
        nTePrimer.setValue(0);
        nTePrimer.setWidth(ancho);
        nTeSegun = new NumberField();
        nTeSegun.setValue(0);
        nTeSegun.setWidth(ancho);

        nOgPrimer = new NumberField();
        nOgPrimer.setValue(0);
        nOgPrimer.setWidth(ancho);
        nOgSegun = new NumberField();
        nOgSegun.setValue(0);
        nOgSegun.setWidth(ancho);

        nRePrimer = new NumberField();
        nRePrimer.setValue(0);
        nRePrimer.setWidth(ancho);
        nReSegun = new NumberField();
        nReSegun.setValue(0);
        nReSegun.setWidth(ancho);

        nPePrimer = new NumberField();
        nPePrimer.setValue(0);
        nPePrimer.setWidth(ancho);
        nPeSegun = new NumberField();
        nPeSegun.setValue(0);
        nPeSegun.setWidth(ancho);

        nEePrimer = new NumberField();
        nEePrimer.setValue(0);
        nEePrimer.setWidth(ancho);
        nEeSegun = new NumberField();
        nEeSegun.setValue(0);
        nEeSegun.setWidth(ancho);

        nAgPrimer = new NumberField();
        nAgPrimer.setValue(0);
        nAgPrimer.setWidth(ancho);
        nAgSegun = new NumberField();
        nAgSegun.setValue(0);
        nAgSegun.setWidth(ancho);
        ///////////////////////////////////////////////////////////
        /////////////////// inicialicacion variables de campos del rival ///////////////////////
        nFz1PrimerR = new NumberField();
        nFz1PrimerR.setValue(0);
        nFz1PrimerR.setWidth(ancho);
        nFz1SegunR = new NumberField();
        nFz1SegunR.setValue(0);
        nFz1SegunR.setWidth(ancho);

        nFz2PrimerR = new NumberField();
        nFz2PrimerR.setValue(0);
        nFz2PrimerR.setWidth(ancho);
        nFz2SegunR = new NumberField();
        nFz2SegunR.setValue(0);
        nFz2SegunR.setWidth(ancho);

        nFz3PrimerR = new NumberField();
        nFz3PrimerR.setValue(0);
        nFz3PrimerR.setWidth(ancho);
        nFz3SegunR = new NumberField();
        nFz3SegunR.setValue(0);
        nFz3SegunR.setWidth(ancho);

        nRbz1PrimerR = new NumberField();
        nRbz1PrimerR.setValue(0);
        nRbz1PrimerR.setWidth(ancho);
        nRbz1SegunR = new NumberField();
        nRbz1SegunR.setValue(0);
        nRbz1SegunR.setWidth(ancho);

        nRbz2PrimerR = new NumberField();
        nRbz2PrimerR.setValue(0);
        nRbz2PrimerR.setWidth(ancho);
        nRbz2SegunR = new NumberField();
        nRbz2SegunR.setValue(0);
        nRbz2SegunR.setWidth(ancho);

        nRbz3PrimerR = new NumberField();
        nRbz3PrimerR.setValue(0);
        nRbz3PrimerR.setWidth(ancho);
        nRbz3SegunR = new NumberField();
        nRbz3SegunR.setValue(0);
        nRbz3SegunR.setWidth(ancho);

        nTlz1PrimerR = new NumberField();
        nTlz1PrimerR.setValue(0);
        nTlz1PrimerR.setWidth(ancho);
        nTlz1SegunR = new NumberField();
        nTlz1SegunR.setValue(0);
        nTlz1SegunR.setWidth(ancho);

        nTlz2PrimerR = new NumberField();
        nTlz2PrimerR.setValue(0);
        nTlz2PrimerR.setWidth(ancho);
        nTlz2SegunR = new NumberField();
        nTlz2SegunR.setValue(0);
        nTlz2SegunR.setWidth(ancho);

        nTlz3PrimerR = new NumberField();
        nTlz3PrimerR.setValue(0);
        nTlz3PrimerR.setWidth(ancho);
        nTlz3SegunR = new NumberField();
        nTlz3SegunR.setValue(0);
        nTlz3SegunR.setWidth(ancho);

        nFlPrimerR = new NumberField();
        nFlPrimerR.setValue(0);
        nFlPrimerR.setWidth(ancho);
        nFlSegunR = new NumberField();
        nFlSegunR.setValue(0);
        nFlSegunR.setWidth(ancho);

        nTePrimerR = new NumberField();
        nTePrimerR.setValue(0);
        nTePrimerR.setWidth(ancho);
        nTeSegunR = new NumberField();
        nTeSegunR.setValue(0);
        nTeSegunR.setWidth(ancho);

        nOgPrimerR = new NumberField();
        nOgPrimerR.setValue(0);
        nOgPrimerR.setWidth(ancho);
        nOgPrimerR.setWidth(ancho);
        nOgSegunR = new NumberField();
        nOgSegunR.setValue(0);
        nOgSegunR.setWidth(ancho);

        nRePrimerR = new NumberField();
        nRePrimerR.setValue(0);
        nRePrimerR.setWidth(ancho);
        nReSegunR = new NumberField();
        nReSegunR.setValue(0);
        nReSegunR.setWidth(ancho);

        nPePrimerR = new NumberField();
        nPePrimerR.setValue(0);
        nPePrimerR.setWidth(ancho);
        nPeSegunR = new NumberField();
        nPeSegunR.setValue(0);
        nPeSegunR.setWidth(ancho);

        nEePrimerR = new NumberField();
        nEePrimerR.setValue(0);
        nEePrimerR.setWidth(ancho);
        nEeSegunR = new NumberField();
        nEeSegunR.setValue(0);
        nEeSegunR.setWidth(ancho);

        nAgPrimerR = new NumberField();
        nAgPrimerR.setValue(0);
        nAgPrimerR.setWidth(ancho);
        nAgSegunR = new NumberField();
        nAgSegunR.setValue(0);
        nAgSegunR.setWidth(ancho);
//      /////////////////////////////////////////////////////////////////////////////////////////

//        tableAnfitrion.setHTML(0, 0, "<div style='font-size: 12px;'><b>Situación Juego</b></span>");
        tableAnfitrion.setHTML(0, 0, "<b>Situación</b>");
        tableAnfitrion.setHTML(0, 1, "<div style='font-size: 12px;'><b>1er Poli</b></span>");
        tableAnfitrion.setHTML(0, 2, "<div style='font-size: 12px;'><b>2do Poli</b></span>");
        tableAnfitrion.setHTML(0, 3, "<div style='font-size: 12px;'><b>1er Rival</b></span>");
        tableAnfitrion.setHTML(0, 4, "<div style='font-size: 12px;'><b>2do Rival</b></span>");

        tableAnfitrion.setWidget(1, 0, btnFz1);
        tableAnfitrion.setWidget(1, 1, nFz1Primer);
        tableAnfitrion.setWidget(1, 2, nFz1Segun);
        tableAnfitrion.setWidget(1, 3, nFz1PrimerR);
        tableAnfitrion.setWidget(1, 4, nFz1SegunR);

        tableAnfitrion.setWidget(2, 0, btnFz2);
        tableAnfitrion.setWidget(2, 1, nFz2Primer);
        tableAnfitrion.setWidget(2, 2, nFz2Segun);
        tableAnfitrion.setWidget(2, 3, nFz2PrimerR);
        tableAnfitrion.setWidget(2, 4, nFz2SegunR);

        tableAnfitrion.setWidget(3, 0, btnFz3);
        tableAnfitrion.setWidget(3, 1, nFz3Primer);
        tableAnfitrion.setWidget(3, 2, nFz3Segun);
        tableAnfitrion.setWidget(3, 3, nFz3PrimerR);
        tableAnfitrion.setWidget(3, 4, nFz3SegunR);

        tableAnfitrion.setWidget(4, 0, btnRb1);
        tableAnfitrion.setWidget(4, 1, nRbz1Primer);
        tableAnfitrion.setWidget(4, 2, nRbz1Segun);
        tableAnfitrion.setWidget(4, 3, nRbz1PrimerR);
        tableAnfitrion.setWidget(4, 4, nRbz1SegunR);

        tableAnfitrion.setWidget(5, 0, btnRb2);
        tableAnfitrion.setWidget(5, 1, nRbz2Primer);
        tableAnfitrion.setWidget(5, 2, nRbz2Segun);
        tableAnfitrion.setWidget(5, 3, nRbz2PrimerR);
        tableAnfitrion.setWidget(5, 4, nRbz2SegunR);

        tableAnfitrion.setWidget(6, 0, btnRb3);
        tableAnfitrion.setWidget(6, 1, nRbz3Primer);
        tableAnfitrion.setWidget(6, 2, nRbz3Segun);
        tableAnfitrion.setWidget(6, 3, nRbz3PrimerR);
        tableAnfitrion.setWidget(6, 4, nRbz3SegunR);

        tableAnfitrion.setWidget(7, 0, btnTl1);
        tableAnfitrion.setWidget(7, 1, nTlz1Primer);
        tableAnfitrion.setWidget(7, 2, nTlz1Segun);
        tableAnfitrion.setWidget(7, 3, nTlz1PrimerR);
        tableAnfitrion.setWidget(7, 4, nTlz1SegunR);

        tableAnfitrion.setWidget(8, 0, btnTl2);
        tableAnfitrion.setWidget(8, 1, nTlz2Primer);
        tableAnfitrion.setWidget(8, 2, nTlz2Segun);
        tableAnfitrion.setWidget(8, 3, nTlz2PrimerR);
        tableAnfitrion.setWidget(8, 4, nTlz2SegunR);

        tableAnfitrion.setWidget(9, 0, btnTl3);
        tableAnfitrion.setWidget(9, 1, nTlz3Primer);
        tableAnfitrion.setWidget(9, 2, nTlz3Segun);
        tableAnfitrion.setWidget(9, 3, nTlz3PrimerR);
        tableAnfitrion.setWidget(9, 4, nTlz3SegunR);

        tableAnfitrion.setWidget(10, 0, btnFl);
        tableAnfitrion.setWidget(10, 1, nFlPrimer);
        tableAnfitrion.setWidget(10, 2, nFlSegun);
        tableAnfitrion.setWidget(10, 3, nFlPrimerR);
        tableAnfitrion.setWidget(10, 4, nFlSegunR);

        tableAnfitrion.setWidget(11, 0, btnTe);
        tableAnfitrion.setWidget(11, 1, nTePrimer);
        tableAnfitrion.setWidget(11, 2, nTeSegun);
        tableAnfitrion.setWidget(11, 3, nTePrimerR);
        tableAnfitrion.setWidget(11, 4, nTeSegunR);

        tableAnfitrion.setWidget(12, 0, btnOg);
        tableAnfitrion.setWidget(12, 1, nOgPrimer);
        tableAnfitrion.setWidget(12, 2, nOgSegun);
        tableAnfitrion.setWidget(12, 3, nOgPrimerR);
        tableAnfitrion.setWidget(12, 4, nOgSegunR);

        tableAnfitrion.setWidget(13, 0, btnRe);
        tableAnfitrion.setWidget(13, 1, nRePrimer);
        tableAnfitrion.setWidget(13, 2, nReSegun);
        tableAnfitrion.setWidget(13, 3, nRePrimerR);
        tableAnfitrion.setWidget(13, 4, nReSegunR);

        tableAnfitrion.setWidget(14, 0, btnPe);
        tableAnfitrion.setWidget(14, 1, nPePrimer);
        tableAnfitrion.setWidget(14, 2, nPeSegun);
        tableAnfitrion.setWidget(14, 3, nPePrimerR);
        tableAnfitrion.setWidget(14, 4, nPeSegunR);

        tableAnfitrion.setWidget(15, 0, btnEe);
        tableAnfitrion.setWidget(15, 1, nEePrimer);
        tableAnfitrion.setWidget(15, 2, nEeSegun);
        tableAnfitrion.setWidget(15, 3, nEePrimerR);
        tableAnfitrion.setWidget(15, 4, nEeSegunR);

        tableAnfitrion.setWidget(16, 0, btnAg);
        tableAnfitrion.setWidget(16, 1, nAgPrimer);
        tableAnfitrion.setWidget(16, 2, nAgSegun);
        tableAnfitrion.setWidget(16, 3, nAgPrimerR);
        tableAnfitrion.setWidget(16, 4, nAgSegunR);
////////////////////////////////////////////////////////////////////////////////
//     
//        tabItemAnfitrion.add(new Text("Hola"));
        cpSituacionesJuego.add(tableAnfitrion);

        return cpSituacionesJuego;
    }

    public void preguntaTiempoEquipo(final NumberField nf1local, final NumberField nf2local, final NumberField nf1Rival, final NumberField nf2Rival, String nombreSituacion) {

        final Window window = new Window();
        window.setSize(300, 160);
        window.setPlain(true);
        window.setModal(true);
        window.setBlinkModal(true);
        window.setHeading(nombreSituacion);
        window.setLayout(new FillLayout());
        FormData formData = new FormData("10");

        FormPanel simple = new FormPanel();
        simple.setFrame(true);
        simple.setHeaderVisible(false);

        rd1Tiempo = new Radio();
        rd1Tiempo.setBoxLabel("1er Tiempo");
        rd1Tiempo.setValue(true);

        rd2Tiempo = new Radio();
        rd2Tiempo.setBoxLabel("2do Tiempo");

        RadioGroup radioGroup = new RadioGroup();
        radioGroup.setFieldLabel("Tiempo");
        radioGroup.add(rd1Tiempo);
        radioGroup.add(rd2Tiempo);
        simple.add(radioGroup, formData);

        rdAnfitrion = new Radio();
        rdAnfitrion.setBoxLabel("POLITÉCNICO JIC");
        rdAnfitrion.setValue(true);

        rdRival = new Radio();
        rdRival.setBoxLabel("RIVAL");

        RadioGroup radioGroup2 = new RadioGroup();
        radioGroup2.setFieldLabel("Equipo");
        radioGroup2.add(rdAnfitrion);
        radioGroup2.add(rdRival);
        simple.add(radioGroup2, formData);
        window.add(simple);
        window.setButtonAlign(Style.HorizontalAlignment.CENTER);

        Button btnAceptar = new Button("Aceptar", new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                if (rd1Tiempo.getValue() == true && rdAnfitrion.getValue() == true) {
                    nf1local.setValue(nf1local.getValue().intValue() + 1);
                } else if (rd2Tiempo.getValue() == true && rdAnfitrion.getValue() == true) {
                    nf2local.setValue(nf2local.getValue().intValue() + 1);
                } else if (rd1Tiempo.getValue() == true && rdRival.getValue() == true) {
                    nf1Rival.setValue(nf1Rival.getValue().intValue() + 1);
                } else if (rd2Tiempo.getValue() == true && rdRival.getValue() == true) {
                    nf2Rival.setValue(nf2Rival.getValue().intValue() + 1);
                }
                window.hide();
            }
        });
        Button btnCancelar = new Button("Cancelar", new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {
                window.hide();
            }
        });
        window.addButton(btnAceptar);
        window.addButton(btnCancelar);
        window.setFocusWidget(window.getButtonBar().getItem(0));

        window.show();
    }

    public RPCAdminSituacionesJuegoAsync getServiceSituaciones() {
        RPCAdminSituacionesJuegoAsync svc = (RPCAdminSituacionesJuegoAsync) GWT.create(RPCAdminSituacionesJuego.class);
        ServiceDefTarget endpoint = (ServiceDefTarget) svc;
        endpoint.setServiceEntryPoint("services/RPCAdminSituacionesJuego");
        return svc;
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
    
    public void buscarSituacionesxCompetencia(Long idCompetencia, final boolean habilitar){
    
        getServiceSituaciones().getSituacionesXCompetencia(idCompetencia, new AsyncCallback<List<SituacionesJuegoCompe>>() {

            @Override
            public void onFailure(Throwable caught) {
                System.out.println("No fue posible consultar las situaciones");
            }

            @Override
            public void onSuccess(List<SituacionesJuegoCompe> result) {
                cargarSitucionesJuego(result, habilitar);
                habilitarBotonesSituaciones(habilitar);
            }
        });
    
    
    }

    public void cargarSitucionesJuego(List<SituacionesJuegoCompe> situacionesJuegoCompes, boolean habitados) {

        for (SituacionesJuegoCompe situacionesJuegoCompe : situacionesJuegoCompes) {
            if (situacionesJuegoCompe.getEquipoSituacion().equalsIgnoreCase("POLITECNICO JIC")) {
                if (situacionesJuegoCompe.getTiempoSituacion().intValue() == 1) {
                    nFz1Primer.setValue(situacionesJuegoCompe.getFaltaZona1());
                    nFz1Primer.setEnabled(habitados);
                    nFz2Primer.setValue(situacionesJuegoCompe.getFaltaZona2());
                    nFz2Primer.setEnabled(habitados);
                    nFz3Primer.setValue(situacionesJuegoCompe.getFaltaZona3());
                    nFz3Primer.setEnabled(habitados);
                    nRbz1Primer.setValue(situacionesJuegoCompe.getRecuperacionZona1());
                    nRbz1Primer.setEnabled(habitados);
                    nRbz2Primer.setValue(situacionesJuegoCompe.getRecuperacionZona2());
                    nRbz2Primer.setEnabled(habitados);
                    nRbz3Primer.setValue(situacionesJuegoCompe.getRecuperacionZona3());
                    nRbz3Primer.setEnabled(habitados);
                    nTlz1Primer.setValue(situacionesJuegoCompe.getTiroLibreZona1());
                    nTlz1Primer.setEnabled(habitados);
                    nTlz2Primer.setValue(situacionesJuegoCompe.getTiroLibreZona2());
                    nTlz2Primer.setEnabled(habitados);
                    nTlz3Primer.setValue(situacionesJuegoCompe.getTiroLibreZona3());
                    nTlz3Primer.setEnabled(habitados);
                    nTePrimer.setValue(situacionesJuegoCompe.getTiroEsquina());
                    nTePrimer.setEnabled(habitados);
                    nFlPrimer.setValue(situacionesJuegoCompe.getFueraLugar());
                    nFlPrimer.setEnabled(habitados);
                    nPePrimer.setValue(situacionesJuegoCompe.getPenalty());
                    nPePrimer.setEnabled(habitados);
                    nOgPrimer.setValue(situacionesJuegoCompe.getOpcionGol());
                    nOgPrimer.setEnabled(habitados);
//                    situaAnfrition1.setCentrolLateral(0);
                    nRePrimer.setValue(situacionesJuegoCompe.getRemates());
                    nRePrimer.setEnabled(habitados);
                    nEePrimer.setValue(situacionesJuegoCompe.getEntregasErradas());
                    nEePrimer.setEnabled(habitados);
                } else {

                    nFz1Segun.setValue(situacionesJuegoCompe.getFaltaZona1());
                    nFz1Segun.setEnabled(habitados);
                    nFz2Segun.setValue(situacionesJuegoCompe.getFaltaZona2());
                    nFz2Segun.setEnabled(habitados);
                    nFz3Segun.setValue(situacionesJuegoCompe.getFaltaZona3());
                    nFz3Segun.setEnabled(habitados);
                    nRbz1Segun.setValue(situacionesJuegoCompe.getRecuperacionZona1());
                    nRbz1Segun.setEnabled(habitados);
                    nRbz2Segun.setValue(situacionesJuegoCompe.getRecuperacionZona2());
                    nRbz2Segun.setEnabled(habitados);
                    nRbz3Segun.setValue(situacionesJuegoCompe.getRecuperacionZona3());
                    nRbz3Segun.setEnabled(habitados);
                    nTlz1Segun.setValue(situacionesJuegoCompe.getTiroLibreZona1());
                    nTlz1Segun.setEnabled(habitados);
                    nTlz2Segun.setValue(situacionesJuegoCompe.getTiroLibreZona2());
                    nTlz2Segun.setEnabled(habitados);
                    nTlz3Segun.setValue(situacionesJuegoCompe.getTiroLibreZona3());
                    nTlz3Segun.setEnabled(habitados);
                    nTeSegun.setValue(situacionesJuegoCompe.getTiroEsquina());
                    nTeSegun.setEnabled(habitados);
                    nFlSegun.setValue(situacionesJuegoCompe.getFueraLugar());
                    nFlSegun.setEnabled(habitados);
                    nPeSegun.setValue(situacionesJuegoCompe.getPenalty());
                    nPeSegun.setEnabled(habitados);
                    nOgSegun.setValue(situacionesJuegoCompe.getOpcionGol());
                    nOgSegun.setEnabled(habitados);
//                    situaAnfrition1.setCentrolLateral(0);
                    nReSegun.setValue(situacionesJuegoCompe.getRemates());
                    nReSegun.setEnabled(habitados);
                    nEeSegun.setValue(situacionesJuegoCompe.getEntregasErradas());
                    nEeSegun.setEnabled(habitados);
                }
            } else if (situacionesJuegoCompe.getEquipoSituacion().equalsIgnoreCase("RIVAL")) {
                if (situacionesJuegoCompe.getTiempoSituacion().intValue() == 1) {

                    nFz1PrimerR.setValue(situacionesJuegoCompe.getFaltaZona1());
                    nFz1PrimerR.setEnabled(habitados);
                    nFz2PrimerR.setValue(situacionesJuegoCompe.getFaltaZona2());
                    nFz2PrimerR.setEnabled(habitados);
                    nFz3PrimerR.setValue(situacionesJuegoCompe.getFaltaZona3());
                    nFz3PrimerR.setEnabled(habitados);
                    nRbz1PrimerR.setValue(situacionesJuegoCompe.getRecuperacionZona1());
                    nRbz1PrimerR.setEnabled(habitados);
                    nRbz2PrimerR.setValue(situacionesJuegoCompe.getRecuperacionZona2());
                    nRbz2PrimerR.setEnabled(habitados);
                    nRbz3PrimerR.setValue(situacionesJuegoCompe.getRecuperacionZona3());
                    nRbz3PrimerR.setEnabled(habitados);
                    nTlz1PrimerR.setValue(situacionesJuegoCompe.getTiroLibreZona1());
                    nTlz1PrimerR.setEnabled(habitados);
                    nTlz2PrimerR.setValue(situacionesJuegoCompe.getTiroLibreZona2());
                    nTlz2PrimerR.setEnabled(habitados);
                    nTlz3PrimerR.setValue(situacionesJuegoCompe.getTiroLibreZona3());
                    nTlz3PrimerR.setEnabled(habitados);
                    nTePrimerR.setValue(situacionesJuegoCompe.getTiroEsquina());
                    nTePrimerR.setEnabled(habitados);
                    nFlPrimerR.setValue(situacionesJuegoCompe.getFueraLugar());
                    nFlPrimerR.setEnabled(habitados);
                    nPePrimerR.setValue(situacionesJuegoCompe.getPenalty());
                    nPePrimerR.setEnabled(habitados);
                    nOgPrimerR.setValue(situacionesJuegoCompe.getOpcionGol());
                    nOgPrimerR.setEnabled(habitados);
//                    situaAnfrition1.setCentrolLateral(0);
                    nRePrimerR.setValue(situacionesJuegoCompe.getRemates());
                    nRePrimerR.setEnabled(habitados);
                    nEePrimerR.setValue(situacionesJuegoCompe.getEntregasErradas());
                    nEePrimerR.setEnabled(habitados);

                } else {

                    nFz1SegunR.setValue(situacionesJuegoCompe.getFaltaZona1());
                    nFz1SegunR.setEnabled(habitados);
                    nFz2SegunR.setValue(situacionesJuegoCompe.getFaltaZona2());
                    nFz2SegunR.setEnabled(habitados);
                    nFz3SegunR.setValue(situacionesJuegoCompe.getFaltaZona3());
                    nFz3SegunR.setEnabled(habitados);
                    nRbz1SegunR.setValue(situacionesJuegoCompe.getRecuperacionZona1());
                    nRbz1SegunR.setEnabled(habitados);
                    nRbz2SegunR.setValue(situacionesJuegoCompe.getRecuperacionZona2());
                    nRbz2SegunR.setEnabled(habitados);
                    nRbz3SegunR.setValue(situacionesJuegoCompe.getRecuperacionZona3());
                    nRbz3SegunR.setEnabled(habitados);
                    nTlz1SegunR.setValue(situacionesJuegoCompe.getTiroLibreZona1());
                    nTlz1SegunR.setEnabled(habitados);
                    nTlz2SegunR.setValue(situacionesJuegoCompe.getTiroLibreZona2());
                    nTlz2SegunR.setEnabled(habitados);
                    nTlz3SegunR.setValue(situacionesJuegoCompe.getTiroLibreZona3());
                    nTlz3SegunR.setEnabled(habitados);
                    nTeSegunR.setValue(situacionesJuegoCompe.getTiroEsquina());
                    nTeSegunR.setEnabled(habitados);
                    nFlSegunR.setValue(situacionesJuegoCompe.getFueraLugar());
                    nFlSegunR.setEnabled(habitados);
                    nPeSegunR.setValue(situacionesJuegoCompe.getPenalty());
                    nPeSegunR.setEnabled(habitados);
                    nOgSegunR.setValue(situacionesJuegoCompe.getOpcionGol());
                    nOgSegunR.setEnabled(habitados);
//                    situaAnfrition1.setCentrolLateral(0);
                    nReSegunR.setValue(situacionesJuegoCompe.getRemates());
                    nReSegunR.setEnabled(habitados);
                    nEeSegunR.setValue(situacionesJuegoCompe.getEntregasErradas());
                    nEeSegunR.setEnabled(habitados);

                }
            }
        }

    }

    public void habilitarBotonesSituaciones(boolean habilitar) {

        btnFz1.setEnabled(habilitar);
        btnFz2.setEnabled(habilitar);
        btnFz3.setEnabled(habilitar);
        btnRb1.setEnabled(habilitar);
        btnRb2.setEnabled(habilitar);
        btnRb3.setEnabled(habilitar);
        btnTl1.setEnabled(habilitar);
        btnTl2.setEnabled(habilitar);
        btnTl3.setEnabled(habilitar);
        btnFl.setEnabled(habilitar);
        btnTe.setEnabled(habilitar);
        btnOg.setEnabled(habilitar);
        btnRe.setEnabled(habilitar);
        btnPe.setEnabled(habilitar);
        btnEe.setEnabled(habilitar);
        btnAg.setEnabled(habilitar);
        btnGuardarSituaciones.setEnabled(habilitar);

    }

}
