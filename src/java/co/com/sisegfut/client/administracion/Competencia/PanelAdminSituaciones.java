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
import com.extjs.gxt.ui.client.widget.MessageBox;
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
    private Button btnCl;

    //////////////////////////////////////////////////////////////////////////////////////////////
    private List<DTOSituacionJuegoComp> listaValoresJuego;
////////////////////////////// campos de 1er y 2do de anfitrion /////////////////////////////////

    private FlexTable tableSituaciones;
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

    private NumberField nClPrimer;
    private NumberField nClSegun;

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

    private NumberField nClPrimerR;
    private NumberField nClSegunR;
    //////////////////////////////////////////////////////////////////////////////////////////////////////////
    private Radio rd1Tiempo;
    private Radio rd2Tiempo;
    private Radio rdAnfitrion;
    private Radio rdRival;
    private Button btnGuardarSituaciones;
    private Button btnReiniciar;
    private Long IdCompetencia;
    private FormPanel cp;

    private Main myConstants = (Main) GWT.create(Main.class);

    public PanelAdminSituaciones() {
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
//        FormButtonBinding binding2 = new FormButtonBinding(cp);
//        binding2.addButton(btnGuardarSituaciones);
//
        cp.addButton(btnGuardarSituaciones);

        cp.setButtonAlign(Style.HorizontalAlignment.CENTER);

        add(cp);

    }

    protected SelectionListener<ButtonEvent> listenerGuardarSistuaciones() {
        return new SelectionListener<ButtonEvent>() {
            @Override
            public void componentSelected(ButtonEvent ce) {

                if (cp.isValid()) {
                        getServiceSituaciones().getSituacionesXCompetencia(IdCompetencia, new AsyncCallback<List<SituacionesJuegoCompe>>() {

                        @Override
                        public void onFailure(Throwable caught) {
                            Info.display("Error", "No Se guardaron las situaciones de juego");
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
                } else {
                    MessageBox.info("Error", "Verifique los datos", null);
                }

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
        situaAnfrition1.setCentrolLateral(nClPrimer.getValue().intValue());
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
        situaAnfrition2.setCentrolLateral(nClSegun.getValue().intValue());

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
        situaRival1.setCentrolLateral(nClPrimerR.getValue().intValue());

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
        situaRival2.setCentrolLateral(nClSegunR.getValue().intValue());

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

    private FormPanel crearSituacionesJuego() {

        FormPanel cpSituacionesJuego = new FormPanel();
        cpSituacionesJuego.setBodyBorder(true);
        cpSituacionesJuego.setScrollMode(Style.Scroll.AUTO);
        cpSituacionesJuego.setIcon(Resources.ICONS.table());
        cpSituacionesJuego.setHeaderVisible(false);
        cpSituacionesJuego.setLayout(new FillLayout());

//        FormButtonBinding binding2 = new FormButtonBinding(cpSituacionesJuego);
//        binding2.addButton(btnGuardarSituaciones);
        tableSituaciones = new FlexTable();
//        tableSituaciones.setWidth("50px");
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
                if (txt.equals("<b>Falta zona 1</b>")) {
                    preguntaTiempoEquipo(nFz1Primer, nFz1Segun, nFz1PrimerR, nFz1SegunR, txt);
                } else if (txt.equals("<b>Falta zona 2</b>")) {
                    preguntaTiempoEquipo(nFz2Primer, nFz2Segun, nFz2PrimerR, nFz2SegunR, txt);
                } else if (txt.equals("<b>Falta zona 3</b>")) {
                    preguntaTiempoEquipo(nFz3Primer, nFz3Segun, nFz3PrimerR, nFz3SegunR, txt);
                } else if (txt.equals("<b>Recuperación balon Zona 1</b>")) {
                    preguntaTiempoEquipo(nRbz1Primer, nRbz1Segun, nRbz1PrimerR, nRbz1SegunR, txt);
                } else if (txt.equals("<b>Recuperación balon Zona 2</b>")) {
                    preguntaTiempoEquipo(nRbz2Primer, nRbz2Segun, nRbz2PrimerR, nRbz2SegunR, txt);
                } else if (txt.equals("<b>Recuperación balon Zona 3</b>")) {
                    preguntaTiempoEquipo(nRbz3Primer, nRbz3Segun, nRbz3PrimerR, nRbz3SegunR, txt);
                } else if (txt.equals("<b>Tiro libre Zona 1</b>")) {
                    preguntaTiempoEquipo(nTlz1Primer, nTlz1Segun, nTlz1PrimerR, nTlz1SegunR, txt);
                } else if (txt.equals("<b>Tiro libre Zona 2</b>")) {
                    preguntaTiempoEquipo(nTlz2Primer, nTlz2Segun, nTlz2PrimerR, nTlz2SegunR, txt);
                } else if (txt.equals("<b>Tiro libre Zona 3</b>")) {
                    preguntaTiempoEquipo(nTlz3Primer, nTlz3Segun, nTlz3PrimerR, nTlz3SegunR, txt);
                } else if (txt.equals("<b>Fuera de lugar</b>")) {
                    preguntaTiempoEquipo(nFlPrimer, nFlSegun, nFlPrimerR, nFlSegunR, txt);
                } else if (txt.equals("<b>Tiro esquina</b>")) {
                    preguntaTiempoEquipo(nTePrimer, nTeSegun, nTePrimerR, nTeSegunR, txt);
                } else if (txt.equals("<b>Opciones gol</b>")) {
                    preguntaTiempoEquipo(nOgPrimer, nOgSegun, nOgPrimerR, nOgSegunR, txt);
                } else if (txt.equals("<b>Remates</b>")) {
                    preguntaTiempoEquipo(nRePrimer, nReSegun, nRePrimerR, nReSegunR, txt);
                } else if (txt.equals("<b>Penalty</b>")) {
                    preguntaTiempoEquipo(nPePrimer, nPeSegun, nPePrimerR, nPeSegunR, txt);
                } else if (txt.equals("<b>Entregas erradas</b>")) {
                    preguntaTiempoEquipo(nEePrimer, nEeSegun, nEePrimerR, nEeSegunR, txt);
                } else if (txt.equals("<b>Centro lateral</b>")) {
                    preguntaTiempoEquipo(nClPrimer, nClSegun, nClPrimerR, nClSegunR, txt);
                }

            }
        };
        int anchotbtn = 200;
//        Button btnFz1 = new Button("<b>Falta zona 1<b/>", Resources.ICONS.iconoNuevaCita(), sl);

        btnFz1 = new Button("<b>Falta zona 1</b>", Resources.ICONS.iconoNuevaCita(), sl);
        btnFz1.setWidth(anchotbtn);
        btnFz2 = new Button("<b>Falta zona 2</b>", Resources.ICONS.iconoNuevaCita(), sl);
        btnFz2.setWidth(anchotbtn);
        btnFz3 = new Button("<b>Falta zona 3</b>", Resources.ICONS.iconoNuevaCita(), sl);
        btnFz3.setWidth(anchotbtn);
        btnRb1 = new Button("<b>Recuperación balon Zona 1</b>", Resources.ICONS.iconoNuevaCita(), sl);
        btnRb1.setWidth(anchotbtn);
        btnRb2 = new Button("<b>Recuperación balon Zona 2</b>", Resources.ICONS.iconoNuevaCita(), sl);
        btnRb2.setWidth(anchotbtn);
        btnRb3 = new Button("<b>Recuperación balon Zona 3</b>", Resources.ICONS.iconoNuevaCita(), sl);
        btnRb3.setWidth(anchotbtn);
        btnTl1 = new Button("<b>Tiro libre Zona 1</b>", Resources.ICONS.iconoNuevaCita(), sl);
        btnTl1.setWidth(anchotbtn);
        btnTl2 = new Button("<b>Tiro libre Zona 2</b>", Resources.ICONS.iconoNuevaCita(), sl);
        btnTl2.setWidth(anchotbtn);
        btnTl3 = new Button("<b>Tiro libre Zona 3</b>", Resources.ICONS.iconoNuevaCita(), sl);
        btnTl3.setWidth(anchotbtn);
        btnFl = new Button("<b>Fuera de lugar</b>", Resources.ICONS.iconoNuevaCita(), sl);
        btnFl.setWidth(anchotbtn);
        btnTe = new Button("<b>Tiro esquina</b>", Resources.ICONS.iconoNuevaCita(), sl);
        btnTe.setWidth(anchotbtn);
        btnOg = new Button("<b>Opciones gol</b>", Resources.ICONS.iconoNuevaCita(), sl);
        btnOg.setWidth(anchotbtn);
        btnRe = new Button("<b>Remates</b>", Resources.ICONS.iconoNuevaCita(), sl);
        btnRe.setWidth(anchotbtn);
        btnPe = new Button("<b>Penalty</b>", Resources.ICONS.iconoNuevaCita(), sl);
        btnPe.setWidth(anchotbtn);
        btnEe = new Button("<b>Entregas erradas</b>", Resources.ICONS.iconoNuevaCita(), sl);
        btnEe.setWidth(anchotbtn);
        btnCl = new Button("<b>Centro lateral</b>", Resources.ICONS.iconoNuevaCita(), sl);
        btnCl.setWidth(anchotbtn);
        int ancho = 50;

        nFz1Primer = new NumberField();
        nFz1Primer.setValue(0);
        nFz1Primer.setWidth(ancho);
        nFz1Primer.setMaxValue(130);
        nFz1Primer.setMaxLength(3);
        nFz1Primer.setAllowDecimals(false);
        nFz1Primer.setAllowNegative(false);
        nFz1Primer.setAllowBlank(false);

        nFz1Segun = new NumberField();
        nFz1Segun.setValue(0);
        nFz1Segun.setWidth(ancho);
        nFz1Segun.setMaxValue(130);
        nFz1Segun.setMaxLength(3);
        nFz1Segun.setAllowDecimals(false);
        nFz1Segun.setAllowNegative(false);
        nFz1Segun.setAllowBlank(false);

        nFz2Primer = new NumberField();
        nFz2Primer.setValue(0);
        nFz2Primer.setWidth(ancho);
        nFz2Primer.setMaxValue(130);
        nFz2Primer.setMaxLength(3);
        nFz2Primer.setAllowDecimals(false);
        nFz2Primer.setAllowNegative(false);
        nFz2Primer.setAllowBlank(false);

        nFz2Segun = new NumberField();
        nFz2Segun.setValue(0);
        nFz2Segun.setWidth(ancho);
        nFz2Segun.setMaxValue(130);
        nFz2Segun.setMaxLength(3);
        nFz2Segun.setAllowDecimals(false);
        nFz2Segun.setAllowNegative(false);
        nFz2Segun.setAllowBlank(false);

        nFz3Primer = new NumberField();
        nFz3Primer.setValue(0);
        nFz3Primer.setWidth(ancho);
        nFz3Primer.setMaxValue(130);
        nFz3Primer.setMaxLength(3);
        nFz3Primer.setAllowDecimals(false);
        nFz3Primer.setAllowNegative(false);
        nFz3Primer.setAllowBlank(false);

        nFz3Segun = new NumberField();
        nFz3Segun.setValue(0);
        nFz3Segun.setWidth(ancho);
        nFz3Segun.setMaxValue(130);
        nFz3Segun.setMaxLength(3);
        nFz3Segun.setAllowDecimals(false);
        nFz3Segun.setAllowNegative(false);
        nFz3Segun.setAllowBlank(false);

        nRbz1Primer = new NumberField();
        nRbz1Primer.setValue(0);
        nRbz1Primer.setWidth(ancho);
        nRbz1Primer.setMaxValue(130);
        nRbz1Primer.setMaxLength(3);
        nRbz1Primer.setAllowDecimals(false);
        nRbz1Primer.setAllowNegative(false);
        nRbz1Primer.setAllowBlank(false);

        nRbz1Segun = new NumberField();
        nRbz1Segun.setValue(0);
        nRbz1Segun.setWidth(ancho);
        nRbz1Segun.setMaxValue(130);
        nRbz1Segun.setMaxLength(3);
        nRbz1Segun.setAllowDecimals(false);
        nRbz1Segun.setAllowNegative(false);
        nRbz1Segun.setAllowBlank(false);

        nRbz2Primer = new NumberField();
        nRbz2Primer.setValue(0);
        nRbz2Primer.setWidth(ancho);
        nRbz2Primer.setMaxValue(130);
        nRbz2Primer.setMaxLength(3);
        nRbz2Primer.setAllowDecimals(false);
        nRbz2Primer.setAllowNegative(false);
        nRbz2Primer.setAllowBlank(false);

        nRbz2Segun = new NumberField();
        nRbz2Segun.setValue(0);
        nRbz2Segun.setWidth(ancho);
        nRbz2Segun.setMaxValue(130);
        nRbz2Segun.setMaxLength(3);
        nRbz2Segun.setAllowDecimals(false);
        nRbz2Segun.setAllowNegative(false);
        nRbz2Segun.setAllowBlank(false);

        nRbz3Primer = new NumberField();
        nRbz3Primer.setValue(0);
        nRbz3Primer.setWidth(ancho);
        nRbz3Primer.setMaxValue(130);
        nRbz3Primer.setMaxLength(3);
        nRbz3Primer.setAllowDecimals(false);
        nRbz3Primer.setAllowNegative(false);
        nRbz3Primer.setAllowBlank(false);

        nRbz3Segun = new NumberField();
        nRbz3Segun.setValue(0);
        nRbz3Segun.setWidth(ancho);
        nRbz3Segun.setMaxValue(130);
        nRbz3Segun.setMaxLength(3);
        nRbz3Segun.setAllowDecimals(false);
        nRbz3Segun.setAllowNegative(false);
        nRbz3Segun.setAllowBlank(false);

        nTlz1Primer = new NumberField();
        nTlz1Primer.setValue(0);
        nTlz1Primer.setWidth(ancho);
        nTlz1Primer.setMaxValue(130);
        nTlz1Primer.setMaxLength(3);
        nTlz1Primer.setAllowDecimals(false);
        nTlz1Primer.setAllowNegative(false);
        nTlz1Primer.setAllowBlank(false);

        nTlz1Segun = new NumberField();
        nTlz1Segun.setValue(0);
        nTlz1Segun.setWidth(ancho);
        nTlz1Segun.setMaxValue(130);
        nTlz1Segun.setMaxLength(3);
        nTlz1Segun.setAllowDecimals(false);
        nTlz1Segun.setAllowNegative(false);
        nTlz1Segun.setAllowBlank(false);

        nTlz2Primer = new NumberField();
        nTlz2Primer.setValue(0);
        nTlz2Primer.setWidth(ancho);
        nTlz2Primer.setMaxValue(130);
        nTlz2Primer.setMaxLength(3);
        nTlz2Primer.setAllowDecimals(false);
        nTlz2Primer.setAllowNegative(false);
        nTlz2Primer.setAllowBlank(false);

        nTlz2Segun = new NumberField();
        nTlz2Segun.setValue(0);
        nTlz2Segun.setWidth(ancho);
        nTlz2Segun.setMaxValue(130);
        nTlz2Segun.setMaxLength(3);
        nTlz2Segun.setAllowDecimals(false);
        nTlz2Segun.setAllowNegative(false);
        nTlz2Segun.setAllowBlank(false);

        nTlz3Primer = new NumberField();
        nTlz3Primer.setValue(0);
        nTlz3Primer.setWidth(ancho);
        nTlz3Primer.setMaxValue(130);
        nTlz3Primer.setMaxLength(3);
        nTlz3Primer.setAllowDecimals(false);
        nTlz3Primer.setAllowNegative(false);
        nTlz3Primer.setAllowBlank(false);

        nTlz3Segun = new NumberField();
        nTlz3Segun.setValue(0);
        nTlz3Segun.setWidth(ancho);
        nTlz3Segun.setMaxValue(130);
        nTlz3Segun.setMaxLength(3);
        nTlz3Segun.setAllowDecimals(false);
        nTlz3Segun.setAllowNegative(false);
        nTlz3Segun.setAllowBlank(false);

        nFlPrimer = new NumberField();
        nFlPrimer.setValue(0);
        nFlPrimer.setWidth(ancho);
        nFlPrimer.setMaxValue(130);
        nFlPrimer.setMaxLength(3);
        nFlPrimer.setAllowDecimals(false);
        nFlPrimer.setAllowNegative(false);
        nFlPrimer.setAllowBlank(false);

        nFlSegun = new NumberField();
        nFlSegun.setValue(0);
        nFlSegun.setWidth(ancho);
        nFlSegun.setMaxValue(130);
        nFlSegun.setMaxLength(3);
        nFlSegun.setAllowDecimals(false);
        nFlSegun.setAllowNegative(false);
        nFlSegun.setAllowBlank(false);

        nTePrimer = new NumberField();
        nTePrimer.setValue(0);
        nTePrimer.setWidth(ancho);
        nTePrimer.setMaxValue(130);
        nTePrimer.setMaxLength(3);
        nTePrimer.setAllowDecimals(false);
        nTePrimer.setAllowNegative(false);
        nTePrimer.setAllowBlank(false);

        nTeSegun = new NumberField();
        nTeSegun.setValue(0);
        nTeSegun.setWidth(ancho);
        nTeSegun.setMaxValue(130);
        nTeSegun.setMaxLength(3);
        nTeSegun.setAllowDecimals(false);
        nTeSegun.setAllowNegative(false);
        nTeSegun.setAllowBlank(false);

        nOgPrimer = new NumberField();
        nOgPrimer.setValue(0);
        nOgPrimer.setWidth(ancho);
        nOgPrimer.setMaxValue(130);
        nOgPrimer.setMaxLength(3);
        nOgPrimer.setAllowDecimals(false);
        nOgPrimer.setAllowNegative(false);
        nOgPrimer.setAllowBlank(false);

        nOgSegun = new NumberField();
        nOgSegun.setValue(0);
        nOgSegun.setWidth(ancho);
        nOgSegun.setMaxValue(130);
        nOgSegun.setMaxLength(3);
        nOgSegun.setAllowDecimals(false);
        nOgSegun.setAllowNegative(false);
        nOgSegun.setAllowBlank(false);

        nRePrimer = new NumberField();
        nRePrimer.setValue(0);
        nRePrimer.setWidth(ancho);
        nRePrimer.setMaxValue(130);
        nRePrimer.setMaxLength(3);
        nRePrimer.setAllowDecimals(false);
        nRePrimer.setAllowNegative(false);
        nRePrimer.setAllowBlank(false);

        nReSegun = new NumberField();
        nReSegun.setValue(0);
        nReSegun.setWidth(ancho);
        nReSegun.setMaxValue(130);
        nReSegun.setMaxLength(3);
        nReSegun.setAllowDecimals(false);
        nReSegun.setAllowNegative(false);
        nReSegun.setAllowBlank(false);

        nPePrimer = new NumberField();
        nPePrimer.setValue(0);
        nPePrimer.setWidth(ancho);
        nPePrimer.setMaxValue(130);
        nPePrimer.setMaxLength(3);
        nPePrimer.setAllowDecimals(false);
        nPePrimer.setAllowNegative(false);
        nPePrimer.setAllowBlank(false);

        nPeSegun = new NumberField();
        nPeSegun.setValue(0);
        nPeSegun.setWidth(ancho);
        nPeSegun.setMaxValue(130);
        nPeSegun.setMaxLength(3);
        nPeSegun.setAllowDecimals(false);
        nPeSegun.setAllowNegative(false);
        nPeSegun.setAllowBlank(false);

        nEePrimer = new NumberField();
        nEePrimer.setValue(0);
        nEePrimer.setWidth(ancho);
        nEePrimer.setMaxValue(130);
        nEePrimer.setMaxLength(3);
        nEePrimer.setAllowDecimals(false);
        nEePrimer.setAllowNegative(false);
        nEePrimer.setAllowBlank(false);

        nEeSegun = new NumberField();
        nEeSegun.setValue(0);
        nEeSegun.setWidth(ancho);
        nEeSegun.setMaxValue(130);
        nEeSegun.setMaxLength(3);
        nEeSegun.setAllowDecimals(false);
        nEeSegun.setAllowNegative(false);
        nEeSegun.setAllowBlank(false);

        nClPrimer = new NumberField();
        nClPrimer.setValue(0);
        nClPrimer.setWidth(ancho);
        nClPrimer.setMaxValue(130);
        nClPrimer.setMaxLength(3);
        nClPrimer.setAllowDecimals(false);
        nClPrimer.setAllowNegative(false);
        nClPrimer.setAllowBlank(false);

        nClSegun = new NumberField();
        nClSegun.setValue(0);
        nClSegun.setWidth(ancho);
        nClSegun.setMaxValue(130);
        nClSegun.setMaxLength(3);
        nClSegun.setAllowDecimals(false);
        nClSegun.setAllowNegative(false);
        nClSegun.setAllowBlank(false);
        ///////////////////////////////////////////////////////////
        /////////////////// inicialicacion variables de campos del rival ///////////////////////
        nFz1PrimerR = new NumberField();
        nFz1PrimerR.setValue(0);
        nFz1PrimerR.setWidth(ancho);
        nFz1PrimerR.setMaxValue(130);
        nFz1PrimerR.setMaxLength(3);
        nFz1PrimerR.setAllowDecimals(false);
        nFz1PrimerR.setAllowNegative(false);
        nFz1PrimerR.setAllowBlank(false);

        nFz1SegunR = new NumberField();
        nFz1SegunR.setValue(0);
        nFz1SegunR.setWidth(ancho);
        nFz1SegunR.setMaxValue(130);
        nFz1SegunR.setMaxLength(3);
        nFz1SegunR.setAllowDecimals(false);
        nFz1SegunR.setAllowNegative(false);
        nFz1SegunR.setAllowBlank(false);

        nFz2PrimerR = new NumberField();
        nFz2PrimerR.setValue(0);
        nFz2PrimerR.setWidth(ancho);
        nFz2PrimerR.setMaxValue(130);
        nFz2PrimerR.setMaxLength(3);
        nFz2PrimerR.setAllowDecimals(false);
        nFz2PrimerR.setAllowNegative(false);
        nFz2PrimerR.setAllowBlank(false);

        nFz2SegunR = new NumberField();
        nFz2SegunR.setValue(0);
        nFz2SegunR.setWidth(ancho);
        nFz2SegunR.setMaxValue(130);
        nFz2SegunR.setMaxLength(3);
        nFz2SegunR.setAllowDecimals(false);
        nFz2SegunR.setAllowNegative(false);
        nFz2SegunR.setAllowBlank(false);

        nFz3PrimerR = new NumberField();
        nFz3PrimerR.setValue(0);
        nFz3PrimerR.setWidth(ancho);
        nFz3PrimerR.setMaxValue(130);
        nFz3PrimerR.setMaxLength(3);
        nFz3PrimerR.setAllowDecimals(false);
        nFz3PrimerR.setAllowNegative(false);
        nFz3PrimerR.setAllowBlank(false);

        nFz3SegunR = new NumberField();
        nFz3SegunR.setValue(0);
        nFz3SegunR.setWidth(ancho);
        nFz3SegunR.setMaxValue(130);
        nFz3SegunR.setMaxLength(3);
        nFz3SegunR.setAllowDecimals(false);
        nFz3SegunR.setAllowNegative(false);
        nFz3SegunR.setAllowBlank(false);

        nRbz1PrimerR = new NumberField();
        nRbz1PrimerR.setValue(0);
        nRbz1PrimerR.setWidth(ancho);
        nRbz1PrimerR.setMaxValue(130);
        nRbz1PrimerR.setMaxLength(3);
        nRbz1PrimerR.setAllowDecimals(false);
        nRbz1PrimerR.setAllowNegative(false);
        nRbz1PrimerR.setAllowBlank(false);

        nRbz1SegunR = new NumberField();
        nRbz1SegunR.setValue(0);
        nRbz1SegunR.setWidth(ancho);
        nRbz1SegunR.setMaxValue(130);
        nRbz1SegunR.setMaxLength(3);
        nRbz1SegunR.setAllowDecimals(false);
        nRbz1SegunR.setAllowNegative(false);
        nRbz1SegunR.setAllowBlank(false);

        nRbz2PrimerR = new NumberField();
        nRbz2PrimerR.setValue(0);
        nRbz2PrimerR.setWidth(ancho);
        nRbz2PrimerR.setMaxValue(130);
        nRbz2PrimerR.setMaxLength(3);
        nRbz2PrimerR.setAllowDecimals(false);
        nRbz2PrimerR.setAllowNegative(false);
        nRbz2PrimerR.setAllowBlank(false);

        nRbz2SegunR = new NumberField();
        nRbz2SegunR.setValue(0);
        nRbz2SegunR.setWidth(ancho);
        nRbz2SegunR.setMaxValue(130);
        nRbz2SegunR.setMaxLength(3);
        nRbz2SegunR.setAllowDecimals(false);
        nRbz2SegunR.setAllowNegative(false);
        nRbz2SegunR.setAllowBlank(false);

        nRbz3PrimerR = new NumberField();
        nRbz3PrimerR.setValue(0);
        nRbz3PrimerR.setWidth(ancho);
        nRbz3PrimerR.setMaxValue(130);
        nRbz3PrimerR.setMaxLength(3);
        nRbz3PrimerR.setAllowDecimals(false);
        nRbz3PrimerR.setAllowNegative(false);
        nRbz3PrimerR.setAllowBlank(false);

        nRbz3SegunR = new NumberField();
        nRbz3SegunR.setValue(0);
        nRbz3SegunR.setWidth(ancho);
        nRbz3SegunR.setMaxValue(130);
        nRbz3SegunR.setMaxLength(3);
        nRbz3SegunR.setAllowDecimals(false);
        nRbz3SegunR.setAllowNegative(false);
        nRbz3SegunR.setAllowBlank(false);

        nTlz1PrimerR = new NumberField();
        nTlz1PrimerR.setValue(0);
        nTlz1PrimerR.setWidth(ancho);
        nTlz1PrimerR.setMaxValue(130);
        nTlz1PrimerR.setMaxLength(3);
        nTlz1PrimerR.setAllowDecimals(false);
        nTlz1PrimerR.setAllowNegative(false);
        nTlz1PrimerR.setAllowBlank(false);

        nTlz1SegunR = new NumberField();
        nTlz1SegunR.setValue(0);
        nTlz1SegunR.setWidth(ancho);
        nTlz1SegunR.setMaxValue(130);
        nTlz1SegunR.setMaxLength(3);
        nTlz1SegunR.setAllowDecimals(false);
        nTlz1SegunR.setAllowNegative(false);
        nTlz1SegunR.setAllowBlank(false);

        nTlz2PrimerR = new NumberField();
        nTlz2PrimerR.setValue(0);
        nTlz2PrimerR.setWidth(ancho);
        nTlz2PrimerR.setMaxValue(130);
        nTlz2PrimerR.setMaxLength(3);
        nTlz2PrimerR.setAllowDecimals(false);
        nTlz2PrimerR.setAllowNegative(false);
        nTlz2PrimerR.setAllowBlank(false);

        nTlz2SegunR = new NumberField();
        nTlz2SegunR.setValue(0);
        nTlz2SegunR.setWidth(ancho);
        nTlz2SegunR.setMaxValue(130);
        nTlz2SegunR.setMaxLength(3);
        nTlz2SegunR.setAllowDecimals(false);
        nTlz2SegunR.setAllowNegative(false);
        nTlz2SegunR.setAllowBlank(false);

        nTlz3PrimerR = new NumberField();
        nTlz3PrimerR.setValue(0);
        nTlz3PrimerR.setWidth(ancho);
        nTlz3PrimerR.setMaxValue(130);
        nTlz3PrimerR.setMaxLength(3);
        nTlz3PrimerR.setAllowDecimals(false);
        nTlz3PrimerR.setAllowNegative(false);
        nTlz3PrimerR.setAllowBlank(false);

        nTlz3SegunR = new NumberField();
        nTlz3SegunR.setValue(0);
        nTlz3SegunR.setWidth(ancho);
        nTlz3SegunR.setMaxValue(130);
        nTlz3SegunR.setMaxLength(3);
        nTlz3SegunR.setAllowDecimals(false);
        nTlz3SegunR.setAllowNegative(false);
        nTlz3SegunR.setAllowBlank(false);

        nFlPrimerR = new NumberField();
        nFlPrimerR.setValue(0);
        nFlPrimerR.setWidth(ancho);
        nFlPrimerR.setMaxValue(130);
        nFlPrimerR.setMaxLength(3);
        nFlPrimerR.setAllowDecimals(false);
        nFlPrimerR.setAllowNegative(false);
        nFlPrimerR.setAllowBlank(false);

        nFlSegunR = new NumberField();
        nFlSegunR.setValue(0);
        nFlSegunR.setWidth(ancho);
        nFlSegunR.setMaxValue(130);
        nFlSegunR.setMaxLength(3);
        nFlSegunR.setAllowDecimals(false);
        nFlSegunR.setAllowNegative(false);
        nFlSegunR.setAllowBlank(false);

        nTePrimerR = new NumberField();
        nTePrimerR.setValue(0);
        nTePrimerR.setWidth(ancho);
        nTePrimerR.setMaxValue(130);
        nTePrimerR.setMaxLength(3);
        nTePrimerR.setAllowDecimals(false);
        nTePrimerR.setAllowNegative(false);
        nTePrimerR.setAllowBlank(false);

        nTeSegunR = new NumberField();
        nTeSegunR.setValue(0);
        nTeSegunR.setWidth(ancho);
        nTeSegunR.setMaxValue(130);
        nTeSegunR.setMaxLength(3);
        nTeSegunR.setAllowDecimals(false);
        nTeSegunR.setAllowNegative(false);
        nTeSegunR.setAllowBlank(false);

        nOgPrimerR = new NumberField();
        nOgPrimerR.setValue(0);
        nOgPrimerR.setWidth(ancho);
        nOgPrimerR.setMaxValue(130);
        nOgPrimerR.setMaxLength(3);
        nOgPrimerR.setAllowDecimals(false);
        nOgPrimerR.setAllowNegative(false);
        nOgPrimerR.setAllowBlank(false);

        nOgSegunR = new NumberField();
        nOgSegunR.setValue(0);
        nOgSegunR.setWidth(ancho);
        nOgSegunR.setMaxValue(130);
        nOgSegunR.setMaxLength(3);
        nOgSegunR.setAllowDecimals(false);
        nOgSegunR.setAllowNegative(false);
        nOgSegunR.setAllowBlank(false);

        nRePrimerR = new NumberField();
        nRePrimerR.setValue(0);
        nRePrimerR.setWidth(ancho);
        nRePrimerR.setMaxValue(130);
        nRePrimerR.setMaxLength(3);
        nRePrimerR.setAllowDecimals(false);
        nRePrimerR.setAllowNegative(false);
        nRePrimerR.setAllowBlank(false);

        nReSegunR = new NumberField();
        nReSegunR.setValue(0);
        nReSegunR.setWidth(ancho);
        nReSegunR.setMaxValue(130);
        nReSegunR.setMaxLength(3);
        nReSegunR.setAllowDecimals(false);
        nReSegunR.setAllowNegative(false);
        nReSegunR.setAllowBlank(false);

        nPePrimerR = new NumberField();
        nPePrimerR.setValue(0);
        nPePrimerR.setWidth(ancho);
        nPePrimerR.setMaxValue(130);
        nPePrimerR.setMaxLength(3);
        nPePrimerR.setAllowDecimals(false);
        nPePrimerR.setAllowNegative(false);
        nPePrimerR.setAllowBlank(false);

        nPeSegunR = new NumberField();
        nPeSegunR.setValue(0);
        nPeSegunR.setWidth(ancho);
        nPeSegunR.setMaxValue(130);
        nPeSegunR.setMaxLength(3);
        nPeSegunR.setAllowDecimals(false);
        nPeSegunR.setAllowNegative(false);
        nPeSegunR.setAllowBlank(false);

        nEePrimerR = new NumberField();
        nEePrimerR.setValue(0);
        nEePrimerR.setWidth(ancho);
        nEePrimerR.setMaxValue(130);
        nEePrimerR.setMaxLength(3);
        nEePrimerR.setAllowDecimals(false);
        nEePrimerR.setAllowNegative(false);
        nEePrimerR.setAllowBlank(false);

        nEeSegunR = new NumberField();
        nEeSegunR.setValue(0);
        nEeSegunR.setWidth(ancho);
        nEeSegunR.setMaxValue(130);
        nEeSegunR.setMaxLength(3);
        nEeSegunR.setAllowDecimals(false);
        nEeSegunR.setAllowNegative(false);
        nEeSegunR.setAllowBlank(false);

        nClPrimerR = new NumberField();
        nClPrimerR.setValue(0);
        nClPrimerR.setWidth(ancho);
        nClPrimerR.setMaxValue(130);
        nClPrimerR.setMaxLength(3);
        nClPrimerR.setAllowDecimals(false);
        nClPrimerR.setAllowNegative(false);
        nClPrimerR.setAllowBlank(false);

        nClSegunR = new NumberField();
        nClSegunR.setValue(0);
        nClSegunR.setWidth(ancho);
        nClSegunR.setMaxValue(130);
        nClSegunR.setMaxLength(3);
        nClSegunR.setAllowDecimals(false);
        nClSegunR.setAllowNegative(false);
        nClSegunR.setAllowBlank(false);
//      /////////////////////////////////////////////////////////////////////////////////////////

        tableSituaciones.setHTML(0, 0, "<div style='font-size: 13px;'><center><b>Situación Juego</b></center></span>");
//        tableSituaciones.setHTML(0, 0, "<b>Situación</b>");
        tableSituaciones.setHTML(0, 1, "<div style='font-size: 13px;'><b>1er Poli</b></span>");
        tableSituaciones.setHTML(0, 2, "<div style='font-size: 13px;'><b>2do Poli</b></span>");
        tableSituaciones.setHTML(0, 3, "<div style='font-size: 13px;'><b>1er Rival</b></span>");
        tableSituaciones.setHTML(0, 4, "<div style='font-size: 13px;'><b>2do Rival</b></span>");

        tableSituaciones.setWidget(1, 0, btnFz1);
        tableSituaciones.setWidget(1, 1, nFz1Primer);
        tableSituaciones.setWidget(1, 2, nFz1Segun);
        tableSituaciones.setWidget(1, 3, nFz1PrimerR);
        tableSituaciones.setWidget(1, 4, nFz1SegunR);

        tableSituaciones.setWidget(2, 0, btnFz2);
        tableSituaciones.setWidget(2, 1, nFz2Primer);
        tableSituaciones.setWidget(2, 2, nFz2Segun);
        tableSituaciones.setWidget(2, 3, nFz2PrimerR);
        tableSituaciones.setWidget(2, 4, nFz2SegunR);

        tableSituaciones.setWidget(3, 0, btnFz3);
        tableSituaciones.setWidget(3, 1, nFz3Primer);
        tableSituaciones.setWidget(3, 2, nFz3Segun);
        tableSituaciones.setWidget(3, 3, nFz3PrimerR);
        tableSituaciones.setWidget(3, 4, nFz3SegunR);

        tableSituaciones.setWidget(4, 0, btnRb1);
        tableSituaciones.setWidget(4, 1, nRbz1Primer);
        tableSituaciones.setWidget(4, 2, nRbz1Segun);
        tableSituaciones.setWidget(4, 3, nRbz1PrimerR);
        tableSituaciones.setWidget(4, 4, nRbz1SegunR);

        tableSituaciones.setWidget(5, 0, btnRb2);
        tableSituaciones.setWidget(5, 1, nRbz2Primer);
        tableSituaciones.setWidget(5, 2, nRbz2Segun);
        tableSituaciones.setWidget(5, 3, nRbz2PrimerR);
        tableSituaciones.setWidget(5, 4, nRbz2SegunR);

        tableSituaciones.setWidget(6, 0, btnRb3);
        tableSituaciones.setWidget(6, 1, nRbz3Primer);
        tableSituaciones.setWidget(6, 2, nRbz3Segun);
        tableSituaciones.setWidget(6, 3, nRbz3PrimerR);
        tableSituaciones.setWidget(6, 4, nRbz3SegunR);

        tableSituaciones.setWidget(7, 0, btnTl1);
        tableSituaciones.setWidget(7, 1, nTlz1Primer);
        tableSituaciones.setWidget(7, 2, nTlz1Segun);
        tableSituaciones.setWidget(7, 3, nTlz1PrimerR);
        tableSituaciones.setWidget(7, 4, nTlz1SegunR);

        tableSituaciones.setWidget(8, 0, btnTl2);
        tableSituaciones.setWidget(8, 1, nTlz2Primer);
        tableSituaciones.setWidget(8, 2, nTlz2Segun);
        tableSituaciones.setWidget(8, 3, nTlz2PrimerR);
        tableSituaciones.setWidget(8, 4, nTlz2SegunR);

        tableSituaciones.setWidget(9, 0, btnTl3);
        tableSituaciones.setWidget(9, 1, nTlz3Primer);
        tableSituaciones.setWidget(9, 2, nTlz3Segun);
        tableSituaciones.setWidget(9, 3, nTlz3PrimerR);
        tableSituaciones.setWidget(9, 4, nTlz3SegunR);

        tableSituaciones.setWidget(10, 0, btnFl);
        tableSituaciones.setWidget(10, 1, nFlPrimer);
        tableSituaciones.setWidget(10, 2, nFlSegun);
        tableSituaciones.setWidget(10, 3, nFlPrimerR);
        tableSituaciones.setWidget(10, 4, nFlSegunR);

        tableSituaciones.setWidget(11, 0, btnTe);
        tableSituaciones.setWidget(11, 1, nTePrimer);
        tableSituaciones.setWidget(11, 2, nTeSegun);
        tableSituaciones.setWidget(11, 3, nTePrimerR);
        tableSituaciones.setWidget(11, 4, nTeSegunR);

        tableSituaciones.setWidget(12, 0, btnOg);
        tableSituaciones.setWidget(12, 1, nOgPrimer);
        tableSituaciones.setWidget(12, 2, nOgSegun);
        tableSituaciones.setWidget(12, 3, nOgPrimerR);
        tableSituaciones.setWidget(12, 4, nOgSegunR);

        tableSituaciones.setWidget(13, 0, btnRe);
        tableSituaciones.setWidget(13, 1, nRePrimer);
        tableSituaciones.setWidget(13, 2, nReSegun);
        tableSituaciones.setWidget(13, 3, nRePrimerR);
        tableSituaciones.setWidget(13, 4, nReSegunR);

        tableSituaciones.setWidget(14, 0, btnPe);
        tableSituaciones.setWidget(14, 1, nPePrimer);
        tableSituaciones.setWidget(14, 2, nPeSegun);
        tableSituaciones.setWidget(14, 3, nPePrimerR);
        tableSituaciones.setWidget(14, 4, nPeSegunR);

        tableSituaciones.setWidget(15, 0, btnEe);
        tableSituaciones.setWidget(15, 1, nEePrimer);
        tableSituaciones.setWidget(15, 2, nEeSegun);
        tableSituaciones.setWidget(15, 3, nEePrimerR);
        tableSituaciones.setWidget(15, 4, nEeSegunR);

        tableSituaciones.setWidget(16, 0, btnCl);
        tableSituaciones.setWidget(16, 1, nClPrimer);
        tableSituaciones.setWidget(16, 2, nClSegun);
        tableSituaciones.setWidget(16, 3, nClPrimerR);
        tableSituaciones.setWidget(16, 4, nClSegunR);
////////////////////////////////////////////////////////////////////////////////
//     
//        tabItemAnfitrion.add(new Text("Hola"));
        cpSituacionesJuego.add(tableSituaciones);

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

        RadioGroup radioGroupTiepo = new RadioGroup();
        radioGroupTiepo.setFieldLabel("Tiempo");
        radioGroupTiepo.add(rd1Tiempo);
        radioGroupTiepo.add(rd2Tiempo);
        simple.add(radioGroupTiepo, formData);

        rdAnfitrion = new Radio();
        rdAnfitrion.setBoxLabel("POLITÉCNICO JIC");
        rdAnfitrion.setValue(true);

        rdRival = new Radio();
        rdRival.setBoxLabel("RIVAL");

        RadioGroup radioGroupEquipo = new RadioGroup();
        radioGroupEquipo.setFieldLabel("Equipo");
        radioGroupEquipo.add(rdAnfitrion);
        radioGroupEquipo.add(rdRival);
        simple.add(radioGroupEquipo, formData);

        NumberField nCantidad = new NumberField();
        nCantidad.setValue(0);
        nCantidad.setMaxValue(130);
        nCantidad.setMaxLength(3);
        nCantidad.setAllowDecimals(false);
        nCantidad.setAllowNegative(false);
        nCantidad.setAllowBlank(false);

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

    public void buscarSituacionesxCompetencia(Long idCompetenc, final boolean habilitar) {
          setIdCompetencia(idCompetenc);
        getServiceSituaciones().getSituacionesXCompetencia(idCompetenc, new AsyncCallback<List<SituacionesJuegoCompe>>() {

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

    public void cargarSitucionesJuego(List<SituacionesJuegoCompe> situacionesJuegoCompes, boolean habilitados) {

        for (SituacionesJuegoCompe situacionesJuegoCompe : situacionesJuegoCompes) {
            if (situacionesJuegoCompe.getEquipoSituacion().equalsIgnoreCase("POLITECNICO JIC")) {
                if (situacionesJuegoCompe.getTiempoSituacion().intValue() == 1) {
                    nFz1Primer.setValue(situacionesJuegoCompe.getFaltaZona1());
                    nFz1Primer.setEnabled(habilitados);
                    nFz2Primer.setValue(situacionesJuegoCompe.getFaltaZona2());
                    nFz2Primer.setEnabled(habilitados);
                    nFz3Primer.setValue(situacionesJuegoCompe.getFaltaZona3());
                    nFz3Primer.setEnabled(habilitados);
                    nRbz1Primer.setValue(situacionesJuegoCompe.getRecuperacionZona1());
                    nRbz1Primer.setEnabled(habilitados);
                    nRbz2Primer.setValue(situacionesJuegoCompe.getRecuperacionZona2());
                    nRbz2Primer.setEnabled(habilitados);
                    nRbz3Primer.setValue(situacionesJuegoCompe.getRecuperacionZona3());
                    nRbz3Primer.setEnabled(habilitados);
                    nTlz1Primer.setValue(situacionesJuegoCompe.getTiroLibreZona1());
                    nTlz1Primer.setEnabled(habilitados);
                    nTlz2Primer.setValue(situacionesJuegoCompe.getTiroLibreZona2());
                    nTlz2Primer.setEnabled(habilitados);
                    nTlz3Primer.setValue(situacionesJuegoCompe.getTiroLibreZona3());
                    nTlz3Primer.setEnabled(habilitados);
                    nTePrimer.setValue(situacionesJuegoCompe.getTiroEsquina());
                    nTePrimer.setEnabled(habilitados);
                    nFlPrimer.setValue(situacionesJuegoCompe.getFueraLugar());
                    nFlPrimer.setEnabled(habilitados);
                    nPePrimer.setValue(situacionesJuegoCompe.getPenalty());
                    nPePrimer.setEnabled(habilitados);
                    nOgPrimer.setValue(situacionesJuegoCompe.getOpcionGol());
                    nOgPrimer.setEnabled(habilitados);
                    nRePrimer.setValue(situacionesJuegoCompe.getRemates());
                    nRePrimer.setEnabled(habilitados);
                    nEePrimer.setValue(situacionesJuegoCompe.getEntregasErradas());
                    nEePrimer.setEnabled(habilitados);
                    nClPrimer.setValue(situacionesJuegoCompe.getCentrolLateral());
                    nClPrimer.setEnabled(habilitados);
                } else {
                    nFz1Segun.setValue(situacionesJuegoCompe.getFaltaZona1());
                    nFz1Segun.setEnabled(habilitados);
                    nFz2Segun.setValue(situacionesJuegoCompe.getFaltaZona2());
                    nFz2Segun.setEnabled(habilitados);
                    nFz3Segun.setValue(situacionesJuegoCompe.getFaltaZona3());
                    nFz3Segun.setEnabled(habilitados);
                    nRbz1Segun.setValue(situacionesJuegoCompe.getRecuperacionZona1());
                    nRbz1Segun.setEnabled(habilitados);
                    nRbz2Segun.setValue(situacionesJuegoCompe.getRecuperacionZona2());
                    nRbz2Segun.setEnabled(habilitados);
                    nRbz3Segun.setValue(situacionesJuegoCompe.getRecuperacionZona3());
                    nRbz3Segun.setEnabled(habilitados);
                    nTlz1Segun.setValue(situacionesJuegoCompe.getTiroLibreZona1());
                    nTlz1Segun.setEnabled(habilitados);
                    nTlz2Segun.setValue(situacionesJuegoCompe.getTiroLibreZona2());
                    nTlz2Segun.setEnabled(habilitados);
                    nTlz3Segun.setValue(situacionesJuegoCompe.getTiroLibreZona3());
                    nTlz3Segun.setEnabled(habilitados);
                    nTeSegun.setValue(situacionesJuegoCompe.getTiroEsquina());
                    nTeSegun.setEnabled(habilitados);
                    nFlSegun.setValue(situacionesJuegoCompe.getFueraLugar());
                    nFlSegun.setEnabled(habilitados);
                    nPeSegun.setValue(situacionesJuegoCompe.getPenalty());
                    nPeSegun.setEnabled(habilitados);
                    nOgSegun.setValue(situacionesJuegoCompe.getOpcionGol());
                    nOgSegun.setEnabled(habilitados);
                    nReSegun.setValue(situacionesJuegoCompe.getRemates());
                    nReSegun.setEnabled(habilitados);
                    nEeSegun.setValue(situacionesJuegoCompe.getEntregasErradas());
                    nEeSegun.setEnabled(habilitados);
                    nClSegun.setValue(situacionesJuegoCompe.getCentrolLateral());
                    nClSegun.setEnabled(habilitados);
                }
            } else if (situacionesJuegoCompe.getEquipoSituacion().equalsIgnoreCase("RIVAL")) {
                if (situacionesJuegoCompe.getTiempoSituacion().intValue() == 1) {

                    nFz1PrimerR.setValue(situacionesJuegoCompe.getFaltaZona1());
                    nFz1PrimerR.setEnabled(habilitados);
                    nFz2PrimerR.setValue(situacionesJuegoCompe.getFaltaZona2());
                    nFz2PrimerR.setEnabled(habilitados);
                    nFz3PrimerR.setValue(situacionesJuegoCompe.getFaltaZona3());
                    nFz3PrimerR.setEnabled(habilitados);
                    nRbz1PrimerR.setValue(situacionesJuegoCompe.getRecuperacionZona1());
                    nRbz1PrimerR.setEnabled(habilitados);
                    nRbz2PrimerR.setValue(situacionesJuegoCompe.getRecuperacionZona2());
                    nRbz2PrimerR.setEnabled(habilitados);
                    nRbz3PrimerR.setValue(situacionesJuegoCompe.getRecuperacionZona3());
                    nRbz3PrimerR.setEnabled(habilitados);
                    nTlz1PrimerR.setValue(situacionesJuegoCompe.getTiroLibreZona1());
                    nTlz1PrimerR.setEnabled(habilitados);
                    nTlz2PrimerR.setValue(situacionesJuegoCompe.getTiroLibreZona2());
                    nTlz2PrimerR.setEnabled(habilitados);
                    nTlz3PrimerR.setValue(situacionesJuegoCompe.getTiroLibreZona3());
                    nTlz3PrimerR.setEnabled(habilitados);
                    nTePrimerR.setValue(situacionesJuegoCompe.getTiroEsquina());
                    nTePrimerR.setEnabled(habilitados);
                    nFlPrimerR.setValue(situacionesJuegoCompe.getFueraLugar());
                    nFlPrimerR.setEnabled(habilitados);
                    nPePrimerR.setValue(situacionesJuegoCompe.getPenalty());
                    nPePrimerR.setEnabled(habilitados);
                    nOgPrimerR.setValue(situacionesJuegoCompe.getOpcionGol());
                    nOgPrimerR.setEnabled(habilitados);
//                    situaAnfrition1.setCentrolLateral(0);
                    nRePrimerR.setValue(situacionesJuegoCompe.getRemates());
                    nRePrimerR.setEnabled(habilitados);
                    nEePrimerR.setValue(situacionesJuegoCompe.getEntregasErradas());
                    nEePrimerR.setEnabled(habilitados);
                    nClPrimerR.setValue(situacionesJuegoCompe.getCentrolLateral());
                    nClPrimerR.setEnabled(habilitados);

                } else {

                    nFz1SegunR.setValue(situacionesJuegoCompe.getFaltaZona1());
                    nFz1SegunR.setEnabled(habilitados);
                    nFz2SegunR.setValue(situacionesJuegoCompe.getFaltaZona2());
                    nFz2SegunR.setEnabled(habilitados);
                    nFz3SegunR.setValue(situacionesJuegoCompe.getFaltaZona3());
                    nFz3SegunR.setEnabled(habilitados);
                    nRbz1SegunR.setValue(situacionesJuegoCompe.getRecuperacionZona1());
                    nRbz1SegunR.setEnabled(habilitados);
                    nRbz2SegunR.setValue(situacionesJuegoCompe.getRecuperacionZona2());
                    nRbz2SegunR.setEnabled(habilitados);
                    nRbz3SegunR.setValue(situacionesJuegoCompe.getRecuperacionZona3());
                    nRbz3SegunR.setEnabled(habilitados);
                    nTlz1SegunR.setValue(situacionesJuegoCompe.getTiroLibreZona1());
                    nTlz1SegunR.setEnabled(habilitados);
                    nTlz2SegunR.setValue(situacionesJuegoCompe.getTiroLibreZona2());
                    nTlz2SegunR.setEnabled(habilitados);
                    nTlz3SegunR.setValue(situacionesJuegoCompe.getTiroLibreZona3());
                    nTlz3SegunR.setEnabled(habilitados);
                    nTeSegunR.setValue(situacionesJuegoCompe.getTiroEsquina());
                    nTeSegunR.setEnabled(habilitados);
                    nFlSegunR.setValue(situacionesJuegoCompe.getFueraLugar());
                    nFlSegunR.setEnabled(habilitados);
                    nPeSegunR.setValue(situacionesJuegoCompe.getPenalty());
                    nPeSegunR.setEnabled(habilitados);
                    nOgSegunR.setValue(situacionesJuegoCompe.getOpcionGol());
                    nOgSegunR.setEnabled(habilitados);
                    nReSegunR.setValue(situacionesJuegoCompe.getRemates());
                    nReSegunR.setEnabled(habilitados);
                    nEeSegunR.setValue(situacionesJuegoCompe.getEntregasErradas());
                    nEeSegunR.setEnabled(habilitados);
                    nClSegunR.setValue(situacionesJuegoCompe.getCentrolLateral());
                    nClSegunR.setEnabled(habilitados);

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
        btnCl.setEnabled(habilitar);
        btnGuardarSituaciones.setVisible(habilitar);

    }

    public void reiniciarSituaciones() {
        boolean habilitado = true;
        setIdCompetencia(null);
        nFz1Primer.setEnabled(habilitado);
        nFz1Primer.setValue(0);

        nFz2Primer.setEnabled(habilitado);
        nFz2Primer.setValue(0);

        nFz3Primer.setEnabled(habilitado);
        nFz3Primer.setValue(0);

        nRbz1Primer.setEnabled(habilitado);
        nRbz1Primer.setValue(0);
        
        nRbz2Primer.setEnabled(habilitado);
        nRbz2Primer.setValue(0);
        
        nRbz3Primer.setEnabled(habilitado);
        nRbz3Primer.setValue(0);
                
        nTlz1Primer.setEnabled(habilitado);
        nTlz1Primer.setValue(0);
        
        nTlz2Primer.setEnabled(habilitado);
        nTlz2Primer.setValue(0);
        
        nTlz3Primer.setEnabled(habilitado);
        nTlz3Primer.setValue(0);
        
        nTePrimer.setEnabled(habilitado);
        nTePrimer.setValue(0);
        
        nFlPrimer.setEnabled(habilitado);
        nFlPrimer.setValue(0);
        
        nPePrimer.setEnabled(habilitado);
        nPePrimer.setValue(0);
        
        nOgPrimer.setEnabled(habilitado);
        nOgPrimer.setValue(0);
        
        nRePrimer.setEnabled(habilitado);
        nRePrimer.setValue(0);
        
        nEePrimer.setEnabled(habilitado);
        nEePrimer.setValue(0);
        
        nClPrimer.setEnabled(habilitado);
        nClPrimer.setValue(0);

        nFz1Segun.setEnabled(habilitado);
        nFz1Segun.setValue(0);
        
        nFz2Segun.setEnabled(habilitado);
        nFz2Segun.setValue(0);
        
        nFz3Segun.setEnabled(habilitado);
        nFz3Segun.setValue(0);
        
        nRbz1Segun.setEnabled(habilitado);
        nRbz1Segun.setValue(0);
        
        nRbz2Segun.setEnabled(habilitado);
        nRbz2Segun.setValue(0);
        
        nRbz3Segun.setEnabled(habilitado);
        nRbz3Segun.setValue(0);
        
        nTlz1Segun.setEnabled(habilitado);
        nTlz1Segun.setValue(0);
        
        nTlz2Segun.setEnabled(habilitado);
        nTlz2Segun.setValue(0);
        
        nTlz3Segun.setEnabled(habilitado);
        nTlz3Segun.setValue(0);
        
        nTeSegun.setEnabled(habilitado);
        nTeSegun.setValue(0);
        
        nFlSegun.setEnabled(habilitado);
        nFlSegun.setValue(0);
        
        nPeSegun.setEnabled(habilitado);
        nPeSegun.setValue(0);
        
        nOgSegun.setEnabled(habilitado);
        nOgSegun.setValue(0);
        
        nReSegun.setEnabled(habilitado);
        nReSegun.setValue(0);
        
        nEeSegun.setEnabled(habilitado);
        nEeSegun.setValue(0);
        
        nClSegun.setEnabled(habilitado);
        nClSegun.setValue(0);

        nFz1PrimerR.setEnabled(habilitado);
        nFz1PrimerR.setValue(0);
        
        nFz2PrimerR.setEnabled(habilitado);
        nFz2PrimerR.setValue(0);
        
        nFz3PrimerR.setEnabled(habilitado);
        nFz3PrimerR.setValue(0);
        
        nRbz1PrimerR.setEnabled(habilitado);
        nRbz1PrimerR.setValue(0);
        
        nRbz2PrimerR.setEnabled(habilitado);
        nRbz2PrimerR.setValue(0);
        
        nRbz3PrimerR.setEnabled(habilitado);
        nRbz3PrimerR.setValue(0);
        
        nTlz1PrimerR.setEnabled(habilitado);
        nTlz1PrimerR.setValue(0);
        
        nTlz2PrimerR.setEnabled(habilitado);
        nTlz2PrimerR.setValue(0);
        
        nTlz3PrimerR.setEnabled(habilitado);
        nTlz3PrimerR.setValue(0);
        
        nTePrimerR.setEnabled(habilitado);
        nTePrimerR.setValue(0);
        
        nFlPrimerR.setEnabled(habilitado);
        nFlPrimerR.setValue(0);
        
        nPePrimerR.setEnabled(habilitado);
        nPePrimerR.setValue(0);
        
        nOgPrimerR.setEnabled(habilitado);
        nOgPrimerR.setValue(0);
        
        nRePrimerR.setEnabled(habilitado);
        nRePrimerR.setValue(0);
        
        nEePrimerR.setEnabled(habilitado);
        nEePrimerR.setValue(0);
        
        nClPrimerR.setEnabled(habilitado);
        nClPrimerR.setValue(0);

        nFz1SegunR.setEnabled(habilitado);
        nFz1SegunR.setValue(0);
        
        nFz2SegunR.setEnabled(habilitado);
        nFz2SegunR.setValue(0);
        
        nFz3SegunR.setEnabled(habilitado);
        nFz3SegunR.setValue(0);
        
        nRbz1SegunR.setEnabled(habilitado);
        nRbz1SegunR.setValue(0);
        
        nRbz2SegunR.setEnabled(habilitado);
        nRbz2SegunR.setValue(0);
        
        nRbz3SegunR.setEnabled(habilitado);
        nRbz3SegunR.setValue(0);
        
        nTlz1SegunR.setEnabled(habilitado);
        nTlz1SegunR.setValue(0);
        
        nTlz2SegunR.setEnabled(habilitado);
        nTlz2SegunR.setValue(0);
        
        nTlz3SegunR.setEnabled(habilitado);
        nTlz3SegunR.setValue(0);
        
        nTeSegunR.setEnabled(habilitado);
        nTeSegunR.setValue(0);
        
        nFlSegunR.setEnabled(habilitado);
        nFlSegunR.setValue(0);
        
        nPeSegunR.setEnabled(habilitado);
        nPeSegunR.setValue(0);
        
        nOgSegunR.setEnabled(habilitado);
        nOgSegunR.setValue(0);
        
        nReSegunR.setEnabled(habilitado);
        nReSegunR.setValue(0);
        
        nEeSegunR.setEnabled(habilitado);
        nEeSegunR.setValue(0);
        
        nClSegunR.setEnabled(habilitado);
        nClSegunR.setValue(0);

        habilitarBotonesSituaciones(habilitado);

        btnGuardarSituaciones.setVisible(habilitado);

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
}
